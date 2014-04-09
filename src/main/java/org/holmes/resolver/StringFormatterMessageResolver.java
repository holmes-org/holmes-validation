package org.holmes.resolver;

import org.holmes.MessageResolver;

/**
 * A {@link MessageResolver} that resolves messages considering a format string (the message itself) and the arguments.
 * 
 * @author diegossilveira
 */
public class StringFormatterMessageResolver implements MessageResolver {

	@Override
	public String resolveMessage(String message, Object... arguments) {

		return (message != null) ? String.format(message, arguments) : null;
	}
}
