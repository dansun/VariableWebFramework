package nu.danielsundberg.variableweb;

import java.net.URL;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class VariableWebApplicationController {

	 public static void main(String[] args) throws Exception {
	        Server server = new Server();
	        Connector connector = new SelectChannelConnector();
	        connector.setPort(8089);
	        connector.setHost("127.0.0.1");
	        server.addConnector(connector);
	        URL warUrl = VariableWebApplicationController.class.getClassLoader().getResource("WEB-INF");
	        String warUrlString = warUrl.toExternalForm();
	        server.setHandler(new WebAppContext(warUrlString, "/"));
	        server.setStopAtShutdown(true);
	        server.start();
	    }
	 
}
