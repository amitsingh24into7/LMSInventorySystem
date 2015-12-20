package com.lms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.LeadScheduleDao;
import com.lms.daoImpl.LeadScheduleDaoImpl;
import com.lms.model.LeadSchedule;
import com.lms.util.Utility;


/**
 * Servlet implementation class ScheduleController
 */
public class ScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LeadScheduleDao leadScheduleDao;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleController() {
        super();
        leadScheduleDao = new LeadScheduleDaoImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LeadSchedule leadSchedule=new LeadSchedule();
		
		String scheduleRemarks=request.getParameter("scheduleRemarks");
		String scheduleQty=request.getParameter("scheduleQty");
		String scheduleDate=request.getParameter("scheduleDate");
		String modalleadID=request.getParameter("modalleadID");
		String itemid=request.getParameter("itemid");
		
		String scheduleAction=request.getParameter("scheduleAction");
		
		


		String createdBy=request.getParameter("createdBy");
		
		leadSchedule.setLEAD_ID(modalleadID);
		leadSchedule.setSCHD_DELIVERY_DATE(scheduleDate);
		leadSchedule.setSCHD_QTY(scheduleQty);
		leadSchedule.setSCHD_REMARKS(scheduleRemarks);
		leadSchedule.setDTL_ID(Integer.parseInt(itemid));
		leadSchedule.setSCHD_CRETAED_DATE(Utility.getCurrentdate());
		leadSchedule.setSCH_CREATED_BY(createdBy);
		
		
		System.out.println(leadSchedule);
		if(scheduleAction.equalsIgnoreCase("add"))
		{
			leadScheduleDao.addLeadSchedule(leadSchedule);
		}
		else
		{
			int schdid=Integer.parseInt(request.getParameter("schdid"));
			leadSchedule.setSCHD_ID(schdid);
			leadScheduleDao.updateLeadSchedule(leadSchedule);
			
		}
		
		response.setContentType("text/html");
		response.getWriter().print("Done");
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
