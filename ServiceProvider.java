package GetContent;

public class ServiceProvider {
   private Service sendMail;

   public ServiceProvider() {
      sendMail = new SendMail();
   }

   public String mailService(String email,
	   		 		       String subject,
	   		 		       String content) throws Exception {
	  String ret;
	  
      ret = sendMail.doService(email, subject, content);
      
      return ret;
   }
}
