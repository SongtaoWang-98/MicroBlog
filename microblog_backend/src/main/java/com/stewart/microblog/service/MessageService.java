package com.stewart.microblog.service;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.MessageListVO;
import com.stewart.microblog.vo.MessagePageVO;

/**
 * 私信功能接口
 * @author 王松涛
 */
public interface MessageService {
    MessageListVO showMessageList();
    MessagePageVO showMessagePage(Integer personId);
    StatusCode sendMessage(String content, Integer personId);
}
