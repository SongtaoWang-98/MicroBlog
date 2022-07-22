package com.stewart.microblog.repository;

import com.stewart.microblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户数据持久层接口
 * @author 王松涛
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 通过用户名查询用户
     * @param userName
     * String类型用户名
     * @return 用户对象User
     */
    User findByUsername(String userName);

    /**
     * 通过用户id和状态查询用户
     * @param id
     * Integer类型用户id
     * @param state
     * String类型用户状态
     * @return 用户对象User
     */
    User findUserByIdAndState(Integer id, String state);
}
