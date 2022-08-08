package com.stewart.microblog.repository;

import com.stewart.microblog.entity.ConcernGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 关注组持久化接口
 * @author 王松涛
 */
public interface ConcernGroupRepository extends JpaRepository<ConcernGroup, Integer> {

    /**
     * 根据用户id查找关注组列表
     * @param userId
     * Integer类型用户id
     * @param deleted
     * Boolean类型删除标记
     * @return 关注组列表
     */
    List<ConcernGroup> findByUserIdAndDeleted(Integer userId, Boolean deleted);
}
