package com.stewart.microblog.repository;

import com.stewart.microblog.entity.ConcernGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcernGroupRepository extends JpaRepository<ConcernGroup, Integer> {
    Integer countByUserIdAndDeleted(Integer userId, Boolean deleted);
    List<ConcernGroup> findByUserIdAndDeleted(Integer userId, Boolean deleted);
}
