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
import com.lms.dao.IndentPaymentDao;
import com.lms.dao.LeadItemTechnicalDao;
import com.lms.dao.LeadPricingInformationDao;
import com.lms.dao.SupplyScopeDao;
import com.lms.daoImpl.IndentPaymentDaoImpl;
import com.lms.daoImpl.LeadItemTechnicalDaoImpl;
import com.lms.daoImpl.LeadPricingInformationDaoImpl;
import com.lms.daoImpl.SupplyScopeDaoImpl;
import com.lms.model.IndentPaymentDetails;
import com.lms.model.LeadItemTechnicalDetails;
import com.lms.model.LeadSchedule;
import com.lms.model.Lead_Pricing;
import com.lms.model.SupplyScope;

/**
 * Servlet implementation class GetIndentDetails
 */
public class GetIndentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IndentPaymentDao indentPaymentDao;
	private LeadPricingInformationDao leadPricingDao;
	private LeadItemTechnicalDao leadItemtechnicalDao;
	private SupplyScopeDao supplyScopeDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetIndentDetails() {
        super();
        indentPaymentDao=new IndentPaymentDaoImpl();
        leadItemtechnicalDao=new LeadItemTechnicalDaoImpl();
        supplyScopeDao=new SupplyScopeDaoImpl();
        leadPricingDao=new LeadPricingInformationDaoImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indentAction=request.getParameter("indentAction");
		
		
		if(indentAction.equalsIgnoreCase("TECHDETAIL"))
		{
			List<LeadItemTechnicalDetails > leadTechnicalDetails = new ArrayList<LeadItemTechnicalDetails>();
			
			String leadID=request.getParameter("leadID");
			String itemId=request.getParameter("dtl_id");
			
			leadTechnicalDetails=leadItemtechnicalDao.getTechnicalDetails(leadID, itemId);
			Gson gson = new Gson();
			
			JsonElement element = gson.toJsonTree(leadTechnicalDetails,
					new TypeToken<List<LeadItemTechnicalDetails>>() {
					}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}
		if(indentAction.equalsIgnoreCase("DGPAYMENT"))
		{
			List<IndentPaymentDetails> indentPaymentDetails = new ArrayList<IndentPaymentDetails>();
			
			String leadID=request.getParameter("leadID");
			
			
			indentPaymentDetails=indentPaymentDao.getIndentPaymentDetailsByType(leadID, "DG");
			Gson gson = new Gson();
			
			JsonElement element = gson.toJsonTree(indentPaymentDetails,
					new TypeToken<List<IndentPaymentDetails>>() {
					}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}
		if(indentAction.equalsIgnoreCase("INSTPAYMENT"))
		{
			List<IndentPaymentDetails> indentPaymentDetails = new ArrayList<IndentPaymentDetails>();
			
			String leadID=request.getParameter("leadID");
			
			
			indentPaymentDetails=indentPaymentDao.getIndentPaymentDetailsByType(leadID, "INST");
			Gson gson = new Gson();
			
			JsonElement element = gson.toJsonTree(indentPaymentDetails,
					new TypeToken<List<IndentPaymentDetails>>() {
					}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}
		if(indentAction.equalsIgnoreCase("DGPRICING"))
		{
			List<Lead_Pricing > leadPricing = new ArrayList<Lead_Pricing>();
			
			String leadID=request.getParameter("leadID");
			
			
			leadPricing=leadPricingDao.getLeadPricingInfo(leadID, "DG");
			Gson gson = new Gson();
			
			JsonElement element = gson.toJsonTree(leadPricing,
					new TypeToken<List<Lead_Pricing>>() {
					}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}
		if(indentAction.equalsIgnoreCase("INSTPRICING"))
		{
			List<Lead_Pricing > leadPricing = new ArrayList<Lead_Pricing>();
			
			String leadID=request.getParameter("leadID");
			
			
			leadPricing=leadPricingDao.getLeadPricingInfo(leadID, "INST");
			Gson gson = new Gson();
			
			JsonElement element = gson.toJsonTree(leadPricing,
					new TypeToken<List<Lead_Pricing>>() {
					}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}
		
		if(indentAction.equalsIgnoreCase("SUPPLYSCOPE"))
		{
			List<SupplyScope> supplyScopeList = new ArrayList<SupplyScope>();
			
			String leadID=request.getParameter("leadID");
			
			
			supplyScopeList=supplyScopeDao.getSupplyScopeDetails(leadID);
			Gson gson = new Gson();
			
			JsonElement element = gson.toJsonTree(supplyScopeList,
					new TypeToken<List<SupplyScope>>() {
					}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
