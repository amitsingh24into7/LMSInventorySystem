package com.lms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.lms.dao.AttachmentDao;
import com.lms.dao.CommentsDao;
import com.lms.dao.ConsultantDao;
import com.lms.dao.CustomerDao;
import com.lms.dao.IndentPaymentDao;
import com.lms.dao.LeadHeaderDao;
import com.lms.dao.LeadItemDetailsDao;
import com.lms.dao.LeadItemTechnicalDao;
import com.lms.dao.LeadPricingInformationDao;
import com.lms.dao.LeadScheduleDao;
import com.lms.dao.LeadSequenceDao;
import com.lms.dao.PMCDao;
import com.lms.dao.SupplyScopeDao;
import com.lms.dao.UserRoleDao;
import com.lms.daoImpl.AttachmentDaoImpl;
import com.lms.daoImpl.CommentsDaoImpl;
import com.lms.daoImpl.ConfigMasterDaoImpl;
import com.lms.daoImpl.ConsultantDaoImpl;
import com.lms.daoImpl.CustomerDaoImpl;
import com.lms.daoImpl.IndentPaymentDaoImpl;
import com.lms.daoImpl.LeadHeaderDaoImpl;
import com.lms.daoImpl.LeadItemDetailsDaoImpl;
import com.lms.daoImpl.LeadItemTechnicalDaoImpl;
import com.lms.daoImpl.LeadPricingInformationDaoImpl;
import com.lms.daoImpl.LeadScheduleDaoImpl;
import com.lms.daoImpl.LeadSequenceDaoImpl;
import com.lms.daoImpl.PMCDaoImpl;
import com.lms.daoImpl.SupplyScopeDaoImpl;
import com.lms.daoImpl.UserDaoImpl;
import com.lms.daoImpl.UserRoleDaoImpl;
import com.lms.model.Comments;
import com.lms.model.Customer;
import com.lms.model.IndentPaymentDetails;
import com.lms.model.LeadHeader;
import com.lms.model.LeadItemDetails;

/**
 * Servlet implementation class ValidatorController
 */
public class ValidatorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerDao customerdao;
	private LeadHeaderDao leadheaderdao;
	private CustomerDao customerDao;
	private LeadItemDetailsDao leadItemDetailDao;
	private CommentsDao commenstDao;
	private AttachmentDao attachementDao;
	private ConsultantDao consultantDao;
	private PMCDao pmcDao;
	private LeadScheduleDao leadScheduleDao;

	private LeadItemTechnicalDao leadItemTechnicalDao;
	private LeadPricingInformationDao leadPricingInfoDao;
	private SupplyScopeDao supplyScopeDao;
	private IndentPaymentDao indentPaymentDao;

	public ValidatorController() {
		super();

		customerdao = new CustomerDaoImpl();

		leadheaderdao = new LeadHeaderDaoImpl();
		customerDao = new CustomerDaoImpl();
		leadItemDetailDao = new LeadItemDetailsDaoImpl();
		commenstDao = new CommentsDaoImpl();
		attachementDao = new AttachmentDaoImpl();
		pmcDao = new PMCDaoImpl();
		leadScheduleDao=new LeadScheduleDaoImpl();
		consultantDao = new ConsultantDaoImpl();

		leadItemTechnicalDao = new LeadItemTechnicalDaoImpl();
		leadPricingInfoDao = new LeadPricingInformationDaoImpl();
		supplyScopeDao = new SupplyScopeDaoImpl();
		indentPaymentDao = new IndentPaymentDaoImpl();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String str = "";
		String tableType = request.getParameter("tabletype");
		String leadID = request.getParameter("leadID");
		List ls;
		Iterator it = null;
		List outputList = new ArrayList();

		List leadValidation = new ArrayList();
		if (tableType.equalsIgnoreCase("LEAD")) {
			leadValidation.add("LEAD_HEAD");
			leadValidation.add("CUSTOMER");
			leadValidation.add("PMC");
			leadValidation.add("CONSULTANT");
			leadValidation.add("COMMENTS");
			leadValidation.add("ATTACHMENTS");
			leadValidation.add("COSTSHEET");
			leadValidation.add("LEADITEMS");

			Iterator itValidation = leadValidation.iterator();
			while (itValidation.hasNext()) {
				tableType = itValidation.next().toString();
				if (tableType.equalsIgnoreCase("LEAD_HEAD")) {
					ls = leadheaderdao.getLeadHeadData(leadID);
					System.out.println(ls);
					System.out.println("Total " + ls.size());
					if (ls.size() > 0) {

						str = "yes";
						outputList.add("LEAD_HEAD" + "--" + str);
					} else {
						str = "no";
						outputList.add("LEAD_HEAD" + "--" + str);
					}
				}
				if (tableType.equalsIgnoreCase("CUSTOMER")) {
					ls = customerdao.getCustomerDetailsByLeadID(leadID);
					if (ls.size() > 0) {
						str = "yes";
						outputList.add("CUSTOMER" + "--" + str);
					} else {
						str = "no";
						outputList.add("CUSTOMER" + "--" + str);
					}
				}
				if (tableType.equalsIgnoreCase("PMC")) {
					ls = customerdao.getCustomerDetailsByLeadID(leadID);
					if (ls.size() > 0) {
						it = ls.iterator();
						while (it.hasNext()) {
							Customer cs = new Customer();
							cs = (Customer) it.next();
							if (cs.getPMC().equalsIgnoreCase("")) {
								str = "no";
								outputList.add("PMC" + "--" + str);
							} else {
								str = "yes";
								outputList.add("PMC" + "--" + str);
							}
						}
					} else {
						str = "no";
						outputList.add("PMC" + "--" + str);
					}

				}
				if (tableType.equalsIgnoreCase("CONSULTANT")) {
					ls = customerdao.getCustomerDetailsByLeadID(leadID);
					if (ls.size() > 0) {
						it = ls.iterator();
						while (it.hasNext()) {
							Customer cs = new Customer();
							cs = (Customer) it.next();
							if (cs.getCONSULTANT().equalsIgnoreCase("")) {
								str = "no";
								outputList.add("CONSULTANT" + "--" + str);
							} else {
								str = "yes";
								outputList.add("CONSULTANT" + "--" + str);
							}
						}
					} else {
						str = "no";
						outputList.add("CONSULTANT" + "--" + str);
					}
				}
				if (tableType.equalsIgnoreCase("COMMENTS")) {
					ls = commenstDao.getCommentsByLeadID(leadID);
					if (ls.size() > 0) {
						str = "yes";
						outputList.add("COMMENTS" + "--" + str);
					} else {
						str = "no";
						outputList.add("COMMENTS" + "--" + str);
					}
				}
				if (tableType.equalsIgnoreCase("ATTACHMENTS")) {
					ls = attachementDao.getCommentsAttachmentsByLeadID(leadID);
					if (ls.size() > 0) {
						str = "yes";
						outputList.add("ATTACHMENTS" + "--" + str);
					} else {
						str = "no";
						outputList.add("ATTACHMENTS" + "--" + str);
					}
				}
				if (tableType.equalsIgnoreCase("COSTSHEET")) {
					ls = attachementDao.getCostSheet(leadID);
					if (ls.size() > 0) {
						str = "yes";
						outputList.add("COSTSHEET" + "--" + str);
					} else {
						str = "no";
						outputList.add("COSTSHEET" + "--" + str);
					}
				}
				if (tableType.equalsIgnoreCase("LEADITEMS")) {
					ls = leadItemDetailDao.getLeadItemsByLeadID(leadID);
					if (ls.size() > 0) {
						str = "yes";
						outputList.add("LEADITEMS" + "--" + str);
					} else {
						str = "no";
						outputList.add("LEADITEMS" + "--" + str);
					}
				}

			}
		} else if (tableType.equalsIgnoreCase("DGINDENT")) {

			leadValidation.add("SCHEDULE");
			leadValidation.add("TECH_INFO");
			leadValidation.add("COMMERCIALDG");
			leadValidation.add("COMMERCIALINST");
			leadValidation.add("PAYMENTDG");
			leadValidation.add("PAYMENTINST");
			leadValidation.add("SUPPLY_SCOPE");

			Iterator itValidation = leadValidation.iterator();
			while (itValidation.hasNext()) {
				tableType = itValidation.next().toString();
				
				if (tableType.equalsIgnoreCase("SCHEDULE")) {
					ls = leadScheduleDao.getScheudleByLeadID(leadID);
					System.out.println(ls);

					if (ls.size() > 0) {

						str = "yes";
						outputList.add("SCHEDULE" + "--" + str);
					} else {
						str = "no";
						outputList.add("SCHEDULE" + "--" + str);
					}
				}
				if (tableType.equalsIgnoreCase("TECH_INFO")) {
					List ls1=leadItemDetailDao.getLeadItemsByLeadID(leadID);
					LeadItemDetails ld;
					Iterator itL=ls1.iterator();
					while(itL.hasNext())
					{
						ld= (LeadItemDetails)itL.next();
						int i=ld.getDTL_ID();
						ls=leadItemTechnicalDao.getTechnicalDetails(leadID, i+"");
						
						System.out.println(ls);
	
						if (ls.size() > 0) {
	
							str = "yes";
							
						} else {
							str = "no";
							break;
							
						}
					}
					outputList.add("TECH_INFO" + "--" + str);
				}
				
				if (tableType.equalsIgnoreCase("COMMERCIALDG")) {
					ls = leadPricingInfoDao.getLeadPricingInfo(leadID, "DG");

					if (ls.size() > 0) {
						str = "yes";
						outputList.add("COMMERCIALDG" + "--" + str);
					} else {
						str = "no";
						outputList.add("COMMERCIALDG" + "--" + str);
					}
				}
				if (tableType.equalsIgnoreCase("COMMERCIALINST")) {
					ls = leadPricingInfoDao.getLeadPricingInfo(leadID, "INST");

					if (ls.size() > 0) {
						str = "yes";
						outputList.add("COMMERCIALINST" + "--" + str);
					} else {
						str = "no";
						outputList.add("COMMERCIALINST" + "--" + str);
					}
				}

				if (tableType.equalsIgnoreCase("PAYMENTDG")) {
					ls = indentPaymentDao.getIndentPaymentDetailsByType(leadID,"DG");
					System.out.println("Hey "+ls.size());
					if (ls.size() > 0) {
						str = "yes";
						outputList.add("PAYMENTDG" + "--" + str);

					} else {
						str = "no";
						outputList.add("PAYMENTDG" + "--" + str);
					}
				}
				if (tableType.equalsIgnoreCase("PAYMENTINST")) {
					ls = indentPaymentDao.getIndentPaymentDetailsByType(leadID,"INST");

					if (ls.size() > 0) {

						str = "yes";
						outputList.add("PAYMENTINST" + "--" + str);

					} else {
						str = "no";
						outputList.add("PAYMENTINST" + "--" + str);
					}
				}
				if (tableType.equalsIgnoreCase("SUPPLY_SCOPE")) {
					ls = supplyScopeDao.getSupplyScopeDetails(leadID);
					if (ls.size() > 0) {
						str = "yes";
						outputList.add("SUPPLY_SCOPE" + "--" + str);
					} else {
						str = "no";
						outputList.add("SUPPLY_SCOPE" + "--" + str);
					}
				}

			}

		}

		Gson gson = new Gson();

		JsonElement element = gson.toJsonTree(outputList,
				new TypeToken<List<String>>() {
				}.getType());

		JsonArray jsonArray = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);

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
