package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.LeadItemDetailsDao;
import com.lms.model.LeadItemDetails;
import com.lms.util.DbUtil;
import com.lms.util.Utility;

public class LeadItemDetailsDaoImpl implements LeadItemDetailsDao {

	private Connection connection;

	public LeadItemDetailsDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello" + connection);
	}

	public void addLeadItems(LeadItemDetails leadItems) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into lead_details("
							+ "LEAD_ID, LEAD_DEFAULT, "
							+ "DTL_RATING,DTL_ITEM_TYPE,DTL_ENGINE_MAKE,"
							+ " DTL_ENGINE_MODEL, DTL_ALTERNATOR_MAKE, "
							+ "DTL_ALTERNATOR_MODEL, DTL_VOLTAGE,"
							+ " DTL_HZ, DTL_QTY, "
							+ "DTL_COOLING_SYSTEM, DTL_AMOUNT,"
							+ " DTL_FLEX1, DTL_FLEX2)"
							+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");

			preparedStatement.setString(1, leadItems.getLEAD_ID());
			preparedStatement.setString(2, leadItems.getLEAD_DEFAULT());
			preparedStatement.setString(3, leadItems.getDTL_RATING());
			preparedStatement.setString(4, leadItems.getDTL_ITEM_TYPE());
			preparedStatement.setString(5, leadItems.getDTL_ENGINE_MAKE());
			preparedStatement.setString(6, leadItems.getDTL_ENGINE_MODEL());
			preparedStatement.setString(7, leadItems.getDTL_ALTERNATOR_MAKE());
			preparedStatement.setString(8, leadItems.getDTL_ALTERNATOR_MODEL());
			preparedStatement.setString(9, leadItems.getDTL_VOLTAGE());
			preparedStatement.setString(10, leadItems.getDTL_HZ());
			preparedStatement.setString(11, leadItems.getDTL_QTY());
			preparedStatement.setString(12, leadItems.getDTL_COOLING_SYSTEM());
			preparedStatement.setString(13, leadItems.getDTL_AMOUNT());
			preparedStatement.setString(14, leadItems.getDTL_FLEX1());
			preparedStatement.setString(15, leadItems.getDTL_FLEX2());
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<LeadItemDetails> getLeadItemsByLeadID(String leadID) {
		// TODO Auto-generated method stub
		List<LeadItemDetails> cMasters = new ArrayList<LeadItemDetails>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select * from lead_details where lead_id='"
							+ leadID + "'");
			while (rs.next()) {
				LeadItemDetails leadDetails = new LeadItemDetails();

				leadDetails.setDTL_ID(rs.getInt("dTL_ID"));
				leadDetails.setLEAD_ID(Utility.encodeURIComponent(rs.getString("lEAD_ID")));
				leadDetails.setDTL_RATING(Utility.encodeURIComponent(rs.getString("dTL_RATING")));

				leadDetails.setDTL_ITEM_TYPE(Utility.encodeURIComponent(rs.getString("dTL_ITEM_TYPE")));
				leadDetails.setDTL_ENGINE_MAKE(Utility.encodeURIComponent(rs.getString("dTL_ENGINE_MAKE")));

				leadDetails.setDTL_ENGINE_MODEL(rs
						.getString(Utility.encodeURIComponent("dTL_ENGINE_MODEL")));

				leadDetails.setDTL_ALTERNATOR_MAKE(Utility.encodeURIComponent(rs.getString("dTL_ALTERNATOR_MAKE")));
				leadDetails.setDTL_ALTERNATOR_MODEL(Utility.encodeURIComponent(rs.getString("dTL_ALTERNATOR_MODEL")));
				leadDetails.setDTL_COOLING_SYSTEM(Utility.encodeURIComponent(rs.getString("dTL_COOLING_SYSTEM")));
				leadDetails.setDTL_HZ(rs.getString("dTL_HZ"));
				leadDetails.setDTL_QTY(rs.getString("dTL_QTY"));
				leadDetails.setDTL_AMOUNT(rs.getString("dTL_AMOUNT"));
				leadDetails.setDTL_VOLTAGE(rs.getString("dTL_VOLTAGE"));
				leadDetails.setDTL_FLEX1(Utility.encodeURIComponent(rs.getString("dTL_FLEX1")));
				leadDetails.setDTL_FLEX2(Utility.encodeURIComponent(rs.getString("dTL_FLEX2")));
				

				cMasters.add(leadDetails);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}

	public String getTotalLeadAmount(String leadID) {
		// TODO Auto-generated method stub
		String total = "";
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select sum(DTL_QTY*DTL_AMOUNT) as totalleaddtlamt from lead_details where  lead_id='"
							+ leadID + "'");
			while (rs.next()) {
				total=rs.getDouble(1)+"";
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return total;
	}
	public String deleteLeadItem(int id)
	{
		String msg="";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE from lead_details where DTL_ID=?");

			preparedStatement.setInt(1,id);
			

			preparedStatement.executeUpdate();
			msg="Done";

		} catch (SQLException e) {
			msg=e.getMessage();
			e.printStackTrace();
		}

		return msg;	
	}

	public void updateLeadItemsLeadID(String budleadID,String leadID,String createdDate,String createdBy) {
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			PreparedStatement preparedStatement = connection
					.prepareStatement("update lead_details set LEAD_ID=? where LEAD_ID=?");

			preparedStatement.setString(1, leadID);
			
			preparedStatement.setString(2, budleadID);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void updateLeadItems(LeadItemDetails leadItems) {
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			String sql="update lead_details set DTL_RATING=?"
					+ ",DTL_ENGINE_MAKE=?,DTL_ENGINE_MODEL=?,DTL_ALTERNATOR_MODEL=?"
					+ ",DTL_VOLTAGE=?,DTL_HZ=?,DTL_QTY=?,DTL_COOLING_SYSTEM=?,DTL_AMOUNT=?,DTL_FLEX1=?,DTL_FLEX2=?"
					
					+ " where DTL_ID=?";
			System.out.println("Update Item Details"+sql);
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			preparedStatement.setString(1, leadItems.getDTL_RATING());
			
			preparedStatement.setString(2, leadItems.getDTL_ENGINE_MAKE());
			preparedStatement.setString(3, leadItems.getDTL_ENGINE_MODEL());
			preparedStatement.setString(4, leadItems.getDTL_ALTERNATOR_MODEL());
			preparedStatement.setString(5, leadItems.getDTL_VOLTAGE());
			preparedStatement.setString(6, leadItems.getDTL_HZ());
			preparedStatement.setString(7, leadItems.getDTL_QTY());
			preparedStatement.setString(8, leadItems.getDTL_COOLING_SYSTEM());
			preparedStatement.setString(9, leadItems.getDTL_AMOUNT());
			preparedStatement.setString(10, leadItems.getDTL_FLEX1());
			preparedStatement.setString(11, leadItems.getDTL_FLEX2());
			preparedStatement.setInt(12, leadItems.getDTL_ID());
			System.out.println("Formed SQL"+preparedStatement.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<String> getDistinctEngineNamefromLeaadDetails()
	{
		List ls=new ArrayList();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select distinct DTL_ENGINE_MAKE from lead_details  where substr(LEAD_ID,3,2)='DG'");
			while (rs.next()) {
				ls.add(rs.getString("DTL_ENGINE_MAKE"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
}
