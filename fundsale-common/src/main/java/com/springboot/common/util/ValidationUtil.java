package com.springboot.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public final class ValidationUtil {

    private static Validator validator;

    @Autowired(required = true)
    public void setValidator(Validator validator) {
        ValidationUtil.validator = validator;
    }

    public static <T> Set<ConstraintViolation<T>> validate(T object) {
        return validator.validate(object);
    }
}
