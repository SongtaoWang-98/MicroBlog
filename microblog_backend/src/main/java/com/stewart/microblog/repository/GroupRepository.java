package com.stewart.microblog.repository;

import com.stewart.microblog.entity.ConcernGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<ConcernGroup, Integer> {
    Integer countByUserId(Integer userId);
    List<ConcernGroup> findByUserId(Integer userId);
}
