package com.stewart.microblog.service;

import com.stewart.microblog.dto.DetailedUserInfo;
import com.stewart.microblog.dto.RegisterDTO;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.*;

/**
 * 用户功能接口
 * @author 王松涛
 */
public interface UserService {
    StatusCode register(RegisterDTO registerDTO);
    UserPageVO showPersonalPage(Integer userId);
    UserDetailedInfoVO showDetailedInfo();
    StatusCode updateDetailedInfo(DetailedUserInfo detailedUserInfo);
    UserStatVO showUserStatInfo();
    StatusCode follow(Integer userId);
    StatusCode unFollow(Integer userId);
    UserListVO showFollowList();
    UserListVO showFollowerList();
    GroupListVO showGroupList();
    StatusCode createGroup(String groupName);
    StatusCode deleteGroup(Integer groupId);
    GroupUserListVO showGroupUserList(Integer groupId);
    StatusCode addUserToGroup(Integer groupId, Integer userId);
    StatusCode removeUserFromGroup(Integer groupId, Integer userId);
}
