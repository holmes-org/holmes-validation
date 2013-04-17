package org.holmes.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that represents a validation case exception.
 * 
 * @author diegossilveira
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 3422556211439163240L;

	private final List<String> violationsDescriptors;

	public ValidationException() {

		violationsDescriptors = new ArrayList<String>();
	}

	public void addViolationDescriptor(String violationDescriptor) {

		if (violationDescriptor != null) {
			violationsDescriptors.add(violationDescriptor);
		}
	}

	public boolean hasViolations() {

		return !violationsDescriptors.isEmpty();
	}

	public List<String> getViolationsDescriptors() {

		return Collections.unmodifiableList(violationsDescriptors);
	}
	
	@Override
	public String toString() {
		
		return violationsDescriptors.toString();
	}
}
