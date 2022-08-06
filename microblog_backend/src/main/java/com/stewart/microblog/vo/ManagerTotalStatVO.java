package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员总体统计视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerTotalStatVO {
    @JsonProperty("userNum")
    private Long totalUserNum;
    @JsonProperty("blogNum")
    private Long totalBlogNum;
    @JsonProperty("topicNum")
    private Long totalTopicNum;
    @JsonProperty("maleNum")
    private Integer totalMaleNum;
    @JsonProperty("femaleNum")
    private Integer totalFemaleNum;
    @JsonProperty("unknownGenderNum")
    private Integer unknownGenderNum;
    @JsonProperty("a10sNum")
    private Integer after10s;
    @JsonProperty("g00sNum")
    private Integer generation00s;
    @JsonProperty("g90sNum")
    private Integer generation90s;
    @JsonProperty("g80sNum")
    private Integer generation80s;
    @JsonProperty("b70sNum")
    private Integer before70s;
    @JsonProperty("unknownBirthdayNum")
    private Integer unknownBirthdayNum;
}
