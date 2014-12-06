package GetContent;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetContent
 */
public class GetContent extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	  
	// Method to handle GET method request.
	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
		  String email = request.getParameter("email-id");
		  String subject = request.getParameter("sub");
		  String content =  request.getParameter("body");
		  String msg = "Mail not sent";
		  ServiceProvider servProv = new ServiceProvider();
		  
	      // Set response content type
	      response.setContentType("text/html");

	      PrintWriter out = response.getWriter();
	      
	      try {
	    	msg = servProv.mailService(email, subject, content);
	      } catch (Exception e) {			
			e.printStackTrace();
	      }
	      
	      out.println(
	    		  	"<html> \n" +
	    		  	"<body> \n" +
	    		  	"<h1> <b>" + msg + "</b> </h1> \n" +
	    		  	"<a href='http://mailsenderapp.elasticbeanstalk.com/'> Click Here to try MailSender again </a>" +
	    		  	"</body> \n" +
	    		  	"</html>");
	  }
}

