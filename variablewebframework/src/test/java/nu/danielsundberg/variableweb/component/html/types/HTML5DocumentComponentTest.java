package nu.danielsundberg.variableweb.component.html.types;

import nu.danielsundberg.variableweb.component.html.modules.sectioning.Div;
import nu.danielsundberg.variableweb.component.html.modules.structure.Title;
import nu.danielsundberg.variableweb.component.types.HTML5DocumentComponent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HTML5DocumentComponentTest {

	@Test
	public void testRenderDocument() {
		HTML5DocumentComponent document = new HTML5DocumentComponent("test");
		document.addComponentToBody(new Div("Hello world."));
		document.addTitle(new Title("Testme."));
		String output = document.renderElement();
		System.out.println(output);
	}
	
}
