package com.lms.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.dao.CustomerDao;
import com.lms.dao.LeadHeaderDao;
import com.lms.daoImpl.CustomerDaoImpl;
import com.lms.daoImpl.LeadHeaderDaoImpl;
import com.lms.model.Customer;
import com.lms.model.LeadHeader;
import com.lms.util.Utility;

/**
 * Servlet implementation class LeadHeaderController
 */
public class LeadHeaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LeadHeaderDao leadheaderdao;
	private CustomerDao customerDao;
	private static String CREATE_LEAD = "/createlead.jsp";
	private static String LIST_LEAD = "/viewlead.jsp";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeadHeaderController() {
        super();
        leadheaderdao=new LeadHeaderDaoImpl();
        customerDao=new CustomerDaoImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LeadHeader lHeader=new LeadHeader();
		Customer cmtr=new Customer();
		HttpSession session=request.getSession();
		
		String leadID=request.getParameter("leadID");
		String actionValue=request.getParameter("actionValue");
		String projectType=request.getParameter("projectType");
		String projectExeState=request.getParameter("projectExeState");
		String projectExePlace=request.getParameter("projectExePlace");
		String leadExeDate=request.getParameter("LeadExeDate");
		
		String dgDeliveryDate=request.getParameter("DGDeliveryDate");
		String projectClosureDate=request.getParameter("ProjectClosureDate");

		String dgType=request.getParameter("dgType");
		String projectReference=request.getParameter("projectReference");
		String segment=request.getParameter("segment");
		String transactionType=request.getParameter("transactionType");
		String enquiryType=request.getParameter("enquiryType");
		String leadStatus=request.getParameter("leadStatus");
		String leadChances=request.getParameter("leadChances");
		
		String competitions=request.getParameter("competitions");
		String ownerID=request.getParameter("ownerID");
		String ownerName=request.getParameter("ownerName");
		
		String createdBy=request.getParameter("createdBy");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String createdDate=dateFormat.format(cal.getTime());
		
		
		lHeader.setLEAD_ID(leadID);
		lHeader.setPROJECT_TYPE(projectType);
		lHeader.setPROJECT_EXE_STATE(projectExeState);
		lHeader.setPROJECT_EXE_PLACE(projectExePlace);
		lHeader.setDG_TYPE(dgType);
		lHeader.setENQUIRY_TYPE(enquiryType);
		lHeader.setPROJECT_REFERENCE(projectReference);
		lHeader.setSEGMENT(segment);
		lHeader.setTRANSACTION_TYPE(transactionType);
		lHeader.setLEAD_STATUS(leadStatus);
		lHeader.setLEAD_CHANCES(leadChances);
		lHeader.setCOMPETITIONS(competitions);
		lHeader.setOWNER_ID(ownerID);
		lHeader.setOWNER_NAME(ownerName);
		lHeader.setDG_DELIVERY_DATE(dgDeliveryDate);
		lHeader.setPROJECT_CLOUSURE_DATE(projectClosureDate);
		
		if(actionValue.equalsIgnoreCase("create"))
		{
			lHeader.setLEAD_BUDG("0");
		}
		else
		{
			lHeader.setLEAD_BUDG("1");
		}
		
		if(actionValue.equalsIgnoreCase("create") || actionValue.equalsIgnoreCase("creatbudgetlead"))
		{
			
			lHeader.setCREATED_BY(createdBy);
			lHeader.setCREATION_DATE(createdDate);
			lHeader.setLEAD_EXECUTION_DATE(leadExeDate);
			lHeader.setLEAD_CREATION_PLACE(session.getAttribute("lcity").toString());
			lHeader.setLEAD_CREATION_REGION(session.getAttribute("lregion").toString());
			lHeader.setLEAD_CREATION_STATE(session.getAttribute("lstate").toString());
			lHeader.setLEAD_CRETION_SHORT_STATE(session.getAttribute("lshortState").toString());

			System.out.println(lHeader);
			leadheaderdao.addLeadHeader(lHeader);

		}
		else
		{
			lHeader.setMODIFIED_BY(createdBy);
			lHeader.setMODIFIED_DATE(createdDate);
			System.out.println(lHeader);
			leadheaderdao.updateLeadHeader(lHeader);
		}
		response.setContentType("text/html");
		response.getWriter().print("Done");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	System.out.println("Hello WOrld");
		LeadHeader lHeader=new LeadHeader();
		Customer cmtr=new Customer();
		String leadID=request.getParameter("leadID");

		String projectType=request.getParameter("projectType");
		String projectExeState=request.getParameter("projectExeState");
		String projectExePlace=request.getParameter("projectExePlace");

		String dgType=request.getParameter("dgType");
		String projectReference=request.getParameter("projectReference");
		String segment=request.getParameter("segment");
		String transactionType=request.getParameter("transactionType");
		String enquiryType=request.getParameter("enquiryType");
		String leadStatus=request.getParameter("leadStatus");
		String leadChances=request.getParameter("leadChances");
		
		String competitions=request.getParameter("competitions");
		
		String ownerID=request.getParameter("ownerID");
		String createdBy=request.getParameter("createdBy");
		
		
		String createdDate=Utility.getCurrentdate();
		
		//Customer Information
		String customerType=request.getParameter("customerType");
		String customerName=request.getParameter("customerName");

		String customerContactNumber=request.getParameter("customerContactNumber");
		String customerAlternateNumber=request.getParameter("customerAlternateNumber");
		String customerEmail=request.getParameter("customerEmail");
		
		String customerConsultant=request.getParameter("customerConsultant");
		String customerConsultantNumber=request.getParameter("customerConsultantNumber");
		String customerConsultantPersonName=request.getParameter("customerConsultantPersonName");
		
		String customerBillingAddress1=request.getParameter("customerBillingAddress1");
		String customerBillingAddress2=request.getParameter("customerBillingAddress2");
		String customerBillingAddress3=request.getParameter("customerBillingAddress3");
		String customerBillingAddress4=request.getParameter("customerBillingAddress4");
		String customerDeliveryAddress1=request.getParameter("customerDeliveryAddress1");
		String customerDeliveryAddress2=request.getParameter("customerDeliveryAddress2");
		String customerDeliveryAddress3=request.getParameter("customerDeliveryAddress3");
		String customerDeliveryAddress4=request.getParameter("customerDeliveryAddress4");
		
		String customerECCNO=request.getParameter("customerECCNO");
		String customerCSTNO=request.getParameter("customerCSTNO");
		String customerTINNO=request.getParameter("customerTINNO");
		String customerIECNO=request.getParameter("customerIECNO");
		String pmc=request.getParameter("pmc");
		String pmcContact=request.getParameter("pmcContact");
		String pmcAlternateContact=request.getParameter("pmcAlternateContact");
		String pmcEmail=request.getParameter("pmcEmail");
		String pmcPerson=request.getParameter("pmcPerson");
		
		
		String customergroup=request.getParameter("customergroup");
		String customergroup_person=request.getParameter("customergroup_person");
		String customergroup_person_contact=request.getParameter("customergroup_person_contact");
			

		
		/*LEAD_ID, LEAD_TYPE, TRANSACTION_TYPE, PROJECT_TYPE, LEAD_BUDG, PROJECT_EXE_PLACE,
		PROJECT_EXE_STATE, CREATION_DATE, CREATED_BY, OWNER_ID,PROJECT_REFERENCE, 
		DG_TYPE, SEGMENT, COMPETITIONS, LEAD_CHANCES, LEAD_STATUS*/
		
		lHeader.setLEAD_ID(leadID);
		lHeader.setLEAD_BUDG("0");
		lHeader.setPROJECT_TYPE(projectType);
		lHeader.setPROJECT_EXE_STATE(projectExeState);
		lHeader.setPROJECT_EXE_PLACE(projectExePlace);
		lHeader.setDG_TYPE(dgType);
		lHeader.setPROJECT_REFERENCE(projectReference);
		lHeader.setSEGMENT(segment);
		lHeader.setTRANSACTION_TYPE(transactionType);
		lHeader.setLEAD_STATUS(leadStatus);
		lHeader.setLEAD_CHANCES(leadChances);
		lHeader.setCOMPETITIONS(competitions);
		lHeader.setOWNER_ID(ownerID);
		lHeader.setCREATED_BY(createdBy);
		lHeader.setCREATION_DATE(createdDate);
		
		//Customer Details in Lead Header
		lHeader.setCUSTOMER_NAME(customerName);
		lHeader.setCUSTOMER_EMAIL_ADDRESS(customerEmail);
		lHeader.setCUSTOMER_MOBILENO(customerContactNumber);
		lHeader.setCUSTOMER_ALTERNATE_NO(customerAlternateNumber);
		lHeader.setCUSTOMER_ADDRESS(customerBillingAddress1+" "+customerBillingAddress2+" "+customerBillingAddress3+" "+customerBillingAddress4);
		
		
		//End Person Customer Contact Person
		
		lHeader.setEND_PERSON_CUSTOMER_NAME(customergroup_person);
		lHeader.setEND_PERSON_CUSTOMER_CONTACTNO(customergroup_person_contact);
		
		//Consultant Details in Lead Header
		lHeader.setCONSULTANT_NAME(customerConsultant);
		//Need to Add Consultant Person Name Input Box in CreatLead.jsp 
		lHeader.setCONSULTANT_PERSON_NAME(customerConsultantPersonName);
		lHeader.setCONSULTANT_MOBILE_NO(customerConsultantNumber);
		
		//PMC Details in Lead Header
		lHeader.setPMC_NAME(pmc);
		//Need to Add PMC COntact Person Name Input Box in CreatLead.jsp
		lHeader.setPMC_CONTACT_PERSON(pmcPerson);
		lHeader.setPMC_CONTACT_NO(pmcContact);
		lHeader.setPMC_CONTACT_ALT_NO(pmcAlternateContact);
		lHeader.setPMC_EMAIL(pmcEmail);
		
		
		cmtr.setLEAD_ID(leadID);
		cmtr.setCUSTOMER_NAME(customerName);
		cmtr.setCONTACT_NUMBER(customerContactNumber);
		cmtr.setALTERNATE_NO(customerAlternateNumber);
		cmtr.setEMAIL_ADDRESS(customerEmail);
		cmtr.setCONSULTANT(customerConsultant);
		cmtr.setCONSULTANT_PERSON_NAME(customerConsultantPersonName);
		cmtr.setCONSULTANT_CONTACT(customerConsultantNumber);
		cmtr.setBILLING_ADDRESS1(customerBillingAddress1);
		cmtr.setBILLING_ADDRESS2(customerBillingAddress2);
		cmtr.setBILLING_ADDRESS3(customerBillingAddress3);
		cmtr.setBILLING_ADDRESS4(customerBillingAddress4);
		cmtr.setDELIVERY_ADDRESS1(customerDeliveryAddress1);
		cmtr.setDELIVERY_ADDRESS2(customerDeliveryAddress2);
		cmtr.setDELIVERY_ADDRESS3(customerDeliveryAddress3);
		cmtr.setDELIVERY_ADDRESS4(customerDeliveryAddress4);
		cmtr.setCUSTOMER_GROUP(customergroup);
		cmtr.setCUSTOMER_GROUP_CONTACT_PERSON(customergroup_person);
		cmtr.setCUSTOMER_GROUP_CONTACT_NUMBER(customergroup_person_contact);
		cmtr.setECCNO(customerECCNO);
		cmtr.setCSTNO(customerCSTNO);
		cmtr.setTINNO(customerTINNO);
		cmtr.setIECNO(customerIECNO);
		cmtr.setPMC(pmc);
		cmtr.setPMC_PERSON_NAME(pmcPerson);
		cmtr.setPMC_CONTACT(pmcContact);
		cmtr.setPMC_ALTERNATE_CONTACT(pmcAlternateContact);
		cmtr.setPMC_EMAIL(pmcEmail);
		cmtr.setCREATED_BY(createdBy);
		cmtr.setCRETAED_DATE(createdDate);
		

		
		
		System.out.println(lHeader.toString());
		System.out.println(cmtr.toString());
		customerDao.addCustomer(cmtr);
		leadheaderdao.addLeadHeader(lHeader);
		
		
		RequestDispatcher view = request.getRequestDispatcher(LIST_LEAD);
       // request.setAttribute("leaddetails", leadheaderdao.getLeadHeadDataDetails("Pending",Utility.getFirstDayofCurrentMonth(),Utility.getLastDayofCurrentMonth()));
        view.forward(request, response);
		
		
		
		
		
		
	}
	

}
