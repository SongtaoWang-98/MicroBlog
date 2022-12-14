package com.stewart.microblog.service.impl;

import com.stewart.microblog.dto.NewBlogDTO;
import com.stewart.microblog.entity.*;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.repository.*;
import com.stewart.microblog.service.BlogService;
import com.stewart.microblog.util.GetCurrentUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
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
    private UserInfoRepository userInfoRepository;
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

    /**
     * 日志logger对象
     */
    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Override
    @Transactional(rollbackOn = Exception.class)
    public StatusCode likeBlog(Integer blogId) {
        //获取当前登录用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        //获取当前用户与目标博文的点赞记录
        LikeSet likeSet = likeSetRepository.findByUserIdAndBlogId(userId, blogId);
        if(likeSet != null) {
            logger.info("数据库有用户id为{}博文id为{}的点赞记录，删除标记为true", userId, blogId);
            likeSet.setDeleted(false);
            logger.info("保存修改该点赞记录删除标记为false");
            likeSetRepository.save(likeSet);
        }
        else {
            likeSetRepository.save(new LikeSet(null, userId, blogId, false));
            logger.info("插入用户id为{}博文id为{}的点赞记录", userId, blogId);
        }
        //更新点赞数据
        Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
        blog.setLikeNum(blog.getLikeNum() + 1);
        blog.setHeat(blog.getHeat() + 1);
        logger.info("更新点赞数量+1 更新热度+1");
        blogRepository.save(blog);
        return StatusCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public StatusCode dislikeBlog(Integer blogId) {
        //获取当前登录用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        //查看当前用户与目标博文的被的点赞记录
        LikeSet likeSet = likeSetRepository.findByUserIdAndBlogIdAndDeleted(userId, blogId, false);
        //更新删除标记数据
        likeSet.setDeleted(true);
        logger.info("查询用户id为{}博文id为{}}的点赞记录，删除标记改为true", userId, blogId);
        likeSetRepository.save(likeSet);
        Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
        blog.setLikeNum(blog.getLikeNum() - 1);
        blog.setHeat(blog.getHeat() - 1);
        logger.info("更新点赞数量-1 更新热度-1");
        blogRepository.save(blog);
        return StatusCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public StatusCode collectBlog(Integer blogId) {
        //获取当前登录用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        //获取当前用户与目标博文的收藏记录
        Collection collection = collectionRepository.findByUserIdAndBlogId(userId, blogId);
        if(collection != null) {
            logger.info("数据库有用户id为{}博文id为{}的收藏记录，删除标记为true", userId, blogId);
            collection.setDeleted(false);
            logger.info("保存修改该收藏记录删除标记为false");
            collectionRepository.save(collection);
        }
        else {
            collectionRepository.save(new Collection(null, userId, blogId, false));
            logger.info("插入用户id为{}博文id为{}的收藏记录", userId, blogId);
        }
        //更新收藏关系
        Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
        blog.setCollectNum(blog.getCollectNum() + 1);
        blog.setHeat(blog.getHeat() + 3);
        logger.info("更新收藏数量+1 更新热度+3");
        blogRepository.save(blog);
        return StatusCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public StatusCode disCollectBlog(Integer blogId) {
        //获取当前登录用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        //获取当前用户与目标博文的收藏关系对象
        Collection collection = collectionRepository.findByUserIdAndBlogIdAndDeleted(userId, blogId, false);
        //更新收藏关系
        collection.setDeleted(true);
        logger.info("查询用户id为{}博文id为{}的收藏记录，删除标记改为true", userId, blogId);
        collectionRepository.save(collection);
        Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
        blog.setCollectNum(blog.getCollectNum() - 1);
        blog.setHeat(blog.getHeat() - 3);
        logger.info("更新收藏数量-1 更新热度-3");
        blogRepository.save(blog);
        return StatusCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public StatusCode commentBlog(Integer blogId,  Boolean isReply, Integer replyCommentId, String content) {
        //获取当前登录用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        //创建新评论对象
        Comment comment = new Comment(null, blogId, userId, content, new Date(), isReply, replyCommentId, false);
        //保存评论
        logger.info("id为{}的用户与{}在id为{}的微博下发布评论，内容为{}", userId, new Date(), blogId, content);
        commentRepository.save(comment);
        Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
        blog.setHeat(blog.getHeat() + 2);
        blogRepository.save(blog);
        return StatusCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public StatusCode publishBlog(NewBlogDTO newBlogDTO) {
        //获取当前登录用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        //使用正则表达式分割正文并保存
        String content = newBlogDTO.getContent();
        String regex = "#.*\n";
        String str = content.replaceAll(regex, "");
        logger.info("博文正文：{}", str);
        Blog blog = new Blog(
                null, userId, new Date(), newBlogDTO.getScope(), str,
                newBlogDTO.getPicId(), 0, 0, 0, "NORMAL",
                "ORIGINAL", null, 0, false
        );
        blogRepository.save(blog);
        Blog thisBlog = blogRepository.findFirstByOrderByIdDesc();
        //使用正则表达式获取用户所使用话题
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        List<String> topicList = new ArrayList<>();
        while(matcher.find()) {
            String t = content.substring(matcher.start() + 1, matcher.end() - 1);
            logger.info("用户发布博文话题：{}", t);
            topicList.add(t);
        }
        //更新博文-话题关系表
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
                        topicRepository.findFirstByOrderByIdDesc().getId(), false));
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
