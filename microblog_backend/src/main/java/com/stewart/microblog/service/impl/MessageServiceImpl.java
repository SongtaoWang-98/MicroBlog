package com.stewart.microblog.service.impl;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.service.MessageService;
import com.stewart.microblog.vo.DialogBoxVO;
import com.stewart.microblog.vo.MessageListVO;

/**
 * 私信功能接口实现类
 * @author 王松涛
 */
public class MessageServiceImpl implements MessageService {
    @Override
    public MessageListVO showMessageList() {
        return null;
    }

    @Override
    public DialogBoxVO showDialogBox(Integer personId) {
        return null;
    }

    @Override
    public StatusCode sendMessage(String content, Integer personId) {
        return null;
    }
}
