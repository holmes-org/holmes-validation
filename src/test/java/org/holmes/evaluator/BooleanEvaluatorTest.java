package org.holmes.evaluator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BooleanEvaluatorTest {

	private BooleanEvaluator evaluator;

	@Test
	public void testSuccessfulTrueEval() {

		evaluator = new BooleanEvaluator(Boolean.TRUE);
		evaluator.isTrue();
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulTrueEval() {

		evaluator = new BooleanEvaluator(Boolean.FALSE);
		evaluator.isTrue();
		assertFalse(evaluator.evaluate());
	}
	
	@Test
	public void testSuccessfulFalseEval() {

		evaluator = new BooleanEvaluator(Boolean.FALSE);
		evaluator.isFalse();
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulFalseEval() {

		evaluator = new BooleanEvaluator(Boolean.TRUE);
		evaluator.isFalse();
		assertFalse(evaluator.evaluate());
	}
}
