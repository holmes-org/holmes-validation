package org.holmes.evaluator;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.holmes.Joint;

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
	 * Ensures that the target is greater than (>) the argument number.
	 * 
	 * @param number
	 *            the number to compare the target to.
	 * @return an instance of {@link Joint} class
	 */
	public Joint isGreaterThan(final Number number) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return target != null && toBigDecimal(target).compareTo(toBigDecimal(number)) > ZERO;
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
	public Joint isGreterThanOrEqualTo(final Number number) {

		return setEvaluation(new Evaluation<Number>() {

			public boolean evaluate(Number target) {

				return target != null && toBigDecimal(target).compareTo(toBigDecimal(number)) >= ZERO;
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

				return target != null && toBigDecimal(target).compareTo(toBigDecimal(number)) < ZERO;
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

				return target != null && toBigDecimal(target).compareTo(toBigDecimal(number)) <= ZERO;
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

				return target != null && toBigDecimal(target).signum() == ONE;
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

				return target != null && toBigDecimal(target).signum() == MINUS_ONE;
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

				return target != null && toBigDecimal(target).signum() >= ZERO;
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

				return target != null && !isMultiple(toBigInteger(target), BIG_INT_TWO);
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

				return target != null && isMultiple(toBigInteger(target), BIG_INT_TWO);
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

				return target != null && isMultiple(toBigInteger(target), toBigInteger(number));
			}

		}).getJoint();
	}

	private boolean isMultiple(BigInteger multiple, BigInteger multipleOf) {

		return multipleOf.intValue() != ZERO && multiple.remainder(multipleOf).intValue() == ZERO;
	}

	private BigDecimal toBigDecimal(Number number) {

		if (number instanceof BigDecimal) {
			return (BigDecimal) number;
		}

		return number != null ? new BigDecimal(number.toString()) : null;
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
