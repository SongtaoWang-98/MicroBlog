package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 收藏持久化接口
 * @author 王松涛
 */
public interface CollectionRepository extends JpaRepository<Collection, Integer> {
    List<Collection> findAllByUserIdAndDeleted(Integer userId, Boolean deleted);
    Collection findByUserIdAndBlogId(Integer userId, Integer blogId);
    Collection findByUserIdAndBlogIdAndDeleted(Integer userId, Integer blogId, Boolean deleted);
}
