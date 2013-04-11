package org.holmes.evaluator;

import org.holmes.Evaluator;
import org.holmes.Joint;

/**
 * An {@link Evaluator} for the {@link String} type.
 * 
 * @author arthuralmeida
 */
public class StringEvaluator extends ObjectEvaluator<String> {

	public StringEvaluator(String target) {
		super(target);
	}

	/**
	 * Ensures that the target contains text.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isNotEmpty() {
		return setEvaluation(new Evaluation<String>() {

			public boolean evaluate(String target) {
				return target != null && !target.trim().isEmpty();
			}
		}).getJoint();
	}
}
