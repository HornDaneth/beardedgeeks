package org.beardedgeeks.spring.validation.annotations.matches;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import java.lang.reflect.Field;

import org.beardedgeeks.spring.validation.annotations.ExtendedValidationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.validation.Errors;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ExtendedValidationUtils.class)
public class MatchesPatternValidatorTest {

	@Test
	public void testGetAnnotationType() {
		assertEquals(MatchesPattern.class, new MatchesPatternValidator()
				.getAnnotationType());
	}

	@Test
	public void testValidate() throws SecurityException, NoSuchFieldException {
		final Errors errorsMock = createMock(Errors.class);
		final Field fieldMock = TestObject.class.getDeclaredField("foo");
		mockStatic(ExtendedValidationUtils.class);
		ExtendedValidationUtils.rejectIfNotMatches(errorsMock, "foo", "test",
				"error");
		expectLastCall();
		replayAll();
		new MatchesPatternValidator().validate(fieldMock, errorsMock, "error");
		verifyAll();
	}

	private static final class TestObject {
		@SuppressWarnings("unused")
		@MatchesPattern("test")
		private String foo;
	}

}
