package com.stewart.microblog.service.impl;

import com.stewart.microblog.dto.NewBlogDTO;
import com.stewart.microblog.entity.*;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.repository.*;
import com.stewart.microblog.service.BlogService;
import com.stewart.microblog.util.GetCurrentUserUtil;
import com.stewart.microblog.vo.UserPageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @Override
    public StatusCode likeBlog(Integer blogId) {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userRepository.findByUsername(userName).getId();
        LikeSet likeSet = likeSetRepository.findByUserIdAndAndBlogId(userId, blogId);
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
        return null;
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
