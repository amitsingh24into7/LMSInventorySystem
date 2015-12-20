package com.lms.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.dao.AttachmentDao;
import com.lms.dao.CommentsDao;
import com.lms.dao.CustomerDao;
import com.lms.dao.LeadHeaderDao;
import com.lms.dao.LeadItemDetailsDao;
import com.lms.dao.LeadScheduleDao;
import com.lms.dao.LeadSequenceDao;
import com.lms.daoImpl.AttachmentDaoImpl;
import com.lms.daoImpl.CommentsDaoImpl;
import com.lms.daoImpl.CustomerDaoImpl;
import com.lms.daoImpl.LeadHeaderDaoImpl;
import com.lms.daoImpl.LeadItemDetailsDaoImpl;
import com.lms.daoImpl.LeadScheduleDaoImpl;
import com.lms.daoImpl.LeadSequenceDaoImpl;
import com.lms.model.Customer;
import com.lms.model.LeadHeader;
import com.lms.model.LeadItemDetails;

/**
 * Servlet implementation class ConvertBudgetToLead
 */
public class ConvertBudgetToLead extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	private LeadSequenceDao leadsequenceDao;
	private LeadHeaderDao leadHeaderDao;
	private CustomerDao customerDao;
	private LeadItemDetailsDao leadItemDetailsDao;
	private AttachmentDao attachmentDao;
	private CommentsDao commenstDao;
	private LeadScheduleDao leadScheduleDao;

	LeadHeader lHeader = new LeadHeader();
	Customer customer = new Customer();
	LeadItemDetails leadItems = new LeadItemDetails();

	List<LeadHeader> leadHeaderList = new ArrayList<LeadHeader>();
	List<Customer> customerList = new ArrayList<Customer>();
	List<LeadItemDetails> leadItemsList = new ArrayList<LeadItemDetails>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConvertBudgetToLead() {
		super();
		leadsequenceDao = new LeadSequenceDaoImpl();
		leadHeaderDao = new LeadHeaderDaoImpl();
		customerDao = new CustomerDaoImpl();
		leadItemDetailsDao = new LeadItemDetailsDaoImpl();
		attachmentDao = new AttachmentDaoImpl();
		commenstDao = new CommentsDaoImpl();
		leadScheduleDao = new LeadScheduleDaoImpl();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String budgetLead = request.getParameter("budgetLead");
		session = request.getSession();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String createdDate = dateFormat.format(cal.getTime());
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String projectType = budgetLead.substring(2, 4);

		String shortState = budgetLead.substring(4, 6);
		String leadNo = leadsequenceDao
				.getLeadNo(year, projectType, shortState);
		String createdBy = session.getAttribute("luserId").toString();

		// lHeader.setMODIFIED_BY(session.getAttribute("luserId").toString());

		leadHeaderDao.updateLeadHeadLeadID(budgetLead, leadNo, createdDate,
				createdBy);
		customerDao.updateCustomerLeadID(budgetLead, leadNo, createdDate,
				createdBy);
		leadItemDetailsDao.updateLeadItemsLeadID(budgetLead, leadNo,
				createdDate, createdBy);
		attachmentDao.updateAttachmentLeadID(budgetLead, leadNo, createdDate,
				createdBy);
		commenstDao.updateCommentsLeadID(budgetLead, leadNo, createdDate,
				createdBy);
		leadScheduleDao.updateLeadScheduleLeadID(budgetLead, leadNo,
				createdDate, createdBy);
		response.setContentType("text/html");
		response.getWriter().print(leadNo);

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
