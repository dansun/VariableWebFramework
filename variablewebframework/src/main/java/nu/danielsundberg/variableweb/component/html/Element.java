package nu.danielsundberg.variableweb.component.html;

import java.io.Serializable;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

public class Element implements VariableWebComponent, Serializable, Comparable<Object> {
	
	public static final long serialVersionUID = 1L;
	
	protected static final String LINEBREAK = "\n";
	protected static final String TAB = "   ";
	protected final String TAG_BEGIN_CHARACTER = "<";
	protected final String TAG_BEGIN_CLOSE_CHARACTER = "</";
	protected final String TAG_END_NO_CONTENT_CHARACTER = "/>";
	protected final String TAG_END_CONTENT_CHARACTER =">";
	protected final String SPACER=" ";
	protected final String EQUALS="=";
	
	protected NavigableSet<Attribute> attributes = new TreeSet<Attribute>();
	protected NavigableSet<VariableWebComponent> children = new TreeSet<VariableWebComponent>();
	protected VariableWebComponent parent = null;
	protected String name;
	protected String content;
	
	public Element(String name) {
		this.name = name;
	}
	
	public Element(String name, String content) {
		this.name = name;
		this.content = content;
	}
	
	public String getElementType() {
		return name;
	}
	
	public void setParent(VariableWebComponent parent) {
		this.parent = parent;
	}
	
	public VariableWebComponent getParent() {
		return parent;
	}
	
	public void setAttributes(NavigableSet<Attribute> attributes) {
		this.attributes = attributes;
	}
	public Set<Attribute> getAttributes() {
		return attributes;
	}
	
	public void addChild(VariableWebComponent component) {
		component.setParent(this);
		this.children.add(component);
	}
	
	public void removeChild(VariableWebComponent component) {
		if(this.children.contains(component)) {
			this.children.remove(component);
			component.setParent(null);
		}
	}
	
	public Set<VariableWebComponent> getChildren() {
		return children;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public String renderElement() {
		VariableWebComponent parent = this.getParent();
		int level = 0;
		while(parent!=null) {
			level++;
			parent = parent.getParent();
		}
		String myTabs = "";
		for(int i = 0 ; i<level; i++) {
			myTabs += TAB;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(myTabs + TAG_BEGIN_CHARACTER);
		sb.append(name);
		for(Attribute attribute : attributes) {
			sb.append(SPACER);
			sb.append(attribute.getName());
			sb.append(EQUALS);
			sb.append(attribute.getProperty());
		}
		if(StringUtils.isBlank(content) &&
				children.isEmpty()) {
			sb.append(TAG_END_NO_CONTENT_CHARACTER + LINEBREAK);
		} else {
			sb.append(TAG_END_CONTENT_CHARACTER + (StringUtils.isBlank(content)?LINEBREAK:""));
			if(StringUtils.isNotBlank(content)) sb.append(content);
			for(VariableWebComponent child:children.descendingSet()) {
				sb.append(child.renderElement());
			}
			sb.append(StringUtils.isBlank(content)?myTabs:"");
			sb.append(TAG_BEGIN_CLOSE_CHARACTER);
			sb.append(name);
			sb.append(TAG_END_CONTENT_CHARACTER + LINEBREAK);
		}
		
		return sb.toString();
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}

	@Override
	public void beforeRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EQUALS == null) ? 0 : EQUALS.hashCode());
		result = prime * result + ((SPACER == null) ? 0 : SPACER.hashCode());
		result = prime
				* result
				+ ((TAG_BEGIN_CHARACTER == null) ? 0 : TAG_BEGIN_CHARACTER
						.hashCode());
		result = prime
				* result
				+ ((TAG_BEGIN_CLOSE_CHARACTER == null) ? 0
						: TAG_BEGIN_CLOSE_CHARACTER.hashCode());
		result = prime
				* result
				+ ((TAG_END_CONTENT_CHARACTER == null) ? 0
						: TAG_END_CONTENT_CHARACTER.hashCode());
		result = prime
				* result
				+ ((TAG_END_NO_CONTENT_CHARACTER == null) ? 0
						: TAG_END_NO_CONTENT_CHARACTER.hashCode());
		result = prime * result
				+ ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Element other = (Element) obj;
		if (EQUALS == null) {
			if (other.EQUALS != null)
				return false;
		} else if (!EQUALS.equals(other.EQUALS))
			return false;
		if (SPACER == null) {
			if (other.SPACER != null)
				return false;
		} else if (!SPACER.equals(other.SPACER))
			return false;
		if (TAG_BEGIN_CHARACTER == null) {
			if (other.TAG_BEGIN_CHARACTER != null)
				return false;
		} else if (!TAG_BEGIN_CHARACTER.equals(other.TAG_BEGIN_CHARACTER))
			return false;
		if (TAG_BEGIN_CLOSE_CHARACTER == null) {
			if (other.TAG_BEGIN_CLOSE_CHARACTER != null)
				return false;
		} else if (!TAG_BEGIN_CLOSE_CHARACTER
				.equals(other.TAG_BEGIN_CLOSE_CHARACTER))
			return false;
		if (TAG_END_CONTENT_CHARACTER == null) {
			if (other.TAG_END_CONTENT_CHARACTER != null)
				return false;
		} else if (!TAG_END_CONTENT_CHARACTER
				.equals(other.TAG_END_CONTENT_CHARACTER))
			return false;
		if (TAG_END_NO_CONTENT_CHARACTER == null) {
			if (other.TAG_END_NO_CONTENT_CHARACTER != null)
				return false;
		} else if (!TAG_END_NO_CONTENT_CHARACTER
				.equals(other.TAG_END_NO_CONTENT_CHARACTER))
			return false;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object o) {
		if(o.equals(this)) return 0;
		else return -1;
	}
	
}
