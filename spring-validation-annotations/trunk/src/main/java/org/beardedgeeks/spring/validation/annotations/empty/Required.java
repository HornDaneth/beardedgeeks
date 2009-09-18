package org.beardedgeeks.spring.validation.annotations.empty;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the field value must be set.
 * 
 * @author hleinone
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
	/**
	 * The optional custom errorCode.
	 */
	String errorCode() default "";
}
