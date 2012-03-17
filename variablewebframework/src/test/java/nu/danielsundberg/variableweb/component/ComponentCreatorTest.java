package nu.danielsundberg.variableweb.component;

import static org.junit.Assert.assertEquals;
import nu.danielsundberg.variableweb.component.html.VariableWebComponent;
import nu.danielsundberg.variableweb.component.html.modules.structure.Title;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ComponentCreatorTest {
	
	@Test
	public void testCreateComponent() throws Exception {
		ComponentCreator c = new ComponentCreator();
		VariableWebComponent component = c.makeComponent("test", "return \"hello world\";");
		assertEquals("hello world", component.renderElement());
		assertEquals("test", component.getElementType());
	}
	
	@Test
	public void testConnectComponent() throws Exception {
		ComponentCreator c = new ComponentCreator();
		VariableWebComponent component = c.makeComponent("test", "return \"<h1>Hello World!</h1>\";");
		component.addChild(new Title("Test"));
		assertEquals(1, component.getChildren().size());
	}

}
