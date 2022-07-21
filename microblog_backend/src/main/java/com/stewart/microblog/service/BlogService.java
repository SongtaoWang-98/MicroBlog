package com.stewart.microblog.service;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.UserPageVO;

import java.util.Date;
import java.util.List;

/**
 * @author stewartwang
 */
public interface BlogService {
    StatusCode likeBlog(Integer blogId);
    StatusCode disLikeBlog(Integer blogId);
    StatusCode collectBlog(Integer blogId);
    StatusCode disCollectBlog(Integer blogId);
    UserPageVO showCollectList();
    StatusCode commentBlog(Integer blogId, Date time, Boolean isReply, Integer replyCommentId);
    StatusCode publishBlog(String content, Integer picId, List<String> topics);
    StatusCode repostBlog(Integer blogId);
    StatusCode reportBlog(Integer blogId, String content, String email);
}
