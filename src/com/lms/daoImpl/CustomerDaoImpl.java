package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.CustomerDao;
import com.lms.model.Customer;
import com.lms.util.DbUtil;

public class CustomerDaoImpl implements CustomerDao {

	
	private Connection connection;
	 
	public CustomerDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello"+connection);
	}
	
	public List<Customer> getAllCustomerNames() {
	
		List<Customer> cMasters = new ArrayList<Customer>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select distinct CUSTOMER_NAME,CONTACT_NUMBER,CUSTOMER_TEMP_ADDRESS,EMAIL_ADDRESS from stg_mtr_customer where LEAD_ID='NO'");
			while (rs.next()) {

				Customer cMaster = new Customer();
				cMaster.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
				cMaster.setCONTACT_NUMBER(rs.getString("cONTACT_NUMBER"));
				cMaster.setCUSTOMER_TEMP_ADDRESS(rs.getString("cUSTOMER_TEMP_ADDRESS"));
				cMaster.setEMAIL_ADDRESS(rs.getString("eMAIL_ADDRESS"));
				
				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}
	public List<Customer> getCustomerDetails() {
		// TODO Auto-generated method stub
		List<Customer> cMasters = new ArrayList<Customer>();
	
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from stg_mtr_customer");
			while (rs.next()) {

				Customer cMaster = new Customer();
				
				cMaster.setLEAD_ID(rs.getString("lEAD_ID"));
				cMaster.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
				cMaster.setCONTACT_NUMBER(rs.getString("cONTACT_NUMBER"));
				cMaster.setEMAIL_ADDRESS(rs.getString("eMAIL_ADDRESS"));
				cMaster.setALTERNATE_NO(rs.getString("aLTERNATE_NO"));
				cMaster.setCONSULTANT(rs.getString("cONSULTANT"));
				cMaster.setCONSULTANT_PERSON_NAME(rs.getString("cONSULTANT_PERSON_NAME"));
				cMaster.setCONSULTANT_CONTACT(rs.getString("cONSULTANT_CONTACT"));
				cMaster.setBILLING_ADDRESS1(rs.getString("bILLING_ADDRESS1"));
				cMaster.setBILLING_ADDRESS2(rs.getString("bILLING_ADDRESS2"));
				cMaster.setBILLING_ADDRESS3(rs.getString("bILLING_ADDRESS3"));
				cMaster.setBILLING_ADDRESS4(rs.getString("bILLING_ADDRESS4"));
				cMaster.setDELIVERY_ADDRESS1(rs.getString("dELIVERY_ADDRESS1"));
				cMaster.setDELIVERY_ADDRESS2(rs.getString("dELIVERY_ADDRESS2"));
				cMaster.setDELIVERY_ADDRESS2(rs.getString("dELIVERY_ADDRESS2"));
				cMaster.setDELIVERY_ADDRESS3(rs.getString("dELIVERY_ADDRESS3"));
				cMaster.setDELIVERY_ADDRESS4(rs.getString("dELIVERY_ADDRESS4"));
				cMaster.setCUSTOMER_GROUP(rs.getString("cUSTOMER_GROUP"));
				cMaster.setCUSTOMER_GROUP_CONTACT_PERSON(rs.getString("cUSTOMER_GROUP_CONTACT_PERSON"));
				cMaster.setCUSTOMER_GROUP_CONTACT_NUMBER(rs.getString("cUSTOMER_GROUP_CONTACT_NUMBER"));
				cMaster.setECCNO(rs.getString("eCCNO"));
				cMaster.setCSTNO(rs.getString("cSTNO"));
				cMaster.setTINNO(rs.getString("tINNO"));
				cMaster.setIECNO(rs.getString("iECNO"));
				cMaster.setPMC(rs.getString("pMC"));
				cMaster.setPMC_PERSON_NAME(rs.getString("pMC_PERSON_NAME"));
				cMaster.setPMC_CONTACT(rs.getString("pMC_CONTACT"));
				cMaster.setPMC_ALTERNATE_CONTACT(rs.getString("pMC_ALTERNATE_CONTACT"));
				cMaster.setPMC_EMAIL(rs.getString("pMC_EMAIL"));
				cMaster.setCREATED_BY(rs.getString("cREATED_BY"));
				cMaster.setCRETAED_DATE(rs.getString("cRETAED_DATE"));
				cMaster.setMODIFIED_BY(rs.getString("mODIFIED_BY"));
				cMaster.setMODIFIED_DATE(rs.getString("mODIFIED_DATE"));

				//cMaster
			//	cMaster.setPROJECT_EXE_PLACE(rs.getString("pROJECT_EXE_PLACE"));
				//cMaster.setCREATION_DATE(rs.getString("cREATION_DATE"));
				
				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}

	public void addMasterCustomer(Customer customer) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into stg_mtr_customer("
							+ "LEAD_ID, CUSTOMER_NAME,"
							+ " CONTACT_NUMBER,"
							+ " ALTERNATE_NO, EMAIL_ADDRESS,"
							+ " CONSULTANT, CONSULTANT_PERSON_NAME,"
							+ " CONSULTANT_CONTACT, BILLING_ADDRESS1,"
							+ " BILLING_ADDRESS2, BILLING_ADDRESS3,"
							+ " BILLING_ADDRESS4, DELIVERY_ADDRESS1,"
							+ " DELIVERY_ADDRESS2, DELIVERY_ADDRESS3,"
							+ " DELIVERY_ADDRESS4, CUSTOMER_GROUP,"
							+ " CUSTOMER_GROUP_CONTACT_PERSON,"
							+ " CUSTOMER_GROUP_CONTACT_NUMBER, ECCNO,"
							+ " CSTNO, TINNO,"
							+ " IECNO, PMC,PMC_PERSON_NAME, "
							+ "PMC_CONTACT, PMC_ALTERNATE_CONTACT,"
							+ " PMC_EMAIL,"
							+ " CREATED_BY, "
							+ "CRETAED_DATE,CUSTOMER_TEMP_ADDRESS)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?, ? ,?,?)");

			
			preparedStatement.setString(1, customer.getLEAD_ID());
			preparedStatement.setString(2, customer.getCUSTOMER_NAME());
			preparedStatement.setString(3, customer.getCONTACT_NUMBER());
			preparedStatement.setString(4, customer.getALTERNATE_NO());
			preparedStatement.setString(5, customer.getEMAIL_ADDRESS());
			preparedStatement.setString(6, customer.getCONSULTANT());
			preparedStatement.setString(7, customer.getCONSULTANT_PERSON_NAME());
			preparedStatement.setString(8, customer.getCONSULTANT_CONTACT());
			preparedStatement.setString(9, customer.getBILLING_ADDRESS1());
			preparedStatement.setString(10, customer.getBILLING_ADDRESS2());
			preparedStatement.setString(11, customer.getBILLING_ADDRESS3());
			preparedStatement.setString(12, customer.getBILLING_ADDRESS4());
			preparedStatement.setString(13, customer.getDELIVERY_ADDRESS1());
			preparedStatement.setString(14, customer.getDELIVERY_ADDRESS2());
			preparedStatement.setString(15, customer.getDELIVERY_ADDRESS3());
			preparedStatement.setString(16, customer.getDELIVERY_ADDRESS4());
			preparedStatement.setString(17, customer.getCUSTOMER_GROUP());
			preparedStatement.setString(18, customer.getCUSTOMER_GROUP_CONTACT_PERSON());
			preparedStatement.setString(19, customer.getCUSTOMER_GROUP_CONTACT_NUMBER());
			preparedStatement.setString(20, customer.getECCNO());
			preparedStatement.setString(21, customer.getCSTNO());
			preparedStatement.setString(22, customer.getTINNO());
			preparedStatement.setString(23, customer.getIECNO());
			preparedStatement.setString(24, customer.getPMC());
			preparedStatement.setString(25, customer.getPMC_PERSON_NAME());
			preparedStatement.setString(26, customer.getPMC_CONTACT());
			preparedStatement.setString(27, customer.getPMC_ALTERNATE_CONTACT());
			preparedStatement.setString(28, customer.getPMC_EMAIL());
			preparedStatement.setString(29, customer.getCREATED_BY());
			preparedStatement.setString(30, customer.getCRETAED_DATE());
			preparedStatement.setString(31, customer.getCUSTOMER_TEMP_ADDRESS());
			
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		List<Customer> ls=getCustomerDetailsByLeadID(customer.getLEAD_ID());
		if(ls.size()>0)
		{
			customer.setMODIFIED_BY(customer.getCREATED_BY());
			customer.setMODIFIED_DATE(customer.getCRETAED_DATE());
			updateCustomer(customer);
		}
		else
		{
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into stg_mtr_customer("
							+ "LEAD_ID, CUSTOMER_NAME,"
							+ " CONTACT_NUMBER,"
							+ " ALTERNATE_NO, EMAIL_ADDRESS,"
							+ " CONSULTANT, CONSULTANT_PERSON_NAME,"
							+ " CONSULTANT_CONTACT, BILLING_ADDRESS1,"
							+ " BILLING_ADDRESS2, BILLING_ADDRESS3,"
							+ " BILLING_ADDRESS4, DELIVERY_ADDRESS1,"
							+ " DELIVERY_ADDRESS2, DELIVERY_ADDRESS3,"
							+ " DELIVERY_ADDRESS4, CUSTOMER_GROUP,"
							+ " CUSTOMER_GROUP_CONTACT_PERSON,"
							+ " CUSTOMER_GROUP_CONTACT_NUMBER, ECCNO,"
							+ " CSTNO, TINNO,"
							+ " IECNO, PMC,PMC_PERSON_NAME, "
							+ "PMC_CONTACT, PMC_ALTERNATE_CONTACT,"
							+ " PMC_EMAIL,"
							+ " CREATED_BY, "
							+ "CRETAED_DATE,CUSTOMER_TEMP_ADDRESS)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?, ?,?, ? ,?,?)");

			
			preparedStatement.setString(1, customer.getLEAD_ID());
			preparedStatement.setString(2, customer.getCUSTOMER_NAME());
			preparedStatement.setString(3, customer.getCONTACT_NUMBER());
			preparedStatement.setString(4, customer.getALTERNATE_NO());
			preparedStatement.setString(5, customer.getEMAIL_ADDRESS());
			preparedStatement.setString(6, customer.getCONSULTANT());
			preparedStatement.setString(7, customer.getCONSULTANT_PERSON_NAME());
			preparedStatement.setString(8, customer.getCONSULTANT_CONTACT());
			preparedStatement.setString(9, customer.getBILLING_ADDRESS1());
			preparedStatement.setString(10, customer.getBILLING_ADDRESS2());
			preparedStatement.setString(11, customer.getBILLING_ADDRESS3());
			preparedStatement.setString(12, customer.getBILLING_ADDRESS4());
			preparedStatement.setString(13, customer.getDELIVERY_ADDRESS1());
			preparedStatement.setString(14, customer.getDELIVERY_ADDRESS2());
			preparedStatement.setString(15, customer.getDELIVERY_ADDRESS3());
			preparedStatement.setString(16, customer.getDELIVERY_ADDRESS4());
			preparedStatement.setString(17, customer.getCUSTOMER_GROUP());
			preparedStatement.setString(18, customer.getCUSTOMER_GROUP_CONTACT_PERSON());
			preparedStatement.setString(19, customer.getCUSTOMER_GROUP_CONTACT_NUMBER());
			preparedStatement.setString(20, customer.getECCNO());
			preparedStatement.setString(21, customer.getCSTNO());
			preparedStatement.setString(22, customer.getTINNO());
			preparedStatement.setString(23, customer.getIECNO());
			preparedStatement.setString(24, customer.getPMC());
			preparedStatement.setString(25, customer.getPMC_PERSON_NAME());
			preparedStatement.setString(26, customer.getPMC_CONTACT());
			preparedStatement.setString(27, customer.getPMC_ALTERNATE_CONTACT());
			preparedStatement.setString(28, customer.getPMC_EMAIL());
			preparedStatement.setString(29, customer.getCREATED_BY());
			preparedStatement.setString(30, customer.getCRETAED_DATE());
			preparedStatement.setString(31, customer.getCUSTOMER_TEMP_ADDRESS());
			
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}

	public List<Customer> getCustomerDetailsByLeadID(String leadID) {
		List<Customer> cMasters = new ArrayList<Customer>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from stg_mtr_customer where LEAD_ID='"+leadID+"'");
			while (rs.next()) {
				Customer cMaster = new Customer();
				
				cMaster.setLEAD_ID(rs.getString("lEAD_ID"));
				cMaster.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
				cMaster.setCONTACT_NUMBER(rs.getString("cONTACT_NUMBER"));
				cMaster.setCUSTOMER_TEMP_ADDRESS(rs.getString("cUSTOMER_TEMP_ADDRESS"));
				cMaster.setEMAIL_ADDRESS(rs.getString("eMAIL_ADDRESS"));
				cMaster.setALTERNATE_NO(rs.getString("aLTERNATE_NO"));
				cMaster.setCONSULTANT(rs.getString("cONSULTANT"));
				cMaster.setCONSULTANT_PERSON_NAME(rs.getString("cONSULTANT_PERSON_NAME"));
				cMaster.setCONSULTANT_CONTACT(rs.getString("cONSULTANT_CONTACT"));
				cMaster.setBILLING_ADDRESS1(rs.getString("bILLING_ADDRESS1"));
				cMaster.setBILLING_ADDRESS2(rs.getString("bILLING_ADDRESS2"));
				cMaster.setBILLING_ADDRESS3(rs.getString("bILLING_ADDRESS3"));
				cMaster.setBILLING_ADDRESS4(rs.getString("bILLING_ADDRESS4"));
				cMaster.setDELIVERY_ADDRESS1(rs.getString("dELIVERY_ADDRESS1"));
				cMaster.setDELIVERY_ADDRESS2(rs.getString("dELIVERY_ADDRESS2"));
				cMaster.setDELIVERY_ADDRESS2(rs.getString("dELIVERY_ADDRESS2"));
				cMaster.setDELIVERY_ADDRESS3(rs.getString("dELIVERY_ADDRESS3"));
				cMaster.setDELIVERY_ADDRESS4(rs.getString("dELIVERY_ADDRESS4"));
				cMaster.setCUSTOMER_GROUP(rs.getString("cUSTOMER_GROUP"));
				cMaster.setCUSTOMER_GROUP_CONTACT_PERSON(rs.getString("cUSTOMER_GROUP_CONTACT_PERSON"));
				cMaster.setCUSTOMER_GROUP_CONTACT_NUMBER(rs.getString("cUSTOMER_GROUP_CONTACT_NUMBER"));
				cMaster.setECCNO(rs.getString("eCCNO"));
				cMaster.setCSTNO(rs.getString("cSTNO"));
				cMaster.setTINNO(rs.getString("tINNO"));
				cMaster.setIECNO(rs.getString("iECNO"));
				cMaster.setPMC(rs.getString("pMC"));
				cMaster.setPMC_PERSON_NAME(rs.getString("pMC_PERSON_NAME"));
				cMaster.setPMC_CONTACT(rs.getString("pMC_CONTACT"));
				cMaster.setPMC_ALTERNATE_CONTACT(rs.getString("pMC_ALTERNATE_CONTACT"));
				cMaster.setPMC_EMAIL(rs.getString("pMC_EMAIL"));
				cMaster.setCREATED_BY(rs.getString("cREATED_BY"));
				cMaster.setCRETAED_DATE(rs.getString("cRETAED_DATE"));
				cMaster.setMODIFIED_BY(rs.getString("mODIFIED_BY"));
				cMaster.setMODIFIED_DATE(rs.getString("mODIFIED_DATE"));
				//cMaster.setCu

				//cMaster
			//	cMaster.setPROJECT_EXE_PLACE(rs.getString("pROJECT_EXE_PLACE"));
				//cMaster.setCREATION_DATE(rs.getString("cREATION_DATE"));
				
				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}
	public List<Customer> getCustomerDetailsByCustomerName(String CUSTOMER_NAME) {
		List<Customer> cMasters = new ArrayList<Customer>();
		
		try {
			Statement statement = connection.createStatement();
			String sql="select * from stg_mtr_customer where LEAD_ID='NO' and CUSTOMER_NAME='"+CUSTOMER_NAME+"'";
			System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Customer cMaster = new Customer();
				
				cMaster.setLEAD_ID(rs.getString("lEAD_ID"));
				cMaster.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
				cMaster.setCONTACT_NUMBER(rs.getString("cONTACT_NUMBER"));
				cMaster.setEMAIL_ADDRESS(rs.getString("eMAIL_ADDRESS"));
				cMaster.setALTERNATE_NO(rs.getString("aLTERNATE_NO"));
				cMaster.setCONSULTANT(rs.getString("cONSULTANT"));
				cMaster.setCONSULTANT_PERSON_NAME(rs.getString("cONSULTANT_PERSON_NAME"));
				cMaster.setCONSULTANT_CONTACT(rs.getString("cONSULTANT_CONTACT"));
				cMaster.setBILLING_ADDRESS1(rs.getString("bILLING_ADDRESS1"));
				cMaster.setBILLING_ADDRESS2(rs.getString("bILLING_ADDRESS2"));
				cMaster.setBILLING_ADDRESS3(rs.getString("bILLING_ADDRESS3"));
				cMaster.setBILLING_ADDRESS4(rs.getString("bILLING_ADDRESS4"));
				cMaster.setDELIVERY_ADDRESS1(rs.getString("dELIVERY_ADDRESS1"));
				cMaster.setDELIVERY_ADDRESS2(rs.getString("dELIVERY_ADDRESS2"));
				cMaster.setDELIVERY_ADDRESS2(rs.getString("dELIVERY_ADDRESS2"));
				cMaster.setDELIVERY_ADDRESS3(rs.getString("dELIVERY_ADDRESS3"));
				cMaster.setDELIVERY_ADDRESS4(rs.getString("dELIVERY_ADDRESS4"));
				cMaster.setCUSTOMER_GROUP(rs.getString("cUSTOMER_GROUP"));
				cMaster.setCUSTOMER_GROUP_CONTACT_PERSON(rs.getString("cUSTOMER_GROUP_CONTACT_PERSON"));
				cMaster.setCUSTOMER_GROUP_CONTACT_NUMBER(rs.getString("cUSTOMER_GROUP_CONTACT_NUMBER"));
				cMaster.setECCNO(rs.getString("eCCNO"));
				cMaster.setCSTNO(rs.getString("cSTNO"));
				cMaster.setTINNO(rs.getString("tINNO"));
				cMaster.setIECNO(rs.getString("iECNO"));
				cMaster.setPMC(rs.getString("pMC"));
				cMaster.setPMC_PERSON_NAME(rs.getString("pMC_PERSON_NAME"));
				cMaster.setPMC_CONTACT(rs.getString("pMC_CONTACT"));
				cMaster.setPMC_ALTERNATE_CONTACT(rs.getString("pMC_ALTERNATE_CONTACT"));
				cMaster.setPMC_EMAIL(rs.getString("pMC_EMAIL"));
				cMaster.setCREATED_BY(rs.getString("cREATED_BY"));
				cMaster.setCRETAED_DATE(rs.getString("cRETAED_DATE"));
				cMaster.setMODIFIED_BY(rs.getString("mODIFIED_BY"));
				cMaster.setMODIFIED_DATE(rs.getString("mODIFIED_DATE"));

				//cMaster
			//	cMaster.setPROJECT_EXE_PLACE(rs.getString("pROJECT_EXE_PLACE"));
				//cMaster.setCREATION_DATE(rs.getString("cREATION_DATE"));
				
				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}
	public List<Customer> getCustomerNames(String CUSTOMER_NAME) {
		List<Customer> cMasters = new ArrayList<Customer>();
				
				try {
					Statement statement = connection.createStatement();
					String sql="select * from stg_mtr_customer where LEAD_ID='NO' and CUSTOMER_NAME like'"+CUSTOMER_NAME+"%'";
					System.out.println(sql);
					ResultSet rs = statement.executeQuery(sql);
					while (rs.next()) {
						Customer cMaster = new Customer();
						
						
						cMaster.setCUSTOMER_NAME(rs.getString("cUSTOMER_NAME"));
												
						cMasters.add(cMaster);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return cMasters;
			}
	public void updateCustomer(Customer customer) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update stg_mtr_customer set "
							+ "CUSTOMER_NAME=?,"
							+ " CONTACT_NUMBER=?,"
							+ " ALTERNATE_NO=?, EMAIL_ADDRESS=?,"
							+ " CONSULTANT=?, CONSULTANT_PERSON_NAME=?,"
							+ " CONSULTANT_CONTACT=?, BILLING_ADDRESS1=?,"
							+ " BILLING_ADDRESS2=?, BILLING_ADDRESS3=?,"
							+ " BILLING_ADDRESS4=?, DELIVERY_ADDRESS1=?,"
							+ " DELIVERY_ADDRESS2=?, DELIVERY_ADDRESS3=?,"
							+ " DELIVERY_ADDRESS4=?, CUSTOMER_GROUP=?,"
							+ " CUSTOMER_GROUP_CONTACT_PERSON=?,"
							+ " CUSTOMER_GROUP_CONTACT_NUMBER=?, ECCNO=?,"
							+ " CSTNO=?, TINNO=?,"
							+ " IECNO=?, PMC=?,PMC_PERSON_NAME=?, "
							+ "PMC_CONTACT=?, PMC_ALTERNATE_CONTACT=?,"
							+ " PMC_EMAIL=?,"
							+ " MODIFIED_BY=?, "
							+ "MODIFIED_DATE=?,"
							+ "CUSTOMER_TEMP_ADDRESS=?"
							+ " where LEAD_ID=?");

			
			
			preparedStatement.setString(1, customer.getCUSTOMER_NAME());
			preparedStatement.setString(2, customer.getCONTACT_NUMBER());
			preparedStatement.setString(3, customer.getALTERNATE_NO());
			preparedStatement.setString(4, customer.getEMAIL_ADDRESS());
			preparedStatement.setString(5, customer.getCONSULTANT());
			preparedStatement.setString(6, customer.getCONSULTANT_PERSON_NAME());
			preparedStatement.setString(7, customer.getCONSULTANT_CONTACT());
			preparedStatement.setString(8, customer.getBILLING_ADDRESS1());
			preparedStatement.setString(9, customer.getBILLING_ADDRESS2());
			preparedStatement.setString(10, customer.getBILLING_ADDRESS3());
			preparedStatement.setString(11, customer.getBILLING_ADDRESS4());
			preparedStatement.setString(12, customer.getDELIVERY_ADDRESS1());
			preparedStatement.setString(13, customer.getDELIVERY_ADDRESS2());
			preparedStatement.setString(14, customer.getDELIVERY_ADDRESS3());
			preparedStatement.setString(15, customer.getDELIVERY_ADDRESS4());
			preparedStatement.setString(16, customer.getCUSTOMER_GROUP());
			preparedStatement.setString(17, customer.getCUSTOMER_GROUP_CONTACT_PERSON());
			preparedStatement.setString(18, customer.getCUSTOMER_GROUP_CONTACT_NUMBER());
			preparedStatement.setString(19, customer.getECCNO());
			preparedStatement.setString(20, customer.getCSTNO());
			preparedStatement.setString(21, customer.getTINNO());
			preparedStatement.setString(22, customer.getIECNO());
			preparedStatement.setString(23, customer.getPMC());
			preparedStatement.setString(24, customer.getPMC_PERSON_NAME());
			preparedStatement.setString(25, customer.getPMC_CONTACT());
			preparedStatement.setString(26, customer.getPMC_ALTERNATE_CONTACT());
			preparedStatement.setString(27, customer.getPMC_EMAIL());
			preparedStatement.setString(28, customer.getMODIFIED_BY());
			preparedStatement.setString(29, customer.getMODIFIED_DATE());
			preparedStatement.setString(30, customer.getCUSTOMER_TEMP_ADDRESS());
			preparedStatement.setString(31, customer.getLEAD_ID());
			
			
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void updateCustomerLeadID(String budleadID,String leadID,String createdDate,String createdBy) {
		try {
			// ID, DETAILS, CREATED_BY, CRETAED_DATE, LEAD_ID
			PreparedStatement preparedStatement = connection
					.prepareStatement("update stg_mtr_customer set LEAD_ID=?,MODIFIED_BY=?,MODIFIED_DATE=? where LEAD_ID=?");

			preparedStatement.setString(1, leadID);
			preparedStatement.setString(2, createdBy);
			preparedStatement.setString(3, createdDate);
			preparedStatement.setString(4, budleadID);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	
}
