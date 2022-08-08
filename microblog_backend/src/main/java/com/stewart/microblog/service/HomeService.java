package com.stewart.microblog.service;

import com.stewart.microblog.vo.HomeVO;

/**
 * HomeService类提供了若干按条件展示博文主页的方法，其中包括
 * 展示热门博文、展示最新博文、展示好友圈博文和按分组展示博文
 * @author 王松涛
 */
public interface HomeService {

    /**
     * 展示热门博文界面
     * @return 热门博文视图对象
     */
    HomeVO showHotBlogsHome();

    /**
     * 展示最新博文界面
     * @return 最新博文视图对象
     */
    HomeVO showNewBlogsHome();

    /**
     * 展示好友圈博文界面
     * @return 好友圈博文视图对象
     */
    HomeVO showFriendsBlogsHome();

    /**
     * 按选择分组展示博文
     * @param groupId
     * 输入分组id
     * @return 对应id分组博文视图对象
     */
    HomeVO showGroupBlogsHome(Integer groupId);
}
