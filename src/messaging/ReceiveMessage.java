package messaging;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReceiveMessage
 */
@WebServlet("/ReceiveMessage")
public class ReceiveMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private QueueConnection queueConnection;
	private MessageConsumer consumer;
	public static ArrayList<TextModel> arrayString = new ArrayList<TextModel>();
	private String userID = null;
	private String userTo = null;
	private GenerationOfKey gok;
	private PrivateKey privateKey;

	public ReceiveMessage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean name = false;
		HttpSession session = request.getSession(false);
		if (session != null) {
			userID = (String) session.getAttribute("userID");
			userTo = (String) session.getAttribute("toUser");
			privateKey = (PrivateKey) session.getAttribute("privateKey");
		}
		else {
			response.sendRedirect("Login");
			//System.out.println("Session not created - redirect to login");
		}
		
		try {
			gok = new GenerationOfKey();
			InitialContext initCtx = new InitialContext();
			QueueConnectionFactory connectionFactory = (QueueConnectionFactory) initCtx.lookup("java:/comp/env/jms/ConnectionFactory");
			queueConnection = connectionFactory.createQueueConnection();
			QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			//Queue queue = (Queue) initCtx.lookup("java:comp/env/jms/queue/MyQueue");
			
			String line = "java:/comp/env/jms/queue/";
			if(Integer.parseInt(userID) < Integer.parseInt(userTo)){
				line += userID + "~" + userTo;
			}else{
				line += userTo + "~" + userID;
			}
			Queue queue = (Queue) initCtx.lookup(line);
			consumer = queueSession.createConsumer(queue);
			queueConnection.start();
			messageLoop ml = new messageLoop();
			ml.start();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out = response.getWriter();

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
				+ 			"}"
				+ "#textDiv{"
				+ "overflow-x: none;"
				+ "overflow-y: auto;"
				+ 		"</style>"
				+ 	"</head>"
				+ 	"<body>"
				+ 		"<div id='textDiv'");

		if(arrayString.size() > 3){
			arrayString.remove(0);
		}
		for(TextModel s:arrayString){
			if(s.getUserID().equals(userID))
				out.println("<div class='textMessage' style='text-align:right;color:green;''><p>" + s.getMessage() + " " + s.getTimestamp() + "</p></div>");
			else{
				out.println("<div class='textMessage' style='text-align:left;color:blue;''><p>" + s.getMessage() + " " + s.getTimestamp() + "</p></div>");
			}
		}
		
		out.println(	"</div>"
				+ 	"</body>"
				+ "</html>");
		
		response.setIntHeader("refresh", 1);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private class messageLoop extends Thread {

		public void run() {
			try {
				queueConnection.start();
				while(true){
					Message m = consumer.receive();
					if (m != null && m instanceof Message) {
						byte[] message = m.getStringProperty("Message").getBytes();
						String timestamp = m.getStringProperty("Timestamp");
						String owner = m.getStringProperty("Owner");
						PublicKey publickey = (PublicKey) m.getObjectProperty("PublicKey");
						byte[] signature = m.getStringProperty("Signature").getBytes();
						if(gok.verifySignature(gok.getHash(Base64.getDecoder().decode(message)), signature, publickey)){
							String text = gok.decrypt(message, privateKey);
							arrayString.add(new TextModel(text, timestamp, owner));
						}
					}
					Thread.sleep(100);
				}
			} catch (JMSException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private class TextModel{
		private String message;
		private String timestamp;
		private String userID;
		
		private TextModel(String message, String timestamp, String userID){
			this.message = message;
			this.timestamp = timestamp;
			this.userID = userID;
		}

		public String getMessage() {
			return message;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public String getUserID() {
			return userID;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
	}
}
