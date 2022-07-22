package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 私信持久化接口
 * @author 王松涛
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
