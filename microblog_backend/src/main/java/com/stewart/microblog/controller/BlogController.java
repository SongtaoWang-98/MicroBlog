package com.stewart.microblog.controller;

import com.stewart.microblog.dto.NewBlogDTO;
import com.stewart.microblog.service.BlogService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 博客功能控制层类
 * @author 王松涛
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogService blogService;
    @PostMapping ("/like")
    public ResultVO likeBlog(Integer blogId) {
        return ResultVOUtil.success(blogService.likeBlog(blogId));
    }
    @PostMapping ("/dislike")
    public ResultVO dislikeBlog(Integer blogId) {
        return ResultVOUtil.success(blogService.dislikeBlog(blogId));
    }
    @PostMapping("/collect")
    public ResultVO collectBlog(Integer blogId) {
        return ResultVOUtil.success(blogService.collectBlog(blogId));
    }
    @PostMapping("/disCollect")
    public ResultVO disCollectBlog(Integer blogId) {
        return ResultVOUtil.success(blogService.disCollectBlog(blogId));
    }
    @PostMapping("/comment")
    public ResultVO commentBlog(Integer blogId, String content) {
        return ResultVOUtil.success(blogService.commentBlog(blogId, false, null,  content));
    }
    @PostMapping("/publish")
    public ResultVO publishNewBlog(@RequestBody NewBlogDTO newBlogDTO) {
        return ResultVOUtil.success(blogService.publishBlog(newBlogDTO));
    }
}
