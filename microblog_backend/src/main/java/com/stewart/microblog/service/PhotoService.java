package com.stewart.microblog.service;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.PhotographsVO;
import com.stewart.microblog.vo.PhotosVO;

/**
 * 照片功能接口
 * @author 王松涛
 */
public interface PhotoService {
    PhotographsVO showPhotographs();
    StatusCode createPhotograph(String photographName);
    StatusCode modifyPhotograph(Integer photographId, String newPhotographName);
    StatusCode deletePhotograph(Integer photographId);
    PhotosVO showPhotos(Integer photographId);
    StatusCode addPhoto(Integer photographId, String photoUrl);
    StatusCode removePhoto(Integer photoId);
}
