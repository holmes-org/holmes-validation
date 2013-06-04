package org.holmes.test;

import java.util.Arrays;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.holmes.exception.ValidationException;

class ValidationExceptionMatcher extends BaseMatcher<ValidationException> {

	private final String[] violationDescriptors;

	public ValidationExceptionMatcher(String... violationDescriptors) {

		this.violationDescriptors = violationDescriptors;
	}

	@Override
	public boolean matches(Object item) {

		if (!(item instanceof ValidationException)) {
			return false;
		}

		ValidationException ex = (ValidationException) item;

		boolean sizeMatches = CoreMatchers.is(violationDescriptors.length)
				.matches(ex.getViolationsDescriptors().size());
		boolean descMatches = CoreMatchers.hasItems(violationDescriptors).matches(ex.getViolationsDescriptors());
		
		return sizeMatches && descMatches;
	}

	@Override
	public void describeTo(Description description) {
		
		description.appendText("the descriptors: ");
		description.appendText(Arrays.toString(violationDescriptors));
	}

}
