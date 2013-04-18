package org.holmes.evaluator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringEvaluatorTest {

	private StringEvaluator evaluator;

	@Test
	public void testSuccessfulNotEmptyEval() {

		evaluator = new StringEvaluator("text");
		evaluator.isNotEmpty();
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulNotEmptyEval() {

		evaluator = new StringEvaluator("    ");
		evaluator.isNotEmpty();
		assertFalse(evaluator.evaluate());

		evaluator = new StringEvaluator("");
		evaluator.isNotEmpty();
		assertFalse(evaluator.evaluate());

		evaluator = new StringEvaluator(null);
		evaluator.isNotEmpty();
		assertFalse(evaluator.evaluate());
	}
}
