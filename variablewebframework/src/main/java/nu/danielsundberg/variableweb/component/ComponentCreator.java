package nu.danielsundberg.variableweb.component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import nu.danielsundberg.variableweb.component.html.VariableWebComponent;

import org.codehaus.janino.CompileException;
import org.codehaus.janino.Parser.ParseException;
import org.codehaus.janino.Scanner.ScanException;
import org.codehaus.janino.SimpleCompiler;

public class ComponentCreator {
	
	private static String classnameReplacer = 		"{classname}";
	private static String componentRenderReplacer = 	"{componentRenderReplacer}";
	
	private String classImports =    				"import java.io.Serializable;" +
													"import java.util.HashSet;" +
													"import java.util.Set;" + 
													"import nu.danielsundberg.variableweb.component.html.Element;";
	private String classDeclaration =				"public class "+classnameReplacer+" extends Element {";
	private String classImplementation = 			"	private static final long serialVersionUID = 1L;" +
													"	public "+classnameReplacer+"(String id) {" +
													"   	super(id);" +
													"	}"; 
	private String classRender = 					"	" + 
													"	public String renderElement(){"+componentRenderReplacer+"}";
	private static String classEnd =				"}";
	
	@SuppressWarnings("unchecked")
	public VariableWebComponent makeComponent(String className, String onRenderCode) throws CompileException, ParseException, ScanException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException, NoSuchMethodException {
		SimpleCompiler c = new SimpleCompiler();
		StringBuilder b = new StringBuilder();
		b.append(classImports);
		b.append(classDeclaration.replace(classnameReplacer, className));
		b.append(classImplementation.replace(classnameReplacer, className));
		b.append(classRender.replace(componentRenderReplacer, onRenderCode));
		b.append(classEnd);
		c.cook(b.toString());
		
		Class<VariableWebComponent> myClass = (Class<VariableWebComponent>) c.getClassLoader().loadClass(className);
		for(Constructor<?> constructor : myClass.getConstructors()) {
			return (VariableWebComponent) constructor.newInstance(className);
		}
		return null;
	}
	
}
