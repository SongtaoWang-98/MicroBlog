package com.stewart.microblog.repository;

import com.stewart.microblog.entity.HeatTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeatTimeRepository extends JpaRepository<HeatTime, Integer> {
}
