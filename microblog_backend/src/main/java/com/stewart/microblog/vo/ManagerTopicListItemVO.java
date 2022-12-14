package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员话题统计视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerTopicListItemVO {
    @JsonProperty("topicId")
    private Integer topicId;
    @JsonProperty("topicName")
    private String topicName;
    @JsonProperty("heat")
    private Integer heat;
    @JsonProperty("blogNum")
    private Integer blogNum;
}
