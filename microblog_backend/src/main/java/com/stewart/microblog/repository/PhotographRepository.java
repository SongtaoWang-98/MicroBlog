package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Photograph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 相册持久化接口
 * @author 王松涛
 */
public interface PhotographRepository extends JpaRepository<Photograph, Integer> {
}
