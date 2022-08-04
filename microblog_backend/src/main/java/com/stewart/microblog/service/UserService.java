package com.stewart.microblog.service;

import com.stewart.microblog.dto.DetailedInfoDTO;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.*;

import java.text.ParseException;

/**
 * 用户功能接口
 * @author 王松涛
 */
public interface UserService {

    /**
     * 根据用户id展示个人主页
     * @param userId
     * Integer类型用户id
     * @return 用户主页视图对象
     */
    UserPageVO showPersonalPage(Integer userId);

    /**
     * 展示用户收藏页面
     * @return 用户收藏页视图对象
     */
    CollectionPageVO showMyCollections();

    /**
     * 展示用户详细信息页面
     * @return 用户详细信息视图对象
     */
    UserDetailedInfoVO showDetailedInfo();

    /**
     * 更新用户详细信息
     * @param detailedInfoDTO
     * 用户详细信息表单
     * @return 修改状态码
     * @throws ParseException
     * 抛出异常
     */
    StatusCode updateDetailedInfo(DetailedInfoDTO detailedInfoDTO) throws ParseException;

    /**
     * 展示用户统计信息
     * @return 用户统计信息视图对象
     */
    UserStatVO showUserStatInfo();

    /**
     * 关注接口
     * @param userId
     * Integer类型目标用户id
     * @return 状态码
     */
    StatusCode follow(Integer userId);

    /**
     * 取消关注接口
     * @param userId
     * Integer类型目标用户id
     * @return 状态码
     */
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
