package org.holmes.collector;

import org.holmes.OperationMode;
import org.holmes.exception.RuleViolationException;
import org.holmes.exception.ValidationException;

/**
 * A {@link ResultCollector} related to the LAZY {@link OperationMode}.
 * 
 * @author diegossilveira
 */
public class LazyResultCollector implements ResultCollector {

	private final ValidationException validationException;
	
	public LazyResultCollector() {
		
		validationException = new ValidationException();
	}
	
	public void onRuleViolation(RuleViolationException e) throws ValidationException {
		
		validationException.addViolationDescriptor(e.getViolationDescriptor());
	}

	public void finish() throws ValidationException {
		
		if(validationException.hasViolations()) {
			
			throw validationException;
		}
	}

}
