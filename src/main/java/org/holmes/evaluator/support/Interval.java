package org.holmes.evaluator.support;

/**
 * Represents a interval, between left boundary and right boundary.
 * 
 * @author diegossilveira
 */
public final class Interval<T extends Comparable<T>> {

	private final T leftBoundary;

	private final T rightBoundary;

	private final boolean leftOpen;

	private final boolean rightOpen;

	private Interval(T leftBoundary, T rightBoundary, boolean leftOpen, boolean rightOpen) {

		this.leftBoundary = leftBoundary;
		this.rightBoundary = rightBoundary;
		this.leftOpen = leftOpen;
		this.rightOpen = rightOpen;
	}

	/**
	 * Creates a closed interval.
	 * 
	 * @param leftBoundary
	 * @param rightBoundary
	 * @return the configured {@link Interval} instance.
	 */
	public static <T extends Comparable<T>> Interval<T> closedInterval(T leftBoundary, T rightBoundary) {

		return new Interval<T>(leftBoundary, rightBoundary, false, false);
	}

	/**
	 * Creates a left-open interval.
	 * 
	 * @param leftBoundary
	 * @param rightBoundary
	 * @return the configured {@link Interval} instance.
	 */
	public static <T extends Comparable<T>> Interval<T> leftOpenInterval(T leftBoundary, T rightBoundary) {

		return new Interval<T>(leftBoundary, rightBoundary, true, false);
	}

	/**
	 * Creates a right-open interval.
	 * 
	 * @param leftBoundary
	 * @param rightBoundary
	 * @return the configured {@link Interval} instance.
	 */
	public static <T extends Comparable<T>> Interval<T> rightOpenInterval(T leftBoundary, T rightBoundary) {

		return new Interval<T>(leftBoundary, rightBoundary, false, true);
	}

	/**
	 * Creates an open interval.
	 * 
	 * @param leftBoundary
	 * @param rightBoundary
	 * @return the configured {@link Interval} instance.
	 */
	public static <T extends Comparable<T>> Interval<T> openInterval(T leftBoundary, T rightBoundary) {

		return new Interval<T>(leftBoundary, rightBoundary, true, true);
	}

	/**
	 * Checks if this interval contains the element.
	 * 
	 * @param element
	 * @return <code>true</code> if the interval contains the element,
	 *         <code>otherwise</code>.
	 */
	public boolean contains(T element) {

		selfCheck();
		return analyzeLeftBoundary(element) && analyzeRightBoundary(element);
	}

	private void selfCheck() {

		if (leftBoundary == null && rightBoundary == null) {

			throw new IllegalArgumentException("both leftBoundary and rightBoundary must been set.");
		}
	}

	private boolean analyzeLeftBoundary(T element) {

		if (leftOpen) {
			return element.compareTo(leftBoundary) > 0;
		}

		return element.compareTo(leftBoundary) >= 0;
	}

	private boolean analyzeRightBoundary(T element) {

		if (rightOpen) {
			return element.compareTo(rightBoundary) < 0;
		}

		return element.compareTo(rightBoundary) <= 0;
	}
}
