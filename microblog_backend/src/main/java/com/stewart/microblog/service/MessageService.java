package com.stewart.microblog.service;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.MessageListVO;
import com.stewart.microblog.vo.MessagePageVO;

/**
 * 私信功能接口
 * @author 王松涛
 */
public interface MessageService {

    /**
     * 展示消息列表
     * @return 消息列表视图对象
     */
    MessageListVO showMessageList();

    /**
     * 展示消息对话页
     * @param personId
     * 收信人用户id
     * @return 对话页视图对象
     */
    MessagePageVO showMessagePage(Integer personId);

    /**
     * 发送私信
     * @param content
     * 私信内容
     * @param personId
     * 私信对象id
     * @return StatusCode
     */
    StatusCode sendMessage(String content, Integer personId);
}
