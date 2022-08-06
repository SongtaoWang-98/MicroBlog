package com.stewart.microblog.repository;

import com.stewart.microblog.entity.HeatTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 热度时间持久化接口
 * @author 王松涛
 */
public interface HeatTimeRepository extends JpaRepository<HeatTime, Integer> {
    List<HeatTime> findByTopicId(Integer topicId);
}
