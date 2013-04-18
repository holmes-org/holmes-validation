package org.holmes.evaluator;

import java.util.Date;

import org.holmes.Evaluator;
import org.holmes.evaluator.support.Diff;
import org.holmes.evaluator.support.FutureNumber;
import org.holmes.Joint;

/**
 * An {@link Evaluator} for the {@link Date} type.
 * 
 * @author diegossilveira
 */
public class DateEvaluator extends ObjectEvaluator<Date> {

	public DateEvaluator(Date target) {
		super(target);
	}

	/**
	 * Applies a diff between the target and a past or future date.
	 * 
	 * @param diff
	 *            The diff configuration.
	 * @return
	 */
	public NumberEvaluator applying(final Diff diff) {

		final FutureNumber futureNumber = new FutureNumber();
		final NumberEvaluator evaluator = new NumberEvaluator(futureNumber);

		setEvaluation(new Evaluation<Date>() {

			public boolean evaluate(Date target) {
				
				diff.setTarget(target);
				futureNumber.wrap(diff.calculate());

				return evaluator.evaluate();
			}
		});

		evaluator.setJoint(getJoint());
		return evaluator;
	}

	/**
	 * Ensures that the target is after than the argument date.
	 * 
	 * @param date
	 *            the date to compare the target to
	 * @return an instance of {@link Joint} class
	 */
	public Joint isAfterThan(final Date date) {

		return setEvaluation(new Evaluation<Date>() {

			public boolean evaluate(Date target) {

				return target != null && target.after(date);
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is before than the argument date.
	 * 
	 * @param date
	 *            the date to compare the target to
	 * @return an instance of {@link Joint} class
	 */
	public Joint isBeforeThan(final Date date) {

		return setEvaluation(new Evaluation<Date>() {

			public boolean evaluate(Date target) {

				return target != null && target.before(date);
			}

		}).getJoint();
	}
}
