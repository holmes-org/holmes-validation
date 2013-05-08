package org.holmes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that holds the result of a validation.
 * 
 * @author diegossilveira
 */
public class ValidationResult {

	private final List<String> violationsDescriptors;

	private ValidationResult() {

		violationsDescriptors = new ArrayList<String>();
	}
	
	static ValidationResult init() {
		
		return new ValidationResult();
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
