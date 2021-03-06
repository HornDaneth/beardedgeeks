package org.beardedgeeks.spring.validation.annotations.length;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.beardedgeeks.spring.validation.annotations.ExtendedValidationUtils;
import org.beardedgeeks.spring.validation.annotations.FieldValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link MinLength} annotations.
 * 
 * @author hleinone
 */
@Component("minLengthValidator")
public class MinLengthValidator extends FieldValidator {
	@Override
	protected final Class<? extends Annotation> getAnnotationType() {
		return MinLength.class;
	}

	@Override
	protected final void validate(final Field field, final Errors errors,
			final String errorCode) {
		int minLength = field.getAnnotation(MinLength.class).value();
		ExtendedValidationUtils.rejectIfLengthLessThan(errors, field.getName(),
				minLength, errorCode, new Object[] { minLength });
	}
}