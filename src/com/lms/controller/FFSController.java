package com.lms.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.dao.ConfigMasterDao;
import com.lms.dao.EngineStockDao;
import com.lms.dao.FFSReportDao;
import com.lms.daoImpl.ConfigMasterDaoImpl;
import com.lms.daoImpl.EngineStockDaoImpl;
import com.lms.daoImpl.FFSReportDaoImpl;
import com.lms.model.ConfigMaster;
import com.lms.model.EngineStock;
import com.lms.model.FFSReport;

/**
 * Servlet implementation class FFSController
 */
public class FFSController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static String CREATE_FFS = "AddFFS.jsp";
	private static String VIEW_STOCK = "updateLead.jsp";
	private static String LIST_FFS = "viewFFS.jsp";
	

	private ConfigMasterDao configdao;
	
	private FFSReportDao ffsReportDao;
	String forward = "";
	String createdBy = "";
	HttpSession session;
	String userName;
	String userID;
	String userRole;
	String userRegion;
	String userState;
	String userShortState;
	String userDCity;
	String txt_m1budgetqty = "";
	String txt_m2budgetqty = "";
	String txt_m3budgetqty = "";
	String txt_m4budgetqty = "";
	String txt_m5budgetqty = "";
	String txt_m6budgetqty = "";
	String txt_m7budgetqty = "";
	String txt_m8budgetqty = "";
	String txt_m9budgetqty = "";
	String txt_m10budgetqty = "";
	String txt_m11budgetqty = "";
	String txt_m12budgetqty = "";
	
	String txt_transit = "";
	String txt_stock_as_on = "";
	String txt_year = "";
	String txt_engine = "";
	List<ConfigMaster> cMasters = new ArrayList<ConfigMaster>();
	List projectTypeList = new ArrayList();
	List regionType = new ArrayList();

	String projectType = "", region = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FFSController() {
        super();
        configdao = new ConfigMasterDaoImpl();
		
		ffsReportDao=new FFSReportDaoImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cMasters = configdao.getConfigMasterData();
		List<FFSReport> engingBudList;
		projectTypeList.clear();
	//	regionType.clear();
		Iterator it = cMasters.iterator();

		while (it.hasNext()) {
			ConfigMaster c = (ConfigMaster) it.next();
			System.out.println(c);
			if (c.getName().equalsIgnoreCase("PROJECT_TYPE")) {
				projectTypeList.add(c.getValue());
			}
			//if (c.getName().equalsIgnoreCase("REGION_TYPE")) {
				//regionType.add(c.getValue());
		//	}
		}

		session = request.getSession();

		System.out.println(session);

		userName = (String) session.getAttribute("luserName");

		userID = (String) session.getAttribute("luserId");
		userRole = (String) session.getAttribute("lroleId");
		userRegion = (String) session.getAttribute("lregion");
		userState = (String) session.getAttribute("lstate");
		userShortState = (String) session.getAttribute("lshortState");
		userDCity = (String) session.getAttribute("lcity");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String createdDate = dateFormat.format(cal.getTime());

		String action = request.getParameter("action");
		request.setAttribute("userRegion", userRegion);
		request.setAttribute("userID", userID);

		request.setAttribute("projectTypes", projectTypeList);

		//request.setAttribute("regionType", regionType);
		String prevFinYear,curFinYear;
		
		Calendar now = Calendar.getInstance(); // Gets the current date
		if ((now.get(Calendar.MONTH)) >= 3) {
			curFinYear = now.get(Calendar.YEAR) + "-"
					+ (now.get(Calendar.YEAR) + 1);
			prevFinYear =(now.get(Calendar.YEAR) - 1) + "-"
					+ now.get(Calendar.YEAR);
		} else {
			curFinYear =(now.get(Calendar.YEAR) - 1) + "-"
					+ now.get(Calendar.YEAR);
			prevFinYear = now.get(Calendar.YEAR) + "-"
					+ (now.get(Calendar.YEAR) + 1);
		}
		request.setAttribute("curFinYear", curFinYear);
		request.setAttribute("prevFinYear", prevFinYear);

		if (action.equalsIgnoreCase("createFFS")) {
			// Get Stock Data
			
			
			request.setAttribute("action", "addFFS");
			request.setAttribute("curFinYear", curFinYear);
			request.setAttribute("prevFinYear", prevFinYear);
			engingBudList = ffsReportDao.getAllFFSReport("", "");
			request.setAttribute("engingBudList", engingBudList);
			forward = CREATE_FFS;
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
		if (action.equalsIgnoreCase("listFFS")) {
			
			String searAction = request.getParameter("search");

			if (searAction.equalsIgnoreCase("All")) {
				 now = Calendar.getInstance(); // Gets the current date
														// and time
				int year = now.get(Calendar.YEAR); // The current year as an int

		
				String sel_txt_year = "";
				if ((now.get(Calendar.MONTH)) >= 3) {
					sel_txt_year = now.get(Calendar.YEAR) + "-"
							+ (now.get(Calendar.YEAR) + 1);
				} else {
					sel_txt_year =(now.get(Calendar.YEAR) - 1) + "-"
							+ now.get(Calendar.YEAR);
				}
				request.setAttribute("action", "listBudgetStock");

				engingBudList = ffsReportDao.getAllFFSReport("","");
				
			} else {
				
				String sel_projectType = request.getParameter("projectType");
				
				String sel_txt_engine = request.getParameter("txt_engine");
				

				engingBudList = ffsReportDao.getAllFFSReport(sel_projectType, sel_txt_engine);
				
				
				request.setAttribute("selprojectType", sel_projectType);
				request.setAttribute("engingBudList", engingBudList);
				

			}

			request.setAttribute("engingBudList", engingBudList);
			forward = LIST_FFS;
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}
		
		if (action.equalsIgnoreCase("listFFS")) {
			
			
			String searAction = request.getParameter("search");

			if (searAction.equalsIgnoreCase("All")) {
				 now = Calendar.getInstance(); // Gets the current date
														// and time
				int year = now.get(Calendar.YEAR); // The current year as an int

		
				String sel_txt_year = "";
				if ((now.get(Calendar.MONTH)) >= 3) {
					sel_txt_year = now.get(Calendar.YEAR) + "-"
							+ (now.get(Calendar.YEAR) + 1);
				} else {
					sel_txt_year =(now.get(Calendar.YEAR) - 1) + "-"
							+ now.get(Calendar.YEAR);
				}
				request.setAttribute("action", "listBudgetStock");
				ffsReportDao.updatePendingandTotalStock();

				engingBudList = ffsReportDao.getAllFFSReport("", "");
				
			} else {
			//	String sel_region = request.getParameter("region");
				String sel_projectType = request.getParameter("projectType");
				//String sel_txt_year = request.getParameter("txt_year");
				String sel_txt_engine = request.getParameter("txt_engine");

				engingBudList = ffsReportDao.getAllFFSReport(sel_projectType, sel_txt_engine);
				
				
				request.setAttribute("selprojectType", sel_projectType);
				//request.setAttribute("userRegion", sel_region);
				//request.setAttribute("sel_txt_year", sel_txt_year);
				request.setAttribute("engingBudList", engingBudList);
				

			}

			request.setAttribute("engingBudList", engingBudList);
			forward = LIST_FFS;
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
		}

		if (action.equalsIgnoreCase("addFFS")) {
			txt_m1budgetqty = request.getParameter("txt_m1budgetqty");
			txt_m2budgetqty = request.getParameter("txt_m2budgetqty");
			txt_m3budgetqty = request.getParameter("txt_m3budgetqty");
			txt_m4budgetqty = request.getParameter("txt_m4budgetqty");
			txt_m5budgetqty = request.getParameter("txt_m5budgetqty");
			txt_m6budgetqty = request.getParameter("txt_m6budgetqty");
			txt_m7budgetqty = request.getParameter("txt_m7budgetqty");
			txt_m8budgetqty = request.getParameter("txt_m8budgetqty");
			txt_m9budgetqty = request.getParameter("txt_m9budgetqty");
			txt_m10budgetqty = request.getParameter("txt_m10budgetqty");
			txt_m11budgetqty = request.getParameter("txt_m11budgetqty");
			txt_m12budgetqty = request.getParameter("txt_m12budgetqty");

			

			txt_transit = request.getParameter("txt_transit");

			region = request.getParameter("region");
			projectType = request.getParameter("projectType");
			txt_year = request.getParameter("txt_year");
			txt_engine = request.getParameter("txt_engine");

			txt_stock_as_on = request.getParameter("txt_stock_as_on");
			txt_year = request.getParameter("txt_year");

			txt_engine = request.getParameter("txt_engine");

			createdBy = request.getParameter("createdBy");
			FFSReport engineStock = new FFSReport();

			
			engineStock.setPROJECT_TYPE(projectType);
			engineStock.setENGINE_NAME(txt_engine);
			engineStock.setFIN_YEAR(txt_year);

			engineStock.setSTOCK_AS_ON(txt_stock_as_on);
			engineStock.setTRANSIT(txt_transit);

			engineStock.setM1qty(txt_m1budgetqty);
			engineStock.setM2qty(txt_m2budgetqty);
			engineStock.setM3qty(txt_m3budgetqty);
			engineStock.setM4qty(txt_m4budgetqty);
			engineStock.setM5qty(txt_m5budgetqty);
			engineStock.setM6qty(txt_m6budgetqty);
			engineStock.setM7qty(txt_m7budgetqty);
			engineStock.setM8qty(txt_m8budgetqty);
			engineStock.setM9qty(txt_m9budgetqty);
			engineStock.setM10qty(txt_m10budgetqty);
			engineStock.setM11qty(txt_m11budgetqty);
			engineStock.setM12qty(txt_m12budgetqty);
			
			engineStock.setTotalFStock("0");
			engineStock.setTotalStock("0");
			engineStock.setExe_pending_order("0");
			engineStock.setPending_order_onhold("0");
			engineStock.setTotalPendingorder("0");
			engineStock.setLeads("");

			

			engineStock.setMODIFIED_BY(createdBy);
			engineStock.setMODIFIED_DATE(createdDate);

			String message = "";
			if (ffsReportDao.addFFSData(engineStock).equalsIgnoreCase(
					"done")) {
				message = "FSS Report Details Has been Successfult Updated";
			} else {
				message = "Error in creating Stock Budget ";
			}
			engingBudList = ffsReportDao.getAllFFSReport("", "");
			
			request.setAttribute("engingBudList", engingBudList);
			
			request.setAttribute("message", message);
			request.setAttribute("action", "addFFS");

			forward = CREATE_FFS;
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
