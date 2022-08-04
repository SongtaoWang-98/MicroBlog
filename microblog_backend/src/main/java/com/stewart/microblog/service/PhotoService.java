package com.stewart.microblog.service;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.PhotosVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 照片功能接口
 * @author 王松涛
 */
public interface PhotoService {
    PhotosVO showPhotos();
    StatusCode addPhoto(MultipartFile file);
    StatusCode removePhoto(Integer photoId);
}
