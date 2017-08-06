package fileScanning;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VirusScanning
 */
@WebServlet("/VirusScanning")
public class VirusScanning{
	private static final long serialVersionUID = 1L;
    
    public VirusScanning() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	public static void main(String[] args) throws Exception{
		File file = new File("C:/Users/Wei Xuan/Desktop/eicar.com.txt");
		ScanningThread st = new ScanningThread(file);
		Thread thread =new Thread(st);
		thread.start();
	}
	
}
