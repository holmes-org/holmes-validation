package org.holmes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.holmes.collector.ResultCollector;
import org.holmes.evaluator.BooleanEvaluator;
import org.holmes.evaluator.DateEvaluator;
import org.holmes.evaluator.NumberEvaluator;
import org.holmes.evaluator.ObjectEvaluator;
import org.holmes.exception.RuleViolationException;
import org.holmes.exception.ValidationException;

/**
 * The main class of the framework.
 * 
 * @author diegossilveira
 */
public class HolmesEngine {

	private final List<Rule> rules;

	private final OperationMode mode;

	private HolmesEngine(OperationMode mode) {

		rules = new ArrayList<Rule>();
		this.mode = mode;
	}

	/**
	 * Initializes the engine with LAZY {@link OperationMode}.
	 * 
	 * @return initialized engine instance.
	 */
	public static HolmesEngine init() {

		return new HolmesEngine(OperationMode.LAZY);
	}

	/**
	 * Initializes the engine with the given {@link OperationMode}.
	 * 
	 * @param mode
	 *            the {@link OperationMode}
	 * @return initialized engine instance.
	 */
	public static HolmesEngine init(OperationMode mode) {

		return new HolmesEngine(mode);
	}

	/**
	 * Creates a new {@link Rule} for a {@link Boolean} target type.
	 * 
	 * @param bool
	 *            the target
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public BooleanEvaluator ensureThat(final Boolean bool) {

		return configure(new BooleanEvaluator(bool));
	}

	/**
	 * Creates a new {@link Rule} for a {@link String} target type.
	 * 
	 * @param string
	 *            the target
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public Evaluator<?> ensureThat(final String string) {

		// TODO: implement StringEvaluator class
		return null;
	}

	/**
	 * Creates a new {@link Rule} for a {@link Collection} target type.
	 * 
	 * @param collection
	 *            the target
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public Evaluator<?> ensureThat(final Collection<?> collection) {

		// TODO: implement CollectionEvaluator class
		return null;
	}

	/**
	 * Creates a new {@link Rule} for a {@link Number} target type.
	 * 
	 * @param number
	 *            the target
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public NumberEvaluator ensureThat(final Number number) {

		return configure(new NumberEvaluator(number));
	}

	/**
	 * Creates a new {@link Rule} for a {@link Date} target type.
	 * 
	 * @param date
	 *            the target
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public DateEvaluator ensureThat(final Date date) {

		return configure(new DateEvaluator(date));
	}
	
	/**
	 * Creates a new {@link Rule} for a {@link Object} target type.
	 * 
	 * @param object
	 *            the target
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public ObjectEvaluator<Object> ensureThat(final Object object) {

		return configure(new ObjectEvaluator<Object>(object));
	}

	public void run() throws ValidationException {

		ResultCollector collector = mode.getResultCollector();
		
		for (Rule rule : rules) {
			evaluateRule(rule, collector);
		}
		
		collector.finish();
	}

	private <E extends Evaluator<?>> E configure(E evaluator) {

		Rule rule = Rule.simpleFor(evaluator);
		evaluator.setJoint(new Joint(rule));
		rules.add(rule);

		return evaluator;
	}

	private void evaluateRule(Rule rule, ResultCollector collector) {

		try {

			rule.evaluate();

		} catch (RuleViolationException e) {

			collector.onRuleViolation(e);
		}
	}
}
