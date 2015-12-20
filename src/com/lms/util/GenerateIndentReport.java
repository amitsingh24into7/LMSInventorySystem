package com.lms.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lms.dao.ConfigMasterDao;
import com.lms.dao.CustomerDao;
import com.lms.dao.LeadHeaderDao;
import com.lms.dao.LeadItemDetailsDao;
import com.lms.dao.LeadSequenceDao;
import com.lms.daoImpl.ConfigMasterDaoImpl;
import com.lms.daoImpl.CustomerDaoImpl;
import com.lms.daoImpl.LeadHeaderDaoImpl;
import com.lms.daoImpl.LeadItemDetailsDaoImpl;
import com.lms.daoImpl.LeadSequenceDaoImpl;
import com.lms.model.Customer;
import com.lms.model.LeadHeader;
import com.lms.model.LeadItemDetails;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Servlet implementation class GenerateIndentReport
 */
public class GenerateIndentReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConfigMasterDao configMasterDao;
	private LeadSequenceDao leadsequenceDao;
	private LeadHeaderDao leadHeaderDao;
	private CustomerDao customerDao;
	private LeadItemDetailsDao leadItemDetailsDao;
	LeadHeader lHeader = new LeadHeader();
	Customer customer = new Customer();
	LeadItemDetails leadItems = new LeadItemDetails();

	List<LeadHeader> leadHeaderList = new ArrayList<LeadHeader>();
	List<Customer> customerList = new ArrayList<Customer>();
	List<LeadItemDetails> leadItemsList = new ArrayList<LeadItemDetails>();
	HttpSession session;
	private Connection connection;
	private Statement st = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateIndentReport() {
		super();
		// TODO Auto-generated constructor stub
		configMasterDao = new ConfigMasterDaoImpl();
		leadsequenceDao = new LeadSequenceDaoImpl();
		leadHeaderDao = new LeadHeaderDaoImpl();
		customerDao = new CustomerDaoImpl();
		leadItemDetailsDao = new LeadItemDetailsDaoImpl();

	}

	String writeToXLS(String fl, String leadID) {

		String out = "";
		try {
			FileInputStream file = new FileInputStream(new File(fl));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("Indent Format");
			Cell cell = null;

			leadHeaderList = leadHeaderDao.getLeadHeadData(leadID);
			customerList = customerDao.getCustomerDetailsByLeadID(leadID);
			leadItemsList = leadItemDetailsDao.getLeadItemsByLeadID(leadID);

			Iterator itList;

			itList = leadHeaderList.iterator();
			lHeader = (LeadHeader) itList.next();

			itList = customerList.iterator();
			customer = (Customer) itList.next();

			// Header Information
			leadHeaderDao.getLeadHeadData(leadID);

			// Branch
			cell = sheet.getRow(1).getCell(1);
			cell.setCellValue(lHeader.getPROJECT_EXE_PLACE());
			// Order Type
			cell = sheet.getRow(1).getCell(5);
			cell.setCellValue(lHeader.getENQUIRY_TYPE());

			// Indent
			cell = sheet.getRow(2).getCell(1);
			cell.setCellValue(lHeader.getINDENT_NO());
			// Creation Date
			cell = sheet.getRow(3).getCell(1);
			cell.setCellValue(lHeader.getCREATION_DATE());

			// DG Type
			cell = sheet.getRow(2).getCell(10);
			cell.setCellValue(configMasterDao.getDescription("TYPE_OF_DG",
					lHeader.getDG_TYPE()));

			// Creation Date
			cell = sheet.getRow(3).getCell(10);
			cell.setCellValue(lHeader.getTRANSACTION_TYPE());

			// Segment
			cell = sheet.getRow(4).getCell(10);
			cell.setCellValue(lHeader.getSEGMENT());

			// Customer Information

			customerDao.getCustomerDetailsByLeadID(leadID);
			// Customer Name
			cell = sheet.getRow(7).getCell(2);
			cell.setCellValue(customer.getCUSTOMER_NAME());

			// Customer Billing Address
			cell = sheet.getRow(8).getCell(2);
			cell.setCellValue(customer.getBILLING_ADDRESS1());

			cell = sheet.getRow(9).getCell(2);
			cell.setCellValue(customer.getBILLING_ADDRESS2());

			cell = sheet.getRow(10).getCell(2);
			cell.setCellValue(customer.getBILLING_ADDRESS3());

			cell = sheet.getRow(11).getCell(2);
			cell.setCellValue(customer.getBILLING_ADDRESS4());

			// Customer Delivery Address
			cell = sheet.getRow(7).getCell(7);
			cell.setCellValue(customer.getDELIVERY_ADDRESS1());

			cell = sheet.getRow(8).getCell(7);
			cell.setCellValue(customer.getDELIVERY_ADDRESS2());

			cell = sheet.getRow(9).getCell(7);
			cell.setCellValue(customer.getDELIVERY_ADDRESS3());

			cell = sheet.getRow(10).getCell(7);
			cell.setCellValue(customer.getDELIVERY_ADDRESS4());

			// Customer COntact

			cell = sheet.getRow(12).getCell(2);
			cell.setCellValue(customer.getCUSTOMER_GROUP_CONTACT_PERSON());

			cell = sheet.getRow(14).getCell(2);
			cell.setCellValue(customer.getCUSTOMER_GROUP_CONTACT_NUMBER());

			cell = sheet.getRow(15).getCell(2);
			cell.setCellValue(customer.getEMAIL_ADDRESS());

			// Consutant Details

			cell = sheet.getRow(16).getCell(2);
			cell.setCellValue(customer.getCONSULTANT());

			cell = sheet.getRow(17).getCell(2);
			cell.setCellValue(customer.getCONSULTANT_PERSON_NAME());

			cell = sheet.getRow(18).getCell(2);
			cell.setCellValue(customer.getCONSULTANT_CONTACT());

			// ECC and other details

			cell = sheet.getRow(12).getCell(7);
			cell.setCellValue(customer.getECCNO());

			cell = sheet.getRow(13).getCell(7);
			cell.setCellValue(customer.getCSTNO());

			cell = sheet.getRow(14).getCell(7);
			cell.setCellValue(customer.getTINNO());

			cell = sheet.getRow(15).getCell(7);
			cell.setCellValue(customer.getIECNO());

			// PMC details

			cell = sheet.getRow(16).getCell(7);
			cell.setCellValue(customer.getPMC());

			cell = sheet.getRow(17).getCell(7);
			cell.setCellValue(customer.getPMC_PERSON_NAME());

			cell = sheet.getRow(18).getCell(7);
			cell.setCellValue(customer.getPMC_CONTACT());

			cell = sheet.getRow(19).getCell(7);
			cell.setCellValue(customer.getPMC_EMAIL());

			file.close();

			FileOutputStream outFile = new FileOutputStream(new File(fl));
			workbook.write(outFile);
			outFile.close();
			System.out.println("Done");
			out = "Done";

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			out = e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			out = e.getMessage();
		}
		return out;
	}

	String writeToFile(String filePath, String FileName, String indentNo,
			String FileExt, String leadID) {
		InputStream inStream = null;
		OutputStream outStream = null;

		String outFile = "";

		try {

			File afile = new File(filePath + FileName + FileExt);
			outFile = filePath + FileName + "_" + indentNo + FileExt;
			File bfile = new File(outFile);

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();

			System.out.println("File is copied successful!");

		} catch (IOException e) {
			outFile = e.getMessage();
			e.printStackTrace();
		}
		return outFile;

	}

	public Connection openDBConnection() throws ClassNotFoundException,
			SQLException {
		connection = DbUtil.getDBConnection();
		return connection;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String val = request.getParameter("val");
		String leadID = request.getParameter("leadID");
		String projectType = request.getParameter("projectType");

		File uploadedFile;
		if (projectType.equalsIgnoreCase("Project")) {
			projectType = "P";
		}
		if (projectType.equalsIgnoreCase("Retail")) {
			projectType = "R";
		}
		String state = request.getParameter("state");
		String shortState = configMasterDao.getShortState(state);
		String str = new String(leadID);
		String businessFile = str.substring(2, 4);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String indentNo = "";
		String root = getServletContext().getRealPath("/");
		String fileName = root + "/Parameter File.xlsx";
		System.out.println(fileName);
		String finalUrl = "";
		session = request.getSession();

		if (val.equalsIgnoreCase("new")) {
			// Create Indet

			// getIndentNo(int year, String businessFile, String leadType,
			// String shortState)

			indentNo = leadsequenceDao.getIndentNo(year, businessFile,
					projectType, shortState);

			lHeader.setINDENT_NO(indentNo);
		}
		else
		{
			
				lHeader.setINDENT_NO(val);
			
			
		}
			lHeader.setLEAD_ID(leadID);

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String createdDate = dateFormat.format(cal.getTime());

			lHeader.setMODIFIED_BY(session.getAttribute("luserId").toString());
			lHeader.setMODIFIED_DATE(createdDate);

			leadHeaderDao.updateIndent(lHeader);

			/*
			 * File path = new File(root + "/uploads"); if (!path.exists()) {
			 * boolean status = path.mkdirs(); }
			 */

			// uploadedFile = new File(root + "/" + fileName);
			String oFile = root + "/" + "IndentReport/Indent_report_" + indentNo + ".pdf";
			String path = oFile;

			System.out.println(oFile);

			// Getting Static Value
			Vector main = mainVector(leadID);

			// Getting Scope
			int temp = 0;

			LinkedHashMap<String, String> scope_of_supply_sgpl = new LinkedHashMap<String, String>();
			LinkedHashMap<String, String> scope_of_supply_exeu_branch = new LinkedHashMap<String, String>();

			scope_of_supply_sgpl.putAll(scope_of_supply_sgpl(leadID));
			scope_of_supply_exeu_branch
					.putAll(scope_of_supply_exeu_branch(leadID));

			if (scope_of_supply_sgpl.size() == scope_of_supply_exeu_branch
					.size()) {

			} else if (scope_of_supply_sgpl.size() > scope_of_supply_exeu_branch
					.size()) {
				temp = scope_of_supply_sgpl.size()
						- scope_of_supply_exeu_branch.size();

				for (int i = 0; i < temp; i++) {
					scope_of_supply_exeu_branch.put("", "");
				}
			} else if (scope_of_supply_exeu_branch.size() > scope_of_supply_sgpl
					.size()) {
				temp = scope_of_supply_exeu_branch.size()
						- scope_of_supply_sgpl.size();

				for (int i = 0; i < temp; i++) {
					scope_of_supply_sgpl.put("", "");
				}
			}

			try {
				int i = Indent_Report(main, scope_of_supply_sgpl,
						scope_of_supply_exeu_branch, path);

				// finalUrl=writeToXLS(oFile,leadID);

				if (i == 1) {
					finalUrl = "IndentReport/Indent_report_" + indentNo + ".pdf";
				} else {
					finalUrl = "Error in Generating Report";
				}
			} catch (Exception e) {
				finalUrl = "Error in Generating Report";
				e.printStackTrace();
			}
			// System.out.println(uploadedFile);
		
		response.setContentType("text/html");
		response.getWriter().print(finalUrl);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public Vector indent_details(String leadid) {
		String query = "";

		Vector indent_details = new Vector();

		try {
			query = "select LEAD_CREATION_PLACE,INDENT_NO,DATE_FORMAT(CREATION_DATE,'%D %b %Y') as INDENTDATE,ENQUIRY_TYPE,DG_TYPE,TRANSACTION_TYPE,SEGMENT from lead_header where LEAD_ID='"
					+ leadid + "';";
			st = openDBConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				indent_details.add(removeNull(rs
						.getString("LEAD_CREATION_PLACE")));
				indent_details.add(removeNull(rs.getString("INDENT_NO")));
				indent_details.add(removeNull(rs.getString("INDENTDATE")));
				indent_details.add("Existing");
				indent_details.add(removeNull(rs.getString("ENQUIRY_TYPE")));
				indent_details.add(removeNull(rs.getString("DG_TYPE")));
				indent_details
						.add(removeNull(rs.getString("TRANSACTION_TYPE")));
				indent_details.add(removeNull(rs.getString("SEGMENT")));
			} else {
				indent_details.add("");
				indent_details.add("");
				indent_details.add("");
				indent_details.add("");
				indent_details.add("");
				indent_details.add("");
				indent_details.add("");
				indent_details.add("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return indent_details;
	}

	public Vector commercials_info_dg(String leadid) {
		Vector customer_info = new Vector();

		String query = "";

		try {
			Vector billing_details = new Vector();
			Vector delivery_details = new Vector();

			query = "select CUSTOMER_NAME,BILLING_ADDRESS1,BILLING_ADDRESS2,BILLING_ADDRESS3,BILLING_ADDRESS4,CONTACT_NUMBER,ALTERNATE_NO,EMAIL_ADDRESS,CONSULTANT,CONSULTANT_PERSON_NAME,CONSULTANT_CONTACT,DELIVERY_ADDRESS1,DELIVERY_ADDRESS2,DELIVERY_ADDRESS3,DELIVERY_ADDRESS4,ECCNO,CSTNO,TINNO,IECNO,PMC,PMC_CONTACT,PMC_ALTERNATE_CONTACT,PMC_EMAIL from stg_mtr_customer where LEAD_ID='"
					+ leadid + "'; ";
			st = openDBConnection().createStatement();
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				billing_details.add(removeNull(rs.getString("CUSTOMER_NAME")));
				billing_details
						.add(removeNull(rs.getString("BILLING_ADDRESS1")));
				billing_details
						.add(removeNull(rs.getString("BILLING_ADDRESS2")));
				billing_details
						.add(removeNull(rs.getString("BILLING_ADDRESS3")));
				billing_details
						.add(removeNull(rs.getString("BILLING_ADDRESS4")));
				billing_details.add(removeNull(rs.getString("CONTACT_NUMBER")));
				billing_details.add("");
				billing_details.add(removeNull(rs.getString("ALTERNATE_NO")));
				billing_details.add(removeNull(rs.getString("EMAIL_ADDRESS")));
				billing_details.add(removeNull(rs.getString("CONSULTANT")));
				billing_details.add(removeNull(rs
						.getString("CONSULTANT_PERSON_NAME")));
				billing_details.add(removeNull(rs
						.getString("CONSULTANT_CONTACT")));
				billing_details.add("");

				delivery_details.add(removeNull(rs
						.getString("DELIVERY_ADDRESS1")));
				delivery_details.add(removeNull(rs
						.getString("DELIVERY_ADDRESS2")));
				delivery_details.add(removeNull(rs
						.getString("DELIVERY_ADDRESS3")));
				delivery_details.add(removeNull(rs
						.getString("DELIVERY_ADDRESS4")));
				delivery_details.add("");
				delivery_details.add(removeNull(rs.getString("ECCNO")));
				delivery_details.add(removeNull(rs.getString("CSTNO")));
				delivery_details.add(removeNull(rs.getString("TINNO")));
				delivery_details.add(removeNull(rs.getString("IECNO")));
				delivery_details.add(removeNull(rs.getString("PMC")));
				delivery_details.add(removeNull(rs.getString("PMC_CONTACT")));
				delivery_details.add(removeNull(rs
						.getString("PMC_ALTERNATE_CONTACT")));
				delivery_details.add(removeNull(rs.getString("PMC_EMAIL")));
			} else {
				for (int i = 0; i < 13; i++) {
					billing_details.add("");
					delivery_details.add("");
				}
			}

			customer_info.add(billing_details);
			customer_info.add(delivery_details);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer_info;
	}

	public Vector technical_info(String leadid) {
		Vector technical_info = new Vector();
		Vector technical_infoline = new Vector();
		String query_lead_details = "", query_lead_item_technical_details = "";

		try {
			query_lead_details = "select DTL_ID,DTL_RATING,DTL_ENGINE_MAKE,DTL_ENGINE_MODEL,DTL_COOLING_SYSTEM,DTL_ALTERNATOR_MAKE,DTL_VOLTAGE from lead_details where LEAD_ID='"
					+ leadid + "';";
			st = openDBConnection().createStatement();
			ResultSet rs = st.executeQuery(query_lead_details);
			while (rs.next()) {
				technical_infoline = new Vector();

				query_lead_item_technical_details = "";
				query_lead_item_technical_details = "select FUEL_TANK,ENGINE_OPTIONAL,DG_TESTING,TESTING_PROCEDURE,SOLOPARALLEL,TYPE_OF_PANEL,PANEL_OPTIONAL,ALTERNATOR_OPTIONAL,TESTING_CHARGES_INCLUDED from lead_item_technical_details where DTL_ID='"
						+ rs.getString("DTL_ID")
						+ "' and LEAD_ID='"
						+ leadid
						+ "';";
				st = openDBConnection().createStatement();
				ResultSet rs1 = st
						.executeQuery(query_lead_item_technical_details);
				if (rs1.next()) {
					technical_infoline.add(removeNull(rs
							.getString("DTL_RATING")));
					technical_infoline.add(removeNull(rs
							.getString("DTL_ENGINE_MAKE")));
					technical_infoline.add(removeNull(rs
							.getString("DTL_ENGINE_MODEL")));
					technical_infoline.add(removeNull(rs
							.getString("DTL_COOLING_SYSTEM")));
					technical_infoline.add(removeNull(rs1
							.getString("FUEL_TANK")));
					technical_infoline.add(removeNull(rs1
							.getString("ENGINE_OPTIONAL")));
					technical_infoline.add(removeNull(rs1
							.getString("DG_TESTING")));
					technical_infoline.add(removeNull(rs1
							.getString("TESTING_PROCEDURE")));
					technical_infoline.add(removeNull(rs
							.getString("DTL_RATING")));
					technical_infoline.add(removeNull(rs
							.getString("DTL_ALTERNATOR_MAKE")));
					technical_infoline.add(removeNull(rs
							.getString("DTL_VOLTAGE")));
					technical_infoline.add(removeNull(rs1
							.getString("SOLOPARALLEL")));
					technical_infoline.add(removeNull(rs1
							.getString("TYPE_OF_PANEL")));
					technical_infoline.add(removeNull(rs1
							.getString("PANEL_OPTIONAL")));
					technical_infoline.add(removeNull(rs1
							.getString("ALTERNATOR_OPTIONAL")));

					if (rs1.getString("TESTING_CHARGES_INCLUDED")
							.equalsIgnoreCase("1")) {
						technical_infoline.add("Included in DG price");
						technical_infoline.add("INCLUDED IN DG PRICE");
					} else {
						technical_infoline.add("");
						technical_infoline.add("");
					}
				}

				technical_info.add(technical_infoline);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return technical_info;
	}

	public Vector commercials_info_DG_SET(String leadid) {
		Vector commercials_info_DG_SET = new Vector();

		String query = "";

		List keylist_commercials_info_DG_SET = new ArrayList();
		keylist_commercials_info_DG_SET.add("commercialDGPrice");
		keylist_commercials_info_DG_SET.add("commercialexcise");
		keylist_commercials_info_DG_SET.add("commercialCESS");
		keylist_commercials_info_DG_SET.add("commercialSHEC");
		keylist_commercials_info_DG_SET.add("commercialsubTOTAL");
		keylist_commercials_info_DG_SET.add("commercialVAT");
		keylist_commercials_info_DG_SET.add("commercialEntrytax");
		keylist_commercials_info_DG_SET.add("commercialOCTROI");
		keylist_commercials_info_DG_SET.add("commercialAnyOther");
		keylist_commercials_info_DG_SET.add("commercialFI");
		keylist_commercials_info_DG_SET.add("commercialTOTAL");
		keylist_commercials_info_DG_SET.add("commercialNoofDG");
		keylist_commercials_info_DG_SET.add("commercialFinalTOTAL");

		Vector lead_pricing_info_DG_SET = new Vector();
		Vector indent_payment_details_DG_SET = new Vector();
		try {
			Iterator itr = keylist_commercials_info_DG_SET.iterator();
			while (itr.hasNext()) {
				query = "select VALUE from lead_pricing_information where NAME='"
						+ itr.next()
						+ "' and ITEMTYPE='DG' and LEAD_ID='"
						+ leadid + "';";
				st = openDBConnection().createStatement();
				ResultSet rs = st.executeQuery(query);
				if (rs.next()) {
					lead_pricing_info_DG_SET.add(removeNull(rs
							.getString("VALUE")));
				} else {
					lead_pricing_info_DG_SET.add("");
				}
			}

			query = "select ADVANCE,CHEQUE_NO,DATE_FORMAT(CHEQUE_DATE,'%d/%m/%Y') as CHEQUEDATE,BANK_DETAILS,BALANCE,FORMS_TEXT,PAYMENT_TERMS1,PAYMENT_TERMS2,PAYMENT_TERMS3,PG_TEXT,LIQUIDATE_DAMAGE_TEXT from indent_payment_details where LEAD_ID='"
					+ leadid + "' and ITEMTYPE='DG';";
			st = openDBConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				indent_payment_details_DG_SET.add(removeNull(rs
						.getString("ADVANCE")));
				indent_payment_details_DG_SET.add(removeNull(rs
						.getString("CHEQUE_NO")));
				indent_payment_details_DG_SET.add(removeNull(rs
						.getString("CHEQUEDATE")));
				indent_payment_details_DG_SET.add(removeNull(rs
						.getString("BANK_DETAILS")));
				indent_payment_details_DG_SET.add(removeNull(rs
						.getString("BALANCE")));
				indent_payment_details_DG_SET.add(removeNull(rs
						.getString("FORMS_TEXT")));
				indent_payment_details_DG_SET.add(removeNull(rs
						.getString("PAYMENT_TERMS1")));
				indent_payment_details_DG_SET.add(removeNull(rs
						.getString("PAYMENT_TERMS2")));
				indent_payment_details_DG_SET.add(removeNull(rs
						.getString("PAYMENT_TERMS3")));
				indent_payment_details_DG_SET.add(removeNull(rs
						.getString("PG_TEXT")));
				indent_payment_details_DG_SET.add(removeNull(rs
						.getString("LIQUIDATE_DAMAGE_TEXT")));
			} else {
				indent_payment_details_DG_SET.add("");
				indent_payment_details_DG_SET.add("");
				indent_payment_details_DG_SET.add("");
				indent_payment_details_DG_SET.add("");
				indent_payment_details_DG_SET.add("");
				indent_payment_details_DG_SET.add("");
				indent_payment_details_DG_SET.add("");
				indent_payment_details_DG_SET.add("");
				indent_payment_details_DG_SET.add("");
				indent_payment_details_DG_SET.add("");
				indent_payment_details_DG_SET.add("");
			}

			commercials_info_DG_SET.add(lead_pricing_info_DG_SET);
			commercials_info_DG_SET.add(indent_payment_details_DG_SET);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return commercials_info_DG_SET;
	}

	public Vector commercials_info_installation(String leadid) {
		Vector commercials_info_installation = new Vector();
		String query = "";

		List keylist_commercials_info_installation = new ArrayList();
		keylist_commercials_info_installation.add("commercialpipes");
		keylist_commercials_info_installation.add("commercialpipesVAT");
		keylist_commercials_info_installation.add("commercialCables");
		keylist_commercials_info_installation.add("commercialCablesVAT");
		keylist_commercials_info_installation
				.add("commercialInstTotalDGAccess");
		keylist_commercials_info_installation.add("lowSidemateriaSupply");
		keylist_commercials_info_installation.add("lowSidemateriaSupplyVAT");
		keylist_commercials_info_installation.add("labourinstallation");
		keylist_commercials_info_installation.add("installationlabourvat");
		keylist_commercials_info_installation.add("sublowsideworks");
		keylist_commercials_info_installation.add("totalprojectvalue");

		Vector lead_pricing_info_INST = new Vector();
		Vector indent_payment_details_INST = new Vector();
		try {
			Iterator itr = keylist_commercials_info_installation.iterator();
			while (itr.hasNext()) {
				query = "select VALUE from lead_pricing_information where NAME='"
						+ itr.next()
						+ "' and ITEMTYPE='INST' and LEAD_ID='"
						+ leadid + "';";
				st = openDBConnection().createStatement();
				ResultSet rs = st.executeQuery(query);
				if (rs.next()) {
					lead_pricing_info_INST
							.add(removeNull(rs.getString("VALUE")));
				} else {
					lead_pricing_info_INST.add("");
				}
			}

			query = "select ADVANCE,CHEQUE_NO,DATE_FORMAT(CHEQUE_DATE,'%d/%m/%Y') as CHEQUEDATE,BANK_DETAILS,BALANCE,FORMS_TEXT,PAYMENT_TERMS1,PAYMENT_TERMS2,PAYMENT_TERMS3,PG_TEXT,LIQUIDATE_DAMAGE_TEXT from indent_payment_details where LEAD_ID='"
					+ leadid + "' and ITEMTYPE='INST';";
			st = openDBConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				indent_payment_details_INST.add(removeNull(rs
						.getString("ADVANCE")));
				indent_payment_details_INST.add(removeNull(rs
						.getString("CHEQUE_NO")));
				indent_payment_details_INST.add(removeNull(rs
						.getString("CHEQUEDATE")));
				indent_payment_details_INST.add(removeNull(rs
						.getString("BANK_DETAILS")));
				indent_payment_details_INST.add(removeNull(rs
						.getString("BALANCE")));
				indent_payment_details_INST.add(removeNull(rs
						.getString("FORMS_TEXT")));
				indent_payment_details_INST.add(removeNull(rs
						.getString("PAYMENT_TERMS1")));
				indent_payment_details_INST.add(removeNull(rs
						.getString("PAYMENT_TERMS2")));
				indent_payment_details_INST.add(removeNull(rs
						.getString("PAYMENT_TERMS3")));
				indent_payment_details_INST.add("");
				indent_payment_details_INST.add(removeNull(rs
						.getString("LIQUIDATE_DAMAGE_TEXT")));
				indent_payment_details_INST.add(removeNull(rs
						.getString("PG_TEXT")));

			} else {
				indent_payment_details_INST.add("");
				indent_payment_details_INST.add("");
				indent_payment_details_INST.add("");
				indent_payment_details_INST.add("");
				indent_payment_details_INST.add("");
				indent_payment_details_INST.add("");
				indent_payment_details_INST.add("");
				indent_payment_details_INST.add("");
				indent_payment_details_INST.add("");
				indent_payment_details_INST.add("");
				indent_payment_details_INST.add("");
				indent_payment_details_INST.add("");
			}

			commercials_info_installation.add(lead_pricing_info_INST);
			commercials_info_installation.add(indent_payment_details_INST);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return commercials_info_installation;
	}

	public Vector additional_info(String leadid) {
		Vector additional_info = new Vector();

		String query = "";

		try {
			query = "select DETAILS from comments where TYPES='INDENT_COMMENTS'"
					+" and LEAD_ID='"+ leadid + "';";
					
			st = openDBConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				additional_info.add(removeNull(rs.getString("DETAILS")));
			} else {
				additional_info.add("");
			}

			query = "select DATE_FORMAT(CREATION_DATE,'%d/%m/%Y') as createddate,DATE_FORMAT(MODIFIED_DATE,'%d/%m/%Y') as modifieddate,CREATED_BY,MODIFIED_BY from lead_header where LEAD_ID='"
					+ leadid + "';";
			st = openDBConnection().createStatement();
			ResultSet rs1 = st.executeQuery(query);
			if (rs1.next()) {
				additional_info.add(removeNull(rs1.getString("createddate")));
				additional_info.add(removeNull(rs1.getString("createddate")));
				additional_info.add(removeNull(rs1.getString("modifieddate")));
				additional_info.add(removeNull(rs1.getString("CREATED_BY")));
				additional_info.add(removeNull(rs1.getString("MODIFIED_BY")));
			} else {
				additional_info.add("");
				additional_info.add("");
				additional_info.add("");
				additional_info.add("");
				additional_info.add("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return additional_info;
	}

	public Vector warranty(String leadid) {
		Vector warranty = new Vector();

		String query = "";

		try {
			query = "select WARRANTY_DETAILS,OPERATOR_REQUIRED from lead_supply_scope where LEAD_ID='"
					+ leadid + "';";
			st = openDBConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				warranty.add(removeNull(rs.getString("WARRANTY_DETAILS")));
				warranty.add(removeNull(rs.getString("OPERATOR_REQUIRED")));
			} else {
				warranty.add("");
				warranty.add("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return warranty;
	}

	public LinkedHashMap<String, String> scope_of_supply_sgpl(String leadid) {
		LinkedHashMap<String, String> scope_of_supply_sgpl = new LinkedHashMap<String, String>();

		String query = "", FACTORY_SUPPLY_SCOPE = "", temp = "", part1 = "", part2 = "", yes_or_no = "";

		try {

			query = "select FACTORY_SUPPLY_SCOPE from lead_supply_scope where LEAD_ID='"
					+ leadid + "';";
			st = openDBConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				FACTORY_SUPPLY_SCOPE = removeNull(rs
						.getString("FACTORY_SUPPLY_SCOPE"));
			}

			String[] obj_sgpl = FACTORY_SUPPLY_SCOPE.split(",");
			for (int i = 0; i < obj_sgpl.length - 1; i++) {
				String[] t = obj_sgpl[i].split("=");

				System.out.println(t[0]);
				System.out.println(t[1]);
				part1 = t[0];
				part2 = t[1];

				if (part2.trim().equalsIgnoreCase("1")) {
					yes_or_no = "YES";
				} else {
					yes_or_no = "NO";
				}

				scope_of_supply_sgpl.put(part1, yes_or_no);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return scope_of_supply_sgpl;
	}

	public LinkedHashMap<String, String> scope_of_supply_exeu_branch(
			String leadid) {
		LinkedHashMap<String, String> scope_of_supply_exeu_branch = new LinkedHashMap<String, String>();

		String query = "", BRANCH_SUPPLY_SCOPE = "", temp = "", part1 = "", part2 = "", yes_or_no = "";

		try {

			query = "select BRANCH_SUPPLY_SCOPE from lead_supply_scope where LEAD_ID='"
					+ leadid + "';";
			st = openDBConnection().createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				BRANCH_SUPPLY_SCOPE = removeNull(rs
						.getString("BRANCH_SUPPLY_SCOPE"));
			}

			String[] obj_sgpl = BRANCH_SUPPLY_SCOPE.split(",");
			for (int i = 0; i < obj_sgpl.length - 1; i++) {
				String[] t = obj_sgpl[i].split("=");

				System.out.println(t[0]);
				System.out.println(t[1]);
				part1 = t[0];
				part2 = t[1];

				if (part2.trim().equalsIgnoreCase("1")) {
					yes_or_no = "YES";
				} else {
					yes_or_no = "NO";
				}

				scope_of_supply_exeu_branch.put(part1, yes_or_no);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return scope_of_supply_exeu_branch;
	}

	public Vector mainVector(String leadid) {
		Vector main = new Vector();
		main.add(indent_details(leadid));
		main.add(commercials_info_dg(leadid));
		main.add(technical_info(leadid));
		main.add(commercials_info_DG_SET(leadid));
		main.add(commercials_info_installation(leadid));
		main.add(additional_info(leadid));
		main.add(warranty(leadid));
		return main;
	}

	// Util
	public static String removeNull(String txt) {
		String nullString = "";
		if (txt != null) {
			return txt;
		} else {
			return nullString;
		}
	}

	public int Indent_Report(Vector main,
			LinkedHashMap<String, String> scope_of_supply_sgpl,
			LinkedHashMap<String, String> scope_of_supply_exeu_branch,
			String path) throws DocumentException, MalformedURLException,
			IOException {

		Vector indent_details = new Vector();
		Vector customer_info = new Vector();
		Vector technical_info = new Vector();
		Vector commercials_info_DG_SET = new Vector();
		Vector commercials_info_installation = new Vector();
		Vector additional_info = new Vector();
		Vector warranty = new Vector();

		indent_details = (Vector) main.get(0);
		customer_info = (Vector) main.get(1);
		technical_info = (Vector) main.get(2);
		commercials_info_DG_SET = (Vector) main.get(3);
		commercials_info_installation = (Vector) main.get(4);
		additional_info = (Vector) main.get(5);
		warranty = (Vector) main.get(6);

		Vector billing_details = new Vector();
		Vector delivery_details = new Vector();

		billing_details = (Vector) customer_info.get(0);
		delivery_details = (Vector) customer_info.get(1);

		Vector lead_pricing_info_DG_SET = new Vector();
		Vector indent_payment_details_DG_SET = new Vector();

		lead_pricing_info_DG_SET = (Vector) commercials_info_DG_SET.get(0);
		indent_payment_details_DG_SET = (Vector) commercials_info_DG_SET.get(1);

		Vector lead_pricing_info_INST = new Vector();
		Vector indent_payment_details_INST = new Vector();

		lead_pricing_info_INST = (Vector) commercials_info_installation.get(0);
		indent_payment_details_INST = (Vector) commercials_info_installation
				.get(1);

		Document document = new Document(PageSize.A4, 36, 36, 74, 36);

		@SuppressWarnings("unused")
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(path));

		TableHeader event = new TableHeader();
		System.out.println("event");
		Font font_small = FontFactory.getFont("ARIAL", 5, Font.NORMAL);
		Font font_normal = FontFactory.getFont("ARIAL", 8, Font.NORMAL);
		Font font_bold = FontFactory.getFont("ARIAL", 8, Font.BOLD);
		Font font_bold_bg = FontFactory.getFont("ARIAL", 8, Font.BOLD);

		
		Image logo = Image.getInstance(getServletContext().getRealPath("/")+"dist/images/sterling_logo.png");
		// logo.setAlignment(Image.MIDDLE);
		logo.scaleAbsoluteHeight(50);
		logo.scaleAbsoluteWidth(50);
		logo.setAbsolutePosition(60, 920);
		// logo.scalePercent(100);
		Chunk chunk = new Chunk(logo, 0, 20);
		HeaderFooter header = new HeaderFooter(new Phrase(chunk), false);
		header.setAlignment(Element.ALIGN_LEFT);
		header.setBorder(Rectangle.NO_BORDER);

		document.setHeader(header);
		writer.setPageEvent(event);
		System.out.println(writer);

		document.open();

		PdfPTable tbl_indent_headline = new PdfPTable(1);
		tbl_indent_headline.setTotalWidth(530);
		tbl_indent_headline.setLockedWidth(true);
		PdfPCell cell_indent_headline = new PdfPCell();

		cell_indent_headline.setPhrase(new Phrase("DG SET INDENT FORM SWPPL",
				font_bold));
		cell_indent_headline.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_indent_headline.setBorder(Rectangle.BOX);
		tbl_indent_headline.addCell(cell_indent_headline);

		/*-------------------------------Indent Header Information Table--------------------------------*/
		float w[] = { 20, 20, 20, 20, 20, 20 };
		PdfPTable tbl_Indent_InFo = new PdfPTable(w);
		tbl_Indent_InFo.setTotalWidth(530);
		tbl_Indent_InFo.setLockedWidth(true);
		PdfPCell cell_Indent_InFo = new PdfPCell();

		// Row 1
		cell_Indent_InFo.setPhrase(new Phrase("BRANCH", font_bold));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase(indent_details.get(0).toString(),
				font_normal));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase("Order Type", font_bold));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase(indent_details.get(4).toString(),
				font_bold));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase("STD/ NON.STD.", font_bold));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase(indent_details.get(5).toString(),
				font_normal));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		// Row 2
		cell_Indent_InFo.setPhrase(new Phrase("INDENT  No.", font_bold));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase(indent_details.get(1).toString(),
				font_normal));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase("", font_bold));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase("", font_bold));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase("EOU/EPCG/DTA/STPI", font_bold));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase(indent_details.get(6).toString(),
				font_normal));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		// Row 3
		cell_Indent_InFo.setPhrase(new Phrase("DATE", font_bold));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase(indent_details.get(2).toString(),
				font_normal));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase("", font_normal));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase("", font_normal));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase("SEGMENT", font_bold));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase(indent_details.get(7).toString(),
				font_normal));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		// Row 4
		cell_Indent_InFo.setPhrase(new Phrase("CUSTOMER TYPE", font_bold));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase(indent_details.get(3).toString(),
				font_normal));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase("", font_normal));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase("", font_normal));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase("", font_bold));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		cell_Indent_InFo.setPhrase(new Phrase("", font_normal));
		cell_Indent_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_Indent_InFo.setBorder(Rectangle.BOX);
		tbl_Indent_InFo.addCell(cell_Indent_InFo);

		PdfPTable tbl_customer_headline = new PdfPTable(1);
		tbl_customer_headline.setTotalWidth(530);
		tbl_customer_headline.setLockedWidth(true);
		PdfPCell cell_customer_headline = new PdfPCell();

		cell_customer_headline.setPhrase(new Phrase("CUSTOMER  INFORMATION",
				font_bold));
		cell_customer_headline.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_customer_headline.setBorder(Rectangle.BOX);
		tbl_customer_headline.addCell(cell_customer_headline);

		/*-------------------------------Customer Information Table--------------------------------*/

		float w1[] = { 20, 20, 20, 20 };
		PdfPTable tbl_customer_InFo = new PdfPTable(w1);
		tbl_customer_InFo.setTotalWidth(530);
		tbl_customer_InFo.setLockedWidth(true);
		PdfPCell cell_customer_InFo = new PdfPCell();

		// Row 1
		cell_customer_InFo.setPhrase(new Phrase("NAME", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(0)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("", font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.NO_BORDER);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(0)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		// Row 2
		cell_customer_InFo.setPhrase(new Phrase("BILLING ADDRESS", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(1)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("", font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.NO_BORDER);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(1)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		// Row 3
		cell_customer_InFo.setPhrase(new Phrase("", font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(2)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("DELIVERY     ADDRESS",
				font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_customer_InFo.setBorder(Rectangle.NO_BORDER);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(2)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		// Row 4
		cell_customer_InFo.setPhrase(new Phrase("", font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(3)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("", font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.NO_BORDER);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(3)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		// Row 5
		cell_customer_InFo.setPhrase(new Phrase("", font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(4)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("", font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.NO_BORDER);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(4)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		// Row 6
		cell_customer_InFo.setPhrase(new Phrase("CUSTOMER CONTACT", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(5)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("ECC NO", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(5)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		// Row 7
		cell_customer_InFo.setPhrase(new Phrase("DESIGNATION", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(6)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("CST NO.", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(6)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		// Row 8
		cell_customer_InFo.setPhrase(new Phrase("TELE NUMBER", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(7)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("TIN NO.", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(7)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		// Row 9
		cell_customer_InFo.setPhrase(new Phrase("E-MAIL ID", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(8)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("IEC NO.", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(8)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		// Row 10
		cell_customer_InFo.setPhrase(new Phrase("CONSULTANT", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(9)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("P M C ", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(9)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		// Row 11
		cell_customer_InFo
				.setPhrase(new Phrase("CONSULTANT CONTACT", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(10)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("PMC CONTACT", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(10)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		// Row 12
		cell_customer_InFo.setPhrase(new Phrase("TELE.  NUMBER", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(11)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("TELE.  NUMBER", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(11)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		// Row 13
		cell_customer_InFo.setPhrase(new Phrase("", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(billing_details.get(12)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase("E-MAIL  ID", font_bold));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		cell_customer_InFo.setPhrase(new Phrase(delivery_details.get(12)
				.toString(), font_normal));
		cell_customer_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_customer_InFo.setBorder(Rectangle.BOX);
		tbl_customer_InFo.addCell(cell_customer_InFo);

		PdfPTable tbl_technical_headline = new PdfPTable(1);
		tbl_technical_headline.setTotalWidth(530);
		tbl_technical_headline.setLockedWidth(true);
		PdfPCell cell_technical_headline = new PdfPCell();

		cell_technical_headline.setPhrase(new Phrase("TECHNICAL  INFORMATION",
				font_bold_bg));
		cell_technical_headline.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_technical_headline.setBorder(Rectangle.BOX);
		cell_technical_headline.setBackgroundColor(Color.gray);
		tbl_technical_headline.addCell(cell_technical_headline);

		/*-------------------------------Technical Information Table--------------------------------*/

		PdfPTable tbl_Empty = new PdfPTable(1);
		tbl_Empty.setTotalWidth(530);
		tbl_Empty.setLockedWidth(true);
		PdfPCell cell_Empty = new PdfPCell();

		cell_Empty.setPhrase(new Phrase(""));
		cell_Empty.setBorder(Rectangle.NO_BORDER);
		tbl_Empty.addCell(cell_Empty);

		// document.add(tbl_Empty);
		// document.add(tbl_Empty);
		// document.add(tbl_Empty);
		// document.add(tbl_Empty);

		document.add(tbl_indent_headline);
		document.add(tbl_Indent_InFo);

		document.add(tbl_Empty);
		document.add(tbl_Empty);

		document.add(tbl_customer_headline);
		document.add(tbl_customer_InFo);

		document.add(tbl_Empty);
		document.add(tbl_Empty);

		document.add(tbl_technical_headline);

		Vector technical_infoline = new Vector();

		for (int i = 0; i < technical_info.size(); i++) {

			technical_infoline = (Vector) technical_info.get(i);

			float w2[] = { 20, 20, 20, 20 };
			PdfPTable tbl_technical_InFo = new PdfPTable(w2);
			tbl_technical_InFo.setTotalWidth(530);
			tbl_technical_InFo.setLockedWidth(true);
			PdfPCell cell_technical_InFo = new PdfPCell();

			// Row 1
			cell_technical_InFo.setPhrase(new Phrase("ENGINE", font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(0)
					.toString()+" KVA", font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase("ALTERNATOR", font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(8)
					.toString()+" KVA", font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			// Row 2
			cell_technical_InFo.setPhrase(new Phrase("ENGINE MAKE", font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(1)
					.toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase("MAKE", font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(9)
					.toString()
					+ "     |     VOLT      | "
					+ technical_infoline.get(10).toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			// Row 3
			cell_technical_InFo
					.setPhrase(new Phrase("ENGINE MODEL", font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(2)
					.toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo
					.setPhrase(new Phrase("SOLO/PARALLEL", font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(11)
					.toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			// Row 4
			cell_technical_InFo.setPhrase(new Phrase("COOLING SYSTEM",
					font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(3)
					.toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase("TYPE  OF  PANEL",
					font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(12)
					.toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			// Row 5
			cell_technical_InFo.setPhrase(new Phrase("FUEL TANK", font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(4)
					.toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase("PANEL OPTIONALS",
					font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(13)
					.toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			// Row 6
			cell_technical_InFo.setPhrase(new Phrase("ENGINE OPTIONALS",
					font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(5)
					.toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase("ALTERNATOR OPTIONALS",
					font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(14)
					.toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			// Row 7
			cell_technical_InFo.setPhrase(new Phrase("DG TESTING", font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(6)
					.toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase("TESTING CHARGES",
					font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(16)
					.toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			// Row 8
			cell_technical_InFo.setPhrase(new Phrase(
					"TESTING PROCEDURE IF ANY", font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase(technical_infoline.get(7)
					.toString(), font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase("", font_bold));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			cell_technical_InFo.setPhrase(new Phrase("", font_normal));
			cell_technical_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_technical_InFo.setBorder(Rectangle.BOX);
			tbl_technical_InFo.addCell(cell_technical_InFo);

			document.add(tbl_technical_InFo);
			document.add(tbl_Empty);
			document.add(tbl_Empty);

		}

		PdfPTable tbl_commercials_info_supply_headline = new PdfPTable(1);
		tbl_commercials_info_supply_headline.setTotalWidth(530);
		tbl_commercials_info_supply_headline.setLockedWidth(true);
		PdfPCell cell_commercial_info_supply_headline = new PdfPCell();

		cell_commercial_info_supply_headline
				.setPhrase(new Phrase(
						"COMMERCIALS INFORMATION - FOR SUPPLY OF DG SET",
						font_bold_bg));
		cell_commercial_info_supply_headline
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_info_supply_headline.setBorder(Rectangle.BOX);
		cell_commercial_info_supply_headline.setBackgroundColor(Color.gray);
		tbl_commercials_info_supply_headline
				.addCell(cell_commercial_info_supply_headline);

		/*-------------------------------Commercials Information for Supply of dg set Table--------------------------------*/

		float w3[] = { 20, 10, 20, 20, 20 };
		PdfPTable tbl_commercial_InFo_supply_set = new PdfPTable(w3);
		tbl_commercial_InFo_supply_set.setTotalWidth(530);
		tbl_commercial_InFo_supply_set.setLockedWidth(true);
		PdfPCell cell_commercial_InFo_supply_set = new PdfPCell();

		// Row 1
		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				"DG PRICE (Basic)", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(0).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("ADVANCE",
				font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				indent_payment_details_DG_SET.get(0).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		// Row 2
		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				"EXCISE DUTY (DTA )- 12.5%", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("12.5%",
				font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(1).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("CHE/DD DET.",
				font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				indent_payment_details_DG_SET.get(1).toString() + "  |DATE|  "
						+ indent_payment_details_DG_SET.get(2).toString(),
				font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		// Row 3
		cell_commercial_InFo_supply_set
				.setPhrase(new Phrase("CESS", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set
				.setPhrase(new Phrase("0%", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(2).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("BANK, PLACE",
				font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				indent_payment_details_DG_SET.get(3).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		// Row 4
		cell_commercial_InFo_supply_set
				.setPhrase(new Phrase("SHEC", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set
				.setPhrase(new Phrase("0%", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(3).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("BALANCE",
				font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				indent_payment_details_DG_SET.get(4).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		// Row 5
		cell_commercial_InFo_supply_set.setPhrase(new Phrase("SUBTOTAL",
				font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(4).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set
				.setPhrase(new Phrase("FORMS", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				indent_payment_details_DG_SET.get(5).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		// Row 6
		cell_commercial_InFo_supply_set.setPhrase(new Phrase("VAT @ 5.5% ",
				font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("5.5%",
				font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(5).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.TOP);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				indent_payment_details_DG_SET.get(6).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		// Row 7
		cell_commercial_InFo_supply_set.setPhrase(new Phrase("ENTRY TAX",
				font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set
				.setPhrase(new Phrase("0%", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(6).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.NO_BORDER);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		// Row 8
		cell_commercial_InFo_supply_set.setPhrase(new Phrase("OCTROI",
				font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set
				.setPhrase(new Phrase("0%", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(7).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				"PAYMENT TERMS (DG set)", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.NO_BORDER);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				indent_payment_details_DG_SET.get(7).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		// Row 9
		cell_commercial_InFo_supply_set.setPhrase(new Phrase("ANY OTHER TAX",
				font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set
				.setPhrase(new Phrase("0%", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(8).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.NO_BORDER);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		// Row 10
		cell_commercial_InFo_supply_set
				.setPhrase(new Phrase("F & I", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(9).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOTTOM);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				indent_payment_details_DG_SET.get(8).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		// Row 11
		cell_commercial_InFo_supply_set.setPhrase(new Phrase("SUB TOTAL",
				font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(10).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		// Row 12
		cell_commercial_InFo_supply_set.setPhrase(new Phrase("No of DG set",
				font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(11).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				"Performance Gaurantee", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				indent_payment_details_DG_SET.get(9).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		// Row 13
		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				"Sub Total (A) (DG sets)", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		cell_commercial_InFo_supply_set.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		cell_commercial_InFo_supply_set.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				lead_pricing_info_DG_SET.get(12).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		cell_commercial_InFo_supply_set.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				"Liquidated Damages", font_bold));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		cell_commercial_InFo_supply_set.setBackgroundColor(Color.WHITE);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		cell_commercial_InFo_supply_set.setPhrase(new Phrase(
				indent_payment_details_DG_SET.get(10).toString(), font_normal));
		cell_commercial_InFo_supply_set
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_supply_set.setBorder(Rectangle.BOX);
		cell_commercial_InFo_supply_set.setBackgroundColor(Color.WHITE);
		tbl_commercial_InFo_supply_set.addCell(cell_commercial_InFo_supply_set);

		PdfPTable tbl_commercials_info_installation_headline = new PdfPTable(1);
		tbl_commercials_info_installation_headline.setTotalWidth(530);
		tbl_commercials_info_installation_headline.setLockedWidth(true);
		PdfPCell cell_commercial_info_installation_headline = new PdfPCell();

		cell_commercial_info_installation_headline.setPhrase(new Phrase(
				"COMMERCIALS INFORMATION - FOR INSTALLATION", font_bold_bg));
		cell_commercial_info_installation_headline
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_info_installation_headline.setBorder(Rectangle.BOX);
		cell_commercial_info_installation_headline
				.setBackgroundColor(Color.gray);
		tbl_commercials_info_installation_headline
				.addCell(cell_commercial_info_installation_headline);

		/*-------------------------------Commercials Information for Installation Table--------------------------------*/

		float w4[] = { 20, 10, 20, 20, 20 };
		PdfPTable tbl_commercial_InFo_installation = new PdfPTable(w4);
		tbl_commercial_InFo_installation.setTotalWidth(530);
		tbl_commercial_InFo_installation.setLockedWidth(true);
		PdfPCell cell_commercial_InFo_installation = new PdfPCell();

		// Row 1
		cell_commercial_InFo_installation.setPhrase(new Phrase(
				"A. Dg set Accessories", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("ADVANCE",
				font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				indent_payment_details_INST.get(0).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 2
		cell_commercial_InFo_installation.setPhrase(new Phrase(
				"Pipes ( Exhaust & Fuel pipes)", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				lead_pricing_info_INST.get(0).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("CHE/DD DET.",
				font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				indent_payment_details_INST.get(1).toString() + "  | DATE |"
						+ indent_payment_details_INST.get(2).toString(),
				font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 3
		cell_commercial_InFo_installation.setPhrase(new Phrase("Vat @ 5%",
				font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("5%",
				font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				lead_pricing_info_INST.get(1).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("BANK, PLACE",
				font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				indent_payment_details_INST.get(3).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 4
		cell_commercial_InFo_installation.setPhrase(new Phrase("Cables",
				font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				lead_pricing_info_INST.get(2).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("BALANCE",
				font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				indent_payment_details_INST.get(4).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 5
		cell_commercial_InFo_installation.setPhrase(new Phrase("Vat @ 5.5%",
				font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("5.5%",
				font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				lead_pricing_info_INST.get(3).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("FORMS",
				font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				indent_payment_details_INST.get(5).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 6
		cell_commercial_InFo_installation.setPhrase(new Phrase(
				"Sub Total ( B ) ( DG set Accessories)", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				lead_pricing_info_INST.get(4).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.TOP);
		cell_commercial_InFo_installation.setBackgroundColor(Color.WHITE);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				indent_payment_details_INST.get(6).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.WHITE);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 7
		cell_commercial_InFo_installation.setPhrase(new Phrase(
				"B. Low side materials supply", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				lead_pricing_info_INST.get(5).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.NO_BORDER);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 8
		cell_commercial_InFo_installation.setPhrase(new Phrase("VAT @ 14.5%",
				font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("14.5%",
				font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				lead_pricing_info_INST.get(6).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				"PAYMENT TERMS (DG set)", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.NO_BORDER);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				indent_payment_details_INST.get(7).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 9
		cell_commercial_InFo_installation.setPhrase(new Phrase(
				"E. Labour for installation", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				lead_pricing_info_INST.get(7).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.NO_BORDER);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 10
		cell_commercial_InFo_installation.setPhrase(new Phrase("VAT @ 14.5%",
				font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("14%",
				font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				lead_pricing_info_INST.get(8).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOTTOM);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				indent_payment_details_INST.get(8).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 11
		cell_commercial_InFo_installation.setPhrase(new Phrase(
				"Sub Total ( c ) ( Low side works)", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				lead_pricing_info_INST.get(9).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.WHITE);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.WHITE);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 12
		cell_commercial_InFo_installation.setPhrase(new Phrase(
				"Total Project value ( A + B + C )", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				lead_pricing_info_INST.get(10).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.WHITE);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				indent_payment_details_INST.get(9).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.WHITE);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 13
		cell_commercial_InFo_installation.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				"Liquidated Damages", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.WHITE);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				indent_payment_details_INST.get(10).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.WHITE);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		// Row 15
		cell_commercial_InFo_installation.setPhrase(new Phrase("", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation
				.setPhrase(new Phrase("", font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.gray);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				"Performance Gaurantee", font_bold));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.WHITE);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		cell_commercial_InFo_installation.setPhrase(new Phrase(
				indent_payment_details_INST.get(11).toString(), font_normal));
		cell_commercial_InFo_installation
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_commercial_InFo_installation.setBorder(Rectangle.BOX);
		cell_commercial_InFo_installation.setBackgroundColor(Color.WHITE);
		tbl_commercial_InFo_installation
				.addCell(cell_commercial_InFo_installation);

		/*-------------------------------Additional Informations / Commitments If Any Table--------------------------------*/

		float w5[] = { 20, 20, 20, 20 };
		PdfPTable tbl_additional_InFo = new PdfPTable(w5);
		tbl_additional_InFo.setTotalWidth(530);
		tbl_additional_InFo.setLockedWidth(true);
		PdfPCell cell_additional_InFo = new PdfPCell();

		// Row 1
		cell_additional_InFo.setPhrase(new Phrase(
				"ADDITIONAL INFORMATIONS / COMMITMENTS IF ANY", font_bold));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		cell_additional_InFo.setPhrase(new Phrase("", font_bold));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		cell_additional_InFo.setPhrase(new Phrase(additional_info.get(0)
				.toString(), font_normal));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		cell_additional_InFo.setPhrase(new Phrase("", font_bold));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		// Row 2
		cell_additional_InFo.setPhrase(new Phrase("DELIVERY OF DG SET",
				font_bold));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		cell_additional_InFo.setBackgroundColor(Color.gray);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		cell_additional_InFo.setPhrase(new Phrase("", font_bold));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		cell_additional_InFo.setBackgroundColor(Color.gray);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		cell_additional_InFo.setPhrase(new Phrase(additional_info.get(1)
				.toString(), font_normal));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		cell_additional_InFo.setBackgroundColor(Color.gray);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		cell_additional_InFo.setPhrase(new Phrase("", font_bold));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		cell_additional_InFo.setBackgroundColor(Color.gray);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		// Row 3
		cell_additional_InFo.setPhrase(new Phrase("TOTAL PROJECT TIME FRAME",
				font_bold));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		cell_additional_InFo.setBackgroundColor(Color.gray);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		cell_additional_InFo.setPhrase(new Phrase("Start Date :"
				+ additional_info.get(2).toString(), font_normal));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		cell_additional_InFo.setBackgroundColor(Color.gray);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		cell_additional_InFo.setPhrase(new Phrase("", font_bold));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		cell_additional_InFo.setBackgroundColor(Color.gray);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		cell_additional_InFo.setPhrase(new Phrase("Approvals :"
				+ additional_info.get(3).toString(), font_normal));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		cell_additional_InFo.setBackgroundColor(Color.gray);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		// Row 4
		cell_additional_InFo.setPhrase(new Phrase("PREPARED BY", font_bold));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		cell_additional_InFo.setBackgroundColor(Color.WHITE);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		cell_additional_InFo.setPhrase(new Phrase(additional_info.get(4)
				.toString(), font_normal));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		cell_additional_InFo.setBackgroundColor(Color.WHITE);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		cell_additional_InFo.setPhrase(new Phrase("APPROVED BY", font_bold));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		cell_additional_InFo.setBackgroundColor(Color.WHITE);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		cell_additional_InFo.setPhrase(new Phrase(additional_info.get(5)
				.toString(), font_normal));
		cell_additional_InFo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_additional_InFo.setBorder(Rectangle.BOX);
		cell_additional_InFo.setBackgroundColor(Color.WHITE);
		tbl_additional_InFo.addCell(cell_additional_InFo);

		float w6[] = { 20, 20 };
		PdfPTable tbl_branch = new PdfPTable(w6);
		tbl_branch.setTotalWidth(530);
		tbl_branch.setLockedWidth(true);
		PdfPCell cell_branch = new PdfPCell();

		cell_branch.setPhrase(new Phrase("ORIGINAL TO BANGALORE", font_bold));
		cell_branch.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_branch.setBorder(Rectangle.BOX);
		tbl_branch.addCell(cell_branch);

		cell_branch.setPhrase(new Phrase("1 COPY AT LOCAL BRANCH", font_bold));
		cell_branch.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_branch.setBorder(Rectangle.BOX);
		tbl_branch.addCell(cell_branch);

		/*-------------------------------Scope of Supply Table--------------------------------*/

		PdfPTable tbl_scope_of_supply_headline = new PdfPTable(1);
		tbl_scope_of_supply_headline.setTotalWidth(530);
		tbl_scope_of_supply_headline.setLockedWidth(true);
		PdfPCell cell_scope_of_supply_headline = new PdfPCell();

		cell_scope_of_supply_headline.setPhrase(new Phrase("SCOPE OF SUPPLY",
				font_bold_bg));
		cell_scope_of_supply_headline
				.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_scope_of_supply_headline.setBorder(Rectangle.BOX);
		cell_scope_of_supply_headline.setBackgroundColor(Color.gray);
		tbl_scope_of_supply_headline.addCell(cell_scope_of_supply_headline);

		float w7[] = { 20, 20 };
		PdfPTable tbl_scope_of_supply_title = new PdfPTable(w7);
		tbl_scope_of_supply_title.setTotalWidth(530);
		tbl_scope_of_supply_title.setLockedWidth(true);
		PdfPCell cell_scopr_of_supply_title = new PdfPCell();

		// Row 1
		cell_scopr_of_supply_title.setPhrase(new Phrase("SGPL - SILVASSA",
				font_bold));
		cell_scopr_of_supply_title.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_scopr_of_supply_title.setBorder(Rectangle.BOX);
		cell_scopr_of_supply_title.setBackgroundColor(Color.gray);
		tbl_scope_of_supply_title.addCell(cell_scopr_of_supply_title);

		cell_scopr_of_supply_title.setPhrase(new Phrase("EXECUTION BRANCH",
				font_bold));
		cell_scopr_of_supply_title.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell_scopr_of_supply_title.setBorder(Rectangle.BOX);
		cell_scopr_of_supply_title.setBackgroundColor(Color.gray);
		tbl_scope_of_supply_title.addCell(cell_scopr_of_supply_title);

		float w8[] = { 20, 10, 20, 10 };
		PdfPTable tbl_sgpl = new PdfPTable(w8);
		tbl_sgpl.setTotalWidth(530);
		tbl_sgpl.setLockedWidth(true);
		PdfPCell cell_sgpl = new PdfPCell();

		List<Entry<String, String>> indexedList_sgpl = new ArrayList<Map.Entry<String, String>>(
				scope_of_supply_sgpl.entrySet());

		List<Entry<String, String>> indexedList_exec_branch = new ArrayList<Map.Entry<String, String>>(
				scope_of_supply_exeu_branch.entrySet());

		for (int k = 0; k < indexedList_exec_branch.size(); k++) {

			Map.Entry<String, String> entry_sgpl = indexedList_sgpl.get(k);

			Map.Entry<String, String> entry_exec_branch = indexedList_exec_branch
					.get(k);

			// Row 1
			cell_sgpl.setPhrase(new Phrase(entry_sgpl.getKey(), font_bold));
			cell_sgpl.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_sgpl.setBorder(Rectangle.BOX);
			tbl_sgpl.addCell(cell_sgpl);

			cell_sgpl.setPhrase(new Phrase(entry_sgpl.getValue(), font_normal));
			cell_sgpl.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell_sgpl.setBorder(Rectangle.BOX);
			tbl_sgpl.addCell(cell_sgpl);

			cell_sgpl.setPhrase(new Phrase(entry_exec_branch.getKey(),
					font_bold));
			cell_sgpl.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_sgpl.setBorder(Rectangle.BOX);
			tbl_sgpl.addCell(cell_sgpl);

			cell_sgpl.setPhrase(new Phrase(entry_exec_branch.getValue(),
					font_normal));
			cell_sgpl.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell_sgpl.setBorder(Rectangle.BOX);
			tbl_sgpl.addCell(cell_sgpl);
		}

		float w9[] = { 20, 40 };
		PdfPTable tbl_warning_info = new PdfPTable(w9);
		tbl_warning_info.setTotalWidth(530);
		tbl_warning_info.setLockedWidth(true);
		PdfPCell cell_warning_info = new PdfPCell();

		// Row 1
		cell_warning_info.setPhrase(new Phrase(
				"WARRANTY FROM DATE OF COMMISSIOING", font_bold));
		cell_warning_info.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_warning_info.setBorder(Rectangle.BOX);
		cell_warning_info.setBackgroundColor(Color.gray);
		tbl_warning_info.addCell(cell_warning_info);

		cell_warning_info.setPhrase(new Phrase(warranty.get(0).toString(),
				font_bold));
		cell_warning_info.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_warning_info.setBorder(Rectangle.BOX);
		cell_warning_info.setBackgroundColor(Color.gray);
		tbl_warning_info.addCell(cell_warning_info);

		// Row 2
		cell_warning_info.setPhrase(new Phrase("OPERATOR REQUIREMENT",
				font_bold));
		cell_warning_info.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_warning_info.setBorder(Rectangle.BOX);
		cell_warning_info.setBackgroundColor(Color.WHITE);
		tbl_warning_info.addCell(cell_warning_info);

		cell_warning_info.setPhrase(new Phrase(warranty.get(1).toString(),
				font_bold));
		cell_warning_info.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell_warning_info.setBorder(Rectangle.BOX);
		cell_warning_info.setBackgroundColor(Color.WHITE);
		tbl_warning_info.addCell(cell_warning_info);

		document.add(tbl_commercials_info_supply_headline);
		document.add(tbl_commercial_InFo_supply_set);

		document.add(tbl_Empty);
		document.add(tbl_Empty);

		document.add(tbl_commercials_info_installation_headline);
		document.add(tbl_commercial_InFo_installation);
		document.add(tbl_additional_InFo);
		document.add(tbl_branch);

		document.add(tbl_Empty);
		document.add(tbl_Empty);

		document.add(tbl_scope_of_supply_headline);
		document.add(tbl_scope_of_supply_title);
		document.add(tbl_sgpl);
		document.add(tbl_warning_info);

		document.close();

		return 1;
	}

	class TableHeader extends PdfPageEventHelper {
		String header;
		PdfTemplate total;

		public void setHeader(String header) {
			this.header = header;
		}

		public void onOpenDocument(PdfWriter writer, Document document) {
			total = writer.getDirectContent().createTemplate(10, 15);
		}

		public void onEndPage(PdfWriter writer, Document document) {
			PdfPTable table = new PdfPTable(3);
			try {
				table.setWidths(new int[] { 20, 60, 1 });
				table.setTotalWidth(530);
				table.setLockedWidth(true);
				table.getDefaultCell().setFixedHeight(10);
				table.getDefaultCell().setBorder(Rectangle.BOTTOM);

				table.addCell(header);
				table.getDefaultCell().setHorizontalAlignment(
						Element.ALIGN_RIGHT);

				table.addCell(new Phrase(String.format("Page %d of",
						writer.getPageNumber()), FontFactory.getFont("ARIAL",
						7, Font.NORMAL)));

				PdfPCell cell = new PdfPCell(Image.getInstance(total));
				cell.setBorder(Rectangle.NO_BORDER);

				table.addCell(cell);
				table.writeSelectedRows(0, -1, 34, 803,
						writer.getDirectContent());

			} catch (DocumentException de) {
				throw new ExceptionConverter(de);
			}
		}

		public void onCloseDocument(PdfWriter writer, Document document) {
			ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
					new Phrase(String.valueOf(writer.getPageNumber() - 1),
							FontFactory.getFont("ARIAL", 7, Font.NORMAL)), 2,
					6, 0);
		}
	}

}
