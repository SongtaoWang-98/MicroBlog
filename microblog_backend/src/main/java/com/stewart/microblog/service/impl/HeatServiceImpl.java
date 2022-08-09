package com.stewart.microblog.service.impl;

import com.stewart.microblog.entity.Blog;
import com.stewart.microblog.entity.BlogTopic;
import com.stewart.microblog.entity.HeatTime;
import com.stewart.microblog.entity.Topic;
import com.stewart.microblog.repository.BlogRepository;
import com.stewart.microblog.repository.BlogTopicRepository;
import com.stewart.microblog.repository.HeatTimeRepository;
import com.stewart.microblog.repository.TopicRepository;
import com.stewart.microblog.service.HeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 热度功能接口实现类
 * @author 王松涛
 */
@Service
public class HeatServiceImpl implements HeatService {
    @Resource
    private BlogRepository blogRepository;
    @Resource
    private TopicRepository topicRepository;
    @Resource
    private BlogTopicRepository blogTopicRepository;
    @Resource
    private HeatTimeRepository heatTimeRepository;

    /**
     * 日志logger对象
     */
    private static final Logger logger = LoggerFactory.getLogger(HeatServiceImpl.class);

    /**
     * 注解设置定时任务，每5s执行一次
     */
    @Scheduled(cron = "*/5 * * * * *")
    @Override
    public void refreshTopicHeat(){
        //遍历话题列表，一一刷新热度
        logger.info("刷新话题热度");
        List<Topic> topicList = topicRepository.findAllByDeletedOrderByHeatDesc(false);
        for(Topic topic: topicList) {
            int topicHeat = 0;
            List<BlogTopic> blogTopicList = blogTopicRepository.findBlogTopicsByTopicIdAndDeleted(topic.getId(), false);
            for(BlogTopic blogTopic: blogTopicList) {
                Blog blog = blogRepository.findBlogByIdAndDeleted(blogTopic.getBlogId(), false);
                if(blog != null) {
                    topicHeat += blog.getHeat();
                }
            }
            topic.setHeat(topicHeat);
            topicRepository.save(topic);
        }
    }

    /**
     * 注解设置定时任务，每20s执行一次
     */
    @Scheduled(cron = "*/20 * * * * *")
    @Override
    public void recordTopicHeat() {
        //保存话题热度-时间
        logger.info("记录话题热度");
        List<Topic> topicList = topicRepository.findAllByDeletedOrderByHeatDesc(false);
        for(Topic topic: topicList) {
            heatTimeRepository.save(new HeatTime(null, topic.getId(), topic.getHeat(), new Date()));
        }
    }
}
