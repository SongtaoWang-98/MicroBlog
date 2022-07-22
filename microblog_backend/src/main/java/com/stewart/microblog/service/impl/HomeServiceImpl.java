package com.stewart.microblog.service.impl;

import com.stewart.microblog.entity.*;
import com.stewart.microblog.repository.*;
import com.stewart.microblog.service.HomeService;
import com.stewart.microblog.util.GetCurrentUserUtil;
import com.stewart.microblog.vo.BlogVO;
import com.stewart.microblog.vo.HomeVO;
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

    @Override
    public HomeVO showHotBlogsHome() {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        User user = userRepository.findByUsername(userName);
        Integer userId = user.getId();
        List<String> groupNameList = new ArrayList<>();
        List<ConcernGroup> concernGroupList = concernGroupRepository.findByUserIdAndDeleted(userId, false);
        for(ConcernGroup concernGroup : concernGroupList) {
            groupNameList.add(concernGroup.getName());
        }
        List<BlogVO> blogVOList = new ArrayList<>();
        List<Blog> blogList = blogRepository.findAllByScopeAndDeletedAndStateOrderByHeatDesc("PUBLIC", false, "NORMAL");
        for(int i = 0; i < (Math.min(blogList.size(), 10)); i++) {
            Integer blogId = blogList.get(i).getId();
            Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
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
        List<String> topicNameList = new ArrayList<>();
        List<Topic> topicList = topicRepository.findAllByDeletedOrderByHeatDesc(false);
        for(int i = 0; i < Math.min(3, topicList.size()); i++) {
            topicNameList.add(topicList.get(i).getName());
        }
        return new HomeVO(
                pictureRepository.findPictureByIdAndDeleted(user.getPhotoId(), false).getUrl(),
                user.getNickname(),
                user.getUsername(),
                followRepository.countByUserIdAndDeleted(userId, false),
                followRepository.countByFollowingIdAndDeleted(userId, false),
                groupNameList,
                blogVOList,
                topicNameList
        );
    }

    @Override
    public HomeVO showNewBlogsHome() {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        User user = userRepository.findByUsername(userName);
        Integer userId = user.getId();
        List<String> groupNameList = new ArrayList<>();
        List<ConcernGroup> concernGroupList = concernGroupRepository.findByUserIdAndDeleted(userId, false);
        for(ConcernGroup concernGroup : concernGroupList) {
            groupNameList.add(concernGroup.getName());
        }
        List<BlogVO> blogVOList = new ArrayList<>();
        List<Blog> blogList = blogRepository.findAllByScopeAndDeletedAndStateOrderByTimeDesc("PUBLIC", false, "NORMAL");
        for(int i = 0; i < (Math.min(blogList.size(), 10)); i++) {
            Integer blogId = blogList.get(i).getId();
            Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
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
        List<String> topicNameList = new ArrayList<>();
        List<Topic> topicList = topicRepository.findAllByDeletedOrderByHeatDesc(false);
        for(int i = 0; i < Math.min(3, topicList.size()); i++) {
            topicNameList.add(topicList.get(i).getName());
        }
        return new HomeVO(
                pictureRepository.findPictureByIdAndDeleted(user.getPhotoId(), false).getUrl(),
                user.getNickname(),
                user.getUsername(),
                followRepository.countByUserIdAndDeleted(userId, false),
                followRepository.countByFollowingIdAndDeleted(userId, false),
                groupNameList,
                blogVOList,
                topicNameList
        );
    }

    @Override
    public HomeVO showFriendsBlogsHome() {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        User user = userRepository.findByUsername(userName);
        Integer userId = user.getId();
        List<String> groupNameList = new ArrayList<>();
        List<ConcernGroup> concernGroupList = concernGroupRepository.findByUserIdAndDeleted(userId, false);
        for(ConcernGroup concernGroup : concernGroupList) {
            groupNameList.add(concernGroup.getName());
        }
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
        scopes.add("PUBLIC");
        scopes.add("FOLLOW");
        scopes.add("FRIEND");
        List<BlogVO> blogVOList = new ArrayList<>();
        List<Blog> blogList = blogRepository.findAllByScopeInAndDeletedAndStateAndPublisherIdInOrderByTimeDesc(scopes,false, "NORMAL", friendIds);
        for(int i = 0; i < (Math.min(blogList.size(), 10)); i++) {
            Integer blogId = blogList.get(i).getId();
            Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
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
        List<String> topicNameList = new ArrayList<>();
        List<Topic> topicList = topicRepository.findAllByDeletedOrderByHeatDesc(false);
        for(int i = 0; i < Math.min(3, topicList.size()); i++) {
            topicNameList.add(topicList.get(i).getName());
        }
        return new HomeVO(
                pictureRepository.findPictureByIdAndDeleted(user.getPhotoId(), false).getUrl(),
                user.getNickname(),
                user.getUsername(),
                followRepository.countByUserIdAndDeleted(userId, false),
                followRepository.countByFollowingIdAndDeleted(userId, false),
                groupNameList,
                blogVOList,
                topicNameList
        );
    }

    @Override
    public HomeVO showGroupBlogsHome(Integer groupId) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        User user = userRepository.findByUsername(userName);
        Integer userId = user.getId();
        List<String> groupNameList = new ArrayList<>();
        List<ConcernGroup> concernGroupList = concernGroupRepository.findByUserIdAndDeleted(userId, false);
        for(ConcernGroup concernGroup : concernGroupList) {
            groupNameList.add(concernGroup.getName());
        }
        List<GroupFollow>groupFollowList = groupFollowRepository.findAllByGroupIdAndDeleted(groupId, false);
        List<Integer> groupMemberIds = new ArrayList<>();
        for(GroupFollow groupFollow: groupFollowList) {
            groupMemberIds.add(followRepository.findFollowByIdAndDeleted(groupFollow.getFollowId(), false).getFollowingId());
        }
        List<String> scopes = new ArrayList<>();
        scopes.add("PUBLIC");
        scopes.add("FOLLOW");
        List<BlogVO> blogVOList = new ArrayList<>();
        List<Blog> blogList = blogRepository.findAllByScopeInAndDeletedAndStateAndPublisherIdInOrderByTimeDesc(scopes, false, "NORMAL", groupMemberIds);
        for(int i = 0; i < (Math.min(blogList.size(), 10)); i++) {
            Integer blogId = blogList.get(i).getId();
            Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
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
        List<String> topicNameList = new ArrayList<>();
        List<Topic> topicList = topicRepository.findAllByDeletedOrderByHeatDesc(false);
        for(int i = 0; i < Math.min(3, topicList.size()); i++) {
            topicNameList.add(topicList.get(i).getName());
        }
        return new HomeVO(
                pictureRepository.findPictureByIdAndDeleted(user.getPhotoId(), false).getUrl(),
                user.getNickname(),
                user.getUsername(),
                followRepository.countByUserIdAndDeleted(userId, false),
                followRepository.countByFollowingIdAndDeleted(userId, false),
                groupNameList,
                blogVOList,
                topicNameList
        );
    }
}
