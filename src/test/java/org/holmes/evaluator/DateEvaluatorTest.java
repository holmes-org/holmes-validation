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

	@Before
	public void defineLastYear() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, currentYear - 1);

		lastYear = calendar.getTime();
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

		evaluator.applying(Diff.toNow().inMilliseconds()).isGreaterThan(
				31535000000L);
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

		evaluator.applying(Diff.toNow().inMilliseconds()).isLessThan(
				31536000000L);
		assertFalse(evaluator.evaluate());
	}
}
