package org.beardedgeeks.spring.validation.annotations.length;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.beardedgeeks.spring.validation.annotations.ExtendedValidationUtils;
import org.beardedgeeks.spring.validation.annotations.FieldValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component("maxLengthValidator")
public class MaxLengthValidator extends FieldValidator {
	@Override
	protected final Class<? extends Annotation> getAnnotationType() {
		return MaxLength.class;
	}

	@Override
	protected final void validate(final Field field, final Errors errors,
			final String errorCode) {
		int maxLength = field.getAnnotation(MaxLength.class).value();
		ExtendedValidationUtils.rejectIfLengthMoreThan(errors, field.getName(),
				maxLength, errorCode, new Object[] { maxLength });
	}
}