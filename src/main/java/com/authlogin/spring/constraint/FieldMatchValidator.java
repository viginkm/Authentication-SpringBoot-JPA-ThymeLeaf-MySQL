package com.authlogin.spring.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;


public class FieldMatchValidator implements  ConstraintValidator<FieldMatch,Object>{

	
	private String  firstFieldName;
	private String secondFieldName;
	
	@Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

	
	@Override
	public boolean isValid (final Object value,final ConstraintValidatorContext context)
	{
		try
		{
		
		final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
				final Object SecondObj = BeanUtils.getProperty(value, secondFieldName);
				
				return firstObj== null && SecondObj == null || firstObj != null && firstObj.equals(SecondObj);
				
		
		}catch(final Exception ignore) {}
		return true;
		
	}
	
}
