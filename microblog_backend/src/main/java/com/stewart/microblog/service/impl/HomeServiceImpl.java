package com.stewart.microblog.service.impl;

import com.stewart.microblog.entity.*;
import com.stewart.microblog.repository.*;
import com.stewart.microblog.service.HomeService;
import com.stewart.microblog.util.GetCurrentUserUtil;
import com.stewart.microblog.vo.BlogVO;
import com.stewart.microblog.vo.HomeGroupVO;
import com.stewart.microblog.vo.HomeVO;
import org.jetbrains.annotations.NotNull;
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
    private UserRepository userRepository;
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
    private static final Integer TOPIC_NUM = 3;

    @Override
    public HomeVO showHotBlogsHome() {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        User user = userRepository.findByUsername(userName);
        List<Blog> blogList = blogRepository.findAllByScopeAndDeletedAndStateOrderByHeatDesc(PUBLIC, false, NORMAL);
        return createHomeVO(user, blogList);
    }

    @Override
    public HomeVO showNewBlogsHome() {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        User user = userRepository.findByUsername(userName);
        List<Blog> blogList = blogRepository.findAllByScopeAndDeletedAndStateOrderByTimeDesc(PUBLIC, false, NORMAL);
        return createHomeVO(user, blogList);
    }

    @Override
    public HomeVO showFriendsBlogsHome() {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        User user = userRepository.findByUsername(userName);
        Integer userId = user.getId();
        List<Integer> friendIds = new ArrayList<>();
        friendIds.add(userId);
        List<Follow> followList = followRepository.findAllByUserIdAndDeleted(userId, false);
        for(Follow follow: followList) {
            List<Follow> followerList = followRepository.findAllByUserIdAndDeleted(follow.getFollowingId(), false);
            for(Follow follower: followerList) {
                if(follower.getFollowingId().equals(userId)) {
                    friendIds.add(follow.getFollowingId());
                }
            }
        }
        List<String> scopes = new ArrayList<>();
        scopes.add(PUBLIC);
        scopes.add("FOLLOW");
        scopes.add("FRIEND");
        List<Blog> blogList = blogRepository.findAllByScopeInAndDeletedAndStateAndPublisherIdInOrderByTimeDesc(scopes,false, NORMAL, friendIds);
        return createHomeVO(user, blogList);
    }

    @Override
    public HomeVO showGroupBlogsHome(Integer groupId) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        User user = userRepository.findByUsername(userName);
        Integer userId = user.getId();
        List<GroupFollow>groupFollowList = groupFollowRepository.findAllByGroupIdAndDeleted(groupId, false);
        List<Integer> groupMemberIds = new ArrayList<>();
        for(GroupFollow groupFollow: groupFollowList) {
            groupMemberIds.add(followRepository.findFollowByIdAndDeleted(groupFollow.getFollowId(), false).getFollowingId());
        }
        List<String> scopes = new ArrayList<>();
        scopes.add(PUBLIC);
        scopes.add("FOLLOW");
        List<Blog> blogList = blogRepository.findAllByScopeInAndDeletedAndStateAndPublisherIdInOrderByTimeDesc(scopes, false, NORMAL, groupMemberIds);
        List<BlogVO> blogVOList = convertBlogsToVO(blogList);
        return new HomeVO(
                pictureRepository.findPictureByIdAndDeleted(user.getPhotoId(), false).getUrl(),
                user.getNickname(),
                user.getUsername(),
                followRepository.countByUserIdAndDeleted(userId, false),
                followRepository.countByFollowingIdAndDeleted(userId, false),
                getGroupNameList(userId),
                blogVOList,
                getHotTopicList()
        );
    }

    private List<BlogVO> convertBlogsToVO(List<Blog> blogList) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        User user = userRepository.findByUsername(userName);
        Integer userId = user.getId();
        List<BlogVO> blogVOList = new ArrayList<>();
        for(Blog blog: blogList) {
            Integer blogId = blog.getId();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            User publisher = userRepository.findUserByIdAndState(blog.getPublisherId(), NORMAL);
            List<String> blogTopicList = new ArrayList<>();
            List<BlogTopic> blogTopics = blogTopicRepository.findBlogTopicsByBlogIdAndDeleted(blogId, false);
            for(BlogTopic blogTopic: blogTopics) {
                blogTopicList.add(topicRepository.findTopicByIdAndDeleted(blogTopic.getTopicId(), false).getName());
            }
            List<Comment> comments = commentRepository.findAllByBlogIdAndDeleted(blogId, false);
            List<String> commentList = new ArrayList<>();
            for(Comment comment: comments) {
                commentList.add(userRepository.findUserByIdAndState(comment.getSenderId(), NORMAL).getNickname() + "： " + comment.getContent());
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
        return blogVOList;
    }

    List<HomeGroupVO> getGroupNameList(Integer userId) {
        List<HomeGroupVO> groupNameList = new ArrayList<>();
        List<ConcernGroup> concernGroupList = concernGroupRepository.findByUserIdAndDeleted(userId, false);
        for(ConcernGroup concernGroup : concernGroupList) {
            groupNameList.add(new HomeGroupVO(concernGroup.getId(), concernGroup.getName()));
        }
        return groupNameList;
    }

    List<String> getHotTopicList(){
        List<String> topicNameList = new ArrayList<>();
        List<Topic> topicList = topicRepository.findAllByDeletedOrderByHeatDesc(false);
        for(int i = 0; i < Math.min(TOPIC_NUM, topicList.size()); i++) {
            topicNameList.add(topicList.get(i).getName());
        }
        return topicNameList;
    }

    HomeVO createHomeVO(@NotNull User user, List<Blog> blogList) {
        return new HomeVO(
                pictureRepository.findPictureByIdAndDeleted(user.getPhotoId(), false).getUrl(),
                user.getNickname(),
                user.getUsername(),
                followRepository.countByUserIdAndDeleted(user.getId(), false),
                followRepository.countByFollowingIdAndDeleted(user.getId(), false),
                getGroupNameList(user.getId()),
                convertBlogsToVO(blogList),
                getHotTopicList()
        );
    }
}
