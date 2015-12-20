package com.lms.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.dao.LeadHeaderDao;
import com.lms.dao.UserDao;
import com.lms.dao.UserRoleDao;
import com.lms.daoImpl.LeadHeaderDaoImpl;
import com.lms.daoImpl.UserDaoImpl;
import com.lms.daoImpl.UserRoleDaoImpl;
import com.lms.model.UserRole;
import com.lms.util.MailUtility;
import com.lms.util.Utility;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private UserRoleDao userRoleDao;
	private UserDao userDao;
	private LeadHeaderDao lHeaderDao;
	
	private static String LOGIN_PAGE = "/login.jsp";
	private static String HOME_PAGE = "index.jsp";
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        userDao=new UserDaoImpl();
        userRoleDao=new UserRoleDaoImpl();
        lHeaderDao=new LeadHeaderDaoImpl();
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession session=request.getSession();
		if(session.getAttribute("luserId")==null)
		{
			response.sendRedirect("login.jsp");
		}
		else
		{
		
		
		
			request.setAttribute("role", session.getAttribute("lroleId")+"");
		String ldCount=lHeaderDao.getLeadCount(session.getAttribute("luserId")+"", session.getAttribute("lroleId")+"",
				session.getAttribute("lshortState")+"", session.getAttribute("lcity")+"", session.getAttribute("lregion")+"","Pending");
		String ldAmount=lHeaderDao.getLeadAmount(session.getAttribute("luserId")+"", session.getAttribute("lroleId")+"",
				session.getAttribute("lshortState")+"", session.getAttribute("lcity")+"", session.getAttribute("lregion")+"","Pending");
		
		String indentCount=lHeaderDao.getLeadCount(session.getAttribute("luserId")+"", session.getAttribute("lroleId")+"",
				session.getAttribute("lshortState")+"", session.getAttribute("lcity")+"", session.getAttribute("lregion")+"","Closed");
		String indentAmount=lHeaderDao.getLeadAmount(session.getAttribute("luserId")+"", session.getAttribute("lroleId")+"",
				session.getAttribute("lshortState")+"", session.getAttribute("lcity")+"", session.getAttribute("lregion")+"","Closed");

		
		//For Pie Chart
		String leadDistribution="";
		String indentDistribution="";
		String leadAmtDistribution="";
		String indentAmtDistribution="";
		
		leadDistribution=lHeaderDao.getMonthLeadCount(session.getAttribute("luserId")+"", session.getAttribute("lroleId")+"",
				session.getAttribute("lshortState")+"", session.getAttribute("lcity")+"", session.getAttribute("lregion")+"","Pending",Utility.getFirstDayofCurrentMonth(),Utility.getLastDayofCurrentMonth());
		indentDistribution=lHeaderDao.getMonthLeadCount(session.getAttribute("luserId")+"", session.getAttribute("lroleId")+"",
				session.getAttribute("lshortState")+"", session.getAttribute("lcity")+"", session.getAttribute("lregion")+"","Closed",Utility.getFirstDayofCurrentMonth(),Utility.getLastDayofCurrentMonth());

		leadAmtDistribution=lHeaderDao.getMonthLeadAmount(session.getAttribute("luserId")+"", session.getAttribute("lroleId")+"",
				session.getAttribute("lshortState")+"", session.getAttribute("lcity")+"", session.getAttribute("lregion")+"","Pending",Utility.getFirstDayofCurrentMonth(),Utility.getLastDayofCurrentMonth());
		indentAmtDistribution=lHeaderDao.getMonthLeadAmount(session.getAttribute("luserId")+"", session.getAttribute("lroleId")+"",
				session.getAttribute("lshortState")+"", session.getAttribute("lcity")+"", session.getAttribute("lregion")+"","Closed",Utility.getFirstDayofCurrentMonth(),Utility.getLastDayofCurrentMonth());

		
		if(leadDistribution.equalsIgnoreCase("") || leadDistribution.equalsIgnoreCase("0") )
		{
			leadDistribution=0+"";
		}
		if(indentDistribution.equalsIgnoreCase("") || indentDistribution.equalsIgnoreCase("0") )
		{
			indentDistribution=0+"";
		}
		
		if(leadAmtDistribution.equalsIgnoreCase("") || leadAmtDistribution.equalsIgnoreCase("0") )
		{
			leadAmtDistribution=0+"";
		}
		if(indentAmtDistribution.equalsIgnoreCase("") || indentAmtDistribution.equalsIgnoreCase("0") )
		{
			indentAmtDistribution=0+"";
		}
		
		session.setAttribute("leadDistribution",leadDistribution);
		session.setAttribute("indentDistribution",indentDistribution);
		
		session.setAttribute("leadAmtDistribution",leadAmtDistribution);
		session.setAttribute("indentAmtDistribution",indentAmtDistribution);
		
		//Get Bar and Area Chart Value
		List leadList,indentList;
		leadList=new ArrayList();
		indentList=new ArrayList();
		
		leadList=lHeaderDao.getMonthWiseLeadCountTillCurrentMonth(session.getAttribute("luserId")+"", session.getAttribute("lroleId")+"",
				session.getAttribute("lshortState")+"", session.getAttribute("lcity")+"", session.getAttribute("lregion")+"","Pending","4");
		indentList=lHeaderDao.getMonthWiseLeadCountTillCurrentMonth(session.getAttribute("luserId")+"", session.getAttribute("lroleId")+"",
				session.getAttribute("lshortState")+"", session.getAttribute("lcity")+"", session.getAttribute("lregion")+"","Closed","4");
		String curMonth=Utility.getCurrentMonth();
		
		session.setAttribute("leadList",leadList);
		session.setAttribute("indentList",indentList);
		session.setAttribute("curMonth",curMonth);
		
		System.out.println(leadList);
		System.out.println(indentList);

		
		
		if(ldAmount.equalsIgnoreCase("") || ldAmount==null || ldAmount=="null")
		{
			ldAmount=0+"";
		}
		if(ldCount.equalsIgnoreCase("") || ldCount.equalsIgnoreCase("0") )
		{
			ldCount=0+"";
		}

		if(indentAmount.equalsIgnoreCase("") || indentAmount==null || indentAmount=="null")
		{
			indentAmount=0+"";
		}
		if(indentCount.equalsIgnoreCase("") || indentCount.equalsIgnoreCase("0") )
		{
			indentCount=0+"";
		}
		
		float amount;
		DecimalFormat formatter;
		DecimalFormatSymbols symbols;
		String moneyString="";
		amount=Float.parseFloat(ldAmount);
		formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
		symbols = formatter.getDecimalFormatSymbols();
		symbols.setCurrencySymbol(""); // Don't use null
		formatter.setDecimalFormatSymbols(symbols);
		moneyString = formatter.format(amount);

		session.setAttribute("leadCount",ldCount);
		session.setAttribute("leadAmount",moneyString);
		

		amount=Float.parseFloat(indentAmount);
		formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
		symbols = formatter.getDecimalFormatSymbols();
		symbols.setCurrencySymbol(""); // Don't use null
		formatter.setDecimalFormatSymbols(symbols);
		moneyString = formatter.format(amount);

		session.setAttribute("indentCount",indentCount);
		session.setAttribute("indentAmount",moneyString);

        //view.forward(request, response);
		response.sendRedirect(HOME_PAGE);
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<UserRole> userRoleList=new ArrayList<UserRole>(); 
		UserRole userRole=new UserRole();
		String action=request.getParameter("action");
		String email=request.getParameter("email");
		//forgotpassword
		if(action.equalsIgnoreCase("login"))
		{
		
		
		String password=request.getParameter("password");
		System.out.println("Hey"+email+password);
		String status=userDao.validateUser(email, password);
		//System.out.println("Hello1"+status);
		
		String userId="",userName="",roleId="",roleName="",region="",state="",shortState="",city="";
		HttpSession session=request.getSession();
		
		if(status.equals("valid"))
		{
			
			userRoleList=userRoleDao.getDefaultUserOtherDetails(email);
			Iterator it=userRoleList.iterator();
			while(it.hasNext())
			{
				userRole=(UserRole) it.next();
			}
			
			System.out.println("Test"+userRole);
			userId=userRole.getUSER_ID();
			userName=userRole.getUSER_NAME();
			roleId=userRole.getROLE_ID();
			roleName=userRole.getROLE_NAME();
			region=userRole.getREGION_TYPE();
			state=userRole.getEXE_STATE();
			shortState=userRole.getEXE_STATE_SHORT();
			city=userRole.getEXE_CITY();
			
			
			
			RequestDispatcher view = request.getRequestDispatcher(HOME_PAGE);
			
			session.setAttribute("luserId", userId);
			session.setAttribute("luserName", userName);
			session.setAttribute("lroleId", roleId);
			request.setAttribute("role", roleId);
			session.setAttribute("lroleName", roleName);
			session.setAttribute("lregion", region);
			session.setAttribute("lstate", state);
			session.setAttribute("lshortState", shortState);
			session.setAttribute("lcity", city);
		
			String ldCount=lHeaderDao.getLeadCount(userId, roleId, shortState, city, region,"Pending");
			String ldAmount=lHeaderDao.getLeadAmount(userId, roleId, shortState, city, region,"Pending");
			
			String indentCount=lHeaderDao.getLeadCount(userId, roleId, shortState, city, region,"Closed");
			String indentAmount=lHeaderDao.getLeadAmount(userId, roleId, shortState, city, region,"Closed");
			
			
			//For Pie Chart
			String leadDistribution="";
			String indentDistribution="";
			String leadAmtDistribution="";
			String indentAmtDistribution="";
			
			leadDistribution=lHeaderDao.getMonthLeadCount(userId, roleId, shortState, city, region,"Pending",Utility.getFirstDayofCurrentMonth(),Utility.getLastDayofCurrentMonth());
			indentDistribution=lHeaderDao.getMonthLeadCount(userId, roleId, shortState, city, region,"Closed",Utility.getFirstDayofCurrentMonth(),Utility.getLastDayofCurrentMonth());

			leadAmtDistribution=lHeaderDao.getMonthLeadAmount(userId, roleId, shortState, city, region,"Pending",Utility.getFirstDayofCurrentMonth(),Utility.getLastDayofCurrentMonth());
			indentAmtDistribution=lHeaderDao.getMonthLeadAmount(userId, roleId, shortState, city, region,"Closed",Utility.getFirstDayofCurrentMonth(),Utility.getLastDayofCurrentMonth());

			if(leadDistribution.equalsIgnoreCase("") || leadDistribution.equalsIgnoreCase("0") )
			{
				leadDistribution=0+"";
			}
			if(indentDistribution.equalsIgnoreCase("") || indentDistribution.equalsIgnoreCase("0") )
			{
				indentDistribution=0+"";
			}
			
			if(leadAmtDistribution.equalsIgnoreCase("") || leadAmtDistribution.equalsIgnoreCase("0") )
			{
				leadAmtDistribution=0+"";
			}
			if(indentAmtDistribution.equalsIgnoreCase("") || indentAmtDistribution.equalsIgnoreCase("0") )
			{
				indentAmtDistribution=0+"";
			}
			
			session.setAttribute("leadDistribution",leadDistribution);
			session.setAttribute("indentDistribution",indentDistribution);
			
			session.setAttribute("leadAmtDistribution",leadAmtDistribution);
			session.setAttribute("indentAmtDistribution",indentAmtDistribution);

			
			
			//Get Bar and Area Chart Value
			List leadList,indentList;
			leadList=new ArrayList();
			indentList=new ArrayList();
			
			leadList=lHeaderDao.getMonthWiseLeadCountTillCurrentMonth(userId, roleId, shortState, city, region,"Pending","4");
			indentList=lHeaderDao.getMonthWiseLeadCountTillCurrentMonth(userId, roleId, shortState, city, region,"Closed","4");
			String curMonth=Utility.getCurrentMonth();
			
			session.setAttribute("leadList",leadList);
			session.setAttribute("indentList",indentList);
			session.setAttribute("curMonth",curMonth);
			
			System.out.println(leadList);
			System.out.println(indentList);
			
			
			System.out.println("Hello"+ldCount+"asd");
			System.out.println("Hello"+ldAmount+"asd");
			
			
			
			
			if(ldAmount.equalsIgnoreCase("") || ldAmount==null || ldAmount=="null")
			{
				ldAmount=0+"";
			}
			if(ldCount.equalsIgnoreCase("") || ldCount.equalsIgnoreCase("0") )
			{
				ldCount=0+"";
			}
			
			if(indentAmount.equalsIgnoreCase("") || indentAmount==null || indentAmount=="null")
			{
				indentAmount=0+"";
			}
			if(indentCount.equalsIgnoreCase("") || indentCount.equalsIgnoreCase("0") )
			{
				indentCount=0+"";
			}
			
			float amount;
			DecimalFormat formatter;
			DecimalFormatSymbols symbols;
			String moneyString="";
			amount=Float.parseFloat(ldAmount);
			formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
			symbols = formatter.getDecimalFormatSymbols();
			symbols.setCurrencySymbol(""); // Don't use null
			formatter.setDecimalFormatSymbols(symbols);
			moneyString = formatter.format(amount);

			session.setAttribute("leadCount",ldCount);
			session.setAttribute("leadAmount",moneyString);
			

			amount=Float.parseFloat(indentAmount);
			formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
			symbols = formatter.getDecimalFormatSymbols();
			symbols.setCurrencySymbol(""); // Don't use null
			formatter.setDecimalFormatSymbols(symbols);
			moneyString = formatter.format(amount);

			session.setAttribute("indentCount",indentCount);
			session.setAttribute("indentAmount",moneyString);

	        //view.forward(request, response);
			response.sendRedirect(HOME_PAGE);
			
		}
		else
		{
			RequestDispatcher view = request.getRequestDispatcher(LOGIN_PAGE);
	       // request.setAttribute("error", "Invalid User Or Password");
	        request.setAttribute("message", "Invalid User Or Password");
	        view.forward(request, response);
	        
		}	
		
	}	
		if(action.equalsIgnoreCase("forgotpassword"))
		{

				String toEmail=email;
				String password=userDao.getPassword(toEmail);
				String message="";
				
				if(password.equalsIgnoreCase("invalid"))
				{
					message="Email address is not in our database, please check your email address again";
				}
				else
				{
					message="Your Password has been sent to your Email address, please check your inbox and login here";
					String mailMessage=" Your password is "+password;
					String subject="Password Reset - Sterling Lead Mangament Solution";
					String ccAddress="amitkumar.singh@muraai.com";
					
					try{
						//sendEmailWithoutAttachments(toAddress, ccAddress,  subject,  message);
					MailUtility.sendEmailWithoutAttachments(toEmail, ccAddress, subject, mailMessage);
					}catch(Exception e)
					{
						e.getMessage();
						e.printStackTrace();
					}
				}
				
				RequestDispatcher view = request.getRequestDispatcher(LOGIN_PAGE);
		       // request.setAttribute("error", "Invalid User Or Password");
		        request.setAttribute("message", message);
		        view.forward(request, response);
		        
		}
		

	}

}
