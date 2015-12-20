package com.lms.dao;

import java.util.List;

import com.lms.model.EngineStock;

public interface EngineStockDao {
	
public String addStockBudget(EngineStock engineStock);
public String updateStockBudget(EngineStock engineStock);
public String getStockActual(EngineStock engineStock,int month,String type);
public String updateStockActual(EngineStock engineStock,int month,String curQty,String curVal);
public List<EngineStock> getBudgetStock(String region,String projectType,String engineName,String financialYear);
public EngineStock searchBudgetStock(String region,String role,String projectType,String financialYear,String fromDate,String toDate);
public List<EngineStock> getAllEngineStock(String region,String projectType,String engineName,String financialYear);
}
