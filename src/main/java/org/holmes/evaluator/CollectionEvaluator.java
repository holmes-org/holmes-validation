package org.holmes.evaluator;

import java.util.Collection;

import org.holmes.Evaluator;
import org.holmes.Joint;
import org.holmes.Validator;
import org.holmes.evaluator.support.FutureNumber;

/**
 * An {@link Evaluator} for the {@link Collection} type.
 * 
 * @author diegossilveira
 */
public class CollectionEvaluator<E> extends ObjectEvaluator<Collection<E>> {

	public CollectionEvaluator(Collection<E> target) {
		super(target);
	}

	/**
	 * Evaluates the number of occurrences of
	 * <code>element<code> in the target collection
	 * 
	 * @param element
	 *            an element.
	 * @return an instance of {@link NumberEvaluator}
	 */
	public NumberEvaluator cardinalityOf(final E element) {

		final FutureNumber futureNumber = new FutureNumber();
		final NumberEvaluator evaluator = new NumberEvaluator(futureNumber);

		setEvaluation(new Evaluation<Collection<E>>() {

			public boolean evaluate(Collection<E> target) {

				if (target == null) {
					return false;
				}

				futureNumber.wrap(getCardinality(element, target));
				return evaluator.evaluate();
			}
		});

		evaluator.setJoint(getJoint());
		return evaluator;
	}

	/**
	 * Evaluates the size of the target collection. A null collection has no
	 * size!
	 * 
	 * @return an instance of {@link NumberEvaluator}
	 */
	public NumberEvaluator size() {

		final FutureNumber futureNumber = new FutureNumber();
		final NumberEvaluator evaluator = new NumberEvaluator(futureNumber);

		setEvaluation(new Evaluation<Collection<E>>() {

			public boolean evaluate(Collection<E> target) {

				if (target == null) {
					return false;
				}

				futureNumber.wrap(target.size());
				return evaluator.evaluate();
			}
		});

		evaluator.setJoint(getJoint());
		return evaluator;
	}

	/**
	 * Ensures that the target does not contain elements.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isEmpty() {

		return setEvaluation(new Evaluation<Collection<E>>() {

			public boolean evaluate(Collection<E> target) {

				return target == null || target.isEmpty();
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target contains any element.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint isNotEmpty() {

		return setEvaluation(new Evaluation<Collection<E>>() {

			public boolean evaluate(Collection<E> target) {

				return target != null && !target.isEmpty();
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target collection contains an <code>element</code>.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint contains(final E element) {

		return setEvaluation(new Evaluation<Collection<E>>() {

			public boolean evaluate(Collection<E> target) {

				return target != null && target.contains(element);
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target collection does not contain an
	 * <code>element</code>.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint doesNotContain(final E element) {

		return setEvaluation(new Evaluation<Collection<E>>() {

			public boolean evaluate(Collection<E> target) {

				return target != null && !target.contains(element);
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target collection contains all elements of
	 * <code>collection</code>.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint containsAll(final Collection<E> collection) {

		return setEvaluation(new Evaluation<Collection<E>>() {

			public boolean evaluate(Collection<E> target) {

				if (collection == null || target == null) {
					return false;
				}

				for (E e : collection) {
					if (!target.contains(e)) {
						return false;
					}
				}
				return true;
			}

		}).getJoint();
	}

	/**
	 * Ensures that the target collection contains any elements of
	 * <code>collection</code>.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint containsAny(final Collection<E> collection) {

		return setEvaluation(new Evaluation<Collection<E>>() {

			public boolean evaluate(Collection<E> target) {

				if (collection == null || target == null) {
					return false;
				}

				for (E e : collection) {
					if (target.contains(e)) {
						return true;
					}
				}
				return false;
			}

		}).getJoint();
	}

	/**
	 * Ensures that all the elements of the target collection are valid by the
	 * <code>validator</code>.
	 * 
	 * @return an instance of {@link Joint} class
	 */
	public Joint hasAllValidBy(final Validator<E> validator) {

		return setEvaluation(new Evaluation<Collection<E>>() {

			public boolean evaluate(Collection<E> target) {

				if (target == null || validator == null) {
					return false;
				}

				for (E e : target) {
					if (!validator.isValid(e)) {
						return false;
					}
				}

				return true;
			}

		}).getJoint();
	}

	private int getCardinality(E element, Collection<E> collection) {

		int cardinality = 0;

		for (E e : collection) {

			if ((element == null && e == null)
					|| (element != null && element.equals(e))) {
				cardinality++;
			}
		}

		return cardinality;
	}

}
