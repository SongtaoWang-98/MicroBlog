package com.stewart.microblog.repository;

import com.stewart.microblog.entity.GroupFollow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupFollowRepository extends JpaRepository<GroupFollow, Integer> {
    List<GroupFollow> findAllByGroupId(Integer groupId);
}
