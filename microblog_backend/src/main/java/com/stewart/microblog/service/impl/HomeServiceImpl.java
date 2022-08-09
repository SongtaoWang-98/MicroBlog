package com.stewart.microblog.service.impl;

import com.stewart.microblog.entity.*;
import com.stewart.microblog.repository.*;
import com.stewart.microblog.service.HomeService;
import com.stewart.microblog.util.GetCurrentUserUtil;
import com.stewart.microblog.vo.BlogVO;
import com.stewart.microblog.vo.HomeGroupVO;
import com.stewart.microblog.vo.HomeVO;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 主页功能接口实现类
 * @author 王松涛
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private UserInfoRepository userInfoRepository;
    @Resource
    private FollowRepository followRepository;
    @Resource
    private ConcernGroupRepository concernGroupRepository;
    @Resource
    private TopicRepository topicRepository;
    @Resource
    private BlogRepository blogRepository;
    @Resource
    private BlogTopicRepository blogTopicRepository;
    @Resource
    private PictureRepository pictureRepository;
    @Resource
    private CommentRepository commentRepository;
    @Resource
    private GroupFollowRepository groupFollowRepository;
    @Resource
    private LikeSetRepository likeSetRepository;
    @Resource
    private CollectionRepository collectionRepository;

    private static final String PUBLIC = "PUBLIC";
    private static final String NORMAL = "NORMAL";
    /**
     * 默认展示话题数目
     */
    private static final Integer TOPIC_NUM = 3;
    /**
     * 日志logger对象
     */
    private static final Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Override
    public HomeVO showHotBlogsHome() {
        //获取当前登录用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        //获取热门博文
        logger.info("展示用户{}的微博客热门推荐页", userName);
        UserInfo userInfo = userInfoRepository.findByUsername(userName);
        List<Blog> blogList = blogRepository.findAllByScopeAndDeletedAndStateOrderByHeatDesc(PUBLIC, false, NORMAL);
        if(userInfo == null) {
            return createAnonymousHomeVO(blogList);
        }
        return createHomeVO(userInfo, blogList);
    }

    @Override
    public HomeVO showNewBlogsHome() {
        //获取当前登录用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        //获取最新博文
        logger.info("展示用户{}的微博客最新推荐页", userName);
        UserInfo userInfo = userInfoRepository.findByUsername(userName);
        List<Blog> blogList = blogRepository.findAllByScopeAndDeletedAndStateOrderByTimeDesc(PUBLIC, false, NORMAL);
        return createHomeVO(userInfo, blogList);
    }

    @Override
    public HomeVO showFriendsBlogsHome() {
        //获取当前登录用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        logger.info("展示用户{}的微博客好友圈推荐页", userName);
        UserInfo userInfo = userInfoRepository.findByUsername(userName);
        Integer userId = userInfo.getId();
        List<Integer> friendIds = new ArrayList<>();
        friendIds.add(userId);
        List<Follow> followList = followRepository.findAllByUserIdAndDeleted(userId, false);
        //两层循环获取互相关注好友
        for(Follow follow: followList) {
            List<Follow> followerList = followRepository.findAllByUserIdAndDeleted(follow.getFollowingId(), false);
            for(Follow follower: followerList) {
                if(follower.getFollowingId().equals(userId)) {
                    friendIds.add(follow.getFollowingId());
                }
            }
        }
        //设置好友圈可见范围
        List<String> scopes = new ArrayList<>();
        scopes.add(PUBLIC);
        scopes.add("FOLLOW");
        scopes.add("FRIEND");
        List<Blog> blogList = blogRepository.findAllByScopeInAndDeletedAndStateAndPublisherIdInOrderByTimeDesc(scopes,false, NORMAL, friendIds);
        return createHomeVO(userInfo, blogList);
    }

    @Override
    public HomeVO showGroupBlogsHome(Integer groupId) {
        //获取当前登录用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        logger.info("展示用户{}的微博客分组推荐页，分组id：{}", userName, groupId);
        UserInfo userInfo = userInfoRepository.findByUsername(userName);
        Integer userId = userInfo.getId();
        List<GroupFollow>groupFollowList = groupFollowRepository.findAllByGroupIdAndDeleted(groupId, false);
        List<Integer> groupMemberIds = new ArrayList<>();
        for(GroupFollow groupFollow: groupFollowList) {
            groupMemberIds.add(followRepository.findFollowByIdAndDeleted(groupFollow.getFollowId(), false).getFollowingId());
        }
        //设置可见权限
        List<String> scopes = new ArrayList<>();
        scopes.add(PUBLIC);
        scopes.add("FOLLOW");
        List<Blog> blogList = blogRepository.findAllByScopeInAndDeletedAndStateAndPublisherIdInOrderByTimeDesc(scopes, false, NORMAL, groupMemberIds);
        List<BlogVO> blogVOList = convertBlogsToVO(blogList);
        return new HomeVO(
                pictureRepository.findPictureByIdAndDeleted(userInfo.getPhotoId(), false).getUrl(),
                userInfo.getNickname(),
                userInfo.getUsername(),
                followRepository.countByUserIdAndDeleted(userId, false),
                followRepository.countByFollowingIdAndDeleted(userId, false),
                getGroupNameList(userId),
                blogVOList,
                getHotTopicList()
        );
    }

    public List<BlogVO> convertBlogsToVO(List<Blog> blogList) {
        logger.info("加载博文列表");
        String userName = GetCurrentUserUtil.getCurrentUserName();
        UserInfo userInfo = userInfoRepository.findByUsername(userName);
        int userId = (userInfo == null ? 0 : userInfo.getId());
        List<BlogVO> blogVOList = new ArrayList<>();
        //遍历博文对象列表，将其封装成视图对象列表
        for(Blog blog: blogList) {
            Integer blogId = blog.getId();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
            String picUrl = (blog.getPhotoId() == 0 ? "" : pictureRepository.findPictureByIdAndDeleted(blog.getPhotoId(), false).getUrl());
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
                    userId != 0 && (collectionRepository.findByUserIdAndBlogIdAndDeleted(userId, blogId, false) != null),
                    blog.getCollectNum(),
                    userId != 0 && (likeSetRepository.findByUserIdAndBlogIdAndDeleted(userId, blogId, false) != null),
                    blog.getLikeNum(), comments.size(), commentList
            );
            blogVOList.add(blogVO);
        }
        return blogVOList;
    }

    public List<HomeGroupVO> getGroupNameList(Integer userId) {
        logger.info("加载用户分组列表");
        List<HomeGroupVO> groupNameList = new ArrayList<>();
        List<ConcernGroup> concernGroupList = concernGroupRepository.findByUserIdAndDeleted(userId, false);
        for(ConcernGroup concernGroup : concernGroupList) {
            groupNameList.add(new HomeGroupVO(concernGroup.getId(), concernGroup.getName()));
        }
        return groupNameList;
    }

    public List<String> getHotTopicList(){
        logger.info("加载热门话题列表");
        List<String> topicNameList = new ArrayList<>();
        List<Topic> topicList = topicRepository.findAllByDeletedOrderByHeatDesc(false);
        for(int i = 0; i < Math.min(TOPIC_NUM, topicList.size()); i++) {
            topicNameList.add(topicList.get(i).getName());
        }
        return topicNameList;
    }

    public HomeVO createHomeVO(UserInfo userInfo, List<Blog> blogList) {
        //根据博文列表创建用户登录的首页视图对象
        logger.info("生成首页视图对象");
        return new HomeVO(
                pictureRepository.findPictureByIdAndDeleted(userInfo.getPhotoId(), false).getUrl(),
                userInfo.getNickname(),
                userInfo.getUsername(),
                followRepository.countByUserIdAndDeleted(userInfo.getId(), false),
                followRepository.countByFollowingIdAndDeleted(userInfo.getId(), false),
                getGroupNameList(userInfo.getId()),
                convertBlogsToVO(blogList),
                getHotTopicList()
        );
    }

    public HomeVO createAnonymousHomeVO(List<Blog> blogList) {
        //根据博文列表创建用户未登录的首页视图对象
        logger.info("生成首页视图对象");
        return new HomeVO(
                pictureRepository.findPictureByIdAndDeleted(1, false).getUrl(),
                "未登录",
                "",
                0,
                0,
                new ArrayList<>(),
                convertBlogsToVO(blogList),
                getHotTopicList()
        );
    }
}
