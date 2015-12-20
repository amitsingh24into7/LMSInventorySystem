package com.lms.util;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.lms.dao.ConfigMasterDao;
import com.lms.daoImpl.ConfigMasterDaoImpl;

/**
 * Servlet implementation class GetEngineModelDetails
 */
public class GetEngineModelDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private ConfigMasterDao configMasterDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEngineModelDetails() {
        super();
        
        connection = DbUtil.getDBConnection();
        configMasterDao=new ConfigMasterDaoImpl();
		System.out.println("Hello"+connection);
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<String> cmtr=new ArrayList<String>();
		  String ENGINE_TYPE=request.getParameter("ENGINE_TYPE");
		  
		  cmtr=configMasterDao.getEngineModel(ENGINE_TYPE);
		  Gson gson = new Gson();
		  JsonElement element = gson.toJsonTree(cmtr, new TypeToken<List<String>>() {}.getType());

		  JsonArray jsonArray = element.getAsJsonArray();
		  response.setContentType("application/json");
		  response.getWriter().print(jsonArray);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
