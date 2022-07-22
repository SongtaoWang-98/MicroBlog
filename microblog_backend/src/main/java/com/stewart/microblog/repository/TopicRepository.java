package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 话题数据持久层接口
 * @author 王松涛
 */
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    /**
     * 话题列表依热度倒序排列
     * @param deleted
     * Boolean类型删除标记
     * @return 话题对象列表
     */
    List<Topic> findAllByDeletedOrderByHeatDesc(Boolean deleted);

    /**
     * 通过话题id查找话题
     * @param id
     * Integer类型话题id
     * @param deleted
     * Boolean类型删除标记
     * @return 话题对象Topic
     */
    Topic findTopicByIdAndDeleted(Integer id, Boolean deleted);
}
