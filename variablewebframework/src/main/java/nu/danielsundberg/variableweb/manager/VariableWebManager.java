package nu.danielsundberg.variableweb.manager;

import nu.danielsundberg.variableweb.component.html.VariableWebComponent;

public interface VariableWebManager {

	public void addContextRoot(String contextRootId, VariableWebComponent contextRootComponent);
	public VariableWebComponent fetchContextRoot(String contextRootId);
	
}
