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
import com.lms.daoImpl.LeadHeaderDaoImpl;
import com.lms.daoImpl.LeadItemDetailsDaoImpl;
import com.lms.model.LeadHeader;

/**
 * Servlet implementation class GetCostDetails
 */
public class GetCostDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LeadHeaderDao leadHeaderDao;
	private LeadItemDetailsDao leadItemDetailsDao;
	
	
	
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCostDetails() {
        super();
        leadHeaderDao=new LeadHeaderDaoImpl();
        leadItemDetailsDao=new LeadItemDetailsDaoImpl();
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String leadID=request.getParameter("LEAD_ID");
		List<String> ls=new ArrayList<String>();
		List<LeadHeader> headerList;
		String leadDetailTotAmt=leadItemDetailsDao.getTotalLeadAmount(leadID);
		ls.add(leadDetailTotAmt);
		LeadHeader lHeader=new LeadHeader();
		headerList=leadHeaderDao.getLeadCostDetails(leadID);
		System.out.println(headerList.size());
	
		/*
		 leadDetails.setLOW_SIDE_SUPPLY_BASIC(rs.getDouble("lOW_SIDE_SUPPLY_BASIC"));

					leadDetails.setLOW_SIDE_LABOUR_BASIC(rs.getDouble("lOW_SIDE_LABOUR_BASIC"));
					
	
					leadDetails.setLOW_SIDE_MARGIN(rs.getDouble("lOW_SIDE_MARGIN"));
					leadDetails.setPROJECT_MARGIN(rs.getDouble("pROJECT_MARGIN"));
	
					leadDetails.setDG_ED_VALUE(rs.getDouble("dG_ED_VALUE"));
	
					
					leadDetails.setPROJECT_TOTAL(rs.getDouble("pROJECT_TOTAL")); 
		 
		 */
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
		
		
		if(new BigDecimal(leadDetailTotAmt).longValue()==(long)lHeader.getPROJECT_TOTAL())
			
		{
			ls.add("Matching");
		}
		else
		{
			ls.add("NotMatching");
		}
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
