package com.lms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.ConsultantDao;
import com.lms.dao.CustomerDao;
import com.lms.dao.LeadHeaderDao;
import com.lms.dao.PMCDao;
import com.lms.daoImpl.ConsultantDaoImpl;
import com.lms.daoImpl.CustomerDaoImpl;
import com.lms.daoImpl.LeadHeaderDaoImpl;
import com.lms.daoImpl.PMCDaoImpl;
import com.lms.model.ConsultantMaster;
import com.lms.model.Customer;
import com.lms.model.LeadHeader;
import com.lms.model.PmcMaster;
import com.lms.util.Utility;

public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LeadHeaderDao leadheaderdao;
	private CustomerDao customerDao;
	private PMCDao pmcDao;
	private ConsultantDao consultantDao;

	private static String CREATE_LEAD = "/createlead.jsp";
	private static String LIST_LEAD = "/viewlead.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerController() {
		super();
		leadheaderdao = new LeadHeaderDaoImpl();
		customerDao = new CustomerDaoImpl();
		pmcDao = new PMCDaoImpl();
		consultantDao = new ConsultantDaoImpl();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LeadHeader lHeader = new LeadHeader();
		Customer cmtr = new Customer();

		String leadID = request.getParameter("leadID");
		String createdBy = request.getParameter("createdBy");
		String createdDate = Utility.getCurrentdate();
		// Customer Information
		String customerType = request.getParameter("customerType");
		String customerName = request.getParameter("customerName");
		String actionValue = request.getParameter("actionValue");

		String customerContactNumber = request
				.getParameter("customerContactNumber");
		String customerAlternateNumber = request
				.getParameter("customerAlternateNumber");
		String customerEmail = request.getParameter("customerEmail");

		String customerConsultant = request.getParameter("customerConsultant");
		String customerConsultantNumber = request
				.getParameter("customerConsultantNumber");
		String customerConsultantPersonName = request
				.getParameter("customerConsultantPersonName");

		String customerTempAddress = request
				.getParameter("customerTempAddress");
		;
		String customerBillingAddress1 = request
				.getParameter("customerBillingAddress1");
		String customerBillingAddress2 = request
				.getParameter("customerBillingAddress2");
		String customerBillingAddress3 = request
				.getParameter("customerBillingAddress3");
		String customerBillingAddress4 = request
				.getParameter("customerBillingAddress4");
		String customerDeliveryAddress1 = request
				.getParameter("customerDeliveryAddress1");
		String customerDeliveryAddress2 = request
				.getParameter("customerDeliveryAddress2");
		String customerDeliveryAddress3 = request
				.getParameter("customerDeliveryAddress3");
		String customerDeliveryAddress4 = request
				.getParameter("customerDeliveryAddress4");

		String customerECCNO = request.getParameter("customerECCNO");
		String customerCSTNO = request.getParameter("customerCSTNO");
		String customerTINNO = request.getParameter("customerTINNO");
		String customerIECNO = request.getParameter("customerIECNO");
		String pmc = request.getParameter("pmc");
		String pmcContact = request.getParameter("pmcContact");
		String pmcAlternateContact = request
				.getParameter("pmcAlternateContact");
		String pmcEmail = request.getParameter("pmcEmail");
		String pmcPerson = request.getParameter("pmcPerson");

		String customergroup = request.getParameter("customergroup");
		String customergroup_person = request
				.getParameter("customergroup_person");
		String customergroup_person_contact = request
				.getParameter("customergroup_person_contact");

		// Customer Details in Lead Header
		lHeader.setLEAD_ID(leadID);
		lHeader.setCUSTOMER_NAME(customerName);
		lHeader.setCUSTOMER_EMAIL_ADDRESS(customerEmail);
		lHeader.setCUSTOMER_MOBILENO(customerContactNumber);
		lHeader.setCUSTOMER_ALTERNATE_NO(customerAlternateNumber);
		lHeader.setCUSTOMER_ADDRESS(customerTempAddress);

		// End Person Customer Contact Person

		lHeader.setEND_PERSON_CUSTOMER_NAME(customergroup_person);
		lHeader.setEND_PERSON_CUSTOMER_CONTACTNO(customergroup_person_contact);

		// Consultant Details in Lead Header
		lHeader.setCONSULTANT_NAME(customerConsultant);
		// Need to Add Consultant Person Name Input Box in CreatLead.jsp
		lHeader.setCONSULTANT_PERSON_NAME(customerConsultantPersonName);
		lHeader.setCONSULTANT_MOBILE_NO(customerConsultantNumber);

		// PMC Details in Lead Header
		lHeader.setPMC_NAME(pmc);
		// Need to Add PMC COntact Person Name Input Box in CreatLead.jsp
		lHeader.setPMC_CONTACT_PERSON(pmcPerson);
		lHeader.setPMC_CONTACT_NO(pmcContact);
		lHeader.setPMC_CONTACT_ALT_NO(pmcAlternateContact);
		lHeader.setPMC_EMAIL(pmcEmail);
		lHeader.setMODIFIED_BY(createdBy);
		lHeader.setMODIFIED_DATE(createdDate);

		// Customer Object Creation
		cmtr.setLEAD_ID(leadID);
		cmtr.setCUSTOMER_NAME(customerName);
		cmtr.setCONTACT_NUMBER(customerContactNumber);
		cmtr.setCUSTOMER_TEMP_ADDRESS(customerTempAddress);
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

		System.out.println(lHeader);

		System.out.println(cmtr);

		leadheaderdao.updateLeadCustomerInformation(lHeader);

		if (actionValue.equalsIgnoreCase("create") || actionValue.equalsIgnoreCase("creatbudgetlead")) {
			cmtr.setCREATED_BY(createdBy);
			cmtr.setCRETAED_DATE(createdDate);

			System.out.println("Hello "+cmtr);
			customerDao.addCustomer(cmtr);

			// Add PMC Details

		} else {
			cmtr.setMODIFIED_BY(createdBy);
			cmtr.setMODIFIED_DATE(createdDate);
			System.out.println(cmtr);
			customerDao.updateCustomer(cmtr);

		}

		if (customerType.equalsIgnoreCase("New")) {
			cmtr.setLEAD_ID("NO");
			customerDao.addMasterCustomer(cmtr);

		}
		String pmcType = request.getParameter("pmcType");
		String customerConsultantType = request
				.getParameter("customerConsultantType");
		if (pmcType.equalsIgnoreCase("New")) {
			PmcMaster pmcMaster = new PmcMaster();

			pmcMaster.setPMC_NAME(pmc);
			pmcMaster.setPMC_CONTACT_PERSON(pmcPerson);
			pmcMaster.setPMC_CONTACT_MOBILE(pmcContact);
			pmcMaster.setPMC_ALTERNATE_MOBILE(pmcAlternateContact);
			pmcMaster.setPMC_EMAIL(pmcEmail);
			pmcMaster.setCREATED_BY(createdBy);
			pmcMaster.setCRETAED_DATE(createdDate);
			pmcDao.addPMCDetails(pmcMaster);

		}
		if (customerConsultantType.equalsIgnoreCase("New")) {
			ConsultantMaster consultantMaster = new ConsultantMaster();

			consultantMaster.setCONSULATNT_NAME(customerConsultant);
			consultantMaster
					.setCONSULATNT_CONTACT_PERSON(customerConsultantPersonName);
			consultantMaster
					.setCONSULATNT_CONTACT_MOBILE(customerConsultantNumber);

			consultantMaster.setCREATED_BY(createdBy);
			consultantMaster.setCRETAED_DATE(createdDate);
			consultantDao.addConsultantDetails(consultantMaster);

		}
		// Add

		response.setContentType("text/html");
		response.getWriter().print("Done");

	}

}
