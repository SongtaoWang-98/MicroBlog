package com.stewart.microblog.service;

import com.stewart.microblog.enums.RegisterCode;
import com.stewart.microblog.enums.StatusCode;

public interface SignUpService {
    RegisterCode signUp(String username, String nickname, String password);
}
