package com.stewart.microblog.controller;

import com.stewart.microblog.service.BlogService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author stewartwang
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogService blogService;
    @GetMapping("/hot")
    public ResultVO hotBlogs() {
        return ResultVOUtil.success(null);
    }
}
