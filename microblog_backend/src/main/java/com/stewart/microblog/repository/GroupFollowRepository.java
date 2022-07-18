package com.stewart.microblog.repository;

import com.stewart.microblog.entity.GroupFollow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupFollowRepository extends JpaRepository<GroupFollow, Integer> {
}
