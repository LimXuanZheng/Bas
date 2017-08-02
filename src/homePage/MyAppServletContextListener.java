package homePage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.activemq.broker.BrokerService;

@WebListener
public class MyAppServletContextListener implements ServletContextListener{
	private BrokerService broker;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
		try {
			broker.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");
		try {
			broker = new BrokerService();
			broker.addConnector("tcp://localhost:61616");
			broker.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}