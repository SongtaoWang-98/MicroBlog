package com.stewart.microblog.service;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.*;

import java.text.ParseException;

/**
 * 管理员功能接口
 * @author 王松涛
 */
public interface ManageService {

    /**
     * 展示管理端用户列表
     * @return 管理端用户列表视图对象
     */
    ManagerUserListVO showUserList();

    /**
     * 管理端封禁用户
     * @param userId
     * 封禁用户id
     * @return StatusCode
     */
    StatusCode banUser(Integer userId);

    /**
     * 管理端解封用户
     * @param userId
     * 解封用户id
     * @return StatusCode
     */
    StatusCode unbanUser(Integer userId);

    /**
     * 展示统计页面
     * @return 管理端统计页面视图对象
     */
    ManagerTotalStatVO showTotalStat() throws ParseException;

    /**
     * 展示管理端博文列表
     * @return 管理端博文列表视图对象
     */
    ManagerBlogListVO showBlogList();

    /**
     * 管理端删除博文
     * @param blogId
     * 删除博文id
     * @return StatusCode
     */
    StatusCode deleteBlog(Integer blogId);

    /**
     * 展示管理端话题列表
     * @return 管理端话题列表视图对象
     */
    ManagerTopicListVO showTopicList();

    /**
     * 管理端删除话题
     * @param topicId
     * 删除话题id
     * @return StatusCode
     */
    StatusCode deleteTopic(Integer topicId);

    /**
     * 展示管理端话题趋势
     * @param topicId
     * 话题id
     * @return 管理端话题趋势视图对象
     */
    ManagerTopicTrendVO showTopicTrend(Integer topicId);
}
