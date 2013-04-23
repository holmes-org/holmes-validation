package org.holmes.evaluator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

public class StringEvaluatorTest {

	private StringEvaluator evaluator;

	@Test
	public void testSuccessfulEmptyEval() {
		evaluator = new StringEvaluator("");
		evaluator.isEmpty();
		assertTrue(evaluator.evaluate());

		evaluator = new StringEvaluator("     ");
		evaluator.isEmpty();
		assertTrue(evaluator.evaluate());

		evaluator = new StringEvaluator(null);
		evaluator.isEmpty();
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulEmptyEval() {

		evaluator = new StringEvaluator("text");
		evaluator.isEmpty();
		assertFalse(evaluator.evaluate());
	}

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

	@Test
	public void testSuccessfulMatchesEval() {

		Pattern hexadecimalColor = Pattern
				.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

		evaluator = new StringEvaluator("#F2F2F2");
		evaluator.matches(hexadecimalColor);
		assertTrue(evaluator.evaluate());

		evaluator = new StringEvaluator("#FFF");
		evaluator.matches(hexadecimalColor);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulMatchesEval() {

		Pattern hexadecimalColor = Pattern
				.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

		evaluator = new StringEvaluator("  #F2F2F2  ");
		evaluator.matches(hexadecimalColor);
		assertFalse(evaluator.evaluate());

		evaluator = new StringEvaluator("F2F2F2");
		evaluator.matches(hexadecimalColor);
		assertFalse(evaluator.evaluate());

		evaluator = new StringEvaluator("black");
		evaluator.matches(hexadecimalColor);
		assertFalse(evaluator.evaluate());

		evaluator = new StringEvaluator(null);
		evaluator.matches(hexadecimalColor);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulSizeEval() {

		evaluator = new StringEvaluator("cinco");
		evaluator.length().isOdd();
		assertTrue(evaluator.evaluate());

		evaluator = new StringEvaluator("cinco");
		evaluator.length().isGreaterThan(4);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulSizeEval() {

		evaluator = new StringEvaluator("cinco");
		evaluator.length().isEven();
		assertFalse(evaluator.evaluate());

		evaluator = new StringEvaluator("cinco");
		evaluator.length().isLessThan(4);
		assertFalse(evaluator.evaluate());

		evaluator = new StringEvaluator(null);
		evaluator.length().isEqualTo(3);
		assertFalse(evaluator.evaluate());
	}
}
