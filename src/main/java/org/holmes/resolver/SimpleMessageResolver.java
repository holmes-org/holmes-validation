package org.holmes.resolver;

import org.holmes.MessageResolver;

/**
 * A {@link MessageResolver} that performs no operation on messages.
 * 
 * @author diegossilveira
 */
public class SimpleMessageResolver implements MessageResolver {

	@Override
	public String resolveMessage(String message, Object... arguments) {
		
		return message;
	}
}
