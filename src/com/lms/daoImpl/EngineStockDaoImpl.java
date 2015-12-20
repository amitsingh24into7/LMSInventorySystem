package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.EngineStockDao;
import com.lms.model.Comments;
import com.lms.model.EngineStock;
import com.lms.model.LeadHeader;
import com.lms.util.DbUtil;

public class EngineStockDaoImpl implements EngineStockDao {

	private Connection connection;

	public EngineStockDaoImpl() {
		connection = DbUtil.getDBConnection();
	}

	public String addStockBudget(EngineStock engineStock) {

		String message = "";

		@SuppressWarnings("unchecked")
		List<EngineStock> ls = (List<EngineStock>) getBudgetStock(
				engineStock.getREGION(), engineStock.getPROJECT_TYPE(),
				engineStock.getENGINE_NAME(), engineStock.getFIN_YEAR());
		if (ls.size() > 0) {
			engineStock.setMODIFIED_BY(engineStock.getCREATED_BY());
			engineStock.setMODIFIED_DATE(engineStock.getCREATED_DATE());
			message = updateStockBudget(engineStock);
		} else {
			try {
				String sql = " insert into engine_stock(REGION, PROJECT_TYPE, ENGINE_NAME,"
						+ "  FIN_YEAR, STOCK_AS_ON, TRANSIT, M1budget, M1budgetVALUE, M2budget, M2budgetVALUE, "
						+ "M3budget, M3budgetVALUE, M4budget, M4budgetVALUE, M5budget, M5budgetVALUE, M6budget, M6budgetVALUE, M7budget, M7budgetVALUE, M8budget, M8budgetVALUE, M9budget, M9budgetVALUE, M10budget, M10budgetVALUE, M11budget, M11budgetVALUE, M12budget, M12budgetVALUE,"
						+ "  CREATED_DATE, CREATED_BY) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);

				preparedStatement.setString(1, engineStock.getREGION());
				preparedStatement.setString(2, engineStock.getPROJECT_TYPE());
				preparedStatement.setString(3, engineStock.getENGINE_NAME());

				preparedStatement.setString(4, engineStock.getFIN_YEAR());
				preparedStatement.setString(5, engineStock.getSTOCK_AS_ON());
				preparedStatement.setString(6, engineStock.getTRANSIT());
				preparedStatement.setString(7, engineStock.getM1budget());
				preparedStatement.setString(8, engineStock.getM1budgetvalue());
				preparedStatement.setString(9, engineStock.getM2budget());
				preparedStatement.setString(10, engineStock.getM2budgetvalue());
				preparedStatement.setString(11, engineStock.getM3budget());
				preparedStatement.setString(12, engineStock.getM3budgetvalue());
				preparedStatement.setString(13, engineStock.getM4budget());
				preparedStatement.setString(14, engineStock.getM4budgetvalue());
				preparedStatement.setString(15, engineStock.getM5budget());
				preparedStatement.setString(16, engineStock.getM5budgetvalue());
				preparedStatement.setString(17, engineStock.getM6budget());
				preparedStatement.setString(18, engineStock.getM6budgetvalue());
				preparedStatement.setString(19, engineStock.getM7budget());
				preparedStatement.setString(20, engineStock.getM7budgetvalue());
				preparedStatement.setString(21, engineStock.getM8budget());
				preparedStatement.setString(22, engineStock.getM8budgetvalue());
				preparedStatement.setString(23, engineStock.getM9budget());
				preparedStatement.setString(24, engineStock.getM9budgetvalue());
				preparedStatement.setString(25, engineStock.getM10budget());
				preparedStatement
						.setString(26, engineStock.getM10budgetvalue());
				preparedStatement.setString(27, engineStock.getM11budget());
				preparedStatement
						.setString(28, engineStock.getM11budgetvalue());
				preparedStatement.setString(29, engineStock.getM12budget());
				preparedStatement
						.setString(30, engineStock.getM12budgetvalue());

				preparedStatement.setString(31, engineStock.getCREATED_DATE());
				preparedStatement.setString(32, engineStock.getCREATED_BY());

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

	public String updateStockActual(EngineStock engineStock, int month,
			String curQty, String curVal) {
		String message = "";
		String stockQty = getStockActual(engineStock, month, "Qty");
		String stockValue = getStockActual(engineStock, month, "Value");

		String totQty = (Double.parseDouble(curQty) + Double
				.parseDouble(stockQty)) + "";
		String totVal = (Double.parseDouble(curVal) + Double
				.parseDouble(stockValue)) + "";

		// +
		// " REGION=? and PROJECT_TYPE=? and ENGINE_NAME=? and RATING=? and FIN_YEAR=?";
		String region = engineStock.getREGION();
		String engine = engineStock.getENGINE_NAME();
		String project_type = engineStock.getPROJECT_TYPE();
		String rating = engineStock.getRATING();
		String finYear = engineStock.getFIN_YEAR();

		String sql = "update engine_stock set M" + month + "ACTUAL='" + totQty
				+ " , M" + month + "ACTUALVALUE='" + totVal + "'"
				+ " where REGION='" + region + "' and PROJECT_TYPE='"
				+ project_type + "' and ENGINE_NAME='" + engine
				+ "' and RATING='" + rating + "' and FIN_YEAR='" + finYear
				+ "'";

		try {

			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
			message = "Done";
		} catch (Exception e) {
			message = e.getMessage();
			e.printStackTrace();
		}

		return message;
	}

	public String getStockActual(EngineStock engineStock, int month, String type) {

		String stockVal = "";
		try {

			String fieldName = "";
			if (fieldName.equalsIgnoreCase("qty")) {

				fieldName = "M" + month + "ACTUAL";
			} else {
				fieldName = "M" + month + "ACTUALVALUE";
			}

			String sql = " select "
					+ fieldName
					+ " from engine_stock where "
					+ " REGION=? and PROJECT_TYPE=? and ENGINE_NAME=? and RATING=? and FIN_YEAR=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			preparedStatement.setString(1, engineStock.getREGION());
			preparedStatement.setString(2, engineStock.getPROJECT_TYPE());
			preparedStatement.setString(3, engineStock.getENGINE_NAME());
			preparedStatement.setString(4, engineStock.getRATING());
			preparedStatement.setString(5, engineStock.getFIN_YEAR());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				stockVal = rs.getString(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stockVal;
	}

	public List<EngineStock> getBudgetStock(String region, String projectType,
			String engineName, String financialYear) {
		List<EngineStock> engineStocks = new ArrayList<EngineStock>();
		try {
			Statement statement = connection.createStatement();
			String sql = "select * from engine_stock where FIN_YEAR='"
					+ financialYear + "'";

			if (!region.equalsIgnoreCase("")) {
				sql += " and REGION='" + region + "'";
			}

			if (!projectType.equalsIgnoreCase("")) {
				sql += " and PROJECT_TYPE='" + projectType + "'";
			}
			if (!engineName.equalsIgnoreCase("")) {
				sql += " and ENGINE_NAME='" + engineName + "'";
			}

			System.out.println("Budget Sql" + sql);
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				EngineStock engineStock = new EngineStock();

				engineStock.setREGION(rs.getString("rEGION"));
				engineStock.setPROJECT_TYPE(rs.getString("pROJECT_TYPE"));
				engineStock.setENGINE_NAME(rs.getString("eNGINE_NAME"));
				engineStock.setFIN_YEAR(rs.getString("fIN_YEAR"));

				engineStock.setSTOCK_AS_ON(rs.getString("sTOCK_AS_ON"));
				engineStock.setTRANSIT(rs.getString("tRANSIT"));
				
				engineStock.setM1budget(rs.getString("m1budget"));
				engineStock.setM2budget(rs.getString("m2budget"));
				engineStock.setM3budget(rs.getString("m3budget"));
				engineStock.setM4budget(rs.getString("m4budget"));
				engineStock.setM5budget(rs.getString("m5budget"));
				engineStock.setM6budget(rs.getString("m6budget"));
				engineStock.setM7budget(rs.getString("m7budget"));
				engineStock.setM8budget(rs.getString("m8budget"));
				engineStock.setM9budget(rs.getString("m9budget"));
				engineStock.setM10budget(rs.getString("m10budget"));
				engineStock.setM11budget(rs.getString("m11budget"));
				engineStock.setM12budget(rs.getString("m12budget"));

				engineStock.setM1budgetvalue(rs.getString("m1budgetvalue"));
				engineStock.setM2budgetvalue(rs.getString("m2budgetvalue"));
				engineStock.setM3budgetvalue(rs.getString("m3budgetvalue"));
				engineStock.setM4budgetvalue(rs.getString("m4budgetvalue"));
				engineStock.setM5budgetvalue(rs.getString("m5budgetvalue"));
				engineStock.setM6budgetvalue(rs.getString("m6budgetvalue"));
				engineStock.setM7budgetvalue(rs.getString("m7budgetvalue"));
				engineStock.setM8budgetvalue(rs.getString("m8budgetvalue"));
				engineStock.setM9budgetvalue(rs.getString("m9budgetvalue"));
				engineStock.setM10budgetvalue(rs.getString("m10budgetvalue"));
				engineStock.setM11budgetvalue(rs.getString("m11budgetvalue"));
				engineStock.setM12budgetvalue(rs.getString("m12budgetvalue"));

				engineStocks.add(engineStock);
				System.out.println("asd"+engineStock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return engineStocks;
	}

	public EngineStock searchBudgetStock(String region, String role,
			String projectType, String financialYear, String fromDate,
			String toDate) {

		return null;
	}

	public List<EngineStock> getAllEngineStock(String region,String projectType, String engineName,String financialYear) {
		List<EngineStock> engineStocks = new ArrayList<EngineStock>();
		try {
			Statement statement = connection.createStatement();
			String sql = "select * from engine_stock where FIN_YEAR='"
					+ financialYear + "'";

			if (!region.equalsIgnoreCase("")) {
				sql += " and REGION='" + region + "'";
			}

			if (!projectType.equalsIgnoreCase("")) {
				sql += " and PROJECT_TYPE='" + projectType + "'";
			}
			if (!engineName.equalsIgnoreCase("")) {
				sql += " and ENGINE_NAME='" + engineName + "'";
			}

			System.out.println("Budget Sql" + sql);
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				EngineStock engineStock = new EngineStock();

				engineStock.setREGION(rs.getString("rEGION"));
				engineStock.setPROJECT_TYPE(rs.getString("pROJECT_TYPE"));
				engineStock.setENGINE_NAME(rs.getString("eNGINE_NAME"));
				engineStock.setFIN_YEAR(rs.getString("fIN_YEAR"));

				engineStock.setSTOCK_AS_ON(rs.getString("sTOCK_AS_ON"));
				engineStock.setTRANSIT(rs.getString("tRANSIT"));
				
				engineStock.setM1budget(rs.getString("m1budget"));
				engineStock.setM2budget(rs.getString("m2budget"));
				engineStock.setM3budget(rs.getString("m3budget"));
				engineStock.setM4budget(rs.getString("m4budget"));
				engineStock.setM5budget(rs.getString("m5budget"));
				engineStock.setM6budget(rs.getString("m6budget"));
				engineStock.setM7budget(rs.getString("m7budget"));
				engineStock.setM8budget(rs.getString("m8budget"));
				engineStock.setM9budget(rs.getString("m9budget"));
				engineStock.setM10budget(rs.getString("m10budget"));
				engineStock.setM11budget(rs.getString("m11budget"));
				engineStock.setM12budget(rs.getString("m12budget"));

				engineStock.setM1actual(rs.getString("m1actual"));
				engineStock.setM2actual(rs.getString("m2actual"));
				engineStock.setM3actual(rs.getString("m3actual"));
				engineStock.setM4actual(rs.getString("m4actual"));
				engineStock.setM5actual(rs.getString("m5actual"));
				engineStock.setM6actual(rs.getString("m6actual"));
				engineStock.setM7actual(rs.getString("m7actual"));
				engineStock.setM8actual(rs.getString("m8actual"));
				engineStock.setM9actual(rs.getString("m9actual"));
				engineStock.setM10actual(rs.getString("m10actual"));
				engineStock.setM11actual(rs.getString("m11actual"));
				engineStock.setM12actual(rs.getString("m12actual"));

				engineStocks.add(engineStock);
				System.out.println("asd"+engineStock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return engineStocks;
	}

	public String updateStockBudget(EngineStock engineStock) {
		String message = "";
		try {
			String sql = "update engine_stock set "
					+ "  M1budget=?, M2budget=?, M3budget=?, M4budget=?, M5budget=?, M6budget=?, M7budget=?, M8budget=?, M9budget=?, M10budget=?, M11budget=?, M12budget=?"
					+ "  ,M1budgetVALUE=?, M2budgetVALUE=?, M3budgetVALUE=?, M4budgetVALUE=?, M5budgetVALUE=?, M6budgetVALUE=?, M7budgetVALUE=?, M8budgetVALUE=?, M9budgetVALUE=?, M10budgetVALUE=?, M11budgetVALUE=?, M12budgetVALUE=?"
					+ " , MODIFIED_DATE=?,MODIFIED_BY=? "
					+ "where REGION=? and PROJECT_TYPE=? and ENGINE_NAME=? and FIN_YEAR=?";

			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);

			preparedStatement.setString(1, engineStock.getM1budget());
			preparedStatement.setString(2, engineStock.getM2budget());
			preparedStatement.setString(3, engineStock.getM3budget());
			preparedStatement.setString(4, engineStock.getM4budget());
			preparedStatement.setString(5, engineStock.getM5budget());
			preparedStatement.setString(6, engineStock.getM6budget());
			preparedStatement.setString(7, engineStock.getM7budget());
			preparedStatement.setString(8, engineStock.getM8budget());

			preparedStatement.setString(9, engineStock.getM9budget());
			preparedStatement.setString(10, engineStock.getM10budget());
			preparedStatement.setString(11, engineStock.getM11budget());
			preparedStatement.setString(12, engineStock.getM12budget());

			preparedStatement.setString(13, engineStock.getM1budgetvalue());
			preparedStatement.setString(14, engineStock.getM2budgetvalue());
			preparedStatement.setString(15, engineStock.getM3budgetvalue());
			preparedStatement.setString(16, engineStock.getM4budgetvalue());
			preparedStatement.setString(17, engineStock.getM5budgetvalue());
			preparedStatement.setString(18, engineStock.getM6budgetvalue());
			preparedStatement.setString(19, engineStock.getM7budgetvalue());
			preparedStatement.setString(20, engineStock.getM8budgetvalue());

			preparedStatement.setString(21, engineStock.getM9budgetvalue());
			preparedStatement.setString(22, engineStock.getM10budgetvalue());
			preparedStatement.setString(23, engineStock.getM11budgetvalue());
			preparedStatement.setString(24, engineStock.getM12budgetvalue());

			preparedStatement.setString(25, engineStock.getMODIFIED_DATE());
			preparedStatement.setString(26, engineStock.getMODIFIED_BY());
			preparedStatement.setString(27, engineStock.getREGION());
			preparedStatement.setString(28, engineStock.getPROJECT_TYPE());
			preparedStatement.setString(29, engineStock.getENGINE_NAME());

			preparedStatement.setString(30, engineStock.getFIN_YEAR());

			System.out.println(preparedStatement.toString());

			preparedStatement.executeUpdate();
			message = "Done";

		} catch (SQLException e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		return message;
	}

}
