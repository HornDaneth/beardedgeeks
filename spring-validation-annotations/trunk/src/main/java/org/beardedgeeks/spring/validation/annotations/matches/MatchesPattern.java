package org.beardedgeeks.spring.validation.annotations.matches;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the field value must match a pattern. Value <code>null</code>
 * is treated as valid. Assumes that the field used on has a
 * <code>matches(java.lang.String)</code> method with <code>boolean</code>
 * return type.
 * 
 * @author hleinone
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchesPattern {
	/**
	 * The valid pattern for the field value.
	 */
	String value();

	/**
	 * The optional custom errorCode.
	 */
	String errorCode() default "";
}
