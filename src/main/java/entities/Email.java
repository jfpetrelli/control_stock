package entities;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	private String emailFrom = "tpjavacontrolstock@gmail.com";
	private String passwordFrom = "yzdjgylkhecphxjc";
	private String emailTo;
	private String subject;
	private String content;
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    
    
    
    public String getEmailTo() {
		return emailTo;
	}


	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Email(String contact, String subject, String content) {
        mProperties = new Properties();
        this.emailTo = contact;
        this.subject = subject;
        this.content = content;
    }

	
    public void createEmail() {
        emailTo = this.getEmailTo();
        subject = this.getSubject();
        content = this.getContent();
        
         // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user",emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
        
        mSession = Session.getDefaultInstance(mProperties);
                
        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setContent(content, "text/html; charset=utf-8");                             
        } catch (Exception e) {
        	
        }
    }

    public boolean sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();            
        } catch (Exception e) {
        	 return false;
        }        
        return true;
    }
}
