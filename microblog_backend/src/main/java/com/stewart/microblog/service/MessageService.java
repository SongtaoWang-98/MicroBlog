package com.stewart.microblog.service;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.DialogBoxVO;
import com.stewart.microblog.vo.MessageListVO;

/**
 * 私信功能接口
 * @author 王松涛
 */
public interface MessageService {
    MessageListVO showMessageList();
    DialogBoxVO showDialogBox(Integer personId);
    StatusCode sendMessage(String content, Integer personId);
}
