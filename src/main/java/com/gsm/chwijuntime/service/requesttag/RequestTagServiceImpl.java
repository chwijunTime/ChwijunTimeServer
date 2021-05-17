package com.gsm.chwijuntime.service.requesttag;

import com.gsm.chwijuntime.advice.exception.CAuthenticationEntryPointException;
import com.gsm.chwijuntime.dto.requesttag.RequestTagResDto;
import com.gsm.chwijuntime.dto.requesttag.RequestTagSaveDto;
import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.model.RequestTag;
import com.gsm.chwijuntime.repository.MemberRepository;
import com.gsm.chwijuntime.repository.RequestTagRepository;
import com.gsm.chwijuntime.util.GetUserEmailUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RequestTagServiceImpl implements RequestTagService {

    private final MemberRepository memberRepository;
    private final RequestTagRepository requestTagRepository;
    private final GetUserEmailUtil getUserEmailUtil;
    private final ModelMapper mapper;

    @Transactional
    @Override
    public void saveTag(RequestTagSaveDto requestTagSaveDto) {
        Member member = memberRepository.findByMemberEmail(getUserEmailUtil.getUserEmail()).orElseThrow(CAuthenticationEntryPointException::new);

        // 중복 처리
        Optional<RequestTag> byRTagName = requestTagRepository.findByRTagName(requestTagSaveDto.getTagName());
        if(byRTagName.isEmpty()) {
            requestTagRepository.save(requestTagSaveDto.toEntityRequestTag(member));
        } else {
            System.out.println("태그 중복");
            return;
        }
    }

    @Transactional
    @Override
    public void deleteTag(Long idx) {
        requestTagRepository.deleteById(idx);
    }

    @Override
    public List<RequestTagResDto> findAll() {
        List<RequestTagResDto> collect = requestTagRepository.searchAll()
                .stream().map(m -> mapper.map(m, RequestTagResDto.class))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public RequestTagResDto findByIdx(Long idx) {
        RequestTagResDto requestTagResDto = requestTagRepository.findById(idx).map(m -> mapper.map(m, RequestTagResDto.class)).orElseThrow(null);
        return requestTagResDto;
    }
}
