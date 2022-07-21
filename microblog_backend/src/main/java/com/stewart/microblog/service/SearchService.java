package com.stewart.microblog.service;

import com.stewart.microblog.vo.SearchResultVO;
import com.stewart.microblog.vo.UserListVO;

public interface SearchService {
    SearchResultVO searchTopic(String str);
    SearchResultVO searchContent(String str);
    UserListVO searchUser(String str);
}
