package com.stewart.microblog.service.impl;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.service.ManagerService;
import com.stewart.microblog.vo.*;

/**
 * 管理员功能接口实现类
 * @author 王松涛
 */
public class ManagerServiceImpl implements ManagerService {
    @Override
    public ManagerTopicListVO showTopicList() {
        return null;
    }

    @Override
    public StatusCode deleteTopic(Integer topicId) {
        return null;
    }

    @Override
    public ManagerBlogListVO showBlogList() {
        return null;
    }

    @Override
    public StatusCode deleteBlog(Integer blogId) {
        return null;
    }

    @Override
    public ManagerUserListVO showUserList() {
        return null;
    }

    @Override
    public StatusCode banUser(Integer userId) {
        return null;
    }

    @Override
    public ManagerFbRepVO showFeedbackReportList() {
        return null;
    }

    @Override
    public ManagerTotalStatVO showTotalStat() {
        return null;
    }

    @Override
    public ManagerUserStatVO showUserStat() {
        return null;
    }

    @Override
    public ManagerTopicStatVO showTopicStat() {
        return null;
    }

    @Override
    public ManagerTopicTrendVO showTopicTrend(Integer topicId) {
        return null;
    }
}
