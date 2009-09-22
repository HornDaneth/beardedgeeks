package org.beardedgeeks.spring.validation.annotations.value;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.beardedgeeks.spring.validation.annotations.ExtendedValidationUtils;
import org.beardedgeeks.spring.validation.annotations.FieldValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link MaxValue} annotations.
 * 
 * @author hleinone
 */
@Component("maxValueValidator")
public class MaxValueValidator extends FieldValidator {
	@Override
	protected final Class<? extends Annotation> getAnnotationType() {
		return MaxValue.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected final void validate(final Field field, final Errors errors,
			final String errorCode) {
		String maxValueAsString = field.getAnnotation(MaxValue.class).value();
		Class<? extends Number> valueType = field.getAnnotation(MaxValue.class)
				.type();

		if (!valueType.equals(field.getType()))
			throw new IllegalArgumentException("Validating object of type "
					+ field.getType().getName() + " against type "
					+ valueType.getName() + "is not supported.");

		try {
			Constructor<? extends Number> constructor = valueType
					.getConstructor(String.class);
			Comparable<Number> maxValue = (Comparable<Number>) constructor
					.newInstance(maxValueAsString);
			ExtendedValidationUtils.rejectIfValueLessThan(errors, field
					.getName(), maxValue, errorCode, new Object[] { maxValue });
		} catch (SecurityException e) {
			throw new IllegalArgumentException("Validating against type "
					+ valueType.getName() + " is not supported.", e);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("Validating against type "
					+ valueType.getName() + " is not supported.", e);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Validating against type "
					+ valueType.getName() + " is not supported.", e);
		} catch (InstantiationException e) {
			throw new IllegalArgumentException("Validating against type "
					+ valueType.getName() + " is not supported.", e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Validating against type "
					+ valueType.getName() + " is not supported.", e);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException("Validating against type "
					+ valueType.getName() + " is not supported.", e);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Validating against type "
					+ valueType.getName() + " is not supported.", e);
		}
	}
}