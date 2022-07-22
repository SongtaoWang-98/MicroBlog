package com.stewart.microblog.service;

import com.stewart.microblog.dto.DetailedInfoDTO;
import com.stewart.microblog.dto.RegisterDTO;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.*;

import java.text.ParseException;

/**
 * 用户功能接口
 * @author 王松涛
 */
public interface UserService {
    StatusCode register(RegisterDTO registerDTO);

    /**
     * 根据用户id展示个人主页
     * @param userId
     * Integer类型用户id
     * @return 用户主页视图对象
     */
    UserPageVO showPersonalPage(Integer userId);
    UserDetailedInfoVO showDetailedInfo();
    StatusCode updateDetailedInfo(DetailedInfoDTO detailedInfoDTO) throws ParseException;
    UserStatVO showUserStatInfo();
    StatusCode follow(Integer userId);
    StatusCode unfollow(Integer userId);

    /**
     * 展示用户关注列表
     * @return 用户列表视图对象
     */
    UserListVO showFollowingList();

    /**
     * 展示用户粉丝列表
     * @return 用户列表视图对象
     */
    UserListVO showFollowerList();
    GroupListVO showGroupList();
    StatusCode createGroup(String groupName);
    StatusCode deleteGroup(Integer groupId);
    GroupUserListVO showGroupUserList(Integer groupId);
    StatusCode addUserToGroup(Integer groupId, Integer userId);
    StatusCode removeUserFromGroup(Integer groupId, Integer userId);
}
