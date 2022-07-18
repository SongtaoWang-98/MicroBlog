package com.stewart.microblog.repository;

import com.stewart.microblog.entity.BlogTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogTopicRepository extends JpaRepository<BlogTopic, Integer> {
}
