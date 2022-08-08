package com.stewart.microblog.repository;

import com.stewart.microblog.entity.HeatTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 热度时间持久化接口
 * @author 王松涛
 */
public interface HeatTimeRepository extends JpaRepository<HeatTime, Integer> {

    /**
     * 根据话题id查询其热度列表
     * @param topicId
     * Integer类型话题id
     * @return 热度列表
     */
    List<HeatTime> findByTopicId(Integer topicId);
}
