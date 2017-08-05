package messaging;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			GenerationOfKey gok = new GenerationOfKey();
			String userID = "4";
			if (session != null) {
				gok.setPrivateKey((PrivateKey)session.getAttribute("privateKey"));
				gok.setPublicKey((PublicKey)session.getAttribute("publicKey"));
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
			GenerationOfKey.publicKey2 = gok.getPublicKey();
			byte[] encryptedMessage = gok.encrypt(message, GenerationOfKey.publicKey2);
			byte[] signature = gok.signHash(Base64.getEncoder().encode(gok.getHash(encryptedMessage)), gok.getPrivateKey());
			
			byte[] array = (gok.getPublicKey()).getEncoded();
			String publicKey =  Base64.getEncoder().encodeToString(array);
			Message testMessage = connSession.createMessage();
			
			testMessage.setStringProperty("Message", new String(Base64.getEncoder().encode(encryptedMessage)));
			testMessage.setStringProperty("Timestamp", timestamp);
			testMessage.setStringProperty("Owner", userID);
			testMessage.setStringProperty("PublicKey", publicKey);
			testMessage.setStringProperty("Signature", new String(Base64.getEncoder().encode(signature)));
			
			
			producer.send(testMessage);
			
			response.setIntHeader("refresh", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
