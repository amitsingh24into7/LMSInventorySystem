package com.lms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.util.DbUtil;
import com.lms.util.MailUtility;

/**
 * Servlet implementation class MailController
 */
public class MailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MailController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Properties prop = new Properties();
		InputStream inputStream = DbUtil.class.getClassLoader()
				.getResourceAsStream("/mail.properties");
		prop.load(inputStream);

		String toAddress = prop.getProperty("toAddress");
		String ccAddress = prop.getProperty("ccAddress");
		String subject = prop.getProperty("subject");
		String message = prop.getProperty("message");
		
		
		
		List<String> attachFiles=new ArrayList<String>();
		
		String attachmentFile= request.getParameter("attachmentFile");
		System.out.println("asd"+attachmentFile);
		//Parameter%20File_15DGPAR0042.xlsx
		String file=getServletContext().getRealPath("/")+attachmentFile;
		attachFiles.add(file);
		
		try{
			//sendEmailWithoutAttachments(toAddress, ccAddress,  subject,  message);
		MailUtility.sendEmailWithAttachments(toAddress, ccAddress,  subject,  message,attachFiles);
		}catch(Exception e)
		{
			e.getMessage();
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
