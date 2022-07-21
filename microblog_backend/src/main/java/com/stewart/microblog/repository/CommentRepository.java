package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByBlogIdAndDeleted(Integer blogId, Boolean deleted);
}
