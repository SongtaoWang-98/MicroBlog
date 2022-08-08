package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 管理员博客列表视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerBlogListVO {
    @JsonProperty("blogList")
    private List<ManagerBlogListItemVO> managerBlogListItemVOList;
}
