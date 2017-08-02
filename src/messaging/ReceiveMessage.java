package messaging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.activemq.broker.BrokerService;

/**
 * Servlet implementation class ReceiveMessage
 */
@WebServlet("/ReceiveMessage")
public class ReceiveMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private QueueConnection queueConnection;
	private MessageConsumer consumer;
	private ArrayList<TextModel> arrayString = new ArrayList<TextModel>();
	private String userID = "4";

	public ReceiveMessage() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			InitialContext initCtx = new InitialContext();
			QueueConnectionFactory connectionFactory = (QueueConnectionFactory) initCtx.lookup("java:comp/env/jms/ConnectionFactory");
			queueConnection = connectionFactory.createQueueConnection();
			QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = (Queue) initCtx.lookup("java:comp/env/jms/queue/MyQueue");
			consumer = queueSession.createConsumer(queue);
			queueConnection.start();
			messageLoop ml = new messageLoop();
			ml.start();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			userID = (String)session.getAttribute("username");
		}
		else {
			//response.sendRedirect("Login");
			System.out.println("Session not created - redirect to login");
		}

		out = response.getWriter();

		//consumer.setMessageListener(new ConsumerMessageListener(pw));
		out.println(
				"<html>"
				+ 	"<head>"
				+ 		"<style>"
				+			"html, body{"
				+ 				"width:100%;"
				+ 				"height:100%;"
				+ 				"padding:0px;"
				+ 				"margin:0px;"
				+				"background-color: white;"
				+ 			"}"
				+ 			".textMessage{"
				+ 				"width:90vw;"
				+ 				"height: 30px;"
				+ 				"padding: 10px;"
				+ 			"}"
				+ 		"</style>"
				+ 	"</head>"
				+ 	"<body>"
				+ 		"<div>");

		for(TextModel s:arrayString){
			if(s.getUserID().equals(userID))
				out.println("<div class='textMessage' style='text-align:right;color:green;''><p>" + s.getMessage() + "</p></div>");
			else{
				out.println("<div class='textMessage' style='text-align:left;color:blue;''><p>" + s.getMessage() + "</p></div>");
			}
		}
		
		out.println(	"</div>"
				+ 	"</body>"
				+ "</html>");
		
		response.setIntHeader("Refresh", 1);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private class messageLoop extends Thread {

		public void run() {
			try {
				queueConnection.start();
				while(true){
					Message m = consumer.receive(1000);
					if (m != null && m instanceof TextMessage) {
						TextMessage tm = (TextMessage) m;
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						PrintStream ps = new PrintStream(baos);
						PrintStream old = System.out;
						System.setOut(ps);
						System.out.println(tm.getText());
						System.out.flush();
						System.setOut(old);
						arrayString.add(new TextModel(baos.toString(), userID));
					}
					Thread.sleep(100);
				}
			} catch (JMSException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private class TextModel{
		private String message;
		private String userID;
		
		private TextModel(String message, String userID){
			this.message = message;
			this.userID = userID;
		}

		public String getMessage() {
			return message;
		}
		public String getUserID() {
			return userID;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
	}
}
