package org.holmes.evaluator;

import org.holmes.Evaluator;
import org.holmes.Joint;

/**
 * An {@link Evaluator} for the {@link Boolean} type.
 * 
 * @author diegossilveira
 */
public class BooleanEvaluator extends ObjectEvaluator<Boolean> {

	public BooleanEvaluator(Boolean target) {

		super(target);
	}

	/**
	 * Ensures that the {@link Boolean} target is not null and it's logical value is TRUE.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isTrue() {

		return setEvaluation(new Evaluation<Boolean>() {

			public boolean evaluate(Boolean target) {

				return target != null && target;
			}
			
		}).getJoint();
	}
	
	/**
	 * Ensures that the {@link Boolean} target is not null and it's logical value is FALSE.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isFalse() {
		
		return setEvaluation(new Evaluation<Boolean>() {

			public boolean evaluate(Boolean target) {

				return target != null && !target;
			}
			
		}).getJoint();
	}

}
