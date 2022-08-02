package com.wengzw.blood.common.validator.annotations;

import java.lang.annotation.*;

/**
 * 自定义操作日志的注解
 *
 * @author wengzw
 * @date 2022/8/2 10:42
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    /**
     * 操作描述
     *
     * @return 操作描述
     */
    String operationDescription() default "";

    /**
     * 操作类型
     *
     * @return 操作类型
     */
    String operationType() default "";
}

