package com.stewart.microblog.controller;

import com.stewart.microblog.aop.SystemControllerLog;
import com.stewart.microblog.exception.BizException;
import com.stewart.microblog.service.MessageService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 查看私信列表接口
     * @return ResultVO
     */
    @GetMapping("/messageList")
    @SystemControllerLog(description = "查看私信列表")
    public ResultVO showMessages() {
        return ResultVOUtil.success(messageService.showMessageList());
    }

    /**
     * 查看对话框接口
     * @param personId
     * Integer类型用户id
     * @return ResultVO
     */
    @GetMapping("/messagePage")
    @SystemControllerLog(description = "查看与用户私信对话")
    public ResultVO showMessagesToPerson(Integer personId) {
        if(personId == null) {
            throw new BizException("-1", "用户id不能为空");
        }
        return ResultVOUtil.success(messageService.showMessagePage(personId));
    }

    /**
     * 用户发送私信功能接口
     * @param personId
     * Integer类型用户id
     * @param content
     * String类型私信内容
     * @return ResultVO
     */
    @PostMapping("/send")
    @SystemControllerLog(description = "发送私信")
    public ResultVO sendMessage(Integer personId, String content) {
        if(personId == null) {
            throw new BizException("-1", "用户id不能为空");
        }
        if(content == null) {
            throw new BizException("-1", "私信内容id不能为空");
        }
        return ResultVOUtil.success(messageService.sendMessage(content, personId));
    }

}
