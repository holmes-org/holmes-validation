package org.holmes;

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
	LAZY,

	/**
	 * In EAGER mode, HolmesEngine will evaluate all rule and stop at the first
	 * violation occurred, throwing an exception immediately.
	 */
	EAGER;

}
