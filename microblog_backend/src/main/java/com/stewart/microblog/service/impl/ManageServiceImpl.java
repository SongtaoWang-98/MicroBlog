package com.stewart.microblog.service.impl;

import com.stewart.microblog.entity.*;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.repository.*;
import com.stewart.microblog.service.ManageService;
import com.stewart.microblog.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员功能接口实现类
 * @author 王松涛
 */
@Service
public class ManageServiceImpl implements ManageService {
    @Resource
    private UserInfoRepository userInfoRepository;
    @Resource
    private BlogRepository blogRepository;
    @Resource
    private DetailedInfoRepository detailedInfoRepository;
    @Resource
    private TopicRepository topicRepository;
    @Resource
    private BlogTopicRepository blogTopicRepository;
    @Resource
    private HeatTimeRepository heatTimeRepository;

    private static final String STATE_BANNED = "BANNED";
    private static final String STATE_NORMAL = "NORMAL";

    @Override
    public ManagerUserListVO showUserList() {
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        List<ManagerUserListItemVO> managerUserListItemVOList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for(UserInfo userInfo: userInfoList) {
            managerUserListItemVOList.add(new ManagerUserListItemVO(
                    userInfo.getId(), userInfo.getNickname(), userInfo.getUsername(),
                    userInfo.getState(), dateFormat.format(userInfo.getLastTime())
            ));
        }
        return new ManagerUserListVO(managerUserListItemVOList);
    }

    @Override
    public StatusCode banUser(Integer userId) {
        UserInfo userInfo = userInfoRepository.findUserByIdAndState(userId, STATE_NORMAL);
        userInfo.setState(STATE_BANNED);
        userInfoRepository.save(userInfo);
        List<Blog> blogList = blogRepository.findAllByPublisherIdAndDeleted(userId, false);
        for(Blog blog: blogList) {
            blog.setState(STATE_BANNED);
            blogRepository.save(blog);
        }
        return StatusCode.SUCCESS;
    }

    @Override
    public StatusCode unbanUser(Integer userId) {
        UserInfo userInfo = userInfoRepository.findUserByIdAndState(userId, STATE_BANNED);
        userInfo.setState(STATE_NORMAL);
        userInfoRepository.save(userInfo);
        List<Blog> blogList = blogRepository.findAllByPublisherIdAndDeleted(userId, false);
        for(Blog blog: blogList) {
            blog.setState(STATE_NORMAL);
            blogRepository.save(blog);
        }
        return StatusCode.SUCCESS;
    }

    @Override
    public ManagerTotalStatVO showTotalStat() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new ManagerTotalStatVO(
                userInfoRepository.count(), blogRepository.count(), topicRepository.count(),
                detailedInfoRepository.countByGender("MALE"), detailedInfoRepository.countByGender("FEMALE"),
                detailedInfoRepository.countByGenderIsNull(),
                detailedInfoRepository.countByBirthdayAfter(dateFormat.parse("2010-01-01")),
                detailedInfoRepository.countByBirthdayBetween(dateFormat.parse("2000-01-01"), dateFormat.parse("2009-12-31")),
                detailedInfoRepository.countByBirthdayBetween(dateFormat.parse("1990-01-01"), dateFormat.parse("1999-12-31")),
                detailedInfoRepository.countByBirthdayBetween(dateFormat.parse("1980-01-01"), dateFormat.parse("1989-12-31")),
                detailedInfoRepository.countByBirthdayBefore(dateFormat.parse("1979-12-31")),
                detailedInfoRepository.countByBirthdayIsNull()
        );
    }

    @Override
    public ManagerBlogListVO showBlogList() {
        List<Blog> blogList = blogRepository.findAll();
        List<ManagerBlogListItemVO> managerBlogListItemVOList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for(Blog blog: blogList) {
            managerBlogListItemVOList.add(new ManagerBlogListItemVO(
                    blog.getId(), userInfoRepository.findUserInfoById(blog.getPublisherId()).getUsername(),
                    dateFormat.format(blog.getTime()), blog.getScope(), blog.getContent(),
                    blog.getLikeNum(), blog.getCollectNum(), blog.getForwardNum(),
                    blog.getState(), blog.getHeat(), Boolean.TRUE.equals(blog.getDeleted()) ? "已删除" : " "
            ));
        }
        return new ManagerBlogListVO(managerBlogListItemVOList);
    }

    @Override
    public StatusCode deleteBlog(Integer blogId) {
        Blog blog = blogRepository.findBlogByIdAndDeleted(blogId, false);
        blog.setDeleted(true);
        blogRepository.save(blog);
        return StatusCode.SUCCESS;
    }

    @Override
    public ManagerTopicListVO showTopicList() {
        List<Topic> topicList = topicRepository.findAll();
        List<ManagerTopicListItemVO> managerTopicListItemVOList = new ArrayList<>();
        for(Topic topic: topicList) {
            Integer topicBlogNum = blogTopicRepository.findBlogTopicsByTopicIdAndDeleted(topic.getId(), false).size();
            managerTopicListItemVOList.add(new ManagerTopicListItemVO(
                    topic.getId(), topic.getName(), topic.getHeat(), topicBlogNum
            ));
        }
        return new ManagerTopicListVO(managerTopicListItemVOList);
    }

    @Override
    public StatusCode deleteTopic(Integer topicId) {
        Topic topic = topicRepository.findTopicByIdAndDeleted(topicId, false);
        topic.setDeleted(true);
        topicRepository.save(topic);
        List<BlogTopic> blogTopicList = blogTopicRepository.findBlogTopicsByTopicIdAndDeleted(topicId, false);
        for(BlogTopic blogTopic: blogTopicList) {
            blogTopic.setDeleted(true);
            blogTopicRepository.save(blogTopic);
        }
        return StatusCode.SUCCESS;
    }

    @Override
    public ManagerTopicTrendVO showTopicTrend(Integer topicId) {
        List<Integer> heatList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();
        List<HeatTime> heatTimeList = heatTimeRepository.findByTopicId(topicId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
        for(HeatTime heatTime: heatTimeList) {
            timeList.add(dateFormat.format(heatTime.getTime()));
            heatList.add(heatTime.getHeat());
        }
        return new ManagerTopicTrendVO(timeList, heatList);
    }
}
