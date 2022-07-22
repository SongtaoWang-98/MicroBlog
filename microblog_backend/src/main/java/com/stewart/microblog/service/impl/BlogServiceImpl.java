package com.stewart.microblog.service.impl;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.service.BlogService;
import com.stewart.microblog.vo.UserPageVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 博客功能接口实现类
 * @author 王松涛
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Override
    public StatusCode likeBlog(Integer blogId) {
        return null;
    }

    @Override
    public StatusCode disLikeBlog(Integer blogId) {
        return null;
    }

    @Override
    public StatusCode collectBlog(Integer blogId) {
        return null;
    }

    @Override
    public StatusCode disCollectBlog(Integer blogId) {
        return null;
    }

    @Override
    public UserPageVO showCollectList() {
        return null;
    }

    @Override
    public StatusCode commentBlog(Integer blogId, Date time, Boolean isReply, Integer replyCommentId) {
        return null;
    }

    @Override
    public StatusCode publishBlog(String content, Integer picId, List<String> topics) {
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
