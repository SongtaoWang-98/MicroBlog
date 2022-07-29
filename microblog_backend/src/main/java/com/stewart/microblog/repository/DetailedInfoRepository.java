package com.stewart.microblog.repository;

import com.stewart.microblog.entity.DetailedInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 个人信息持久化接口
 * @author 王松涛
 */
public interface DetailedInfoRepository extends JpaRepository<DetailedInfo, Integer> {
    /**
     * 通过用户id查找用户详细信息
     * @param userId
     * Integer类型用户id
     * @return 用户信息Information
     */
    DetailedInfo findInformationByUserId(Integer userId);

}