package com.wislove.annotation.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wislove.annotation.validation.MyContraint;

/**
 * 自定义的验证类
 * @author: 廖双龙
 * @date: 2018年6月21日下午6:20:45
 */
public class MyConstrainValidator implements ConstraintValidator<MyContraint, String> {
	
	
	@Override
	public void initialize(MyContraint myContraint) {
		System.out.println(">>>>初始化验证器");
	}
	
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return false;
	}

}
