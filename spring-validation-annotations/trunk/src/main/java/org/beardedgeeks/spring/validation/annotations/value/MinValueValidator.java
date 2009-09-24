package org.beardedgeeks.spring.validation.annotations.value;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.beardedgeeks.spring.validation.annotations.ExtendedValidationUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link MinValue} annotations.
 * 
 * @author hleinone
 */
@Component("minValueValidator")
public class MinValueValidator extends NumericValueValidator {
	@Override
	protected final Class<? extends Annotation> getAnnotationType() {
		return MinValue.class;
	}

	@Override
	protected String getAnnotationValue(final Field field) {
		return field.getAnnotation(MinValue.class).value();
	}

	@Override
	protected void validateNumber(final Field field, final Errors errors,
			final String errorCode, Comparable<? extends Number> value) {
		ExtendedValidationUtils.rejectIfValueLessThan(errors, field.getName(),
				value, errorCode, new Object[] { value });
	}

}