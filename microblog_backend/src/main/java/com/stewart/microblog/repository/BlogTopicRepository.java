package com.stewart.microblog.repository;

import com.stewart.microblog.entity.BlogTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogTopicRepository extends JpaRepository<BlogTopic, Integer> {
    List<BlogTopic> findBlogTopicsByBlogId(Integer blogId);
}
