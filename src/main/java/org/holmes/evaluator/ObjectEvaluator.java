package org.holmes.evaluator;

import org.holmes.Evaluator;
import org.holmes.Joint;
import org.holmes.Validator;

/**
 * An {@link Evaluator} for the {@link Object} type.
 * 
 * @author diegossilveira
 */
public class ObjectEvaluator<T> extends AbstractEvaluator<T> {

	public ObjectEvaluator(T target) {

		super(target);
	}

	/**
	 * Ensures that the target is null.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isNull() {

		return setEvaluation(new Evaluation<T>() {

			public boolean evaluate(T target) {

				return target == null;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is not null.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isNotNull() {

		return setEvaluation(new Evaluation<T>() {

			public boolean evaluate(T target) {

				return target != null;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is equals to the <code>other</code>.
	 * 
	 * @param other
	 *            the object to compare the target to
	 * @return an instance of {@link Joint} class
	 */
	public Joint isEqualTo(final T other) {

		return setEvaluation(new Evaluation<T>() {

			public boolean evaluate(T target) {

				return (target == null && other == null) || (target != null && target.equals(other));
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is not equals to the <code>other</code>.
	 * 
	 * @param other
	 *            the object to compare the target to
	 * @return an instance of {@link Joint} class
	 */
	public Joint isNotEqualTo(final T other) {

		return setEvaluation(new Evaluation<T>() {

			public boolean evaluate(T target) {

				return (target == null && other != null) || (target != null && !target.equals(other));
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is valid accordingly to the {@link Validator}.
	 * 
	 * @param validator
	 *            the custom validator.
	 * @return an instance of {@link Joint} class
	 */
	public Joint isValidBy(final Validator<T> validator) {

		return setEvaluation(new Evaluation<T>() {

			public boolean evaluate(T target) {

				return validator.isValid(target);
			}

		}).getJoint();
	}
}
