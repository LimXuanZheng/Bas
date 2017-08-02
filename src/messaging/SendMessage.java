package messaging;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
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

@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
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
			//DateFormat df = new SimpleDateFormat("HH:mm:ss");
			//Date date = new Date();
			
			String message = request.getParameter("message");
			//String timestamp = df.format(date);
			
			InitialContext initCtx = new InitialContext();
			ConnectionFactory connectionFactory = (ConnectionFactory) initCtx.lookup("java:comp/env/jms/ConnectionFactory");
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = (Queue) initCtx.lookup("java:comp/env/jms/queue/MyQueue");
			MessageProducer producer = session.createProducer(queue);
			
			TextMessage testMessage = session.createTextMessage();
			testMessage.setText(message);
			producer.send(testMessage);
			
			response.setIntHeader("refresh", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
