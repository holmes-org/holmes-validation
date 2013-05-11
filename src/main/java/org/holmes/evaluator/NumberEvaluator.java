package org.holmes.evaluator;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.holmes.Evaluator;
import org.holmes.Joint;
import org.holmes.evaluator.support.Interval;

/**
 * An {@link Evaluator} for the {@link Number} supertype.
 * 
 * @author diegossilveira
 */
public class NumberEvaluator extends ObjectEvaluator<Number> {

	private static final int MINUS_ONE = -1;

	private static final int ZERO = 0;

	private static final int ONE = 1;

	private static final BigInteger BIG_INT_TWO = BigInteger.valueOf(2);

	public NumberEvaluator(final Number target) {

		super(target);
	}
	
	/**
	 * Ensures that the target value is equal to (==) the argument number.
	 * 
	 * @param number
	 *            the number to compare the target to.
	 * @return an instance of {@link Joint} class
	 */
	@Override
	public Joint isEqualTo(final Number number) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return target != null && toBigDecimal(target).compareTo(toBigDecimal(number)) == ZERO;
			}

		}).getJoint();
	}
	
	/**
	 * Ensures that the target value is equal to (==) the argument number.
	 * 
	 * @param number
	 *            the number to compare the target to.
	 * @return an instance of {@link Joint} class
	 */
	@Override
	public Joint isNotEqualTo(final Number number) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return target != null && toBigDecimal(target).compareTo(toBigDecimal(number)) != ZERO;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is greater than (>) the argument number.
	 * 
	 * @param number
	 *            the number to compare the target to.
	 * @return an instance of {@link Joint} class
	 */
	public Joint isGreaterThan(final Number number) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return toBigDecimal(target).compareTo(toBigDecimal(number)) > ZERO;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is greater than or equal to (>=) the argument
	 * number.
	 * 
	 * @param number
	 *            the number to compare the target to.
	 * @return an instance of {@link Joint} class
	 */
	public Joint isGreaterThanOrEqualTo(final Number number) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return toBigDecimal(target).compareTo(toBigDecimal(number)) >= ZERO;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is less than (<) the argument number.
	 * 
	 * @param number
	 *            the number to compare the target to.
	 * @return an instance of {@link Joint} class
	 */
	public Joint isLessThan(final Number number) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return toBigDecimal(target).compareTo(toBigDecimal(number)) < ZERO;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is less than or equal to (<=) the argument
	 * number.
	 * 
	 * @param number
	 *            the number to compare the target to.
	 * @return an instance of {@link Joint} class
	 */
	public Joint isLessThanOrEqualTo(final Number number) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return toBigDecimal(target).compareTo(toBigDecimal(number)) <= ZERO;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is a positive number. Zero is not considered a
	 * positive number.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isPositive() {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return toBigDecimal(target).signum() == ONE;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is a negative number. Zero is not considered a
	 * negative number.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isNegative() {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return toBigDecimal(target).signum() == MINUS_ONE;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is a negative number or zero.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isNonPositive() {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return toBigDecimal(target).signum() <= ZERO;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is a positive number or zero.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isNonNegative() {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return toBigDecimal(target).signum() >= ZERO;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is an odd number. A number is odd if the
	 * remainder of its division by 2 is not 0.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isOdd() {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return !isMultiple(toBigInteger(target), BIG_INT_TWO);
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is an even number. A number is even if the
	 * remainder of its division by 2 is 0.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isEven() {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return isMultiple(toBigInteger(target), BIG_INT_TWO);
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target is a multiple of the argument. A number X is
	 * multiple of Y if X = N*Y for any integer N.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isMultipleOf(final Number number) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return isMultiple(toBigInteger(target), toBigInteger(number));
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
	public Joint belongsToInterval(final Number leftBoundary,
			final Number rightBoundary) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return Interval.closedInterval(toBigDecimal(leftBoundary),
						toBigDecimal(rightBoundary)).contains(
						toBigDecimal(target));
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
	public Joint belongsToLeftOpenInterval(final Number leftBoundary,
			final Number rightBoundary) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return Interval.leftOpenInterval(toBigDecimal(leftBoundary),
						toBigDecimal(rightBoundary)).contains(
						toBigDecimal(target));
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
	public Joint belongsToRightOpenInterval(final Number leftBoundary,
			final Number rightBoundary) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return Interval.rightOpenInterval(toBigDecimal(leftBoundary),
						toBigDecimal(rightBoundary)).contains(
						toBigDecimal(target));
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
	public Joint belongsToOpenInterval(final Number leftBoundary,
			final Number rightBoundary) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return Interval.openInterval(toBigDecimal(leftBoundary),
						toBigDecimal(rightBoundary)).contains(
						toBigDecimal(target));
			}

		}).getJoint();
	}

	private boolean isMultiple(BigInteger multiple, BigInteger multipleOf) {

		return multipleOf.intValue() != ZERO
				&& multiple.remainder(multipleOf).intValue() == ZERO;
	}

	private BigDecimal toBigDecimal(Number number) {

		if (number instanceof BigDecimal) {
			return (BigDecimal) number;
		}

		try {

			return number != null ? new BigDecimal(number.toString()) : null;

		} catch (NumberFormatException e) {

			return null;
		}
	}

	private BigInteger toBigInteger(Number number) {

		if (number instanceof BigInteger) {
			return (BigInteger) number;
		}

		try {

			return number != null ? new BigInteger(number.toString()) : null;

		} catch (NumberFormatException e) {

			return null;
		}
	}
}
