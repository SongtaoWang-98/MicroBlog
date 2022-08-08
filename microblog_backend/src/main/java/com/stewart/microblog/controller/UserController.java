package com.stewart.microblog.controller;

import com.stewart.microblog.aop.SystemControllerLog;
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

    /**
     * 查看用户主页接口
     * @param personId
     * Integer类型用户id
     * @return ResultVO
     */
    @GetMapping("/homePage")
    @SystemControllerLog(description = "查看用户主页")
    public ResultVO personalHomePage(Integer personId) {
        if(personId == null) {
            throw new BizException("-1", "用户id不能为空");
        }
        return ResultVOUtil.success(userService.showPersonalPage(personId));
    }

    /**
     * 展示用户收藏列表接口
     * @return ResultVO
     */
    @GetMapping("/collections")
    @SystemControllerLog(description = "展示用户收藏列表")
    public ResultVO showMyCollections() {
        return ResultVOUtil.success(userService.showMyCollections());
    }

    /**
     * 展示用户关注列表接口
     * @return ResultVO
     */
    @GetMapping("/followingList")
    @SystemControllerLog(description = "展示用户关注列表")
    public ResultVO followingList() {
        return ResultVOUtil.success(userService.showFollowingList());
    }

    /**
     * 展示用户粉丝列表接口
     * @return ResultVO
     */
    @GetMapping("/followerList")
    @SystemControllerLog(description = "展示用户粉丝列表")
    public ResultVO followerList() {
        return ResultVOUtil.success(userService.showFollowerList());
    }

    /**
     * 展示用户详细信息接口
     * @return ResultVO
     */
    @GetMapping("/detailedInfo")
    @SystemControllerLog(description = "展示用户详细信息")
    public ResultVO detailedInfo() {
        return ResultVOUtil.success(userService.showDetailedInfo());
    }

    /**
     * 修改用户详细信息接口
     * @param detailedInfoDTO
     * 用户详细信息表单对象
     * @return ResultVO
     */
    @PostMapping("/updateDetailedInfo")
    @SystemControllerLog(description = "修改用户详细信息")
    public ResultVO updateDetailedInfo(@RequestBody DetailedInfoDTO detailedInfoDTO) throws ParseException {
        StatusCode statusCode = userService.updateDetailedInfo(detailedInfoDTO);
        return ResultVOUtil.success(statusCode.getMsg());
    }

    /**
     * 关注好友功能接口
     * @param personId
     * Integer类型关注用户id
     * @return ResultVO
     */
    @PostMapping("/follow")
    @SystemControllerLog(description = "关注好友")
    public ResultVO followPerson(Integer personId) {
        if(personId == null) {
            throw new BizException("-1", "关注用户id不能为空");
        }
        return ResultVOUtil.success(userService.follow(personId));
    }

    /**
     * 取消关注好友功能接口
     * @param personId
     * Integer类型取消关注用户id
     * @return ResultVO
     */
    @PostMapping("/unfollow")
    @SystemControllerLog(description = "取消关注")
    public ResultVO unfollowPerson(Integer personId) {
        if(personId == null) {
            throw new BizException("-1", "取消关注用户id不能为空");
        }
        return ResultVOUtil.success(userService.unfollow(personId));
    }

    /**
     * 展示个人统计功能接口
     * @return ResultVO
     */
    @GetMapping("/personalStat")
    @SystemControllerLog(description = "展示个人统计")
    public ResultVO showPersonalStat() {
        return ResultVOUtil.success(userService.showUserStatInfo());
    }
}
