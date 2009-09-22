package org.beardedgeeks.spring.validation.annotations.empty;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import java.lang.reflect.Field;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidationUtils.class)
public class RequiredValidatorTest {

	@Test
	public void testGetAnnotationType() {
		assertEquals(Required.class, new RequiredValidator()
				.getAnnotationType());
	}

	@Test
	public void testValidate() throws SecurityException, NoSuchFieldException {
		final Errors errorsMock = createMock(Errors.class);
		final Field fieldMock = TestObject.class.getDeclaredField("foo"); 
		mockStatic(ValidationUtils.class);
		ValidationUtils.rejectIfEmpty(errorsMock, "foo", "error");
		expectLastCall();
		replayAll();
		new RequiredValidator().validate(fieldMock, errorsMock, "error");
		verifyAll();
	}

	private static final class TestObject {
		@SuppressWarnings("unused")
		private String foo;
	}

}
