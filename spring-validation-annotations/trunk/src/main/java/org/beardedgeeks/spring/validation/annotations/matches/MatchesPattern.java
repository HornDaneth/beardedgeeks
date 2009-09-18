package org.beardedgeeks.spring.validation.annotations.matches;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchesPattern {
	String value();
	String errorCode() default "";
}
