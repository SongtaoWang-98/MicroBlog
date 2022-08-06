package com.stewart.microblog.controller;

import com.stewart.microblog.service.PhotoService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 照片控制层类
 * @author 王松涛
 */
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Resource
    private PhotoService photoService;

    @GetMapping("/photoList")
    public ResultVO showPhotos() {
        return ResultVOUtil.success(photoService.showPhotos());
    }

    @PostMapping("/add")
    public ResultVO addPhoto(@RequestParam("picFile") MultipartFile file) {
        return ResultVOUtil.success(photoService.addPhoto(file));
    }
}
