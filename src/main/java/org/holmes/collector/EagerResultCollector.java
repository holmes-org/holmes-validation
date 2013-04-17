package org.holmes.collector;

import org.holmes.OperationMode;
import org.holmes.exception.RuleViolationException;
import org.holmes.exception.ValidationException;

/**
 * A {@link ResultCollector} related to the EAGER {@link OperationMode}.
 * 
 * @author diegossilveira
 */
public class EagerResultCollector implements ResultCollector {

	public void onRuleViolation(RuleViolationException e) throws ValidationException {
		
		ValidationException validationException = new ValidationException();
		validationException.addViolationDescriptor(e.getViolationDescriptor());
		
		throw validationException;
	}

	public void finish() throws ValidationException {
		// Nothing to do
	}
}
