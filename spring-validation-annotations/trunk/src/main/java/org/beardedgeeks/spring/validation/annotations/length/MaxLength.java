package org.beardedgeeks.spring.validation.annotations.length;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the field value has a maximum length. Value <code>null</code>
 * is treated as valid. Assumes that the field used on has a
 * <code>length()</code> method with <code>int</code> return type.
 * 
 * @author hleinone
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxLength {
	/**
	 * The maximum length of the field value.
	 */
	int value();

	/**
	 * The optional custom errorCode.
	 */
	String errorCode() default "";
}
