package com.stewart.microblog.controller;

import com.stewart.microblog.dto.DetailedInfoDTO;
import com.stewart.microblog.enums.StatusCode;
import com.stewart.microblog.exception.BizException;
import com.stewart.microblog.service.UserService;
import com.stewart.microblog.util.ResultVOUtil;
import com.stewart.microblog.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * 用户功能控制层类
 * @author 王松涛
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/homePage")
    public ResultVO personalHomePage(Integer personId) {
        if(personId == null) {
            throw new BizException("-1", "用户id不能为空");
        }
        return ResultVOUtil.success(userService.showPersonalPage(personId));
    }
    @GetMapping("/collections")
    public ResultVO showMyCollections() {
        return ResultVOUtil.success(userService.showMyCollections());
    }
    @GetMapping("/followingList")
    public ResultVO followingList() {
        return ResultVOUtil.success(userService.showFollowingList());
    }
    @GetMapping("/followerList")
    public ResultVO followerList() {
        return ResultVOUtil.success(userService.showFollowerList());
    }
    @GetMapping("/detailedInfo")
    public ResultVO detailedInfo() {
        return ResultVOUtil.success(userService.showDetailedInfo());
    }
    @PostMapping("/updateDetailedInfo")
    public ResultVO updateDetailedInfo(@RequestBody DetailedInfoDTO detailedInfoDTO) throws ParseException {
        StatusCode statusCode = userService.updateDetailedInfo(detailedInfoDTO);
        return ResultVOUtil.success(statusCode.getMsg());
    }
    @PostMapping("/follow")
    public ResultVO followPerson(Integer personId) {
        if(personId == null) {
            throw new BizException("-1", "关注用户id不能为空");
        }
        return ResultVOUtil.success(userService.follow(personId));
    }
    @PostMapping("/unfollow")
    public ResultVO unfollowPerson(Integer personId) {
        if(personId == null) {
            throw new BizException("-1", "取消关注用户id不能为空");
        }
        return ResultVOUtil.success(userService.unfollow(personId));
    }
    @GetMapping("/personalStat")
    public ResultVO showPersonalStat() {
        return ResultVOUtil.success(userService.showUserStatInfo());
    }
}
