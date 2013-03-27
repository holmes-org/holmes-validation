package org.holmes;

/**
 * Statements are the multiple components of a {@link Rule}. There are complex
 * statements, which have other statements; and there are simple statements,
 * which have a single {@link Evaluator}.
 * 
 * @author diegossilveira
 */
public interface Statement {

	/**
	 * Evaluates the statement. A statement evaluation can be delegated to its
	 * inner statements or even to the related evaluator.
	 * 
	 * @return <code>true</code> if the statement conforms to the logic,
	 *         <code>false</code> otherwise.
	 */
	boolean evaluate();

}
