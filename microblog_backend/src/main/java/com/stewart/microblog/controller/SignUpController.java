package com.stewart.microblog.controller;

import com.stewart.microblog.aop.SystemControllerLog;
import com.stewart.microblog.service.SignUpService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/signUp")
public class SignUpController {
    @Resource
    private SignUpService signUpService;

    @PostMapping("/sign")
    @SystemControllerLog(description = "注册")
    public ResultVO searchByContent(String username, String nickname, String password) {
        return ResultVOUtil.success(signUpService.signUp(username, nickname, password));
    }
}
