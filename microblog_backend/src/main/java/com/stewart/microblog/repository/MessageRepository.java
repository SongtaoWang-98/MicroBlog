package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 私信持久化接口
 * @author 王松涛
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByReceiverIdAndDeletedOrderByTimeDesc(Integer receiverId, Boolean deleted);
    List<Message> findAllBySenderIdAndDeletedOrderByTimeDesc(Integer senderId, Boolean deleted);
    List<Message> findAllBySenderIdAndReceiverIdAndDeletedOrderByTime(Integer senderId, Integer receiverId, Boolean deleted);
}
