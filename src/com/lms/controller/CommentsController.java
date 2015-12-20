package com.lms.controller;

import java.io.IOException;
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
import com.lms.dao.CommentsDao;
import com.lms.daoImpl.CommentsDaoImpl;
import com.lms.model.Comments;
import com.lms.util.Utility;

/**
 * Servlet implementation class CommentsController
 */
public class CommentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentsDao commentsDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentsController() {
		super();
		commentsDao = new CommentsDaoImpl();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Comments comment = new Comments();

		String leadID = request.getParameter("leadID");
		String createdBy = request.getParameter("createdBy");
		String cmnts = request.getParameter("comments");
		String types = request.getParameter("types");
		String createdDate = Utility.getCurrentdate();

		comment.setLEAD_ID(leadID);
		comment.setDETAILS(cmnts);
		comment.setCREATED_BY(createdBy);
		comment.setCRETAED_DATE(createdDate);
		comment.setTYPES(types);
		System.out.println(comment.toString());
		commentsDao.addComments(comment);

		List<Comments> comments = new ArrayList<Comments>();

		comments = commentsDao.getCommentsByLeadID(leadID);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(comments,
				new TypeToken<List<Comments>>() {
				}.getType());

		JsonArray jsonArray = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
