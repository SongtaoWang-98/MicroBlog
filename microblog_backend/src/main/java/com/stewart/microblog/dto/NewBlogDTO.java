package com.stewart.microblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 发布新博客表单类
 *
 * @author 王松涛
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBlogDTO {
    private String scope;
    private Integer picId;
    private String content;
}