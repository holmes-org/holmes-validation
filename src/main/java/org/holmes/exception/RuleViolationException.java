package org.holmes.exception;

/**
 * Class that represents a {@link Rule} violation.
 * 
 * @author diegossilveira
 */
public class RuleViolationException extends RuntimeException {

	private static final long serialVersionUID = -6285497347762856549L;

	public RuleViolationException(String violationDescriptor) {

		super(violationDescriptor);
	}

	public String getViolationDescriptor() {

		return getMessage();
	}
}
