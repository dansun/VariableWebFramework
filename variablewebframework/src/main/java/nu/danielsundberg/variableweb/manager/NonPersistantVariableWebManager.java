package nu.danielsundberg.variableweb.manager;

import java.util.HashMap;
import java.util.Map;

import nu.danielsundberg.variableweb.component.html.VariableWebComponent;

public class NonPersistantVariableWebManager implements VariableWebManager {

	private Map<String, VariableWebComponent> contextRoots = new HashMap<String, VariableWebComponent>();
	
	public VariableWebComponent fetchRootComponentForContext(String contextRootId) {
		return contextRoots.get(contextRootId);
	}

	public void addContextRoot(String contextRootId, VariableWebComponent contextRootComponent) {
		this.contextRoots.put(contextRootId, contextRootComponent);
	}

	public VariableWebComponent fetchContextRoot(String contextRootId) {
		return this.contextRoots.get(contextRootId);
	}

}
