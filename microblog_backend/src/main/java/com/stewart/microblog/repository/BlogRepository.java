package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 博客持久化接口
 * @author 王松涛
 */
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    /**
     * 根据博客id查找博客
     * @param id
     * Integer类型博客id
     * @param deleted
     * Boolean类型删除标记
     * @return 博客对象Blog
     */
    Blog findBlogByIdAndDeleted(Integer id, Boolean deleted);

    /**
     * 根据可见范围按热度倒序查看最热博文列表
     * @param scope
     * String类型可见范围
     * @param deleted
     * Boolean类型删除标记
     * @param state
     * String类型封禁状态
     * @return 最热博文列表
     */
    List<Blog> findAllByScopeAndDeletedAndStateOrderByHeatDesc(String scope, Boolean deleted, String state);

    /**
     * 根据可见范围按时间倒序查看最新博文列表
     * @param scope
     * String类型可见范围
     * @param deleted
     * Boolean类型删除标记
     * @param state
     * String类型封禁状态
     * @return 最新博文列表
     */
    List<Blog> findAllByScopeAndDeletedAndStateOrderByTimeDesc(String scope, Boolean deleted, String state);

    /**
     * 根据可见范围组和发布者范围查看最新博文列表
     * @param scopes
     * String集合可见范围列表
     * @param deleted
     * String类型删除标记
     * @param state
     * String类型封禁状态
     * @param personIds
     * Integer集合发布者范围
     * @return 限定范围内博文列表
     */
    List<Blog> findAllByScopeInAndDeletedAndStateAndPublisherIdInOrderByTimeDesc(Collection<String> scopes, Boolean deleted, String state, Collection<Integer> personIds);
}
