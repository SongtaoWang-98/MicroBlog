package com.stewart.microblog.controller;

import com.stewart.microblog.service.HomeService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/hot")
    public ResultVO hotBlogs() {
        return ResultVOUtil.success(homeService.showHotBlogsHome());
    }

    @GetMapping("/new")
    public ResultVO newBlogs() {
        return ResultVOUtil.success(homeService.showNewBlogsHome());
    }

    @GetMapping("/friends")
    public ResultVO friendsBlogs() {
        return ResultVOUtil.success(homeService.showFriendsBlogsHome());
    }

    @GetMapping("/group/{groupId}")
    public ResultVO groupBlogs(@PathVariable Integer groupId) {
        return ResultVOUtil.success(homeService.showGroupBlogsHome(groupId));
    }

//    @GetMapping
//    public ResultVO test() {
//        return ResultVOUtil.success(homeService.testFindNickName());
//    }

}
