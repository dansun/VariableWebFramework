package nu.danielsundberg.variableweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nu.danielsundberg.variableweb.component.html.modules.sectioning.Div;
import nu.danielsundberg.variableweb.component.html.modules.structure.Title;
import nu.danielsundberg.variableweb.component.types.HTML5DocumentComponent;
import nu.danielsundberg.variableweb.manager.VariableWebManager;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private VariableWebManager variableWebManager;

	public void init (ServletConfig config) throws ServletException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-config.xml");
		for(String name : ctx.getBeanDefinitionNames()) System.out.println(name);
		variableWebManager = (VariableWebManager) ctx.getBean("persistantVariableWebManager");
		HTML5DocumentComponent testDoc = new HTML5DocumentComponent("index.html");
		testDoc.addTitle(new Title("Test of VariableWebComponents"));
		testDoc.addChild(new Div("Hello World!"));
		variableWebManager.addContextRoot("/", testDoc);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		handleResponse(req,res);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		handleResponse(req,res);
	}
	   
	public void handleResponse(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter output = null;
		try	{
			output = res.getWriter();
		} catch(IOException iex) {
			iex.printStackTrace();
		}
		if(output != null) {
			System.out.println(req.getRequestURI());
			output.print(variableWebManager.fetchContextRoot(req.getRequestURI()).renderElement());
			output.close();
		}
	}
	  
}
