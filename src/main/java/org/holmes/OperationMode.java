package org.holmes;

import org.holmes.collector.EagerResultCollector;
import org.holmes.collector.LazyResultCollector;
import org.holmes.collector.ResultCollector;

/**
 * Represents the operation modes supported by the {@link HolmesEngine}
 * 
 * @author diegossilveira
 */
public enum OperationMode {

	/**
	 * In LAZY mode, HolmesEngine will evaluate all rules, collecting the
	 * violations, before throwing an exception.
	 */
	LAZY {

		@Override
		public ResultCollector getResultCollector() {
			
			return new LazyResultCollector();
		}
	},

	/**
	 * In EAGER mode, HolmesEngine will evaluate all rules and stop at the first
	 * violation occurred, throwing an exception immediately.
	 */
	EAGER {
		
		@Override
		public ResultCollector getResultCollector() {
			
			return new EagerResultCollector();
		}
	};

	public abstract ResultCollector getResultCollector();

}
