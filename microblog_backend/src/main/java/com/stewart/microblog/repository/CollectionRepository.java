package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 收藏持久化接口
 * @author 王松涛
 */
public interface CollectionRepository extends JpaRepository<Collection, Integer> {
}
