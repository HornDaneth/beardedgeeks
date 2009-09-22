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
	 * The auto-boxed class of the desired primitive type. Must be a
	 * {@link java.lang.Number} implementation,
	 * {@link java.util.concurrent.atomic.AtomicInteger} and
	 * {@link java.util.concurrent.atomic.AtomicLong} are currently NOT
	 * supported. Must also implement {@link java.lang.Comparable} interface.
	 * Must have a public constructor which takes a {@link java.lang.String} as
	 * a parameter. Defaults to {@link java.lang.Integer}.
	 */
	Class<? extends Number> type() default Integer.class;

	/**
	 * The optional custom errorCode.
	 */
	String errorCode() default "";
}
