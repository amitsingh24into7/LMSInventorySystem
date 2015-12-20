package com.lms.util;

import java.io.IOException;
import java.sql.Connection;
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
import com.lms.dao.ConfigMasterDao;
import com.lms.dao.ConsultantDao;
import com.lms.dao.CustomerDao;
import com.lms.dao.PMCDao;
import com.lms.daoImpl.ConfigMasterDaoImpl;
import com.lms.daoImpl.ConsultantDaoImpl;
import com.lms.daoImpl.CustomerDaoImpl;
import com.lms.daoImpl.PMCDaoImpl;
import com.lms.model.ConfigMaster;
import com.lms.model.ConsultantMaster;
import com.lms.model.Customer;
import com.lms.model.PmcMaster;

/**
 * Servlet implementation class GetCustomerDetails
 */
public class GetCustomerDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private Connection connection;
	private CustomerDao customerDao;
	private ConsultantDao consultantDao;
	private PMCDao pmcDao;
	private ConfigMasterDao configMasterDao;

	public GetCustomerDetails() {
		super();
		connection = DbUtil.getDBConnection();
		customerDao = new CustomerDaoImpl();
		pmcDao = new PMCDaoImpl();
		consultantDao = new ConsultantDaoImpl();
		configMasterDao=new ConfigMasterDaoImpl();
		System.out.println("Hello" + connection);

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Customer> cmtr = new ArrayList<Customer>();
		List<PmcMaster> pmcList = new ArrayList<PmcMaster>();
		List<ConsultantMaster> conList = new ArrayList<ConsultantMaster>();
		List<ConfigMaster> configList = new ArrayList<ConfigMaster>();
		
		Gson gson = new Gson();
		JsonElement element = null;
		String NAME = "";
		String type = request.getParameter("type");

		if (type.equalsIgnoreCase("customer")) {
			NAME = request.getParameter("NAME");
			cmtr = customerDao.getCustomerDetailsByCustomerName(NAME);
			element = gson.toJsonTree(cmtr, new TypeToken<List<Customer>>() {
			}.getType());
		}
		if (type.equalsIgnoreCase("consultant")) {
			NAME = request.getParameter("NAME");
			conList = consultantDao.getConsultantDetailsByName(NAME);
			element = gson.toJsonTree(conList,
					new TypeToken<List<ConsultantMaster>>() {
					}.getType());
		}
		if (type.equalsIgnoreCase("pmc")) {
			NAME = request.getParameter("NAME");
			pmcList = pmcDao.getPMCDetailsByName(NAME);
			element = gson.toJsonTree(pmcList,
					new TypeToken<List<PmcMaster>>() {
					}.getType());
		}
		if (type.equalsIgnoreCase("engine")) {
			NAME = request.getParameter("NAME");
			configList = configMasterDao.getEngineDefaultValue(NAME);
			element = gson.toJsonTree(configList,
					new TypeToken<List<ConfigMaster>>() {
					}.getType());
		}

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
