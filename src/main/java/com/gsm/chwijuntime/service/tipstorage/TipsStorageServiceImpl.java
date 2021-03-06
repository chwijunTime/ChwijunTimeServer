package com.gsm.chwijuntime.service.tipstorage;

import com.gsm.chwijuntime.advice.exception.AuthorNotCertifiedException;
import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.advice.exception.NotFoundTagException;
import com.gsm.chwijuntime.advice.exception.NotFoundTipsStorageException;
import com.gsm.chwijuntime.dto.tipstorage.TipsStorageResDto;
import com.gsm.chwijuntime.dto.tipstorage.TipsStorageSaveDto;
import com.gsm.chwijuntime.dto.tipstorage.TipsStorageUpdateDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.TipsStorage;
import com.gsm.chwijuntime.model.tagmapping.TipsStorageTag;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.TagRepository;
import com.gsm.chwijuntime.repository.TipsStorageRepository;
import com.gsm.chwijuntime.repository.tag.TipsStorageTagRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class TipsStorageServiceImpl implements TipsStorageService {

    private final MemberRepository memberRepository;
    private final GetUserEmailUtil getUserEmailUtil;
    private final TipsStorageRepository tipsStorageRepository;
    private final TagRepository tagRepository;
    private final ModelMapper mapper;
    private final TipsStorageTagRepository tipsStorageTagRepository;

    @Transactional
    @Override
    public void saveTipsStorage(TipsStorageSaveDto tipsStorageSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        tipsStorageRepository.save(tipsStorageSaveDto.toEntityByTipsStorage(member));
        for(String i : tipsStorageSaveDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i).orElseThrow(NotFoundTagException::new);
            List<TipsStorage> allByWorkCompanyName = tipsStorageRepository.findAllByWorkCompanyName(tipsStorageSaveDto.getWorkCompanyName());
            int size = allByWorkCompanyName.size() - 1;
            tipsStorageSaveDto.mappingTag_ContractingCompany(tag, allByWorkCompanyName.get(size));
            log.info(tipsStorageSaveDto.getWorkCompanyName());
            tipsStorageTagRepository.save(tipsStorageSaveDto.toEntityByTipsStorageTag());
        }
    }

    @Override
    public List<TipsStorageResDto> findAll() {
        List<TipsStorageResDto> collect = tipsStorageRepository.findAll().stream()
                .map(m -> mapper.map(m, TipsStorageResDto.class))
                .collect(Collectors.toList());
        return getTipsStorageResDtos(collect);
    }

    private List<TipsStorageResDto> getTipsStorageResDtos(List<TipsStorageResDto> tipsStorageResDtos) {
        for (TipsStorageResDto i : tipsStorageResDtos) {
            TipsStorage tipsStorage = tipsStorageRepository.findById(i.getTipsStorageIdx()).orElseThrow(NotFoundTipsStorageException::new);
            List<TipsStorageTag> allByTipsStorage = tipsStorageTagRepository.findAllByTipsStorage(tipsStorage);
            for (TipsStorageTag j : allByTipsStorage) {
                i.getTagName().add(j.getTag().getTagName());
            }
        }
        return tipsStorageResDtos;
    }

    @Override
    public TipsStorageResDto findByIdx(Long idx) {
        TipsStorageResDto tipsStorageResDto = tipsStorageRepository.findById(idx).map(m -> mapper.map(m, TipsStorageResDto.class)).orElseThrow(NotFoundTipsStorageException::new);
        TipsStorage tipsStorage = tipsStorageRepository.findById(idx).orElseThrow(NotFoundTipsStorageException::new);
        List<TipsStorageTag> allByTipsStorage = tipsStorageTagRepository.findAllByTipsStorage(tipsStorage);
        for (TipsStorageTag i : allByTipsStorage) {
            tipsStorageResDto.getTagName().add(i.getTag().getTagName());
        }
        return tipsStorageResDto;

    }

    @Transactional
    @Override
    public void updateTipsStorage(Long idx, TipsStorageUpdateDto tipsStorageUpdateDto) {
        userWriteCheck(idx);
        TipsStorage tipsStorage = tipsStorageRepository.findById(idx).orElseThrow(NotFoundTipsStorageException::new);
        // 1?????? ??????
        tipsStorage.update(tipsStorageUpdateDto);
        List<TipsStorageTag> allByTipsStorage = tipsStorageTagRepository.findAllByTipsStorage(tipsStorage);
        // ????????? ?????? ?????? ??????
        for (TipsStorageTag tipsStorageTag : allByTipsStorage) {
            tipsStorageTagRepository.delete(tipsStorageTag);
        }
        // ?????? ??????
        for(String i : tipsStorageUpdateDto.getTagName()) {
            Tag tag = tagRepository.findByTagName(i).orElseThrow(NotFoundTagException::new);
            tipsStorageUpdateDto.mappingTag_ContractingCompany(tag, tipsStorage);
            tipsStorageTagRepository.save(tipsStorageUpdateDto.toEntityByTipsStorageTag());
        }
    }

    @Transactional
    @Override
    public void deleteTipsStorage(Long idx) {
        userWriteCheck(idx);
        TipsStorage tipsStorage = tipsStorageRepository.findById(idx).orElseThrow(NotFoundTipsStorageException::new);
        tipsStorageTagRepository.deleteAllByTipsStorage(tipsStorage);
        tipsStorageRepository.deleteById(idx);
    }

    @Override
    public List<TipsStorageResDto> findByWorkCompanyName(String keyword) {
        List<TipsStorageResDto> collect = tipsStorageRepository.searchByWorkCompanyName(keyword).stream()
                .map(m -> mapper.map(m, TipsStorageResDto.class))
                .collect(Collectors.toList());
        return getTipsStorageResDtos(collect);
    }

    @Override
    public List<TipsStorageResDto> findByMember() {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        List<TipsStorageResDto> collect = tipsStorageRepository.findByMember(member).stream()
                .map(m -> mapper.map(m, TipsStorageResDto.class))
                .collect(Collectors.toList());
        return getTipsStorageResDtos(collect);
    }

    //????????? ?????? ?????? Method
    private void userWriteCheck(Long idx) {
        Member CurrentUser = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);
        Member WriteMember = tipsStorageRepository.findById(idx).orElseThrow(CAuthenticationEntryPointException::new).getMember();
        if (!CurrentUser.getMemberEmail().equals(WriteMember.getMemberEmail())) {
            throw new AuthorNotCertifiedException();
        }
    }
}
