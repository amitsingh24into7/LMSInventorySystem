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
import javax.servlet.http.HttpSession;

import com.lms.dao.AttachmentDao;
import com.lms.dao.CommentsDao;
import com.lms.dao.ConfigMasterDao;
import com.lms.dao.ConsultantDao;
import com.lms.dao.CustomerDao;
import com.lms.dao.LeadHeaderDao;
import com.lms.dao.LeadItemDetailsDao;
import com.lms.dao.LeadSequenceDao;
import com.lms.dao.PMCDao;
import com.lms.dao.UserDao;
import com.lms.dao.UserRoleDao;
import com.lms.daoImpl.AttachmentDaoImpl;
import com.lms.daoImpl.CommentsDaoImpl;
import com.lms.daoImpl.ConfigMasterDaoImpl;
import com.lms.daoImpl.ConsultantDaoImpl;
import com.lms.daoImpl.CustomerDaoImpl;
import com.lms.daoImpl.LeadHeaderDaoImpl;
import com.lms.daoImpl.LeadItemDetailsDaoImpl;
import com.lms.daoImpl.LeadSequenceDaoImpl;
import com.lms.daoImpl.PMCDaoImpl;
import com.lms.daoImpl.UserDaoImpl;
import com.lms.daoImpl.UserRoleDaoImpl;
import com.lms.model.Attachment;
import com.lms.model.Comments;
import com.lms.model.ConfigMaster;
import com.lms.model.ConsultantMaster;
import com.lms.model.Customer;
import com.lms.model.LeadHeader;
import com.lms.model.LeadItemDetails;
import com.lms.model.PmcMaster;
import com.lms.model.UserRole;
import com.lms.util.Utility;

public class MainController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static String CREATE_LEAD = "/createlead.jsp";
	private static String CREATE_PN_LEAD = "/createPNlead.jsp";
	private static String UPDATE_LEAD = "updateLead.jsp";
	private static String UPDATE_PN_LEAD = "updatePNLead.jsp";
	private static String LIST_LEAD = "/viewlead.jsp";

	private static String LIST_BUD_LEAD = "/viewbudgetlead.jsp";
	private static String LIST_QUALIFIED_LEAD = "/viewqualifiedlead.jsp";
	private static String LIST_INDENT = "/viewindents.jsp";

	private static String CREATE_INDENT = "/createindent.jsp";
	private static String UPDATE_INDENT = "/updateindent.jsp";
	

	private ConfigMasterDao configdao;
	private UserDao userdao;
	private UserRoleDao userRoleDao;
	private CustomerDao customerdao;
	private LeadSequenceDao leadsequenceDao;
	private LeadHeaderDao leadheaderdao;
	private CustomerDao customerDao;
	private LeadItemDetailsDao leadItemDetailDao;
	private CommentsDao commenstDao;
	private AttachmentDao attachementDao;
	private ConsultantDao consultantDao;
	private PMCDao pmcDao;
	private static String leadid = "";
	String businessFile = "PN";
	HttpSession session;

	/*-------UserDetails---------- */

	String userName;
	String userID;
	String userRole;
	String userRegion;
	String userState;
	String userShortState;
	String userDCity;

	/*----------------*/
	List<String> customerNames = new ArrayList<String>();
	List<String> pmcNames = new ArrayList<String>();
	List<String> consultantNames = new ArrayList<String>();

	List<String> projectType = new ArrayList<String>();
	List<String> dgType = new ArrayList<String>();
	List<String> ratingTypes = new ArrayList<String>();

	List<String> engineMakeType = new ArrayList<String>();
	List<String> engineModelType = new ArrayList<String>();
	List<String> alternatorMakeType = new ArrayList<String>();
	List<String> alternatorModelType = new ArrayList<String>();
	List<String> voltgaeType = new ArrayList<String>();

	List<String> hzType = new ArrayList<String>();
	List<String> qtyType = new ArrayList<String>();
	List<String> chancesType = new ArrayList<String>();
	List<String> statusType = new ArrayList<String>();
	List<String> coolingSystemType = new ArrayList<String>();
	List<String> segmentType = new ArrayList<String>();
	List<String> enquiryType = new ArrayList<String>();
	List<String> customerType = new ArrayList<String>();
	List<String> pmcTypeList = new ArrayList<String>();
	List<String> consultantTypeList = new ArrayList<String>();

	List<String> transactionType = new ArrayList<String>();
	List<String> exe_city = new ArrayList<String>();
	List<String> exe_state = new ArrayList<String>();
	List<String> regionType = new ArrayList<String>();

	List<String> roleName = new ArrayList<String>();
	String pmcType = "", consultantType = "";
	List<ConfigMaster> cMasters = new ArrayList<ConfigMaster>();

	public void clearList() {
		// Clear All List

		projectType.clear();
		dgType.clear();

		ratingTypes.clear();

		engineMakeType.clear();
		engineModelType.clear();
		alternatorMakeType.clear();
		alternatorModelType.clear();
		voltgaeType.clear();

		hzType.clear();
		qtyType.clear();
		chancesType.clear();
		statusType.clear();
		coolingSystemType.clear();
		segmentType.clear();
		enquiryType.clear();
		customerType.clear();

		transactionType.clear();
		consultantTypeList.clear();
		pmcTypeList.clear();

		exe_city.clear();
		exe_state.clear();
		regionType.clear();
		roleName.clear();
		customerNames.clear();
		pmcNames.clear();
		pmcNames.clear();
		/*****************************************/
	}

	public MainController() {
		super();
		configdao = new ConfigMasterDaoImpl();
		customerdao = new CustomerDaoImpl();
		leadsequenceDao = new LeadSequenceDaoImpl();
		leadheaderdao = new LeadHeaderDaoImpl();
		customerDao = new CustomerDaoImpl();
		leadItemDetailDao = new LeadItemDetailsDaoImpl();
		commenstDao = new CommentsDaoImpl();
		attachementDao = new AttachmentDaoImpl();
		userdao = new UserDaoImpl();
		userRoleDao = new UserRoleDaoImpl();
		pmcDao = new PMCDaoImpl();
		consultantDao = new ConsultantDaoImpl();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forward = "";

		String action = request.getParameter("action");
		String businessFile = request.getParameter("businessFile");
		session = request.getSession();

		System.out.println(session);

		userName = (String) session.getAttribute("luserName");

		userID = (String) session.getAttribute("luserId");
		userRole = (String) session.getAttribute("lroleId");
		userRegion = (String) session.getAttribute("lregion");
		userState = (String) session.getAttribute("lstate");
		userShortState = (String) session.getAttribute("lshortState");
		userDCity = (String) session.getAttribute("lcity");

		List<Customer> cus = customerdao.getAllCustomerNames();
		customerNames.clear();
		pmcNames.clear();
		consultantNames.clear();

		Iterator it1 = cus.iterator();
		while (it1.hasNext()) {
			Customer c = (Customer) it1.next();
			customerNames.add(c.getCUSTOMER_NAME());
		}

		List<PmcMaster> pmcMaster = pmcDao.getPMCName();

		Iterator itPmc = pmcMaster.iterator();
		while (itPmc.hasNext()) {
			PmcMaster c = (PmcMaster) itPmc.next();
			pmcNames.add(c.getPMC_NAME());
		}

		List<ConsultantMaster> consutantMaster = consultantDao
				.getConsultantName();

		Iterator itCon = consutantMaster.iterator();
		while (itCon.hasNext()) {
			ConsultantMaster c = (ConsultantMaster) itCon.next();
			consultantNames.add(c.getCONSULATNT_NAME());
		}

		System.out.println("Size"+cMasters.size());
		if (cMasters.size() == 0) {
			cMasters = configdao.getConfigMasterData();
			clearList();

			// if(cMasters.get(index))
			// System.out.println(cMasters.get(1));
			Iterator it = cMasters.iterator();

			while (it.hasNext()) {
				ConfigMaster c = (ConfigMaster) it.next();
				System.out.println(c);
				if (c.getName().equalsIgnoreCase("CONPMC_TYPE")) {
					pmcTypeList.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("PROJECT_TYPE")) {
					projectType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("TYPE_OF_DG")) {
					dgType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("RATING")) {
					ratingTypes.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("ENGINE_MAKE")) {
					engineMakeType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("ENGINE_MODEL")) {
					engineModelType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("ALTERNATOR_MAKE")) {
					alternatorMakeType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("ALTERNATOR_MODEL")) {
					alternatorModelType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("VOLTAGE")) {
					voltgaeType.add(c.getValue());
				}

				if (c.getName().equalsIgnoreCase("HZ")) {
					hzType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("QTY")) {
					qtyType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("CHANCES_TYPE")) {
					chancesType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("STATUS_TYPE")) {
					statusType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("COOLING_SYSTEM")) {
					coolingSystemType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("SEGMENT")) {
					segmentType.add(c.getValue());
				}

				if (c.getName().equalsIgnoreCase("ENQUIRY_TYPE")) {
					enquiryType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("CUSTOMER_TYPE")) {
					customerType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("TYPE_OF_TRANSACTION")) {
					transactionType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("EXE_CITY")) {
					exe_city.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("EXE_STATE")) {
					exe_state.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("REGION_TYPE")) {
					regionType.add(c.getValue());
				}
				if (c.getName().equalsIgnoreCase("ROLE_NAME")) {
					roleName.add(c.getValue());
				}
			}
		}
		// System.out.println(projectType);
		// Common Drop DOwn Values
		request.setAttribute("customerNames", customerNames);
		request.setAttribute("consultantNames", consultantNames);
		request.setAttribute("pmcNames", pmcNames);

		request.setAttribute("projectTypes", projectType);
		request.setAttribute("dgTypes", dgType);
		request.setAttribute("ratingTypes", ratingTypes);

		request.setAttribute("engineMakeType", engineMakeType);
		request.setAttribute("engineModelType", engineModelType);
		request.setAttribute("alternatorMakeType", alternatorMakeType);
		request.setAttribute("alternatorModelType", alternatorModelType);
		request.setAttribute("voltgaeType", voltgaeType);

		request.setAttribute("hzType", hzType);
		request.setAttribute("qtyType", qtyType);
		request.setAttribute("chancesType", chancesType);
		request.setAttribute("statusType", statusType);
		request.setAttribute("coolingSystemType", coolingSystemType);
		request.setAttribute("segmentType", segmentType);
		request.setAttribute("enquiryType", enquiryType);
		request.setAttribute("customerType", customerType);
		request.setAttribute("pmcTypeList", pmcTypeList);

		request.setAttribute("transactionType", transactionType);
		request.setAttribute("exe_city", exe_city);
		request.setAttribute("exe_state", exe_state);
		request.setAttribute("regionType", regionType);

		request.setAttribute("userID", userID);
		request.setAttribute("userName", userName);

		request.setAttribute("roleName", roleName);

		if (action.equalsIgnoreCase("creatlead")) {

			request.setAttribute("create", "create");
			request.setAttribute("sel_state", userState);
			request.setAttribute("sel_city", userDCity);
			

			forward = CREATE_LEAD;
			if (businessFile.equalsIgnoreCase("PN")) {
				request.setAttribute("selbusinessFile", "Project");
				forward = CREATE_PN_LEAD;
			} else {
				request.setAttribute("selbusinessFile", "0");

			}

		} else if (action.equalsIgnoreCase("creatbudgetlead")) {

			/*
			 * int year = Calendar.getInstance().get(Calendar.YEAR); String
			 * runningSequence = leadsequenceDao.getRunningSequence(year + "",
			 * businessFile); leadsequenceDao.updateSequenceNumber(year + "",
			 * businessFile); leadid = (year % 100) + userShortState +
			 * businessFile + runningSequence;
			 * 
			 * System.out.println(leadid);
			 */

			request.setAttribute("leadID", leadid);
			request.setAttribute("create", "creatbudgetlead");

			forward = CREATE_LEAD;
			if (businessFile.equalsIgnoreCase("PN")) {
				forward = CREATE_PN_LEAD;
				request.setAttribute("selbusinessFile", "Project");
			} else {
				request.setAttribute("selbusinessFile", "0");

			}

		} else if (action.equalsIgnoreCase("edit")
				|| action.equalsIgnoreCase("view")
				|| action.equalsIgnoreCase("createIndent")||action.equalsIgnoreCase("updateIndent")||action.equalsIgnoreCase("Draft")) {

			leadid = request.getParameter("leadid");
			request.setAttribute("leadID", leadid);
			//Get All Details for Common (lead and indent)
			List<LeadHeader> leadHeaderList = new ArrayList<LeadHeader>();
			List<Customer> customerList = new ArrayList<Customer>();
			List<LeadItemDetails> leadItemsList = new ArrayList<LeadItemDetails>();
			List<Comments> commentsList = new ArrayList<Comments>();
			List<Attachment> attachmentsList = new ArrayList<Attachment>();
			List<Attachment> costSheetList = new ArrayList<Attachment>();

			leadHeaderList = leadheaderdao.getLeadHeadData(leadid);
			customerList = customerDao.getCustomerDetailsByLeadID(leadid);
			leadItemsList = leadItemDetailDao.getLeadItemsByLeadID(leadid);
			commentsList = commenstDao.getCommentsByLeadID(leadid);
			attachmentsList = attachementDao.getOtherAttachement(leadid);
			costSheetList = attachementDao.getCostSheet(leadid);

			Iterator itList;
			LeadHeader lHeader = new LeadHeader();
			Comments comments = new Comments();
			Attachment attachment = new Attachment();

			LeadItemDetails leadItems = new LeadItemDetails();
			Customer customer = new Customer();

			itList = leadHeaderList.iterator();
			lHeader = (LeadHeader) itList.next();

			if(customerList.size()>0)
			{
				itList = customerList.iterator();
				customer = (Customer) itList.next();
			}
			

			request.setAttribute("attachmentsList", attachmentsList);
			request.setAttribute("costSheetList", costSheetList);
			request.setAttribute("leadItemsList", leadItemsList);
			request.setAttribute("commentsList", commentsList);

			// itList=commentsList.iterator();
			// comments=(Comments) itList.next();

			// itList=attachmentsList.iterator();
			// attachment=(Attachment) itList.next();

			// itList=leadItemsList.iterator();
			// leadItems=(LeadItemDetails) itList.next();

			System.out.println("Hello Sree" + lHeader);
			// System.out.println(comments);
			System.out.println(attachment);
			// System.out.println(leadItems);
			System.out.println(customer);

			String leadID = lHeader.getLEAD_ID();
			String leadprojectType = lHeader.getPROJECT_TYPE();

			String sel_state = lHeader.getPROJECT_EXE_STATE();
			String sel_city = lHeader.getPROJECT_EXE_PLACE();
			String sel_dgType = lHeader.getDG_TYPE();
			String sel_proj_ref = lHeader.getPROJECT_REFERENCE();
			String sel_segment = lHeader.getSEGMENT();
			String sel_transaction_type = lHeader.getTRANSACTION_TYPE();
			String sel_lead_status = lHeader.getLEAD_STATUS();
			String sel_lead_Chance = lHeader.getLEAD_CHANCES();
			String sel_comp = lHeader.getCOMPETITIONS();
			String lead_owner_id = lHeader.getOWNER_ID();
			String lead_owner_name = lHeader.getOWNER_NAME();
			String sel_enq_type = lHeader.getENQUIRY_TYPE();
			String createdBy = lHeader.getCREATED_BY();
			String leadExeDate = lHeader.getLEAD_EXECUTION_DATE();
			String dgDeliveryDate = lHeader.getDG_DELIVERY_DATE();
			String projectClosureDate = lHeader.getPROJECT_CLOUSURE_DATE();
			
			//Indent Details


			// Get Amount Details
			String DGValue = lHeader.getDG_ED_VALUE();
			long projecttotal = (long) lHeader.getPROJECT_TOTAL();
			long lowsidesupbasic = (long) lHeader.getLOW_SIDE_SUPPLY_BASIC();

			long lowsidelabbasic = (long) lHeader.getLOW_SIDE_LABOUR_BASIC();

			long lowsidesmargin = (long) lHeader.getLOW_SIDE_MARGIN();

			long projectmargin = (long) lHeader.getPROJECT_MARGIN();

			// Get Customer Details from DB
			String customerName="",customerContactNumber="",customerAlternateNumber="",customerEmail="",
			customerTempAddress="",customerConsultant="",customerConsultantPersonName="",
			customerConsultantNumber="";
			String customerBillingAddress1="";
			String customerBillingAddress2="";
			String customerBillingAddress3="";
			String customerBillingAddress4="";
			String customerDeliveryAddress1="";
			String customerDeliveryAddress2="";
			String customerDeliveryAddress3="";
			String customerDeliveryAddress4="";
			String customergroup="";
			String customergroup_person="";
			String customergroup_person_contact="";
			String customerECCNO="";
			String customerCSTNO="";
			String customerTINNO="";
			String customerIECNO="";

			String pmc="";
			String pmcPerson="";
			String pmcContact="";
			String pmcAlternateContact="";
			String pmcEmail="";
			
			if(customerList.size()>0)
			{
					customerName = customer.getCUSTOMER_NAME();
					customerContactNumber = customer.getCONTACT_NUMBER();
					customerAlternateNumber = customer.getALTERNATE_NO();
					customerEmail = customer.getEMAIL_ADDRESS();
					customerTempAddress = customer.getCUSTOMER_TEMP_ADDRESS();
		
					customerConsultant = customer.getCONSULTANT();
		
					customerConsultantPersonName = customer
							.getCONSULTANT_PERSON_NAME();
					customerConsultantNumber = customer.getCONSULTANT_CONTACT();
		
					if (customerConsultant.equalsIgnoreCase("")
							&& customerConsultantPersonName.equalsIgnoreCase("")
							&& customerConsultantNumber.equalsIgnoreCase("")) {
						consultantType = "None";
					} else {
						consultantType = "Existing";
					}
		
					 customerBillingAddress1 = customer.getBILLING_ADDRESS1();
					 customerBillingAddress2 = customer.getBILLING_ADDRESS2();
					 customerBillingAddress3 = customer.getBILLING_ADDRESS3();
					 customerBillingAddress4 = customer.getBILLING_ADDRESS4();
					 customerDeliveryAddress1 = customer.getDELIVERY_ADDRESS1();
					 customerDeliveryAddress2 = customer.getDELIVERY_ADDRESS2();
					 customerDeliveryAddress3 = customer.getDELIVERY_ADDRESS3();
					 customerDeliveryAddress4 = customer.getDELIVERY_ADDRESS4();
					 customergroup = customer.getCUSTOMER_GROUP();
					 customergroup_person = customer
							.getCUSTOMER_GROUP_CONTACT_PERSON();
					 customergroup_person_contact = customer
							.getCUSTOMER_GROUP_CONTACT_NUMBER();
					 customerECCNO = customer.getECCNO();
					 customerCSTNO = customer.getCSTNO();
					 customerTINNO = customer.getTINNO();
					 customerIECNO = customer.getIECNO();
		
					 pmc = customer.getPMC();
					 pmcPerson = customer.getPMC_PERSON_NAME();
					 pmcContact = customer.getPMC_CONTACT();
					 pmcAlternateContact = customer.getPMC_ALTERNATE_CONTACT();
					 pmcEmail = customer.getPMC_EMAIL();
		
					if (pmc.equalsIgnoreCase("") && pmcPerson.equalsIgnoreCase("")
							&& pmcContact.equalsIgnoreCase("")) {
						pmcType = "None";
					} else {
						pmcType = "Existing";
					}
			}

			request.setAttribute("leadID", leadID);
			request.setAttribute("doAction", action);
			request.setAttribute("LeadExeDate", leadExeDate);
			request.setAttribute("DGDeliveryDate", dgDeliveryDate);
			request.setAttribute("ProjectClosureDate", projectClosureDate);

			request.setAttribute("selectedprojectType", leadprojectType);
			request.setAttribute("sel_state", sel_state);
			request.setAttribute("sel_city", sel_city);
			request.setAttribute("sel_dgType", sel_dgType);
			request.setAttribute("projectReference", sel_proj_ref);
			request.setAttribute("sel_segment", sel_segment);
			request.setAttribute("sel_transaction_type", sel_transaction_type);
			request.setAttribute("sel_enq_type", sel_enq_type);
			request.setAttribute("sel_lead_status", sel_lead_status);
			request.setAttribute("sel_lead_Chance", sel_lead_Chance);
			request.setAttribute("competitions", sel_comp);
			request.setAttribute("lead_owner_id", lead_owner_id);
			request.setAttribute("lead_owner_name", lead_owner_name);

			request.setAttribute("DGValue", DGValue);
			request.setAttribute("projecttotal", projecttotal);
			request.setAttribute("lowsidesupbasic", lowsidesupbasic);
			request.setAttribute("lowsidelabbasic", lowsidelabbasic);
			request.setAttribute("lowsidesmargin", lowsidesmargin);
			request.setAttribute("projectmargin", projectmargin);

			request.setAttribute("userID", createdBy);

			request.setAttribute("sel_customerType", "Existing");
			request.setAttribute("sel_consultantType", consultantType);
			request.setAttribute("sel_pmcType", pmcType);

			request.setAttribute("sel_customerName", customerName);

			request.setAttribute("customerContactNumber", customerContactNumber);
			request.setAttribute("customerAlternateNumber",
					customerAlternateNumber);
			request.setAttribute("customerTempAddress", customerTempAddress);

			request.setAttribute("customerEmail", customerEmail);

			request.setAttribute("customerConsultant", customerConsultant);

			request.setAttribute("customerConsultantPersonName",
					customerConsultantPersonName);
			request.setAttribute("customerConsultantNumber",
					customerConsultantNumber);
			request.setAttribute("customerBillingAddress1",
					customerBillingAddress1);
			request.setAttribute("sel_customerBillingAddress2",
					customerBillingAddress2);
			request.setAttribute("sel_customerBillingAddress3",
					customerBillingAddress3);
			request.setAttribute("customerBillingAddress4",
					customerBillingAddress4);
			request.setAttribute("customerDeliveryAddress1",
					customerDeliveryAddress1);
			request.setAttribute("sel_customerDeliveryAddress2",
					customerDeliveryAddress2);
			request.setAttribute("sel_customerDeliveryAddress3",
					customerDeliveryAddress3);
			request.setAttribute("customerDeliveryAddress4",
					customerDeliveryAddress4);
			request.setAttribute("customergroup", customergroup);
			request.setAttribute("customergroup_person", customergroup_person);
			request.setAttribute("customergroup_person_contact",
					customergroup_person_contact);
			request.setAttribute("customerECCNO", customerECCNO);
			request.setAttribute("customerCSTNO", customerCSTNO);
			request.setAttribute("customerTINNO", customerTINNO);
			request.setAttribute("customerIECNO", customerIECNO);
			request.setAttribute("pmc", pmc);
			request.setAttribute("pmcPerson", pmcPerson);
			request.setAttribute("pmcContact", pmcContact);
			request.setAttribute("pmcAlternateContact", pmcAlternateContact);
			request.setAttribute("pmcEmail", pmcEmail);
			
			
			
			if (action.equalsIgnoreCase("createIndent")) {

				// Get ALL data need to create indent creation
				List<ConfigMaster> factorySupplyScope = new ArrayList<ConfigMaster>();
				List<ConfigMaster> branchSupplyScope = new ArrayList<ConfigMaster>();

				factorySupplyScope = configdao
						.getValueByName("SCOPE_SUPPLY_FACTORY");
				branchSupplyScope = configdao
						.getValueByName("SCOPE_SUPPLY_BRANCH");
				request.setAttribute("factorySupplyScope", factorySupplyScope);
				request.setAttribute("branchSupplyScope", branchSupplyScope);

				forward = CREATE_INDENT;
				
				
			}
			else if (action.equalsIgnoreCase("updateIndent")) {
				
				// Get ALL data need to create indent creation
				List<ConfigMaster> factorySupplyScope = new ArrayList<ConfigMaster>();
				List<ConfigMaster> branchSupplyScope = new ArrayList<ConfigMaster>();
				String indentNo=lHeader.getINDENT_NO();
				request.setAttribute("indentNo", indentNo);

				factorySupplyScope = configdao
						.getValueByName("SCOPE_SUPPLY_FACTORY");
				branchSupplyScope = configdao
						.getValueByName("SCOPE_SUPPLY_BRANCH");
				request.setAttribute("factorySupplyScope", factorySupplyScope);
				request.setAttribute("branchSupplyScope", branchSupplyScope);

				forward = UPDATE_INDENT;
				
			}
			
			else if (action.equalsIgnoreCase("Draft")) {
				
				String str = new String(leadID);
				businessFile = str.substring(2, 4);
				
				request.setAttribute("create", "create");
				request.setAttribute("sel_customerType", "New");
				request.setAttribute("sel_consultantType", "New");
				request.setAttribute("sel_pmcType", "New");
				
				
				
				request.setAttribute("selbusinessFile", leadprojectType);
				request.setAttribute("sel_state", sel_state);
				request.setAttribute("sel_city", sel_city);
				request.setAttribute("sel_dgType", sel_dgType);
				request.setAttribute("projectReference", sel_proj_ref);
				request.setAttribute("sel_segment", sel_segment);
				request.setAttribute("sel_transaction_type", sel_transaction_type);
	
				forward = CREATE_LEAD;
				if (businessFile.equalsIgnoreCase("PN")) {
					request.setAttribute("selbusinessFile", "Project");
					forward = CREATE_PN_LEAD;
				} 
			}
			
			else {
				forward = UPDATE_LEAD;
			}

			

		} else if (action.equalsIgnoreCase("editIndent")
				|| action.equalsIgnoreCase("viewIndent")) {

			leadid = request.getParameter("leadid");
			request.setAttribute("leadID", leadid);
			if (Utility.getBusinessType(leadid).equalsIgnoreCase("PN")) {
				forward = UPDATE_PN_LEAD;
			} else {
				forward = UPDATE_LEAD;
			}

			List<LeadHeader> leadHeaderList = new ArrayList<LeadHeader>();
			List<Customer> customerList = new ArrayList<Customer>();
			List<LeadItemDetails> leadItemsList = new ArrayList<LeadItemDetails>();
			List<Comments> commentsList = new ArrayList<Comments>();
			List<Attachment> attachmentsList = new ArrayList<Attachment>();
			List<Attachment> costSheetList = new ArrayList<Attachment>();

			leadHeaderList = leadheaderdao.getLeadHeadData(leadid);
			customerList = customerDao.getCustomerDetailsByLeadID(leadid);
			leadItemsList = leadItemDetailDao.getLeadItemsByLeadID(leadid);
			commentsList = commenstDao.getCommentsByLeadID(leadid);
			attachmentsList = attachementDao.getOtherAttachement(leadid);
			costSheetList = attachementDao.getCostSheet(leadid);

			Iterator itList;
			LeadHeader lHeader = new LeadHeader();
			Comments comments = new Comments();
			Attachment attachment = new Attachment();

			LeadItemDetails leadItems = new LeadItemDetails();
			Customer customer = new Customer();

			itList = leadHeaderList.iterator();
			lHeader = (LeadHeader) itList.next();

			itList = customerList.iterator();
			customer = (Customer) itList.next();

			request.setAttribute("attachmentsList", attachmentsList);
			request.setAttribute("costSheetList", costSheetList);
			request.setAttribute("leadItemsList", leadItemsList);
			request.setAttribute("commentsList", commentsList);

			// itList=commentsList.iterator();
			// comments=(Comments) itList.next();

			// itList=attachmentsList.iterator();
			// attachment=(Attachment) itList.next();

			// itList=leadItemsList.iterator();
			// leadItems=(LeadItemDetails) itList.next();

			System.out.println(lHeader);
			// System.out.println(comments);
			System.out.println(attachment);
			// System.out.println(leadItems);
			System.out.println(customer);

			String leadID = lHeader.getLEAD_ID();
			String leadprojectType = lHeader.getPROJECT_TYPE();

			String sel_state = lHeader.getPROJECT_EXE_STATE();
			String sel_city = lHeader.getPROJECT_EXE_PLACE();
			String sel_dgType = lHeader.getDG_TYPE();
			String sel_proj_ref = lHeader.getPROJECT_REFERENCE();
			String sel_segment = lHeader.getSEGMENT();
			String sel_transaction_type = lHeader.getTRANSACTION_TYPE();
			String sel_lead_status = lHeader.getLEAD_STATUS();
			String sel_lead_Chance = lHeader.getLEAD_CHANCES();
			String sel_comp = lHeader.getCOMPETITIONS();
			String lead_owner_id = lHeader.getOWNER_ID();
			String lead_owner_name = lHeader.getOWNER_NAME();
			String sel_enq_type = lHeader.getENQUIRY_TYPE();
			String createdBy = lHeader.getCREATED_BY();

			// Get Amount Details
			String DGValue = lHeader.getDG_ED_VALUE();
			long projecttotal = (long) lHeader.getPROJECT_TOTAL();
			long lowsidesupbasic = (long) lHeader.getLOW_SIDE_SUPPLY_BASIC();

			long lowsidelabbasic = (long) lHeader.getLOW_SIDE_LABOUR_BASIC();

			long lowsidesmargin = (long) lHeader.getLOW_SIDE_MARGIN();

			long projectmargin = (long) lHeader.getPROJECT_MARGIN();

			// Get Customer Details from DB
			String customerName = customer.getCUSTOMER_NAME();
			String customerContactNumber = customer.getCONTACT_NUMBER();
			String customerAlternateNumber = customer.getALTERNATE_NO();
			String customerEmail = customer.getEMAIL_ADDRESS();
			String customerConsultant = customer.getCONSULTANT();
			String customerConsultantPersonName = customer
					.getCONSULTANT_PERSON_NAME();
			String customerConsultantNumber = customer.getCONSULTANT_CONTACT();
			String customerBillingAddress1 = customer.getBILLING_ADDRESS1();
			String customerBillingAddress2 = customer.getBILLING_ADDRESS2();
			String customerBillingAddress3 = customer.getBILLING_ADDRESS3();
			String customerBillingAddress4 = customer.getBILLING_ADDRESS4();
			String customerDeliveryAddress1 = customer.getDELIVERY_ADDRESS1();
			String customerDeliveryAddress2 = customer.getDELIVERY_ADDRESS2();
			String customerDeliveryAddress3 = customer.getDELIVERY_ADDRESS3();
			String customerDeliveryAddress4 = customer.getDELIVERY_ADDRESS4();
			String customergroup = customer.getCUSTOMER_GROUP();
			String customergroup_person = customer
					.getCUSTOMER_GROUP_CONTACT_PERSON();
			String customergroup_person_contact = customer
					.getCUSTOMER_GROUP_CONTACT_NUMBER();
			String customerECCNO = customer.getECCNO();
			String customerCSTNO = customer.getCSTNO();
			String customerTINNO = customer.getTINNO();
			String customerIECNO = customer.getIECNO();
			String pmc = customer.getPMC();
			String pmcPerson = customer.getPMC_PERSON_NAME();
			String pmcContact = customer.getPMC_CONTACT();
			String pmcAlternateContact = customer.getPMC_ALTERNATE_CONTACT();
			String pmcEmail = customer.getPMC_EMAIL();

			request.setAttribute("leadID", leadID);
			request.setAttribute("doAction", action);
			request.setAttribute("selectedprojectType", leadprojectType);
			request.setAttribute("sel_state", sel_state);
			request.setAttribute("sel_city", sel_city);
			request.setAttribute("sel_dgType", sel_dgType);
			request.setAttribute("projectReference", sel_proj_ref);
			request.setAttribute("sel_segment", sel_segment);
			request.setAttribute("sel_transaction_type", sel_transaction_type);
			request.setAttribute("sel_enq_type", sel_enq_type);
			request.setAttribute("sel_lead_status", sel_lead_status);
			request.setAttribute("sel_lead_Chance", sel_lead_Chance);
			request.setAttribute("competitions", sel_comp);
			request.setAttribute("lead_owner_id", lead_owner_id);
			request.setAttribute("lead_owner_name", lead_owner_name);

			request.setAttribute("DGValue", DGValue);
			request.setAttribute("projecttotal", projecttotal);
			request.setAttribute("lowsidesupbasic", lowsidesupbasic);
			request.setAttribute("lowsidelabbasic", lowsidelabbasic);
			request.setAttribute("lowsidesmargin", lowsidesmargin);
			request.setAttribute("projectmargin", projectmargin);

			request.setAttribute("userID", createdBy);

			request.setAttribute("sel_customerType", "Existing");
			request.setAttribute("sel_customerName", customerName);

			request.setAttribute("customerContactNumber", customerContactNumber);
			request.setAttribute("customerAlternateNumber",
					customerAlternateNumber);
			request.setAttribute("customerEmail", customerEmail);
			request.setAttribute("customerConsultant", customerConsultant);
			request.setAttribute("customerConsultantPersonName",
					customerConsultantPersonName);
			request.setAttribute("customerConsultantNumber",
					customerConsultantNumber);
			request.setAttribute("customerBillingAddress1",
					customerBillingAddress1);
			request.setAttribute("sel_customerBillingAddress2",
					customerBillingAddress2);
			request.setAttribute("sel_customerBillingAddress3",
					customerBillingAddress3);
			request.setAttribute("customerBillingAddress4",
					customerBillingAddress4);
			request.setAttribute("customerDeliveryAddress1",
					customerDeliveryAddress1);
			request.setAttribute("sel_customerDeliveryAddress2",
					customerDeliveryAddress2);
			request.setAttribute("sel_customerDeliveryAddress3",
					customerDeliveryAddress3);
			request.setAttribute("customerDeliveryAddress4",
					customerDeliveryAddress4);
			request.setAttribute("customergroup", customergroup);
			request.setAttribute("customergroup_person", customergroup_person);
			request.setAttribute("customergroup_person_contact",
					customergroup_person_contact);
			request.setAttribute("customerECCNO", customerECCNO);
			request.setAttribute("customerCSTNO", customerCSTNO);
			request.setAttribute("customerTINNO", customerTINNO);
			request.setAttribute("customerIECNO", customerIECNO);
			request.setAttribute("pmc", pmc);
			request.setAttribute("pmcPerson", pmcPerson);
			request.setAttribute("pmcContact", pmcContact);
			request.setAttribute("pmcAlternateContact", pmcAlternateContact);
			request.setAttribute("pmcEmail", pmcEmail);

		} else if (action.equalsIgnoreCase("listLead")
				|| action.equalsIgnoreCase("listIndent")
				|| action.equalsIgnoreCase("listBudgetLead")) {
			forward = LIST_LEAD;

			List<UserRole> seList = userRoleDao.getUsersByRole("SE");

			String searchTerm = request.getParameter("search");

			request.setAttribute("exe_city", exe_city);
			request.setAttribute("exe_state", exe_state);
			request.setAttribute("regionType", regionType);

			request.setAttribute("seList", seList);

			request.setAttribute("userID", userID);
			request.setAttribute("userName", userName);
			request.setAttribute("userRole", userRole);

			request.setAttribute("userRegion", userRegion);
			request.setAttribute("userState", userState);
			request.setAttribute("userShortState", userShortState);
			request.setAttribute("userDCity", userDCity);

			String fromDate = "";
			String toDate = "";

			if (searchTerm.equalsIgnoreCase("All")) {

				fromDate = Utility.getFirstDayofCurrentMonth();
				toDate = Utility.getLastDayofCurrentMonth();

				request.setAttribute("from_date", fromDate);
				request.setAttribute("to_date", toDate);
				if (action.equalsIgnoreCase("listIndent")) {
					request.setAttribute("leaddetails1", leadheaderdao
							.getIndentDataDetails(fromDate, toDate,
									userShortState));
					forward = LIST_INDENT;
				} else if (action.equalsIgnoreCase("listBudgetLead")) {
					forward = LIST_BUD_LEAD;
					request.setAttribute("leaddetails1", leadheaderdao
							.getBudgetLeadHeadDataDetails(fromDate, toDate,
									userShortState));
				} else

				{
					request.setAttribute("leaddetails1", leadheaderdao
							.getLeadHeadDataDetails("PendingDRAFT", fromDate,
									toDate, userShortState));
				}
			} else {
				fromDate = request.getParameter("from_date");
				toDate = request.getParameter("to_date");

				String region = request.getParameter("region");
				String state = request.getParameter("state");
				String city = request.getParameter("city");
				String user = request.getParameter("user");
				String role = request.getParameter("roleName");

				// Set All Drop Down
				request.setAttribute("exe_city", exe_city);
				request.setAttribute("exe_state", exe_state);
				request.setAttribute("regionType", regionType);
				request.setAttribute("seList", seList);

				request.setAttribute("userRegion", userRegion);
				request.setAttribute("userState", userState);
				request.setAttribute("userShortState", userShortState);
				request.setAttribute("userDCity", userDCity);

				// Set Value on User Selection
				if (!region.equalsIgnoreCase("0")) {
					request.setAttribute("userRegion", region);
				}
				if (!state.equalsIgnoreCase("0")) {
					request.setAttribute("userState", state);
				}
				if (!city.equalsIgnoreCase("0")) {
					request.setAttribute("userDCity", city);
				}

				if (role.equalsIgnoreCase("SE")) {
					request.setAttribute("userID", userID);
					request.setAttribute("userName", userName);
					request.setAttribute("userRole", userRole);

				} else {
					if (!user.equalsIgnoreCase("0")) {
						request.setAttribute("userID", user);
						request.setAttribute("userName", userName);
						request.setAttribute("userRole", role);
					} else {
						request.setAttribute("userID", userID);
						request.setAttribute("userName", userName);
						request.setAttribute("userRole", userRole);
					}
				}

				System.out.println("Hello India1");

				request.setAttribute("from_date", fromDate);
				request.setAttribute("to_date", toDate);

				if (action.equalsIgnoreCase("listIndent")) {

					forward = LIST_INDENT;
					request.setAttribute("leaddetails1", leadheaderdao
							.getIndentDetailsData(fromDate, toDate, region,
									state, city, role, user));
				} else if (action.equalsIgnoreCase("listBudgetLead")) {
					forward = LIST_BUD_LEAD;
					request.setAttribute("leaddetails1", leadheaderdao
							.getBudgetLeadHeadDetailsData(fromDate, toDate,
									region, state, city, role, user));
				}

				else {
					request.setAttribute("leaddetails1", leadheaderdao
							.getLeadHeadDetailsData(fromDate, toDate, region,
									state, city, role, user));
				}
				// System.out.println(leaddetails1);
				region = request.getParameter("region");
				state = request.getParameter("state");
				city = request.getParameter("city");
				user = request.getParameter("user");
				role = request.getParameter("roleName");

			}

		} else if (action.equalsIgnoreCase("listQualifiedLead")) {
			forward = LIST_QUALIFIED_LEAD;
			List<UserRole> seList = userRoleDao.getUsersByRole("SE");

			String searchTerm = request.getParameter("search");

			request.setAttribute("exe_city", exe_city);
			request.setAttribute("exe_state", exe_state);
			request.setAttribute("regionType", regionType);

			request.setAttribute("seList", seList);

			request.setAttribute("userID", userID);
			request.setAttribute("userName", userName);
			request.setAttribute("userRole", userRole);

			request.setAttribute("userRegion", userRegion);
			request.setAttribute("userState", userState);
			request.setAttribute("userShortState", userShortState);
			request.setAttribute("userDCity", userDCity);

			String fromDate = "";
			String toDate = "";

			if (searchTerm.equalsIgnoreCase("All")) {

				fromDate = Utility.getFirstDayofCurrentMonth();
				toDate = Utility.getLastDayofCurrentMonth();

				request.setAttribute("from_date", fromDate);
				request.setAttribute("to_date", toDate);
				request.setAttribute("leaddetails1", leadheaderdao
						.getLeadHeadDataDetails("Won", fromDate, toDate,
								userShortState));
			} else {
				fromDate = request.getParameter("from_date");
				toDate = request.getParameter("to_date");

				String region = request.getParameter("region");
				String state = request.getParameter("state");
				String city = request.getParameter("city");
				String user = request.getParameter("user");
				String role = request.getParameter("roleName");

				// Set All Drop Down
				request.setAttribute("exe_city", exe_city);
				request.setAttribute("exe_state", exe_state);
				request.setAttribute("regionType", regionType);
				request.setAttribute("seList", seList);

				request.setAttribute("userRegion", userRegion);
				request.setAttribute("userState", userState);
				request.setAttribute("userShortState", userShortState);
				request.setAttribute("userDCity", userDCity);

				// Set Value on User Selection
				if (!region.equalsIgnoreCase("0")) {
					request.setAttribute("userRegion", region);
				}
				if (!state.equalsIgnoreCase("0")) {
					request.setAttribute("userState", state);
				}
				if (!city.equalsIgnoreCase("0")) {
					request.setAttribute("userDCity", city);
				}

				if (role.equalsIgnoreCase("SE")) {
					request.setAttribute("userID", userID);
					request.setAttribute("userName", userName);
					request.setAttribute("userRole", userRole);

				} else {
					if (!user.equalsIgnoreCase("0")) {
						request.setAttribute("userID", user);
						request.setAttribute("userName", userName);
						request.setAttribute("userRole", role);
					} else {
						request.setAttribute("userID", userID);
						request.setAttribute("userName", userName);
						request.setAttribute("userRole", userRole);
					}
				}

				System.out.println("Hello India1");

				request.setAttribute("from_date", fromDate);
				request.setAttribute("to_date", toDate);

				request.setAttribute("leaddetails1", leadheaderdao
						.getLeadHeadDetailsData(fromDate, toDate, region,
								state, city, role, user));
				// System.out.println(leaddetails1);
				region = request.getParameter("region");
				state = request.getParameter("state");
				city = request.getParameter("city");
				user = request.getParameter("user");
				role = request.getParameter("roleName");

			}

		} else {
			forward = CREATE_LEAD;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}
}
