package org.holmes.evaluator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ObjectEvaluatorTest {

	private ObjectEvaluator<Object> evaluator;

	@Test
	public void testSuccessfulNullEval() {

		evaluator = new ObjectEvaluator<Object>(null);
		evaluator.isNull();
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulNullEval() {

		evaluator = new ObjectEvaluator<Object>(new Object());
		evaluator.isNull();
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulNotNullEval() {

		evaluator = new ObjectEvaluator<Object>(new Object());
		evaluator.isNotNull();
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulNotNullEval() {

		evaluator = new ObjectEvaluator<Object>(null);
		evaluator.isNotNull();
		assertFalse(evaluator.evaluate());
	}
	
	@Test
	public void testSuccessfulIsEqualsToEval() {

		evaluator = new ObjectEvaluator<Object>("Hello");
		evaluator.isEqualsTo("Hello");
		assertTrue(evaluator.evaluate());
	}
	
	@Test
	public void testUnsuccessfulIsEqualsToEval() {

		evaluator = new ObjectEvaluator<Object>("Hello");
		evaluator.isEqualsTo("World");
		assertFalse(evaluator.evaluate());
	}
	
	@Test
	public void testSuccessfulIsNotEqualsToEval() {

		evaluator = new ObjectEvaluator<Object>("Hello");
		evaluator.isNotEqualsTo("World");
		assertTrue(evaluator.evaluate());
	}
	
	@Test
	public void testUnsuccessfulIsNotEqualsToEval() {

		evaluator = new ObjectEvaluator<Object>("Hello");
		evaluator.isNotEqualsTo("Hello");
		assertFalse(evaluator.evaluate());
	}

}
