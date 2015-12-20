package com.lms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

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
 * Servlet implementation class LeadItemDetailsController
 */
public class LeadItemDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private LeadItemDetailsDao leaditemsDao;
	
	private static String CREATE_LEAD = "/createlead.jsp";
	private static String LIST_LEAD = "/viewlead.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeadItemDetailsController() {
        super();
        leaditemsDao=new LeadItemDetailsDaoImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		LeadItemDetails ldItems=new LeadItemDetails();
		String rating,laterName,itemMake = null,itemModel = null,flex1="";
		
		String leadID=request.getParameter("leadID");
		String createdBy=request.getParameter("createdBy");
		
		String itemType=request.getParameter("engineType");
		String engineMakeType=request.getParameter("engineMakeType");

		String alternator=request.getParameter("alternator");
		
		String itemQty=request.getParameter("itemQty");
		String itemAmount=request.getParameter("itemAmount");
		String itemHz=request.getParameter("itemHz");
		String itemVoltage=request.getParameter("itemVoltage");
		String coolingSystem=request.getParameter("coolingSystem");
		
		
		String DGModel=request.getParameter("DGModel");
		String EngineModel=request.getParameter("EngineModel");
		String alternatorMakeType=request.getParameter("alternatorMakeType");
		
		
		String itemAction=request.getParameter("itemAction");
		
		
		StringTokenizer st = new StringTokenizer(engineMakeType,"--");
		System.out.println(st.toString());
		
		HashMap<Integer,String> hm=new HashMap<Integer,String>();  
		
		int j=0;
	     while (st.hasMoreTokens()) {  
	    	
	    	hm.put(j++, st.nextToken());
	    	
	     }
	     
	     itemMake=hm.get(0);
	     itemModel=hm.get(1);
	     if(hm.size()==3)
	     {
	    	 flex1=hm.get(2);
	     }
	     
		ldItems.setLEAD_ID(leadID);
		ldItems.setDTL_ITEM_TYPE(itemType);
		
		ldItems.setDTL_ENGINE_MAKE(itemMake);
		ldItems.setDTL_ENGINE_MODEL(EngineModel);
		
		
		ldItems.setDTL_RATING(itemModel);
		
		ldItems.setDTL_ALTERNATOR_MODEL(alternator);
		ldItems.setDTL_ALTERNATOR_MAKE(alternatorMakeType);
		ldItems.setDTL_FLEX2(DGModel);
		
		ldItems.setDTL_QTY(itemQty);
		ldItems.setDTL_AMOUNT(itemAmount);
		ldItems.setDTL_HZ(itemHz);
		ldItems.setDTL_VOLTAGE(itemVoltage);
		ldItems.setDTL_FLEX1(flex1);
		ldItems.setDTL_COOLING_SYSTEM(coolingSystem);
		
		System.out.println(ldItems);
		
		if(itemAction.equalsIgnoreCase("add"))
		{
			leaditemsDao.addLeadItems(ldItems);
		}
		else
		{
			String dtlID=request.getParameter("dtlID");
			ldItems.setDTL_ID(Integer.parseInt(dtlID));
			leaditemsDao.updateLeadItems(ldItems);
			
		}
		
		
			List<LeadItemDetails> leadItemsDtls=new ArrayList<LeadItemDetails>();
		  
			leadItemsDtls=leaditemsDao.getLeadItemsByLeadID(leadID);
			
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(leadItemsDtls, new TypeToken<List<LeadItemDetails>>() {}.getType());

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
