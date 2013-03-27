package org.holmes.evaluator;

import java.util.Date;

/**
 * An {@link Evaluator} for the {@link Date} type.
 * 
 * @author diegossilveira
 */
public class DateEvaluator extends ObjectEvaluator<Date> {

	public DateEvaluator(Date target) {

		super(target);
	}
	
	//DRAFT: NumberValidator applying(Diff.to(Date d).inDays()).isGreaterThan(3)

}
