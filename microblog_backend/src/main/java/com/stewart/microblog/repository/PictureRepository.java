package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
    Picture findPictureById(Integer id);
}
