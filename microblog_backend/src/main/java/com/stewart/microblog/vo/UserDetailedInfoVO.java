package com.stewart.microblog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户详细信息视图类
 * @author 王松涛
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailedInfoVO {
    @JsonProperty("realName")
    private String realName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("company")
    private String company;
    @JsonProperty("job")
    private String job;
    @JsonProperty("university")
    private String university;
    @JsonProperty("telNumber")
    private String telNumber;
    @JsonProperty("hobby")
    private String hobby;
    @JsonProperty("province")
    private String province;
}
