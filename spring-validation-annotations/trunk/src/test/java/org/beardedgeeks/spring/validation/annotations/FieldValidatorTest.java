package org.beardedgeeks.spring.validation.annotations;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import org.junit.Test;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FieldValidatorTest {
	@Test
	public void testSupports_notSupports() throws SecurityException,
			NoSuchMethodException {
		final Validator fieldValidatorMock = createMock(FieldValidator.class,
				FieldValidator.class.getDeclaredMethod("validate",
						new Class<?>[] { Field.class, Errors.class,
								String.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}));
		replay(fieldValidatorMock);
		assertFalse(fieldValidatorMock.supports(NotValidatableClass.class));
		verify(fieldValidatorMock);
	}

	@Test
	public void testSupports_supports() throws SecurityException,
			NoSuchMethodException {
		final Validator fieldValidatorMock = createMock(FieldValidator.class,
				FieldValidator.class.getDeclaredMethod("validate",
						new Class<?>[] { Field.class, Errors.class,
								String.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}));
		replay(fieldValidatorMock);
		assertTrue(fieldValidatorMock.supports(ValidatableClass.class));
		verify(fieldValidatorMock);
	}

	private static final class NotValidatableClass {
	}

	@Validatable
	private static final class ValidatableClass {
	}

	@Test
	public void testValidate_hasFieldErrors() throws SecurityException,
			NoSuchMethodException {
		final Validator fieldValidatorMock = createMock(FieldValidator.class,
				FieldValidator.class.getDeclaredMethod("validate",
						new Class<?>[] { Field.class, Errors.class,
								String.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}));
		final Errors errorsMock = createMock(Errors.class);
		final TestObjectWithoutErrorCode testObject = new TestObjectWithoutErrorCode();
		expect(errorsMock.hasFieldErrors("testField")).andReturn(true);

		replay(fieldValidatorMock, errorsMock);
		fieldValidatorMock.validate(testObject, errorsMock);
		verify(fieldValidatorMock, errorsMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testValidate_annotationNotPresent() throws SecurityException,
			NoSuchMethodException {
		final FieldValidator fieldValidatorMock = createMock(
				FieldValidator.class, FieldValidator.class.getDeclaredMethod(
						"validate", new Class<?>[] { Field.class, Errors.class,
								String.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}));
		final Errors errorsMock = createMock(Errors.class);
		final TestObjectWithoutErrorCode testObject = new TestObjectWithoutErrorCode();
		expect(errorsMock.hasFieldErrors("testField")).andReturn(false);
		// for some reason doesn't work with generics
		expect(fieldValidatorMock.getAnnotationType()).andReturn(
				(Class) Test.class);

		replay(fieldValidatorMock, errorsMock);
		fieldValidatorMock.validate(testObject, errorsMock);
		verify(fieldValidatorMock, errorsMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testValidate_annotationWithoutErrorCode()
			throws SecurityException, NoSuchMethodException,
			NoSuchFieldException {
		final FieldValidator fieldValidatorMock = createMock(
				FieldValidator.class, FieldValidator.class.getDeclaredMethod(
						"validate", new Class<?>[] { Field.class, Errors.class,
								String.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}));
		final Errors errorsMock = createMock(Errors.class);
		final TestObjectWithoutErrorCode testObject = new TestObjectWithoutErrorCode();
		expect(errorsMock.hasFieldErrors("testField")).andReturn(false);
		// for some reason doesn't work with generics
		expect(fieldValidatorMock.getAnnotationType()).andReturn(
				(Class) TestAnnotationWithoutErrorCode.class);
		expect(errorsMock.getObjectName()).andReturn("test");
		fieldValidatorMock.validate(TestObjectWithoutErrorCode.class
				.getDeclaredField("testField"), errorsMock,
				"test.testField.testAnnotationWithoutErrorCode");

		replay(fieldValidatorMock, errorsMock);
		fieldValidatorMock.validate(testObject, errorsMock);
		verify(fieldValidatorMock, errorsMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testValidate_annotationWithErrorCodeThoughNotDefined()
			throws SecurityException, NoSuchMethodException,
			NoSuchFieldException {
		final FieldValidator fieldValidatorMock = createMock(
				FieldValidator.class, FieldValidator.class.getDeclaredMethod(
						"validate", new Class<?>[] { Field.class, Errors.class,
								String.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}));
		final Errors errorsMock = createMock(Errors.class);
		final TestObjectWithErrorCodeThoughNotDefined testObject = new TestObjectWithErrorCodeThoughNotDefined();
		expect(errorsMock.hasFieldErrors("testField")).andReturn(false);
		// for some reason doesn't work with generics
		expect(fieldValidatorMock.getAnnotationType()).andReturn(
				(Class) TestAnnotationWithErrorCode.class);
		expect(errorsMock.getObjectName()).andReturn("test");
		fieldValidatorMock.validate(
				TestObjectWithErrorCodeThoughNotDefined.class
						.getDeclaredField("testField"), errorsMock,
				"test.testField.testAnnotationWithErrorCode");

		replay(fieldValidatorMock, errorsMock);
		fieldValidatorMock.validate(testObject, errorsMock);
		verify(fieldValidatorMock, errorsMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testValidate_annotationWithErrorCode()
			throws SecurityException, NoSuchMethodException,
			NoSuchFieldException {
		final FieldValidator fieldValidatorMock = createMock(
				FieldValidator.class, FieldValidator.class.getDeclaredMethod(
						"validate", new Class<?>[] { Field.class, Errors.class,
								String.class }), FieldValidator.class
						.getDeclaredMethod("getAnnotationType",
								new Class<?>[] {}));
		final Errors errorsMock = createMock(Errors.class);
		final TestObjectWithErrorCode testObject = new TestObjectWithErrorCode();
		expect(errorsMock.hasFieldErrors("testField")).andReturn(false);
		// for some reason doesn't work with generics
		expect(fieldValidatorMock.getAnnotationType()).andReturn(
				(Class) TestAnnotationWithErrorCode.class);
		fieldValidatorMock.validate(TestObjectWithErrorCode.class
				.getDeclaredField("testField"), errorsMock, "error");

		replay(fieldValidatorMock, errorsMock);
		fieldValidatorMock.validate(testObject, errorsMock);
		verify(fieldValidatorMock, errorsMock);
	}

	@Validatable
	private static final class TestObjectWithoutErrorCode {
		@SuppressWarnings("unused")
		@TestAnnotationWithoutErrorCode
		private String testField = "foo";
	}

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	private static @interface TestAnnotationWithoutErrorCode {

	}

	@Validatable
	private static final class TestObjectWithErrorCodeThoughNotDefined {
		@SuppressWarnings("unused")
		@TestAnnotationWithErrorCode
		private String testField = "foo";
	}

	@Validatable
	private static final class TestObjectWithErrorCode {
		@SuppressWarnings("unused")
		@TestAnnotationWithErrorCode(errorCode = "error")
		private String testField = "foo";
	}

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface TestAnnotationWithErrorCode {
		String errorCode() default "";
	}

}
