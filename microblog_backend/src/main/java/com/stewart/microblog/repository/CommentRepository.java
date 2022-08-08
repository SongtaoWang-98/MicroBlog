package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 评论持久化接口
 * @author 王松涛
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * 根据博客id查找评论列表
     * @param blogId
     * Integer类型博客id
     * @param deleted
     * Boolean类型删除标记
     * @return 评论列表
     */
    List<Comment> findAllByBlogIdAndDeleted(Integer blogId, Boolean deleted);
}
