package com.stewart.microblog.repository;

import com.stewart.microblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Administrator
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String userName);
    User findUserById(Integer id);
}
