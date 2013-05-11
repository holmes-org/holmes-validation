package org.holmes.evaluator;

import java.util.regex.Pattern;

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

				return pattern.matcher(target).matches();
			}

		}).getJoint();
	}
}
