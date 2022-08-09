package com.stewart.microblog.service.impl;

import com.stewart.microblog.entity.Message;
import com.stewart.microblog.entity.UserInfo;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.repository.MessageRepository;
import com.stewart.microblog.repository.PictureRepository;
import com.stewart.microblog.repository.UserInfoRepository;
import com.stewart.microblog.service.MessageService;
import com.stewart.microblog.util.GetCurrentUserUtil;
import com.stewart.microblog.vo.MessageListItemVO;
import com.stewart.microblog.vo.MessageListVO;
import com.stewart.microblog.vo.MessagePageItemVO;
import com.stewart.microblog.vo.MessagePageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 私信功能接口实现类
 * @author 王松涛
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageRepository messageRepository;
    @Resource
    private UserInfoRepository userInfoRepository;
    @Resource
    private PictureRepository pictureRepository;

    @Override
    public MessageListVO showMessageList() {
        //获取当前用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        //找到自己为收信人的私信列表
        List<Message> receiveMessageList = messageRepository.findAllByReceiverIdAndDeletedOrderByTimeDesc(userId, false);
        //找到自己为发信人的私信列表
        List<Message> sendMessageList = messageRepository.findAllBySenderIdAndDeletedOrderByTimeDesc(userId, false);
        List<MessageListItemVO> messageListItemVOList = new ArrayList<>();
        //用Set集合保存聊天对象并做到去重
        Set<Integer> idSet = new HashSet<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int reIndex = 0;
        int seIndex = 0;
        //遍历与自己相关的私信列表，取出与每个人的最后一条私信信息
        while(reIndex + seIndex < sendMessageList.size() + receiveMessageList.size()) {
            //若收件人列表已遍历结束，或此条发送信息时间更迟
            if(reIndex == receiveMessageList.size() || seIndex < sendMessageList.size() && reIndex < receiveMessageList.size()
                    && receiveMessageList.get(reIndex).getTime().before(sendMessageList.get(seIndex).getTime())) {
                if(idSet.add(sendMessageList.get(seIndex).getReceiverId())) {
                    Message message = sendMessageList.get(seIndex);
                    UserInfo person = userInfoRepository.findUserByIdAndState(message.getReceiverId(), "NORMAL");
                    messageListItemVOList.add(new MessageListItemVO(
                            message.getId(), message.getReceiverId(),
                            pictureRepository.findPictureByIdAndDeleted(person.getPhotoId(), false ).getUrl(),
                            person.getNickname(), dateFormat.format(message.getTime()), message.getContent(), "READ"
                    ));
                }
                seIndex ++;
            }
            else if(seIndex == sendMessageList.size() || seIndex < sendMessageList.size() && reIndex < receiveMessageList.size()
                    && receiveMessageList.get(reIndex).getTime().after(sendMessageList.get(seIndex).getTime())) {
                if(idSet.add(receiveMessageList.get(reIndex).getSenderId())) {
                    Message message = receiveMessageList.get(reIndex);
                    UserInfo person = userInfoRepository.findUserByIdAndState(message.getSenderId(), "NORMAL");
                    messageListItemVOList.add(new MessageListItemVO(
                            message.getId(), message.getSenderId(),
                            pictureRepository.findPictureByIdAndDeleted(person.getPhotoId(), false ).getUrl(),
                            person.getNickname(), dateFormat.format(message.getTime()), message.getContent(), message.getState()
                    ));
                }
                reIndex ++;
            }
        }
        return new MessageListVO(messageListItemVOList);
    }

    @Override
    public MessagePageVO showMessagePage(Integer personId) {
        //获取当前用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        //获取双方头像信息
        String personPic = pictureRepository.findPictureByIdAndDeleted(userInfoRepository.findUserByIdAndState(
                personId, "NORMAL").getPhotoId(), false).getUrl();
        String userPic = pictureRepository.findPictureByIdAndDeleted(userInfoRepository.findUserByIdAndState(
                userId, "NORMAL").getPhotoId(), false).getUrl();
        //获取双方聊天历史记录
        List<MessagePageItemVO> messagePageItemVOList = new ArrayList<>();
        List<Message> receiveMessageList = messageRepository.findAllBySenderIdAndReceiverIdAndDeletedOrderByTime(personId, userId, false);
        List<Message> sendMessageList = messageRepository.findAllBySenderIdAndReceiverIdAndDeletedOrderByTime(userId, personId, false);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //刷新已读标记
        for(Message message: receiveMessageList) {
            message.setState("READ");
            messageRepository.save(message);
        }
        //遍历两个列表展示对话
        int reIndex = 0;
        int seIndex = 0;
        while(reIndex + seIndex < receiveMessageList.size() + sendMessageList.size()) {
            if(reIndex == receiveMessageList.size() || reIndex < receiveMessageList.size() && seIndex < sendMessageList.size()
                    && receiveMessageList.get(reIndex).getTime().after(sendMessageList.get(seIndex).getTime())) {
                Message message = sendMessageList.get(seIndex);
                messagePageItemVOList.add(new MessagePageItemVO(
                        userPic, dateFormat.format(message.getTime()), message.getContent(), "SEND"
                ));
                seIndex ++;
            }
            else {
                Message message = receiveMessageList.get(reIndex);
                messagePageItemVOList.add(new MessagePageItemVO(
                        personPic, dateFormat.format(message.getTime()), message.getContent(), "RECEIVE"
                ));
                reIndex ++;
            }
        }
        return new MessagePageVO(
                userInfoRepository.findUserByIdAndState(personId, "NORMAL").getNickname(), messagePageItemVOList
        );
    }

    @Override
    public StatusCode sendMessage(String content, Integer personId) {
        //获取当前用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        //创建新的私信对象
        Message message = new Message(
                null, userId, personId, new Date(), "UNREAD", content, false
        );
        messageRepository.save(message);
        return StatusCode.SUCCESS;
    }

}
