package org.holmes;

import java.util.Collection;
import java.util.Date;

import org.holmes.evaluator.BooleanEvaluator;
import org.holmes.evaluator.CollectionEvaluator;
import org.holmes.evaluator.DateEvaluator;
import org.holmes.evaluator.NumberEvaluator;
import org.holmes.evaluator.ObjectEvaluator;
import org.holmes.evaluator.StringEvaluator;
import org.holmes.statement.SimpleStatement;

/**
 * This class provides a way to chain {@link Evaluator} instances within a same
 * {@link Rule}, through a suitable instance of {@link Statement}.
 * 
 * @author diegossilveira
 */
public class Joint {

	private final Rule rule;

	Joint(Rule rule) {

		this.rule = rule;
	}

	/**
	 * Finalizes an {@link Rule}, specifying a descriptor used to represent a
	 * violation to the {@link Rule}.
	 * 
	 * @param violationDescriptor
	 *            the violation descriptor.
	 */
	public void otherwise(String violationDescriptor) {

		rule.setViolationDescriptor(violationDescriptor);
	}

	/**
	 * Adds a disjunctive statement to the {@link Rule}.
	 * 
	 * @param bool
	 *            the target of the disjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public BooleanEvaluator or(Boolean bool) {

		return or(new BooleanEvaluator(bool));
	}

	/**
	 * Adds a conjunctive statement to the {@link Rule}.
	 * 
	 * @param bool
	 *            the target of the conjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public BooleanEvaluator and(Boolean bool) {

		return and(new BooleanEvaluator(bool));
	}

	/**
	 * Adds a disjunctive statement to the {@link Rule}.
	 * 
	 * @param string
	 *            the target of the disjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public StringEvaluator or(String string) {

		return or(new StringEvaluator(string));
	}

	/**
	 * Adds a conjunctive statement to the {@link Rule}.
	 * 
	 * @param string
	 *            the target of the conjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public StringEvaluator and(String string) {

		return and(new StringEvaluator(string));
	}

	/**
	 * Adds a disjunctive statement to the {@link Rule}.
	 * 
	 * @param collection
	 *            the target of the disjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public <E> CollectionEvaluator<E> or(Collection<E> collection) {

		return or(new CollectionEvaluator<E>(collection));
	}

	/**
	 * Adds a conjunctive statement to the {@link Rule}.
	 * 
	 * @param collection
	 *            the target of the conjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public <E> CollectionEvaluator<E> and(Collection<E> collection) {

		return and(new CollectionEvaluator<E>(collection));
	}

	/**
	 * Adds a disjunctive statement to the {@link Rule}.
	 * 
	 * @param number
	 *            the target of the disjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public NumberEvaluator or(Number number) {

		return or(new NumberEvaluator(number));
	}

	/**
	 * Adds a conjunctive statement to the {@link Rule}.
	 * 
	 * @param number
	 *            the target of the conjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public NumberEvaluator and(Number number) {

		return and(new NumberEvaluator(number));
	}

	/**
	 * Adds a disjunctive statement to the {@link Rule}.
	 * 
	 * @param date
	 *            the target of the disjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public DateEvaluator or(Date date) {

		return or(new DateEvaluator(date));
	}

	/**
	 * Adds a conjunctive statement to the {@link Rule}.
	 * 
	 * @param date
	 *            the target of the conjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public DateEvaluator and(Date date) {

		return and(new DateEvaluator(date));
	}

	/**
	 * Adds a disjunctive statement to the {@link Rule}.
	 * 
	 * @param object
	 *            the target of the disjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public ObjectEvaluator<Object> or(Object object) {

		return or(new ObjectEvaluator<Object>(object));
	}

	/**
	 * Adds a conjunctive statement to the {@link Rule}.
	 * 
	 * @param object
	 *            the target of the conjunctive statement.
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public ObjectEvaluator<Object> and(Object object) {

		return and(new ObjectEvaluator<Object>(object));
	}

	private <E extends Evaluator<?>> E or(E evaluator) {

		evaluator.setJoint(this);
		rule.addOrStatement(new SimpleStatement(evaluator));
		return evaluator;
	}

	private <E extends Evaluator<?>> E and(E evaluator) {

		evaluator.setJoint(this);
		rule.addAndStatement(new SimpleStatement(evaluator));
		return evaluator;
	}

}
