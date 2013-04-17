package org.holmes.collector;

import org.holmes.exception.RuleViolationException;
import org.holmes.exception.ValidationException;

/**
 * ResultCollectors are notified whenever a {@link Rule} evaluation fails.
 * 
 * @author diegossilveira
 */
public interface ResultCollector {

	void onRuleViolation(RuleViolationException e) throws ValidationException;

	void finish() throws ValidationException;
}
