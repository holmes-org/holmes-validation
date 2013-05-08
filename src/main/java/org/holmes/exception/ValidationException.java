package org.holmes.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class that represents a validation case exception.
 * 
 * @author diegossilveira
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 3422556211439163240L;

	private final List<String> violationsDescriptors;

	public ValidationException(Collection<String> violationsDescriptors) {

		this.violationsDescriptors = new ArrayList<>(violationsDescriptors);
	}

	public List<String> getViolationsDescriptors() {

		return violationsDescriptors;
	}

	@Override
	public String toString() {

		return violationsDescriptors.toString();
	}
}
