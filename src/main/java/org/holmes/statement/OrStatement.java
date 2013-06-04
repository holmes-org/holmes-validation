package org.holmes.statement;

import org.holmes.Statement;

/**
 * A disjunctive {@link Statement}. The evaluation is short-circuited; i.e., if
 * the first statement is evaluated as <code>true</code>, the second statement is never
 * evaluated.
 * 
 * @author diegossilveira
 */
public class OrStatement implements Statement {

	private final Statement firstStatement;

	private final Statement secondStatement;

	public OrStatement(Statement firstStatement, Statement secondStatement) {

		this.firstStatement = firstStatement;
		this.secondStatement = secondStatement;
	}

	@Override
	public boolean evaluate() {

		return firstStatement.evaluate() || secondStatement.evaluate();
	}
}
