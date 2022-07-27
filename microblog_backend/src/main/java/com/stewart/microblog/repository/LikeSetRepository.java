package com.stewart.microblog.repository;

import com.stewart.microblog.entity.LikeSet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 点赞持久化接口
 * @author 王松涛
 */
public interface LikeSetRepository extends JpaRepository<LikeSet, Integer> {
    LikeSet findByUserIdAndAndBlogId(Integer userId, Integer blogId);
    LikeSet findByUserIdAndBlogIdAndDeleted(Integer userId, Integer blogId, Boolean deleted);
}
