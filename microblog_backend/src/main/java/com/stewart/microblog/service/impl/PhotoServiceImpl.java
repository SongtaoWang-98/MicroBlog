package com.stewart.microblog.service.impl;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.service.PhotoService;
import com.stewart.microblog.vo.PhotographsVO;
import com.stewart.microblog.vo.PhotosVO;

/**
 * 照片功能接口实现类
 * @author 王松涛
 */
public class PhotoServiceImpl implements PhotoService {
    @Override
    public PhotographsVO showPhotographs() {
        return null;
    }

    @Override
    public StatusCode createPhotograph(String photographName) {
        return null;
    }

    @Override
    public StatusCode modifyPhotograph(Integer photographId, String newPhotographName) {
        return null;
    }

    @Override
    public StatusCode deletePhotograph(Integer photographId) {
        return null;
    }

    @Override
    public PhotosVO showPhotos(Integer photographId) {
        return null;
    }

    @Override
    public StatusCode addPhoto(Integer photographId, String photoUrl) {
        return null;
    }

    @Override
    public StatusCode removePhoto(Integer photoId) {
        return null;
    }
}
