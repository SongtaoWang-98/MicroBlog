package com.stewart.microblog.service.impl;

import com.stewart.microblog.entity.ConcernGroup;
import com.stewart.microblog.entity.DetailedInfo;
import com.stewart.microblog.entity.UserInfo;
import com.stewart.microblog.enums.RegisterCode;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.repository.ConcernGroupRepository;
import com.stewart.microblog.repository.DetailedInfoRepository;
import com.stewart.microblog.repository.UserInfoRepository;
import com.stewart.microblog.service.SignUpService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Resource
    private ConcernGroupRepository concernGroupRepository;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisterCode signUp(String username, String nickname, String password) {
        if(userInfoRepository.findByUsername(username) != null) {
            return RegisterCode.USERNAME_EXISTS;
        }
        else {
            userInfoRepository.save(new UserInfo(
                    null, username, nickname, passwordEncoder.encode(password), "USER",
                    "NORMAL", 2, new Date()
            ));
            Integer newUserId = userInfoRepository.findFirstByOrderByIdDesc().getId();
            detailedInfoRepository.save(new DetailedInfo(newUserId, null,
                    null, new Date(), null, null, null, null, null, null));
            concernGroupRepository.save(new ConcernGroup(null, newUserId, "默认分组", false));
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
