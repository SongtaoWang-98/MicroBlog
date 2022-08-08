package com.stewart.microblog.repository;

import com.stewart.microblog.entity.LikeSet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 点赞持久化接口
 * @author 王松涛
 */
public interface LikeSetRepository extends JpaRepository<LikeSet, Integer> {

    /**
     * 根据用户id和博客id查询点赞关系
     * @param userId
     * Integer类型用户id
     * @param blogId
     * Integer类型博客id
     * @return 点赞表对象LikeSet
     */
    LikeSet findByUserIdAndBlogId(Integer userId, Integer blogId);

    /**
     * 根据用户id和博客id查询点赞关系
     * @param userId
     * Integer类型用户id
     * @param blogId
     * Integer类型博客id
     * @param deleted
     * Boolean类型删除标记
     * @return 点赞表对象LikeSet
     */
    LikeSet findByUserIdAndBlogIdAndDeleted(Integer userId, Integer blogId, Boolean deleted);
}
