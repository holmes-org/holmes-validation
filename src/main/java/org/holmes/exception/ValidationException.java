package org.holmes.exception;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a validation case exception.
 * 
 * @author diegossilveira
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 3422556211439163240L;

	private final Set<String> violationsDescriptors;

	public ValidationException(Collection<String> violationsDescriptors) {

		this.violationsDescriptors = new HashSet<String>(violationsDescriptors);
	}

	public Set<String> getViolationsDescriptors() {

		return violationsDescriptors;
	}
	
	@Override
	public String getMessage() {
		
		return this.toString();
	}

	@Override
	public String toString() {

		return violationsDescriptors.toString();
	}
}
