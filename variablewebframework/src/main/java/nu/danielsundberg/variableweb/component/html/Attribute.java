package nu.danielsundberg.variableweb.component.html;

import java.io.Serializable;

public class Attribute implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String property;
	
	public Attribute(String name, String property) {
		this.setName(name);
		this.setProperty(property);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}
	
}
