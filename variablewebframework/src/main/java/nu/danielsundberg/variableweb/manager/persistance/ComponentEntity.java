package nu.danielsundberg.variableweb.manager.persistance;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COMPONENTS")
public class ComponentEntity {

	@Id
	@Column(name="COMPONENT_ID")
	private String componentId;
	
	@Column(name="COMPONENT")
	private byte[] component;

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public Object getComponent() throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(component) ;
	    ObjectInputStream in = new ObjectInputStream(bis) ;
	    Object component = in.readObject();
	    in.close();
		bis.close();
		return component;
	}

	public void setComponent(Object component) throws IOException {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
	    ObjectOutputStream out = new ObjectOutputStream(bos) ;
	    out.writeObject(component);
	    out.close();
		this.component = bos.toByteArray();
		bos.close();
	}
	
	
	
}
