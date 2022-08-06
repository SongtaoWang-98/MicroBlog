package com.stewart.microblog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerBlogListItemVO {
    private Integer blogId;
    private String publisherName;
    private String time;
    private String scope;
    private String content;
    private Integer likeNum;
    private Integer collectNum;
    private Integer forwardNum;
    private String state;
    private Integer heat;
    private Boolean deleted;
}
