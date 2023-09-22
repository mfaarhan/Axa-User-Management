package com.UserManagement.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompareValidator implements ConstraintValidator<Compare, Object>{

    private String firstField;

    private String secondField;

    @Override
    public void initialize(Compare constraintAnnotation) {
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        String firsValue = new BeanWrapperImpl(value).getPropertyValue(firstField).toString();
        String secondValue = new BeanWrapperImpl(value).getPropertyValue(secondField).toString();

        return (firsValue.equals(secondValue));
    }
}
