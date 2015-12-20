package com.lms.util;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.lms.dao.ConfigMasterDao;
import com.lms.dao.LeadSequenceDao;
import com.lms.daoImpl.ConfigMasterDaoImpl;
import com.lms.daoImpl.LeadSequenceDaoImpl;

/**
 * Servlet implementation class GetCity
 */
public class GetCity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private ConfigMasterDao configMasterDao;
	private LeadSequenceDao leadsequenceDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCity() {
		super();

		connection = DbUtil.getDBConnection();
		configMasterDao = new ConfigMasterDaoImpl();
		leadsequenceDao = new LeadSequenceDaoImpl();
		System.out.println("Hello" + connection);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<String> cmtr = new ArrayList<String>();

		String state = request.getParameter("state");
		String val = request.getParameter("val");
		cmtr = configMasterDao.getCity(state);
		
		if(val.equalsIgnoreCase("create"))
		{
		
			String businessFile = request.getParameter("businessFile");
			//HttpSession session = request.getSession();
	
			
	
			String userShortState = configMasterDao.getShortState(state);
			System.out.println("asd" + userShortState);
			
			
			int year = Calendar.getInstance().get(Calendar.YEAR);
			String leadid="";
			leadid=leadsequenceDao.getLeadNo(year, businessFile, userShortState);
			System.out.println(leadid);
			
			cmtr.add(leadid);
		}

		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(cmtr,
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
