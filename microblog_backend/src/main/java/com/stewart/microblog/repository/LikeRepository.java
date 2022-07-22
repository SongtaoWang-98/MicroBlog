package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 点赞持久化接口
 * @author 王松涛
 */
public interface LikeRepository extends JpaRepository<Like, Integer> {
}
