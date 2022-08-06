package com.stewart.microblog.service;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.*;

import java.text.ParseException;

/**
 * 管理员功能接口
 * @author 王松涛
 */
public interface ManageService {
    ManagerUserListVO showUserList();
    StatusCode banUser(Integer userId);
    StatusCode unbanUser(Integer userId);

    ManagerTotalStatVO showTotalStat() throws ParseException;

    ManagerBlogListVO showBlogList();
    StatusCode deleteBlog(Integer blogId);

    ManagerTopicListVO showTopicList();
    StatusCode deleteTopic(Integer topicId);
    ManagerTopicTrendVO showTopicTrend(Integer topicId);
}
