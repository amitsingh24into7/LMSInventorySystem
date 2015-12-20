package com.lms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lms.dao.AttachmentDao;
import com.lms.dao.ConfigMasterDao;
import com.lms.daoImpl.AttachmentDaoImpl;
import com.lms.daoImpl.ConfigMasterDaoImpl;
import com.lms.model.Attachment;
import com.lms.model.ConfigMaster;
import com.lms.model.LeadHeader;
import com.lms.model.Quoted_Projects;

/**
 * Servlet implementation class WorkingReport
 */
public class WorkingReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private AttachmentDao attachmentDao;
	private ConfigMasterDao configdao;
	List<String> regionType = new ArrayList<String>();
	private static String WORKING_REPORT = "/WorkingReport.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorkingReport() {
		super();
		connection = DbUtil.getDBConnection();
		attachmentDao = new AttachmentDaoImpl();
		configdao = new ConfigMasterDaoImpl();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forward = "", engine = "";
		String cType = "", pType = "";
		regionType.clear();

		List<ConfigMaster> cMasters = configdao.getConfigMasterData();

		Iterator it = cMasters.iterator();

		while (it.hasNext()) {
			ConfigMaster c = (ConfigMaster) it.next();
			System.out.println(c);

			if (c.getName().equalsIgnoreCase("REGION_TYPE")) {
				regionType.add(c.getValue());
			}

		}
		String region = "", state = "", city = "", user = "", role = "";
		String action = request.getParameter("action");
		String businessFile = request.getParameter("businessFile");
		HttpSession session = request.getSession();

		if (action.equalsIgnoreCase("all")) {
			String userName = (String) session.getAttribute("luserName");
			String userID = (String) session.getAttribute("luserId");
			user = userID;
			String userRole = (String) session.getAttribute("lroleId");
			role = userRole;
			String userRegion = (String) session.getAttribute("lregion");
			region = userRegion;
			String userState = (String) session.getAttribute("lstate");
			state = userState;
			String userShortState = (String) session
					.getAttribute("lshortState");
			String userDCity = (String) session.getAttribute("lcity");
			action = "viewWorking";
			forward = WORKING_REPORT;
			request.setAttribute("userDCity", city);
			request.setAttribute("userState", state);
			request.setAttribute("userRegion", region);
			request.setAttribute("userRole", role);
			request.setAttribute("userID", user);
			request.setAttribute("regionTypes", regionType);

			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);

		} else {
			region = request.getParameter("region");
			user = request.getParameter("user");
			// role = request.getParameter("roleName");
			action = "viewWorking";
			String outfileName = "";
			try {
				outfileName = downloadWorkingReport(region);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.setContentType("text/html");
			response.getWriter().print(outfileName);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String forward = "", engine = "", outputfileName = "";
		String cType = "", pType = "";
		regionType.clear();

		List<ConfigMaster> cMasters = configdao.getConfigMasterData();

		Iterator it = cMasters.iterator();

		while (it.hasNext()) {
			ConfigMaster c = (ConfigMaster) it.next();
			System.out.println(c);

			if (c.getName().equalsIgnoreCase("REGION_TYPE")) {
				regionType.add(c.getValue());
			}

		}
		String region = "", state = "", city = "", user = "", role = "";
		String action = request.getParameter("action");
		String businessFile = request.getParameter("businessFile");
		HttpSession session = request.getSession();

		action = "viewWorking";
		forward = WORKING_REPORT;
		request.setAttribute("userDCity", city);
		request.setAttribute("userState", state);
		request.setAttribute("userRegion", region);
		request.setAttribute("userRole", role);
		request.setAttribute("userID", user);

		try {
			outputfileName = uploadReport(request, response);
			updateRecords(outputfileName);
			request.setAttribute("message",
					"Sheet has  uploaded and updated successfully");
		} catch (Exception e) {
			outputfileName = e.getMessage();
			request.setAttribute("message",
					"There are some error while updating sheet , Either Sheet is not proper or there are some error , please contact administrator");
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);

		view.forward(request, response);
	}

	public String downloadWorkingReport(String region) throws IOException {

		String inFileName = "/excelTemplate/Blr_Working_format.xlsx";
		// InputStream inputStream =
		// DbUtil.class.getClassLoader().getResourceAsStream(inFileName);
		// InputStream is = this.getClass().getResourceAsStream(inFileName);

		Calendar cal = Calendar.getInstance();
		cal.getTime();
		String root = getServletContext().getRealPath("/");

		File path = new File(root + "/uploads/excelTemplate");
		if (!path.exists()) {
			boolean status = path.mkdirs();
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar1 = Calendar.getInstance();
		System.out.println(dateFormat.format(calendar1.getTime()));

		String fName = "Blr_Working_format_"
				+ dateFormat.format(calendar1.getTime()) + ".xlsx";
		String outfileName = path + "/" + fName;
		File outFile = new File(outfileName);

		FileInputStream fis = new FileInputStream(new File(root + inFileName));
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFRow row = null;
		XSSFCell colum = null;

		String[] sheetname = { "QUOTED PROJECTS-Current Year",
				"QUOTED PROJECTS-Earlier Yr", "UPCOMNG PROJETCS" };

		for (int sheetno = 0; sheetno < sheetname.length; sheetno++) {
			XSSFSheet s = wb.getSheet(sheetname[sheetno]);

			if (s.getSheetName().trim()
					.equalsIgnoreCase("QUOTED PROJECTS-Current Year")
					|| s.getSheetName().trim()
							.equalsIgnoreCase("QUOTED PROJECTS-Earlier Yr")) {
				String getsheetname = s.getSheetName().trim();

				int rowcount = s.getLastRowNum();
				for (int r = 1; r < rowcount; r++) {
					row = s.getRow(r);

					try {
						s.removeRow(row);
					} catch (Exception e) {
						continue;
					}
				}

				List<Quoted_Projects> quotedprojects_list = getQuoted_Projects(
						getsheetname, region);

				int rowIndex = 1;

				for (Quoted_Projects quoted_projects : quotedprojects_list) {
					row = s.createRow(rowIndex++);
					int cellIndex = 0;

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getLEAD_ID());// QUOTE REF NO.-PEDKF
															// or QUOTE

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getLEAD_CREATION_REGION());// REGION

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getCREATION_DATE());// MONTH

					row.createCell(cellIndex++).setCellValue("");// DAY

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPROJECT_TYPE());// CLIENT NAME

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPROJECT_REFERENCE());// PROJECT
																	// REFERENCE

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getLEAD_CREATION_PLACE());// LOCATION

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getDTL_ENGINE_MAKE());// ENGINE

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getDTL_ALTERNATOR_MAKE());// ALTERNATOR
																		// MAKE

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPROJECT_TYPE());// DG SET
																// RANGE-P=PROJ
																// /R=RETAIL

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getDTL_RATING());// RATING

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getDTL_QTY());// QTY

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getDG_ED_VALUE());// DG VALUE IN
																// LAKHS

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getLOW_SIDE_LABOUR_BASIC());// LSW
																		// VALUE

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPROJECT_TOTAL());// PROJECT TOTAL

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPROJECT_MARGIN());// COSTING
																	// MARGIN
																	// FOR WON
																	// PROJECTS

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getLEAD_STATUS());// STATUS

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getCREATION_DATE());// MONTH OF
																// ORDER

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getCOMPETITIONS());// COMPETITORS

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getLEAD_CHANCES());// CHANCES:R=RED/Y=YELLOW/G=GREEN

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getOWNER_NAME());// OWNER

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getCOMMENTS());// REMARKS

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getSEGMENT());// SEGMENT

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getCONSULTANT_NAME());// CONSULTANT

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getCONSULTANT_PERSON_NAME());// CONTACT

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getCONSULTANT_MOBILE_NO());// CONTACT
																		// NO

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPMC_NAME());// PMC

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPMC_CONTACT_PERSON());// PRIMARY
																		// CONTACT
																		// PERSON

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPMC_CONTACT_NO());// CONTACT NO

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getEND_PERSON_CUSTOMER_NAME());// NAME
																			// OF
																			// END
																			// CUSTOMER

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getEND_PERSON_CUSTOMER_NAME());// END
																			// CUSTOMER
																			// CONTACT
																			// PERSON

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getEND_PERSON_CUSTOMER_CONTACTNO());// CONTACT
																				// NO

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getDTL_ID());// ID
				}
			} else if (s.getSheetName().trim()
					.equalsIgnoreCase("UPCOMNG PROJETCS")) {

				String getsheetname = s.getSheetName().trim();

				int rowcount = s.getLastRowNum();
				for (int r = 1; r < rowcount; r++) {
					row = s.getRow(r);

					try {
						s.removeRow(row);
					} catch (Exception e) {
						continue;
					}
				}

				List<Quoted_Projects> quotedprojects_list = getQuoted_Projects(
						getsheetname, region);
				int rowIndex = 1;

				for (Quoted_Projects quoted_projects : quotedprojects_list) {
					row = s.createRow(rowIndex++);
					int cellIndex = 0;

					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getLEAD_ID());// SRN
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPROJECT_TYPE());// CLIENT NAME
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getLEAD_CREATION_PLACE());// LOCATION
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPROJECT_REFERENCE());// PROJECT
																	// REFERENCE
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getDTL_ENGINE_MAKE());// ENGINE
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getDTL_RATING());// RATING
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getDTL_QTY());// QTY
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getDG_ED_VALUE());// DG VALUE IN
																// LAKHS
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPROJECT_TYPE());// DG SET
																// RANGE-P=PROJ
																// /R=RETAIL
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getLEAD_CHANCES());// CHANCES:R=RED/Y=YELLOW/G=GREEN
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getOWNER_NAME());// OWNER
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getCOMMENTS());// REMARKS
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getSEGMENT());// SEGMENT
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getCONSULTANT_NAME());// CONSULTANT
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getCONSULTANT_PERSON_NAME());// CONTACT
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getCONSULTANT_MOBILE_NO());// CONTACT
																		// NO
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPMC_NAME());// PMC
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPMC_CONTACT_PERSON());// PRIMARY
																		// CONTACT
																		// PERSON
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getPMC_CONTACT_NO());// CONTACT NO
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getEND_PERSON_CUSTOMER_NAME());// NAME
																			// OF
																			// END
																			// CUSTOMER
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getEND_PERSON_CUSTOMER_NAME());// END
																			// CUSTOMER
																			// CONTACT
																			// PERSON
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getEND_PERSON_CUSTOMER_CONTACTNO());// CONTACT
																				// NO
					row.createCell(cellIndex++).setCellValue(
							quoted_projects.getDTL_ID());// ID
				}
			}
		}

		fis.close();

		FileOutputStream output_file = new FileOutputStream(outFile); // Open
																		// FileOutputStream
																		// to
																		// write
																		// updates

		wb.write(output_file);

		output_file.close();
		return fName;
	}

	public String uploadReport(HttpServletRequest request,
			HttpServletResponse response) {

		// Getting Current Date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String createdDate = dateFormat.format(cal.getTime());

		String outputfileName = "";
		String fileName = "";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		Attachment attachment = new Attachment();
		String createdBy = "";
		File uploadedFile;
		if (isMultipart) {
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<FileItem> multiparts = upload.parseRequest(request);

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {

						fileName = item.getName();
						fileName = Math.random() + fileName;
						String root = getServletContext().getRealPath("/");
						File path = new File(root + "/uploads/excelTemplate");
						if (!path.exists()) {
							boolean status = path.mkdirs();
						}

						outputfileName = path + "/" + fileName;
						uploadedFile = new File(path + "/" + fileName);
						System.out.println(uploadedFile);
						item.write(uploadedFile);

					} else if (item.isFormField()) {
						String name = item.getFieldName();
						String value = item.getString();
						if (name.equalsIgnoreCase("createdBy")) {
							createdBy = item.getString();
						}

					} else {
					}

				}

				attachment.setDOC_NAME(fileName);
				attachment.setDOC_URL("uploads/excelTemplate/" + fileName);
				attachment.setDOC_CONTENT_TYPE("EXCEL");
				attachment.setDOC_CREATED_BY(createdBy);
				attachment.setLEAD_ID("NO");
				attachment.setDOC_VERSION("1.0");
				attachment.setDOC_CREATED_DATE(createdDate);
				attachment.setDOC_TYPE("WORKINGREPORT");
				System.out.println(attachment.toString());
				attachmentDao.addAttachments(attachment);

			} catch (Exception e) {
				// System.out.println("File upload failed");
				e.printStackTrace();
				outputfileName = "File upload failed";
			}
		}

		return outputfileName;

	}

	public String updateRecords(String fileName) throws Exception {

		String message = "";
		FileInputStream fis = new FileInputStream(new File(fileName));
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFRow row = null;
		XSSFCell colum = null;
		List<Quoted_Projects> quotedprojects_list = new ArrayList<Quoted_Projects>();
		List<Quoted_Projects> quotedprojects_list_upcomig_year = new ArrayList<Quoted_Projects>();
		try {

			String[] sheetname = { "QUOTED PROJECTS-Current Year",
					"QUOTED PROJECTS-Earlier Yr", "UPCOMNG PROJETCS" };

			for (int sheetno = 0; sheetno < sheetname.length; sheetno++) {
				XSSFSheet s = wb.getSheet(sheetname[sheetno]);

				if (s.getSheetName().trim()
						.equalsIgnoreCase("QUOTED PROJECTS-Current Year")
						|| s.getSheetName().trim()
								.equalsIgnoreCase("QUOTED PROJECTS-Earlier Yr")) {

					Iterator ite = s.rowIterator();

					int length = s.getLastRowNum();

					while (ite.hasNext()) {
						Row r = (Row) ite.next();

						String LEAD_ID = "", DTL_ENGINE_MAKE = "", DTL_ALTERNATOR_MAKE = "", PROJECT_TYPE = "", LEAD_STATUS = "", LEAD_CHANCES = "";

						int DTL_ID = -1;

						if (r.getCell(32) != null) {
							if (!r.getCell(32).toString().equalsIgnoreCase("")) {

								if (r.getCell(0) != null) {
									LEAD_ID = r.getCell(0).toString();
								} else {
									LEAD_ID = "";
								}

								if (r.getCell(7) != null) {
									DTL_ENGINE_MAKE = r.getCell(7).toString();
								} else {
									DTL_ENGINE_MAKE = "";
								}

								if (r.getCell(8) != null) {
									DTL_ALTERNATOR_MAKE = r.getCell(8)
											.toString();
								} else {
									DTL_ALTERNATOR_MAKE = "";
								}

								if (r.getCell(9) != null) {
									PROJECT_TYPE = r.getCell(9).toString();
								} else {
									PROJECT_TYPE = "";
								}

								if (r.getCell(16) != null) {
									LEAD_STATUS = r.getCell(16).toString();
								} else {
									LEAD_STATUS = "";
								}

								if (r.getCell(19) != null) {
									LEAD_CHANCES = r.getCell(19).toString();
								} else {
									LEAD_CHANCES = "";
								}

								if (r.getCell(32) != null) {
									DTL_ID = (int) Float.parseFloat(r.getCell(
											32).toString());
								} else {
									DTL_ID = -1;
								}

								System.out.println(LEAD_ID);
								System.out.println(DTL_ENGINE_MAKE);
								System.out.println(DTL_ALTERNATOR_MAKE);
								System.out.println(PROJECT_TYPE);
								System.out.println(LEAD_CHANCES);
								System.out.println(LEAD_STATUS);
								System.out.println(DTL_ID);
								System.out.println();

								quotedprojects_list.add(new Quoted_Projects(
										LEAD_ID, DTL_ENGINE_MAKE,
										DTL_ALTERNATOR_MAKE, PROJECT_TYPE,
										LEAD_STATUS, LEAD_CHANCES, DTL_ID));
							}
						}
					}
				} else if (s.getSheetName().trim()
						.equalsIgnoreCase("UPCOMNG PROJETCS")) {
					Iterator ite = s.rowIterator();

					while (ite.hasNext()) {
						Row r = (Row) ite.next();

						String LEAD_ID = "", DTL_ENGINE_MAKE = "", PROJECT_TYPE = "", LEAD_CHANCES = "";

						int DTL_ID = -1;

						if (r.getCell(22) != null) {
							if (!r.getCell(22).toString().equalsIgnoreCase("")) {
								if (r.getCell(0) != null) {
									LEAD_ID = r.getCell(0).toString();
								} else {
									LEAD_ID = "";
								}

								if (r.getCell(4) != null) {
									DTL_ENGINE_MAKE = r.getCell(4).toString();
								} else {
									DTL_ENGINE_MAKE = "";
								}

								if (r.getCell(8) != null) {
									PROJECT_TYPE = r.getCell(8).toString();
								} else {
									PROJECT_TYPE = "";
								}

								if (r.getCell(9) != null) {
									LEAD_CHANCES = r.getCell(9).toString();
								} else {
									LEAD_CHANCES = "";
								}

								if (r.getCell(22) != null) {
									DTL_ID = (int) Float.parseFloat(r.getCell(
											22).toString());
								} else {
									DTL_ID = -1;
								}

								quotedprojects_list_upcomig_year
										.add(new Quoted_Projects(LEAD_ID,
												DTL_ENGINE_MAKE, "",
												PROJECT_TYPE, "", LEAD_CHANCES,
												DTL_ID));

							}
						}

					}
				}
			}
			Statement stm = connection.createStatement();
			String query = "";
			for (Quoted_Projects quoted_projects : quotedprojects_list) {

				query = "";
				query = "update lead_details set DTL_ENGINE_MAKE='"
						+ quoted_projects.getDTL_ENGINE_MAKE()
						+ "',DTL_ALTERNATOR_MAKE='"
						+ quoted_projects.getDTL_ALTERNATOR_MAKE()
						+ "' where DTL_ID='" + quoted_projects.getDTL_ID()
						+ "';";
				stm.addBatch(query);

				query = "";
				query = "update lead_header set PROJECT_TYPE='"
						+ quoted_projects.getPROJECT_TYPE()
						+ "', LEAD_STATUS='" + quoted_projects.getLEAD_STATUS()
						+ "', LEAD_CHANCES='"
						+ quoted_projects.getLEAD_CHANCES()
						+ "' where LEAD_ID='" + quoted_projects.getLEAD_ID()
						+ "' ";
				stm.addBatch(query);

			}

			for (Quoted_Projects quoted_projects_upcoming_year : quotedprojects_list_upcomig_year) {

				query = "";
				query = "update lead_details set DTL_ENGINE_MAKE='"
						+ quoted_projects_upcoming_year.getDTL_ENGINE_MAKE()
						+ "' where DTL_ID='"
						+ quoted_projects_upcoming_year.getDTL_ID() + "';";
				stm.addBatch(query);

				query = "";
				query = "update lead_header set PROJECT_TYPE='"
						+ quoted_projects_upcoming_year.getPROJECT_TYPE()
						+ "', LEAD_CHANCES='"
						+ quoted_projects_upcoming_year.getLEAD_CHANCES()
						+ "' where LEAD_ID='"
						+ quoted_projects_upcoming_year.getLEAD_ID() + "' ";
				stm.addBatch(query);

			}

			stm.executeBatch();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	// Get Quoted Project using sheet Name
	public List<Quoted_Projects> getQuoted_Projects(String sheetname,
			String region) {
		Calendar now = Calendar.getInstance();
		String sel_txt_year;
		String start_date = "", end_date = "";
		String prev_start_date = "", prev_end_date = "";

		if ((now.get(Calendar.MONTH)) >= 3) {

			start_date = now.get(Calendar.YEAR) + "-04" + "-01";
			end_date = (now.get(Calendar.YEAR) + 1) + "-03" + "-31";

			prev_start_date = (now.get(Calendar.YEAR) - 1) + "-04" + "-01";
			prev_end_date = now.get(Calendar.YEAR) + "-03" + "-31";

		} else {
			start_date = (now.get(Calendar.YEAR) - 1) + "-04" + "-01";
			end_date = now.get(Calendar.YEAR) + "-03" + "-31";

			prev_start_date = (now.get(Calendar.YEAR) - 2) + "-04" + "-01";
			prev_end_date = (now.get(Calendar.YEAR) - 1) + "-03" + "-31";

		}

		String query = "", comments = "";

		List<Quoted_Projects> quotedprojects_list = new ArrayList<Quoted_Projects>();

		try {

			Statement st = connection.createStatement();
			Statement st1 = connection.createStatement();

			System.out.println("sheetname----->" + sheetname);

			if (sheetname.equalsIgnoreCase("QUOTED PROJECTS-Current Year")) {
				query = "select c.* from (select a.LEAD_ID, a.TRANSACTION_TYPE, a.PROJECT_TYPE, a.ENQUIRY_TYPE, a.LEAD_BUDG, a.PROJECT_EXE_PLACE, a.PROJECT_EXE_STATE, a.LEAD_CREATION_PLACE, a.LEAD_CREATION_STATE, a.LEAD_CRETION_SHORT_STATE, a.LEAD_CREATION_REGION, cast(a.CREATION_DATE as DATE) as CREATION_DATE, a.CREATED_BY, a.MODIFIED_DATE, a.MODIFIED_BY, a.OWNER_ID, a.OWNER_NAME, a.PROJECT_REFERENCE, a.DG_TYPE, a.SEGMENT, a.COMPETITIONS, a.LEAD_CHANCES, a.LEAD_STATUS, a.CONSULTANT_NAME, a.CONSULTANT_PERSON_NAME, a.CONSULTANT_MOBILE_NO, a.CUSTOMER_NAME, a.CUSTOMER_EMAIL_ADDRESS, a.CUSTOMER_ADDRESS, a.CUSTOMER_MOBILENO, a.CUSTOMER_ALTERNATE_NO, a.END_PERSON_CUSTOMER_NAME, a.END_PERSON_CUSTOMER_CONTACTNO, a.PMC_NAME, a.PMC_CONTACT_PERSON, a.PMC_CONTACT_NO, a.PMC_CONTACT_ALT_NO, a.LOW_SIDE_SUPPLY_BASIC, a.LOW_SIDE_LABOUR_BASIC, a.LOW_SIDE_MARGIN, a.PROJECT_MARGIN, a.DG_ED_VALUE, a.PROJECT_TOTAL, b.DTL_RATING, b.DTL_ITEM_TYPE, b.DTL_ENGINE_MAKE, b.DTL_ENGINE_MODEL, b.DTL_ALTERNATOR_MAKE, b.DTL_ALTERNATOR_MODEL, b.DTL_VOLTAGE, b.DTL_HZ, b.DTL_QTY, b.DTL_COOLING_SYSTEM, b.DTL_AMOUNT, b.DTL_FLEX1, b.DTL_ID from  lead_header a,lead_details b where a.lead_id=b.lead_id and b.DTL_ITEM_TYPE='ENGINE' and a.LEAD_BUDG='0' and a.LEAD_CREATION_REGION='"
						+ region
						+ "' ) as c  where cast(c.creation_date as date) between '"
						+ start_date + "' and '" + end_date + "';";
			} else if (sheetname.equalsIgnoreCase("QUOTED PROJECTS-Earlier Yr")) {
				query = "select c.* from (select a.LEAD_ID, a.TRANSACTION_TYPE, a.PROJECT_TYPE, a.ENQUIRY_TYPE, a.LEAD_BUDG, a.PROJECT_EXE_PLACE, a.PROJECT_EXE_STATE, a.LEAD_CREATION_PLACE, a.LEAD_CREATION_STATE, a.LEAD_CRETION_SHORT_STATE, a.LEAD_CREATION_REGION, cast(a.CREATION_DATE as DATE) as CREATION_DATE, a.CREATED_BY, a.MODIFIED_DATE, a.MODIFIED_BY, a.OWNER_ID, a.OWNER_NAME, a.PROJECT_REFERENCE, a.DG_TYPE, a.SEGMENT, a.COMPETITIONS, a.LEAD_CHANCES, a.LEAD_STATUS, a.CONSULTANT_NAME, a.CONSULTANT_PERSON_NAME, a.CONSULTANT_MOBILE_NO, a.CUSTOMER_NAME, a.CUSTOMER_EMAIL_ADDRESS, a.CUSTOMER_ADDRESS, a.CUSTOMER_MOBILENO, a.CUSTOMER_ALTERNATE_NO, a.END_PERSON_CUSTOMER_NAME, a.END_PERSON_CUSTOMER_CONTACTNO, a.PMC_NAME, a.PMC_CONTACT_PERSON, a.PMC_CONTACT_NO, a.PMC_CONTACT_ALT_NO, a.LOW_SIDE_SUPPLY_BASIC, a.LOW_SIDE_LABOUR_BASIC, a.LOW_SIDE_MARGIN, a.PROJECT_MARGIN, a.DG_ED_VALUE, a.PROJECT_TOTAL, b.DTL_RATING, b.DTL_ITEM_TYPE, b.DTL_ENGINE_MAKE, b.DTL_ENGINE_MODEL, b.DTL_ALTERNATOR_MAKE, b.DTL_ALTERNATOR_MODEL, b.DTL_VOLTAGE, b.DTL_HZ, b.DTL_QTY, b.DTL_COOLING_SYSTEM, b.DTL_AMOUNT, b.DTL_FLEX1, b.DTL_ID from  lead_header a,lead_details b where a.lead_id=b.lead_id and b.DTL_ITEM_TYPE='ENGINE' and a.LEAD_BUDG='0' and a.LEAD_CREATION_REGION='"
						+ region
						+ "' ) as c  where cast(c.creation_date as date) between '"
						+ start_date + "' and '" + end_date + "';";
			} else if (sheetname.equalsIgnoreCase("UPCOMNG PROJETCS")) {
				query = "select c.* from (select a.LEAD_ID, a.TRANSACTION_TYPE, a.PROJECT_TYPE, a.ENQUIRY_TYPE, a.LEAD_BUDG, a.PROJECT_EXE_PLACE, a.PROJECT_EXE_STATE, a.LEAD_CREATION_PLACE, a.LEAD_CREATION_STATE, a.LEAD_CRETION_SHORT_STATE, a.LEAD_CREATION_REGION, cast(a.CREATION_DATE as DATE) as CREATION_DATE, a.CREATED_BY, a.MODIFIED_DATE, a.MODIFIED_BY, a.OWNER_ID, a.OWNER_NAME, a.PROJECT_REFERENCE, a.DG_TYPE, a.SEGMENT, a.COMPETITIONS, a.LEAD_CHANCES, a.LEAD_STATUS, a.CONSULTANT_NAME, a.CONSULTANT_PERSON_NAME, a.CONSULTANT_MOBILE_NO, a.CUSTOMER_NAME, a.CUSTOMER_EMAIL_ADDRESS, a.CUSTOMER_ADDRESS, a.CUSTOMER_MOBILENO, a.CUSTOMER_ALTERNATE_NO, a.END_PERSON_CUSTOMER_NAME, a.END_PERSON_CUSTOMER_CONTACTNO, a.PMC_NAME, a.PMC_CONTACT_PERSON, a.PMC_CONTACT_NO, a.PMC_CONTACT_ALT_NO, a.LOW_SIDE_SUPPLY_BASIC, a.LOW_SIDE_LABOUR_BASIC, a.LOW_SIDE_MARGIN, a.PROJECT_MARGIN, a.DG_ED_VALUE, a.PROJECT_TOTAL, b.DTL_RATING, b.DTL_ITEM_TYPE, b.DTL_ENGINE_MAKE, b.DTL_ENGINE_MODEL, b.DTL_ALTERNATOR_MAKE, b.DTL_ALTERNATOR_MODEL, b.DTL_VOLTAGE, b.DTL_HZ, b.DTL_QTY, b.DTL_COOLING_SYSTEM, b.DTL_AMOUNT, b.DTL_FLEX1, b.DTL_ID from  lead_header a,lead_details b where a.lead_id=b.lead_id and b.DTL_ITEM_TYPE='ENGINE' and a.LEAD_BUDG='1' and a.LEAD_CREATION_REGION='"
						+ region + "' ) as c ";
			}
			System.out.println("query----->" + query);

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				query = "";
				query = "select DETAILS from comments where LEAD_ID='"
						+ rs.getString("LEAD_ID") + "';";
				ResultSet rs1 = st1.executeQuery(query);
				if (rs1.next()) {
					comments = rs1.getString("DETAILS");
				} else {
					comments = "";
				}

				quotedprojects_list.add(new Quoted_Projects(rs.getString("LEAD_ID"),rs.getString("TRANSACTION_TYPE"), rs.getString("PROJECT_TYPE"), rs
								.getString("ENQUIRY_TYPE"), rs
								.getString("LEAD_BUDG"), rs
								.getString("PROJECT_EXE_PLACE"), rs
								.getString("PROJECT_EXE_STATE"), rs
								.getString("LEAD_CREATION_PLACE"), rs
								.getString("LEAD_CREATION_STATE"), rs
								.getString("LEAD_CRETION_SHORT_STATE"), rs
								.getString("LEAD_CREATION_REGION"), rs
								.getString("CREATION_DATE"), rs
								.getString("CREATED_BY"), rs
								.getString("MODIFIED_DATE"), rs
								.getString("MODIFIED_BY"), rs
								.getString("OWNER_ID"), rs
								.getString("OWNER_NAME"), rs
								.getString("DG_TYPE"), rs.getString("SEGMENT"),
						rs.getString("COMPETITIONS"), rs
								.getString("LEAD_CHANCES"), rs
								.getString("LEAD_STATUS"), rs
								.getString("CONSULTANT_NAME"), rs
								.getString("CONSULTANT_PERSON_NAME"), rs
								.getString("CONSULTANT_MOBILE_NO"), rs
								.getString("CUSTOMER_NAME"), rs
								.getString("CUSTOMER_EMAIL_ADDRESS"), rs
								.getString("CUSTOMER_ADDRESS"), rs
								.getString("CUSTOMER_MOBILENO"), rs
								.getString("CUSTOMER_ALTERNATE_NO"), rs
								.getString("END_PERSON_CUSTOMER_NAME"), rs
								.getString("END_PERSON_CUSTOMER_CONTACTNO"), rs
								.getString("PMC_NAME"), rs
								.getString("PMC_CONTACT_PERSON"), rs
								.getString("PMC_CONTACT_NO"), rs
								.getString("PMC_CONTACT_ALT_NO"), rs
								.getString("LOW_SIDE_SUPPLY_BASIC"), rs
								.getString("LOW_SIDE_LABOUR_BASIC"), rs
								.getString("LOW_SIDE_MARGIN"), rs
								.getString("PROJECT_MARGIN"), rs
								.getString("DG_ED_VALUE"), rs
								.getString("PROJECT_TOTAL"), rs
								.getString("DTL_RATING"), rs
								.getString("DTL_ITEM_TYPE"), rs
								.getString("DTL_ENGINE_MAKE"), rs
								.getString("DTL_ENGINE_MODEL"), rs
								.getString("DTL_ALTERNATOR_MAKE"), rs
								.getString("DTL_ALTERNATOR_MODEL"), rs
								.getString("DTL_VOLTAGE"), rs
								.getString("DTL_HZ"), rs.getString("DTL_QTY"),
						rs.getString("DTL_COOLING_SYSTEM"), rs
								.getString("DTL_AMOUNT"), rs
								.getString("DTL_FLEX1"), rs
								.getString("PROJECT_REFERENCE"), Integer
								.parseInt(rs.getString("DTL_ID")), comments));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return quotedprojects_list;
	}

}

/*
 * package com.lms.util;
 * 
 * import java.io.File; import java.io.FileInputStream; import
 * java.io.FileNotFoundException; import java.io.FileOutputStream; import
 * java.io.IOException; import java.io.InputStream; import java.sql.Connection;
 * import java.sql.ResultSet; import java.sql.Statement; import
 * java.text.DateFormat; import java.text.SimpleDateFormat; import
 * java.util.ArrayList; import java.util.Calendar; import java.util.Iterator;
 * import java.util.LinkedHashMap; import java.util.List; import java.util.Map;
 * 
 * import javax.servlet.RequestDispatcher; import
 * javax.servlet.ServletException; import javax.servlet.http.HttpServlet; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession;
 * 
 * import org.apache.commons.fileupload.FileItem; import
 * org.apache.commons.fileupload.FileItemFactory; import
 * org.apache.commons.fileupload.disk.DiskFileItemFactory; import
 * org.apache.commons.fileupload.servlet.ServletFileUpload; import
 * org.apache.poi.ss.usermodel.Row; import
 * org.apache.poi.xssf.usermodel.XSSFCell; import
 * org.apache.poi.xssf.usermodel.XSSFRow; import
 * org.apache.poi.xssf.usermodel.XSSFSheet; import
 * org.apache.poi.xssf.usermodel.XSSFWorkbook;
 * 
 * import com.lms.dao.AttachmentDao; import com.lms.dao.ConfigMasterDao; import
 * com.lms.daoImpl.AttachmentDaoImpl; import
 * com.lms.daoImpl.ConfigMasterDaoImpl; import com.lms.model.Attachment; import
 * com.lms.model.ConfigMaster; import com.lms.model.LeadHeader; import
 * com.lms.model.Quoted_Projects;
 * 
 * 
 * public class WorkingReport extends HttpServlet { private static final long
 * serialVersionUID = 1L; private Connection connection; private AttachmentDao
 * attachmentDao; private ConfigMasterDao configdao; List<String> regionType =
 * new ArrayList<String>(); private static String WORKING_REPORT =
 * "/WorkingReport.jsp";
 * 
 * 
 * public WorkingReport() { super(); connection = DbUtil.getDBConnection();
 * attachmentDao=new AttachmentDaoImpl(); configdao = new ConfigMasterDaoImpl();
 * 
 * 
 * // TODO Auto-generated constructor stub }
 * 
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { // TODO Auto-generated
 * method stub String forward = "", engine = ""; String cType="",pType="";
 * regionType.clear();
 * 
 * List<ConfigMaster> cMasters = configdao.getConfigMasterData();
 * 
 * Iterator it = cMasters.iterator();
 * 
 * while (it.hasNext()) { ConfigMaster c = (ConfigMaster) it.next();
 * System.out.println(c);
 * 
 * 
 * if (c.getName().equalsIgnoreCase("REGION_TYPE")) {
 * regionType.add(c.getValue()); }
 * 
 * } String region="", state="", city="", user="", role=""; String action =
 * request.getParameter("action"); String businessFile =
 * request.getParameter("businessFile"); HttpSession session =
 * request.getSession();
 * 
 * if (action.equalsIgnoreCase("all")) { String userName = (String)
 * session.getAttribute("luserName"); String userID = (String)
 * session.getAttribute("luserId"); user = userID; String userRole = (String)
 * session.getAttribute("lroleId"); role = userRole; String userRegion =
 * (String) session.getAttribute("lregion"); region = userRegion; String
 * userState = (String) session.getAttribute("lstate"); state = userState;
 * String userShortState = (String) session .getAttribute("lshortState"); String
 * userDCity = (String) session.getAttribute("lcity"); action="viewWorking";
 * forward=WORKING_REPORT; request.setAttribute("userDCity", city);
 * request.setAttribute("userState", state); request.setAttribute("userRegion",
 * region); request.setAttribute("userRole", role);
 * request.setAttribute("userID", user); request.setAttribute("regionTypes",
 * regionType);
 * 
 * RequestDispatcher view = request.getRequestDispatcher(forward);
 * view.forward(request, response);
 * 
 * 
 * } else { region = request.getParameter("region"); user =
 * request.getParameter("user"); //role = request.getParameter("roleName");
 * action="viewWorking"; String outfileName=""; try{
 * outfileName=downloadWorkingReport(region); } catch(Exception e) {
 * e.printStackTrace(); } response.setContentType("text/html");
 * response.getWriter().print(outfileName); }
 * 
 * 
 * 
 * 
 * }
 * 
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { // TODO Auto-generated
 * method stub
 * 
 * String forward = "", engine = "",outputfileName=""; String cType="",pType="";
 * regionType.clear();
 * 
 * List<ConfigMaster> cMasters = configdao.getConfigMasterData();
 * 
 * Iterator it = cMasters.iterator();
 * 
 * while (it.hasNext()) { ConfigMaster c = (ConfigMaster) it.next();
 * System.out.println(c);
 * 
 * 
 * if (c.getName().equalsIgnoreCase("REGION_TYPE")) {
 * regionType.add(c.getValue()); }
 * 
 * } String region="", state="", city="", user="", role=""; String action =
 * request.getParameter("action"); String businessFile =
 * request.getParameter("businessFile"); HttpSession session =
 * request.getSession();
 * 
 * action="viewWorking"; forward=WORKING_REPORT;
 * request.setAttribute("userDCity", city); request.setAttribute("userState",
 * state); request.setAttribute("userRegion", region);
 * request.setAttribute("userRole", role); request.setAttribute("userID", user);
 * 
 * 
 * 
 * try { outputfileName=uploadReport(request,response);
 * updateRecords(outputfileName); request.setAttribute("message",
 * "Sheet has  uplaoded and updated successfully"); }catch(Exception e) {
 * outputfileName=e.getMessage(); request.setAttribute("message",
 * "There are some erro while updating sheet , please contact administrator"); }
 * RequestDispatcher view = request.getRequestDispatcher(forward);
 * 
 * 
 * view.forward(request, response); }
 * 
 * public String downloadWorkingReport(String region) throws IOException {
 * 
 * String inFileName = "/excelTemplate/Blr_Working_format.xlsx"; //InputStream
 * inputStream = DbUtil.class.getClassLoader().getResourceAsStream(inFileName);
 * //InputStream is = this.getClass().getResourceAsStream(inFileName);
 * 
 * Calendar cal=Calendar.getInstance(); cal.getTime(); String root =
 * getServletContext().getRealPath("/");
 * 
 * 
 * 
 * 
 * File path = new File(root + "/uploads/excelTemplate"); if (!path.exists()) {
 * boolean status = path.mkdirs(); }
 * 
 * 
 * DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); Calendar
 * calendar1 = Calendar.getInstance();
 * System.out.println(dateFormat.format(calendar1.getTime()));
 * 
 * String
 * fName="Blr_Working_format_"+dateFormat.format(calendar1.getTime())+".xlsx";
 * String outfileName = path + "/" +fName; File outFile=new File(outfileName);
 * 
 * FileInputStream fis = new FileInputStream(new File(root + inFileName));
 * XSSFWorkbook wb = new XSSFWorkbook(fis);
 * 
 * XSSFRow row = null; XSSFCell colum = null; String[]
 * sheetList={"QUOTED PROJECTS-Current Year"
 * ,"QUOTED PROJECTS-Earlier Yr","UPCOMNG PROJETCS"};
 * 
 * 
 * for (int sheetno = 0; sheetno < sheetList.length; sheetno++) {
 * 
 * 
 * XSSFSheet s = wb.getSheet(sheetList[sheetno]);
 * 
 * if (s.getSheetName().trim() .equalsIgnoreCase("QUOTED PROJECTS-Current Year")
 * || s.getSheetName().trim() .equalsIgnoreCase("QUOTED PROJECTS-Earlier Yr")) {
 * String sheetname = s.getSheetName().trim();
 * 
 * 
 * List<Quoted_Projects> quotedprojects_list =
 * getQuoted_Projects(sheetname,region);
 * 
 * int rowIndex = 1;
 * 
 * for (Quoted_Projects quoted_projects : quotedprojects_list) {
 * System.out.println(quotedprojects_list);
 * 
 * row = s.createRow(rowIndex++); System.out.println(rowIndex); int cellIndex =
 * 0; System.out.println(quoted_projects.getLEAD_ID());
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getLEAD_ID());//
 * QUOTE REF NO.-PEDKF // or QUOTE
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getLEAD_CREATION_REGION());// REGION
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getCREATION_DATE());// MONTH
 * 
 * row.createCell(cellIndex++).setCellValue("");// DAY
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getPROJECT_TYPE());// CLIENT NAME
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getPROJECT_REFERENCE());// PROJECT // REFERENCE
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getLEAD_CREATION_PLACE());// LOCATION
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getDTL_ENGINE_MAKE());// ENGINE
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getDTL_ALTERNATOR_MODEL());// ALTERNATOR // MAKE
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getPROJECT_TYPE());// DG SET // RANGE-P=PROJ // /R=RETAIL
 * 
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getDTL_RATING());//
 * RATING
 * 
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getDTL_QTY());//
 * QTY
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getDG_ED_VALUE());// DG VALUE IN // LAKHS
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getLOW_SIDE_LABOUR_BASIC());// LSW // VALUE
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getPROJECT_TOTAL());// PROJECT TOTAL
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getPROJECT_MARGIN());// COSTING // MARGIN // FOR WON //
 * PROJECTS
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getLEAD_STATUS());// STATUS
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getCREATION_DATE());// MONTH OF // ORDER
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getCOMPETITIONS());// COMPETITORS
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getLEAD_CHANCES());// CHANCES:R=RED/Y=YELLOW/G=GREEN
 * 
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getOWNER_NAME());//
 * OWNER
 * 
 * row.createCell(cellIndex++).setCellValue("");// REMARKS
 * 
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getSEGMENT());//
 * SEGMENT
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getCONSULTANT_NAME());// CONSULTANT
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getCONSULTANT_PERSON_NAME());// CONTACT
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getCONSULTANT_MOBILE_NO());// CONTACT // NO
 * 
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getPMC_NAME());//
 * PMC
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getPMC_CONTACT_PERSON());// PRIMARY // CONTACT // PERSON
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getPMC_CONTACT_NO());// CONTACT NO
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getEND_PERSON_CUSTOMER_NAME());// NAME // OF // END //
 * CUSTOMER
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getEND_PERSON_CUSTOMER_NAME());// END // CUSTOMER // CONTACT
 * // PERSON
 * 
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getEND_PERSON_CUSTOMER_CONTACTNO());// CONTACT // NO
 * 
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getDTL_ID());// ID
 * } } else if (s.getSheetName().trim() .equalsIgnoreCase("UPCOMNG PROJETCS")) {
 * 
 * String sheetname = s.getSheetName().trim();
 * 
 * 
 * 
 * List<Quoted_Projects> quotedprojects_list =
 * getQuoted_Projects(sheetname,region);
 * 
 * int rowIndex = 1;
 * 
 * for (Quoted_Projects quoted_projects : quotedprojects_list) { row =
 * s.createRow(rowIndex++); int cellIndex = 0;
 * 
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getLEAD_ID());//
 * SRN row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getPROJECT_TYPE());// CLIENT NAME
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getLEAD_CREATION_PLACE());// LOCATION
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getPROJECT_REFERENCE());// PROJECT // REFERENCE
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getDTL_ENGINE_MAKE());// ENGINE
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getDTL_RATING());//
 * RATING row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getDTL_QTY());// QTY
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getDG_ED_VALUE());// DG VALUE IN // LAKHS
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getPROJECT_TYPE());// DG SET // RANGE-P=PROJ // /R=RETAIL
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getLEAD_CHANCES());// CHANCES:R=RED/Y=YELLOW/G=GREEN
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getOWNER_NAME());//
 * OWNER row.createCell(cellIndex++).setCellValue("");// REMARKS
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getSEGMENT());//
 * SEGMENT row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getCONSULTANT_NAME());// CONSULTANT
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getCONSULTANT_PERSON_NAME());// CONTACT
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getCONSULTANT_MOBILE_NO());// CONTACT // NO
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getPMC_NAME());//
 * PMC row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getPMC_CONTACT_PERSON());// PRIMARY // CONTACT // PERSON
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getPMC_CONTACT_NO());// CONTACT NO
 * row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getEND_PERSON_CUSTOMER_NAME());// NAME // OF // END //
 * CUSTOMER row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getEND_PERSON_CUSTOMER_NAME());// END // CUSTOMER // CONTACT
 * // PERSON row.createCell(cellIndex++).setCellValue(
 * quoted_projects.getEND_PERSON_CUSTOMER_CONTACTNO());// CONTACT // NO
 * row.createCell(cellIndex++).setCellValue( quoted_projects.getDTL_ID());// ID
 * } } }
 * 
 * fis.close();
 * 
 * 
 * FileOutputStream output_file = new FileOutputStream(outFile); // Open
 * FileOutputStream to write updates
 * 
 * wb.write(output_file);
 * 
 * output_file.close(); return fName; }
 * 
 * public String uploadReport(HttpServletRequest request, HttpServletResponse
 * response) {
 * 
 * //Getting Current Date DateFormat dateFormat = new
 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Calendar cal =
 * Calendar.getInstance(); String createdDate=dateFormat.format(cal.getTime());
 * 
 * 
 * String outputfileName = ""; String fileName=""; boolean isMultipart =
 * ServletFileUpload.isMultipartContent(request); Attachment attachment=new
 * Attachment(); String createdBy=""; File uploadedFile; if (isMultipart) { //
 * Create a factory for disk-based file items FileItemFactory factory = new
 * DiskFileItemFactory();
 * 
 * // Create a new file upload handler ServletFileUpload upload = new
 * ServletFileUpload(factory); try { // Parse the request List<FileItem>
 * multiparts = upload.parseRequest(request);
 * 
 * for (FileItem item : multiparts) { if (!item.isFormField()) {
 * 
 * fileName = item.getName(); fileName=Math.random()+fileName; String root =
 * getServletContext().getRealPath("/"); File path = new File(root +
 * "/uploads/excelTemplate"); if (!path.exists()) { boolean status =
 * path.mkdirs(); }
 * 
 * outputfileName=path + "/" + fileName; uploadedFile = new File(path + "/" +
 * fileName); System.out.println(uploadedFile); item.write(uploadedFile);
 * 
 * 
 * } else if (item.isFormField()) { String name = item.getFieldName(); String
 * value = item.getString(); if(name.equalsIgnoreCase("createdBy")) {
 * createdBy=item.getString(); }
 * 
 * 
 * }else{}
 * 
 * 
 * 
 * }
 * 
 * 
 * 
 * attachment.setDOC_NAME(fileName);
 * attachment.setDOC_URL("uploads/excelTemplate/"+fileName);
 * attachment.setDOC_CONTENT_TYPE("EXCEL");
 * attachment.setDOC_CREATED_BY(createdBy); attachment.setLEAD_ID("NO");
 * attachment.setDOC_VERSION("1.0");
 * attachment.setDOC_CREATED_DATE(createdDate);
 * attachment.setDOC_TYPE("WORKINGREPORT");
 * System.out.println(attachment.toString());
 * attachmentDao.addAttachments(attachment);
 * 
 * 
 * } catch (Exception e) { // System.out.println("File upload failed");
 * e.printStackTrace(); outputfileName="File upload failed"; } }
 * 
 * return outputfileName;
 * 
 * }
 * 
 * public String updateRecords(String fileName) throws Exception {
 * 
 * 
 * 
 * String message = ""; FileInputStream fis = new FileInputStream(new
 * File(fileName)); XSSFWorkbook wb = new XSSFWorkbook(fis);
 * 
 * XSSFRow row = null; XSSFCell colum = null; List<Quoted_Projects>
 * quotedprojects_list = new ArrayList<Quoted_Projects>();
 * 
 * try {
 * 
 * for (int sheetno = 0; sheetno < wb.getNumberOfNames() - 2; sheetno++) {
 * XSSFSheet s = wb.getSheetAt(sheetno);
 * 
 * if (s.getSheetName().trim() .equalsIgnoreCase("QUOTED PROJECTS-Current Year")
 * || s.getSheetName().trim() .equalsIgnoreCase("QUOTED PROJECTS-Earlier Yr")) {
 * 
 * Iterator ite = s.rowIterator();
 * 
 * int length = s.getLastRowNum();
 * 
 * while (ite.hasNext()) { Row r = (Row) ite.next();
 * 
 * String LEAD_ID = "", DTL_ENGINE_MAKE = "", DTL_ALTERNATOR_MAKE = "",
 * PROJECT_TYPE = "", LEAD_STATUS = "", LEAD_CHANCES = "";
 * 
 * int DTL_ID = -1;
 * 
 * if (r.getCell(32) != null) { if
 * (!r.getCell(32).toString().equalsIgnoreCase("")) {
 * 
 * if (r.getCell(0) != null) { LEAD_ID = r.getCell(0).toString(); } else {
 * LEAD_ID = ""; }
 * 
 * if (r.getCell(7) != null) { DTL_ENGINE_MAKE = r.getCell(7).toString(); } else
 * { DTL_ENGINE_MAKE = ""; }
 * 
 * if (r.getCell(8) != null) { DTL_ALTERNATOR_MAKE = r.getCell(8) .toString(); }
 * else { DTL_ALTERNATOR_MAKE = ""; }
 * 
 * if (r.getCell(9) != null) { PROJECT_TYPE = r.getCell(9).toString(); } else {
 * PROJECT_TYPE = ""; }
 * 
 * if (r.getCell(16) != null) { LEAD_STATUS = r.getCell(16).toString(); } else {
 * LEAD_STATUS = ""; }
 * 
 * if (r.getCell(19) != null) { LEAD_CHANCES = r.getCell(19).toString(); } else
 * { LEAD_CHANCES = ""; }
 * 
 * if (r.getCell(32) != null) { DTL_ID = (int) Float.parseFloat(r.getCell(
 * 32).toString()); } else { DTL_ID = -1; }
 * 
 * System.out.println(DTL_ENGINE_MAKE); System.out.println(DTL_ALTERNATOR_MAKE);
 * System.out.println(PROJECT_TYPE); System.out.println(LEAD_CHANCES);
 * System.out.println(LEAD_STATUS); System.out.println(DTL_ID);
 * System.out.println();
 * 
 * quotedprojects_list.add(new Quoted_Projects( LEAD_ID, DTL_ENGINE_MAKE,
 * DTL_ALTERNATOR_MAKE, PROJECT_TYPE, LEAD_STATUS, LEAD_CHANCES, DTL_ID)); } } }
 * } } Statement stm = connection.createStatement(); for (Quoted_Projects
 * quoted_projects : quotedprojects_list) {
 * 
 * String query = "";
 * 
 * query = ""; query = "update lead_details set DTL_ENGINE_MAKE='" +
 * quoted_projects.getDTL_ENGINE_MAKE() + "',DTL_ALTERNATOR_MAKE='" +
 * quoted_projects.getDTL_ALTERNATOR_MAKE() + "' where DTL_ID='" +
 * quoted_projects.getDTL_ID() + "';"; stm.executeUpdate(query);
 * 
 * System.out.println("Lead Details "+query); query = "";
 * 
 * query = "update lead_header set PROJECT_TYPE='" +
 * quoted_projects.getPROJECT_TYPE() + "', LEAD_STATUS='" +
 * quoted_projects.getLEAD_STATUS() + "', LEAD_CHANCES='" +
 * quoted_projects.getLEAD_CHANCES() + "' where LEAD_ID='" +
 * quoted_projects.getLEAD_ID() + "' ";
 * System.out.println("Lead Header "+query); stm.executeUpdate(query);
 * 
 * }
 * 
 * } catch (Exception e) { e.printStackTrace(); } return message; }
 * 
 * // Get Quoted Project using sheet Name public List<Quoted_Projects>
 * getQuoted_Projects(String sheetname,String region) { Calendar now =
 * Calendar.getInstance(); String sel_txt_year; String start_date = "", end_date
 * = ""; if ((now.get(Calendar.MONTH)) >= 3) {
 * 
 * start_date = now.get(Calendar.YEAR) + "-04" + "-01"; end_date =
 * (now.get(Calendar.YEAR) + 1) + "-03" + "-31";
 * 
 * } else { start_date = (now.get(Calendar.YEAR) - 1) + "-04" + "-01"; end_date
 * = now.get(Calendar.YEAR) + "-03" + "-31"; }
 * 
 * String query = "";
 * 
 * List<Quoted_Projects> quotedprojects_list = new ArrayList<Quoted_Projects>();
 * 
 * try { System.out.println("sheetname----->" + sheetname);
 * 
 * if (sheetname.equalsIgnoreCase("QUOTED PROJECTS-Current Year")) { query =
 * "select c.* from (select a.LEAD_ID, a.TRANSACTION_TYPE, a.PROJECT_TYPE, a.ENQUIRY_TYPE, a.LEAD_BUDG, a.PROJECT_EXE_PLACE, a.PROJECT_EXE_STATE, a.LEAD_CREATION_PLACE, a.LEAD_CREATION_STATE, a.LEAD_CRETION_SHORT_STATE, a.LEAD_CREATION_REGION, cast(a.CREATION_DATE as DATE) as CREATION_DATE, a.CREATED_BY, a.MODIFIED_DATE, a.MODIFIED_BY, a.OWNER_ID, a.OWNER_NAME, a.PROJECT_REFERENCE, a.DG_TYPE, a.SEGMENT, a.COMPETITIONS, a.LEAD_CHANCES, a.LEAD_STATUS, a.CONSULTANT_NAME, a.CONSULTANT_PERSON_NAME, a.CONSULTANT_MOBILE_NO, a.CUSTOMER_NAME, a.CUSTOMER_EMAIL_ADDRESS, a.CUSTOMER_ADDRESS, a.CUSTOMER_MOBILENO, a.CUSTOMER_ALTERNATE_NO, a.END_PERSON_CUSTOMER_NAME, a.END_PERSON_CUSTOMER_CONTACTNO, a.PMC_NAME, a.PMC_CONTACT_PERSON, a.PMC_CONTACT_NO, a.PMC_CONTACT_ALT_NO, a.LOW_SIDE_SUPPLY_BASIC, a.LOW_SIDE_LABOUR_BASIC, a.LOW_SIDE_MARGIN, a.PROJECT_MARGIN, a.DG_ED_VALUE, a.PROJECT_TOTAL, b.DTL_RATING, b.DTL_ITEM_TYPE, b.DTL_ENGINE_MAKE, b.DTL_ENGINE_MODEL, b.DTL_ALTERNATOR_MAKE, b.DTL_ALTERNATOR_MODEL, b.DTL_VOLTAGE, b.DTL_HZ, b.DTL_QTY, b.DTL_COOLING_SYSTEM, b.DTL_AMOUNT, b.DTL_FLEX1, b.DTL_ID from  lead_header a,lead_details b where a.lead_id=b.lead_id and b.DTL_ITEM_TYPE='ENGINE' and a.LEAD_BUDG='0' and a.LEAD_CREATION_REGION='"
 * +region+"' ) as c  where cast(c.creation_date as date) between '" +
 * start_date + "' and '" + end_date + "';"; } else if
 * (sheetname.equalsIgnoreCase("QUOTED PROJECTS-Earlier Yr")) { query =
 * "select c.* from (select a.LEAD_ID, a.TRANSACTION_TYPE, a.PROJECT_TYPE, a.ENQUIRY_TYPE, a.LEAD_BUDG, a.PROJECT_EXE_PLACE, a.PROJECT_EXE_STATE, a.LEAD_CREATION_PLACE, a.LEAD_CREATION_STATE, a.LEAD_CRETION_SHORT_STATE, a.LEAD_CREATION_REGION, cast(a.CREATION_DATE as DATE) as CREATION_DATE, a.CREATED_BY, a.MODIFIED_DATE, a.MODIFIED_BY, a.OWNER_ID, a.OWNER_NAME, a.PROJECT_REFERENCE, a.DG_TYPE, a.SEGMENT, a.COMPETITIONS, a.LEAD_CHANCES, a.LEAD_STATUS, a.CONSULTANT_NAME, a.CONSULTANT_PERSON_NAME, a.CONSULTANT_MOBILE_NO, a.CUSTOMER_NAME, a.CUSTOMER_EMAIL_ADDRESS, a.CUSTOMER_ADDRESS, a.CUSTOMER_MOBILENO, a.CUSTOMER_ALTERNATE_NO, a.END_PERSON_CUSTOMER_NAME, a.END_PERSON_CUSTOMER_CONTACTNO, a.PMC_NAME, a.PMC_CONTACT_PERSON, a.PMC_CONTACT_NO, a.PMC_CONTACT_ALT_NO, a.LOW_SIDE_SUPPLY_BASIC, a.LOW_SIDE_LABOUR_BASIC, a.LOW_SIDE_MARGIN, a.PROJECT_MARGIN, a.DG_ED_VALUE, a.PROJECT_TOTAL, b.DTL_RATING, b.DTL_ITEM_TYPE, b.DTL_ENGINE_MAKE, b.DTL_ENGINE_MODEL, b.DTL_ALTERNATOR_MAKE, b.DTL_ALTERNATOR_MODEL, b.DTL_VOLTAGE, b.DTL_HZ, b.DTL_QTY, b.DTL_COOLING_SYSTEM, b.DTL_AMOUNT, b.DTL_FLEX1, b.DTL_ID from  lead_header a,lead_details b where a.lead_id=b.lead_id and b.DTL_ITEM_TYPE='ENGINE' and a.LEAD_BUDG='0' and a.LEAD_CREATION_REGION='"
 * +region+"' ) as c  where cast(c.creation_date as date) between '" +
 * start_date + "' and '" + end_date + "';"; } else if
 * (sheetname.equalsIgnoreCase("UPCOMNG PROJETCS")) { query =
 * "select c.* from (select a.LEAD_ID, a.TRANSACTION_TYPE, a.PROJECT_TYPE, a.ENQUIRY_TYPE, a.LEAD_BUDG, a.PROJECT_EXE_PLACE, a.PROJECT_EXE_STATE, a.LEAD_CREATION_PLACE, a.LEAD_CREATION_STATE, a.LEAD_CRETION_SHORT_STATE, a.LEAD_CREATION_REGION, cast(a.CREATION_DATE as DATE) as CREATION_DATE, a.CREATED_BY, a.MODIFIED_DATE, a.MODIFIED_BY, a.OWNER_ID, a.OWNER_NAME, a.PROJECT_REFERENCE, a.DG_TYPE, a.SEGMENT, a.COMPETITIONS, a.LEAD_CHANCES, a.LEAD_STATUS, a.CONSULTANT_NAME, a.CONSULTANT_PERSON_NAME, a.CONSULTANT_MOBILE_NO, a.CUSTOMER_NAME, a.CUSTOMER_EMAIL_ADDRESS, a.CUSTOMER_ADDRESS, a.CUSTOMER_MOBILENO, a.CUSTOMER_ALTERNATE_NO, a.END_PERSON_CUSTOMER_NAME, a.END_PERSON_CUSTOMER_CONTACTNO, a.PMC_NAME, a.PMC_CONTACT_PERSON, a.PMC_CONTACT_NO, a.PMC_CONTACT_ALT_NO, a.LOW_SIDE_SUPPLY_BASIC, a.LOW_SIDE_LABOUR_BASIC, a.LOW_SIDE_MARGIN, a.PROJECT_MARGIN, a.DG_ED_VALUE, a.PROJECT_TOTAL, b.DTL_RATING, b.DTL_ITEM_TYPE, b.DTL_ENGINE_MAKE, b.DTL_ENGINE_MODEL, b.DTL_ALTERNATOR_MAKE, b.DTL_ALTERNATOR_MODEL, b.DTL_VOLTAGE, b.DTL_HZ, b.DTL_QTY, b.DTL_COOLING_SYSTEM, b.DTL_AMOUNT, b.DTL_FLEX1, b.DTL_ID from  lead_header a,lead_details b where a.lead_id=b.lead_id and b.DTL_ITEM_TYPE='ENGINE' and a.LEAD_BUDG='1' and a.LEAD_CREATION_REGION='"
 * +region+"' ) as c "; } System.out.println("query----->" + query); Statement
 * st = connection.createStatement();
 * 
 * ResultSet rs = st.executeQuery(query);
 * 
 * while (rs.next()) { quotedprojects_list.add(new Quoted_Projects(rs
 * .getString("LEAD_ID"), rs.getString("TRANSACTION_TYPE"), rs
 * .getString("PROJECT_TYPE"), rs .getString("ENQUIRY_TYPE"), rs
 * .getString("LEAD_BUDG"), rs .getString("PROJECT_EXE_PLACE"), rs
 * .getString("PROJECT_EXE_STATE"), rs .getString("LEAD_CREATION_PLACE"), rs
 * .getString("LEAD_CREATION_STATE"), rs .getString("LEAD_CRETION_SHORT_STATE"),
 * rs .getString("LEAD_CREATION_REGION"), rs .getString("CREATION_DATE"), rs
 * .getString("CREATED_BY"), rs .getString("MODIFIED_DATE"), rs
 * .getString("MODIFIED_BY"), rs .getString("OWNER_ID"), rs
 * .getString("OWNER_NAME"), rs .getString("DG_TYPE"), rs.getString("SEGMENT"),
 * rs.getString("COMPETITIONS"), rs .getString("LEAD_CHANCES"), rs
 * .getString("LEAD_STATUS"), rs .getString("CONSULTANT_NAME"), rs
 * .getString("CONSULTANT_PERSON_NAME"), rs .getString("CONSULTANT_MOBILE_NO"),
 * rs .getString("CUSTOMER_NAME"), rs .getString("CUSTOMER_EMAIL_ADDRESS"), rs
 * .getString("CUSTOMER_ADDRESS"), rs .getString("CUSTOMER_MOBILENO"), rs
 * .getString("CUSTOMER_ALTERNATE_NO"), rs
 * .getString("END_PERSON_CUSTOMER_NAME"), rs
 * .getString("END_PERSON_CUSTOMER_CONTACTNO"), rs .getString("PMC_NAME"), rs
 * .getString("PMC_CONTACT_PERSON"), rs .getString("PMC_CONTACT_NO"), rs
 * .getString("PMC_CONTACT_ALT_NO"), rs .getString("LOW_SIDE_SUPPLY_BASIC"), rs
 * .getString("LOW_SIDE_LABOUR_BASIC"), rs .getString("LOW_SIDE_MARGIN"), rs
 * .getString("PROJECT_MARGIN"), rs .getString("DG_ED_VALUE"), rs
 * .getString("PROJECT_TOTAL"), rs .getString("DTL_RATING"), rs
 * .getString("DTL_ITEM_TYPE"), rs .getString("DTL_ENGINE_MAKE"), rs
 * .getString("DTL_ENGINE_MODEL"), rs .getString("DTL_ALTERNATOR_MAKE"), rs
 * .getString("DTL_ALTERNATOR_MODEL"), rs .getString("DTL_VOLTAGE"), rs
 * .getString("DTL_HZ"), rs.getString("DTL_QTY"),
 * rs.getString("DTL_COOLING_SYSTEM"), rs .getString("DTL_AMOUNT"), rs
 * .getString("DTL_FLEX1"), rs .getString("PROJECT_REFERENCE"), Integer
 * .parseInt(rs.getString("DTL_ID")))); } } catch (Exception e) {
 * e.printStackTrace(); }
 * 
 * return quotedprojects_list; }
 * 
 * }
 */
