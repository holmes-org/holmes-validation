package org.holmes.evaluator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.holmes.evaluator.support.Diff;
import org.junit.Before;
import org.junit.Test;

public class DateEvaluatorTest {

	private DateEvaluator evaluator;

	private Date lastYear;

	private Date nextYear;

	@Before
	public void defineLastYear() {

		lastYear = addYearsFromNow(-1);
	}

	@Before
	public void defineNextYear() {

		nextYear = addYearsFromNow(1);
	}

	@Test
	public void testSuccessfulApplyingEval() {

		evaluator = new DateEvaluator(lastYear);

		evaluator.applying(Diff.toNow().inYears()).isLessThanOrEqualTo(1);
		assertTrue(evaluator.evaluate());

		evaluator.applying(Diff.toNow().inMonths()).isGreaterThan(11);
		assertTrue(evaluator.evaluate());

		evaluator.applying(Diff.toNow().inDays()).isGreaterThan(364);
		assertTrue(evaluator.evaluate());

		evaluator.applying(Diff.toNow().inHours()).isGreaterThan(8759);
		assertTrue(evaluator.evaluate());

		evaluator.applying(Diff.toNow().inMinutes()).isGreaterThan(525599);
		assertTrue(evaluator.evaluate());

		evaluator.applying(Diff.toNow().inSeconds()).isGreaterThan(31535000);
		assertTrue(evaluator.evaluate());

		evaluator.applying(Diff.toNow().inMilliseconds()).isGreaterThan(31535000000L);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulApplyingEval() {

		evaluator = new DateEvaluator(lastYear);

		evaluator.applying(Diff.toNow().inYears()).isGreaterThan(1);
		assertFalse(evaluator.evaluate());

		evaluator.applying(Diff.toNow().inMonths()).isLessThan(12);
		assertFalse(evaluator.evaluate());

		evaluator.applying(Diff.toNow().inDays()).isLessThan(365);
		assertFalse(evaluator.evaluate());

		evaluator.applying(Diff.toNow().inHours()).isLessThan(8760);
		assertFalse(evaluator.evaluate());

		evaluator.applying(Diff.toNow().inMinutes()).isLessThan(525600);
		assertFalse(evaluator.evaluate());

		evaluator.applying(Diff.toNow().inSeconds()).isLessThan(31536000);
		assertFalse(evaluator.evaluate());

		evaluator.applying(Diff.toNow().inMilliseconds()).isLessThan(31536000000L);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(null);
		evaluator.applying(Diff.toNow().inYears()).isLessThanOrEqualTo(1);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulIsPastEval() {

		evaluator = new DateEvaluator(lastYear);
		
		evaluator.isPast();
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulIsPastEval() {

		evaluator = new DateEvaluator(nextYear);

		evaluator.isPast();
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulIsFutureEval() {

		evaluator = new DateEvaluator(nextYear);
		
		evaluator.isFuture();
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulIsFutureEval() {

		evaluator = new DateEvaluator(lastYear);

		evaluator.isFuture();
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulIsAfterThanEval() {

		evaluator = new DateEvaluator(new Date());
		
		evaluator.isAfterThan(lastYear);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulIsAfterThanEval() {

		evaluator = new DateEvaluator(lastYear);

		evaluator.isAfterThan(new Date());
		assertFalse(evaluator.evaluate());

		evaluator.isAfterThan(lastYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(null);
		evaluator.isAfterThan(lastYear);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulIsAfterThanOrEqualTo() {

		evaluator = new DateEvaluator(new Date());
		evaluator.isAfterThanOrEqualTo(lastYear);
		assertTrue(evaluator.evaluate());

		evaluator = new DateEvaluator(lastYear);
		evaluator.isAfterThanOrEqualTo(lastYear);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulIsAfterThanOrEqualTo() {

		evaluator = new DateEvaluator(lastYear);
		evaluator.isAfterThanOrEqualTo(new Date());
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(null);
		evaluator.isAfterThanOrEqualTo(new Date());
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulIsBeforeThanEval() {

		evaluator = new DateEvaluator(lastYear);
		evaluator.isBeforeThan(new Date());
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulIsBeforeThanEval() {

		evaluator = new DateEvaluator(new Date());
		evaluator.isBeforeThan(lastYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(lastYear);
		evaluator.isBeforeThan(lastYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(null);
		evaluator.isBeforeThan(lastYear);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulIsBeforeThanOrEqualTo() {

		evaluator = new DateEvaluator(lastYear);
		evaluator.isBeforeThanOrEqualTo(new Date());
		assertTrue(evaluator.evaluate());

		evaluator = new DateEvaluator(lastYear);
		evaluator.isBeforeThanOrEqualTo(lastYear);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulIsBeforeThanOrEqualTo() {

		evaluator = new DateEvaluator(new Date());
		evaluator.isBeforeThanOrEqualTo(lastYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(null);
		evaluator.isBeforeThanOrEqualTo(lastYear);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulBelongsToInterval() {

		evaluator = new DateEvaluator(new Date());
		evaluator.belongsToInterval(lastYear, nextYear);
		assertTrue(evaluator.evaluate());

		evaluator = new DateEvaluator(lastYear);
		evaluator.belongsToInterval(lastYear, nextYear);
		assertTrue(evaluator.evaluate());

		evaluator = new DateEvaluator(nextYear);
		evaluator.belongsToInterval(lastYear, nextYear);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulBelongsToInterval() {

		evaluator = new DateEvaluator(null);
		evaluator.belongsToInterval(lastYear, nextYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(lastYear);
		evaluator.belongsToInterval(new Date(), nextYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(nextYear);
		evaluator.belongsToInterval(lastYear, new Date());
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulBelongsToLeftOpenInterval() {

		evaluator = new DateEvaluator(new Date());
		evaluator.belongsToLeftOpenInterval(lastYear, nextYear);
		assertTrue(evaluator.evaluate());

		evaluator = new DateEvaluator(nextYear);
		evaluator.belongsToLeftOpenInterval(lastYear, nextYear);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulBelongsToLeftOpenInterval() {

		evaluator = new DateEvaluator(null);
		evaluator.belongsToLeftOpenInterval(lastYear, nextYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(lastYear);
		evaluator.belongsToLeftOpenInterval(new Date(), nextYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(nextYear);
		evaluator.belongsToLeftOpenInterval(lastYear, new Date());
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(lastYear);
		evaluator.belongsToLeftOpenInterval(lastYear, nextYear);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulBelongsToRightOpenInterval() {

		evaluator = new DateEvaluator(new Date());
		evaluator.belongsToRightOpenInterval(lastYear, nextYear);
		assertTrue(evaluator.evaluate());

		evaluator = new DateEvaluator(lastYear);
		evaluator.belongsToRightOpenInterval(lastYear, nextYear);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulBelongsToRightOpenInterval() {

		evaluator = new DateEvaluator(null);
		evaluator.belongsToRightOpenInterval(lastYear, nextYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(lastYear);
		evaluator.belongsToRightOpenInterval(new Date(), nextYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(nextYear);
		evaluator.belongsToRightOpenInterval(lastYear, new Date());
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(nextYear);
		evaluator.belongsToRightOpenInterval(lastYear, nextYear);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulBelongsToOpenInterval() {

		evaluator = new DateEvaluator(new Date());
		evaluator.belongsToOpenInterval(lastYear, nextYear);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulBelongsToOpenInterval() {

		evaluator = new DateEvaluator(null);
		evaluator.belongsToOpenInterval(lastYear, nextYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(lastYear);
		evaluator.belongsToOpenInterval(new Date(), nextYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(nextYear);
		evaluator.belongsToOpenInterval(lastYear, new Date());
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(nextYear);
		evaluator.belongsToOpenInterval(lastYear, nextYear);
		assertFalse(evaluator.evaluate());

		evaluator = new DateEvaluator(lastYear);
		evaluator.belongsToOpenInterval(lastYear, nextYear);
		assertFalse(evaluator.evaluate());
	}

	private Date addYearsFromNow(int amount) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, currentYear + amount);

		return calendar.getTime();
	}
}
