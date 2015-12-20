package com.lms.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.lms.dao.LeadScheduleDao;
import com.lms.daoImpl.LeadScheduleDaoImpl;
import com.lms.model.LeadSchedule;

/**
 * Servlet implementation class GetScheduleDetails
 */
public class GetScheduleDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private LeadScheduleDao leadScheduleDao;
	//String cmnts="";
	String leadID="";
	int dtl_id;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetScheduleDetails() {
        super();
        leadScheduleDao=new LeadScheduleDaoImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<LeadSchedule> leadSchedules = new ArrayList<LeadSchedule>();
		leadID=request.getParameter("leadID");
		dtl_id=Integer.parseInt(request.getParameter("dtl_id"));
		String val=request.getParameter("val");
		if(val.equalsIgnoreCase("all"))
		{
		leadSchedules = leadScheduleDao.getScheudleByItem(leadID, dtl_id);
		System.out.println("hey"+leadSchedules);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(leadSchedules,
				new TypeToken<List<LeadSchedule>>() {
				}.getType());

		JsonArray jsonArray = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
		}
		else
		{
			String qty=leadScheduleDao.getQty(leadID, dtl_id);
			response.setContentType("text/html");
			response.getWriter().print(qty);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
