package com.stewart.microblog.service;

import com.stewart.microblog.vo.CollectionPageVO;
import com.stewart.microblog.vo.SearchResultVO;
import com.stewart.microblog.vo.UserListVO;

/**
 * 搜索功能接口
 * @author 王松涛
 */
public interface SearchService {
    /**
     * 根据输入字符串搜索话题
     * @param str
     * 输入字符串
     * @return 搜索结果视图对象
     */
    SearchResultVO searchByTopic(String str);

    /**
     * 根据输入字符串搜索内容
     * @param str
     * 输入字符串
     * @return 搜索结果视图对象
     */
    SearchResultVO searchByContent(String str);

    /**
     * 根据输入字符串搜索用户
     * @param str
     * 输入字符串
     * @return 用户列表视图对象
     */
    UserListVO searchByUsername(String str);

    /**
     * 根据输入字符串搜索具体话题
     * @param str
     * 输入字符串
     * @return 搜索结果视图对象
     */
    SearchResultVO searchBySpecificTopic(String str);
}
