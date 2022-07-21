package com.stewart.microblog.service;

import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.vo.PhotographsVO;
import com.stewart.microblog.vo.PhotosVO;

/**
 * @author stewartwang
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
