package org.beardedgeeks.spring.validation.annotations.value;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the field value has a maximum. Value <code>null</code> is
 * treated as valid.
 * 
 * @author hleinone
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxValue {
	/**
	 * The {@link java.lang.String} representation of the maximum value for the
	 * field.
	 */
	String value();

	/**
	 * The optional custom errorCode.
	 */
	String errorCode() default "";
}
