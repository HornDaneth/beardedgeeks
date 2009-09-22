package org.beardedgeeks.spring.validation.annotations.value;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;

import org.beardedgeeks.spring.validation.annotations.ExtendedValidationUtils;
import org.beardedgeeks.spring.validation.annotations.FieldValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link MinValue} annotations.
 * 
 * @author hleinone
 */
@Component("minValueValidator")
public class MinValueValidator extends FieldValidator {
	@Override
	protected final Class<? extends Annotation> getAnnotationType() {
		return MinValue.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected final void validate(final Field field, final Errors errors,
			final String errorCode) {
		String minValueAsString = field.getAnnotation(MinValue.class).value();
		Class<? extends Number> valueType = field.getAnnotation(MinValue.class)
				.type();

		if (!valueType.equals(field.getType()))
			throw new IllegalArgumentException("Validating object of type "
					+ field.getType().getName() + " against type "
					+ valueType.getName() + "is not supported.");

		try {
			Constructor<? extends Number> constructor = valueType
					.getConstructor(String.class);
			Comparable<Number> minValue = (Comparable<Number>) constructor
					.newInstance(minValueAsString);
			ExtendedValidationUtils.rejectIfValueLessThan(errors, field
					.getName(), minValue, errorCode, new Object[] { minValue });
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