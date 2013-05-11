package org.holmes.evaluator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.holmes.Validator;
import org.holmes.test.TypeA;
import org.junit.Test;

public class CollectionEvaluatorTest {

	@Test
	public void testSuccessfulCardinalityEval() {

		CollectionEvaluator<Integer> evaluator = new CollectionEvaluator<Integer>(
				Arrays.asList(1, 3, 5, 5, 3, 1));

		evaluator.cardinalityOf(3).isEqualTo(2);
		assertTrue(evaluator.evaluate());

		evaluator.cardinalityOf(6).isEqualTo(0);
		assertTrue(evaluator.evaluate());

		evaluator.cardinalityOf(null).isEqualTo(0);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulCardinalityEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(
				Arrays.asList("one", "two"));

		evaluator.cardinalityOf("two").isEqualTo(2);
		assertFalse(evaluator.evaluate());

		evaluator = new CollectionEvaluator<String>(null);

		evaluator.cardinalityOf("one").isEqualTo(0);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulSizeEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(
				Arrays.asList("a", "b", "c", "d"));

		evaluator.size().isEqualTo(4);
		assertTrue(evaluator.evaluate());

		evaluator = new CollectionEvaluator<String>(
				Collections.<String> emptyList());

		evaluator.size().isEqualTo(0);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulSizeEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(
				Arrays.asList("a", "b", "c", "d"));

		evaluator.size().isGreaterThan(4);
		assertFalse(evaluator.evaluate());

		evaluator = new CollectionEvaluator<String>(null);
		evaluator.size().isEqualTo(0);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulIsEmptyEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(
				Collections.<String> emptyList());

		evaluator.isEmpty();
		assertTrue(evaluator.evaluate());

		evaluator = new CollectionEvaluator<String>(null);
		evaluator.isEmpty();
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulIsEmptyEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(
				Arrays.asList("a", "b", "c", "d"));

		evaluator.isEmpty();
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulIsNotEmptyEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(
				Arrays.asList("a", "b", "c", "d"));

		evaluator.isNotEmpty();
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulIsNotEmptyEval() {

		CollectionEvaluator<String> evaluator = new CollectionEvaluator<String>(
				Collections.<String> emptyList());

		evaluator.isNotEmpty();
		assertFalse(evaluator.evaluate());

		evaluator = new CollectionEvaluator<String>(null);
		evaluator.isNotEmpty();
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulContainsEval() {

		CollectionEvaluator<TypeA> evaluator = new CollectionEvaluator<TypeA>(
				getCollection());

		evaluator.contains(new TypeA(8));
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulContainsEval() {

		CollectionEvaluator<TypeA> evaluator = new CollectionEvaluator<TypeA>(
				getCollection());

		evaluator.contains(new TypeA(18));
		assertFalse(evaluator.evaluate());

		evaluator = new CollectionEvaluator<TypeA>(null);
		evaluator.contains(new TypeA(5));
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulDoesNotContainEval() {

		CollectionEvaluator<TypeA> evaluator = new CollectionEvaluator<TypeA>(
				getCollection());

		evaluator.doesNotContain(new TypeA(18));
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulDoesNotContainEval() {

		CollectionEvaluator<TypeA> evaluator = new CollectionEvaluator<TypeA>(
				getCollection());

		evaluator.doesNotContain(new TypeA(2));
		assertFalse(evaluator.evaluate());

		evaluator = new CollectionEvaluator<TypeA>(null);
		evaluator.doesNotContain(new TypeA(5));
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulContainsAllEval() {

		Collection<TypeA> col = getCollection();
		col.add(new TypeA(34));

		CollectionEvaluator<TypeA> evaluator = new CollectionEvaluator<TypeA>(
				col);

		evaluator.containsAll(getCollection());
		assertTrue(evaluator.evaluate());

		evaluator.containsAll(getCollection());
		assertTrue(evaluator.evaluate());

		evaluator.containsAll(Collections.<TypeA> emptyList());
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulContainsAllEval() {

		CollectionEvaluator<TypeA> evaluator = new CollectionEvaluator<TypeA>(
				getCollection());

		Collection<TypeA> col = getCollection();
		col.add(new TypeA(34));

		evaluator.containsAll(col);
		assertFalse(evaluator.evaluate());

		evaluator.containsAll(null);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulContainsAnyEval() {

		CollectionEvaluator<TypeA> evaluator = new CollectionEvaluator<TypeA>(
				getCollection());

		evaluator.containsAny(getCollection());
		assertTrue(evaluator.evaluate());

		Collection<TypeA> col = new ArrayList<TypeA>();
		col.add(new TypeA(34));
		col.add(new TypeA(21));
		col.add(new TypeA(18));

		evaluator.containsAny(col);
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulContainsAnyEval() {

		CollectionEvaluator<TypeA> evaluator = new CollectionEvaluator<TypeA>(
				getCollection());

		evaluator.containsAny(getCollection());
		assertTrue(evaluator.evaluate());

		Collection<TypeA> col = new ArrayList<TypeA>();
		col.add(new TypeA(36));
		col.add(new TypeA(18));

		evaluator.containsAny(col);
		assertFalse(evaluator.evaluate());

		evaluator.containsAny(Collections.<TypeA> emptyList());
		assertFalse(evaluator.evaluate());

		evaluator.containsAny(null);
		assertFalse(evaluator.evaluate());
	}

	@Test
	public void testSuccessfulHasAllValidByEval() {

		CollectionEvaluator<TypeA> evaluator = new CollectionEvaluator<TypeA>(
				getCollection());

		evaluator.hasAllValidBy(new Validator<TypeA>() {

			public boolean isValid(TypeA target) {

				return target != null && target.getId() < 100;
			}
		});
		assertTrue(evaluator.evaluate());
	}

	@Test
	public void testUnsuccessfulHasAllValidByEval() {

		CollectionEvaluator<TypeA> evaluator = new CollectionEvaluator<TypeA>(
				getCollection());

		evaluator.hasAllValidBy(new Validator<TypeA>() {

			public boolean isValid(TypeA target) {

				return target != null && target.getId() > 10;
			}
		});
		assertFalse(evaluator.evaluate());

		evaluator.hasAllValidBy(null);
		assertFalse(evaluator.evaluate());
	}

	private Collection<TypeA> getCollection() {

		List<TypeA> l = new ArrayList<TypeA>();
		l.add(new TypeA(21));
		l.add(new TypeA(13));
		l.add(new TypeA(8));
		l.add(new TypeA(5));
		l.add(new TypeA(3));
		l.add(new TypeA(2));
		l.add(new TypeA(1));

		return l;
	}
}
