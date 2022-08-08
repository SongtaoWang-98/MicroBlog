package com.stewart.microblog.aop;

import java.lang.annotation.*;

/**
 * 注解声明
 * @author 王松涛
 */

//作用在参数和方法上
@Target({ElementType.PARAMETER, ElementType.METHOD})
//运行时注解
@Retention(RetentionPolicy.RUNTIME)
//表明这个注解应该被 javadoc工具记录
@Documented
public @interface SystemControllerLog {
    String description() default "";
}
