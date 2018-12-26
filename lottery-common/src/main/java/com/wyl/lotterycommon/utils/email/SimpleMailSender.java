package com.wyl.lotterycommon.utils.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class SimpleMailSender {
	/**
	 * 不再使用,异步发送邮件请使用EmailUtil.sendToUserAsyn("jin.liu@ksudi.com", "邮件标题", "邮件内容");
	 * @deprecated
	 * @author jin
	 * @date 2015年12月8日
	 * @param toAddress 收件人邮箱，subject 邮件标题，content 邮件内容
	 */
	public static void sendToUserAsyn(final String toAddress,final String subject,final String content){
		sendToUser(toAddress, subject, content);
	}
	//发送文本格式的邮件
	public boolean sendTextMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
		// 根据session创建一个邮件消息
		Message mailMessage = new MimeMessage(sendMailSession);
		// 创建邮件发送者地址
		Address from = new InternetAddress(mailInfo.getFromAddress());
		// 设置邮件消息的发送者
		mailMessage.setFrom(from);
		// 创建邮件的接收者地址，并设置到邮件消息中
		Address to = new InternetAddress(mailInfo.getToAddress());
		mailMessage.setRecipient(Message.RecipientType.TO, to);
		// 设置邮件消息的主题
		mailMessage.setSubject(mailInfo.getSubject());
		// 设置邮件消息发送的时间
		mailMessage.setSentDate(new Date());
		// 设置邮件消息的主要内容
		String mailContent = mailInfo.getContent();
		mailMessage.setText(mailContent);
		// 发送邮件
		Transport.send(mailMessage);
		return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	//发送HTML格式邮件
	public  boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			//Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);	
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	
	
	public static Boolean sendToUser(String toAddress,String subject,String content,String sendEmail,String password){
		Boolean flag=false;
		try{
		// 设置邮件服务器信息
		  MailSenderInfo mailInfo = new MailSenderInfo();
		  mailInfo.setMailServerHost("mail.ksudi.com");
		  mailInfo.setMailServerPort("25");
		  mailInfo.setValidate(true);
		  // 邮箱用户名
		  mailInfo.setUserName(sendEmail);
		  // 邮箱密码
		  mailInfo.setPassword(password);
		  // 发件人邮箱
		  mailInfo.setFromAddress(sendEmail);
		  // 收件人邮箱
		  mailInfo.setToAddress(toAddress);
		  // 邮件标题
		  mailInfo.setSubject(subject);
		  // 邮件内容
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(content);
		  mailInfo.setContent(buffer.toString());
		  // 发送邮件
		  // 发送html格式
		  SimpleMailSender mailSender = new SimpleMailSender();
		   flag = mailSender.sendHtmlMail(mailInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
		  return flag;
	}
	
	public static Boolean sendToUser(String toAddress,String subject,String content){
		Boolean flag=false;
		try{
		// 设置邮件服务器信息
		  MailSenderInfo mailInfo = new MailSenderInfo();
		  mailInfo.setMailServerHost("mail.ksudi.com");
		  mailInfo.setMailServerPort("25");
		  mailInfo.setValidate(true);
		  // 邮箱用户名
		  mailInfo.setUserName("notify@ksudi.com");
		  // 邮箱密码
		  mailInfo.setPassword("NotifY123");
		  // 发件人邮箱
		  mailInfo.setFromAddress("notify@ksudi.com");
		  // 收件人邮箱
		  mailInfo.setToAddress(toAddress);
		  // 邮件标题
		  mailInfo.setSubject(subject);
		  // 邮件内容
		  StringBuffer buffer = new StringBuffer();
		  buffer.append(content);
		  mailInfo.setContent(buffer.toString());
		  // 发送邮件
		  // 发送html格式
		  SimpleMailSender mailSender = new SimpleMailSender();
		   flag = mailSender.sendHtmlMail(mailInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
		  return flag;
	}
}
