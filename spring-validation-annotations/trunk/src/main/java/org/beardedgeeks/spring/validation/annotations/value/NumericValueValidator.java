package org.beardedgeeks.spring.validation.annotations.value;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.beardedgeeks.spring.validation.annotations.FieldValidator;
import org.springframework.validation.Errors;

/**
 * Enables validation of any numeric value. The field type must either be a
 * numeric primitive or an instance of {@link java.lang.Number}.
 * 
 * @author hleinone
 */
public abstract class NumericValueValidator extends FieldValidator {
	/**
	 * Resolves the value of the annotation.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected final void validate(final Field field, final Errors errors,
			final String errorCode) {
		final String valueAsString = getAnnotationValue(field);
		Class<?> valueType = field.getType();
		if (valueType.isPrimitive()) {
			if (valueType.equals(byte.class))
				valueType = Byte.class;
			else if (valueType.equals(short.class))
				valueType = Short.class;
			else if (valueType.equals(int.class))
				valueType = Integer.class;
			else if (valueType.equals(long.class))
				valueType = Long.class;
			else if (valueType.equals(float.class))
				valueType = Float.class;
			else if (valueType.equals(double.class))
				valueType = Double.class;
			else
				throw new IllegalArgumentException("Validating against type "
						+ valueType.getName() + " is not supported.");
		}

		if (!Number.class.isAssignableFrom(valueType))
			throw new IllegalArgumentException("Validating against type "
					+ valueType.getName() + " is not supported.");

		try {
			Constructor<? extends Number> constructor = ((Class<? extends Number>) valueType)
					.getConstructor(String.class);
			Comparable<Number> value = (Comparable<Number>) constructor
					.newInstance(valueAsString);
			validateNumber(field, errors, errorCode, value);
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

	/**
	 * Extension point for the actual validation of the numeric field.
	 * 
	 * @param field
	 *            The field to validate.
	 * @param errors
	 *            The errors object to bind validation errors.
	 * @param errorCode
	 *            The error code.
	 * @param value
	 *            The value to validate against.
	 */
	protected abstract void validateNumber(final Field field,
			final Errors errors, final String errorCode,
			Comparable<? extends Number> value);

	/**
	 * Extension point for the value retrieval in its String form.
	 * 
	 * @param field
	 *            The field to validate.
	 * @return The value to validate against in its String form.
	 */
	protected abstract String getAnnotationValue(final Field field);
}