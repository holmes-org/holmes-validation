package org.holmes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that holds the result of a validation.
 * 
 * @author diegossilveira
 */
public class ValidationResult {

	private final Set<String> violationsDescriptors;

	private ValidationResult() {

		violationsDescriptors = new HashSet<String>();
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

	public Set<String> getViolationsDescriptors() {

		return Collections.unmodifiableSet(violationsDescriptors);
	}
	
	@Override
	public String toString() {
		
		return violationsDescriptors.toString();
	}
	
}
