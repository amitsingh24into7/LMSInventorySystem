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
import com.lms.dao.LeadItemDetailsDao;
import com.lms.daoImpl.LeadItemDetailsDaoImpl;
import com.lms.model.LeadItemDetails;

/**
 * Servlet implementation class DeleteLeadItems
 */
public class DeleteLeadItems extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LeadItemDetailsDao leaditemsDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteLeadItems() {
		super();
		leaditemsDao = new LeadItemDetailsDaoImpl();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<LeadItemDetails> leadItemsDtls = new ArrayList<LeadItemDetails>();
		int id = Integer.parseInt(request.getParameter("id"));
		String leadID = request.getParameter("leadID");

		String status = leaditemsDao.deleteLeadItem(id);
		if (status.equalsIgnoreCase("Done")) {

			leadItemsDtls = leaditemsDao.getLeadItemsByLeadID(leadID);
		}
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(leadItemsDtls,
				new TypeToken<List<LeadItemDetails>>() {
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
