package org.beardedgeeks.spring.validation.annotations.matches;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.beardedgeeks.spring.validation.annotations.ExtendedValidationUtils;
import org.beardedgeeks.spring.validation.annotations.FieldValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link MatchesPattern} annotations.
 * 
 * @author hleinone
 */
@Component("matchesPatternValidator")
public class MatchesPatternValidator extends FieldValidator {
	@Override
	protected final Class<? extends Annotation> getAnnotationType() {
		return MatchesPattern.class;
	}

	@Override
	protected final void validate(final Field field, final Errors errors,
			final String errorCode) {
		String pattern = field.getAnnotation(MatchesPattern.class).value();
		ExtendedValidationUtils.rejectIfNotMatches(errors, field.getName(),
				pattern, errorCode);
	}
}