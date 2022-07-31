package com.stewart.microblog.service;

import com.stewart.microblog.vo.CollectionPageVO;
import com.stewart.microblog.vo.SearchResultVO;
import com.stewart.microblog.vo.UserListVO;

/**
 * 搜索功能接口
 * @author 王松涛
 */
public interface SearchService {
    SearchResultVO searchByTopic(String str);
    SearchResultVO searchByContent(String str);
    UserListVO searchByUsername(String str);
}
