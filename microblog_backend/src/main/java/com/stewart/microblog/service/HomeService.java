package com.stewart.microblog.service;

import com.stewart.microblog.vo.HomeVO;

/**
 * @author Administrator
 */
public interface HomeService {
    /**
     *
     * @return
     */
    String testFindNickName();


    /**
     *
     * @return
     */
    HomeVO showHotBlogsHome();

    HomeVO showNewBlogsHome();

    HomeVO showFriendsBlogsHome();

    HomeVO showGroupBlogsHome(Integer groupId);
}
