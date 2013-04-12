package org.holmes.evaluator;

import java.util.Date;

import org.holmes.Evaluator;
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
