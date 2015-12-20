package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.ReportDao;
import com.lms.model.EngineStatus;
import com.lms.model.OrderBoard;
import com.lms.model.Project;
import com.lms.model.Quote;
import com.lms.model.ROrderLost;
import com.lms.util.DbUtil;

public class ReportDaoImpl  implements ReportDao{

	
	
	private Connection connection;

	public ReportDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello" + connection);
	}
	public List<Project> getProjectReport(String year) {
		List<Project> projects = new ArrayList<Project>();
		try {
			Statement statement = connection.createStatement();
			String sql="select c.* from "
							+ "(select a.LEAD_ID, a.TRANSACTION_TYPE, a.PROJECT_TYPE, a.ENQUIRY_TYPE, "
							+ "a.LEAD_BUDG, a.PROJECT_EXE_PLACE, a.PROJECT_EXE_STATE, a.LEAD_CREATION_PLACE,"
							+ " a.LEAD_CREATION_STATE, a.LEAD_CRETION_SHORT_STATE, a.LEAD_CREATION_REGION,"
							+ " cast(a.CREATION_DATE as DATE) as CREATION_DATE, a.CREATED_BY, a.MODIFIED_DATE, a.MODIFIED_BY, a.OWNER_ID,"
							+ " a.OWNER_NAME, a.PROJECT_REFERENCE, a.DG_TYPE, a.SEGMENT, a.COMPETITIONS, "
							+ "a.LEAD_CHANCES, a.LEAD_STATUS, a.CONSULTANT_NAME, a.CONSULTANT_PERSON_NAME,"
							+ " a.CONSULTANT_MOBILE_NO, a.CUSTOMER_NAME, a.CUSTOMER_EMAIL_ADDRESS, "
							+ "a.CUSTOMER_ADDRESS, a.CUSTOMER_MOBILENO, a.CUSTOMER_ALTERNATE_NO,"
							+ " a.END_PERSON_CUSTOMER_NAME, a.END_PERSON_CUSTOMER_CONTACTNO,"
							+ " a.PMC_NAME, a.PMC_CONTACT_PERSON, a.PMC_CONTACT_NO, a.PMC_CONTACT_ALT_NO,"
							+ " a.LOW_SIDE_SUPPLY_BASIC, a.LOW_SIDE_LABOUR_BASIC, a.LOW_SIDE_MARGIN, a.PROJECT_MARGIN,"
							+ " a.DG_ED_VALUE, a.PROJECT_TOTAL,"
							+ " b.DTL_RATING, b.DTL_ITEM_TYPE, b.DTL_ENGINE_MAKE,"
							+ " b.DTL_ENGINE_MODEL, b.DTL_ALTERNATOR_MAKE, b.DTL_ALTERNATOR_MODEL,"
							+ " b.DTL_VOLTAGE, b.DTL_HZ, b.DTL_QTY, b.DTL_COOLING_SYSTEM, b.DTL_AMOUNT,"
							+ " b.DTL_FLEX1 from  lead_header a,lead_details b"
							+ " where a.lead_id=b.lead_id and b.DTL_ITEM_TYPE='ENGINE' ) as c";
			System.out.println(sql);
							ResultSet rs = statement
									.executeQuery(sql);
			
			while (rs.next()) {
				Project project = new Project();
				project.setLEAD_ID(rs.getString("lEAD_ID"));
				project.setTRANSACTION_TYPE(rs.getString("tRANSACTION_TYPE"));
				project.setPROJECT_TYPE(rs.getString("pROJECT_TYPE"));
				project.setENQUIRY_TYPE(rs.getString("eNQUIRY_TYPE"));
				project.setLEAD_BUDG(rs.getString("lEAD_BUDG"));
				project.setPROJECT_EXE_PLACE(rs.getString("pROJECT_EXE_PLACE"));
				project.setPROJECT_EXE_STATE(rs.getString("pROJECT_EXE_STATE"));
				project.setLEAD_CREATION_PLACE(rs.getString("lEAD_CREATION_PLACE"));
				project.setLEAD_CREATION_STATE(rs.getString("lEAD_CREATION_STATE"));
				project.setLEAD_CRETION_SHORT_STATE(rs.getString("lEAD_CRETION_SHORT_STATE"));
				project.setLEAD_CREATION_REGION(rs.getString("lEAD_CREATION_REGION"));
				project.setCREATION_DATE(rs.getString("cREATION_DATE"));
				project.setCREATED_BY(rs.getString("cREATED_BY"));
				project.setMODIFIED_DATE(rs.getString("mODIFIED_DATE"));
				project.setMODIFIED_BY(rs.getString("mODIFIED_BY"));
				project.setOWNER_ID(rs.getString("oWNER_ID"));
				project.setOWNER_NAME(rs.getString("oWNER_NAME"));
				project.setPROJECT_REFERENCE(rs.getString("pROJECT_REFERENCE"));
				project.setDG_TYPE(rs.getString("dG_TYPE"));
				project.setSEGMENT(rs.getString("sEGMENT"));
				project.setCOMPETITIONS(rs.getString("cOMPETITIONS"));
				project.setLEAD_CHANCES(rs.getString("lEAD_CHANCES"));
				project.setLEAD_STATUS(rs.getString("lEAD_STATUS"));
				project.setCONSULTANT_NAME(rs.getString("cONSULTANT_NAME"));
				project.setCONSULTANT_PERSON_NAME(rs.getString("cONSULTANT_PERSON_NAME"));
				project.setCONSULTANT_MOBILE_NO(rs.getString("cONSULTANT_MOBILE_NO"));
				project.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
				project.setCUSTOMER_EMAIL_ADDRESS(rs.getString("cUSTOMER_EMAIL_ADDRESS"));
				project.setCUSTOMER_ADDRESS(rs.getString("cUSTOMER_ADDRESS"));
				project.setCUSTOMER_MOBILENO(rs.getString("cUSTOMER_MOBILENO"));
				project.setCUSTOMER_ALTERNATE_NO(rs.getString("cUSTOMER_ALTERNATE_NO"));
				project.setEND_PERSON_CUSTOMER_NAME(rs.getString("eND_PERSON_CUSTOMER_NAME"));
				project.setEND_PERSON_CUSTOMER_CONTACTNO(rs.getString("eND_PERSON_CUSTOMER_CONTACTNO"));
				project.setPMC_NAME(rs.getString("pMC_NAME"));
				project.setPMC_CONTACT_PERSON(rs.getString("pMC_CONTACT_PERSON"));
				project.setPMC_CONTACT_NO(rs.getString("pMC_CONTACT_NO"));
				project.setPMC_CONTACT_ALT_NO(rs.getString("pMC_CONTACT_ALT_NO"));
				project.setLOW_SIDE_SUPPLY_BASIC(rs.getString("lOW_SIDE_SUPPLY_BASIC"));
				project.setLOW_SIDE_LABOUR_BASIC(rs.getString("lOW_SIDE_LABOUR_BASIC"));
				project.setLOW_SIDE_MARGIN(rs.getString("lOW_SIDE_MARGIN"));
				project.setPROJECT_MARGIN(rs.getString("pROJECT_MARGIN"));
				project.setDG_ED_VALUE(rs.getString("dG_ED_VALUE"));
				project.setPROJECT_TOTAL(rs.getString("pROJECT_TOTAL"));
				project.setDTL_RATING(rs.getString("dTL_RATING"));
				project.setDTL_ITEM_TYPE(rs.getString("dTL_ITEM_TYPE"));
				project.setDTL_ENGINE_MAKE(rs.getString("dTL_ENGINE_MAKE"));
				project.setDTL_ENGINE_MODEL(rs.getString("dTL_ENGINE_MODEL"));
				project.setDTL_ALTERNATOR_MAKE(rs.getString("dTL_ALTERNATOR_MAKE"));
				project.setDTL_ALTERNATOR_MODEL(rs.getString("dTL_ALTERNATOR_MODEL"));
				project.setDTL_VOLTAGE(rs.getString("dTL_VOLTAGE"));
				project.setDTL_HZ(rs.getString("dTL_HZ"));
				project.setDTL_QTY(rs.getString("dTL_QTY"));
				project.setDTL_COOLING_SYSTEM(rs.getString("dTL_COOLING_SYSTEM"));
				project.setDTL_AMOUNT(rs.getString("dTL_AMOUNT"));
				project.setDTL_FLEX1(rs.getString("dTL_FLEX1"));
				
				projects.add(project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return projects;
	}
	public List<OrderBoard> getOrderBoardDetails(String fromDate,
			String toDate, String region, String state, String city,
			String roleId, String createdBy)
			{
				List<OrderBoard> orderBoards = new ArrayList<OrderBoard>();
				try {
					Statement statement = connection.createStatement();
					String sql=" select distinct lead_header.OWNER_ID,lead_header.OWNER_NAME, lead_header.LEAD_CREATION_REGION,lead_header.PROJECT_EXE_STATE,lead_header.PROJECT_EXE_PLACE ,lead_header.CREATED_BY,lead_header.CREATION_DATE,lead_header.PROJECT_REFERENCE,lead_header.LEAD_ID,lead_header.DG_ED_VALUE,lead_header.LOW_SIDE_SUPPLY_BASIC,lead_header.LOW_SIDE_LABOUR_BASIC,lead_header.LOW_SIDE_MARGIN,lead_details.DTL_RATING,lead_details.DTL_ENGINE_MAKE,lead_details.DTL_QTY  from  lead_header,lead_details where lead_header.LEAD_ID=lead_details.LEAD_ID ";
					sql+=" and lead_header.LEAD_STATUS='Won'";
					sql+=" and  cast(lead_header.creation_date as date) between '"
					+ fromDate + "' and '" + toDate + "'";

					if (!region.equalsIgnoreCase("0")) {
						sql += " and lead_header.LEAD_CREATION_REGION='" + region + "'";
					}
					if (!state.equalsIgnoreCase("0")) {
						sql += " and lead_header.PROJECT_EXE_STATE='" + state + "'";
					}
					if (!city.equalsIgnoreCase("0")) {
						sql += " and lead_header.PROJECT_EXE_PLACE='" + city + "'";
					}
					if (!createdBy.equalsIgnoreCase("0")) {
						sql += " and lead_header.CREATED_BY='" + createdBy + "'";
					}
					
					System.out.println(sql);
									ResultSet rs = statement
											.executeQuery(sql);
					
					while (rs.next()) {
						OrderBoard orderBoard = new OrderBoard();
						orderBoard.setCREATED_BY(rs.getString("CREATED_BY"));
						orderBoard.setCREATION_DATE(rs.getString("CREATION_DATE"));
						orderBoard.setDG_ED_VALUE(rs.getString("DG_ED_VALUE"));
						orderBoard.setDTL_ENGINE_MAKE(rs.getString("DTL_ENGINE_MAKE"));
						orderBoard.setDTL_QTY(rs.getString("DTL_QTY"));
						orderBoard.setDTL_RATING(rs.getString("DTL_RATING"));
						orderBoard.setLEAD_CREATION_REGION(rs.getString("LEAD_CREATION_REGION"));
						orderBoard.setLEAD_ID(rs.getString("LEAD_ID"));
						orderBoard.setLOW_SIDE_LABOUR_BASIC(rs.getString("LOW_SIDE_LABOUR_BASIC"));
						orderBoard.setLOW_SIDE_MARGIN(rs.getString("LOW_SIDE_MARGIN"));
						orderBoard.setLOW_SIDE_SUPPLY_BASIC(rs.getString("LOW_SIDE_SUPPLY_BASIC"));
						orderBoard.setOWNER_ID(rs.getString("OWNER_ID"));
						orderBoard.setOWNER_NAME(rs.getString("OWNER_NAME"));
						orderBoard.setPROJECT_EXE_PLACE(rs.getString("PROJECT_EXE_PLACE"));
						orderBoard.setPROJECT_EXE_STATE(rs.getString("PROJECT_EXE_STATE"));
						orderBoard.setPROJECT_REFERENCE(rs.getString("PROJECT_REFERENCE"));
						
						orderBoards.add(orderBoard);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return orderBoards;
			}
	public List<Quote> getQuoteAnalysis(String fromDate, String toDate,
			String region, String state, String city, String roleId,
			String createdBy, String engine,String status) {
		List<Quote> quoteList= new ArrayList<Quote>();
		try {
			Statement statement = connection.createStatement();
			String sql=" select d.* from (select lead_header.OWNER_ID,lead_header.OWNER_NAME, lead_header.LEAD_CREATION_REGION,lead_header.PROJECT_EXE_STATE,lead_header.PROJECT_EXE_PLACE ,lead_header.CREATED_BY,lead_header.CREATION_DATE,lead_header.LEAD_ID,lead_header.LEAD_STATUS,lead_details.DTL_ENGINE_MAKE,lead_details.DTL_QTY,lead_details.DTL_AMOUNT  from lead_header,lead_details where lead_header.LEAD_ID=lead_details.LEAD_ID group by lead_header.LEAD_STATUS,lead_details.DTL_ENGINE_MAKE order by lead_details.DTL_ENGINE_MAKE) as d ";
			sql+=" where d.LEAD_STATUS not in ('Draft')";
			
			sql+=" and  cast(d.creation_date as date) between '"
			+ fromDate + "' and '" + toDate + "'";

			
			if (!region.equalsIgnoreCase("0")) {
				sql += " and d.LEAD_CREATION_REGION='" + region + "'";
			}
			if (!state.equalsIgnoreCase("0")) {
				sql += " and d.PROJECT_EXE_STATE='" + state + "'";
			}
			if (!city.equalsIgnoreCase("0")) {
				sql += " and d.PROJECT_EXE_PLACE='" + city + "'";
			}
			if (!createdBy.equalsIgnoreCase("0")) {
				sql += " and d.CREATED_BY='" + createdBy + "'";
			}
			
			sql += " and d.DTL_ENGINE_MAKE='" + engine + "'";
			
			sql += " and d.LEAD_STATUS='" + status + "'";
			
			System.out.println(sql);
							ResultSet rs = statement
									.executeQuery(sql);
			
			while (rs.next()) {
				Quote quot = new Quote();
				quot.setDTL_ENGINE_MAKE(rs.getString("dTL_ENGINE_MAKE"));
				quot.setLEAD_STATUS(rs.getString("lEAD_STATUS"));
				quot.setDTL_QTY(rs.getString("dTL_QTY"));
				quot.setDTL_AMOUNT(rs.getString("dTL_AMOUNT"));
				
				quoteList.add(quot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return quoteList;
	}
	
	public List<ROrderLost> getLostOrders(String fromDate,
			String toDate, String region, String state, String city,
			String roleId, String createdBy)
			{
		List<ROrderLost> orderLostList= new ArrayList<ROrderLost>();
		try {
			Statement statement = connection.createStatement();
			String sql=" select distinct comments.DETAILS, d.* from (select lead_header.LEAD_CREATION_PLACE,lead_header.LEAD_CREATION_STATE,lead_header.PROJECT_REFERENCE,lead_header.COMPETITIONS,lead_header.OWNER_ID,lead_header.OWNER_NAME,lead_header.LEAD_CREATION_REGION,lead_header.PROJECT_EXE_STATE,lead_header.PROJECT_EXE_PLACE,lead_header.CREATED_BY,lead_header.CREATION_DATE,lead_header.LEAD_ID,lead_header.LEAD_STATUS,lead_details.DTL_ENGINE_MAKE,lead_details.DTL_RATING, sum(lead_details.DTL_QTY) as DTL_QTY,sum(lead_details.DTL_AMOUNT) as DTL_AMOUNT from lead_header,lead_details where  lead_header.LEAD_ID=lead_details.LEAD_ID  ";
			sql+=" and  lead_header.LEAD_STATUS='Lost' ";
			
			sql+=" and  cast(lead_header.creation_date as date) between '"
			+ fromDate + "' and '" + toDate + "'";

			
			if (!region.equalsIgnoreCase("0")) {
				sql += " and lead_header.LEAD_CREATION_REGION='" + region + "'";
			}
			if (!state.equalsIgnoreCase("0")) {
				sql += " and lead_header.LEAD_CREATION_STATE='" + state + "'";
			}
			if (!city.equalsIgnoreCase("0")) {
				sql += " and lead_header.LEAD_CREATION_PLACE='" + city + "'";
			}
			if (!createdBy.equalsIgnoreCase("0")) {
				sql += " and lead_header.CREATED_BY='" + createdBy + "'";
			}
			sql += " group by lead_header.PROJECT_REFERENCE,lead_details.DTL_ENGINE_MAKE,lead_details.DTL_RATING ";
			sql += " ) as d ,comments where d.LEAD_ID=comments.LEAD_ID "; 
			
			
			
			System.out.println(sql);
							ResultSet rs = statement
									.executeQuery(sql);
			
			while (rs.next()) {
				ROrderLost rOrderLost = new ROrderLost();
				rOrderLost.setCOMPETITIONS(rs.getString("COMPETITIONS"));
				rOrderLost.setDTL_ENGINE_MAKE(rs.getString("DTL_ENGINE_MAKE"));
				rOrderLost.setPROJECT_REFERENCE(rs.getString("PROJECT_REFERENCE"));
				
				rOrderLost.setDTL_QTY(rs.getString("DTL_QTY"));
				rOrderLost.setDTL_RATING(rs.getString("DTL_RATING"));
				rOrderLost.setDETAILS(rs.getString("DETAILS"));
				rOrderLost.setDTL_AMOUNT(rs.getString("DTL_AMOUNT"));
				
				
				
				orderLostList.add(rOrderLost);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orderLostList;
			}
	
	public List<EngineStatus> getEnquiryBank(String fromDate,
			String toDate, String region, String state, String city,
			String roleId, String createdBy,String project,String leadChance,String engine)
			{
				List<EngineStatus> engineStatusList= new ArrayList<EngineStatus>();
				try {
					Statement statement = connection.createStatement();
					String sql=" select d.* from (select lead_header.PROJECT_TYPE , lead_header.OWNER_ID,lead_header.OWNER_NAME,lead_header.LEAD_CREATION_REGION,lead_header.PROJECT_EXE_STATE,lead_header.PROJECT_EXE_PLACE,lead_header.CREATED_BY,lead_header.CREATION_DATE,lead_header.LEAD_ID,lead_header.LEAD_CHANCES,lead_details.DTL_ENGINE_MAKE ,sum(COALESCE(lead_details.DTL_QTY,0)) as DTL_QTY,sum(COALESCE(lead_details.DTL_AMOUNT,0)) as DTL_AMOUNT from lead_header,lead_details  where lead_header.LEAD_ID=lead_details.LEAD_ID group by lead_header.PROJECT_TYPE,lead_header.LEAD_CHANCES,lead_details.DTL_ENGINE_MAKE order by PROJECT_TYPE,DTL_ENGINE_MAKE ) as d ";
					
					sql+=" where  d.PROJECT_TYPE='"+project+"' ";
					sql+=" and  d.LEAD_CHANCES='"+leadChance+"' ";
					sql+=" and  d.DTL_ENGINE_MAKE='"+engine+"' ";
					
					sql+=" and  cast(d.creation_date as date) between '"
					+ fromDate + "' and '" + toDate + "'";

					
					if (!region.equalsIgnoreCase("0")) {
						sql += " and d.LEAD_CREATION_REGION='" + region + "'";
					}
					if (!state.equalsIgnoreCase("0")) {
						sql += " and d.PROJECT_EXE_STATE='" + state + "'";
					}
					if (!city.equalsIgnoreCase("0")) {
						sql += " and d.PROJECT_EXE_PLACE='" + city + "'";
					}
					if (!createdBy.equalsIgnoreCase("0")) {
						sql += " and d.CREATED_BY='" + createdBy + "'";
					}
					
					
					
					System.out.println(sql);
									ResultSet rs = statement
											.executeQuery(sql);
					
					
						while (rs.next()) {
							EngineStatus engineStatus = new EngineStatus();
							
							engineStatus.setEngine(engine);
							engineStatus.setAmt(rs.getString("DTL_AMOUNT"));
							engineStatus.setQty(rs.getString("DTL_QTY"));
							engineStatus.setRegion(region);
							engineStatus.setChance(leadChance); 
							
							System.out.println("Engine Status"+engineStatus);
							engineStatusList.add(engineStatus);
						}
					
					
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return engineStatusList;
			}
	
}
