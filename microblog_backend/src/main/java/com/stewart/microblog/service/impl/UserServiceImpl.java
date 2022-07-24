package com.stewart.microblog.service.impl;

import com.stewart.microblog.dto.DetailedInfoDTO;
import com.stewart.microblog.dto.RegisterDTO;
import com.stewart.microblog.entity.*;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.repository.*;
import com.stewart.microblog.service.UserService;
import com.stewart.microblog.util.GetCurrentUserUtil;
import com.stewart.microblog.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户功能接口实现类
 * @author 王松涛
 */
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserRepository userRepository;
    @Resource
    private PictureRepository pictureRepository;
    @Resource
    private FollowRepository followRepository;
    @Resource
    private BlogRepository blogRepository;
    @Resource
    private BlogTopicRepository blogTopicRepository;
    @Resource
    private TopicRepository topicRepository;
    @Resource
    private CommentRepository commentRepository;
    @Resource
    private InformationRepository informationRepository;
    @Resource
    private LikeRepository likeRepository;

    @Override
    public StatusCode register(RegisterDTO registerDTO) {
        return null;
    }

    @Override
    public UserPageVO showPersonalPage(Integer personId) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        User user = userRepository.findByUsername(userName);
        Integer userId = user.getId();
        User person = userRepository.findUserByIdAndState(personId, "NORMAL");
        String personPicImg = pictureRepository.findPictureByIdAndDeleted(person.getPhotoId(), false).getUrl();
        Integer followingNum = followRepository.countByUserIdAndDeleted(personId, false);
        Integer followerNum = followRepository.countByFollowingIdAndDeleted(personId, false);
        List<String> scopeList = new ArrayList<>();
        scopeList.add("PUBLIC");
        if(followRepository.findFollowByUserIdAndFollowingIdAndDeleted(userId, personId, false) != null) {
            scopeList.add("FOLLOW");
            if(followRepository.findFollowByUserIdAndFollowingIdAndDeleted(personId, userId, false) != null) {
                scopeList.add("FRIEND");
            }
        }
        List<Integer> publisherIdList = new ArrayList<>();
        publisherIdList.add(personId);
        List<Blog> blogList = blogRepository.findAllByScopeInAndDeletedAndStateAndPublisherIdInOrderByTimeDesc(
                scopeList, false, "NORMAL", publisherIdList);
        List<BlogVO> blogVOList = new ArrayList<>();
        for(Blog blog: blogList) {
            Integer blogId = blog.getId();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            User publisher = userRepository.findUserByIdAndState(blog.getPublisherId(), "NORMAL");
            List<String> blogTopicList = new ArrayList<>();
            List<BlogTopic> blogTopics = blogTopicRepository.findBlogTopicsByBlogIdAndDeleted(blogId, false);
            for(BlogTopic blogTopic: blogTopics) {
                blogTopicList.add(topicRepository.findTopicByIdAndDeleted(blogTopic.getTopicId(), false).getName());
            }
            List<Comment> comments = commentRepository.findAllByBlogIdAndDeleted(blogId, false);
            List<String> commentList = new ArrayList<>();
            for(int j = 0; j < Math.min(comments.size(), 3); j++) {
                commentList.add(userRepository.findUserByIdAndState(comments.get(j).getSenderId(), "NORMAL").getNickname() + "： " + comments.get(j).getContent());
            }
            String picUrl = (blog.getPhotoId() == null ? "" : pictureRepository.findPictureByIdAndDeleted(blog.getPhotoId(), false).getUrl());
            BlogVO blogVO = new BlogVO(
                    dateFormat.format(blog.getTime()),
                    publisher.getNickname(),
                    pictureRepository.findPictureByIdAndDeleted(publisher.getPhotoId(), false).getUrl(),
                    blog.getContent(),
                    blogTopicList,
                    picUrl,
                    blog.getForwardNum(),
                    blog.getCollectNum(),
                    blog.getLikeNum(), comments.size(), commentList
            );
            blogVOList.add(blogVO);
        }
        return new UserPageVO(
                personPicImg, person.getNickname(), person.getUsername(), followingNum, followerNum, blogVOList
        );
    }

    @Override
    public UserDetailedInfoVO showDetailedInfo() {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userRepository.findByUsername(userName).getId();
        Information information = informationRepository.findInformationByUserId(userId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return new UserDetailedInfoVO(
                information.getRealName(),
                information.getGender(),
                dateFormat.format(information.getBirthday()),
                information.getCompany(),
                information.getJob(),
                information.getUniversity(),
                information.getTelNumber(),
                information.getHobby(),
                information.getProvince()
        );
    }

    @Override
    public StatusCode updateDetailedInfo(DetailedInfoDTO detailedInfoDTO) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userRepository.findByUsername(userName).getId();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday;
        try {
            birthday = dateFormat.parse(detailedInfoDTO.getBirthday());
        } catch (ParseException e) {
            return StatusCode.FAIL;
        }
        Information information = new Information(
                userId, detailedInfoDTO.getRealName(), detailedInfoDTO.getGender(),
                birthday, detailedInfoDTO.getCompany(),
                detailedInfoDTO.getJob(), detailedInfoDTO.getUniversity(),
                detailedInfoDTO.getTelNumber(), detailedInfoDTO.getHobby(),
                detailedInfoDTO.getProvince()
        );
        informationRepository.save(information);
        return StatusCode.SUCCESS;
    }

    @Override
    public UserStatVO showUserStatInfo() {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userRepository.findByUsername(userName).getId();
        List<Blog> blogList = blogRepository.findAllByPublisherIdAndDeleted(userId, false);
        Integer totalBlogNum = blogList.size();
        Integer totalHeat = 0;
        Integer totalCommentNum = 0;
        Integer totalLikeNum = 0;
        Integer totalRepostNum = 0;
        Integer totalCollectNum = 0;
        for(Blog blog: blogList) {
            totalHeat += blog.getHeat();
            Integer blogId = blog.getId();
            totalCollectNum += commentRepository.findAllByBlogIdAndDeleted(blogId, false).size();
            totalLikeNum += blog.getLikeNum();
            totalRepostNum += blog.getForwardNum();
            totalCollectNum += blog.getCollectNum();
        }
//        return new UserStatVO(
//            totalBlogNum, totalHeat, totalCommentNum, totalLikeNum, totalRepostNum, totalCollectNum,
//                blogRepository.findFirstByOrderByHeat()
//        );
        return null;
    }

    @Override
    public StatusCode follow(Integer personId) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userRepository.findByUsername(userName).getId();
        Follow follow = followRepository.findFollowByUserIdAndFollowingIdAndDeleted(userId, personId, true);
        if(follow != null) {
            follow.setDeleted(false);
            followRepository.save(follow);
        }
        else {
            followRepository.save(new Follow(null, userId, personId, false));
        }
        return StatusCode.SUCCESS;
    }

    @Override
    public StatusCode unfollow(Integer personId) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userRepository.findByUsername(userName).getId();
        Follow follow = followRepository.findFollowByUserIdAndFollowingIdAndDeleted(userId, personId, false);
        follow.setDeleted(true);
        followRepository.save(follow);
        return StatusCode.SUCCESS;
    }

    @Override
    public UserListVO showFollowingList() {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        User user = userRepository.findByUsername(userName);
        Integer userId = user.getId();
        List<Follow> followList = followRepository.findAllByUserIdAndDeleted(userId, false);
        List<UserListItemVO> userListItemVOList = new ArrayList<>();
        for(Follow follow: followList) {
            Integer personId = follow.getFollowingId();
            User person = userRepository.findUserByIdAndState(personId, "NORMAL");
            userListItemVOList.add(new UserListItemVO(
                    pictureRepository.findPictureByIdAndDeleted(person.getPhotoId(), false).getUrl(),
                    person.getNickname(), person.getUsername()
            ));
        }
        return new UserListVO(userListItemVOList);
    }

    @Override
    public UserListVO showFollowerList() {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        User user = userRepository.findByUsername(userName);
        Integer userId = user.getId();
        List<Follow> followList = followRepository.findAllByFollowingIdAndDeleted(userId, false);
        List<UserListItemVO> userListItemVOList = new ArrayList<>();
        for(Follow follow: followList) {
            Integer personId = follow.getUserId();
            User person = userRepository.findUserByIdAndState(personId, "NORMAL");
            userListItemVOList.add(new UserListItemVO(
                    pictureRepository.findPictureByIdAndDeleted(person.getPhotoId(), false).getUrl(),
                    person.getNickname(), person.getUsername()
            ));
        }
        return new UserListVO(userListItemVOList);
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
