package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Integer countByFollowingIdAndDeleted(Integer userId, Boolean deleted);
    Integer countByUserIdAndDeleted(Integer userId, Boolean deleted);
    List<Follow> findAllByUserIdAndDeleted(Integer userId, Boolean deleted);
    List<Follow> findAllByFollowingIdAndDeleted(Integer userId, Boolean deleted);
    Follow findFollowById(Integer id);
}
