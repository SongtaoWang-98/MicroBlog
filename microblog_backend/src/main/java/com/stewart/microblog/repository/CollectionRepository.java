package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 收藏持久化接口
 * @author 王松涛
 */
public interface CollectionRepository extends JpaRepository<Collection, Integer> {

    /**
     * 根据用户id查询所有收藏
     * @param userId
     * Integer类型用户id
     * @param deleted
     * Boolean类型删除标记
     * @return 用户收藏列表
     */
    List<Collection> findAllByUserIdAndDeleted(Integer userId, Boolean deleted);

    /**
     * 根据用户id和博文id查询收藏关系
     * @param userId
     * Integer类型用户id
     * @param blogId
     * Integer类型博文id
     * @return 用户收藏对象Collection
     */
    Collection findByUserIdAndBlogId(Integer userId, Integer blogId);

    /**
     * 根据用户id和博文id查询收藏关系
     * @param userId
     * Integer类型用户id
     * @param blogId
     * Integer类型博文id
     * @param deleted
     * Boolean类型删除标记
     * @return 用户收藏对象Collection
     */
    Collection findByUserIdAndBlogIdAndDeleted(Integer userId, Integer blogId, Boolean deleted);
}
