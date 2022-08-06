package com.stewart.microblog.service.impl;

import com.stewart.microblog.entity.DetailedInfo;
import com.stewart.microblog.entity.UserInfo;
import com.stewart.microblog.enums.RegisterCode;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.repository.DetailedInfoRepository;
import com.stewart.microblog.repository.UserInfoRepository;
import com.stewart.microblog.service.SignUpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;

/**
 * @author Administrator
 */
@Service
public class SignUpServiceImpl implements SignUpService {

    @Resource
    private UserInfoRepository userInfoRepository;
    @Resource
    private DetailedInfoRepository detailedInfoRepository;

    @Override
    public RegisterCode signUp(String username, String nickname, String password) {
        if(userInfoRepository.findByUsername(username) != null) {
            return RegisterCode.USERNAME_EXISTS;
        }
        else {
            userInfoRepository.save(new UserInfo(
                    null, username, nickname, password, "USER",
                    "NORMAL", 5, new Date()
            ));
            detailedInfoRepository.save(new DetailedInfo(userInfoRepository.findFirstByOrderById().getId(), null,
                    null, null, null, null, null, null, null, null));
            String filePath1 = "../microblog_frontend/static/" + username;
            //生成相册文件夹
            File file1=new File(filePath1);
            if(!file1.exists()){
                file1.mkdir();
            }
            return RegisterCode.SUCCESS;
        }
    }
}
