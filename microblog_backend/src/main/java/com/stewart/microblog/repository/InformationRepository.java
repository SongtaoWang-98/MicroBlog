package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 个人信息持久化接口
 * @author 王松涛
 */
public interface InformationRepository extends JpaRepository<Information, Integer> {
    /**
     * 通过用户id查找用户详细信息
     * @param userId
     * Integer类型用户id
     * @return 用户信息Information
     */
    Information findInformationByUserId(Integer userId);

}
