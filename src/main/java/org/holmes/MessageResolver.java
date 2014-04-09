package org.holmes;

/**
 * MessageResolvers are the components that processes violation messages.
 * 
 * @author diegossilveira
 */
public interface MessageResolver {

	String resolveMessage(String message, Object... arguments);
	
}
