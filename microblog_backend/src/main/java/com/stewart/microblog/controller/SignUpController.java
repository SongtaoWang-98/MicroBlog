package com.stewart.microblog.controller;

import com.stewart.microblog.aop.SystemControllerLog;
import com.stewart.microblog.exception.BizException;
import com.stewart.microblog.service.SignUpService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 注册功能实现类
 * @author 王松涛
 */
@RestController
@RequestMapping("/signUp")
public class SignUpController {
    @Resource
    private SignUpService signUpService;

    /**
     * 注册功能接口
     * @param username
     * String类型用户名
     * @param nickname
     * String类型用户昵称
     * @param password
     * String类型用户密码
     * @return ResultVO
     */
    @PostMapping("/sign")
    @SystemControllerLog(description = "注册")
    public ResultVO sign(String username, String nickname, String password) {
        if(username == null || nickname == null || password == null) {
            throw new BizException("-1", "注册信息不能为空！");
        }
        return ResultVOUtil.success(signUpService.signUp(username, nickname, password));
    }
}
