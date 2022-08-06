package com.stewart.microblog.controller;

import com.stewart.microblog.aop.SystemControllerLog;
import com.stewart.microblog.exception.BizException;
import com.stewart.microblog.service.ManageService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * 管理员控制层类
 * @author 王松涛
 */
@RestController
@RequestMapping("/manage")
public class ManageController {

    @Resource
    private ManageService manageService;

    @GetMapping("/userList")
    @SystemControllerLog(description = "展示全部用户列表")
    public ResultVO showAllUsers() {
        return ResultVOUtil.success(manageService.showUserList());
    }

    @PostMapping("/ban")
    @SystemControllerLog(description = "封禁用户")
    public ResultVO banUser(Integer userId) {
        return ResultVOUtil.success(manageService.banUser(userId));
    }

    @PostMapping("/unban")
    @SystemControllerLog(description = "解封用户")
    public ResultVO unbanUser(Integer userId) {
        return ResultVOUtil.success(manageService.unbanUser(userId));
    }

    @GetMapping("/statistics")
    @SystemControllerLog(description = "统计信息")
    public ResultVO showStatistics() throws ParseException {
        return ResultVOUtil.success(manageService.showTotalStat());
    }

    @GetMapping("/blogList")
    @SystemControllerLog(description = "展示全部博文列表")
    public ResultVO showAllBlogs() {
        return ResultVOUtil.success(manageService.showBlogList());
    }

    @PostMapping("/deleteBlog")
    @SystemControllerLog(description = "删除博文")
    public ResultVO deleteBlog(Integer blogId) {
        return ResultVOUtil.success(manageService.deleteBlog(blogId));
    }

    @GetMapping("/topicList")
    @SystemControllerLog(description = "展示全部话题列表")
    public ResultVO showAllTopics() {
        return ResultVOUtil.success(manageService.showTopicList());
    }

    @PostMapping("/deleteTopic")
    @SystemControllerLog(description = "删除话题")
    public ResultVO deleteTopic(Integer topicId) {
        return ResultVOUtil.success(manageService.deleteTopic(topicId));
    }

    @GetMapping("/topicTrend")
    @SystemControllerLog(description = "展示全部话题列表")
    public ResultVO showTopicTrend(Integer topicId) {
        return ResultVOUtil.success(manageService.showTopicTrend(topicId));
    }
}
