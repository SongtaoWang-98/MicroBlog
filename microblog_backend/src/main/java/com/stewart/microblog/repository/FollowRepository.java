package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 关注持久化接口
 * @author 王松涛
 */
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    /**
     * 根据被关注id查找粉丝数量
     * @param userId
     * Integer类型用户id
     * @param deleted
     * Boolean类型的删除标记
     * @return Integer类型粉丝数量
     */
    Integer countByFollowingIdAndDeleted(Integer userId, Boolean deleted);

    /**
     * 根据用户id查找关注数量
     * @param userId
     * Integer类型用户id
     * @param deleted
     * Boolean类型删除标记
     * @return Integer类型关注数量
     */
    Integer countByUserIdAndDeleted(Integer userId, Boolean deleted);

    /**
     * 根据用户id查找所有关注列表
     * @param userId
     * Integer类型用户id
     * @param deleted
     * Boolean类型删除标记
     * @return 关注列表
     */
    List<Follow> findAllByUserIdAndDeleted(Integer userId, Boolean deleted);

    /**
     * 根据用户id查找所有被关注（粉丝）列表
     * @param userId
     * Integer类型用户id
     * @param deleted
     * Boolean类型删除标记
     * @return 粉丝列表
     */
    List<Follow> findAllByFollowingIdAndDeleted(Integer userId, Boolean deleted);

    /**
     * 根据id查找关注信息
     * @param id
     * 关注表主键id
     * @param deleted
     * Boolean类型删除标记
     * @return 关注对象Follow
     */
    Follow findFollowByIdAndDeleted(Integer id, Boolean deleted);

    /**
     * 根据用户id和关注id查找关注关系
     * @param userId
     * Integer类型用户id
     * @param followingId
     * Integer类型关注
     * @param deleted
     * Boolean类型删除标记
     * @return 关注关系对象Follow
     */
    Follow findFollowByUserIdAndFollowingIdAndDeleted(Integer userId, Integer followingId, Boolean deleted);
}
