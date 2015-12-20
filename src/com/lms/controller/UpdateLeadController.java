package com.lms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.AttachmentDao;
import com.lms.dao.CommentsDao;
import com.lms.dao.CustomerDao;
import com.lms.dao.LeadHeaderDao;
import com.lms.dao.LeadItemDetailsDao;
import com.lms.daoImpl.AttachmentDaoImpl;
import com.lms.daoImpl.CommentsDaoImpl;
import com.lms.daoImpl.CustomerDaoImpl;
import com.lms.daoImpl.LeadHeaderDaoImpl;
import com.lms.daoImpl.LeadItemDetailsDaoImpl;
import com.lms.model.Attachment;
import com.lms.model.Comments;
import com.lms.model.Customer;
import com.lms.model.LeadHeader;
import com.lms.model.LeadItemDetails;

/**
 * Servlet implementation class UpdateLeadController
 */
public class UpdateLeadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LeadHeaderDao leadheaderdao;
	private CustomerDao customerDao;
	private LeadItemDetailsDao leadItemDetailDao;
	private CommentsDao commenstDao;
	private AttachmentDao attachementDao;
	
	private static String CREATE_LEAD = "/createlead.jsp";
	private static String UPDATE_LEAD = "/updateLead.jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLeadController() {
        super();
        leadheaderdao=new LeadHeaderDaoImpl();
        customerDao=new CustomerDaoImpl();
        leadItemDetailDao=new LeadItemDetailsDaoImpl();
        commenstDao=new CommentsDaoImpl();
        attachementDao=new AttachmentDaoImpl();
        
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String leadid=request.getParameter("leadid");
		System.out.println("hello"+leadid);
		
		List<LeadHeader> leadHeaderList=new ArrayList<LeadHeader>();
		List<Customer> customerList=new ArrayList<Customer>();
		List<LeadItemDetails> leadItemsList=new ArrayList<LeadItemDetails>();
		List<Comments> commentsList=new ArrayList<Comments>();
		List<Attachment> attachmentsList=new ArrayList<Attachment>();
		
		
		leadHeaderList=leadheaderdao.getLeadHeadData(leadid);
		customerList=customerDao.getCustomerDetailsByLeadID(leadid);
		leadItemsList=leadItemDetailDao.getLeadItemsByLeadID(leadid);
		commentsList=commenstDao.getCommentsByLeadID(leadid);
		attachmentsList=attachementDao.getCommentsAttachmentsByLeadID(leadid);
		
		Iterator it;
		LeadHeader lHeader=new LeadHeader();
		Comments comments=new Comments();
		Attachment attachment=new Attachment();
		LeadItemDetails leadItems=new LeadItemDetails();
		Customer customer=new Customer();
				
		it=leadHeaderList.iterator();
		lHeader=(LeadHeader) it.next();
		
		it=customerList.iterator();
		customer=(Customer) it.next();
		
		it=commentsList.iterator();
		comments=(Comments) it.next();
		
		it=attachmentsList.iterator();
		attachment=(Attachment) it.next();
		
		it=leadItemsList.iterator();
		leadItems=(LeadItemDetails) it.next();
		
		System.out.println(lHeader);
		System.out.println(comments);
		System.out.println(attachment);
		System.out.println(leadItems);
		System.out.println(customer);
		
		String leadID=lHeader.getLEAD_ID();
		String projectType=lHeader.getPROJECT_TYPE();
		
		RequestDispatcher view = request.getRequestDispatcher(UPDATE_LEAD);
        request.setAttribute("leadID", leadID);
        request.setAttribute("selectedprojectType", projectType);
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

