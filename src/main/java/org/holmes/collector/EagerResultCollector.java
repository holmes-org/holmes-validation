package org.holmes.collector;

import org.holmes.OperationMode;
import org.holmes.ResultCollector;
import org.holmes.ValidationResult;
import org.holmes.exception.RuleViolationException;
import org.holmes.exception.ValidationException;

/**
 * A {@link ResultCollector} related to the EAGER {@link OperationMode}.
 * 
 * @author diegossilveira
 */
public class EagerResultCollector implements ResultCollector {

	public void onRuleViolation(RuleViolationException e, ValidationResult result) throws ValidationException {
		
		result.addViolationDescriptor(e.getViolationDescriptor());
		throw new ValidationException(result.getViolationsDescriptors());
	}

	public void finish(ValidationResult result) throws ValidationException {
		// Nothing to do
	}
}
