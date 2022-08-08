package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 管理员话题列表视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerTopicListVO {
    @JsonProperty("topicList")
    private List<ManagerTopicListItemVO> managerTopicListItemVOList;
}
