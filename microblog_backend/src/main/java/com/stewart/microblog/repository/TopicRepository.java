package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    List<Topic> findAllByDeletedOrderByHeatDesc(Boolean deleted);

    Topic findTopicByIdAndDeleted(Integer id, Boolean deleted);
}
