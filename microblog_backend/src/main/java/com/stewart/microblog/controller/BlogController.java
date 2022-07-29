package com.stewart.microblog.controller;

import com.stewart.microblog.aop.SystemControllerLog;
import com.stewart.microblog.dto.NewBlogDTO;
import com.stewart.microblog.exception.BizException;
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
    @SystemControllerLog(description = "点赞博文")
    public ResultVO likeBlog(Integer blogId) {
        if(blogId == null) {
            throw new BizException("-1", "点赞博客id不能为空");
        }
        return ResultVOUtil.success(blogService.likeBlog(blogId));
    }
    @PostMapping ("/dislike")
    @SystemControllerLog(description = "取消点赞博文")
    public ResultVO dislikeBlog(Integer blogId) {
        if(blogId == null) {
            throw new BizException("-1", "取消点赞博客id不能为空");
        }
        return ResultVOUtil.success(blogService.dislikeBlog(blogId));
    }
    @PostMapping("/collect")
    @SystemControllerLog(description = "收藏博文")
    public ResultVO collectBlog(Integer blogId) {
        if(blogId == null) {
            throw new BizException("-1", "收藏博客id不能为空");
        }
        return ResultVOUtil.success(blogService.collectBlog(blogId));
    }
    @PostMapping("/disCollect")
    @SystemControllerLog(description = "取消收藏博文")
    public ResultVO disCollectBlog(Integer blogId) {
        if(blogId == null) {
            throw new BizException("-1", "取消收藏博客id不能为空");
        }
        return ResultVOUtil.success(blogService.disCollectBlog(blogId));
    }
    @PostMapping("/comment")
    @SystemControllerLog(description = "评论博文")
    public ResultVO commentBlog(Integer blogId, String content) {
        if(blogId == 0) {
            throw new BizException("-1", "评论博客id不能为空");
        }
        if(content.length() == 0) {
            throw new BizException("-1", "评论内容不能为空");
        }
        return ResultVOUtil.success(blogService.commentBlog(blogId, false, null,  content));
    }
    @PostMapping("/publish")
    @SystemControllerLog(description = "发布博文")
    public ResultVO publishNewBlog(@RequestBody NewBlogDTO newBlogDTO) {
        if(newBlogDTO.getContent().length() == 0) {
            throw new BizException("-1", "博客内容不能为空");
        }
        return ResultVOUtil.success(blogService.publishBlog(newBlogDTO));
    }
}
