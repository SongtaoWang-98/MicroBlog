package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author stewartwang
 */
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Blog findBlogByIdAndDeleted(Integer id, Boolean deleted);
    List<Blog> findAllByScopeAndDeletedAndStateOrderByHeatDesc(String scope, Boolean deleted, String state);
    List<Blog> findAllByScopeAndDeletedAndStateOrderByTimeDesc(String scope, Boolean deleted, String state);
    List<Blog> findAllByScopeInAndDeletedAndStateAndPublisherIdInOrderByTimeDesc(Collection<String> scopes, Boolean deleted, String state, Collection<Integer> personIds);
}
