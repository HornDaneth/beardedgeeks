package org.beardedgeeks.spring.validation.annotations;

import java.lang.reflect.InvocationTargetException;

import org.springframework.util.Assert;
import org.springframework.validation.Errors;

/**
 * Utility class offering convenient methods for invoking a Validator and for
 * rejecting common restrictions on fields.
 * <p>
 * Checks for common restrictions of a field in Validator implementations can
 * become one-liners when using these rejection methods.
 * 
 * @author hleinone
 * @see org.springframework.validation.ValidationUtils
 */
public class ExtendedValidationUtils {
	/**
	 * Reject the given field with the given error code if the value does not
	 * match the pattern. Value <code>null</code> is always considered valid.
	 * Requires the validated object to have a public
	 * <code>matches(java.lang.String)<code> method returning a <code>boolean</code>
	 * value.
	 * <p>
	 * The object whose field is being validated does not need to be passed in
	 * because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * 
	 * @param errors
	 *            the <code>Errors</code> instance to register errors on
	 * @param field
	 *            the field name to check
	 * @param pattern
	 *            the pattern to match
	 * @param errorCode
	 *            the error code, interpretable as message key
	 */
	public static void rejectIfNotMatches(final Errors errors,
			final String field, final String pattern, final String errorCode) {
		rejectIfNotMatches(errors, field, pattern, errorCode, null, null);
	}

	/**
	 * Reject the given field with the given error code and default message if
	 * the value does not match the pattern. Value <code>null</code> is always
	 * considered valid. Requires the validated object to have a public
	 * <code>matches(java.lang.String)<code> method returning a <code>boolean</code>
	 * value.
	 * <p>
	 * The object whose field is being validated does not need to be passed in
	 * because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * 
	 * @param errors
	 *            the <code>Errors</code> instance to register errors on
	 * @param field
	 *            the field name to check
	 * @param pattern
	 *            the pattern to match
	 * @param errorCode
	 *            error code, interpretable as message key
	 * @param defaultMessage
	 *            fallback default message
	 */
	public static void rejectIfNotMatches(final Errors errors,
			final String field, final String pattern, final String errorCode,
			final String defaultMessage) {
		rejectIfNotMatches(errors, field, pattern, errorCode, null,
				defaultMessage);
	}

	/**
	 * Reject the given field with the given error code and error arguments if
	 * the value does not match the pattern. Value <code>null</code> is always
	 * considered valid. Requires the validated object to have a public
	 * <code>matches(java.lang.String)<code> method returning a <code>boolean</code>
	 * value.
	 * <p>
	 * The object whose field is being validated does not need to be passed in
	 * because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * 
	 * @param errors
	 *            the <code>Errors</code> instance to register errors on
	 * @param field
	 *            the field name to check
	 * @param pattern
	 *            the pattern to match
	 * @param errorCode
	 *            the error code, interpretable as message key
	 * @param errorArgs
	 *            the error arguments, for argument binding via MessageFormat
	 *            (can be <code>null</code>)
	 */
	public static void rejectIfNotMatches(final Errors errors,
			final String field, final String pattern, final String errorCode,
			final Object[] errorArgs) {
		rejectIfNotMatches(errors, field, pattern, errorCode, errorArgs, null);
	}

	/**
	 * Reject the given field with the given error code, error arguments and
	 * default message if the value does not match the pattern. Value
	 * <code>null</code> is always considered valid. Requires the validated
	 * object to have a public
	 * <code>matches(java.lang.String)<code> method returning a <code>boolean</code>
	 * value.
	 * <p>
	 * The object whose field is being validated does not need to be passed in
	 * because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * 
	 * @param errors
	 *            the <code>Errors</code> instance to register errors on
	 * @param field
	 *            the field name to check
	 * @param pattern
	 *            the pattern to match
	 * @param errorCode
	 *            the error code, interpretable as message key
	 * @param errorArgs
	 *            the error arguments, for argument binding via MessageFormat
	 *            (can be <code>null</code>)
	 * @param defaultMessage
	 *            fallback default message
	 */
	public static void rejectIfNotMatches(final Errors errors,
			final String field, final String pattern, final String errorCode,
			final Object[] errorArgs, final String defaultMessage) {

		Assert.notNull(errors, "Errors object must not be null");
		final Object value = errors.getFieldValue(field);
		if (value == null)
			return;
		try {
			if (!(Boolean) value.getClass().getMethod("matches",
					new Class<?>[] { String.class }).invoke(value, pattern))
				errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (SecurityException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		}
	}

	/**
	 * Reject the given field with the given error code if the length of the
	 * value is less than <code>minLength</code>. Value <code>null</code> is
	 * always considered valid. Requires the validated object to have a public
	 * <code>length()<code> method returning a <code>int</code> value.
	 * <p>
	 * The object whose field is being validated does not need to be passed in
	 * because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * 
	 * @param errors
	 *            the <code>Errors</code> instance to register errors on
	 * @param field
	 *            the field name to check
	 * @param pattern
	 *            the pattern to match
	 * @param errorCode
	 *            the error code, interpretable as message key
	 */
	public static void rejectIfLengthLessThan(final Errors errors,
			final String field, final int minLength, final String errorCode) {
		rejectIfLengthLessThan(errors, field, minLength, errorCode, null, null);
	}

	/**
	 * Reject the given field with the given error code and default message if
	 * the length of the value is less than <code>minLength</code>. Value
	 * <code>null</code> is always considered valid. Requires the validated
	 * object to have a public
	 * <code>length()<code> method returning a <code>int</code> value.
	 * <p>
	 * The object whose field is being validated does not need to be passed in
	 * because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * 
	 * @param errors
	 *            the <code>Errors</code> instance to register errors on
	 * @param field
	 *            the field name to check
	 * @param pattern
	 *            the pattern to match
	 * @param errorCode
	 *            error code, interpretable as message key
	 * @param defaultMessage
	 *            fallback default message
	 */
	public static void rejectIfLengthLessThan(final Errors errors,
			final String field, final int minLength, final String errorCode,
			final String defaultMessage) {
		rejectIfLengthLessThan(errors, field, minLength, errorCode, null,
				defaultMessage);
	}

	/**
	 * Reject the given field with the given error code and error arguments if
	 * the length of the value is less than <code>minLength</code>. Value
	 * <code>null</code> is always considered valid. Requires the validated
	 * object to have a public
	 * <code>length()<code> method returning a <code>int</code> value.
	 * <p>
	 * The object whose field is being validated does not need to be passed in
	 * because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * 
	 * @param errors
	 *            the <code>Errors</code> instance to register errors on
	 * @param field
	 *            the field name to check
	 * @param pattern
	 *            the pattern to match
	 * @param errorCode
	 *            the error code, interpretable as message key
	 * @param errorArgs
	 *            the error arguments, for argument binding via MessageFormat
	 *            (can be <code>null</code>)
	 */
	public static void rejectIfLengthLessThan(final Errors errors,
			final String field, final int minLength, final String errorCode,
			final Object[] errorArgs) {
		rejectIfLengthLessThan(errors, field, minLength, errorCode, errorArgs,
				null);
	}

	/**
	 * Reject the given field with the given error code, error arguments and
	 * default message if the length of the value is less than
	 * <code>minLength</code>. Value <code>null</code> is always considered
	 * valid. Requires the validated object to have a public
	 * <code>length()<code> method returning a <code>int</code> value.
	 * <p>
	 * The object whose field is being validated does not need to be passed in
	 * because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * 
	 * @param errors
	 *            the <code>Errors</code> instance to register errors on
	 * @param field
	 *            the field name to check
	 * @param pattern
	 *            the pattern to match
	 * @param errorCode
	 *            the error code, interpretable as message key
	 * @param errorArgs
	 *            the error arguments, for argument binding via MessageFormat
	 *            (can be <code>null</code>)
	 * @param defaultMessage
	 *            fallback default message
	 */
	public static void rejectIfLengthLessThan(final Errors errors,
			final String field, final int minLength, final String errorCode,
			final Object[] errorArgs, final String defaultMessage) {

		Assert.notNull(errors, "Errors object must not be null");
		final Object value = errors.getFieldValue(field);
		if (value == null)
			return;
		try {
			if ((Integer) value.getClass().getMethod("length",
					new Class<?>[] {}).invoke(value) < minLength)
				errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (SecurityException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		}
	}

	/**
	 * Reject the given field with the given error code if the length of the
	 * value is more than <code>maxLength</code>. Value <code>null</code> is
	 * always considered valid. Requires the validated object to have a public
	 * <code>length()<code> method returning a <code>int</code> value.
	 * <p>
	 * The object whose field is being validated does not need to be passed in
	 * because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * 
	 * @param errors
	 *            the <code>Errors</code> instance to register errors on
	 * @param field
	 *            the field name to check
	 * @param pattern
	 *            the pattern to match
	 * @param errorCode
	 *            the error code, interpretable as message key
	 */
	public static void rejectIfLengthMoreThan(final Errors errors,
			final String field, final int maxLength, final String errorCode) {
		rejectIfLengthMoreThan(errors, field, maxLength, errorCode, null, null);
	}

	/**
	 * Reject the given field with the given error code and default message if
	 * the length of the value is more than <code>maxLength</code>. Value
	 * <code>null</code> is always considered valid. Requires the validated
	 * object to have a public
	 * <code>length()<code> method returning a <code>int</code> value.
	 * <p>
	 * The object whose field is being validated does not need to be passed in
	 * because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * 
	 * @param errors
	 *            the <code>Errors</code> instance to register errors on
	 * @param field
	 *            the field name to check
	 * @param pattern
	 *            the pattern to match
	 * @param errorCode
	 *            error code, interpretable as message key
	 * @param defaultMessage
	 *            fallback default message
	 */
	public static void rejectIfLengthMoreThan(final Errors errors,
			final String field, final int maxLength, final String errorCode,
			final String defaultMessage) {
		rejectIfLengthMoreThan(errors, field, maxLength, errorCode, null,
				defaultMessage);
	}

	/**
	 * Reject the given field with the given error code and error arguments if
	 * the length of the value is less than <code>minLength</code>. Value
	 * <code>null</code> is always considered valid. Requires the validated
	 * object to have a public
	 * <code>length()<code> method returning a <code>int</code> value.
	 * <p>
	 * The object whose field is being validated does not need to be passed in
	 * because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * 
	 * @param errors
	 *            the <code>Errors</code> instance to register errors on
	 * @param field
	 *            the field name to check
	 * @param pattern
	 *            the pattern to match
	 * @param errorCode
	 *            the error code, interpretable as message key
	 * @param errorArgs
	 *            the error arguments, for argument binding via MessageFormat
	 *            (can be <code>null</code>)
	 */
	public static void rejectIfLengthMoreThan(final Errors errors,
			final String field, final int maxLength, final String errorCode,
			final Object[] errorArgs) {
		rejectIfLengthMoreThan(errors, field, maxLength, errorCode, errorArgs,
				null);
	}

	/**
	 * Reject the given field with the given error code, error arguments and
	 * default message if the length of the value is more than
	 * <code>maxLength</code>. Value <code>null</code> is always considered
	 * valid. Requires the validated object to have a public
	 * <code>length()<code> method returning a <code>int</code> value.
	 * <p>
	 * The object whose field is being validated does not need to be passed in
	 * because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * 
	 * @param errors
	 *            the <code>Errors</code> instance to register errors on
	 * @param field
	 *            the field name to check
	 * @param pattern
	 *            the pattern to match
	 * @param errorCode
	 *            the error code, interpretable as message key
	 * @param errorArgs
	 *            the error arguments, for argument binding via MessageFormat
	 *            (can be <code>null</code>)
	 * @param defaultMessage
	 *            fallback default message
	 */
	public static void rejectIfLengthMoreThan(final Errors errors,
			final String field, final int maxLength, final String errorCode,
			final Object[] errorArgs, final String defaultMessage) {

		Assert.notNull(errors, "Errors object must not be null");
		final Object value = errors.getFieldValue(field);
		if (value == null)
			return;
		try {
			if ((Integer) value.getClass().getMethod("length",
					new Class<?>[] {}).invoke(value) > maxLength)
				errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (SecurityException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Validating object oftype "
					+ value.getClass().getName() + " is not supported.", e);
		}
	}
}
