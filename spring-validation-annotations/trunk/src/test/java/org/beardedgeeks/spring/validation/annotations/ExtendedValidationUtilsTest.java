package org.beardedgeeks.spring.validation.annotations;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.Errors;

/**
 * Tests for {@link ExtendedValidationUtils}.
 * 
 * @author hleinone
 */
public class ExtendedValidationUtilsTest {
	private Errors errorsMock;

	@Before
	public void setUp() {
		errorsMock = createMock(Errors.class);
	}

	@After
	public void tearDown() {
		verify(errorsMock);
	}

	// rejectIfNotMatches

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches1_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(null, "foo", "bar", "error");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches2_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(null, "foo", "bar", "error",
				"Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches3_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(null, "foo", "bar", "error",
				new Object[] {});
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches4_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(null, "foo", "bar", "error",
				new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfNotMatches1_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error");
	}

	@Test
	public void testRejectIfNotMatches2_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", "Error!");
	}

	@Test
	public void testRejectIfNotMatches3_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfNotMatches4_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", new Object[] {}, "Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches1_noMatchesMethod() {
		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches2_noMatchesMethod() {
		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo",
					"bar", "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches3_noMatchesMethod() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches4_noMatchesMethod() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches1_inaccessibleMatches() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches2_inaccessibleMatches() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches3_inaccessibleMatches() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches4_inaccessibleMatches() {
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

	}

	private static final class InaccessibleMatches {
		@SuppressWarnings("unused")
		private boolean matches(String pattern) {
			return false;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches1_matchesThrowsException() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches2_matchesThrowsException() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches3_matchesThrowsException() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches4_matchesThrowsException() {
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

	}

	private static final class MatchesThrowsException {
		@SuppressWarnings("unused")
		public boolean matches(String pattern) {
			throw new NullPointerException();
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches1_incompatibleMatchesReturn() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches2_incompatibleMatchesReturn() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches3_incompatibleMatchesReturn() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfNotMatches4_incompatibleMatchesReturn() {
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

	}

	private static final class IncompatibleMatchesReturn {
		@SuppressWarnings("unused")
		public String matches(String pattern) {
			return "foo";
		}
	}

	@Test
	public void testRejectIfNotMatches1_notMatches() {
		expect(errorsMock.getFieldValue("foo")).andReturn("foo");
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error");
	}

	@Test
	public void testRejectIfNotMatches2_notMatches() {
		expect(errorsMock.getFieldValue("foo")).andReturn("foo");
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", "Error!");
	}

	@Test
	public void testRejectIfNotMatches3_notMatches() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("foo");
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfNotMatches4_notMatches() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("foo");
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfNotMatches1_matches() {
		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error");
	}

	@Test
	public void testRejectIfNotMatches2_matches() {
		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", "Error!");
	}

	@Test
	public void testRejectIfNotMatches3_matches() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfNotMatches4_matches() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "bar",
				"error", expectedErrorArgs, "Error!");
	}

	// rejectIfLengthLessThan

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan1_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(null, "foo", 1, "error");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan2_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(null, "foo", 1, "error",
				"Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan3_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(null, "foo", 1, "error",
				new Object[] {});
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan4_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(null, "foo", 1, "error",
				new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfLengthLessThan1_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfLengthLessThan2_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfLengthLessThan3_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfLengthLessThan4_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", new Object[] {}, "Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan1_noLengthMethod() {
		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan2_noLengthMethod() {
		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo",
					1, "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan3_noLengthMethod() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan4_noLengthMethod() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan1_inaccessibleLength() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan2_inaccessibleLength() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan3_inaccessibleLength() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan4_inaccessibleLength() {
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

	}

	private static final class InaccessibleLength {
		@SuppressWarnings("unused")
		private int length() {
			return -1;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan1_lengthThrowsException() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan2_lengthThrowsException() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan3_lengthThrowsException() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan4_lengthThrowsException() {
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

	}

	private static final class LengthThrowsException {
		@SuppressWarnings("unused")
		public int length() {
			throw new NullPointerException();
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan1_incompatibleLengthReturn() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan2_incompatibleLengthReturn() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan3_incompatibleLengthReturn() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthLessThan4_incompatiblLengthReturn() {
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

	}

	private static final class IncompatibleLengthReturn {
		@SuppressWarnings("unused")
		public String length() {
			return "foo";
		}
	}

	@Test
	public void testRejectIfLengthLessThan1_less() {
		expect(errorsMock.getFieldValue("foo")).andReturn("");
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfLengthLessThan2_less() {
		expect(errorsMock.getFieldValue("foo")).andReturn("");
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfLengthLessThan3_less() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("");
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfLengthLessThan4_less() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("");
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfLengthLessThan1_equal() {
		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfLengthLessThan2_equal() {
		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfLengthLessThan3_equal() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfLengthLessThan4_equal() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfLengthLessThan1_more() {
		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfLengthLessThan2_more() {
		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfLengthLessThan3_more() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfLengthLessThan4_more() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
	}

	// rejectIfLengthMoreThan

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan1_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(null, "foo", 1, "error");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan2_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(null, "foo", 1, "error",
				"Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan3_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(null, "foo", 1, "error",
				new Object[] {});
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan4_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(null, "foo", 1, "error",
				new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfLengthMoreThan1_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfLengthMoreThan2_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfLengthMoreThan3_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfLengthMoreThan4_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", new Object[] {}, "Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan1_noLengthMethod() {
		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan2_noLengthMethod() {
		expect(errorsMock.getFieldValue("foo")).andReturn(new Integer(1));
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo",
					1, "error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), NoSuchMethodException.class);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan3_noLengthMethod() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan4_noLengthMethod() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan1_inaccessibleLength() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan2_inaccessibleLength() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan3_inaccessibleLength() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan4_inaccessibleLength() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan1_lengthThrowsException() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan2_lengthThrowsException() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan3_lengthThrowsException() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan4_lengthThrowsException() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan1_incompatibleLengthReturn() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan2_incompatibleLengthReturn() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan3_incompatibleLengthReturn() {
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

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfLengthMoreThan4_incompatiblLengthReturn() {
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

	}

	@Test
	public void testRejectIfLengthMoreThan1_more() {
		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfLengthMoreThan2_more() {
		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfLengthMoreThan3_more() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfLengthMoreThan4_more() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("fo");
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfLengthMoreThan1_equal() {
		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfLengthMoreThan2_equal() {
		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfLengthMoreThan3_equal() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfLengthMoreThan4_equal() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("f");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfLengthMoreThan1_less() {
		expect(errorsMock.getFieldValue("foo")).andReturn("");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfLengthMoreThan2_less() {
		expect(errorsMock.getFieldValue("foo")).andReturn("");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfLengthMoreThan3_less() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfLengthMoreThan4_less() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn("");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfLengthMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
	}

	// rejectIfValueLessThan

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueLessThan1_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(null, "foo", 1, "error");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueLessThan2_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(null, "foo", 1, "error",
				"Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueLessThan3_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(null, "foo", 1, "error",
				new Object[] {});
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueLessThan4_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(null, "foo", 1, "error",
				new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error", new Object[] {}, "Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueLessThan1_inCompatibleValue() {
		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
					"error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueLessThan2_inCompatibleValue() {
		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
					"error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueLessThan3_inCompatibleValue() {
		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
					"error", new Object[] {});
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueLessThan4_inCompatibleValue() {
		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
					"error", new Object[] {}, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}

	}

	@Test
	public void testRejectIfValueLessThan1_moreWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_moreWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_moreWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_moreWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_moreWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1d,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_moreWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1d,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_moreWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1d,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_moreWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1d,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_moreWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 2);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(short) 1, "error");
	}

	@Test
	public void testRejectIfValueLessThan2_moreWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 2);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(short) 1, "error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_moreWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 2);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(short) 1, "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_moreWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 2);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(short) 1, "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_moreWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1L,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_moreWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1L,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_moreWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1L,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_moreWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1L,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_moreWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 2);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(byte) 1, "error");
	}

	@Test
	public void testRejectIfValueLessThan2_moreWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 2);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(byte) 1, "error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_moreWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 2);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(byte) 1, "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_moreWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 2);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(byte) 1, "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_moreWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1f,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_moreWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1f,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_moreWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1f,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_moreWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1f,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_moreWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(2));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error");
	}

	@Test
	public void testRejectIfValueLessThan2_moreWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(2));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_moreWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(2));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_moreWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(2));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_moreWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(2));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error");
	}

	@Test
	public void testRejectIfValueLessThan2_moreWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(2));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_moreWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(2));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_moreWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(2));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_equalWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_equalWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_equalWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_equalWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_equalWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1d,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_equalWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1d,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_equalWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1d,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_equalWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1d,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_equalWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(short) 1, "error");
	}

	@Test
	public void testRejectIfValueLessThan2_equalWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(short) 1, "error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_equalWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(short) 1, "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_equalWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(short) 1, "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_equalWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1L,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_equalWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1L,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_equalWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1L,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_equalWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1L,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_equalWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(byte) 1, "error");
	}

	@Test
	public void testRejectIfValueLessThan2_equalWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(byte) 1, "error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_equalWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(byte) 1, "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_equalWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(byte) 1, "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_equalWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1f,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_equalWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1f,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_equalWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1f,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_equalWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1f,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_equalWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error");
	}

	@Test
	public void testRejectIfValueLessThan2_equalWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_equalWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_equalWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_equalWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error");
	}

	@Test
	public void testRejectIfValueLessThan2_equalWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_equalWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueLessThan4_equalWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_lessWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0);
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_lessWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0);
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_lessWithInteger() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(0);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueLessThan4_lessWithInteger() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(0);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_lessWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0d);
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1d,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_lessWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0d);
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1d,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_lessWithDouble() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(0d);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1d,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueLessThan4_lessWithDouble() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(0d);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1d,
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_lessWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 0);
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(short) 1, "error");
	}

	@Test
	public void testRejectIfValueLessThan2_lessWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 0);
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(short) 1, "error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_lessWithShort() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 0);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(short) 1, "error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueLessThan4_lessWithShort() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 0);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(short) 1, "error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_lessWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0L);
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1L,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_lessWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0L);
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1L,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_lessWithLong() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(0L);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1L,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueLessThan4_lessWithLong() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(0L);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1L,
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_lessWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 0);
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(byte) 1, "error");
	}

	@Test
	public void testRejectIfValueLessThan2_lessWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 0);
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(byte) 1, "error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_lessWithByte() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 0);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(byte) 1, "error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueLessThan4_lessWithByte() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 0);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				(byte) 1, "error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_lessWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0f);
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1f,
				"error");
	}

	@Test
	public void testRejectIfValueLessThan2_lessWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0f);
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1f,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_lessWithFloat() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(0f);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1f,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueLessThan4_lessWithFloat() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(0f);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo", 1f,
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_lessWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(0));
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error");
	}

	@Test
	public void testRejectIfValueLessThan2_lessWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(0));
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_lessWithBigInteger() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(0));
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueLessThan4_lessWithBigInteger() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(0));
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueLessThan1_lessWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(0));
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error");
	}

	@Test
	public void testRejectIfValueLessThan2_lessWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(0));
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", "Error!");
	}

	@Test
	public void testRejectIfValueLessThan3_lessWithBigDecimal() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(0));
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueLessThan4_lessWithBigDecimal() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(0));
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueLessThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", expectedErrorArgs, "Error!");
	}

	// rejectIfValueMoreThan

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueMoreThan1_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(null, "foo", 1, "error");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueMoreThan2_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(null, "foo", 1, "error",
				"Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueMoreThan3_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(null, "foo", 1, "error",
				new Object[] {});
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueMoreThan4_errorsNull() {
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(null, "foo", 1, "error",
				new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_valueNull() {
		expect(errorsMock.getFieldValue("foo")).andReturn(null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error", new Object[] {}, "Error!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueMoreThan1_inCompatibleValue() {
		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
					"error");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueMoreThan2_inCompatibleValue() {
		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
					"error", "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueMoreThan3_inCompatibleValue() {
		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
					"error", new Object[] {});
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRejectIfValueMoreThan4_inCompatibleValue() {
		expect(errorsMock.getFieldValue("foo")).andReturn("bar");
		replay(errorsMock);
		try {
			ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
					"error", new Object[] {}, "Error!");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getCause().getClass(), ClassCastException.class);
			throw e;
		}

	}

	@Test
	public void testRejectIfValueMoreThan1_lessWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_lessWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_lessWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_lessWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_lessWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1d,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_lessWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1d,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_lessWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1d,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_lessWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1d,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_lessWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 0);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(short) 1, "error");
	}

	@Test
	public void testRejectIfValueMoreThan2_lessWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 0);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(short) 1, "error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_lessWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 0);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(short) 1, "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_lessWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 0);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(short) 1, "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_lessWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1L,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_lessWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1L,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_lessWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1L,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_lessWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1L,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_lessWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 0);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(byte) 1, "error");
	}

	@Test
	public void testRejectIfValueMoreThan2_lessWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 0);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(byte) 1, "error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_lessWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 0);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(byte) 1, "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_lessWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 0);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(byte) 1, "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_lessWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1f,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_lessWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1f,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_lessWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1f,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_lessWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(0f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1f,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_lessWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(0));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error");
	}

	@Test
	public void testRejectIfValueMoreThan2_lessWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(0));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_lessWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(0));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_lessWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(0));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_lessWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(0));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error");
	}

	@Test
	public void testRejectIfValueMoreThan2_lessWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(0));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_lessWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(0));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_lessWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(0));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_equalWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_equalWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_equalWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_equalWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_equalWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1d,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_equalWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1d,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_equalWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1d,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_equalWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1d);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1d,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_equalWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(short) 1, "error");
	}

	@Test
	public void testRejectIfValueMoreThan2_equalWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(short) 1, "error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_equalWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(short) 1, "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_equalWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(short) 1, "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_equalWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1L,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_equalWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1L,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_equalWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1L,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_equalWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1L);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1L,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_equalWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(byte) 1, "error");
	}

	@Test
	public void testRejectIfValueMoreThan2_equalWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(byte) 1, "error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_equalWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(byte) 1, "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_equalWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 1);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(byte) 1, "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_equalWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1f,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_equalWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1f,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_equalWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1f,
				"error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_equalWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(1f);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1f,
				"error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_equalWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error");
	}

	@Test
	public void testRejectIfValueMoreThan2_equalWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_equalWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_equalWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_equalWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error");
	}

	@Test
	public void testRejectIfValueMoreThan2_equalWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_equalWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", new Object[] {});
	}

	@Test
	public void testRejectIfValueMoreThan4_equalWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(1));
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", new Object[] {}, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_moreWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2);
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_moreWithInteger() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2);
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_moreWithInteger() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(2);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueMoreThan4_moreWithInteger() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(2);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1,
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_moreWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2d);
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1d,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_moreWithDouble() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2d);
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1d,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_moreWithDouble() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(2d);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1d,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueMoreThan4_moreWithDouble() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(2d);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1d,
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_moreWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 2);
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(short) 1, "error");
	}

	@Test
	public void testRejectIfValueMoreThan2_moreWithShort() {
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 2);
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(short) 1, "error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_moreWithShort() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 2);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(short) 1, "error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueMoreThan4_moreWithShort() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn((short) 2);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(short) 1, "error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_moreWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2L);
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1L,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_moreWithLong() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2L);
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1L,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_moreWithLong() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(2L);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1L,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueMoreThan4_moreWithLong() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(2L);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1L,
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_moreWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 2);
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(byte) 1, "error");
	}

	@Test
	public void testRejectIfValueMoreThan2_moreWithByte() {
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 2);
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(byte) 1, "error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_moreWithByte() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 2);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(byte) 1, "error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueMoreThan4_moreWithByte() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn((byte) 2);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				(byte) 1, "error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_moreWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2f);
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1f,
				"error");
	}

	@Test
	public void testRejectIfValueMoreThan2_moreWithFloat() {
		expect(errorsMock.getFieldValue("foo")).andReturn(2f);
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1f,
				"error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_moreWithFloat() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(2f);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1f,
				"error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueMoreThan4_moreWithFloat() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo")).andReturn(2f);
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo", 1f,
				"error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_moreWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(2));
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error");
	}

	@Test
	public void testRejectIfValueMoreThan2_moreWithBigInteger() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(2));
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_moreWithBigInteger() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(2));
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueMoreThan4_moreWithBigInteger() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigInteger.valueOf(2));
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigInteger.valueOf(1), "error", expectedErrorArgs, "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan1_moreWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(2));
		errorsMock.rejectValue("foo", "error", null, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error");
	}

	@Test
	public void testRejectIfValueMoreThan2_moreWithBigDecimal() {
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(2));
		errorsMock.rejectValue("foo", "error", null, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", "Error!");
	}

	@Test
	public void testRejectIfValueMoreThan3_moreWithBigDecimal() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(2));
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, null);
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", expectedErrorArgs);
	}

	@Test
	public void testRejectIfValueMoreThan4_moreWithBigDecimal() {
		final Object[] expectedErrorArgs = new Object[] {};
		expect(errorsMock.getFieldValue("foo"))
				.andReturn(BigDecimal.valueOf(2));
		errorsMock.rejectValue("foo", "error", expectedErrorArgs, "Error!");
		replay(errorsMock);
		ExtendedValidationUtils.rejectIfValueMoreThan(errorsMock, "foo",
				BigDecimal.valueOf(1), "error", expectedErrorArgs, "Error!");
	}

}
