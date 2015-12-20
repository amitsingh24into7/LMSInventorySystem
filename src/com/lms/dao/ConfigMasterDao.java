package com.lms.dao;

import java.util.List;

import com.lms.model.ConfigMaster;

public interface ConfigMasterDao {
	public List<ConfigMaster> getConfigMasterData();
	public List<String>  getEngineModel(String engineType);
	public List<String>  getCity(String state);

	public String  getShortState(String state);
	public String  getDescription(String link1,String value);
	
	public List<ConfigMaster> getEngineDefaultValue(String engine);
	public List<ConfigMaster> getValueByName(String name);
	
	
	
	

}
