package com.lms.util;

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
import com.lms.dao.AttachmentDao;
import com.lms.daoImpl.AttachmentDaoImpl;
import com.lms.model.Attachment;

/**
 * Servlet implementation class GetAttachmentByLeadID
 */
public class GetAttachmentByLeadID extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AttachmentDao attachmentDao;
	//String cmnts="";
	String leadID="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAttachmentByLeadID() {
        super();
    	attachmentDao=new AttachmentDaoImpl();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Attachment> attachments = new ArrayList<Attachment>();
		leadID=request.getParameter("leadID");
		attachments = attachmentDao.getCommentsAttachmentsByLeadID(leadID);
		System.out.println("hey"+attachments);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(attachments,
				new TypeToken<List<Attachment>>() {
				}.getType());

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
