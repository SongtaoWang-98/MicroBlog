package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author stewartwang
 */
public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
