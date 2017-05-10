package com.controller;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("email")
public class EmailManager {
	
	
	
	@RequestMapping(value="/receiveVerifyCode.do",method=RequestMethod.GET)
	@ResponseBody
	public String receiveVerifyCode(@Param("usermail") String usermail,
									HttpServletRequest request,HttpServletResponse response) throws Exception {
		   Random r = new Random();
		   String code=100000+r.nextInt(900000)+"";
		   boolean success = sendMail(usermail, code,"加班通知",
				   "由于任务紧急务必周末过来加班"+code+"谢谢<br/><hr/><a>15801636814@163.com</a>",
				   "text/html;charset=UTF-8");
		   
		   if(success){//发送验证码成功！
			   HttpSession session = request.getSession();
			   session.setAttribute("verifyCode",code );
			   session.setAttribute("time_verifyCodeInit", System.currentTimeMillis());
			   //将当前时间写入session
			   
			   return "0";
		   }else{
			   return "1";
		   }
	}
	
	
	
    /**
     * 发送激活邮件
     * @param to 收件人邮箱地址
     * @param code 激活码
     */
   /* public  boolean sendMail(String to, String code,String subject,Object content,String contenType) {  
        try {  
        	String fromEmail="15801636814@163.com";
        	String fromPass="123456testtest";
            Properties props = new Properties();  
            props.put("username", fromEmail);   
            props.put("password", fromPass);   
            props.put("mail.transport.protocol", "smtp" );
            props.put("mail.smtp.host", "smtp.163.com");  
            props.put("mail.smtp.port", "25" );  
            props.put("mail.debug", "true");//便于调试

            
			Session mailSession = Session.getDefaultInstance(props);

            Message msg = new MimeMessage(mailSession);     
            msg.setFrom(new InternetAddress(fromEmail));  
            msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));   
            msg.setSubject(subject);   
            msg.setContent(content,contenType);
            msg.saveChanges();  

            Transport transport = mailSession.getTransport("smtp");  
            transport.connect(props.getProperty("mail.smtp.host"), 
            				  props.getProperty("username"),
            				  props.getProperty("password"));   
            
            transport.sendMessage(msg, msg.getAllRecipients());  
            transport.close();     
            System.out.println("发送完毕!");
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println(e);  
            return false;  
        }  
        return true;  
    }   */
    
    public  boolean sendMail(String to, String code,String subject,Object content,String contenType) {  
    	
    	Email email = new SimpleEmail();
    	email.setHostName("smtp.163.com");
    	email.setSmtpPort(25);
    	email.setAuthenticator(new DefaultAuthenticator("15801636814@163.com", "123456testtest"));
    	email.setSSLOnConnect(true);
    	try {
			email.setFrom("15801636814@163.com");
			email.setSubject("激活邮件");
	    	email.setMsg("验证码为"+code);
	    	email.addTo(to);
	    	email.send();
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
		return true;
    }   
    
    public static void main(String[] args) {
    	
	}

}