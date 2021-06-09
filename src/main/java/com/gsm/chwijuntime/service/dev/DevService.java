package com.gsm.chwijuntime.service.dev;

import java.util.Map;

public interface DevService {

    // 이메일로 권한 검사
    Map<String, String> PermissionCheck(String email);

    // 이메일로 권한 바꾸기
    void ChangePermissions(String email);
}
