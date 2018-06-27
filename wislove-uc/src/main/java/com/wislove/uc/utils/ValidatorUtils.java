package com.wislove.uc.utils;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;

/**
 * 验证工具类
 * @author: 廖双龙
 * @date: 2018年6月26日下午5:00:40
 */
public class ValidatorUtils {
	
	
	public Validator validator() {
		ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        
        return validator;
	}
	
	
}
