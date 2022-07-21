package com.stewart.microblog.service;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.*;

public interface ManagerService {
    ManagerTpoicListVO showTopicList();
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
