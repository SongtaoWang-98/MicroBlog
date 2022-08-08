package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 私信持久化接口
 * @author 王松涛
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {

    /**
     * 根据收件人id按时间倒序查询私信列表
     * @param receiverId
     * Integer类型收件人id
     * @param deleted
     * Boolean类型删除标记
     * @return 私信列表
     */
    List<Message> findAllByReceiverIdAndDeletedOrderByTimeDesc(Integer receiverId, Boolean deleted);

    /**
     * 根据发件人id按时间倒序查询私信列表
     * @param senderId
     * Integer类型发件人id
     * @param deleted
     * Boolean类型删除标记
     * @return 私信列表
     */
    List<Message> findAllBySenderIdAndDeletedOrderByTimeDesc(Integer senderId, Boolean deleted);

    /**
     * 根据收件人id和发件人id按时间倒序查询私信列表
     * @param senderId
     * Integer类型收件人id
     * @param receiverId
     * Integer类型发件人id
     * @param deleted
     * Boolean 类型删除标记
     * @return 私信列表
     */
    List<Message> findAllBySenderIdAndReceiverIdAndDeletedOrderByTime(Integer senderId, Integer receiverId, Boolean deleted);
}
