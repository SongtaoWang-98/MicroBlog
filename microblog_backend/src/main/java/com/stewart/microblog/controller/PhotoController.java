package com.stewart.microblog.controller;

import com.stewart.microblog.aop.SystemControllerLog;
import com.stewart.microblog.exception.BizException;
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

    /**
     * 查看用户照片列表接口
     * @return ResultVO
     */
    @GetMapping("/photoList")
    @SystemControllerLog(description = "查看用户照片列表")
    public ResultVO showPhotos() {
        return ResultVOUtil.success(photoService.showPhotos());
    }

    /**
     * 上传照片接口
     * @param file
     * 选取照片文件
     * @return ResultVO
     */
    @PostMapping("/add")
    @SystemControllerLog(description = "添加照片")
    public ResultVO addPhoto(@RequestParam("picFile") MultipartFile file) {
        if(file == null) {
            throw new BizException("-1", "图片不能为空");
        }
        return ResultVOUtil.success(photoService.addPhoto(file));
    }
}
