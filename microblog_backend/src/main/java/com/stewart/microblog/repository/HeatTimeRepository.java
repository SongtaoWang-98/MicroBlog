package com.stewart.microblog.repository;

import com.stewart.microblog.entity.HeatTime;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 热度时间持久化接口
 * @author 王松涛
 */
public interface HeatTimeRepository extends JpaRepository<HeatTime, Integer> {
}
