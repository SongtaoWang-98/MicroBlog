package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author stewartwang
 */
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Optional<Blog> findById(Integer id);
    List<Blog> findAllByOrderByHeatDesc();
}
