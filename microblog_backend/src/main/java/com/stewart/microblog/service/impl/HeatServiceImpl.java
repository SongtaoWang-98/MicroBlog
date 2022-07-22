package com.stewart.microblog.service.impl;

import com.stewart.microblog.repository.BlogRepository;
import com.stewart.microblog.repository.HeatTimeRepository;
import com.stewart.microblog.repository.TopicRepository;
import com.stewart.microblog.service.HeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    private HeatTimeRepository heatTimeRepository;

    @Override
    public void refreshHeat(){

    }

    @Override
    public void recordTopicHeat(){

    }

}
