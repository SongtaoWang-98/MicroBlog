package com.stewart.microblog.controller;

import com.stewart.microblog.aop.SystemControllerLog;
import com.stewart.microblog.exception.BizException;
import com.stewart.microblog.service.HomeService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 博客首页控制层类
 * @author 王松涛
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    @Resource
    private HomeService homeService;

    @SystemControllerLog(description = "展示热门博文")
    @GetMapping("/hot")
    public ResultVO hotBlogs() {
        return ResultVOUtil.success(homeService.showHotBlogsHome());
    }

    @SystemControllerLog(description = "展示最新博文")
    @GetMapping("/new")
    public ResultVO newBlogs() {
        return ResultVOUtil.success(homeService.showNewBlogsHome());
    }

    @SystemControllerLog(description = "展示好友圈博文")
    @GetMapping("/friends")
    public ResultVO friendsBlogs() {
        return ResultVOUtil.success(homeService.showFriendsBlogsHome());
    }

    @GetMapping("/group")
    @SystemControllerLog(description = "按分组展示博文")
    public ResultVO groupBlogs(Integer groupId) {
        if(groupId == null) {
            throw new BizException("-1", "分组id不能为空");
        }
        return ResultVOUtil.success(homeService.showGroupBlogsHome(groupId));
    }
}
