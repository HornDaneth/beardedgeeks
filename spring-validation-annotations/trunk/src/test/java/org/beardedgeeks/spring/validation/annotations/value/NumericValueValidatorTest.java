package org.beardedgeeks.spring.validation.annotations.value;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicInteger;

import org.beardedgeeks.spring.validation.annotations.FieldValidator;
import org.junit.Test;
import org.springframework.validation.Errors;

public class NumericValueValidatorTest {

	@Test(expected = IllegalArgumentException.class)
	public void testValidate_incompatiblePrimitive() throws SecurityException,
			NoSuchFieldException, NoSuchMethodException {
		final NumericValueValidator numericValueValidatorMock = createMock(
				NumericValueValidator.class, NumericValueValidator.class
						.getDeclaredMethod("validateNumber", new Class<?>[] {
								Field.class, Errors.class, String.class,
								Comparable.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}), NumericValueValidator.class
						.getDeclaredMethod("getAnnotationValue",
								new Class<?>[] { Field.class }));
		final Errors errorsMock = createMock(Errors.class);
		final Field fieldMock = IncompatiblePrimitive.class
				.getDeclaredField("foo");
		expect(numericValueValidatorMock.getAnnotationValue(fieldMock))
				.andReturn("3");
		replay(errorsMock, numericValueValidatorMock);
		numericValueValidatorMock.validate(fieldMock, errorsMock, "error");
		verify(errorsMock, numericValueValidatorMock);
	}

	private static final class IncompatiblePrimitive {
		@SuppressWarnings("unused")
		@MaxValue("3")
		private boolean foo;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidate_incompatibleObject() throws SecurityException,
			NoSuchFieldException, NoSuchMethodException {
		final NumericValueValidator numericValueValidatorMock = createMock(
				NumericValueValidator.class, NumericValueValidator.class
						.getDeclaredMethod("validateNumber", new Class<?>[] {
								Field.class, Errors.class, String.class,
								Comparable.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}), NumericValueValidator.class
						.getDeclaredMethod("getAnnotationValue",
								new Class<?>[] { Field.class }));
		final Errors errorsMock = createMock(Errors.class);
		final Field fieldMock = IncompatibleObject.class
				.getDeclaredField("foo");
		expect(numericValueValidatorMock.getAnnotationValue(fieldMock))
				.andReturn("3");
		replay(errorsMock, numericValueValidatorMock);
		numericValueValidatorMock.validate(fieldMock, errorsMock, "error");
		verify(errorsMock, numericValueValidatorMock);
	}

	private static final class IncompatibleObject {
		@SuppressWarnings("unused")
		@MaxValue("3")
		private Object foo;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidate_incompatibleNumber() throws SecurityException,
			NoSuchFieldException, NoSuchMethodException {
		final NumericValueValidator numericValueValidatorMock = createMock(
				NumericValueValidator.class, NumericValueValidator.class
						.getDeclaredMethod("validateNumber", new Class<?>[] {
								Field.class, Errors.class, String.class,
								Comparable.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}), NumericValueValidator.class
						.getDeclaredMethod("getAnnotationValue",
								new Class<?>[] { Field.class }));
		final Errors errorsMock = createMock(Errors.class);
		final Field fieldMock = IncompatibleNumber.class
				.getDeclaredField("foo");
		expect(numericValueValidatorMock.getAnnotationValue(fieldMock))
				.andReturn("3");
		replay(errorsMock, numericValueValidatorMock);
		try {
			numericValueValidatorMock.validate(fieldMock, errorsMock, "error");
		} catch (IllegalArgumentException e) {
			assertEquals(NoSuchMethodException.class, e.getCause().getClass());
			throw e;
		}
		verify(errorsMock, numericValueValidatorMock);
	}

	private static final class IncompatibleNumber {
		@SuppressWarnings("unused")
		@MaxValue("3")
		private AtomicInteger foo;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidate_incompatibleValue() throws SecurityException,
			NoSuchFieldException, NoSuchMethodException {
		final NumericValueValidator numericValueValidatorMock = createMock(
				NumericValueValidator.class, NumericValueValidator.class
						.getDeclaredMethod("validateNumber", new Class<?>[] {
								Field.class, Errors.class, String.class,
								Comparable.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}), NumericValueValidator.class
						.getDeclaredMethod("getAnnotationValue",
								new Class<?>[] { Field.class }));
		final Errors errorsMock = createMock(Errors.class);
		final Field fieldMock = DoubleObject.class.getDeclaredField("foo");
		expect(numericValueValidatorMock.getAnnotationValue(fieldMock))
				.andReturn("3L");
		replay(errorsMock, numericValueValidatorMock);
		try {
			numericValueValidatorMock.validate(fieldMock, errorsMock, "error");
		} catch (IllegalArgumentException e) {
			assertEquals(InvocationTargetException.class, e.getCause()
					.getClass());
			throw e;
		}
		verify(errorsMock, numericValueValidatorMock);
	}

	private static final class DoubleObject {
		@SuppressWarnings("unused")
		@MaxValue("3")
		private Double foo;
	}

	@Test
	public void testValidate_compatiblePrimitive() throws SecurityException,
			NoSuchFieldException, NoSuchMethodException {
		final NumericValueValidator numericValueValidatorMock = createMock(
				NumericValueValidator.class, NumericValueValidator.class
						.getDeclaredMethod("validateNumber", new Class<?>[] {
								Field.class, Errors.class, String.class,
								Comparable.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}), NumericValueValidator.class
						.getDeclaredMethod("getAnnotationValue",
								new Class<?>[] { Field.class }));
		final Errors errorsMock = createMock(Errors.class);
		final Field fieldMock = CompatiblePrimitive.class
				.getDeclaredField("foo");
		expect(numericValueValidatorMock.getAnnotationValue(fieldMock))
				.andReturn("3");
		numericValueValidatorMock.validateNumber(fieldMock, errorsMock,
				"error", 3);
		replay(errorsMock, numericValueValidatorMock);
		numericValueValidatorMock.validate(fieldMock, errorsMock, "error");
		verify(errorsMock, numericValueValidatorMock);
	}

	private static final class CompatiblePrimitive {
		@SuppressWarnings("unused")
		@MaxValue("3")
		private int foo;
	}

	@Test
	public void testValidate_compatibleNumber() throws SecurityException,
			NoSuchFieldException, NoSuchMethodException {
		final NumericValueValidator numericValueValidatorMock = createMock(
				NumericValueValidator.class, NumericValueValidator.class
						.getDeclaredMethod("validateNumber", new Class<?>[] {
								Field.class, Errors.class, String.class,
								Comparable.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}), NumericValueValidator.class
						.getDeclaredMethod("getAnnotationValue",
								new Class<?>[] { Field.class }));
		final Errors errorsMock = createMock(Errors.class);
		final Field fieldMock = CompatibleNumber.class.getDeclaredField("foo");
		expect(numericValueValidatorMock.getAnnotationValue(fieldMock))
				.andReturn("3");
		numericValueValidatorMock.validateNumber(fieldMock, errorsMock,
				"error", 3);
		replay(errorsMock, numericValueValidatorMock);
		numericValueValidatorMock.validate(fieldMock, errorsMock, "error");
		verify(errorsMock, numericValueValidatorMock);
	}

	private static final class CompatibleNumber {
		@SuppressWarnings("unused")
		@MaxValue("3")
		private Integer foo;
	}
}
