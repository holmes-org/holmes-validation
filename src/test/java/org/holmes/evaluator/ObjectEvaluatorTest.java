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
	public void testSuccessfulIsEqualToEval() {

		evaluator = new ObjectEvaluator<Object>("Hello");
		evaluator.isEqualTo("Hello");
		assertTrue(evaluator.evaluate());
		
		evaluator = new ObjectEvaluator<Object>(null);
		evaluator.isEqualTo(null);
		assertTrue(evaluator.evaluate());
	}
	
	@Test
	public void testUnsuccessfulIsEqualToEval() {

		evaluator = new ObjectEvaluator<Object>("Hello");
		evaluator.isEqualTo("World");
		assertFalse(evaluator.evaluate());
		
		evaluator = new ObjectEvaluator<Object>(null);
		evaluator.isEqualTo("World");
		assertFalse(evaluator.evaluate());
	}
	
	@Test
	public void testSuccessfulIsNotEqualToEval() {

		evaluator = new ObjectEvaluator<Object>("Hello");
		evaluator.isNotEqualTo("World");
		assertTrue(evaluator.evaluate());
		
		evaluator = new ObjectEvaluator<Object>(null);
		evaluator.isNotEqualTo("World");
		assertTrue(evaluator.evaluate());
	}
	
	@Test
	public void testUnsuccessfulIsNotEqualToEval() {

		evaluator = new ObjectEvaluator<Object>("Hello");
		evaluator.isNotEqualTo("Hello");
		assertFalse(evaluator.evaluate());
	}

}
