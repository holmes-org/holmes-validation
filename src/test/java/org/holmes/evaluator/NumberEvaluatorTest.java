package org.holmes.evaluator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

public class NumberEvaluatorTest {

	private NumberEvaluator evaluator;

	private static final int PER_TYPE_COUNT = 5;

	@Test
	public void testSuccessfulGreaterThanEval() {

		evaluator = new NumberEvaluator(10);

		for (Number n : randomNumbers(0, 10, true, false)) {

			evaluator.isGreaterThan(n);
			assertTrue(evaluator.evaluate());
		}
	}

	@Test
	public void testUnsuccessfulGreaterThanEval() {

		evaluator = new NumberEvaluator(10);

		for (Number n : randomNumbers(10, 100, true, false)) {

			evaluator.isGreaterThan(n);
			assertFalse(evaluator.evaluate());
		}
	}

	@Test
	public void testSuccessfulGreaterThanOrEqualToEval() {

		evaluator = new NumberEvaluator(10);

		for (Number n : randomNumbers(0, 10, true, true)) {

			evaluator.isGreaterThanOrEqualTo(n);
			assertTrue(evaluator.evaluate());
		}
	}

	@Test
	public void testUnsuccessfulGreaterThanOrEqualToEval() {

		evaluator = new NumberEvaluator(10);

		for (Number n : randomNumbers(10, 100, false, true)) {

			evaluator.isGreaterThanOrEqualTo(n);
			assertFalse(evaluator.evaluate());
		}
	}

	@Test
	public void testSuccessfulLessThanEval() {

		evaluator = new NumberEvaluator(10);

		for (Number n : randomNumbers(10, 100, false, true)) {

			evaluator.isLessThan(n);
			assertTrue(evaluator.evaluate());
		}
	}

	@Test
	public void testUnsuccessfulLessThanEval() {

		evaluator = new NumberEvaluator(10);

		for (Number n : randomNumbers(0, 10, true, true)) {

			evaluator.isLessThan(n);
			assertFalse(evaluator.evaluate());
		}
	}

	@Test
	public void testSuccessfulLessThanOrEqualToEval() {

		evaluator = new NumberEvaluator(10);

		for (Number n : randomNumbers(10, 100, true, true)) {

			evaluator.isLessThanOrEqualTo(n);
			assertTrue(evaluator.evaluate());
		}
	}

	@Test
	public void testUnsuccessfulLessThanOrEqualToEval() {

		evaluator = new NumberEvaluator(10);

		for (Number n : randomNumbers(0, 10, true, false)) {

			evaluator.isLessThanOrEqualTo(n);
			assertFalse(evaluator.evaluate());
		}
	}

	@Test
	public void testSuccessfulIsPositiveEval() {

		for (Number n : randomNumbers(1, 100, true, true)) {

			evaluator = new NumberEvaluator(n);
			evaluator.isPositive();
			assertTrue(evaluator.evaluate());
		}
	}

	@Test
	public void testUnsuccessfulIsPositiveEval() {

		for (Number n : randomNumbers(0, 100, true, true, -1)) {

			evaluator = new NumberEvaluator(n);
			evaluator.isPositive();
			assertFalse(evaluator.evaluate());
		}
	}

	@Test
	public void testSuccessfulIsNegativeEval() {

		for (Number n : randomNumbers(1, 100, true, true, -1)) {

			evaluator = new NumberEvaluator(n);
			evaluator.isNegative();
			assertTrue(evaluator.evaluate());
		}
	}

	@Test
	public void testUnsuccessfulIsNegativeEval() {

		for (Number n : randomNumbers(0, 100, true, true)) {

			evaluator = new NumberEvaluator(n);
			evaluator.isNegative();
			assertFalse(evaluator.evaluate());
		}
	}

	@Test
	public void testSuccessfulIsNonPositiveEval() {

		for (Number n : randomNumbers(0, 100, true, true, -1)) {

			evaluator = new NumberEvaluator(n);
			evaluator.isNonPositive();
			assertTrue(evaluator.evaluate());
		}
	}

	@Test
	public void testUnsuccessfulIsNonPositiveEval() {

		for (Number n : randomNumbers(1, 100, true, true)) {

			evaluator = new NumberEvaluator(n);
			evaluator.isNonPositive();
			assertFalse(evaluator.evaluate());
		}
	}

	@Test
	public void testSuccessfulIsNonNegativeEval() {

		for (Number n : randomNumbers(0, 100, true, true)) {

			evaluator = new NumberEvaluator(n);
			evaluator.isNonNegative();
			assertTrue(evaluator.evaluate());
		}
	}

	@Test
	public void testUnsuccessfulIsNonNegativeEval() {

		for (Number n : randomNumbers(1, 100, true, true, -1)) {

			evaluator = new NumberEvaluator(n);
			evaluator.isNonNegative();
			assertFalse(evaluator.evaluate());
		}
	}

	@Test
	public void testSuccessfulIsOddEval() {

		for (int i = 1; i < 100; i += 2) {

			evaluator = new NumberEvaluator(i);
			evaluator.isOdd();
			assertTrue(evaluator.evaluate());
		}
	}

	@Test
	public void testUnsuccessfulIsOddEval() {

		for (int i = 0; i < 100; i += 2) {

			evaluator = new NumberEvaluator(i);
			evaluator.isOdd();
			assertFalse(evaluator.evaluate());
		}
	}

	@Test
	public void testSuccessfulIsEvenEval() {

		for (int i = 0; i < 100; i += 2) {

			evaluator = new NumberEvaluator(i);
			evaluator.isEven();
			assertTrue(evaluator.evaluate());
		}
	}

	@Test
	public void testUnsuccessfulIsEvenEval() {

		for (int i = 1; i < 100; i += 2) {

			evaluator = new NumberEvaluator(i);
			evaluator.isEven();
			assertFalse(evaluator.evaluate());
		}
	}

	@Test
	public void testSuccessfulIsMultipleOfEval() {

		for (int i = 0; i < 100; i += 15) {

			evaluator = new NumberEvaluator(i);
			evaluator.isMultipleOf(15);
			assertTrue(evaluator.evaluate());
		}
	}

	@Test
	public void testUnsuccessfulIsMultipleOfEval() {

		for (int i = 1; i < 100; i += 2) {

			evaluator = new NumberEvaluator(i);
			evaluator.isMultipleOf(40);
			assertFalse(evaluator.evaluate());
		}
	}

	private Number[] randomNumbers(int min, int max, boolean minIncluded, boolean maxIncluded) {

		return randomNumbers(min, max, minIncluded, maxIncluded, 1);
	}

	private Number[] randomNumbers(int min, int max, boolean minIncluded, boolean maxIncluded, int sign) {

		Number[] n = new Number[PER_TYPE_COUNT * 10];

		int intMin = minIncluded ? min : min + 1;
		int intMax = maxIncluded ? max + 1 : max;

		fillAtomicInteger(n, intMin, intMax, sign);
		fillAtomicLong(n, intMin, intMax, sign);
		fillBigDecimal(n, min, max, sign);
		fillBigInteger(n, intMin, intMax, sign);
		fillByte(n, intMin, intMax, sign);
		fillDouble(n, min, max, sign);
		fillFloat(n, min, max, sign);
		fillInteger(n, intMin, intMax, sign);
		fillLong(n, intMin, intMax, sign);
		fillShort(n, intMin, intMax, sign);

		return n;
	}

	private void fillAtomicInteger(Number[] n, int min, int max, int sign) {

		for (int i = 0; i < PER_TYPE_COUNT; i++) {
			n[i * 10] = new AtomicInteger((int) random(min, max, sign));
		}
	}

	private void fillAtomicLong(Number[] n, int min, int max, int sign) {

		for (int i = 0; i < PER_TYPE_COUNT; i++) {
			n[(i * 10) + 1] = new AtomicLong((long) random(min, max, sign));
		}
	}

	private void fillBigDecimal(Number[] n, int min, int max, int sign) {

		for (int i = 0; i < PER_TYPE_COUNT; i++) {
			n[(i * 10) + 2] = new BigDecimal(random(min, max, sign));
		}
	}

	private void fillBigInteger(Number[] n, int min, int max, int sign) {

		for (int i = 0; i < PER_TYPE_COUNT; i++) {
			n[(i * 10) + 3] = new BigInteger(String.valueOf((int) random(min, max, sign)));
		}
	}

	private void fillByte(Number[] n, int min, int max, int sign) {

		for (int i = 0; i < PER_TYPE_COUNT; i++) {
			n[(i * 10) + 4] = Byte.valueOf((byte) random(min, max, sign));
		}
	}

	private void fillDouble(Number[] n, int min, int max, int sign) {

		for (int i = 0; i < PER_TYPE_COUNT; i++) {
			n[(i * 10) + 5] = Double.valueOf(random(min, max, sign));
		}
	}

	private void fillFloat(Number[] n, int min, int max, int sign) {

		for (int i = 0; i < PER_TYPE_COUNT; i++) {
			n[(i * 10) + 6] = Float.valueOf((float) random(min, max, sign));
		}
	}

	private void fillInteger(Number[] n, int min, int max, int sign) {

		for (int i = 0; i < PER_TYPE_COUNT; i++) {
			n[(i * 10) + 7] = Integer.valueOf((int) random(min, max, sign));
		}
	}

	private void fillLong(Number[] n, int min, int max, int sign) {

		for (int i = 0; i < PER_TYPE_COUNT; i++) {
			n[(i * 10) + 8] = Long.valueOf((long) random(min, max, sign));
		}
	}

	private void fillShort(Number[] n, int min, int max, int sign) {

		for (int i = 0; i < PER_TYPE_COUNT; i++) {
			n[(i * 10) + 9] = Short.valueOf((short) random(min, max, sign));
		}
	}

	private double random(int min, int max, int sign) {

		return (min + Math.random() * max) * sign;
	}

}
