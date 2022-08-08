package com.stewart.microblog.service;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.PhotosVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 照片功能接口
 * @author 王松涛
 */
public interface PhotoService {

    /**
     * 展示用户相册
     * @return 相册视图对象
     */
    PhotosVO showPhotos();

    /**
     * 添加照片
     * @param file
     * 照片文件对象
     * @return StatusCode
     */
    StatusCode addPhoto(MultipartFile file);
    StatusCode removePhoto(Integer photoId);
}
