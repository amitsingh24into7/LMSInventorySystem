package com.lms.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.lms.model.Lead_Pricing;
import com.lms.model.SupplyScope;
import com.lms.util.Utility;

/**
 * Servlet implementation class IndentDetailsController
 */
public class IndentDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndentPaymentDao indentPaymentDao;
	private LeadPricingInformationDao leadPricingDao;
	private LeadItemTechnicalDao leadItemtechnicalDao;
	private SupplyScopeDao supplyScopeDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndentDetailsController() {
        super();
        indentPaymentDao=new IndentPaymentDaoImpl();
        leadItemtechnicalDao=new LeadItemTechnicalDaoImpl();
        supplyScopeDao=new SupplyScopeDaoImpl();
        leadPricingDao=new LeadPricingInformationDaoImpl();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String indentAction=request.getParameter("indentAction");
		String createdDate = Utility.getCurrentdate();
		String createdBy = request.getParameter("createdBy");
		String scheduleAction=request.getParameter("scheduleAction");
		
		if(indentAction.equalsIgnoreCase("TECHDETAIL"))
		{
			//Get All Technical Details
			String output="";
			
			LeadItemTechnicalDetails leadTech=new LeadItemTechnicalDetails();
			String modalleadID=request.getParameter("modalleadID");
			String itemid=request.getParameter("itemid");
			String fuleTank=request.getParameter("fuleTank");
			String engineOptionals=request.getParameter("engineOptionals");
			String alternatorOptionals=request.getParameter("alternatorOptionals");
			String panelOptionals=request.getParameter("panelOptionals");
			String soloparallel=request.getParameter("soloparallel");
			String panelType=request.getParameter("panelType");
			String testingProcedure=request.getParameter("testingProcedure");
			String dgTesting=request.getParameter("dgTesting");
			String testingCharges=request.getParameter("testingCharges");
			
			leadTech.setLEAD_ID(modalleadID);
			leadTech.setBUSINESS_TYPE("DG");
			leadTech.setDTL_ID(Integer.parseInt(itemid));
			leadTech.setFUEL_TANK(fuleTank);
			leadTech.setENGINE_OPTIONAL(engineOptionals);
			leadTech.setALTERNATOR_OPTIONAL(alternatorOptionals);
			leadTech.setPANEL_OPTIONAL(panelOptionals);
			leadTech.setSOLOPARALLEL(soloparallel);
			leadTech.setTYPE_OF_PANEL(panelType);
			leadTech.setTESTING_PROCEDURE(testingProcedure);
			leadTech.setDG_TESTING(dgTesting);
			leadTech.setTESTING_CHARGES_INCLUDED(testingCharges);
			
					if(scheduleAction.equalsIgnoreCase("Add"))
					{
						
						leadTech.setCREATED_BY(createdBy);
						leadTech.setCREATED_DATE(createdDate);
						output=leadItemtechnicalDao.addLeadItemtechicalDetails(leadTech);
						
					}
					
					if(scheduleAction.equalsIgnoreCase("Update"))
					{
						
						leadTech.setMODIFIED_BY(createdBy);
						leadTech.setMODIFIED_DATE(createdDate);

						output=leadItemtechnicalDao.updateLeadItemtechicalDetails(leadTech);
					}
					if(output.equalsIgnoreCase("Done"))
					{
						output="Technical Information Updated Successfully";
					}
					response.setContentType("text/html");
					response.getWriter().print(output);
		
			
		}
		
		if(indentAction.equalsIgnoreCase("PRICEINFO"))
		{
			//Get All Technical Details
			String output="";
			
			
			Lead_Pricing leadPricing=new Lead_Pricing();
			
			String leadID=request.getParameter("leadID");
			String type=request.getParameter("type");
			String name=request.getParameter("name");
		
			String[] exploded=name.split(",");
			System.out.println(exploded);
			
			for(int i=0;i<exploded.length;i++)
			{
				String[] val=exploded[i].split("=");
				leadPricing.setLEAD_ID(leadID);
				leadPricing.setITEMTYPE(type);
				leadPricing.setNAME(val[0]);
				leadPricing.setVALUE(val[1]);
				if(scheduleAction.equalsIgnoreCase("Add"))
				{
					leadPricing.setCREATED_BY(createdBy);
					leadPricing.setCREATED_DATE(createdDate);
					System.out.println(leadPricing);
					output=leadPricingDao.addLeadPricingInfo(leadPricing);
					
				}
				
				if(scheduleAction.equalsIgnoreCase("Update"))
				{
					leadPricing.setMODIFIED_BY(createdBy);
					leadPricing.setMODIFIED_DATE(createdDate);
					System.out.println(leadPricing);
					output=leadPricingDao.updateLeadPricingInfo(leadPricing);
				}
				
				
			}
			if(output.equalsIgnoreCase("Done"))
			{
				output="Pricing Information Updated Successfully";
			}
				
					response.setContentType("text/html");
					response.getWriter().print(output);
		
			
		}
		if(indentAction.equalsIgnoreCase("PAYMENTTERMS"))
		{
			//Get All Technical Details
			String output="";
			
			
			IndentPaymentDetails indentPayment=new IndentPaymentDetails();
			
			String leadID=request.getParameter("leadID");
			String type=request.getParameter("type");
					
			if(type.equalsIgnoreCase("DG"))
			{
				String commercialAdvance=request.getParameter("commercialDGAdvance");
				String commercialcheque=request.getParameter("commercialDGcheque");
				String commercialchequedate=request.getParameter("commercialDGchequedate");
				String commercialBank=request.getParameter("commercialDGBank");
				String commercialBalance=request.getParameter("commercialDGBalance");
				String commercialForms=request.getParameter("commercialDGForms");
				String commercialPaymentTerms1=request.getParameter("commercialDGPaymentTerms1");
				String commercialPaymentTerms2=request.getParameter("commercialDGPaymentTerms2");
				String commercialPaymentTerms3=request.getParameter("commercialDGPaymentTerms3");
				String commercialPG=request.getParameter("commercialDGPG");
				String commercialLiqDamage=request.getParameter("commercialDGLiqDamage");
				

				indentPayment.setLEAD_ID(leadID);
				indentPayment.setITEMTYPE(type);
				indentPayment.setADVANCE(commercialAdvance);
				indentPayment.setCHEQUE_NO(commercialcheque);
				indentPayment.setCHEQUE_DATE(commercialchequedate);
				indentPayment.setBANK_DETAILS(commercialBank);
				indentPayment.setBALANCE(commercialBalance);
				indentPayment.setFORMS_TEXT(commercialForms);
				indentPayment.setPAYMENT_TERMS1(commercialPaymentTerms1);
				indentPayment.setPAYMENT_TERMS2(commercialPaymentTerms2);
				indentPayment.setPAYMENT_TERMS3(commercialPaymentTerms3);
				indentPayment.setPG_TEXT(commercialPG);
				indentPayment.setLIQUIDATE_DAMAGE_TEXT(commercialLiqDamage);
				
				if(scheduleAction.equalsIgnoreCase("Add"))
				{
					indentPayment.setCREATED_BY(createdBy);
					indentPayment.setCREATED_DATE(createdDate);
					System.out.println(indentPayment);
					output=indentPaymentDao.addIndentPaymentDetails(indentPayment) ;
					
				}
				
				if(scheduleAction.equalsIgnoreCase("Update"))
				{
					
					indentPayment.setMODIFIED_BY(createdBy);
					indentPayment.setMODIFIED_DATE(createdDate);
					System.out.println(indentPayment);
					output=indentPaymentDao.updateIndentPaymentDetails(indentPayment);
				}
				
			}
			if(type.equalsIgnoreCase("INST"))
			{
				String commercialAdvance=request.getParameter("commercialINSTAdvance");
				String commercialcheque=request.getParameter("commercialINSTcheque");
				String commercialchequedate=request.getParameter("commercialINSTchequedate");
				String commercialBank=request.getParameter("commercialINSTBank");
				String commercialBalance=request.getParameter("commercialINSTBalance");
				String commercialForms=request.getParameter("commercialINSTForms");
				String commercialPaymentTerms1=request.getParameter("commercialINSTPaymentTerms1");
				String commercialPaymentTerms2=request.getParameter("commercialINSTPaymentTerms2");
				String commercialPaymentTerms3=request.getParameter("commercialINSTPaymentTerms3");
				String commercialPG=request.getParameter("commercialINSTPG");
				String commercialLiqDamage=request.getParameter("commercialINSTLiqDamage");
				
				indentPayment.setLEAD_ID(leadID);
				indentPayment.setITEMTYPE(type);
				indentPayment.setADVANCE(commercialAdvance);
				indentPayment.setCHEQUE_NO(commercialcheque);
				indentPayment.setCHEQUE_DATE(commercialchequedate);
				indentPayment.setBANK_DETAILS(commercialBank);
				indentPayment.setBALANCE(commercialBalance);
				indentPayment.setFORMS_TEXT(commercialForms);
				indentPayment.setPAYMENT_TERMS1(commercialPaymentTerms1);
				indentPayment.setPAYMENT_TERMS2(commercialPaymentTerms2);
				indentPayment.setPAYMENT_TERMS3(commercialPaymentTerms3);
				indentPayment.setPG_TEXT(commercialPG);
				indentPayment.setLIQUIDATE_DAMAGE_TEXT(commercialLiqDamage);
				
				if(scheduleAction.equalsIgnoreCase("Add"))
				{
					indentPayment.setCREATED_BY(createdBy);
					indentPayment.setCREATED_DATE(createdDate);
					output=indentPaymentDao.addIndentPaymentDetails(indentPayment) ;
					
				}
				
				if(scheduleAction.equalsIgnoreCase("Update"))
				{
					
					indentPayment.setMODIFIED_BY(createdBy);
					indentPayment.setMODIFIED_DATE(createdDate);
					output=indentPaymentDao.updateIndentPaymentDetails(indentPayment);
				}
				
			}
			if(output.equalsIgnoreCase("Done"))
			{
				output="Payment Terms Information Updated Successfully";
			}
			response.setContentType("text/html");
			response.getWriter().print(output);
		
			
		}
		
		if(indentAction.equalsIgnoreCase("SUPPLYSCOPE"))
		{
			//Get All Technical Details
			String output="";
			
			SupplyScope  supplyScope=new SupplyScope();
			String leadID=request.getParameter("leadID");
			String factoryScope=request.getParameter("factoryScope");
			String branchScope=request.getParameter("branchScope");

			String factoryName=request.getParameter("factoryName");
			String branchName=request.getParameter("branchName");
			String warranty_details=request.getParameter("warranty_details");
			String operator_required=request.getParameter("operator_required");
			
			supplyScope.setBRANCH_NAME(branchName);
			supplyScope.setBRANCH_SUPPLY_SCOPE(branchScope);
			supplyScope.setLEAD_ID(leadID);
			supplyScope.setFACTORY_NAME(factoryName);
			supplyScope.setFACTORY_SUPPLY_SCOPE(factoryScope);
			supplyScope.setWARRANTY_DETAILS(warranty_details);
			supplyScope.setOPERATOR_REQUIRED(operator_required);
			
			
						
					if(scheduleAction.equalsIgnoreCase("Add"))
					{
						
						supplyScope.setCREATED_BY(createdBy);
						supplyScope.setCREATED_DATE(createdDate);
						output=supplyScopeDao.addSupplyScopeDetails(supplyScope);
						
					}
					
					if(scheduleAction.equalsIgnoreCase("Update"))
					{
						
						supplyScope.setMODIFIED_BY(createdBy);
						supplyScope.setMODIFIED_DATE(createdDate);

						output=supplyScopeDao.updateSupplyScopeDetails(supplyScope);
					}
					if(output.equalsIgnoreCase("Done"))
					{
						output="Supply Scope Information Updated Successfully";
					}
					response.setContentType("text/html");
					response.getWriter().print(output);
		
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
