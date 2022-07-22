package com.stewart.microblog.service;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.*;

/**
 * 管理员功能接口
 * @author 王松涛
 */
public interface ManagerService {
    ManagerTopicListVO showTopicList();
    StatusCode deleteTopic(Integer topicId);
    ManagerBlogListVO showBlogList();
    StatusCode deleteBlog(Integer blogId);
    ManagerUserListVO showUserList();
    StatusCode banUser(Integer userId);
    ManagerFbRepVO showFeedbackReportList();
    ManagerTotalStatVO showTotalStat();
    ManagerUserStatVO showUserStat();
    ManagerTopicStatVO showTopicStat();
    ManagerTopicTrendVO showTopicTrend(Integer topicId);
}
