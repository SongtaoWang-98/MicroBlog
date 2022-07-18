package com.stewart.microblog.service.impl;

import com.stewart.microblog.repository.UserRepository;
import com.stewart.microblog.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String testFindNickName() {
        String userName = "songtao_wang@qq.com";
        return userRepository.findByUsername(userName).getNickname();
    }
}
