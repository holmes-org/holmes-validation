package org.holmes.evaluator;

import java.util.regex.Pattern;

import org.holmes.Evaluator;
import org.holmes.Joint;
import org.holmes.evaluator.support.FutureNumber;

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
	 * Ensures that the target does not contains characters.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isEmpty() {

		return setEvaluation(new Evaluation<String>() {

			public boolean evaluate(String target) {

				return target == null || target.trim().isEmpty();
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target contains characters.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isNotEmpty() {

		return setEvaluation(new Evaluation<String>() {

			public boolean evaluate(String target) {

				return !target.trim().isEmpty();
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target matches the pattern.
	 * 
	 * @param pattern
	 *            the compiled regex to validate the target
	 * @return an instance of {@link Joint} class
	 */
	public Joint matches(final Pattern pattern) {

		return setEvaluation(new Evaluation<String>() {

			public boolean evaluate(String target) {

<<<<<<< HEAD
				return pattern != null && target != null && pattern.matcher(target).matches();
=======
				return pattern.matcher(target).matches();
>>>>>>> date-evaluator
			}

		}).getJoint();
	}

	/**
	 * Gets the {@link String} length.
	 * 
	 * @param string
	 * @return {@link NumberEvaluator}
	 */
	public NumberEvaluator length() {

		final FutureNumber futureNumber = new FutureNumber();
		final NumberEvaluator evaluator = new NumberEvaluator(futureNumber);

		setEvaluation(new Evaluation<String>() {

			public boolean evaluate(String target) {
				if (target == null) {
					return false;
				}
				futureNumber.wrap(target.length());
				return evaluator.evaluate();
			}
		});

		evaluator.setJoint(getJoint());
		return evaluator;
	}
}
