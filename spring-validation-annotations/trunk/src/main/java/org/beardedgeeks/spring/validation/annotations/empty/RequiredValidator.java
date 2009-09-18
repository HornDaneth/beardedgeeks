package org.beardedgeeks.spring.validation.annotations.empty;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.beardedgeeks.spring.validation.annotations.FieldValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * Validator for {@link Required} annotations.
 * 
 * @author hleinone
 */
@Component("requiredValidator")
public class RequiredValidator extends FieldValidator {
	@Override
	protected final Class<? extends Annotation> getAnnotationType() {
		return Required.class;
	}

	@Override
	protected final void validate(Field field, Errors errors, String errorCode) {
		ValidationUtils.rejectIfEmpty(errors, field.getName(), errorCode);
	}
}