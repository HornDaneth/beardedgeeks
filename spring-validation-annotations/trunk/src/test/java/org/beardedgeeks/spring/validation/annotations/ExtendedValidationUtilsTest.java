package org.beardedgeeks.spring.validation.annotations;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.springframework.validation.Errors;

/**
 * Tests for {@link ExtendedValidationUtils}.
 * 
 * @author hleinone
 */
public class ExtendedValidationUtilsTest {

	// rejectIfNotMatches

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches1_errorsNull() {
		ExtendedValidationUtils.rejectIfNotMatches(null, "foo", "bar", "error");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches2_errorsNull() {
		ExtendedValidationUtils.rejectIfNotMatches(null, "foo", "bar", "error",
				"Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches3_errorsNull() {
		ExtendedValidationUtils.rejectIfNotMatches(null, "foo", "bar", "error",
				new Object[] {});
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches4_errorsNull() {
		ExtendedValidationUtils.rejectIfNotMatches(null, "foo", "bar", "error",
				new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfNotMatches1_valueNull() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfNotMatches2_valueNull() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfNotMatches3_valueNull() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", new Object[] {});
		verify(errorsMock);
	}

	@Test
	public void testRejectIfNotMatches4_valueNull() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", new Object[] {}, "Error!");
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches1_noMatchesMethod() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches2_noMatchesMethod() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches3_noMatchesMethod() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", expectedErrorArgs);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches4_noMatchesMethod() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", expectedErrorArgs, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches1_inaccessibleMatches() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new InaccessibleMatches());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches2_inaccessibleMatches() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new InaccessibleMatches());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches3_inaccessibleMatches() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new InaccessibleMatches());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", expectedErrorArgs);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches4_inaccessibleMatches() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new InaccessibleMatches());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", expectedErrorArgs, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	private static final class InaccessibleMatches {
		@SuppressWarnings("unused")
		private boolean matches(String pattern) {
			return false;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches1_matchesThrowsException() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new MatchesThrowsException());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(),
					InvocationTargetException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches2_matchesThrowsException() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new MatchesThrowsException());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(),
					InvocationTargetException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches3_matchesThrowsException() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new MatchesThrowsException());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", expectedErrorArgs);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(),
					InvocationTargetException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches4_matchesThrowsException() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new MatchesThrowsException());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", expectedErrorArgs, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(),
					InvocationTargetException.class);
			throw e;
		}
		verify(errorsMock);
	}

	private static final class MatchesThrowsException {
		@SuppressWarnings("unused")
		public boolean matches(String pattern) {
			throw new NullPointerException();
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches1_incompatibleMatchesReturn() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new IncompatibleMatchesReturn());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches2_incompatibleMatchesReturn() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new IncompatibleMatchesReturn());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches3_incompatibleMatchesReturn() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new IncompatibleMatchesReturn());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", expectedErrorArgs);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches4_incompatibleMatchesReturn() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new IncompatibleMatchesReturn());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", expectedErrorArgs, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}
		verify(errorsMock);
	}

	private static final class IncompatibleMatchesReturn {
		@SuppressWarnings("unused")
		public String matches(String pattern) {
			return "foo";
		}
	}

	@Test
	public void testRejectIfNotMatches1_notMatches() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("foo");
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfNotMatches2_notMatches() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("foo");
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfNotMatches3_notMatches() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("foo");
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", expectedErrorArgs);
		verify(errorsMock);
	}

	@Test
	public void testRejectIfNotMatches4_notMatches() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("foo");
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", expectedErrorArgs, "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfNotMatches1_matches() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfNotMatches2_matches() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfNotMatches3_matches() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", expectedErrorArgs);
		verify(errorsMock);
	}

	@Test
	public void testRejectIfNotMatches4_matches() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", expectedErrorArgs, "Error!");
		verify(errorsMock);
	}

	// rejectIfLengthLessThan

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan1_errorsNull() {
		ExtendedValidationUtils.rejectIfLengthLessThan(null, "foo", 1, "error");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan2_errorsNull() {
		ExtendedValidationUtils.rejectIfLengthLessThan(null, "foo", 1, "error",
				"Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan3_errorsNull() {
		ExtendedValidationUtils.rejectIfLengthLessThan(null, "foo", 1, "error",
				new Object[] {});
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan4_errorsNull() {
		ExtendedValidationUtils.rejectIfLengthLessThan(null, "foo", 1, "error",
				new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfLengthLessThan1_valueNull() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan2_valueNull() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan3_valueNull() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", new Object[] {});
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan4_valueNull() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", new Object[] {}, "Error!");
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan1_noLengthMethod() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan2_noLengthMethod() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan3_noLengthMethod() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", expectedErrorArgs);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan4_noLengthMethod() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", expectedErrorArgs, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan1_inaccessibleLength() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new InaccessibleLength());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan2_inaccessibleLength() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new InaccessibleLength());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan3_inaccessibleLength() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new InaccessibleLength());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", expectedErrorArgs);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan4_inaccessibleLength() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new InaccessibleLength());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", expectedErrorArgs, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	private static final class InaccessibleLength {
		@SuppressWarnings("unused")
		private int length() {
			return -1;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan1_lengthThrowsException() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new LengthThrowsException());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(),
					InvocationTargetException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan2_lengthThrowsException() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new LengthThrowsException());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(),
					InvocationTargetException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan3_lengthThrowsException() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new LengthThrowsException());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", expectedErrorArgs);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(),
					InvocationTargetException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan4_lengthThrowsException() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new LengthThrowsException());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", expectedErrorArgs, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(),
					InvocationTargetException.class);
			throw e;
		}
		verify(errorsMock);
	}

	private static final class LengthThrowsException {
		@SuppressWarnings("unused")
		public int length() {
			throw new NullPointerException();
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan1_incompatibleLengthReturn() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new IncompatibleLengthReturn());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan2_incompatibleLengthReturn() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new IncompatibleLengthReturn());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan3_incompatibleLengthReturn() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new IncompatibleLengthReturn());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", expectedErrorArgs);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan4_incompatiblLengthReturn() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new IncompatibleLengthReturn());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", expectedErrorArgs, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}
		verify(errorsMock);
	}

	private static final class IncompatibleLengthReturn {
		@SuppressWarnings("unused")
		public String length() {
			return "foo";
		}
	}

	@Test
	public void testRejectIfLengthLessThan1_less() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("");
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan2_less() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("");
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan3_less() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("");
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan4_less() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("");
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan1_equal() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan2_equal() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan3_equal() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan4_equal() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan1_more() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan2_more() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan3_more() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthLessThan4_more() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
		verify(errorsMock);
	}

	// rejectIfLengthMoreThan

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan1_errorsNull() {
		ExtendedValidationUtils.rejectIfLengthMoreThan(null, "foo", 1, "error");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan2_errorsNull() {
		ExtendedValidationUtils.rejectIfLengthMoreThan(null, "foo", 1, "error",
				"Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan3_errorsNull() {
		ExtendedValidationUtils.rejectIfLengthMoreThan(null, "foo", 1, "error",
				new Object[] {});
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan4_errorsNull() {
		ExtendedValidationUtils.rejectIfLengthMoreThan(null, "foo", 1, "error",
				new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfLengthMoreThan1_valueNull() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan2_valueNull() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan3_valueNull() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", new Object[] {});
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan4_valueNull() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", new Object[] {}, "Error!");
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan1_noLengthMethod() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan2_noLengthMethod() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan3_noLengthMethod() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", expectedErrorArgs);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan4_noLengthMethod() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", expectedErrorArgs, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan1_inaccessibleLength() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new InaccessibleLength());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan2_inaccessibleLength() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new InaccessibleLength());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan3_inaccessibleLength() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new InaccessibleLength());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", expectedErrorArgs);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan4_inaccessibleLength() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new InaccessibleLength());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", expectedErrorArgs, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan1_lengthThrowsException() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new LengthThrowsException());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(),
					InvocationTargetException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan2_lengthThrowsException() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new LengthThrowsException());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(),
					InvocationTargetException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan3_lengthThrowsException() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new LengthThrowsException());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", expectedErrorArgs);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(),
					InvocationTargetException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan4_lengthThrowsException() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new LengthThrowsException());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", expectedErrorArgs, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(),
					InvocationTargetException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan1_incompatibleLengthReturn() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new IncompatibleLengthReturn());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan2_incompatibleLengthReturn() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new IncompatibleLengthReturn());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan3_incompatibleLengthReturn() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new IncompatibleLengthReturn());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", expectedErrorArgs);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan4_incompatiblLengthReturn() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn(
				new IncompatibleLengthReturn());
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", expectedErrorArgs, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan1_more() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan2_more() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan3_more() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan4_more() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan1_equal() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan2_equal() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan3_equal() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan4_equal() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan1_less() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan2_less() {
		final Errors errorsMock = createMock(Errors.class);

		expect(errorsMock.getFieldValue("foo")).andReturn("");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", "Error!");
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan3_less() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
		verify(errorsMock);
	}

	@Test
	public void testRejectIfLengthMoreThan4_less() {
		final Errors errorsMock = createMock(Errors.class);
		final Object[] expectedErrorArgs = new Object[] {};

		expect(errorsMock.getFieldValue("foo")).andReturn("");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
		verify(errorsMock);
	}
}
