package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import com.lms.dao.ConfigMasterDao;
import com.lms.model.ConfigMaster;
import com.lms.util.DbUtil;

public class ConfigMasterDaoImpl implements ConfigMasterDao {

private Connection connection;
	
	//DbUtil dbutil=new DbUtil(); 
	public ConfigMasterDaoImpl() {
		connection = DbUtil.getDBConnection();
		System.out.println("Hello"+connection);
	}
	public List<ConfigMaster> getConfigMasterData() {
		// TODO Auto-generated method stub
		List<ConfigMaster> cMasters = new ArrayList<ConfigMaster>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from config_master");
			while (rs.next()) {
				ConfigMaster cMaster = new ConfigMaster();
				cMaster.setId(rs.getInt("id"));
				cMaster.setName(rs.getString("name"));
				cMaster.setValue(rs.getString("value"));
				cMaster.setLink1(rs.getString("link1"));
				cMaster.setLink2(rs.getString("link2"));
				cMaster.setCreate_by(rs.getString("created_by"));
				cMaster.setCreated_date(rs.getDate("created_date"));
				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}
	public List<String> getEngineModel(String engineType) {
		// TODO Auto-generated method stub
		//List<ConfigMaster> cMasters = new ArrayList<ConfigMaster>();
		Set set=new HashSet();
		List ls=new LinkedList();
		Statement statement=null;
		ResultSet rs=null;
		String engineName="";
		try {
			statement = connection.createStatement();
			String sql1="select LINK2 from config_master where VALUE='"+engineType+"'";
			System.out.println(sql1);
			rs = statement.executeQuery(sql1);
			
			while (rs.next()) {
				set.add("'"+rs.getString("LINK2")+"'");
				
			}
			System.out.println(set);
			//System.out.println(set);
			String s=set.toString();
			String t=s.replace("[", "");
			String p=t.replace("]", "");
			
			String sql="";
			//if(engineType.equalsIgnoreCase("PANEL_TYPE"))
		//	{
			//	sql="select concat(LINK1,'--',VALUE,IF(LINK2='', '','--'),LINK2) as modelno from config_master where NAME='PANELRATING' and  LINK1 in ("+p+")  order by LINK1 desc";
			//}
			//else
			//{
				sql="select concat(LINK1,'--',VALUE,IF(ENGINE_SERIES='', '','--'),ENGINE_SERIES) as modelno from config_master where NAME='RATING' and  LINK1 in ("+p+")  order by LINK1 desc";
			//}
			System.out.println(sql);
			rs = statement.executeQuery(sql);
			while (rs.next()) {
								
				ls.add(rs.getString("modelno"));
			}
			//System.out.println(p);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ls;
	}

	public List<String> getCity(String state) {
		// TODO Auto-generated method stub
		//List<ConfigMaster> cMasters = new ArrayList<ConfigMaster>();
		Set set=new HashSet();
		List ls=new LinkedList();
		Statement statement=null;
		ResultSet rs=null;
		String engineName="";
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select VALUE from config_master where NAME='EXE_CITY' and LINK1='"+state+"'");
			
			while (rs.next()) {
								
				ls.add(rs.getString("VALUE"));
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ls;
	}
	public String getShortState(String state) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				//List<ConfigMaster> cMasters = new ArrayList<ConfigMaster>();
				
				Statement statement=null;
				ResultSet rs=null;
				String shortState="";
				try {
					statement = connection.createStatement();
					String sql="select LINK2 from config_master where NAME='EXE_STATE' and VALUE='"+state+"'";
					rs = statement.executeQuery(sql);
					
					while (rs.next()) {
										
						shortState=rs.getString("LINK2");
					}

					
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return shortState;
	}
	public String  getDescription(String link1,String value)
	{
		Statement statement=null;
		ResultSet rs=null;
		String link2="";
		try {
			statement = connection.createStatement();
			String sql="select LINK2 from config_master where NAME='"+link1+"' and VALUE='"+value+"'";
			rs = statement.executeQuery(sql);
			
			while (rs.next()) {
								
				link2=rs.getString("LINK2");
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return link2;
	}
	public List<ConfigMaster> getEngineDefaultValue(String engine)
	{
		List<ConfigMaster>  lsConfig=new ArrayList<ConfigMaster>();
		ConfigMaster cMaster=new ConfigMaster();
		StringTokenizer st=new StringTokenizer(engine,"--");
		int totEle=st.countTokens();
		String rating = "";
		String engineModel="";
		String modelSeries="";
		int i=0;
	     while (st.hasMoreTokens()) { 
	    	 if(i==0)
	    	 {
	    		 engineModel=st.nextToken();
	    	 }
	    	 if(i==1)
	    	 {
	    		 rating=st.nextToken();
	    	 }
	    	 if(i==2)
	    	 {
		    	 if(totEle==3)
		    	 {
			    	
			    		 modelSeries=st.nextToken();
			     }
	    	 }
	    	 i++;
	     } 
	     System.out.println(rating);
	     System.out.println(engineModel);
	     System.out.println(modelSeries);
	     
		Statement statement=null;
		ResultSet rs=null;
		String link2="";
		try {
			statement = connection.createStatement();
			String sql="select * from config_master where NAME='RATING' and VALUE='"+rating+"' and LINK1='"+engineModel+"'";
			if(!modelSeries.equalsIgnoreCase(""))
			{
				sql+=" and ENGINE_SERIES='"+modelSeries+"'";
			}
			System.out.println(sql);
			rs = statement.executeQuery(sql);
			
			while (rs.next()) {
								
				cMaster.setDEFAULT_ALTERNATORE_MODEL(rs.getString("DEFAULT_ALTERNATOR_MODEL"));
				cMaster.setDEFAULT_COOLING_SYSTEM(rs.getString("DEFAULT_COOLING_SYSTEM"));
				cMaster.setDEFAULT_HZ(rs.getString("DEFAULT_HZ"));
				cMaster.setDEFAULT_VOLTAGE(rs.getString("DEFAULT_VOLTAGE"));
				cMaster.setDEFAULT_ALTERNATOR_MAKE(rs.getString("dEFAULT_ALTERNATOR_MAKE"));
				cMaster.setDG_MODEL(rs.getString("dG_MODEL"));
				cMaster.setENGINE_MODEL(rs.getString("eNGINE_MODEL"));
				cMaster.setENGINE_SERIES(rs.getString("eNGINE_SERIES"));
				
				lsConfig.add(cMaster);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
		return lsConfig;
	}
	public List<ConfigMaster> getValueByName(String name)
	{
		List<ConfigMaster> cMasters = new ArrayList<ConfigMaster>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from config_master where NAME='"+name+"'");
			while (rs.next()) {
				ConfigMaster cMaster = new ConfigMaster();
				cMaster.setId(rs.getInt("id"));
				cMaster.setName(rs.getString("name"));
				cMaster.setValue(rs.getString("value"));
				cMaster.setLink1(rs.getString("link1"));
				cMaster.setLink2(rs.getString("link2"));
				cMaster.setCreate_by(rs.getString("created_by"));
				cMaster.setCreated_date(rs.getDate("created_date"));
				cMasters.add(cMaster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cMasters;
	}
	
}
