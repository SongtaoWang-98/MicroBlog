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
import com.stewart.microblog.vo.MessagePageVO;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 私信功能接口实现类
 * @author 王松涛
 */
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageRepository messageRepository;
    @Resource
    private UserInfoRepository userInfoRepository;
    @Resource
    private PictureRepository pictureRepository;
    @Override
    public MessageListVO showMessageList() {
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();
        List<Message> receiveMessageList = messageRepository.findAllByReceiverIdAndDeletedOrderByTimeDesc(userId, false);
        List<Message> sendMessageList = messageRepository.findAllBySenderIdAndDeletedOrderByTimeDesc(userId, false);
        List<MessageListItemVO> messageListItemVOList = new ArrayList<>();
        Set<Integer> idSet = new HashSet<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int reIndex = 0;
        int seIndex = 0;
        while(reIndex + seIndex < sendMessageList.size() + receiveMessageList.size()) {
            if(reIndex == receiveMessageList.size()) {
                if(idSet.add(sendMessageList.get(seIndex).getReceiverId())) {

                }
                continue;
            }
            if(seIndex == sendMessageList.size()) {
                continue;
            }

            if(receiveMessageList.get(reIndex).getTime().after(sendMessageList.get(seIndex).getTime())) {
                if(idSet.add(receiveMessageList.get(reIndex).getSenderId())) {
                    Message message = receiveMessageList.get(reIndex);
                    UserInfo person = userInfoRepository.findUserByIdAndState(message.getId(), "NORMAL");
                    messageListItemVOList.add(new MessageListItemVO(
                            message.getId(), message.getSenderId(),
                            pictureRepository.findPictureByIdAndDeleted(person.getPhotoId(), false ).getUrl(),
                            person.getNickname(), dateFormat.format(message.getTime()), message.getContent(), message.getState()
                    ));
                }
                else {
                    reIndex ++;
                }
            }
            else {
                if(idSet.add(sendMessageList.get(seIndex).getReceiverId())) {
                    Message message = sendMessageList.get(seIndex);
                    UserInfo person = userInfoRepository.findUserByIdAndState(message.getId(), "NORMAL");
                    messageListItemVOList.add(new MessageListItemVO(
                            message.getId(), message.getReceiverId(),
                            pictureRepository.findPictureByIdAndDeleted(person.getPhotoId(), false ).getUrl(),
                            person.getNickname(), dateFormat.format(message.getTime()), message.getContent(), message.getState()
                    ));
                }
                else {
                    seIndex ++;
                }
            }
        }
        return new MessageListVO(messageListItemVOList);
    }

    @Override
    public MessagePageVO showMessagePage(Integer personId) {
        return null;
    }

    @Override
    public StatusCode sendMessage(String content, Integer personId) {
        return null;
    }
}
