package org.holmes.test;

import org.holmes.exception.ValidationException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ExpectedException implements TestRule {

	private final org.junit.rules.ExpectedException delegate = org.junit.rules.ExpectedException.none();

	private ExpectedException() {
	}

	public static ExpectedException none() {

		return new ExpectedException();
	}

	@Override
	public Statement apply(Statement base, Description description) {

		return delegate.apply(base, description);
	}

	public void expectValidationException(String... violationDescriptors) {

		delegate.expect(ValidationException.class);
		delegate.expect(new ValidationExceptionMatcher(violationDescriptors));
	}

}
