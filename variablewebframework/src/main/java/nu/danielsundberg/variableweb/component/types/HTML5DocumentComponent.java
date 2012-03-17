package nu.danielsundberg.variableweb.component.types;

import nu.danielsundberg.variableweb.component.html.Element;
import nu.danielsundberg.variableweb.component.html.VariableWebComponent;
import nu.danielsundberg.variableweb.component.html.modules.structure.Body;
import nu.danielsundberg.variableweb.component.html.modules.structure.Head;
import nu.danielsundberg.variableweb.component.html.modules.structure.Html;
import nu.danielsundberg.variableweb.component.html.modules.structure.Title;


public class HTML5DocumentComponent extends Element {

	private static final long serialVersionUID = 1L; 
	private static final String DOCTYPE_HEAD = "<!DOCTYPE html>";

	private Html html = new Html();
	private Head htmlHead = new Head();
	private Body htmlBody = new Body();
	

	public HTML5DocumentComponent(String componentId) {
		super(componentId);
		html.addChild(htmlHead);
		html.addChild(htmlBody);
		super.addChild(html);
	}

	public void addTitle(Title title){
		htmlHead.addChild(title);
	}
	
	public void addComponentToBody(VariableWebComponent component) {
		htmlBody.addChild(component);
	}
	/**
	 * Detaches component from body.
	 * @param componentId
	 */
	public void removeComponentFromBody(VariableWebComponent child) {
		htmlBody.removeChild(child);
	}
	
	@Override
	public String renderElement() {
		StringBuilder sb = new StringBuilder();
		sb.append(DOCTYPE_HEAD + LINEBREAK);
		for(VariableWebComponent child:children.descendingSet()) {
			sb.append(child.renderElement());
		}
		return sb.toString();
	}

	
	
}
