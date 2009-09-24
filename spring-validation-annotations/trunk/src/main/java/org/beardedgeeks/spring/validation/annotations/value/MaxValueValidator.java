package org.beardedgeeks.spring.validation.annotations.value;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.beardedgeeks.spring.validation.annotations.ExtendedValidationUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link MaxValue} annotations.
 * 
 * @author hleinone
 */
@Component("maxValueValidator")
public class MaxValueValidator extends NumericValueValidator {
	@Override
	protected final Class<? extends Annotation> getAnnotationType() {
		return MaxValue.class;
	}

	@Override
	protected String getAnnotationValue(final Field field) {
		return field.getAnnotation(MaxValue.class).value();
	}

	@Override
	protected void validateNumber(final Field field, final Errors errors,
			final String errorCode, Comparable<? extends Number> value) {
		ExtendedValidationUtils.rejectIfValueMoreThan(errors, field.getName(),
				value, errorCode, new Object[] { value });
	}
}
