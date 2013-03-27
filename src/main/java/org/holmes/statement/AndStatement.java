package org.holmes.statement;

import org.holmes.Statement;

/**
 * A conjunctive {@link Statement}. The evaluation is short-circuited; i.e., if
 * the first statement is evaluated as <code>false</code>, the second statement is never
 * evaluated.
 * 
 * @author diegossilveira
 */
public class AndStatement implements Statement {

	private final Statement firstStatement;

	private final Statement secondStatement;

	public AndStatement(Statement firstStatement, Statement secondStatement) {

		this.firstStatement = firstStatement;
		this.secondStatement = secondStatement;
	}

	public boolean evaluate() {

		return firstStatement.evaluate() && secondStatement.evaluate();
	}
}
