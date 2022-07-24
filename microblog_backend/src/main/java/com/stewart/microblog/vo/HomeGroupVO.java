package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 首页分组视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeGroupVO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String groupName;
}
