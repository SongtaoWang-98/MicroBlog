package com.stewart.microblog.util;

import com.stewart.microblog.vo.ResultVO;

/**
 * 向前端返回信息模板工具类
 * @author 王松涛
 */
public class ResultVOUtil {

    /**
     * 成功信息模板
     * @param data
     * 数据对象
     * @return ResultVO
     */
    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 失败信息模板
     * @param data
     * 数据对象
     * @return ResultVO
     */
    public static ResultVO fail(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("fail");
        resultVO.setData(data);
        return resultVO;
    }
}
