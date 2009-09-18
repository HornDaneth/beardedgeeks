package org.beardedgeeks.spring.validation.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * The class enabling validation of fields in an object.
 * 
 * @author hleinone
 * @see org.springframework.validation.Validator
 */
public abstract class FieldValidator implements Validator {
	/**
	 * If the validatable object is annotated with {@link Validatable} return
	 * true.
	 * 
	 * @see org.springframework.validation.Validator#supports(Class)
	 */
	@Override
	public final boolean supports(Class<?> clazz) {
		return clazz.isAnnotationPresent(Validatable.class);
	}

	/**
	 * Validates the field. If usage of a custom error code is wanted the
	 * annotation should have a method called <code>errorCode</code>. The
	 * default error code format is:
	 * <p>
	 * <code>boundObjectName.fieldName.annotationClassName</code>
	 * <p>
	 * ie. <code>loginForm.password.required</code>
	 * 
	 * @see org.springframework.validation.Validator#validate(Object, Errors)
	 */
	@Override
	public final void validate(Object obj, Errors errors) {
		List<Field> fields = new ArrayList<Field>();
		fields.addAll(Arrays.asList(obj.getClass().getFields()));
		fields.addAll(Arrays.asList(obj.getClass().getDeclaredFields()));

		for (final Field field : fields) {
			// don't show more than one validation error per field
			if (errors.hasFieldErrors(field.getName()))
				continue;

			Class<? extends Annotation> annotationType = getAnnotationType();
			if (field.isAnnotationPresent(annotationType)) {
				String errorCode = null;
				// suppress all exceptions
				try {
					Annotation annotation = field.getAnnotation(annotationType);
					errorCode = (String) annotation.annotationType()
							.getDeclaredMethod("errorCode", new Class<?>[] {})
							.invoke(annotation);
				} catch (IllegalArgumentException e) {
				} catch (SecurityException e) {
				} catch (IllegalAccessException e) {
				} catch (InvocationTargetException e) {
				} catch (NoSuchMethodException e) {
				} finally {
					// default errorCode
					if (errorCode == null || errorCode.isEmpty()) {
						String className = annotationType.getSimpleName();
						className = className.substring(0, 1).toLowerCase(
								Locale.ENGLISH)
								+ className.substring(1);
						errorCode = errors.getObjectName() + "."
								+ field.getName() + "." + className;
					}
					validate(field, errors, errorCode);
				}
			}
		}
	}

	/**
	 * Extension point for the actual field validation.
	 * 
	 * @param field
	 *            The field to validate.
	 * @param errors
	 *            The errors object to bind validation errors.
	 * @param errorCode
	 *            The error code.
	 */
	protected abstract void validate(Field field, Errors errors,
			String errorCode);

	/**
	 * Extension point for different annotations.
	 * 
	 * @return The annotation bound to the validator.
	 */
	protected abstract Class<? extends Annotation> getAnnotationType();
}
