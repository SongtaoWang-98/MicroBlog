package com.stewart.microblog.repository;

import com.stewart.microblog.entity.GroupFollow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 关注组-关注持久化接口
 * @author 王松涛
 */
public interface GroupFollowRepository extends JpaRepository<GroupFollow, Integer> {
    /**
     * 通过关注组id查找关注组包含列表
     * @param groupId
     * Integer类型关注组id
     * @param deleted
     * Boolean类型删除标记
     * @return 有关该关注组的关注组-关注列表
     */
    List<GroupFollow> findAllByGroupIdAndDeleted(Integer groupId, Boolean deleted);
}
