package org.holmes.evaluator.support;

import java.util.Date;

/**
 * Represents a date diff.
 * 
 * @author diegossilveira
 */
public class Diff {

	private Date target;

	private Date fromDate;

	private Date toDate;

	private TimeUnit timeUnit;

	private Diff() {
	}

	/**
	 * Creates a Diff instance that begins on parameter <code>date</code> and
	 * ends on target date.
	 * 
	 * @param date
	 *            the beginning of the diff.
	 * @return a Diff instance
	 */
	public static Diff from(Date date) {

		Diff diff = new Diff();
		diff.fromDate = date;

		return diff;
	}

	/**
	 * Creates a Diff instance that begins now and ends on target date.
	 * 
	 * @return a Diff instance
	 */
	public static Diff fromNow() {

		return from(new Date());
	}

	/**
	 * Creates a Diff instance that begins on target date and ends on parameter
	 * <code>date</code>.
	 * 
	 * @param date
	 *            the ending of the diff.
	 * @return a Diff instance
	 */
	public static Diff to(Date date) {

		Diff diff = new Diff();
		diff.toDate = date;

		return diff;
	}

	/**
	 * Creates a Diff instance that begins on target date and ends now.
	 * 
	 * @return a Diff instance
	 */
	public static Diff toNow() {

		return to(new Date());
	}

	/**
	 * Returns a Diff that represents the difference between dates in
	 * milliseconds.
	 * 
	 * @return a Diff instance
	 */
	public Diff inMilliseconds() {

		timeUnit = TimeUnit.MILLISECOND;
		return this;
	}

	/**
	 * Returns a Diff that represents the difference between dates in seconds.
	 * 
	 * @return a Diff instance
	 */
	public Diff inSeconds() {

		timeUnit = TimeUnit.SECOND;
		return this;
	}

	/**
	 * Returns a Diff that represents the difference between dates in minutes.
	 * 
	 * @return a Diff instance
	 */
	public Diff inMinutes() {

		timeUnit = TimeUnit.MINUTE;
		return this;
	}

	/**
	 * Returns a Diff that represents the difference between dates in hours.
	 * 
	 * @return a Diff instance
	 */
	public Diff inHours() {

		timeUnit = TimeUnit.HOUR;
		return this;
	}

	/**
	 * Returns a Diff that represents the difference between dates in days.
	 * 
	 * @return a Diff instance
	 */
	public Diff inDays() {

		timeUnit = TimeUnit.DAY;
		return this;
	}

	/**
	 * Returns a Diff that represents the difference between dates in months. A
	 * month is considered as a 30-day period.
	 * 
	 * @return a Diff instance
	 */
	public Diff inMonths() {

		timeUnit = TimeUnit.MONTH;
		return this;
	}

	/**
	 * Returns a Diff that represents the difference between dates in years. A
	 * year is considered as a 365 days, 5 hours, 48 minutes and 48 seconds
	 * period.
	 * 
	 * @return a Diff instance
	 */
	public Diff inYears() {

		timeUnit = TimeUnit.YEAR;
		return this;
	}

	public void setTarget(Date target) {

		this.target = target;
	}

	public Number calculate() {

		selfCheck();
		return timeUnit.fromMillis(getDiff());
	}

	private void selfCheck() {

		if (target == null) {

			throw new IllegalArgumentException("target date must be set.");
		}

		if (fromDate == null && toDate == null) {

			throw new IllegalArgumentException(
					"either beginning date or ending date must be set.");
		}

		if (timeUnit == null) {

			throw new IllegalArgumentException(
					"time unit must be specified: Diff.[to|toNow|from|fromNow]().in[Milliseconds|Seconds|Minutes|Hours|Days|Months|Years]()");
		}
	}

	private long getDiff() {

		if (fromDate != null) {

			return target.getTime() - fromDate.getTime();
		}

		return toDate.getTime() - target.getTime();
	}

}
