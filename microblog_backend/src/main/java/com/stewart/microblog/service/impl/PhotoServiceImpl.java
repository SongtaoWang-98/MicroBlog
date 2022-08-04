package com.stewart.microblog.service.impl;

import com.stewart.microblog.entity.Picture;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.repository.PictureRepository;
import com.stewart.microblog.repository.UserInfoRepository;
import com.stewart.microblog.service.PhotoService;
import com.stewart.microblog.util.GetCurrentUserUtil;
import com.stewart.microblog.vo.PhotoVO;
import com.stewart.microblog.vo.PhotosVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 照片功能接口实现类
 * @author 王松涛
 */
@Service
public class PhotoServiceImpl implements PhotoService {
    @Resource
    private UserInfoRepository userInfoRepository;

    @Resource
    private PictureRepository pictureRepository;

    private static String BASE_PATH =  "../microblog_frontend/static/";

    @Override
    public PhotosVO showPhotos() {
        //获取当前用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();


        List<Picture> pictureList = pictureRepository.findAllByUserIdAndDeleted(userId, false);
        List<PhotoVO> photoVOList = new ArrayList<>();
        for(Picture picture: pictureList) {
            photoVOList.add(new PhotoVO(picture.getId(), picture.getUrl()));
        }
        return new PhotosVO(photoVOList);
    }

    @Override
    public StatusCode addPhoto(MultipartFile file) {
        //获取当前用户
        String userName = GetCurrentUserUtil.getCurrentUserName();
        Integer userId = userInfoRepository.findByUsername(userName).getId();

        int userPicNum = pictureRepository.findAllByUserIdAndDeleted(userId, false).size() +
                pictureRepository.findAllByUserIdAndDeleted(userId, true).size() + 1;
        String path =  userName + '/' + userPicNum + ".jpg";

        String fileName = BASE_PATH + path;
        if (!file.isEmpty()) {
            try {
                File realFile = new File(fileName);
                file.transferTo(realFile);
                pictureRepository.save(new Picture(null, userId, path, false));

            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                return null;
            }
            return StatusCode.SUCCESS;
        } else {
            return StatusCode.FAIL;
        }
    }

    @Override
    public StatusCode removePhoto(Integer photoId) {
        return null;
    }
}
