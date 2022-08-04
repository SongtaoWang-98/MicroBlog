package com.stewart.microblog.service.impl;

import com.stewart.microblog.dto.DetailedInfoDTO;
import com.stewart.microblog.entity.*;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.repository.*;
import com.stewart.microblog.service.UserService;
import com.stewart.microblog.util.GetCurrentUserUtil;
import com.stewart.microblog.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private UserInfoRepository userInfoRepository;
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
    private DetailedInfoRepository detailedInfoRepository;
    @Resource
    private CollectionRepository collectionRepository;
    @Resource
    private LikeSetRepository likeSetRepository;

    private static final String NORMAL = "NORMAL";
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserPageVO showPersonalPage(Integer personId) {
        logger.info("展示用户id为{}用户的个人主页", personId);
        String userName = GetCurrentUserUtil.getCurrentUserName();
        UserInfo userInfo = userInfoRepository.findByUsername(userName);
        logger.info("当前登录用户：{}", userName);
        Integer userId = userInfo.getId();
        UserInfo person = userInfoRepository.findUserByIdAndState(personId, NORMAL);
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
                scopeList, false, NORMAL, publisherIdList);
        List<BlogVO> blogVOList = new ArrayList<>();
        logger.info("加载博文列表");
        for(Blog blog: blogList) {
            Integer blogId = blog.getId();
            SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
            UserInfo publisher = userInfoRepository.findUserByIdAndState(blog.getPublisherId(), NORMAL);
            List<String> blogTopicList = new ArrayList<>();
            List<BlogTopic> blogTopics = blogTopicRepository.findBlogTopicsByBlogIdAndDeleted(blogId, false);
            for(BlogTopic blogTopic: blogTopics) {
                blogTopicList.add(topicRepository.findTopicByIdAndDeleted(blogTopic.getTopicId(), false).getName());
            }
            List<Comment> comments = commentRepository.findAllByBlogIdAndDeleted(blogId, false);
            List<String> commentList = new ArrayList<>();
            for(Comment comment: comments) {
                commentList.add(userInfoRepository.findUserByIdAndState(comment.getSenderId(), NORMAL).getNickname() + "： " + comment.getContent());
            }
            String picUrl = (blog.getPhotoId() == null ? "" : pictureRepository.findPictureByIdAndDeleted(blog.getPhotoId(), false).getUrl());
            BlogVO blogVO = new BlogVO(
                    blogId,
                    publisher.getId(),
                    dateFormat.format(blog.getTime()),
                    publisher.getNickname(),
                    pictureRepository.findPictureByIdAndDeleted(publisher.getPhotoId(), false).getUrl(),
                    blog.getContent(),
                    blogTopicList,
                    picUrl,
                    blog.getForwardNum(),
                    (collectionRepository.findByUserIdAndBlogIdAndDeleted(userId, blogId, false) != null),
                    blog.getCollectNum(),
                    (likeSetRepository.findByUserIdAndBlogIdAndDeleted(userId, blogId, false) != null),
                    blog.getLikeNum(), comments.size(), commentList
            );
            blogVOList.add(blogVO);
        }
        Follow follow = followRepository.findFollowByUserIdAndFollowingIdAndDeleted(userId, personId, false);
        return new UserPageVO(
                personPicImg, person.getNickname(), person.getUsername(), followingNum, followerNum, (follow != null), blogVOList
        );
    }

    @Override
    public CollectionPageVO showMyCollections() {
        logger.info("展示我的收藏页");
        String userName = GetCurrentUserUtil.getCurrentUserName();
        UserInfo userInfo = userInfoRepository.findByUsername(userName);
        Integer userId = userInfo.getId();
        List<BlogVO> blogVOList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
        List<Collection> collectionList = collectionRepository.findAllByUserIdAndDeleted(userId, false);
        logger.info("加载博文列表");
        for(Collection collection: collectionList) {
            Blog blog = blogRepository.findBlogByIdAndStateAndDeleted(collection.getBlogId(), NORMAL, false);
            Integer blogId = blog.getId();
            UserInfo publisher = userInfoRepository.findUserByIdAndState(blog.getPublisherId(), NORMAL);
            List<String> blogTopicList = new ArrayList<>();
            List<BlogTopic> blogTopics = blogTopicRepository.findBlogTopicsByBlogIdAndDeleted(blogId, false);
            for(BlogTopic blogTopic: blogTopics) {
                blogTopicList.add(topicRepository.findTopicByIdAndDeleted(blogTopic.getTopicId(), false).getName());
            }
            List<Comment> comments = commentRepository.findAllByBlogIdAndDeleted(blogId, false);
            List<String> commentList = new ArrayList<>();
            for(Comment comment: comments) {
                commentList.add(userInfoRepository.findUserByIdAndState(comment.getSenderId(), NORMAL).getNickname() + "： " + comment.getContent());
            }
            String picUrl = (blog.getPhotoId() == null ? "" : pictureRepository.findPictureByIdAndDeleted(blog.getPhotoId(), false).getUrl());
            blogVOList.add(new BlogVO(
                    blogId,
                    publisher.getId(),
                    dateFormat.format(blog.getTime()),
                    publisher.getNickname(),
                    pictureRepository.findPictureByIdAndDeleted(publisher.getPhotoId(), false).getUrl(),
                    blog.getContent(),
                    blogTopicList,
                    picUrl,
                    blog.getForwardNum(),
                    (collectionRepository.findByUserIdAndBlogIdAndDeleted(userId, blogId, false) != null),
                    blog.getCollectNum(),
                    (likeSetRepository.findByUserIdAndBlogIdAndDeleted(userId, blogId, false) != null),
                    blog.getLikeNum(), comments.size(), commentList
            ));
        }
        return new CollectionPageVO(blogVOList);
    }

    @Override
    public UserDetailedInfoVO showDetailedInfo() {
        logger.info("展示详细信息页");
        String userName = GetCurrentUserUtil.getCurrentUserName();
        UserInfo userInfo = userInfoRepository.findByUsername(userName);
        Integer userId = userInfo.getId();
        DetailedInfo detailedInfo = detailedInfoRepository.findInformationByUserId(userId);
        if(detailedInfo == null) {
           detailedInfoRepository.save(new DetailedInfo(
                userId, null, null, new Date(), null, null, null,
                   null, null, null
           ));
           detailedInfo = detailedInfoRepository.findInformationByUserId(userId);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
        List<Picture> pictureList = pictureRepository.findAllByUserIdAndDeleted(userId, false);
        List<PhotoVO> photoVOList = new ArrayList<>();
        for(Picture picture: pictureList) {
            photoVOList.add(new PhotoVO(picture.getId(), picture.getUrl()));
        }
        return new UserDetailedInfoVO(
                pictureRepository.findPictureByIdAndDeleted(userInfo.getPhotoId(), false).getUrl(),
                photoVOList,
                detailedInfo.getRealName(),
                detailedInfo.getGender(),
                dateFormat.format(detailedInfo.getBirthday()),
                detailedInfo.getCompany(),
                detailedInfo.getJob(),
                detailedInfo.getUniversity(),
                detailedInfo.getTelNumber(),
                detailedInfo.getHobby(),
                detailedInfo.getProvince()
        );
    }

    @Override
    public StatusCode updateDetailedInfo(DetailedInfoDTO detailedInfoDTO) throws ParseException {
        logger.info("更新详细信息页");
        String userName = GetCurrentUserUtil.getCurrentUserName();
        UserInfo userInfo = userInfoRepository.findByUsername(userName);
        Integer userId = userInfo.getId();

        userInfo.setPhotoId(detailedInfoDTO.getPicId());
        userInfoRepository.save(userInfo);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday;
        birthday = dateFormat.parse(detailedInfoDTO.getBirthday());
        DetailedInfo detailedInfo = new DetailedInfo(
                userId, detailedInfoDTO.getRealName(), detailedInfoDTO.getGender(),
                birthday, detailedInfoDTO.getCompany(),
                detailedInfoDTO.getJob(), detailedInfoDTO.getUniversity(),
                detailedInfoDTO.getTelNumber(), detailedInfoDTO.getHobby(),
                detailedInfoDTO.getProvince()
        );
        detailedInfoRepository.save(detailedInfo);
        logger.info("更新详细信息操作成功");
        return StatusCode.SUCCESS;
    }

    @Override
    public UserStatVO showUserStatInfo() {
        logger.info("显示用户统计信息");
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        List<Blog> blogList = blogRepository.findAllByPublisherIdAndDeleted(userId, false);
        Integer totalBlogNum = blogList.size();
        Integer totalHeat = 0;
        int totalCommentNum = 0;
        Integer totalLikeNum = 0;
        Integer totalRepostNum = 0;
        Integer totalCollectNum = 0;
        for(Blog blog: blogList) {
            totalHeat += blog.getHeat();
            Integer blogId = blog.getId();
            totalCommentNum += commentRepository.findAllByBlogIdAndDeleted(blogId, false).size();
            totalLikeNum += blog.getLikeNum();
            totalRepostNum += blog.getForwardNum();
            totalCollectNum += blog.getCollectNum();
        }
        Blog hotBlog =  blogRepository.findFirstByPublisherIdAndDeletedOrderByHeat(userId, false);
        UserInfo publisher = userInfoRepository.findUserByIdAndState(userId, NORMAL);
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
        Integer blogId = hotBlog.getId();
        List<String> blogTopicList = new ArrayList<>();
        List<BlogTopic> blogTopics = blogTopicRepository.findBlogTopicsByBlogIdAndDeleted(blogId, false);
        for(BlogTopic blogTopic: blogTopics) {
            blogTopicList.add(topicRepository.findTopicByIdAndDeleted(blogTopic.getTopicId(), false).getName());
        }
        List<Comment> comments = commentRepository.findAllByBlogIdAndDeleted(blogId, false);
        List<String> commentList = new ArrayList<>();
        for(Comment comment: comments) {
            commentList.add(userInfoRepository.findUserByIdAndState(comment.getSenderId(), NORMAL).getNickname() + "： " + comment.getContent());
        }
        String picUrl = (hotBlog.getPhotoId() == null ? "" : pictureRepository.findPictureByIdAndDeleted(hotBlog.getPhotoId(), false).getUrl());
        BlogVO blogVO = new BlogVO(
                blogId,
                publisher.getId(),
                dateFormat.format(hotBlog.getTime()),
                publisher.getNickname(),
                pictureRepository.findPictureByIdAndDeleted(publisher.getPhotoId(), false).getUrl(),
                hotBlog.getContent(),
                blogTopicList,
                picUrl,
                hotBlog.getForwardNum(),
                (collectionRepository.findByUserIdAndBlogIdAndDeleted(userId, blogId, false) != null),
                hotBlog.getCollectNum(),
                (likeSetRepository.findByUserIdAndBlogIdAndDeleted(userId, blogId, false) != null),
                hotBlog.getLikeNum(), comments.size(), commentList
        );
        return new UserStatVO(
            totalBlogNum, totalHeat, totalCommentNum, totalLikeNum, totalRepostNum, totalCollectNum, blogVO
        );
    }

    @Override
    public StatusCode follow(Integer personId) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        logger.info("添加用户id为{}的用户至id为{}的用户的关注关系", userId, personId);
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
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        logger.info("删除用户id为{}的用户至id为{}的用户的关注关系", userId, personId);
        Follow follow = followRepository.findFollowByUserIdAndFollowingIdAndDeleted(userId, personId, false);
        follow.setDeleted(true);
        followRepository.save(follow);
        return StatusCode.SUCCESS;
    }

    @Override
    public UserListVO showFollowingList() {
        logger.info("展示用户关注列表");
        String userName = GetCurrentUserUtil.getCurrentUserName();
        UserInfo userInfo = userInfoRepository.findByUsername(userName);
        Integer userId = userInfo.getId();
        List<Follow> followList = followRepository.findAllByUserIdAndDeleted(userId, false);
        List<UserListItemVO> userListItemVOList = new ArrayList<>();
        for(Follow follow: followList) {
            Integer personId = follow.getFollowingId();
            UserInfo person = userInfoRepository.findUserByIdAndState(personId, NORMAL);
            userListItemVOList.add(new UserListItemVO(
                    personId,
                    pictureRepository.findPictureByIdAndDeleted(person.getPhotoId(), false).getUrl(),
                    person.getNickname(), person.getUsername()
            ));
        }
        return new UserListVO(userListItemVOList);
    }

    @Override
    public UserListVO showFollowerList() {
        logger.info("展示用户粉丝列表");
        String userName = GetCurrentUserUtil.getCurrentUserName();
        UserInfo userInfo = userInfoRepository.findByUsername(userName);
        Integer userId = userInfo.getId();
        List<Follow> followList = followRepository.findAllByFollowingIdAndDeleted(userId, false);
        List<UserListItemVO> userListItemVOList = new ArrayList<>();
        for(Follow follow: followList) {
            Integer personId = follow.getUserId();
            UserInfo person = userInfoRepository.findUserByIdAndState(personId, NORMAL);
            userListItemVOList.add(new UserListItemVO(
                    personId,
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
