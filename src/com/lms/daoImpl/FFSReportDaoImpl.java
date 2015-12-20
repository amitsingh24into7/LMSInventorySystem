package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.FFSReportDao;
import com.lms.model.EngineStock;
import com.lms.model.FFSReport;
import com.lms.util.DbUtil;

public class FFSReportDaoImpl implements FFSReportDao {

	private Connection connection;
	public FFSReportDaoImpl()
	{
		connection = DbUtil.getDBConnection();
	}
	
	public String addFFSData(FFSReport engineStock) {
		String message="";
		
		List<FFSReport> ls = (List<FFSReport>) getAllFFSReport(engineStock.getPROJECT_TYPE(), engineStock.getENGINE_NAME());
		if (ls.size() > 0) {
			
			message = updateAllFFS(engineStock);
		} else {
		try {
			String sql = " insert into engine_forecast(PROJECT_TYPE,ENGINE, FIN_YEAR, STOCK_AS_ON, TRANSIT, M1, M2, M3, M4, M5, M6, M7,"
					+ " M8, M9, M10, M11, M12, TOTALFSTOCK, TOTALSTOCK, EXE_PENDING_ORDER, PENDING_ORDER_ONHOLD,"
					+ " TOTAL_PENDING_ORDER, LEADS, MODIFIED_DATE, MODIFIED_BY) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			preparedStatement.setString(1, engineStock.getPROJECT_TYPE());
			preparedStatement.setString(2, engineStock.getENGINE_NAME());

			preparedStatement.setString(3, engineStock.getFIN_YEAR());
			preparedStatement.setString(4, engineStock.getSTOCK_AS_ON());
			preparedStatement.setString(5, engineStock.getTRANSIT());
			preparedStatement.setString(6, engineStock.getM1qty());
			
			preparedStatement.setString(7, engineStock.getM2qty());
			
			preparedStatement.setString(8, engineStock.getM3qty());
			
			preparedStatement.setString(9, engineStock.getM4qty());
			
			preparedStatement.setString(10, engineStock.getM5qty());
			
			preparedStatement.setString(11, engineStock.getM6qty());
			
			preparedStatement.setString(12, engineStock.getM7qty());
			
			preparedStatement.setString(13, engineStock.getM8qty());
			
			preparedStatement.setString(14, engineStock.getM9qty());
			
			preparedStatement.setString(15, engineStock.getM10qty());
			
			preparedStatement.setString(16, engineStock.getM11qty());
			preparedStatement.setString(17, engineStock.getM12qty());
			
			preparedStatement.setString(18, engineStock.getTotalFStock());
			preparedStatement.setString(19, engineStock.getTotalStock());
			preparedStatement.setString(20, engineStock.getExe_pending_order());
			preparedStatement.setString(21, engineStock.getPending_order_onhold());
			preparedStatement.setString(22, engineStock.getTotalPendingorder());
			preparedStatement.setString(23, engineStock.getLeads());
			
			
			preparedStatement.setString(24, engineStock.getMODIFIED_DATE());
			preparedStatement.setString(25, engineStock.getMODIFIED_BY());

			System.out.println(preparedStatement.toString());

			preparedStatement.executeUpdate();
			message = "Done";

		} catch (SQLException e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		
	}
		return message;
	}

	public List<FFSReport> getAllFFSReport(String projectType,
			String engineName) {
		List<FFSReport> engineStocks = new ArrayList<FFSReport>();
		try {
			Statement statement = connection.createStatement();
			String sql = "select * from engine_forecast where 1=1";
			
			if (!projectType.equalsIgnoreCase("")) {
				sql += " and PROJECT_TYPE='" + projectType + "'";
			}
			if (!engineName.equalsIgnoreCase("")) {
				sql += " and ENGINE='" + engineName + "'";
			}
			sql += " order by MODIFIED_DATE desc";
			System.out.println("FFS Sql" + sql);
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				FFSReport engineStock = new FFSReport();

				
				engineStock.setPROJECT_TYPE(rs.getString("pROJECT_TYPE"));
				engineStock.setENGINE_NAME(rs.getString("ENGINE"));
				engineStock.setFIN_YEAR(rs.getString("fIN_YEAR"));

				engineStock.setSTOCK_AS_ON(rs.getString("sTOCK_AS_ON"));
				engineStock.setTRANSIT(rs.getString("tRANSIT"));
				
				engineStock.setM1qty(rs.getString("m1"));
				engineStock.setM2qty(rs.getString("m2"));
				engineStock.setM3qty(rs.getString("m3"));
				engineStock.setM4qty(rs.getString("m4"));
				engineStock.setM5qty(rs.getString("m5"));
				engineStock.setM6qty(rs.getString("m6"));
				engineStock.setM7qty(rs.getString("m7"));
				engineStock.setM8qty(rs.getString("m8"));
				engineStock.setM9qty(rs.getString("m9"));
				engineStock.setM10qty(rs.getString("m10"));
				engineStock.setM11qty(rs.getString("m11"));
				engineStock.setM12qty(rs.getString("m12"));
				
				engineStock.setTotalFStock(rs.getString("totalFStock"));
				engineStock.setTotalStock(rs.getString("totalStock"));
				engineStock.setExe_pending_order(rs.getString("exe_pending_order"));
				engineStock.setPending_order_onhold(rs.getString("pending_order_onhold"));
				engineStock.setTotalPendingorder(rs.getString("TOTAL_PENDING_ORDER"));
				engineStock.setLeads(rs.getString("leads"));
				engineStock.setMODIFIED_BY(rs.getString("mODIFIED_BY"));
				engineStock.setMODIFIED_DATE(rs.getString("mODIFIED_DATE"));

				

				engineStocks.add(engineStock);
				System.out.println("asd"+engineStock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return engineStocks;
	}

	public String updateAllFFS(FFSReport engineStock) {
		String message="";
		try {
			String sql = " update engine_forecast set STOCK_AS_ON=?, TRANSIT=?, M1=?, M2=?, M3=?, M4=?, M5=?, M6=?, M7=?,"
					+ " M8=?, M9=?, M10=?, M11=?, M12=?, TOTALFSTOCK=?, TOTALSTOCK=?,MODIFIED_BY=?,MODIFIED_DATE=? where PROJECT_TYPE=? and ENGINE=?";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			

			
			preparedStatement.setString(1, engineStock.getSTOCK_AS_ON());
			preparedStatement.setString(2, engineStock.getTRANSIT());
			preparedStatement.setString(3, engineStock.getM1qty());
			
			preparedStatement.setString(4, engineStock.getM2qty());
			
			preparedStatement.setString(5, engineStock.getM3qty());
			
			preparedStatement.setString(6, engineStock.getM4qty());
			
			preparedStatement.setString(7, engineStock.getM5qty());
			
			preparedStatement.setString(8, engineStock.getM6qty());
			
			preparedStatement.setString(9, engineStock.getM7qty());
			
			preparedStatement.setString(10, engineStock.getM8qty());
			
			preparedStatement.setString(11, engineStock.getM9qty());
			
			preparedStatement.setString(12, engineStock.getM10qty());
			
			preparedStatement.setString(13, engineStock.getM11qty());
			preparedStatement.setString(14, engineStock.getM12qty());
			preparedStatement.setString(15, engineStock.getTotalFStock());
			preparedStatement.setString(16, engineStock.getTotalStock());
			
			
			preparedStatement.setString(17, engineStock.getMODIFIED_BY());
			preparedStatement.setString(18, engineStock.getMODIFIED_DATE());

			preparedStatement.setString(19, engineStock.getPROJECT_TYPE());
			preparedStatement.setString(20, engineStock.getENGINE_NAME());
			
			
			
			System.out.println(preparedStatement.toString());

			preparedStatement.executeUpdate();
			message = "Done";

		} catch (SQLException e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		return message;
	}

	public String updatePendingandTotalStock() {
		// TODO Auto-generated method stub
		String sql="";
		ResultSet rs,rsHeader;
		int sum;
		String leads;
		try {
			Statement statement = connection.createStatement();
			sql = "select PROJECT_TYPE,ENGINE from engine_forecast";
						
			 rs = statement.executeQuery(sql);

			while (rs.next()) {
				sum=0;
				leads="";
				
				String projectType=rs.getString("PROJECT_TYPE");
				String engine=rs.getString("ENGINE");
				
				sql="select lead_header.LEAD_ID,stg_mtr_customer.CUSTOMER_NAME,lead_header.PROJECT_TYPE, lead_header.LEAD_EXECUTION_DATE, lead_details.DTL_ENGINE_MAKE,lead_details.DTL_ENGINE_MODEL,lead_details.DTL_QTY from lead_header , lead_details,stg_mtr_customer where lead_header.LEAD_ID=lead_details.LEAD_ID "
						+ " and lead_header.LEAD_ID=stg_mtr_customer.LEAD_ID "
						+ " and lead_header.LEAD_BUDG=0  and substr(lead_header.LEAD_ID,3,2)='DG' and cast(lead_header.LEAD_EXECUTION_DATE as date) between curdate() and  cast(DATE_ADD(curdate(), INTERVAL 3 MONTH) as date)"
						+ " and lead_header.PROJECT_TYPE='"+projectType+"' and lead_details.DTL_ENGINE_MODEL='"+engine+"'";
				
				rsHeader = statement.executeQuery(sql);
				while(rsHeader.next())
				{
					String qty=rsHeader.getString("DTL_QTY");
					sum+=Integer.parseInt(qty);
					leads+=rsHeader.getString("CUSTOMER_NAME")+"-"+qty+",";
					
				}
				sql=" update engine_forecast set EXE_PENDING_ORDER='"+sum+"' , LEADS='"+leads+"' where PROJECT_TYPE='"+projectType+"' and ENGINE='"+engine+"'";
				statement.executeUpdate(sql);
				
			}
		}catch(Exception e)
		{
			
			}
		return null;
	}

}
