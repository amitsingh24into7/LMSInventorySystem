package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.lms.dao.LeadHeaderDao;
import com.lms.model.LeadHeader;
import com.lms.util.DbUtil;

public class LeadHeaderDaoImpl implements LeadHeaderDao {

	private Connection connection;

	public LeadHeaderDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello" + connection);
	}

	public List<LeadHeader> getLeadHeadDataDetails(String status,String from_date,String to_date,String userState) {
		// TODO Auto-generated method stub
		List<LeadHeader> cMasters = new LinkedList<LeadHeader>();
		try {
			Statement statement = connection.createStatement();
			String sql = "select ID, LEAD_ID, TRANSACTION_TYPE, PROJECT_TYPE, ENQUIRY_TYPE, LEAD_BUDG, PROJECT_EXE_PLACE, PROJECT_EXE_STATE, LEAD_CREATION_PLACE, LEAD_CREATION_STATE, LEAD_CRETION_SHORT_STATE, LEAD_CREATION_REGION,"
					+ " cast(LEAD_EXECUTION_DATE as date) as LEAD_EXECUTION_DATE, cast(CREATION_DATE as date) as CREATION_DATE, CREATED_BY,"
					+ " cast(MODIFIED_DATE as date) as MODIFIED_DATE, MODIFIED_BY, OWNER_ID, OWNER_NAME, PROJECT_REFERENCE, DG_TYPE, SEGMENT, COMPETITIONS, LEAD_CHANCES, LEAD_STATUS, CONSULTANT_NAME, CONSULTANT_PERSON_NAME, CONSULTANT_MOBILE_NO, CUSTOMER_NAME, CUSTOMER_EMAIL_ADDRESS, CUSTOMER_ADDRESS, CUSTOMER_MOBILENO, CUSTOMER_ALTERNATE_NO, END_PERSON_CUSTOMER_NAME, END_PERSON_CUSTOMER_CONTACTNO, PMC_NAME, PMC_CONTACT_PERSON, PMC_CONTACT_NO, PMC_CONTACT_ALT_NO, LOW_SIDE_SUPPLY_BASIC, LOW_SIDE_LABOUR_BASIC, LOW_SIDE_MARGIN, PROJECT_MARGIN, DG_ED_VALUE, PROJECT_TOTAL, INDENT_NO,DG_DELIVERY_DATE,PROJECT_CLOUSURE_DATE from lead_header where LEAD_BUDG='0'";

			if(status.equalsIgnoreCase("PENDINGDRAFT"))
			{
				sql+=" and LEAD_STATUS in ('Pending','Draft')";
			}
			
			else if(status.equalsIgnoreCase("Pending"))
			{
				sql+=" and LEAD_STATUS ='"+status+"'";
			}
			else
			{
				sql+=" and LEAD_STATUS ='"+status+"'";
			}
			
					

					sql+=" and LEAD_CRETION_SHORT_STATE='"+userState+"' and cast(CREATION_DATE as date) between '"+from_date+"' and '"+to_date+"'"
							+ " order by cast(CREATION_DATE as date) desc";
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				LeadHeader cMaster = new LeadHeader();

				// cMaster.set.setID(rs.getInt("ID"));
				cMaster.setLEAD_ID(rs.getString("lEAD_ID"));
				cMaster.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
				cMaster.setOWNER_ID(rs.getString("oWNER_ID"));
				cMaster.setPROJECT_EXE_PLACE(rs.getString("pROJECT_EXE_PLACE"));
				cMaster.setCREATION_DATE(rs.getString("cREATION_DATE"));
				cMaster.setLEAD_CHANCES(rs.getString("lEAD_CHANCES"));
				cMaster.setLEAD_STATUS(rs.getString("lEAD_STATUS"));
				cMaster.setCUSTOMER_MOBILENO(rs.getString("cUSTOMER_MOBILENO"));
				cMaster.setCUSTOMER_ADDRESS(rs.getString("cUSTOMER_ADDRESS"));
				cMaster.setCUSTOMER_EMAIL_ADDRESS(rs.getString("cUSTOMER_EMAIL_ADDRESS"));
				cMaster.setINDENT_NO(rs.getString("iNDENT_NO"));
				cMaster.setDG_DELIVERY_DATE(rs.getString("dG_DELIVERY_DATE"));
				cMaster.setPROJECT_CLOUSURE_DATE(rs.getString("pROJECT_CLOUSURE_DATE"));
				
				
				cMaster.setDG_ED_VALUE(rs.getString("DG_ED_VALUE"));
				

				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}
	public List<LeadHeader> getIndentDataDetails(String from_date,String to_date,String userState) {
		// TODO Auto-generated method stub
		List<LeadHeader> cMasters = new LinkedList<LeadHeader>();
		try {
			Statement statement = connection.createStatement();
			String sql = "select ID, LEAD_ID, TRANSACTION_TYPE, PROJECT_TYPE, ENQUIRY_TYPE, LEAD_BUDG, PROJECT_EXE_PLACE, PROJECT_EXE_STATE, LEAD_CREATION_PLACE, LEAD_CREATION_STATE, LEAD_CRETION_SHORT_STATE, LEAD_CREATION_REGION,"
					+ " cast(LEAD_EXECUTION_DATE as date) as LEAD_EXECUTION_DATE, cast(CREATION_DATE as date) as CREATION_DATE, CREATED_BY,"
					+ " cast(MODIFIED_DATE as date) as MODIFIED_DATE, MODIFIED_BY, OWNER_ID, OWNER_NAME, PROJECT_REFERENCE, DG_TYPE, SEGMENT, COMPETITIONS, LEAD_CHANCES, LEAD_STATUS, CONSULTANT_NAME, CONSULTANT_PERSON_NAME, CONSULTANT_MOBILE_NO, CUSTOMER_NAME, CUSTOMER_EMAIL_ADDRESS, CUSTOMER_ADDRESS, CUSTOMER_MOBILENO, CUSTOMER_ALTERNATE_NO, END_PERSON_CUSTOMER_NAME, END_PERSON_CUSTOMER_CONTACTNO, PMC_NAME, PMC_CONTACT_PERSON, PMC_CONTACT_NO, PMC_CONTACT_ALT_NO, LOW_SIDE_SUPPLY_BASIC, LOW_SIDE_LABOUR_BASIC, LOW_SIDE_MARGIN, PROJECT_MARGIN, DG_ED_VALUE, PROJECT_TOTAL, INDENT_NO,DG_DELIVERY_DATE,PROJECT_CLOUSURE_DATE from lead_header where"
					+ "  INDENT_NO is not null and  LEAD_CRETION_SHORT_STATE='"+userState+"' "
					 + " and cast(CREATION_DATE as date) between '"+from_date+"' and '"+to_date+"' order by cast(CREATION_DATE as date) desc";
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				LeadHeader cMaster = new LeadHeader();

				// cMaster.set.setID(rs.getInt("ID"));
				cMaster.setLEAD_ID(rs.getString("lEAD_ID"));
				cMaster.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
				cMaster.setOWNER_ID(rs.getString("oWNER_ID"));
				cMaster.setPROJECT_EXE_PLACE(rs.getString("pROJECT_EXE_PLACE"));
				cMaster.setCREATION_DATE(rs.getString("cREATION_DATE"));
				cMaster.setLEAD_CHANCES(rs.getString("lEAD_CHANCES"));
				cMaster.setCUSTOMER_MOBILENO(rs.getString("cUSTOMER_MOBILENO"));
				cMaster.setCUSTOMER_ADDRESS(rs.getString("cUSTOMER_ADDRESS"));
				cMaster.setCUSTOMER_EMAIL_ADDRESS(rs.getString("cUSTOMER_EMAIL_ADDRESS"));
				cMaster.setINDENT_NO(rs.getString("iNDENT_NO"));
				
				cMaster.setDG_DELIVERY_DATE(rs.getString("dG_DELIVERY_DATE"));
				cMaster.setPROJECT_CLOUSURE_DATE(rs.getString("pROJECT_CLOUSURE_DATE"));
				
				
				cMaster.setDG_ED_VALUE(rs.getString("DG_ED_VALUE"));
				

				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}
	public List<LeadHeader> getLeadHeadData(String leadID) {
		// TODO Auto-generated method stub
		List<LeadHeader> cMasters = new ArrayList<LeadHeader>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select ID, LEAD_ID, TRANSACTION_TYPE, PROJECT_TYPE, ENQUIRY_TYPE,"
							+ " LEAD_BUDG, PROJECT_EXE_PLACE, PROJECT_EXE_STATE, LEAD_CREATION_PLACE,"
							+ " LEAD_CREATION_STATE, LEAD_CRETION_SHORT_STATE,"
							+ " LEAD_CREATION_REGION, LEAD_EXECUTION_DATE, cast(CREATION_DATE as date) as CREATION_DATE,"
							+ " CREATED_BY, cast(MODIFIED_DATE as date) as MODIFIED_DATE, MODIFIED_BY, OWNER_ID, OWNER_NAME, "
							+ "PROJECT_REFERENCE, DG_TYPE, SEGMENT, COMPETITIONS, LEAD_CHANCES, "
							+ "LEAD_STATUS, CONSULTANT_NAME, CONSULTANT_PERSON_NAME, CONSULTANT_MOBILE_NO, "
							+ "CUSTOMER_NAME, CUSTOMER_EMAIL_ADDRESS, CUSTOMER_ADDRESS, CUSTOMER_MOBILENO, "
							+ "CUSTOMER_ALTERNATE_NO, END_PERSON_CUSTOMER_NAME, END_PERSON_CUSTOMER_CONTACTNO, "
							+ "PMC_NAME, PMC_CONTACT_PERSON, PMC_CONTACT_NO, PMC_CONTACT_ALT_NO, LOW_SIDE_SUPPLY_BASIC, "
							+ "LOW_SIDE_LABOUR_BASIC, LOW_SIDE_MARGIN,"
							+ " PROJECT_MARGIN, DG_ED_VALUE, PROJECT_TOTAL, INDENT_NO,DG_DELIVERY_DATE,PROJECT_CLOUSURE_DATE from lead_header where lead_id='"
							+ leadID + "'");
			while (rs.next()) {
				LeadHeader cMaster = new LeadHeader();

				cMaster.setLEAD_ID(rs.getString("lEAD_ID"));

				cMaster.setOWNER_ID(rs.getString("oWNER_ID"));
				cMaster.setLEAD_EXECUTION_DATE(rs.getString("lEAD_EXECUTION_DATE"));
				cMaster.setCREATED_BY(rs.getString("cREATED_BY"));
				cMaster.setCREATION_DATE(rs.getString("cREATION_DATE"));
				cMaster.setOWNER_NAME(rs.getString("oWNER_NAME"));
				cMaster.setPROJECT_EXE_PLACE(rs.getString("pROJECT_EXE_PLACE"));

				cMaster.setLEAD_CHANCES(rs.getString("lEAD_CHANCES"));
				cMaster.setPROJECT_REFERENCE(rs.getString("pROJECT_REFERENCE"));
				cMaster.setCOMPETITIONS(rs.getString("cOMPETITIONS"));

				cMaster.setDG_TYPE(rs.getString("dG_TYPE"));
				cMaster.setENQUIRY_TYPE(rs.getString("eNQUIRY_TYPE"));
				cMaster.setLEAD_BUDG(rs.getString("lEAD_BUDG"));
				cMaster.setLEAD_CHANCES(rs.getString("lEAD_CHANCES"));

				cMaster.setLEAD_STATUS(rs.getString("lEAD_STATUS"));

				cMaster.setPROJECT_TYPE(rs.getString("pROJECT_TYPE"));
				cMaster.setSEGMENT(rs.getString("sEGMENT"));
				cMaster.setTRANSACTION_TYPE(rs.getString("tRANSACTION_TYPE"));
				cMaster.setPROJECT_EXE_PLACE(rs.getString("pROJECT_EXE_PLACE"));
				cMaster.setPROJECT_EXE_STATE(rs.getString("pROJECT_EXE_STATE"));

				// Amount Details
				cMaster.setDG_ED_VALUE(rs.getString("dG_ED_VALUE"));
				cMaster.setLOW_SIDE_LABOUR_BASIC(rs
						.getDouble("lOW_SIDE_LABOUR_BASIC"));
				cMaster.setLOW_SIDE_MARGIN(rs.getDouble("lOW_SIDE_MARGIN"));
				cMaster.setLOW_SIDE_SUPPLY_BASIC(rs
						.getDouble("lOW_SIDE_SUPPLY_BASIC"));
				cMaster.setPROJECT_MARGIN(rs.getDouble("pROJECT_MARGIN"));
				cMaster.setPROJECT_TOTAL(rs.getDouble("pROJECT_TOTAL"));
				cMaster.setCREATION_DATE(rs.getString("cREATION_DATE"));
				cMaster.setINDENT_NO(rs.getString("iNDENT_NO"));
				cMaster.setDG_DELIVERY_DATE(rs.getString("dG_DELIVERY_DATE"));
				cMaster.setPROJECT_CLOUSURE_DATE(rs.getString("pROJECT_CLOUSURE_DATE"));


				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}

	public void addLeaderMainHeader(LeadHeader leadheader) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into lead_header("
							+ "LEAD_ID, TRANSACTION_TYPE, PROJECT_TYPE, "
							+ "LEAD_BUDG, PROJECT_EXE_PLACE, PROJECT_EXE_STATE, "
							+ "CREATION_DATE, CREATED_BY, OWNER_ID,PROJECT_REFERENCE,"
							+ " DG_TYPE, SEGMENT, COMPETITIONS, LEAD_CHANCES,"
							+ " LEAD_STATUS,CONSULTANT_NAME, CONSULTANT_PERSON_NAME,"
							+ " CONSULTANT_MOBILE_NO, CUSTOMER_NAME, CUSTOMER_EMAIL_ADDRESS,"
							+ " CUSTOMER_ADDRESS, CUSTOMER_MOBILENO, CUSTOMER_ALTERNATE_NO,"
							+ " END_PERSON_CUSTOMER_NAME, END_PERSON_CUSTOMER_CONTACTNO,"
							+ " PMC_NAME, PMC_CONTACT_PERSON, PMC_CONTACT_NO,"
							+ " PMC_CONTACT_ALT_NO,OWNER_NAME,ENQUIRY_TYPE,LEAD_EXECUTION_DATE,DG_DELIVERY_DATE,PROJECT_CLOUSURE_DATE)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,? ,?,?,?,?)");

			preparedStatement.setString(1, leadheader.getLEAD_ID());
			preparedStatement.setString(2, leadheader.getTRANSACTION_TYPE());
			preparedStatement.setString(3, leadheader.getPROJECT_TYPE());
			preparedStatement.setString(4, leadheader.getLEAD_BUDG());
			preparedStatement.setString(5, leadheader.getPROJECT_EXE_PLACE());
			preparedStatement.setString(6, leadheader.getPROJECT_EXE_STATE());
			preparedStatement.setString(7, leadheader.getCREATION_DATE());
			preparedStatement.setString(8, leadheader.getCREATED_BY());
			preparedStatement.setString(9, leadheader.getOWNER_ID());
			preparedStatement.setString(10, leadheader.getPROJECT_REFERENCE());
			preparedStatement.setString(11, leadheader.getDG_TYPE());
			preparedStatement.setString(12, leadheader.getSEGMENT());
			preparedStatement.setString(13, leadheader.getCOMPETITIONS());
			preparedStatement.setString(14, leadheader.getLEAD_CHANCES());
			preparedStatement.setString(15, leadheader.getLEAD_STATUS());
			preparedStatement.setString(16, leadheader.getCONSULTANT_NAME());
			preparedStatement.setString(17,
					leadheader.getCONSULTANT_PERSON_NAME());
			preparedStatement.setString(18,
					leadheader.getCONSULTANT_MOBILE_NO());
			preparedStatement.setString(19, leadheader.getCUSTOMER_NAME());
			preparedStatement.setString(20,
					leadheader.getCUSTOMER_EMAIL_ADDRESS());
			preparedStatement.setString(21, leadheader.getCUSTOMER_ADDRESS());
			preparedStatement.setString(22, leadheader.getCUSTOMER_MOBILENO());
			preparedStatement.setString(23,
					leadheader.getCUSTOMER_ALTERNATE_NO());
			preparedStatement.setString(24,
					leadheader.getEND_PERSON_CUSTOMER_NAME());
			preparedStatement.setString(25,
					leadheader.getEND_PERSON_CUSTOMER_CONTACTNO());
			preparedStatement.setString(26, leadheader.getPMC_NAME());
			preparedStatement.setString(27, leadheader.getPMC_CONTACT_PERSON());
			preparedStatement.setString(28, leadheader.getPMC_CONTACT_NO());
			preparedStatement.setString(29, leadheader.getPMC_CONTACT_ALT_NO());
			preparedStatement.setString(30, leadheader.getOWNER_NAME());
			preparedStatement.setString(31, leadheader.getENQUIRY_TYPE());
			preparedStatement.setString(32, leadheader.getLEAD_EXECUTION_DATE());
			preparedStatement.setString(33, leadheader.getDG_DELIVERY_DATE());
			preparedStatement.setString(34, leadheader.getPROJECT_CLOUSURE_DATE());
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addLeadHeader(LeadHeader leadheader) {
		
		List<LeadHeader> ls= getLeadHeadData(leadheader.getLEAD_ID());
		if(ls.size()>0)
		{
			leadheader.setMODIFIED_BY(leadheader.getCREATED_BY());
			leadheader.setMODIFIED_DATE(leadheader.getCREATION_DATE());
			updateLeadHeader(leadheader);
		}
		else
		{
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into lead_header("
							+ "LEAD_ID, TRANSACTION_TYPE, PROJECT_TYPE, "
							+ "LEAD_BUDG, PROJECT_EXE_PLACE, PROJECT_EXE_STATE, "
							+ "CREATION_DATE, CREATED_BY, OWNER_ID,PROJECT_REFERENCE,"
							+ " DG_TYPE, SEGMENT, COMPETITIONS, LEAD_CHANCES,"
							+ " LEAD_STATUS,CONSULTANT_NAME, CONSULTANT_PERSON_NAME,"
							+ " CONSULTANT_MOBILE_NO, CUSTOMER_NAME, CUSTOMER_EMAIL_ADDRESS,"
							+ " CUSTOMER_ADDRESS, CUSTOMER_MOBILENO, CUSTOMER_ALTERNATE_NO,"
							+ " END_PERSON_CUSTOMER_NAME, END_PERSON_CUSTOMER_CONTACTNO,"
							+ " PMC_NAME, PMC_CONTACT_PERSON, PMC_CONTACT_NO,"
							+ " PMC_CONTACT_ALT_NO,OWNER_NAME,ENQUIRY_TYPE,LEAD_CREATION_PLACE,LEAD_CREATION_STATE,LEAD_CRETION_SHORT_STATE,LEAD_CREATION_REGION,LEAD_EXECUTION_DATE,DG_DELIVERY_DATE,PROJECT_CLOUSURE_DATE)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?,? ,?,?,?,?,?,?,?)");

			preparedStatement.setString(1, leadheader.getLEAD_ID());
			preparedStatement.setString(2, leadheader.getTRANSACTION_TYPE());
			preparedStatement.setString(3, leadheader.getPROJECT_TYPE());
			preparedStatement.setString(4, leadheader.getLEAD_BUDG());
			preparedStatement.setString(5, leadheader.getPROJECT_EXE_PLACE());
			preparedStatement.setString(6, leadheader.getPROJECT_EXE_STATE());
			preparedStatement.setString(7, leadheader.getCREATION_DATE());
			preparedStatement.setString(8, leadheader.getCREATED_BY());
			preparedStatement.setString(9, leadheader.getOWNER_ID());
			preparedStatement.setString(10, leadheader.getPROJECT_REFERENCE());
			preparedStatement.setString(11, leadheader.getDG_TYPE());
			preparedStatement.setString(12, leadheader.getSEGMENT());
			preparedStatement.setString(13, leadheader.getCOMPETITIONS());
			preparedStatement.setString(14, leadheader.getLEAD_CHANCES());
			preparedStatement.setString(15, leadheader.getLEAD_STATUS());
			preparedStatement.setString(16, leadheader.getCONSULTANT_NAME());
			preparedStatement.setString(17,
					leadheader.getCONSULTANT_PERSON_NAME());
			preparedStatement.setString(18,
					leadheader.getCONSULTANT_MOBILE_NO());
			preparedStatement.setString(19, leadheader.getCUSTOMER_NAME());
			preparedStatement.setString(20,
					leadheader.getCUSTOMER_EMAIL_ADDRESS());
			preparedStatement.setString(21, leadheader.getCUSTOMER_ADDRESS());
			preparedStatement.setString(22, leadheader.getCUSTOMER_MOBILENO());
			preparedStatement.setString(23,
					leadheader.getCUSTOMER_ALTERNATE_NO());
			preparedStatement.setString(24,
					leadheader.getEND_PERSON_CUSTOMER_NAME());
			preparedStatement.setString(25,
					leadheader.getEND_PERSON_CUSTOMER_CONTACTNO());
			preparedStatement.setString(26, leadheader.getPMC_NAME());
			preparedStatement.setString(27, leadheader.getPMC_CONTACT_PERSON());
			preparedStatement.setString(28, leadheader.getPMC_CONTACT_NO());
			preparedStatement.setString(29, leadheader.getPMC_CONTACT_ALT_NO());
			preparedStatement.setString(30, leadheader.getOWNER_NAME());
			preparedStatement.setString(31, leadheader.getENQUIRY_TYPE());
			preparedStatement
					.setString(32, leadheader.getLEAD_CREATION_PLACE());
			preparedStatement
					.setString(33, leadheader.getLEAD_CREATION_STATE());
			preparedStatement.setString(34,
					leadheader.getLEAD_CRETION_SHORT_STATE());
			preparedStatement.setString(35,
					leadheader.getLEAD_CREATION_REGION());
			preparedStatement.setString(36,
					leadheader.getLEAD_EXECUTION_DATE());
			preparedStatement.setString(37, leadheader.getDG_DELIVERY_DATE());
			preparedStatement.setString(38, leadheader.getPROJECT_CLOUSURE_DATE());
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
	
	public void updateLeadStatus(LeadHeader leadheader) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update lead_header set LEAD_STATUS=?	 where lead_id=?");

			preparedStatement.setString(1,
					leadheader.getLEAD_STATUS());
			preparedStatement.setString(2, leadheader.getLEAD_ID());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void updateLeadCostDetails(LeadHeader leadheader) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update lead_header set LOW_SIDE_SUPPLY_BASIC=?,"
							+ " LOW_SIDE_LABOUR_BASIC=?, LOW_SIDE_MARGIN=?, "
							+ "PROJECT_MARGIN=?, DG_ED_VALUE=?, PROJECT_TOTAL=?,DG_BASIC_MARGIN=?,DG_SELLING_VALUE=?,LOW_SIDE_SELLING_VALUE=?,PROJECT_SELLING_VALUE=? where lead_id=?");

			preparedStatement.setDouble(1,
					leadheader.getLOW_SIDE_SUPPLY_BASIC());
			preparedStatement.setDouble(2,
					leadheader.getLOW_SIDE_LABOUR_BASIC());
			preparedStatement.setDouble(3, leadheader.getLOW_SIDE_MARGIN());
			preparedStatement.setDouble(4, leadheader.getPROJECT_MARGIN());
			preparedStatement.setString(5, leadheader.getDG_ED_VALUE());
			preparedStatement.setDouble(6, leadheader.getPROJECT_TOTAL());
			preparedStatement.setString(7, leadheader.getDG_BASIC_MARGIN());
			preparedStatement.setString(8, leadheader.getDG_SELLING_VALUE());
			preparedStatement.setString(9, leadheader.getLOW_SIDE_SELLING_VALUE());
			preparedStatement.setString(10, leadheader.getPROJECT_SELLING_VALUE());
			preparedStatement.setString(11, leadheader.getLEAD_ID());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateLeadCustomerInformation(LeadHeader leadheader) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update lead_header set CONSULTANT_NAME=? ,"
							+ "CONSULTANT_PERSON_NAME=? ,CONSULTANT_MOBILE_NO=? ,"
							+ "CUSTOMER_NAME=? ,CUSTOMER_EMAIL_ADDRESS=? ,CUSTOMER_ADDRESS=? ,"
							+ "CUSTOMER_MOBILENO=? ,CUSTOMER_ALTERNATE_NO=? ,"
							+ "END_PERSON_CUSTOMER_NAME=? ,"
							+ "END_PERSON_CUSTOMER_CONTACTNO=? ,PMC_NAME=? ,"
							+ "PMC_CONTACT_PERSON=? ,PMC_CONTACT_NO=? ,"
							+ "PMC_CONTACT_ALT_NO=? ,MODIFIED_BY=? ,MODIFIED_DATE=? where lead_id=?");

			preparedStatement.setString(1, leadheader.getCONSULTANT_NAME());
			preparedStatement.setString(2,
					leadheader.getCONSULTANT_PERSON_NAME());
			preparedStatement
					.setString(3, leadheader.getCONSULTANT_MOBILE_NO());
			preparedStatement.setString(4, leadheader.getCUSTOMER_NAME());
			preparedStatement.setString(5, leadheader.getCUSTOMER_ADDRESS());
			preparedStatement.setString(6, leadheader.getCUSTOMER_MOBILENO());
			preparedStatement.setString(7,
					leadheader.getCUSTOMER_ALTERNATE_NO());

			preparedStatement.setString(8,
					leadheader.getEND_PERSON_CUSTOMER_NAME());
			preparedStatement.setString(9,
					leadheader.getEND_PERSON_CUSTOMER_CONTACTNO());
			preparedStatement.setString(10, leadheader.getPMC_NAME());
			preparedStatement.setString(11, leadheader.getPMC_CONTACT_PERSON());
			preparedStatement.setString(12, leadheader.getPMC_CONTACT_NO());
			preparedStatement.setString(13, leadheader.getPMC_CONTACT_NO());
			preparedStatement.setString(14, leadheader.getPMC_CONTACT_ALT_NO());

			preparedStatement.setString(15, leadheader.getMODIFIED_BY());
			preparedStatement.setString(16, leadheader.getMODIFIED_DATE());

			preparedStatement.setString(17, leadheader.getLEAD_ID());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<LeadHeader> getLeadCostDetails(String leadID) {
		// TODO Auto-generated method stub

		List<LeadHeader> lMasters = new ArrayList<LeadHeader>();
		try {
			Statement statement = connection.createStatement();
			String sql = "select LOW_SIDE_SUPPLY_BASIC, LOW_SIDE_LABOUR_BASIC, LOW_SIDE_MARGIN, "
					+ "PROJECT_MARGIN, DG_ED_VALUE, PROJECT_TOTAL,DG_SELLING_VALUE, LOW_SIDE_SELLING_VALUE, PROJECT_SELLING_VALUE,DG_BASIC_MARGIN from lead_header where lead_id='"
					+ leadID + "'";
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				LeadHeader leadDetails = new LeadHeader();

				leadDetails.setLOW_SIDE_SUPPLY_BASIC(rs
						.getDouble("lOW_SIDE_SUPPLY_BASIC"));

				leadDetails.setLOW_SIDE_LABOUR_BASIC(rs
						.getDouble("lOW_SIDE_LABOUR_BASIC"));

				leadDetails.setLOW_SIDE_MARGIN(rs.getDouble("lOW_SIDE_MARGIN"));
				leadDetails.setPROJECT_MARGIN(rs.getDouble("pROJECT_MARGIN"));

				leadDetails.setDG_ED_VALUE(rs.getString("dG_ED_VALUE"));

				
				leadDetails.setPROJECT_TOTAL(rs.getDouble("pROJECT_TOTAL"));
				leadDetails.setDG_BASIC_MARGIN(rs.getString("dG_BASIC_MARGIN"));
				leadDetails.setDG_SELLING_VALUE(rs.getString("dG_SELLING_VALUE"));
				leadDetails.setLOW_SIDE_SELLING_VALUE(rs.getString("lOW_SIDE_SELLING_VALUE"));
				leadDetails.setPROJECT_SELLING_VALUE(rs.getString("pROJECT_SELLING_VALUE"));

				lMasters.add(leadDetails);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lMasters;
	}

	public List<LeadHeader> getLeadHeadDetailsData(String fromDate,
			String toDate, String region, String state, String city,
			String roleId, String createdBy) {
		// TODO Auto-generated method stub
		List<LeadHeader> cMasters = new LinkedList<LeadHeader>();
		try {
			Statement statement = connection.createStatement();
			String sql = "select * from lead_header where LEAD_BUDG='0' and cast(creation_date as date) between '"
					+ fromDate + "' and '" + toDate + "'";

			if (!region.equalsIgnoreCase("0")) {
				sql += " and LEAD_CREATION_REGION='" + region + "'";
			}
			if (!state.equalsIgnoreCase("0")) {
				sql += " and PROJECT_EXE_STATE='" + state + "'";
			}
			if (!city.equalsIgnoreCase("0")) {
				sql += " and PROJECT_EXE_PLACE='" + city + "'";
			}
			if (!createdBy.equalsIgnoreCase("0")) {
				sql += " and CREATED_BY='" + createdBy + "'";
			}

			System.out.println("Hello" + sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				LeadHeader cMaster = new LeadHeader();

				cMaster.setLEAD_ID(rs.getString("lEAD_ID"));
				cMaster.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
				cMaster.setOWNER_ID(rs.getString("oWNER_ID"));
				cMaster.setPROJECT_EXE_PLACE(rs.getString("pROJECT_EXE_PLACE"));
				cMaster.setCREATION_DATE(rs.getString("cREATION_DATE"));
				cMaster.setLEAD_CHANCES(rs.getString("lEAD_CHANCES"));
				cMaster.setCUSTOMER_MOBILENO(rs.getString("cUSTOMER_MOBILENO"));
				cMaster.setCUSTOMER_ADDRESS(rs.getString("cUSTOMER_ADDRESS"));
				cMaster.setCUSTOMER_EMAIL_ADDRESS(rs.getString("cUSTOMER_EMAIL_ADDRESS"));
				cMaster.setLEAD_STATUS(rs.getString("lEAD_STATUS"));
				


				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}
	
	public List<LeadHeader> getIndentDetailsData(String fromDate,
			String toDate, String region, String state, String city,
			String roleId, String createdBy) {
		// TODO Auto-generated method stub
		List<LeadHeader> cMasters = new LinkedList<LeadHeader>();
		try {
			Statement statement = connection.createStatement();
			String sql = "select * from lead_header where indent_no is not null and  cast(creation_date as date) between '"
					+ fromDate + "' and '" + toDate + "'";

			if (!region.equalsIgnoreCase("0")) {
				sql += " and LEAD_CREATION_REGION='" + region + "'";
			}
			if (!state.equalsIgnoreCase("0")) {
				sql += " and PROJECT_EXE_STATE='" + state + "'";
			}
			if (!city.equalsIgnoreCase("0")) {
				sql += " and PROJECT_EXE_PLACE='" + city + "'";
			}
			if (!createdBy.equalsIgnoreCase("0")) {
				sql += " and CREATED_BY='" + createdBy + "'";
			}

			System.out.println("Hello" + sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				LeadHeader cMaster = new LeadHeader();

				cMaster.setLEAD_ID(rs.getString("lEAD_ID"));
				cMaster.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
				cMaster.setOWNER_ID(rs.getString("oWNER_ID"));
				cMaster.setPROJECT_EXE_PLACE(rs.getString("pROJECT_EXE_PLACE"));
				cMaster.setCREATION_DATE(rs.getString("cREATION_DATE"));
				cMaster.setLEAD_CHANCES(rs.getString("lEAD_CHANCES"));
				cMaster.setINDENT_NO(rs.getString("iNDENT_NO"));

				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}
	public List<LeadHeader> getBudgetLeadHeadDataDetails(String from_date,String to_date,String userState) {
		// TODO Auto-generated method stub
		List<LeadHeader> cMasters = new LinkedList<LeadHeader>();
		try {
			Statement statement = connection.createStatement();
			String sql = "select ID, LEAD_ID, TRANSACTION_TYPE, PROJECT_TYPE, ENQUIRY_TYPE, LEAD_BUDG, PROJECT_EXE_PLACE, PROJECT_EXE_STATE, LEAD_CREATION_PLACE, LEAD_CREATION_STATE, LEAD_CRETION_SHORT_STATE, LEAD_CREATION_REGION,"
					+ " cast(LEAD_EXECUTION_DATE as date) as LEAD_EXECUTION_DATE, cast(CREATION_DATE as date) as CREATION_DATE, CREATED_BY,"
					+ " cast(MODIFIED_DATE as date) as MODIFIED_DATE, MODIFIED_BY, OWNER_ID, OWNER_NAME, PROJECT_REFERENCE, DG_TYPE, SEGMENT, COMPETITIONS, LEAD_CHANCES, LEAD_STATUS, CONSULTANT_NAME, CONSULTANT_PERSON_NAME, CONSULTANT_MOBILE_NO, CUSTOMER_NAME, CUSTOMER_EMAIL_ADDRESS, CUSTOMER_ADDRESS, CUSTOMER_MOBILENO, CUSTOMER_ALTERNATE_NO, END_PERSON_CUSTOMER_NAME, END_PERSON_CUSTOMER_CONTACTNO, PMC_NAME, PMC_CONTACT_PERSON, PMC_CONTACT_NO, PMC_CONTACT_ALT_NO, LOW_SIDE_SUPPLY_BASIC, LOW_SIDE_LABOUR_BASIC, LOW_SIDE_MARGIN, PROJECT_MARGIN, DG_ED_VALUE, PROJECT_TOTAL, INDENT_NO from lead_header where "
					+ " LEAD_BUDG='1' and LEAD_CRETION_SHORT_STATE='"+userState+"' "
					 + " and cast(CREATION_DATE as date) between '"+from_date+"' and '"+to_date+"' order by cast(CREATION_DATE as date) desc";
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				LeadHeader cMaster = new LeadHeader();

				// cMaster.set.setID(rs.getInt("ID"));
				cMaster.setLEAD_ID(rs.getString("lEAD_ID"));
				cMaster.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
				cMaster.setOWNER_ID(rs.getString("oWNER_ID"));
				cMaster.setPROJECT_EXE_PLACE(rs.getString("pROJECT_EXE_PLACE"));
				cMaster.setCREATION_DATE(rs.getString("cREATION_DATE"));
				cMaster.setLEAD_CHANCES(rs.getString("lEAD_CHANCES"));
				cMaster.setCUSTOMER_MOBILENO(rs.getString("cUSTOMER_MOBILENO"));
				cMaster.setCUSTOMER_ADDRESS(rs.getString("cUSTOMER_ADDRESS"));
				cMaster.setCUSTOMER_EMAIL_ADDRESS(rs.getString("cUSTOMER_EMAIL_ADDRESS"));
				cMaster.setINDENT_NO(rs.getString("iNDENT_NO"));
				
				
				cMaster.setDG_ED_VALUE(rs.getString("DG_ED_VALUE"));
				

				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}
	
	public List<LeadHeader> getBudgetLeadHeadDetailsData(String fromDate,
			String toDate, String region, String state, String city,
			String roleId, String createdBy) {
		// TODO Auto-generated method stub
		List<LeadHeader> cMasters = new LinkedList<LeadHeader>();
		try {
			Statement statement = connection.createStatement();
			
			String sql = "select * from lead_header where LEAD_BUDG='1' and  cast(creation_date as date) between '"
					+ fromDate + "' and '" + toDate + "'";

			if (!region.equalsIgnoreCase("0")) {
				sql += " and LEAD_CREATION_REGION='" + region + "'";
			}
			if (!state.equalsIgnoreCase("0")) {
				sql += " and PROJECT_EXE_STATE='" + state + "'";
			}
			if (!city.equalsIgnoreCase("0")) {
				sql += " and PROJECT_EXE_PLACE='" + city + "'";
			}
			if (!createdBy.equalsIgnoreCase("0")) {
				sql += " and CREATED_BY='" + createdBy + "'";
			}

			System.out.println("Hello" + sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				LeadHeader cMaster = new LeadHeader();

				cMaster.setLEAD_ID(rs.getString("lEAD_ID"));
				cMaster.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
				cMaster.setOWNER_ID(rs.getString("oWNER_ID"));
				cMaster.setPROJECT_EXE_PLACE(rs.getString("pROJECT_EXE_PLACE"));
				cMaster.setCREATION_DATE(rs.getString("cREATION_DATE"));
				cMaster.setLEAD_CHANCES(rs.getString("lEAD_CHANCES"));
				cMaster.setINDENT_NO(rs.getString("iNDENT_NO"));

				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}

	public String getLeadCount(String userid, String role, String state,
			String city, String region, String status) {

		String sql = "";
		String leadCount = "";
		if (role.equalsIgnoreCase("SE")) {
			sql = "select IFNULL(count(*),0)  as LeadCount from lead_header where CREATED_BY='"
					+ userid + "' and LEAD_STATUS='" + status + "'";
		}

		if (role.equalsIgnoreCase("BM")) {
			sql = "select IFNULL(count(*),0) as LeadCount from lead_header where LEAD_CRETION_SHORT_STATE like'%"
					+ state + "%' and LEAD_STATUS='" + status + "'";
		}

		if (role.equalsIgnoreCase("RSM")) {
			sql = "select IFNULL(count(*),0) as LeadCount from lead_header where LEAD_CREATION_REGION='"
					+ region + "' and LEAD_STATUS='" + status + "'";
		}

		if (role.equalsIgnoreCase("BH")) {
			sql = "select IFNULL(count(*),0) as LeadCount from lead_header where LEAD_STATUS='"
					+ status + "'";
		}
		Statement statement;
		try {
			statement = connection.createStatement();
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				leadCount = rs.getString("LeadCount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return leadCount;
	}

	public String getLeadAmount(String userid, String role, String state,
			String city, String region, String status) {
		String sql = "";
		String leadAmount = "0";
		if (role.equalsIgnoreCase("SE")) {
			sql = "select IFNULL(sum(PROJECT_TOTAL),0) as leadamount from lead_header where CREATED_BY='"
					+ userid + "' and LEAD_STATUS='" + status + "'";
		}

		if (role.equalsIgnoreCase("BM")) {
			sql = "select IFNULL(sum(PROJECT_TOTAL),0) as leadamount from lead_header where LEAD_CRETION_SHORT_STATE like'%"
					+ state + "%' and LEAD_STATUS='" + status + "'";
		}

		if (role.equalsIgnoreCase("RSM")) {
			sql = "select IFNULL(sum(PROJECT_TOTAL),0) as leadamount from lead_header where LEAD_CREATION_REGION='"
					+ region + "' and LEAD_STATUS='" + status + "'";
		}

		if (role.equalsIgnoreCase("BH")) {
			sql = "select IFNULL(sum(PROJECT_TOTAL),0) as leadamount from lead_header where LEAD_STATUS='"
					+ status + "'";
		}
		Statement statement;
		try {
			statement = connection.createStatement();
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				leadAmount = rs.getString("leadamount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return leadAmount;
	}

	public void updateLeadHeader(LeadHeader leadheader) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update lead_header set TRANSACTION_TYPE=?, "
							+ "PROJECT_TYPE=?, PROJECT_EXE_PLACE=?,"
							+ " PROJECT_EXE_STATE=?, MODIFIED_DATE=?, MODIFIED_BY=?, "
							+ "OWNER_ID=?,PROJECT_REFERENCE=?,  DG_TYPE=?, SEGMENT=?,"
							+ " COMPETITIONS=?, LEAD_CHANCES=?,"
							+ " LEAD_STATUS=?,OWNER_NAME=?,DG_DELIVERY_DATE=?,PROJECT_CLOUSURE_DATE=? "
							+ "where LEAD_ID=?");

			preparedStatement.setString(1, leadheader.getTRANSACTION_TYPE());
			preparedStatement.setString(2, leadheader.getPROJECT_TYPE());
			preparedStatement.setString(3, leadheader.getPROJECT_EXE_PLACE());
			preparedStatement.setString(4, leadheader.getPROJECT_EXE_STATE());
			preparedStatement.setString(5, leadheader.getMODIFIED_DATE());
			preparedStatement.setString(6, leadheader.getMODIFIED_BY());
			preparedStatement.setString(7, leadheader.getOWNER_ID());
			preparedStatement.setString(8, leadheader.getPROJECT_REFERENCE());
			preparedStatement.setString(9, leadheader.getDG_TYPE());
			preparedStatement.setString(10, leadheader.getSEGMENT());
			preparedStatement.setString(11, leadheader.getCOMPETITIONS());
			preparedStatement.setString(12, leadheader.getLEAD_CHANCES());
			preparedStatement.setString(13, leadheader.getLEAD_STATUS());
			preparedStatement.setString(14, leadheader.getOWNER_NAME());
			preparedStatement.setString(15, leadheader.getDG_DELIVERY_DATE());
			preparedStatement.setString(16, leadheader.getPROJECT_CLOUSURE_DATE());
			
			preparedStatement.setString(17, leadheader.getLEAD_ID());
			System.out.println(preparedStatement.toString());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<String> getCompetitions(String leadID, String competition) {
		// TODO Auto-generated method stub
		List<String> compList = new ArrayList<String>();

		try {
			Statement statement = connection.createStatement();
			String sql = "select distinct COMPETITIONS from lead_header where  COMPETITIONS like'" + competition + "%'";
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {

				compList.add((rs.getString("COMPETITIONS")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return compList;
	}

	public String getMonthLeadCount(String userid, String role, String state,
			String city, String region, String status, String start_date,
			String end_date) {
		
		String sql = "";
		String leadCount = "";
		if (role.equalsIgnoreCase("SE")) {
			sql = "select IFNULL(count(*),0)  as LeadCount from lead_header where CREATED_BY='"
					+ userid + "' and LEAD_STATUS='" + status + "' and cast(CREATION_DATE as date) between '"+start_date+"' and '"+end_date+"'";
		}

		if (role.equalsIgnoreCase("BM")) {
			sql = "select IFNULL(count(*),0) as LeadCount from lead_header where LEAD_CRETION_SHORT_STATE like'%"
					+ state + "%' and LEAD_STATUS='" + status + "' and cast(CREATION_DATE as date) between '"+start_date+"' and '"+end_date+"'";
		}

		if (role.equalsIgnoreCase("RSM")) {
			sql = "select IFNULL(count(*),0) as LeadCount from lead_header where LEAD_CREATION_REGION='"
					+ region + "' and LEAD_STATUS='" + status + "' and cast(CREATION_DATE as date) between '"+start_date+"' and '"+end_date+"'";
		}

		if (role.equalsIgnoreCase("BH")) {
			sql = "select IFNULL(count(*),0) as LeadCount from lead_header where LEAD_STATUS='"
					+ status + "' and cast(CREATION_DATE as date) between '"+start_date+"' and '"+end_date+"'";
		}
		Statement statement;
		try {
			statement = connection.createStatement();
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				leadCount = rs.getString("LeadCount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return leadCount;
	}

	public List<String> getMonthWiseLeadCountTillCurrentMonth(String userid,String role, String state, String city, String region,
			String status, String start_month) {
		String sql = "";
		List ls=new LinkedList();
		String start_date="";
		String ldCount="";
		String end_date="";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		
		
		int monthMax=calendar.get(Calendar.MONTH);
		int year=calendar.get(Calendar.YEAR);
		int j=0;
		
		
			
			for(int i=Integer.parseInt(start_month);i<=15;i++)
			{
				j=i-1;
				System.out.println("Month"+i);
				calendar.set(year, j, 1);
					
					start_date=dateFormat.format(calendar.getTime());
					
					int dateDay = calendar.getActualMaximum(Calendar.DATE);
					calendar.set(year, j, dateDay);
					end_date=dateFormat.format(calendar.getTime());
					
					System.out.println("Hey"+start_date +"and "+end_date);
					
					
					ldCount=getMonthLeadCount(userid, role, state,city, region, status, start_date,end_date);
					ls.add(ldCount);
				j++;
			
	}
		return ls;

	}

	public String getMonthLeadAmount(String userid, String role, String state,
			String city, String region, String status, String start_date,
			String end_date) {
		String sql = "";
		String leadAmount = "0";
		if (role.equalsIgnoreCase("SE")) {
			sql = "select IFNULL(sum(PROJECT_TOTAL),0) as leadamount from lead_header where CREATED_BY='"
					+ userid + "' and LEAD_STATUS='" + status + "' and cast(CREATION_DATE as date) between '"+start_date+"' and '"+end_date+"'";
		}

		if (role.equalsIgnoreCase("BM")) {
			sql = "select IFNULL(sum(PROJECT_TOTAL),0) as leadamount from lead_header where LEAD_CRETION_SHORT_STATE like'%"
					+ state + "%' and LEAD_STATUS='" + status + "' and cast(CREATION_DATE as date) between '"+start_date+"' and '"+end_date+"'";
		}

		if (role.equalsIgnoreCase("RSM")) {
			sql = "select IFNULL(sum(PROJECT_TOTAL),0) as leadamount from lead_header where LEAD_CREATION_REGION='"
					+ region + "' and LEAD_STATUS='" + status + "' and cast(CREATION_DATE as date) between '"+start_date+"' and '"+end_date+"'";
		}

		if (role.equalsIgnoreCase("BH")) {
			sql = "select IFNULL(sum(PROJECT_TOTAL),0) as leadamount from lead_header where LEAD_STATUS='"
					+ status + "' and cast(CREATION_DATE as date) between '"+start_date+"' and '"+end_date+"'";
		}
		Statement statement;
		try {
			statement = connection.createStatement();
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				leadAmount = rs.getString("leadamount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return leadAmount;
	}
	
	public void updateIndent(LeadHeader leadheader) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update lead_header set INDENT_NO=?,MODIFIED_BY=?,MODIFIED_DATE=?,LEAD_STATUS=?	 where lead_id=?");

			preparedStatement.setString(1,
					leadheader.getINDENT_NO());
			preparedStatement.setString(2, leadheader.getMODIFIED_BY());
			preparedStatement.setString(3, leadheader.getMODIFIED_DATE());
			preparedStatement.setString(4, "Closed");
			preparedStatement.setString(5, leadheader.getLEAD_ID());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateLeadHeadLeadID(String budleadID,String leadID,String createdDate,String createdBy) {
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			String sql="update lead_header set LEAD_ID=?,LEAD_BUDG=?,MODIFIED_BY=?,MODIFIED_DATE=? where LEAD_ID=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			preparedStatement.setString(1, leadID);
			preparedStatement.setString(2, "0");
			preparedStatement.setString(3, createdBy);
			preparedStatement.setString(4, createdDate);
			preparedStatement.setString(5, budleadID);
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
