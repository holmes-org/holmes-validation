package org.holmes.evaluator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class CollectionEvaluatorTest {

	@Test
	public void testSuccessfulCardinalityEval() {

		CollectionEvaluator<Integer> evaluator = new CollectionEvaluator<Integer>(Arrays.asList(1, 3, 5, 5, 3, 1));

		evaluator.cardinalityOf(3).isEqualTo(2);
		assertTrue(evaluator.evaluate());

		evaluator.cardinalityOf(6).isEqualTo(0);
		assertTrue(evaluator.evaluate());

		evaluator.cardinalityOf(null).isEqualTo(0);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulCardinalityEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(Arrays.asList("one", "two"));

		evaluator.cardinalityOf("two").isEqualTo(2);
		assertFalse(evaluator.evaluate());

		evaluator = new CollectionEvaluator<String>(null);

		evaluator.cardinalityOf("one").isEqualTo(0);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulSizeEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(Arrays.asList("a", "b", "c", "d"));

		evaluator.size().isEqualTo(4);
		assertTrue(evaluator.evaluate());

		evaluator = new CollectionEvaluator<String>(Collections.<String> emptyList());

		evaluator.size().isEqualTo(0);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulSizeEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(Arrays.asList("a", "b", "c", "d"));

		evaluator.size().isGreaterThan(4);
		assertFalse(evaluator.evaluate());

		evaluator = new CollectionEvaluator<String>(null);
		evaluator.size().isEqualTo(0);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulIsEmptyEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(Collections.<String> emptyList());

		evaluator.isEmpty();
		assertTrue(evaluator.evaluate());

		evaluator = new CollectionEvaluator<String>(null);
		evaluator.isEmpty();
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulIsEmptyEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(Arrays.asList("a", "b", "c", "d"));

		evaluator.isEmpty();
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulIsNotEmptyEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(Arrays.asList("a", "b", "c", "d"));

		evaluator.isNotEmpty();
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulIsNotEmptyEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(Collections.<String> emptyList());

		evaluator.isNotEmpty();
		assertFalse(evaluator.evaluate());

		evaluator = new CollectionEvaluator<String>(null);
		evaluator.isNotEmpty();
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulContainsEval() {
		
		//TODO: write test
	}

	@Test
	public void testUnsuccessfulContainsEval() {

		//TODO: write test
	}

	@Test
	public void testSuccessfulDoesNotContainEval() {

		//TODO: write test
	}

	@Test
	public void testUnsuccessfulDoesNotContainEval() {

		//TODO: write test
	}

	@Test
	public void testSuccessfulContainsAllEval() {

		//TODO: write test
	}

	@Test
	public void testUnsuccessfulContainsAllEval() {

		//TODO: write test
	}

	@Test
	public void testSuccessfulContainsAnyEval() {

		//TODO: write test
	}

	@Test
	public void testUnsuccessfulContainsAnyEval() {

		//TODO: write test
	}

	@Test
	public void testSuccessfulHasAllValidByEval() {

		//TODO: write test
	}

	@Test
	public void testUnsuccessfulHasAllValidByEval() {

		//TODO: write test
	}
}
