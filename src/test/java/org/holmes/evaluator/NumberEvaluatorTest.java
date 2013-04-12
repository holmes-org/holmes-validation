package org.holmes.evaluator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import org.holmes.Diff;
import org.junit.Test;

public class NumberEvaluatorTest {

	private NumberEvaluator evaluator;

	private Calendar someDate = Calendar.getInstance();

	@Test
	public void testSuccessfulGreaterThanEval() {

		evaluator = new NumberEvaluator(2);

		evaluator.isGreaterThan(1L);
		assertTrue(evaluator.evaluate());

		evaluator.isGreaterThan(1.99f);
		assertTrue(evaluator.evaluate());

		evaluator.isGreaterThan(1.99d);
		assertTrue(evaluator.evaluate());

		evaluator.isGreaterThan(new BigDecimal("1.99999"));
		assertTrue(evaluator.evaluate());

		evaluator.isGreaterThan(new BigInteger("1"));
		assertTrue(evaluator.evaluate());

		someDate.setTime(new Date());

		someDate.set(Calendar.DAY_OF_MONTH, 1);
		evaluator.isGreaterThan(Diff.to(someDate.getTime()).inDays());
		assertTrue(evaluator.evaluate());

		someDate.set(Calendar.MONTH, 1);
		evaluator.isGreaterThan(Diff.to(someDate.getTime()).inMonths());
		assertTrue(evaluator.evaluate());

		someDate.set(Calendar.YEAR, 1);
		evaluator.isGreaterThan(Diff.to(someDate.getTime()).inYears());
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testFailGreateThanEval() {

		evaluator = new NumberEvaluator(1);

		evaluator.isGreaterThan(2L);
		assertFalse(evaluator.evaluate());

		evaluator.isGreaterThan(2.99f);
		assertFalse(evaluator.evaluate());

		evaluator.isGreaterThan(2.99d);
		assertFalse(evaluator.evaluate());

		evaluator.isGreaterThan(new BigDecimal("2.99999"));
		assertFalse(evaluator.evaluate());

		evaluator.isGreaterThan(new BigInteger("2"));
		assertFalse(evaluator.evaluate());

		someDate.setTime(new Date());

		someDate.set(Calendar.DAY_OF_MONTH, 2);
		evaluator.isGreaterThan(Diff.to(someDate.getTime()).inDays());
		assertFalse(evaluator.evaluate());

		someDate.set(Calendar.MONTH, 2);
		evaluator.isGreaterThan(Diff.to(someDate.getTime()).inMonths());
		assertFalse(evaluator.evaluate());

		someDate.set(Calendar.YEAR, 2);
		evaluator.isGreaterThan(Diff.to(someDate.getTime()).inYears());
		assertFalse(evaluator.evaluate());
	}
}
