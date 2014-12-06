package GetContent;

// Interface 
public interface Service {
	String doService(String email,
			   		 String subject,
			   		 String content) throws Exception;
}
