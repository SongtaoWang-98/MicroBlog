package com.stewart.microblog.controller;

import com.stewart.microblog.aop.SystemControllerLog;
import com.stewart.microblog.exception.BizException;
import com.stewart.microblog.service.SearchService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 搜索功能控制层类
 * @author 王松涛
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    @Resource
    private SearchService searchService;
    @GetMapping("/byContent")
    @SystemControllerLog(description = "查询博文正文")
    public ResultVO searchByContent(String str) {
        if(str.length() == 0) {
            throw new BizException("-1", "搜索正文内容不能为空");
        }
        return ResultVOUtil.success(searchService.searchByContent(str));
    }
    @GetMapping("/byTopic")
    @SystemControllerLog(description = "查询话题")
    public ResultVO searchByTopic(String str) {
        if(str.length() == 0) {
            throw new BizException("-1", "搜索话题内容不能为空");
        }
        return ResultVOUtil.success(searchService.searchByTopic(str));
    }
    @GetMapping("/byUser")
    @SystemControllerLog(description = "查询用户")
    public ResultVO searchByUsername(String str) {
        if(str.length() == 0) {
            throw new BizException("-1", "搜索用户内容不能为空");
        }
        return ResultVOUtil.success(searchService.searchByUsername(str));
    }
    @GetMapping("/bySpecificTopic")
    @SystemControllerLog(description = "查询详细话题")
    public ResultVO searchBySpecificTopic(String str) {
        if(str.length() == 0) {
            throw new BizException("-1", "搜索话题内容不能为空");
        }
        return ResultVOUtil.success(searchService.searchBySpecificTopic(str));
    }
}
