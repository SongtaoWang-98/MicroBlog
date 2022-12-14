package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 管理员话题热度趋势视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerTopicTrendVO {
    @JsonProperty("timeList")
    List<String> timeList;
    @JsonProperty("heatList")
    List<Integer> heatList;
}
