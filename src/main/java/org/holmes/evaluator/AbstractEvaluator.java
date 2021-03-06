package org.holmes.evaluator;

import org.holmes.Evaluator;
import org.holmes.Joint;

/**
 * A convenience implementation for {@link Evaluator} subclasses.
 * 
 * @author diegossilveira
 * 
 * @param <T>
 *            the type evaluated by this Evaluator.
 */
abstract class AbstractEvaluator<T> implements Evaluator<T> {

	private final T target;

	private Joint joint;

	private Evaluation<T> evaluation;

	AbstractEvaluator(T target) {

		this.target = target;
	}

	Joint getJoint() {

		return joint;
	}

	@Override
	public void setJoint(Joint joint) {

		this.joint = joint;
	}

	AbstractEvaluator<T> setEvaluation(Evaluation<T> evaluation) {

		this.evaluation = evaluation;
		return this;
	}

	@Override
	public boolean evaluate() {

		try {
			return evaluation.evaluate(target);
		} catch (NullPointerException e) {
			return false;
		}
	}
}
