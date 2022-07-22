package com.stewart.microblog.service.impl;

import com.stewart.microblog.dto.DetailedUserInfo;
import com.stewart.microblog.dto.RegisterDTO;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.service.UserService;
import com.stewart.microblog.vo.*;
import org.springframework.stereotype.Service;

/**
 * 用户功能接口实现类
 * @author 王松涛
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public StatusCode register(RegisterDTO registerDTO) {
        return null;
    }

    @Override
    public UserPageVO showPersonalPage(Integer userId) {
        return null;
    }

    @Override
    public UserDetailedInfoVO showDetailedInfo() {
        return null;
    }

    @Override
    public StatusCode updateDetailedInfo(DetailedUserInfo detailedUserInfo) {
        return null;
    }

    @Override
    public UserStatVO showUserStatInfo() {
        return null;
    }

    @Override
    public StatusCode follow(Integer userId) {
        return null;
    }

    @Override
    public StatusCode unFollow(Integer userId) {
        return null;
    }

    @Override
    public UserListVO showFollowList() {
        return null;
    }

    @Override
    public UserListVO showFollowerList() {
        return null;
    }

    @Override
    public GroupListVO showGroupList() {
        return null;
    }

    @Override
    public StatusCode createGroup(String groupName) {
        return null;
    }

    @Override
    public StatusCode deleteGroup(Integer groupId) {
        return null;
    }

    @Override
    public GroupUserListVO showGroupUserList(Integer groupId) {
        return null;
    }

    @Override
    public StatusCode addUserToGroup(Integer groupId, Integer userId) {
        return null;
    }

    @Override
    public StatusCode removeUserFromGroup(Integer groupId, Integer userId) {
        return null;
    }
}
