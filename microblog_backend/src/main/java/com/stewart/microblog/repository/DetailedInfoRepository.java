package com.stewart.microblog.repository;

import com.stewart.microblog.entity.DetailedInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

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

    /**
     * 查询性别为空的用户数量
     * @return Integer类型数量
     */
    Integer countByGenderIsNull();

    /**
     * 根据性别查询数量
     * @param gender
     * String类型性别
     * @return Integer类型数量
     */
    Integer countByGender(String gender);

    /**
     * 查询生日信息为空的用户数量
     * @return Integer类型数量
     */
    Integer countByBirthdayIsNull();

    /**
     * 根据两个时间查询中间数量
     * @param date1
     * Date类型起始日期
     * @param date2
     * Date类型终止日期
     * @return Integer类型数量
     */
    Integer countByBirthdayBetween(Date date1, Date date2);

    /**
     * 查询某时间之前的数量
     * @param date
     * Date类型日期时间
     * @return Integer类型数量
     */
    Integer countByBirthdayBefore(Date date);

    /**
     * 查询某事件之后的数量
     * @param date
     * Date类型日期时间
     * @return Integer类型数量
     */
    Integer countByBirthdayAfter(Date date);
}
