package org.holmes;

import org.holmes.resolver.SimpleMessageResolver;

/**
 * An utility class that can hold the developer's preferences, making it easier to configure the framework behavior.
 * 
 * @author diegossilveira
 */
public class HolmesFactory {

	private OperationMode operationMode;

	private MessageResolver messageResolver;

	private String defaultViolationDescriptor;

	public HolmesFactory() {

		this.operationMode = OperationMode.GREEDY;
		this.messageResolver = new SimpleMessageResolver();
	}

	/**
	 * Initializes the engine with the given {@link OperationMode}.
	 * 
	 * @param mode
	 *            the {@link OperationMode}
	 */
	public HolmesFactory withOperationMode(OperationMode operationMode) {

		this.operationMode = operationMode;
		return this;
	}

	/**
	 * Specifies an instance of {@link MessageResolver} used to process violation descriptors.
	 * 
	 * @param messageResolver
	 *            an instance of {@link MessageResolver}.
	 */
	public HolmesFactory withMessageResolver(MessageResolver messageResolver) {

		this.messageResolver = messageResolver;
		return this;
	}

	/**
	 * Specifies a default violation descriptor used to represent violations to rules which do not specify its own descriptor.
	 * 
	 * @param defaultViolationDescriptor
	 *            the violation descriptor.
	 */
	public HolmesFactory withDefaultViolationDescriptor(String defaultViolationDescriptor) {

		this.defaultViolationDescriptor = defaultViolationDescriptor;
		return this;
	}

	public HolmesEngine createEngine() {

		return HolmesEngine.init(operationMode).withDefaultDescriptor(defaultViolationDescriptor).withMessageResolver(messageResolver);
	}

}
