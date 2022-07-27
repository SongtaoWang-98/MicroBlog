package com.stewart.microblog.service;

import com.stewart.microblog.dto.NewBlogDTO;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.UserPageVO;

import java.util.Date;
import java.util.List;

/**
 * 博客功能接口
 * @author 王松涛
 */
public interface BlogService {
    /**
     * 点赞博文接口
     * @param blogId
     * 用户点赞的博文id
     * @return 状态码
     */
    StatusCode likeBlog(Integer blogId);

    /**
     * 取消点赞博文接口
     * @param blogId
     * 用户取消点赞的博文id
     * @return 状态码
     */
    StatusCode dislikeBlog(Integer blogId);

    /**
     * 收藏博文接口
     * @param blogId
     * 用户收藏的博文id
     * @return 状态码
     */
    StatusCode collectBlog(Integer blogId);

    /**
     * 取消收藏博文接口
     * @param blogId
     * 用户取消收藏的博文id
     * @return 状态码
     */
    StatusCode disCollectBlog(Integer blogId);

    /**
     *
     * @param blogId
     * @param isReply
     * @param replyCommentId
     * @param content
     * @return
     */
    StatusCode commentBlog(Integer blogId, Boolean isReply, Integer replyCommentId, String content);

    /**
     *
     * @param newBlogDTO
     * @return
     */
    StatusCode publishBlog(NewBlogDTO newBlogDTO);
    StatusCode repostBlog(Integer blogId);
    StatusCode reportBlog(Integer blogId, String content, String email);
}
