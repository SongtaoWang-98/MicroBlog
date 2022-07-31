package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 查询结果视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultVO {
    @JsonProperty("blogs")
    private List<BlogVO> blogVOList;
}
