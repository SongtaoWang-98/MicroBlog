package com.stewart.microblog.controller;

import com.stewart.microblog.service.MessageService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 私信控制层类
 * @author 王松涛
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageService messageService;
    @GetMapping("/messageList")
    public ResultVO showMessages() {
        return ResultVOUtil.success(messageService.showMessageList());
    }

}
