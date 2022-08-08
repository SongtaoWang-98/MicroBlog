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

    /**
     * 管理员查看全部用户列表接口
     * @return ResultVO
     */
    @GetMapping("/userList")
    @SystemControllerLog(description = "展示全部用户列表")
    public ResultVO showAllUsers() {
        return ResultVOUtil.success(manageService.showUserList());
    }

    /**
     * 管理员封禁用户接口
     * @param userId
     * Integer类型用户id
     * @return ResultVO
     */
    @PostMapping("/ban")
    @SystemControllerLog(description = "封禁用户")
    public ResultVO banUser(Integer userId) {
        if(userId == null) {
            throw new BizException("-1", "用户id不能为空");
        }
        return ResultVOUtil.success(manageService.banUser(userId));
    }

    /**
     * 管理员解封用户接口
     * @param userId
     * Integer类型用户id
     * @return ResultVO
     */
    @PostMapping("/unban")
    @SystemControllerLog(description = "解封用户")
    public ResultVO unbanUser(Integer userId) {
        if(userId == null) {
            throw new BizException("-1", "用户id不能为空");
        }
        return ResultVOUtil.success(manageService.unbanUser(userId));
    }

    /**
     * 管理员查看统计信息接口
     * @return ResultVO
     */
    @GetMapping("/statistics")
    @SystemControllerLog(description = "统计信息")
    public ResultVO showStatistics() throws ParseException {
        return ResultVOUtil.success(manageService.showTotalStat());
    }

    /**
     * 管理员查看全部博文列表接口
     * @return ResultVO
     */
    @GetMapping("/blogList")
    @SystemControllerLog(description = "展示全部博文列表")
    public ResultVO showAllBlogs() {
        return ResultVOUtil.success(manageService.showBlogList());
    }

    /**
     * 管理员删除博文接口
     * @param blogId
     * Integer类型博文id
     * @return ResultVO
     */
    @PostMapping("/deleteBlog")
    @SystemControllerLog(description = "删除博文")
    public ResultVO deleteBlog(Integer blogId) {
        if(blogId == null) {
            throw new BizException("-1", "博文id不能为空");
        }
        return ResultVOUtil.success(manageService.deleteBlog(blogId));
    }

    /**
     * 管理员查看全部话题列表接口
     * @return ResultVO
     */
    @GetMapping("/topicList")
    @SystemControllerLog(description = "展示全部话题列表")
    public ResultVO showAllTopics() {
        return ResultVOUtil.success(manageService.showTopicList());
    }

    /**
     * 管理员删除话题接口
     * @param topicId
     * Integer类型话题id
     * @return ResultVO
     */
    @PostMapping("/deleteTopic")
    @SystemControllerLog(description = "删除话题")
    public ResultVO deleteTopic(Integer topicId) {
        if(topicId == null) {
            throw new BizException("-1", "话题id不能为空");
        }
        return ResultVOUtil.success(manageService.deleteTopic(topicId));
    }

    /**
     * 管理员查看话题热度趋势接口
     * @param topicId
     * Integer类型话题id
     * @return ResultVO
     */
    @GetMapping("/topicTrend")
    @SystemControllerLog(description = "展示话题趋势")
    public ResultVO showTopicTrend(Integer topicId) {
        if(topicId == null) {
            throw new BizException("-1", "话题id不能为空");
        }
        return ResultVOUtil.success(manageService.showTopicTrend(topicId));
    }
}
