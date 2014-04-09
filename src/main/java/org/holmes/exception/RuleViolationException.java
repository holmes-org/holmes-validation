package org.holmes.exception;

/**
 * Class that represents a rule violation.
 * 
 * @author diegossilveira
 */
public class RuleViolationException extends RuntimeException {

	private static final long serialVersionUID = -6285497347762856549L;

	private final Object[] violationDescriptorArguments;

	public RuleViolationException(String violationDescriptor, Object... violationDescriptorArguments) {

		super(violationDescriptor);
		this.violationDescriptorArguments = violationDescriptorArguments;
	}

	public String getViolationDescriptor() {

		return getMessage();
	}

	public Object[] getViolationDescriptorArguments() {

		return violationDescriptorArguments;
	}
}
