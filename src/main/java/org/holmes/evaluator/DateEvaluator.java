package org.holmes.evaluator;

import java.util.Date;

import org.holmes.Evaluator;
import org.holmes.evaluator.support.Diff;
import org.holmes.evaluator.support.FutureNumber;

/**
 * An {@link Evaluator} for the {@link Date} type.
 * 
 * @author diegossilveira
 */
public class DateEvaluator extends ObjectEvaluator<Date> {

	public DateEvaluator(Date target) {

		super(target);
	}
	
	public NumberEvaluator applying(final Diff diff) {
		
		final FutureNumber futureNumber = new FutureNumber();
		final NumberEvaluator evaluator = new NumberEvaluator(futureNumber);
		
		setEvaluation(new Evaluation<Date>() {
			
			public boolean evaluate(Date target) {
				
				diff.setTarget(target);
				futureNumber.wrap(diff.calculate());
				
				return evaluator.evaluate();
			}
		});
		
		evaluator.setJoint(getJoint());
		return evaluator;
	}
}
