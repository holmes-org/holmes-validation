package org.holmes;

/**
 * An evaluator is the last piece in the validation chain. To evaluate a target
 * instance by some specific logic is it's responsability.
 * 
 * @author diegossilveira
 * 
 * @param <T>
 *            the type evaluated by this Evaluator.
 */
public interface Evaluator<T> {

	/**
	 * Used to set the {@link Joint} on this Evaluator.
	 * 
	 * @param joint
	 */
	void setJoint(Joint joint);

	/**
	 * Evaluates the target by specific logic.
	 * 
	 * @return <code>true</code> if the target conforms to the logic,
	 *         <code>false</code> otherwise.
	 */
	boolean evaluate();

}
