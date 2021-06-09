package com.gsm.chwijuntime.service.dev;

import com.gsm.chwijuntime.model.Member;
import com.gsm.chwijuntime.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DevServiceImpl implements DevService {

    private final MemberRepository memberRepository;

    @Override
    public Map<String, String> PermissionCheck(String email) {
        Map<String, String> map = new HashMap<>();
        Optional<Member> byMemberEmail = memberRepository.findByMemberEmail(email);
        if (byMemberEmail.isEmpty()) {
            throw new IndexOutOfBoundsException("찾으시는 이메일을 칮지 못했습니다.");
        }
        String s = byMemberEmail.get().getRoles().get(0);
        if(s.equals("ROLE_User")) {
            map.put("msg", "이 계정은 유저 계정 입니다.");
        } else {
            map.put("msg", "이 계정은 관리자 계정 입니다.");
        }
        return map;
    }

    @Transactional
    @Override
    public void ChangePermissions(String email) {
        Optional<Member> byMemberEmail = memberRepository.findByMemberEmail(email);
        if (byMemberEmail.isEmpty()) {
            throw new IndexOutOfBoundsException("찾으시는 이메일을 칮지 못했습니다.");
        }
        String s = byMemberEmail.get().getRoles().get(0);
        if(s.equals("ROLE_User")) {
            byMemberEmail.get().change_roles();
        } else {
            throw new IndexOutOfBoundsException("이 계정은 이미 관리자 계정입니다.");
        }
    }
}
