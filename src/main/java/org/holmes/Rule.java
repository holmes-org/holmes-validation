package org.holmes;

import org.holmes.exception.RuleViolationException;
import org.holmes.statement.AndStatement;
import org.holmes.statement.OrStatement;
import org.holmes.statement.SimpleStatement;

/**
 * A rule represents a complete validation case.
 * 
 * @author diegossilveira
 */
class Rule {

	private Statement statement;

	private String violationDescriptor;

	private Object[] violationDescriptorArguments;

	private Rule(Statement statement) {

		this.statement = statement;
	}

	/**
	 * Initializes a rule for an {@link Evaluator}.
	 * 
	 * @param evaluator
	 * @return
	 */
	static Rule simpleFor(Evaluator<?> evaluator) {

		return new Rule(new SimpleStatement(evaluator));
	}

	/**
	 * Transforms it's root statement into a disjunctive statement, which will be the new root statement of this rule. The disjunctive
	 * statement is made up of the current root statement (highest priority) and the given {@link Statement} (lowest priority).
	 * 
	 * @param statement
	 *            the disjunctive statement.
	 */
	void addOrStatement(Statement statement) {

		this.statement = new OrStatement(this.statement, statement);
	}

	/**
	 * Transforms it's root statement into a conjunctive statement, which will be the new root statement of this rule. The conjunctive
	 * statement is made up of the current root statement (highest priority) and the given {@link Statement} (lowest priority).
	 * 
	 * @param statement
	 *            the disjunctive statement.
	 */
	void addAndStatement(Statement statement) {

		this.statement = new AndStatement(this.statement, statement);
	}

	/**
	 * Specifies a descriptor used to represent a violation to the {@link Rule}. A rule is considered violated if the result of its root
	 * statement if <code>false</code>.
	 * 
	 * @param violationDescriptor
	 *            the violation descriptor.
	 * @param arguments
	 *            arguments to the violation descriptor.
	 */
	void setViolationDescriptor(String violationDescriptor, Object... arguments) {

		this.violationDescriptor = violationDescriptor;
		this.violationDescriptorArguments = arguments;
	}

	/**
	 * Checks if a violation descriptor is set.
	 * 
	 * @return <code>true</code> if there's a violation descriptor set for this rule, <code>false</code> otherwise.
	 */
	boolean hasViolationDescriptor() {

		return violationDescriptor != null && !violationDescriptor.isEmpty();
	}

	/**
	 * Evaluates this rule. If the rule is violated an exception is thrown.
	 * 
	 * @throws RuleViolationException
	 *             when the rule is violated.
	 */
	void evaluate() throws RuleViolationException {

		if (!statement.evaluate()) {
			throw new RuleViolationException(violationDescriptor, violationDescriptorArguments);
		}
	}
}
