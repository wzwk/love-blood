package com.wengzw.blood.common.validator;

import cn.hutool.core.util.StrUtil;
import com.wengzw.blood.common.validator.annotations.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author wengzw
 * @date 2022/7/31 11:35
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final Pattern PATTERN=Pattern.compile( "^1[345678]\\d{9}$");

    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StrUtil.isBlank(value)) {
            return false;
        } else {
            return PATTERN.matcher(value).matches();
        }
    }
}
