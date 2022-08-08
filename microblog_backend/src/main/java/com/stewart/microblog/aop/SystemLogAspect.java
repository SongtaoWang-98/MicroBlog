package com.stewart.microblog.aop;

import com.stewart.microblog.entity.SysAction;
import com.stewart.microblog.repository.SysActionRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 切点类实现
 * @author 王松涛
 */
@Aspect
@Component
@SuppressWarnings("all")
public class SystemLogAspect {
    @Resource
    private SysActionRepository sysActionRepository;

    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    @Pointcut("@annotation(com.stewart.microblog.aop.SystemControllerLog)")
    public void controllerAspect(){
    }

    /**
     * @Description  前置通知  用于拦截Controller层记录用户的操作
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        try {
            //控制台输出
            logger.info("请求方法：{}", request.getMethod());
            logger.info("请求协议：{}", request.getProtocol());
            logger.info("请求URL：{}", request.getRequestURL());
            logger.info("请求参数：{}", request.getQueryString());
            logger.info("请求源ip：{}", request.getRemoteAddr());
            logger.info("请求本地端口：{}", request.getLocalPort());
            logger.info("SESSIONID:{}", session.getId());
            logger.info("请求人：{}", session.getAttribute("user"));
            logger.info("请求调用Controller接口：" +
                    (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            logger.info("功能描述：{}", getControllerMethodDescription(joinPoint));

            //数据库日志
            sysActionRepository.save(new SysAction(
                    null, request.getRemoteAddr(), request.getRequestURL().toString(), request.getMethod(),
                    request.getQueryString(), request.getLocalPort(), session.getId(),
                    session.getAttribute("user").toString(), getControllerMethodDescription(joinPoint),
                    "CONTROLLER", new Date()
            ));

        }catch (Exception e){
            //记录本地异常日志
            logger.error("异常信息：{}",e.getMessage());
        }
    }

    /**
     * @Description  获取注解中对方法的描述信息 用于Controller层注解
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
}