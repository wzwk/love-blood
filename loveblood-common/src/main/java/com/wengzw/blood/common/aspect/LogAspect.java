package com.wengzw.blood.common.aspect;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wengzw.blood.common.dao.mybaits.SysLogMapper;
import com.wengzw.blood.common.entity.SysLog;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.common.validator.annotations.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 操作日志记录
 *
 * @author wengzw
 * @date 2022/8/2 10:44
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    private static final String AUTH_LOGIN = "/auth/login";
    private static final String EXPORT = "export";
    private final SysLogMapper sysLogMapper;

    private static final int SUCCESS = 200;
    private static final int FAILED = -1;
    public static final String CODE = "code";

    public LogAspect(SysLogMapper sysLogMapper) {
        this.sysLogMapper = sysLogMapper;
    }

    /**
     * 定义切点 表示使用了注解@OperationLog 就使用上切面，打印日志
     */
    @Pointcut("@annotation(com.wengzw.blood.common.validator.annotations.OperationLog)")
    public void logPoint() {
    }

    /**
     * 在切点前后切入内容 记录请求
     *
     * @param joinPoint 连接方法
     * @return 执行结果
     */
    @Around("logPoint()")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        log.info("request params = [{}]", Arrays.toString(args));

        SysLog sysLog = new SysLog();
        // 对带注解的方法进行解析
        parseLogAnnotation(joinPoint, sysLog);

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        sysLog.setClientIp(request.getRemoteAddr());
        //如果是登录接口，由于用户尚未登录，所以从请求参数中获取username，其他接口则从token缓存中获取username
        // todo 接口优化 获取token 并取得相关信息
        sysLog.setUserId(request.getRequestURI().equals(AUTH_LOGIN) ? "admin" : "");

        // 执行接口方法
        Object proceed = joinPoint.proceed();
        if (proceed != null) {
            sysLog.setOperationResult(isProcessingSuccessful(proceed));
        } else {
            sysLog.setOperationResult(request.getRequestURI().contains(EXPORT) ? SUCCESS : FAILED);
        }

        log.info("record log = [{}]", sysLog);
//        sysLogMapper.insert(sysLog);
        return proceed;
    }


    /**
     * 处理结果是否成功
     *
     * @param proceed 处理结果
     * @return 0-失败，1-成功
     */
    private int isProcessingSuccessful(Object proceed) {
        JSONObject result = JSONUtil.parseObj(proceed);
        return result.getInt(CODE).equals(RespStatusEnum.SUCCESS.getCode()) ? SUCCESS : FAILED;
    }



    /**
     * 获取自定义注解传递参数的值
     *
     * @param joinPoint 连接的方法
     * @param sysLog  操作日志数据对象
     */
    private void parseLogAnnotation(JoinPoint joinPoint, SysLog sysLog) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperationLog logAnnotation = method.getAnnotation(OperationLog.class);

        if (logAnnotation != null) {
            sysLog.setOperationType(logAnnotation.operationType());
            sysLog.setOperationDescription(logAnnotation.operationDescription());
        }
    }






















}
