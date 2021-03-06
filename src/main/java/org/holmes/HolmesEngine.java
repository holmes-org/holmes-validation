package org.holmes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.holmes.evaluator.BooleanEvaluator;
import org.holmes.evaluator.CollectionEvaluator;
import org.holmes.evaluator.DateEvaluator;
import org.holmes.evaluator.NumberEvaluator;
import org.holmes.evaluator.ObjectEvaluator;
import org.holmes.evaluator.StringEvaluator;
import org.holmes.exception.RuleViolationException;
import org.holmes.exception.ValidationException;
import org.holmes.resolver.SimpleMessageResolver;

/**
 * The main class of the framework.
 * 
 * @author diegossilveira
 */
public class HolmesEngine {

	private final List<Rule> rules;

	private final ResultCollector collector;
	
	private MessageResolver messageResolver;

	private String defaultViolationDescriptor;

	private HolmesEngine(ResultCollector collector) {

		rules = new ArrayList<Rule>();
		this.messageResolver = new SimpleMessageResolver();
		this.collector = collector;
	}

	/**
	 * Initializes the engine with GREEDY {@link Op2erationMode}.
	 * 
	 * @return initialized engine instance.
	 */
	public static HolmesEngine init() {

		return new HolmesEngine(OperationMode.GREEDY.getResultCollector());
	}

	/**
	 * Initializes the engine with the given {@link OperationMode}.
	 * 
	 * @param mode
	 *            the {@link OperationMode}
	 * @return initialized engine instance.
	 */
	public static HolmesEngine init(OperationMode mode) {

		return new HolmesEngine(mode.getResultCollector());
	}

	/**
	 * Initializes the engine with the given {@link ResultCollector}.
	 * 
	 * @param collector
	 * @return initialized engine instance.
	 */
	public static HolmesEngine init(ResultCollector collector) {

		return new HolmesEngine(collector);
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
	public StringEvaluator ensureThat(final String string) {

		return configure(new StringEvaluator(string));
	}

	/**
	 * Creates a new {@link Rule} for a {@link Collection} target type.
	 * 
	 * @param collection
	 *            the target
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public <E> CollectionEvaluator<E> ensureThat(final Collection<E> collection) {

		return configure(new CollectionEvaluator<E>(collection));
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
	 * Creates a new {@link Rule} for a generic {@link Object} target type.
	 * 
	 * @param object
	 *            the target
	 * @return an appropriated {@link Evaluator} for the given target type.
	 */
	public <T> ObjectEvaluator<T> ensureThat(final T object) {

		return configure(new ObjectEvaluator<T>(object));
	}

	/**
	 * Runs all evaluation rules on the context.
	 * 
	 * @return the result of the validation process.
	 * @throws ValidationException
	 */
	public ValidationResult run() throws ValidationException {

		ValidationResult result = ValidationResult.init(messageResolver);

		for (Rule rule : rules) {
			evaluateRule(rule, result);
		}

		collector.finish(result);
		return result;
	}

	/**
	 * Specifies a default violation descriptor used to represent violations to
	 * rules which do not specify its own descriptor.
	 * 
	 * @param defaultViolationDescriptor
	 *            the violation descriptor.
	 */
	public HolmesEngine withDefaultDescriptor(String defaultViolationDescriptor) {

		this.defaultViolationDescriptor = defaultViolationDescriptor;
		return this;
	}
	
	/**
	 * Specifies an instance of {@link MessageResolver} used to process violation descriptors.
	 * 
	 * @param messageResolver
	 *            an instance of {@link MessageResolver}.
	 */
	public HolmesEngine withMessageResolver(MessageResolver messageResolver) {
		
		this.messageResolver = messageResolver;
		return this;
	}

	private <E extends Evaluator<?>> E configure(E evaluator) {

		Rule rule = Rule.simpleFor(evaluator);
		evaluator.setJoint(new Joint(rule));
		rules.add(rule);

		return evaluator;
	}

	private void evaluateRule(Rule rule, ValidationResult result) {

		try {

			if(!rule.hasViolationDescriptor()) {
				rule.setViolationDescriptor(defaultViolationDescriptor);
			}
			
			rule.evaluate();

		} catch (RuleViolationException e) {

			collector.onRuleViolation(e, result);
		}
	}
	
	// these methods are used by unit tests.
	
	ResultCollector getCollector() {
		return collector;
	}
	
	MessageResolver getMessageResolver() {
		return messageResolver;
	}
	
	String getDefaultViolationDescriptor() {
		return defaultViolationDescriptor;
	}
}
