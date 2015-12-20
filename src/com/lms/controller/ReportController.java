package com.lms.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.dao.ConfigMasterDao;
import com.lms.dao.LeadItemDetailsDao;
import com.lms.dao.ReportDao;
import com.lms.dao.UserRoleDao;
import com.lms.daoImpl.ConfigMasterDaoImpl;
import com.lms.daoImpl.LeadItemDetailsDaoImpl;
import com.lms.daoImpl.ReportDaoImpl;
import com.lms.daoImpl.UserRoleDaoImpl;
import com.lms.model.ConfigMaster;
import com.lms.model.EngineStatus;
import com.lms.model.OrderBoard;
import com.lms.model.Project;
import com.lms.model.Quote;
import com.lms.model.QuoteRpt;
import com.lms.model.ROrderLost;
import com.lms.model.UserRole;
import com.lms.util.Utility;

/**
 * Servlet implementation class ReportController
 */
public class ReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReportDao reportDao;
	private ConfigMasterDao configdao;
	private UserRoleDao userRoleDao;
	private LeadItemDetailsDao itemDetailsDao;

	private static String PROJECT_REPORT = "/projectreport.jsp";
	private static String OB_REPORT = "/OBReport.jsp";
	private static String QUOTE_REPORT = "/QuoteAnalysisReport.jsp";
	private static String ENQUIRY_REPORT = "/EnquiryAnalysisReport.jsp";
	private static String ORDER_LOST_REPORT = "/OrderLostReport.jsp";

	private static String MOP_REPORT = "/mopreport.jsp";
	private static String FFS_REPORT = "/mopreport.jsp";
	private static String ORDERLOSS_REPORT = "/mopreport.jsp";
	private static String ANALYSIS_REPORT = "/mopreport.jsp";

	String reportType = "";
	List<String> exe_city = new ArrayList<String>();
	List<String> exe_state = new ArrayList<String>();
	List<String> regionType = new ArrayList<String>();
	List<String> engineMakeType = new ArrayList<String>();

	List<String> chanceType = new ArrayList<String>();
	List<String> projectType = new ArrayList<String>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportController() {
		super();
		reportDao = new ReportDaoImpl();
		configdao = new ConfigMasterDaoImpl();
		userRoleDao = new UserRoleDaoImpl();
		itemDetailsDao = new LeadItemDetailsDaoImpl();

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
		String cType="",pType="";
		chanceType.clear();projectType.clear();
		exe_city.clear();
		exe_state.clear();
		regionType.clear();
		engineMakeType.clear();

		List<ConfigMaster> cMasters = configdao.getConfigMasterData();

		Iterator it = cMasters.iterator();

		while (it.hasNext()) {
			ConfigMaster c = (ConfigMaster) it.next();
			System.out.println(c);

			if (c.getName().equalsIgnoreCase("EXE_CITY")) {
				exe_city.add(c.getValue());
			}
			if (c.getName().equalsIgnoreCase("EXE_STATE")) {
				exe_state.add(c.getValue());
			}
			if (c.getName().equalsIgnoreCase("REGION_TYPE")) {
				regionType.add(c.getValue());
			}
			if (c.getName().equalsIgnoreCase("CHANCES_TYPE")) {
				chanceType.add(c.getValue());
			}
			if (c.getName().equalsIgnoreCase("PROJECT_TYPE")) {
				projectType.add(c.getValue());
			}

		}
		List engineMakesList=itemDetailsDao.getDistinctEngineNamefromLeaadDetails();
		Iterator iteMakes = engineMakesList.iterator();

		while (iteMakes.hasNext()) {
			engineMakeType.add(iteMakes.next().toString());
		}
		
		reportType = request.getParameter("reportType");
		System.out.println(reportType);
		List<Project> projectReportList;
		List<OrderBoard> orderBoardList;
		List<ROrderLost> orderLostList;
		List<Quote> quoteList;
		List enquiryBank;
		
		List<List> enquiryBankList = null;

		String region, state, city, user, role, fromDate, toDate;

		String action = request.getParameter("action");
		String businessFile = request.getParameter("businessFile");
		HttpSession session = request.getSession();

		System.out.println(session);

		request.setAttribute("exe_city", exe_city);
		request.setAttribute("exe_state", exe_state);
		request.setAttribute("regionType", regionType);
		request.setAttribute("engineMakeType", engineMakeType);

		List<UserRole> seList = userRoleDao.getUsersByRole("SE");
		request.setAttribute("seList", seList);

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
			city = userDCity;
			engine = "0";
			cType="0";
			pType="0";

			fromDate = Utility.getFirstDayofCurrentMonth();
			toDate = Utility.getLastDayofCurrentMonth();

		} else {

			region = request.getParameter("region");
			state = request.getParameter("state");
			city = request.getParameter("city");
			user = request.getParameter("user");
			role = request.getParameter("roleName");
			fromDate = request.getParameter("from_date");
			toDate = request.getParameter("to_date");
			if (reportType.equals("QuoteAnalysis")) {
				engine = request.getParameter("engine");
				request.setAttribute("userEngine", engine);

			}
			if (reportType.equals("EnquiryAnalysis")) {
				engine = request.getParameter("engine");
				pType = request.getParameter("projectType");
				cType = request.getParameter("chanceType");
				
				request.setAttribute("userEngine", engine);
				request.setAttribute("userProject", pType);
				request.setAttribute("userChance", cType);

			}
		}

		request.setAttribute("userDCity", city);
		request.setAttribute("userState", state);
		request.setAttribute("userRegion", region);
		request.setAttribute("userRole", role);

		request.setAttribute("userID", user);

		request.setAttribute("from_date", fromDate);
		request.setAttribute("to_date", toDate);
		
		
		

		if (reportType.equalsIgnoreCase("ProjectReport")) {
			forward = PROJECT_REPORT;
			projectReportList = reportDao.getProjectReport(Calendar.YEAR + "");
			request.setAttribute("reportdetails", projectReportList);
		}
		if (reportType.equalsIgnoreCase("OrderBoardReport")) {

			orderBoardList = reportDao.getOrderBoardDetails(fromDate, toDate,
					region, state, city, role, user);
			request.setAttribute("reportdetails", orderBoardList);

			forward = OB_REPORT;
		}
		if (reportType.equalsIgnoreCase("OrderLostReport")) {

			orderLostList = reportDao.getLostOrders(fromDate, toDate,region, state, city, role, user);
			request.setAttribute("reportdetails", orderLostList);

			forward = ORDER_LOST_REPORT;
		}
		if (reportType.equalsIgnoreCase("QuoteAnalysis")) {

			Quote q = new Quote();
			QuoteRpt qp;
			Iterator itQuote = null;

			List<QuoteRpt> quoteReportList = new LinkedList<QuoteRpt>();
			List<QuoteRpt> totquoteReportList = new LinkedList<QuoteRpt>();

			String engineType, totEngineQty, totEngineAmt;
			String orderWinQty = "0", orderLostQty = "0", orderPendingQty = "0", orderWinAmount = "0", orderLostAmount = "0", orderPendingAmount = "0";

			String ordertotQty = "0", ordertotAmt = "0";
			String qty = "0", amt = "0";

			if (engine.equalsIgnoreCase("0")) {
				Iterator engineIt = engineMakeType.iterator();
				
				System.out.println("Engine Make Type"+engineMakeType);

				while (engineIt.hasNext()) {
					qp = new QuoteRpt();
					engine = engineIt.next().toString();
					qp.setEngine(engine);

					quoteList = reportDao.getQuoteAnalysis(fromDate, toDate,
							region, state, city, role, user, engine, "Pending");
					itQuote = quoteList.iterator();
					if (itQuote.hasNext()) {
						q = (Quote) itQuote.next();

						qty = q.getDTL_QTY();
						amt = new BigDecimal(q.getDTL_AMOUNT()).longValue()
								+ "";

						qp.setOrderPendingQty(qty);
						qp.setOrderPendingAmount(amt);

					} else {
						qp.setOrderPendingQty(qty);
						qp.setOrderPendingAmount(amt);

					}
					quoteList.clear();
					quoteList = reportDao.getQuoteAnalysis(fromDate, toDate,
							region, state, city, role, user, engine, "Won");
					itQuote = quoteList.iterator();
					if (itQuote.hasNext()) {
						q = (Quote) itQuote.next();

						qty = q.getDTL_QTY();
						amt = new BigDecimal(q.getDTL_AMOUNT()).longValue()
								+ "";

						qp.setOrderWinQty(qty);
						qp.setOrderWinAmount(amt);

					} else {
						qp.setOrderWinQty(qty);
						qp.setOrderWinQty(qty);

					}
					quoteList.clear();
					quoteList = reportDao.getQuoteAnalysis(fromDate, toDate,
							region, state, city, role, user, engine, "Lost");
					itQuote = quoteList.iterator();
					if (itQuote.hasNext()) {
						q = (Quote) itQuote.next();

						qty = q.getDTL_QTY();
						amt = new BigDecimal(q.getDTL_AMOUNT()).longValue()
								+ "";

						qp.setOrderLostQty(qty);
						qp.setOrderLostAmount(amt);

					} else {
						qp.setOrderLostQty(qty);
						qp.setOrderLostAmount(amt);

					}
					quoteList.clear();
					quoteReportList.add(qp);

				}
			} else {
				qp = new QuoteRpt();
				qp.setEngine(engine);

				quoteList = reportDao.getQuoteAnalysis(fromDate, toDate,
						region, state, city, role, user, engine, "Pending");
				itQuote = quoteList.iterator();
				if (itQuote.hasNext()) {
					q = (Quote) itQuote.next();

					qty = q.getDTL_QTY();
					amt = new BigDecimal(q.getDTL_AMOUNT()).longValue() + "";

					qp.setOrderPendingQty(qty);
					qp.setOrderPendingAmount(amt);

				} else {
					qp.setOrderPendingQty(qty);
					qp.setOrderPendingAmount(amt);

				}
				quoteList.clear();
				quoteList = reportDao.getQuoteAnalysis(fromDate, toDate,
						region, state, city, role, user, engine, "Won");
				itQuote = quoteList.iterator();
				if (itQuote.hasNext()) {
					q = (Quote) itQuote.next();

					qty = q.getDTL_QTY();
					amt = new BigDecimal(q.getDTL_AMOUNT()).longValue() + "";

					qp.setOrderWinQty(qty);
					qp.setOrderWinAmount(amt);

				} else {
					qp.setOrderWinQty(qty);
					qp.setOrderWinQty(qty);

				}
				quoteList.clear();
				quoteList = reportDao.getQuoteAnalysis(fromDate, toDate,
						region, state, city, role, user, engine, "Lost");
				itQuote = quoteList.iterator();
				if (itQuote.hasNext()) {
					q = (Quote) itQuote.next();

					qty = q.getDTL_QTY();
					amt = new BigDecimal(q.getDTL_AMOUNT()).longValue() + "";

					qp.setOrderLostQty(qty);
					qp.setOrderLostAmount(amt);

				} else {
					qp.setOrderLostQty(qty);
					qp.setOrderLostAmount(amt);

				}
				quoteList.clear();
				quoteReportList.add(qp);
			}

			System.out.println("Report List" + quoteReportList);
			request.setAttribute("reportdetails", quoteReportList);

			Iterator itTotal = quoteReportList.iterator();
			String ordertotWinQty = "0", ordertotLostQty = "0", ordertotPendingQty = "0", ordertotWinAmount = "0", ordertotLostAmount = "0", ordertotPendingAmount = "0";
			while (itTotal.hasNext()) {
				qp = (QuoteRpt) itTotal.next();
				System.out.println(qp);
				if(qp.getOrderWinAmount()==null)
				{
					qp.setOrderWinAmount("0");
				}

				if (qp.getOrderWinQty().equalsIgnoreCase("0")
						|| qp.getOrderWinQty().equalsIgnoreCase("")) {
					ordertotWinQty = (new BigDecimal(ordertotWinQty)
							.longValue()) + "";
				} else {
					ordertotWinQty = (new BigDecimal(ordertotWinQty)
							.longValue() + new BigDecimal(qp.getOrderWinQty())
							.longValue())
							+ "";
				}

				if (qp.getOrderPendingQty().equalsIgnoreCase("0")
						|| qp.getOrderPendingQty().equalsIgnoreCase("")) {
					ordertotPendingQty = (new BigDecimal(ordertotPendingQty)
							.longValue()) + "";
				} else {
					ordertotPendingQty = (new BigDecimal(ordertotPendingQty)
							.longValue() + new BigDecimal(
							qp.getOrderPendingQty()).longValue())
							+ "";

				}
				if (qp.getOrderLostQty().equalsIgnoreCase("0")
						|| qp.getOrderLostQty().equalsIgnoreCase("")) {
					ordertotLostQty = (new BigDecimal(ordertotLostQty)
							.longValue()) + "";
				}

				else {
					ordertotLostQty = (new BigDecimal(ordertotLostQty)
							.longValue() + new BigDecimal(qp.getOrderLostQty())
							.longValue())
							+ "";
				}
				if (qp.getOrderWinAmount().equalsIgnoreCase("0")
						|| qp.getOrderWinAmount().equalsIgnoreCase("") || qp.getOrderWinAmount()==null) {
					ordertotWinAmount = (new BigDecimal(ordertotWinAmount)
							.longValue()) + "";
				} else {

					ordertotWinAmount = (new BigDecimal(ordertotWinAmount)
							.longValue() + new BigDecimal(
							qp.getOrderWinAmount()).longValue())
							+ "";
				}
				if (qp.getOrderPendingAmount().equalsIgnoreCase("0")
						|| qp.getOrderPendingAmount().equalsIgnoreCase("")) {
					ordertotPendingAmount = (new BigDecimal(
							ordertotPendingAmount).longValue()) + "";
				} else {
					ordertotPendingAmount = (new BigDecimal(
							ordertotPendingAmount).longValue() + new BigDecimal(
							qp.getOrderPendingAmount()).longValue())
							+ "";
				}

				if (qp.getOrderLostAmount().equalsIgnoreCase("0")
						|| qp.getOrderLostAmount().equalsIgnoreCase("")) {
					ordertotLostAmount = (new BigDecimal(ordertotLostAmount)
							.longValue()) + "";
				} else {
					ordertotLostAmount = (new BigDecimal(ordertotLostAmount)
							.longValue() + new BigDecimal(
							qp.getOrderLostAmount()).longValue())
							+ "";
				}

			}
			QuoteRpt qp1 = new QuoteRpt();
			qp1.setEngine("Total");
			qp1.setOrderLostAmount(ordertotLostAmount);
			qp1.setOrderLostQty(ordertotLostQty);
			qp1.setOrderPendingAmount(ordertotPendingAmount);
			qp1.setOrderPendingQty(ordertotPendingQty);
			qp1.setOrderWinAmount(ordertotWinAmount);
			qp1.setOrderWinQty(ordertotWinQty);
			totquoteReportList.add(qp1);
			request.setAttribute("totreportdetails", totquoteReportList);
			

			forward = QUOTE_REPORT;
		}
		if (reportType.equalsIgnoreCase("EnquiryAnalysis")) {

			Quote q = new Quote();
			EngineStatus enq; 

			List<EngineStatus> engineStatusList=new LinkedList<EngineStatus>();

			String engineType, totEngineQty, totEngineAmt;
			String orderWinQty = "0", orderLostQty = "0", orderPendingQty = "0", orderWinAmount = "0", orderLostAmount = "0", orderPendingAmount = "0";

			String ordertotQty = "0", ordertotAmt = "0";
			String qty = "0", amt = "0";

			
				
				
				if (pType.equalsIgnoreCase("0")) {
					
					
						enquiryBankList=new LinkedList<List>();
						Iterator projectIt = projectType.iterator();
						while(projectIt.hasNext())
						{
								pType=projectIt.next().toString();
									
								
						
						Iterator chanceIt=chanceType.iterator();
						System.out.println("All Chances "+chanceType);
						while(chanceIt.hasNext())
						{
							
							cType=chanceIt.next().toString();
							enquiryBank = new LinkedList();
							enquiryBank.add(pType);
							enquiryBank.add(cType);
							String totQty="0",totAmt="0";
										Iterator engineIt = engineMakeType.iterator();
										while(engineIt.hasNext())
										{
											
											engine=engineIt.next().toString();
											
											
											//enquiryBank.add(engine);
											
																
											
											engineStatusList = reportDao.getEnquiryBank(fromDate, toDate,region, state, city, role, user,pType,cType,engine);
											if(engineStatusList.isEmpty())
											{
											enquiryBank.add("0");
											enquiryBank.add("0");
											}
											else
											{
												Iterator itReport =engineStatusList.iterator();
												while(itReport.hasNext())
												{
													
														enq=(EngineStatus)itReport.next();
													
													
														enquiryBank.add(enq.getQty());
														totQty=(Integer.parseInt(totQty)+ Integer.parseInt(enq.getQty()))+"";

														totAmt=(new BigDecimal(totAmt).longValue()+ new BigDecimal(enq.getAmt()).longValue())+"";
														enquiryBank.add(enq.getAmt());
													
													
												}	
											}
											
											
												
										}
										enquiryBank.add(totQty);
										enquiryBank.add(totAmt);
										
										enquiryBankList.add(enquiryBank);
								}
							
							}
				}
					
					
					else
					{
						enquiryBankList=new LinkedList<List>();
							pType=pType;
							//request.setAttribute("projectType", pType);
							//enquiryBank.add(pType);	
							
							Iterator chanceIt=chanceType.iterator();
							System.out.println("All Chances "+chanceType);
							while(chanceIt.hasNext())
							{
								
								cType=chanceIt.next().toString();
								enquiryBank = new LinkedList();
								enquiryBank.add(pType);
								enquiryBank.add(cType);
								String totQty="0",totAmt="0";
											Iterator engineIt = engineMakeType.iterator();
											while(engineIt.hasNext())
											{
												
												engine=engineIt.next().toString();
												
												
												//enquiryBank.add(engine);
												
																	
												
												engineStatusList = reportDao.getEnquiryBank(fromDate, toDate,region, state, city, role, user,pType,cType,engine);
												if(engineStatusList.isEmpty())
												{
												enquiryBank.add("0");
												enquiryBank.add("0");
												}
												else
												{
													Iterator itReport =engineStatusList.iterator();
													while(itReport.hasNext())
													{
														
															enq=(EngineStatus)itReport.next();
														
														
															enquiryBank.add(enq.getQty());
															totQty=(Integer.parseInt(totQty)+ Integer.parseInt(enq.getQty()))+"";

															totAmt=(new BigDecimal(totAmt).longValue()+ new BigDecimal(enq.getAmt()).longValue())+"";
															enquiryBank.add(enq.getAmt());
														
														
													}	
												}
												
												
													
											}
											enquiryBank.add(totQty);
											enquiryBank.add(totAmt);
											
											enquiryBankList.add(enquiryBank);
									}
					}
					

			System.out.println("Report List" + enquiryBankList);
			int totalEngine=engineMakeType.size();
			request.setAttribute("totalEngine", totalEngine);
			request.setAttribute("engineList", engineMakeType);
			
			request.setAttribute("reportdetails", enquiryBankList);

			
			request.setAttribute("projectType", projectType);

			forward = 	ENQUIRY_REPORT;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);

		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
