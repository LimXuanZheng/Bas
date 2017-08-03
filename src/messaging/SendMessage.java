package messaging;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.activemq.broker.BrokerService;

import homePage.Home;

@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String userTo;
	
    public SendMessage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			out.println(
				  "<html>"
				+ 	"<head>"
				+ 		"<style>"
				+			"html, body{"
				+ 				"width:100%;"
				+ 				"height:100%;"
				+ 				"padding:0px;"
				+ 				"margin:0px;"
				+ 			"}"
				+ 		"</style>"
				+ 	"</head>"
				+ 	"<body>"
				+		"<form method='POST' action='SendMessage'>"
				+ 			"<input type='text' name='message' style='float:left;width:100%;height:100%;' onkeydown='if (event.keyCode == 13) { this.form.submit(); return false; }'>"
				+ 		"</form>"
				+ 	"</body>"
				+ "</html>");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			String userID = "4";
			if (session != null) {
				userID = (String) session.getAttribute("userID");
				userTo = (String) session.getAttribute("toUser");
			}
			else {
				//response.sendRedirect("Login");
				System.out.println("Session not created - redirect to login");
			}
			
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			
			String message = request.getParameter("message");
			String timestamp = df.format(date);
			
			InitialContext initCtx = new InitialContext();
			ConnectionFactory connectionFactory = (ConnectionFactory) initCtx.lookup("java:/comp/env/jms/ConnectionFactory");
			Connection connection = connectionFactory.createConnection();
			Session connSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//Queue queue = (Queue) initCtx.lookup("java:comp/env/jms/queue/MyQueue");
			
			String line = "java:/comp/env/jms/queue/";
			if(Integer.parseInt(userID) < Integer.parseInt(userTo)){
				line += userID + "~" + userTo;
			}else{
				line += userTo + "~" + userID;
			}
			
			Queue queue = (Queue) initCtx.lookup(line);
			MessageProducer producer = connSession.createProducer(queue);
			
			Message testMessage = connSession.createMessage();
			testMessage.setStringProperty("Message", message);
			testMessage.setStringProperty("Timestamp", timestamp);
			testMessage.setStringProperty("Owner", userID);
			
			producer.send(testMessage);
			
			response.setIntHeader("refresh", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
