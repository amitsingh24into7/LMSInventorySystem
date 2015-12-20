package com.lms.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.lms.dao.LeadHeaderDao;
import com.lms.dao.LeadItemDetailsDao;
import com.lms.dao.LeadPricingInformationDao;
import com.lms.daoImpl.LeadHeaderDaoImpl;
import com.lms.daoImpl.LeadItemDetailsDaoImpl;
import com.lms.daoImpl.LeadPricingInformationDaoImpl;
import com.lms.model.LeadHeader;
import com.lms.model.Lead_Pricing;

/**
 * Servlet implementation class GetIndentCostSummaryDetails
 */
public class GetIndentCostSummaryDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LeadHeaderDao leadHeaderDao;
	private LeadItemDetailsDao leadItemDetailsDao;
	private LeadPricingInformationDao leadPricingDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetIndentCostSummaryDetails() {
        super();
        leadHeaderDao=new LeadHeaderDaoImpl();
        leadItemDetailsDao=new LeadItemDetailsDaoImpl();
        leadPricingDao=new LeadPricingInformationDaoImpl();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String leadID=request.getParameter("LEAD_ID");
		List<String> ls=new ArrayList<String>();
		String leadDetailTotAmt="";
		List<LeadHeader> headerList;
		List<Lead_Pricing> leadPricingList;
		leadPricingList=leadPricingDao.getLeadPricingInfoByName(leadID, "INST","totalprojectvalue");
		Iterator itPricing=leadPricingList.iterator();
		Lead_Pricing leadPri;
		while(itPricing.hasNext())
		{
			
			leadPri=(Lead_Pricing) itPricing.next();
			leadDetailTotAmt=leadPri.getVALUE();
			
		}
		
		
		ls.add(leadDetailTotAmt);
		LeadHeader lHeader=new LeadHeader();
		headerList=leadHeaderDao.getLeadCostDetails(leadID);
		System.out.println(headerList.size());
	
		Iterator it=headerList.iterator();
		while(it.hasNext())
		{
			lHeader=(LeadHeader) it.next();
			
			
		}
		ls.add((long)lHeader.getLOW_SIDE_SUPPLY_BASIC()+"");
		ls.add((long)lHeader.getLOW_SIDE_LABOUR_BASIC()+"");
		ls.add((long)lHeader.getLOW_SIDE_MARGIN()+"");
		ls.add((long)lHeader.getPROJECT_MARGIN()+"");
		ls.add((long)lHeader.getPROJECT_TOTAL()+"");
		ls.add(lHeader.getDG_ED_VALUE());
		System.out.println("Total"+leadDetailTotAmt);
		System.out.println("Project"+lHeader.getPROJECT_SELLING_VALUE());
		
		if(leadDetailTotAmt.equalsIgnoreCase(lHeader.getPROJECT_SELLING_VALUE()))
			
		{
			ls.add("Matching");
		}
		else
		{
			ls.add("NotMatching");
		}
		ls.add(lHeader.getDG_SELLING_VALUE());
		ls.add(lHeader.getLOW_SIDE_SELLING_VALUE());
		ls.add(lHeader.getPROJECT_SELLING_VALUE());
		
		
		
		
		
		System.out.println(ls);
		
		
		
			Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(ls, new TypeToken<List<String>>() {}.getType());

		  JsonArray jsonArray = element.getAsJsonArray();
		  response.setContentType("application/json");
		  response.getWriter().print(jsonArray);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
