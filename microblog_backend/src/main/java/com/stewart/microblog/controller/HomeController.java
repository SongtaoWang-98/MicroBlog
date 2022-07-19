package com.stewart.microblog.controller;

import com.stewart.microblog.service.HomeService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@RestController
//@CrossOrigin
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping
    public ResultVO index() {
        return ResultVOUtil.success(homeService.showHotBlogsHome());
    }

}
