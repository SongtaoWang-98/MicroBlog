package com.stewart.microblog.controller;

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
    public ResultVO searchByContent(String str) {
        if(str.length() == 0) {
            throw new BizException("-1", "搜索内容不能为空");
        }
        return ResultVOUtil.success(searchService.searchByContent(str));
    }
    @GetMapping("/byTopic")
    public ResultVO searchByTopic(String str) {
        if(str.length() == 0) {
            throw new BizException("-1", "搜索内容不能为空");
        }
        return ResultVOUtil.success(searchService.searchByTopic(str));
    }
    @GetMapping("/byUser")
    public ResultVO searchByUsername(String str) {
        if(str.length() == 0) {
            throw new BizException("-1", "搜索内容不能为空");
        }
        return ResultVOUtil.success(searchService.searchByUsername(str));
    }
}
