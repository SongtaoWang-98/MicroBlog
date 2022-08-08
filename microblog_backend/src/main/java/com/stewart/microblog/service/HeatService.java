package com.stewart.microblog.service;

/**
 * 热度功能接口
 * @author 王松涛
 */
public interface HeatService {

    /**
     * 刷新话题热度
     */
    void refreshTopicHeat();

    /**
     * 记录话题时间热度
     */
    void recordTopicHeat();
}
