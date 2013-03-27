package org.holmes;

/**
 * Validators give more flexibility to the validation process. This provides
 * extensibility to the framework, once the user can write his own specific
 * validator.
 * 
 * @author diegossilveira
 * 
 * @param <T>
 *            the type this validator validates.
 */
public interface Validator<T> {

	boolean isValid(T target);

}
