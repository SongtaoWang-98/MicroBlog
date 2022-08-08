package com.stewart.microblog.service;

import com.stewart.microblog.dto.NewBlogDTO;
import com.stewart.microblog.enums.StatusCode;

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
     * 博文评论功能接口
     * @param blogId
     * Integer类型用户id
     * @param isReply
     * Boolean类型是否为回复
     * @param replyCommentId
     * Integer类型回复id
     * @param content
     * String类型博文内容
     * @return 状态码
     */
    StatusCode commentBlog(Integer blogId, Boolean isReply, Integer replyCommentId, String content);

    /**
     * 发布博文功能接口
     * @param newBlogDTO
     * 博文表单对象
     * @return 状态码
     */
    StatusCode publishBlog(NewBlogDTO newBlogDTO);

    StatusCode repostBlog(Integer blogId);
    StatusCode reportBlog(Integer blogId, String content, String email);
}
