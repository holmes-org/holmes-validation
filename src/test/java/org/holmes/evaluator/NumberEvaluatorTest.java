package org.holmes.evaluator;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class NumberEvaluatorTest {

	private NumberEvaluator evaluator;

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
	}
	
}
