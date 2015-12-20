package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.SupplyScopeDao;
import com.lms.model.Lead_Pricing;
import com.lms.model.SupplyScope;
import com.lms.util.DbUtil;

public class SupplyScopeDaoImpl implements SupplyScopeDao {

	private Connection connection;

	public SupplyScopeDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello" + connection);
	}
	


	public String addSupplyScopeDetails(SupplyScope supplyScope) {
		String message="";
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			
			if(getSupplyScopeDetails(supplyScope.getLEAD_ID()).size()>0)
			{
				message=updateSupplyScopeDetails(supplyScope);
			}
			else
			{
			
			String sql="insert into lead_supply_scope(LEAD_ID, FACTORY_NAME, BRANCH_NAME, FACTORY_SUPPLY_SCOPE,"
					+ " BRANCH_SUPPLY_SCOPE, WARRANTY_DETAILS, OPERATOR_REQUIRED,CREATED_BY, CREATED_DATE)"
					+ " values(?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			preparedStatement.setString(1,supplyScope.getLEAD_ID());
			
			
			preparedStatement.setString(2,supplyScope.getFACTORY_NAME());
			preparedStatement.setString(3,supplyScope.getBRANCH_NAME());
			preparedStatement.setString(4,supplyScope.getFACTORY_SUPPLY_SCOPE());
			preparedStatement.setString(5,supplyScope.getBRANCH_SUPPLY_SCOPE());
			preparedStatement.setString(6,supplyScope.getWARRANTY_DETAILS());
			preparedStatement.setString(7,supplyScope.getOPERATOR_REQUIRED());
			preparedStatement.setString(8,supplyScope.getCREATED_BY());
			preparedStatement.setString(9,supplyScope.getCREATED_DATE());

			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			message="Done";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		return message;
	}

	public String updateSupplyScopeDetails(SupplyScope supplyScope) {
		String message="";
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			
			String sql="update lead_supply_scope set  FACTORY_NAME=?, BRANCH_NAME=?, FACTORY_SUPPLY_SCOPE=?,"
					+ " BRANCH_SUPPLY_SCOPE=?, WARRANTY_DETAILS=?, OPERATOR_REQUIRED=?,MODIFIED_BY=?, MODIFIED_DATE=?"
					+ " WHERE LEAD_ID=?";
			
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			
			preparedStatement.setString(1,supplyScope.getFACTORY_NAME());
			preparedStatement.setString(2,supplyScope.getBRANCH_NAME());
			preparedStatement.setString(3,supplyScope.getFACTORY_SUPPLY_SCOPE());
			preparedStatement.setString(4,supplyScope.getBRANCH_SUPPLY_SCOPE());
			preparedStatement.setString(5,supplyScope.getWARRANTY_DETAILS());
			preparedStatement.setString(6,supplyScope.getOPERATOR_REQUIRED());
			preparedStatement.setString(7,supplyScope.getMODIFIED_BY());
			preparedStatement.setString(8,supplyScope.getMODIFIED_DATE());
			preparedStatement.setString(9,supplyScope.getLEAD_ID());

			
			preparedStatement.executeUpdate();
			message="Done";

		} catch (SQLException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		return message;
	}

	public List<SupplyScope> getSupplyScopeDetails(String leadID) {
		List<SupplyScope> itemTechnicals = new ArrayList<SupplyScope>();
		try {
			Statement statement = connection.createStatement();
			String sql="select LEAD_ID, FACTORY_NAME, BRANCH_NAME, FACTORY_SUPPLY_SCOPE,"
					+ " BRANCH_SUPPLY_SCOPE, WARRANTY_DETAILS, OPERATOR_REQUIRED, "
					+ "CREATED_BY, cast(CREATED_DATE as date) as CREATED_DATE,"
					+ " MODIFIED_BY,cast(MODIFIED_DATE as date) as MODIFIED_DATE "
					+ "from lead_supply_scope "
					+ " where LEAD_ID='"+leadID+"'  order by CREATED_DATE desc";
			System.out.println(sql);
			ResultSet rs = statement
					.executeQuery(sql);
			while (rs.next()) {
				SupplyScope supplyInfo = new SupplyScope();
				supplyInfo.setLEAD_ID(rs.getString("lEAD_ID"));
				supplyInfo.setFACTORY_NAME(rs.getString("fACTORY_NAME"));
				supplyInfo.setBRANCH_NAME(rs.getString("bRANCH_NAME"));
				supplyInfo.setFACTORY_SUPPLY_SCOPE(rs.getString("fACTORY_SUPPLY_SCOPE"));
				supplyInfo.setBRANCH_SUPPLY_SCOPE(rs.getString("bRANCH_SUPPLY_SCOPE"));
				supplyInfo.setWARRANTY_DETAILS(rs.getString("wARRANTY_DETAILS"));
				supplyInfo.setOPERATOR_REQUIRED(rs.getString("oPERATOR_REQUIRED"));
				supplyInfo.setCREATED_BY(rs.getString("cREATED_BY"));
				supplyInfo.setCREATED_DATE(rs.getString("cREATED_DATE"));
				supplyInfo.setMODIFIED_BY(rs.getString("mODIFIED_BY"));
				supplyInfo.setMODIFIED_DATE(rs.getString("mODIFIED_DATE"));
				
				itemTechnicals.add(supplyInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return itemTechnicals;
	}

}
