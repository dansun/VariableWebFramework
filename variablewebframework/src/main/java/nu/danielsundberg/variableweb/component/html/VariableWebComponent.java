package nu.danielsundberg.variableweb.component.html;

import java.util.Set;

public abstract interface VariableWebComponent {

	public String getElementType();
	
	public String renderElement();
	
	/**
	 * Called before content is rendered.
	 */
	public void beforeRender();
	
	/**
	 * Called after component has rendered content
	 */
	public void afterRender();

	public VariableWebComponent getParent();
	public void setParent(VariableWebComponent parent);
	public Set<VariableWebComponent> getChildren();
	public void addChild(VariableWebComponent component);
	public void removeChild(VariableWebComponent component);
}
