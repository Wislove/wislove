package com.wislove.annotation.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.wislove.annotation.validation.validator.MyConstrainValidator;

/**
 * 限制相关的注解服务
 * @author: 廖双龙
 * @date: 2018年6月21日下午6:16:20
 */
@Documented
@Retention(RUNTIME)
@Inherited
@Target({ FIELD, METHOD })
@Constraint(validatedBy = { MyConstrainValidator.class})
public @interface MyContraint {
	
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
