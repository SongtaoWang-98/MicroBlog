package com.stewart.microblog.repository;

import com.stewart.microblog.entity.BlogTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 博客-话题持久化接口
 * @author 王松涛
 */
public interface BlogTopicRepository extends JpaRepository<BlogTopic, Integer> {

    /**
     * 根据博客id查找博客-话题关系
     * @param blogId
     * Integer类型博客id
     * @param deleted
     * Boolean类型删除标记
     * @return 博客-话题列表
     */
    List<BlogTopic> findBlogTopicsByBlogIdAndDeleted(Integer blogId, Boolean deleted);

    /**
     * 根据话题id查找博客-话题关系
     * @param topicId
     * Integer类型话题id
     * @param deleted
     * Boolean类型删除标记
     * @return 博客-话题列表
     */
    List<BlogTopic> findBlogTopicsByTopicIdAndDeleted(Integer topicId, Boolean deleted);
}
