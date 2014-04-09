package org.holmes;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class that holds the result of a validation.
 * 
 * @author diegossilveira
 */
public class ValidationResult {

	private final Set<String> violationsDescriptors;
	
	private final MessageResolver messageResolver;

	private ValidationResult(MessageResolver messageResolver) {

		violationsDescriptors = new LinkedHashSet<String>();
		this.messageResolver = messageResolver;
	}
	
	static ValidationResult init(MessageResolver messageResolver) {
		
		return new ValidationResult(messageResolver);
	}

	public void addViolationDescriptor(String violationDescriptor, Object... arguments) {

		if (violationDescriptor != null) {
			violationsDescriptors.add(messageResolver.resolveMessage(violationDescriptor, arguments));
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
