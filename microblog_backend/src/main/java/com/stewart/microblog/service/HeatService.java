package com.stewart.microblog.service;

/**
 * 热度功能接口
 * @author 王松涛
 */
public interface HeatService {
    void refreshHeat();
    void recordTopicHeat();
}
