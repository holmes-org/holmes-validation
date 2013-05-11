package org.holmes.evaluator;

import java.util.Date;

import org.holmes.Evaluator;
import org.holmes.Joint;
import org.holmes.evaluator.support.Diff;
import org.holmes.evaluator.support.FutureNumber;
import org.holmes.evaluator.support.Interval;

/**
 * An {@link Evaluator} for the {@link Date} type.
 * 
 * @author diegossilveira
 */
public class DateEvaluator extends ObjectEvaluator<Date> {

	private static final int ZERO = 0;

	public DateEvaluator(Date target) {
		super(target);
	}

	/**
	 * Applies a diff between the target and a past or future date.
	 * 
	 * @param diff
	 *            The diff configuration.
	 * @return {@link NumberEvaluator}
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

				return target.compareTo(date) > ZERO;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is after than or equal to the argument date.
	 * 
	 * @param date
	 *            the date to compare the target to
	 * @return an instance of {@link Joint} class
	 */
	public Joint isAfterThanOrEqualTo(final Date date) {

		return setEvaluation(new Evaluation<Date>() {

			public boolean evaluate(Date target) {

				return target.compareTo(date) >= ZERO;
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

				return target.compareTo(date) < ZERO;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is before than or equal to the argument date.
	 * 
	 * @param date
	 *            the date to compare the target to
	 * @return an instance of {@link Joint} class
	 */
	public Joint isBeforeThanOrEqualTo(final Date date) {

		return setEvaluation(new Evaluation<Date>() {

			public boolean evaluate(Date target) {

				return target.compareTo(date) <= ZERO;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target belongs to the closed interval [leftBoundary,
	 * rightBoundary].
	 * 
	 * @param leftBoundary
	 *            the left boundary, inclusive.
	 * @param rightBoundary
	 *            the right boundary, inclusive.
	 * @return an instance of {@link Joint} class
	 */
	public Joint belongsToInterval(final Date leftBoundary,
			final Date rightBoundary) {

		return setEvaluation(new Evaluation<Date>() {

			public boolean evaluate(Date target) {

				return Interval.closedInterval(leftBoundary, rightBoundary)
						.contains(target);
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target belongs to the left-open interval (leftBoundary,
	 * rightBoundary].
	 * 
	 * @param leftBoundary
	 *            the left boundary, exclusive.
	 * @param rightBoundary
	 *            the right boundary, inclusive.
	 * @return an instance of {@link Joint} class
	 */
	public Joint belongsToLeftOpenInterval(final Date leftBoundary,
			final Date rightBoundary) {

		return setEvaluation(new Evaluation<Date>() {

			public boolean evaluate(Date target) {

				return Interval.leftOpenInterval(leftBoundary, rightBoundary)
						.contains(target);
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target belongs to the right-open interval [leftBoundary,
	 * rightBoundary).
	 * 
	 * @param leftBoundary
	 *            the left boundary, inclusive.
	 * @param rightBoundary
	 *            the right boundary, exclusive.
	 * @return an instance of {@link Joint} class
	 */
	public Joint belongsToRightOpenInterval(final Date leftBoundary,
			final Date rightBoundary) {

		return setEvaluation(new Evaluation<Date>() {

			public boolean evaluate(Date target) {

				return Interval.rightOpenInterval(leftBoundary, rightBoundary)
						.contains(target);
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target belongs to the open interval (leftBoundary,
	 * rightBoundary).
	 * 
	 * @param leftBoundary
	 *            the left boundary, exclusive.
	 * @param rightBoundary
	 *            the right boundary, exclusive.
	 * @return an instance of {@link Joint} class
	 */
	public Joint belongsToOpenInterval(final Date leftBoundary,
			final Date rightBoundary) {

		return setEvaluation(new Evaluation<Date>() {

			public boolean evaluate(Date target) {

				return Interval.openInterval(leftBoundary, rightBoundary)
						.contains(target);
			}

		}).getJoint();
	}
}
