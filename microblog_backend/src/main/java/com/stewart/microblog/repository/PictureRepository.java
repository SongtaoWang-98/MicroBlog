package com.stewart.microblog.repository;

import com.stewart.microblog.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 照片持久层接口
 * @author 王松涛
 */
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    /**
     * 通过照片id查询照片
     * @param id
     * Integer类型照片id
     * @param deleted
     * Boolean类型照片标记
     * @return 照片对象Picture
     */
    Picture findPictureByIdAndDeleted(Integer id, Boolean deleted);
    List<Picture> findAllByUserIdAndDeleted(Integer id, Boolean deleted);
}
