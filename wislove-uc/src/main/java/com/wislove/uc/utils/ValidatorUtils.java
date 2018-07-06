package com.wislove.uc.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;

import com.wislove.exception.MyException;

/**
 * 验证工具类
 * @author: 廖双龙
 * @date: 2018年6月26日下午5:00:40
 */
public class ValidatorUtils {
	
	private static Validator validator = Validation.byProvider(HibernateValidator.class).configure()
			.failFast(true).buildValidatorFactory().getValidator();
	
	
	/**
	 * 验证参数
	 * @param obj
	 * @throws MyException 
	 * @return void
	 * @author 廖双龙
	 * @date 2018年6月28日 下午2:35:44
	 * @version v1.0.0
	 */
	public static <T> void validate(T obj) throws MyException {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
		// 抛出检验异常
		if (constraintViolations.size() > 0) {
			throw new MyException(constraintViolations.iterator().next().getMessage());
		}
	}
}