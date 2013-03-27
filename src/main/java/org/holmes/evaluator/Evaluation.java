package org.holmes.evaluator;

/**
 * This class represents the deferred evaluation process through a concrete
 * implementation of {@link #evaluate(Object)} method.
 * 
 * @author diegossilveira
 * 
 * @param <T>
 *            the type this Evaluation applies to.
 */
public interface Evaluation<T> {

	/**
	 * Evaluates the target by some given rule.
	 * 
	 * @param target
	 * @return <code>true</code> if the target conforms to the rules,
	 *         <code>false</code> otherwise.
	 */
	boolean evaluate(T target);

}
