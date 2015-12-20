package com.lms.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.LeadHeaderDao;
import com.lms.daoImpl.LeadHeaderDaoImpl;

/**
 * Servlet implementation class GetCompetitiors
 */
public class GetCompetitiors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LeadHeaderDao leadHeaderDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCompetitiors() {
		super();
		leadHeaderDao = new LeadHeaderDaoImpl();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  
		  
		  List<String> compList = new ArrayList<String>();
		  String competition=request.getParameter("keyword");

		  compList=leadHeaderDao.getCompetitions("NO", competition);
		  Iterator it=compList.iterator();
		  response.setContentType("text/html");
		  PrintWriter out=response.getWriter();
		  
		  while(it.hasNext())
		  {
			  String sComp=it.next().toString();
			  out.print("<li onclick=set_item('"+sComp+"')>"+sComp+"</li>");
			  
		  }
			
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
