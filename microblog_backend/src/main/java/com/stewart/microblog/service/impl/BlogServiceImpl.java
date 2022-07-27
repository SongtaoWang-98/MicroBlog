package com.stewart.microblog.service.impl;

import com.stewart.microblog.dto.NewBlogDTO;
import com.stewart.microblog.entity.*;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.repository.*;
import com.stewart.microblog.service.BlogService;
import com.stewart.microblog.util.GetCurrentUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 博客功能接口实现类
 * @author 王松涛
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private LikeSetRepository likeSetRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private CollectionRepository collectionRepository;
    @Resource
    private BlogRepository blogRepository;
    @Resource
    private CommentRepository commentRepository;
    @Resource
    private TopicRepository topicRepository;
    @Resource
    private BlogTopicRepository blogTopicRepository;

    @Override
    public StatusCode likeBlog(Integer blogId) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userRepository.findByUsername(userName).getId();
        LikeSet likeSet = likeSetRepository.findByUserIdAndBlogId(userId, blogId);
        if(likeSet != null) {
            likeSet.setDeleted(false);
            likeSetRepository.save(likeSet);
        }
        else {
            likeSetRepository.save(new LikeSet(null, userId, blogId, false));
        }
        Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
        blog.setLikeNum(blog.getLikeNum() + 1);
        blogRepository.save(blog);
        return StatusCode.SUCCESS;
    }

    @Override
    public StatusCode dislikeBlog(Integer blogId) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userRepository.findByUsername(userName).getId();
        LikeSet likeSet = likeSetRepository.findByUserIdAndBlogIdAndDeleted(userId, blogId, false);
        likeSet.setDeleted(true);
        likeSetRepository.save(likeSet);
        Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
        blog.setLikeNum(blog.getLikeNum() - 1);
        blogRepository.save(blog);
        return StatusCode.SUCCESS;
    }

    @Override
    public StatusCode collectBlog(Integer blogId) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userRepository.findByUsername(userName).getId();
        Collection collection = collectionRepository.findByUserIdAndBlogId(userId, blogId);
        if(collection != null) {
            collection.setDeleted(false);
            collectionRepository.save(collection);
        }
        else {
            collectionRepository.save(new Collection(null, userId, blogId, false));
        }
        Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
        blog.setCollectNum(blog.getCollectNum() + 1);
        blogRepository.save(blog);
        return StatusCode.SUCCESS;
    }

    @Override
    public StatusCode disCollectBlog(Integer blogId) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userRepository.findByUsername(userName).getId();
        Collection collection = collectionRepository.findByUserIdAndBlogIdAndDeleted(userId, blogId, false);
        collection.setDeleted(true);
        collectionRepository.save(collection);
        Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
        blog.setCollectNum(blog.getCollectNum() - 1);
        blogRepository.save(blog);
        return StatusCode.SUCCESS;
    }

    @Override
    public StatusCode commentBlog(Integer blogId,  Boolean isReply, Integer replyCommentId, String content) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userRepository.findByUsername(userName).getId();
        Comment comment = new Comment(null, blogId, userId, content, new Date(), isReply, replyCommentId, false);
        commentRepository.save(comment);
        return StatusCode.SUCCESS;
    }

    @Override
    public StatusCode publishBlog(NewBlogDTO newBlogDTO) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userRepository.findByUsername(userName).getId();
        String content = newBlogDTO.getContent();
        String regex = "#.*\n";
        String str = content.replaceAll(regex, "");
        Blog blog = new Blog(
                null, userId, new Date(), newBlogDTO.getScope(), str,
                newBlogDTO.getPicId(), 0, 0, 0, "NORMAL",
                "ORIGINAL", null, 0, false
        );
        blogRepository.save(blog);
        Blog thisBlog = blogRepository.findFirstByOrderById();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        List<String> topicList = new ArrayList<>();
        while(matcher.find()) {
            topicList.add(content.substring(matcher.start() + 1, matcher.end() - 1));
        }
        for(String s: topicList) {
            Topic topic = topicRepository.findTopicByName(s);
            if(topic != null) {
                if(Boolean.TRUE.equals(topic.getDeleted())) {
                    topic.setDeleted(false);
                    topicRepository.save(topic);
                }
                blogTopicRepository.save(new BlogTopic(null, thisBlog.getId(), topic.getId(), false));
            }
            else {
                topicRepository.save(new Topic(null, s, 0, false));
                blogTopicRepository.save(new BlogTopic(null, thisBlog.getId(),
                        topicRepository.findFirstByOrderById().getId(), false));
            }
        }
        return StatusCode.SUCCESS;
    }

    @Override
    public StatusCode repostBlog(Integer blogId) {
        return null;
    }

    @Override
    public StatusCode reportBlog(Integer blogId, String content, String email) {
        return null;
    }
}
