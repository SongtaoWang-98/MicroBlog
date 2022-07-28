package com.stewart.microblog.controller;

import com.stewart.microblog.exception.BizException;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/")
public class LoginController {
    @PostMapping("/userLogin")
    public ResultVO collectBlog(String userName, String password) {

        return ResultVOUtil.success(null);
    }
}
