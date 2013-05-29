package org.holmes.collector;

import org.holmes.ValidationResult;
import org.holmes.exception.RuleViolationException;
import org.holmes.exception.ValidationException;

/**
 * ResultCollectors are notified whenever a rule evaluation fails.
 * 
 * @author diegossilveira
 */
public interface ResultCollector {

	void onRuleViolation(RuleViolationException e, ValidationResult result) throws ValidationException;

	void finish(ValidationResult result) throws ValidationException;
}
