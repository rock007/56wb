package com.fp.gan.core.annotation;

import java.lang.annotation.*;

/**
 * 初始化继承BaseService的service
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyBatisService {
}
