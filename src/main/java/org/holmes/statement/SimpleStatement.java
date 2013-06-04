package org.holmes.statement;

import org.holmes.Evaluator;
import org.holmes.Statement;

/**
 * A simple {@link Statement}, whose evaluation result is delegated to an
 * {@link Evaluator}.
 * 
 * @author diegossilveira
 */
public class SimpleStatement implements Statement {

	private final Evaluator<?> evaluator;

	public SimpleStatement(Evaluator<?> evaluator) {

		this.evaluator = evaluator;
	}

	@Override
	public boolean evaluate() {

		return evaluator.evaluate();
	}
}
