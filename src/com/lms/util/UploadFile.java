package com.lms.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lms.dao.AttachmentDao;
import com.lms.daoImpl.AttachmentDaoImpl;
import com.lms.model.Attachment;
import com.lms.model.Comments;

public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "D:/MuraaiProject/sterling/docs/upload/";
//	private CommentsDao commentsDao;
	private AttachmentDao attachmentDao;
	//String cmnts="";
	String leadID="";
	File uploadedFile;
	
	public UploadFile()
	{
		//commentsDao=new CommentsDaoImpl();
		attachmentDao=new AttachmentDaoImpl();
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	// process only if its multipart content
	String comments=request.getParameter("comments");
	String createdBy="";
	String createdDate=Utility.getCurrentdate();
	
	String fileName="";
	Attachment attachment=new Attachment();
	Comments comment=new Comments();
	
	if (isMultipart) {
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// Parse the request
			List<FileItem> multiparts = upload.parseRequest(request);

			for (FileItem item : multiparts) {
				if (!item.isFormField()) {
					
					 fileName = item.getName();
                     fileName=Math.random()+fileName;
                     String root = getServletContext().getRealPath("/");
                     File path = new File(root + "/uploads");
                     if (!path.exists()) {
							boolean status = path.mkdirs();
                     }

                     uploadedFile = new File(path + "/" + fileName);
                     System.out.println(uploadedFile);
                     item.write(uploadedFile);
                     
                     
				}
				else if (item.isFormField()) {
				    String name = item.getFieldName();
				    String value = item.getString();
				   
				    if(name.equalsIgnoreCase("leadID"))
				    {
				    	leadID=item.getString();
				    }
				    if(name.equalsIgnoreCase("createdBy"))
				    {
				    	createdBy=item.getString();
				    }
				    
				    
				}else{}
				
				
				
			}
			/*comment.setLEAD_ID(leadID);
			comment.setDETAILS(cmnts);
			comment.setCREATED_BY(createdBy);
			comment.setCRETAED_DATE(createdDate);
			System.out.println(comment.toString());
			commentsDao.addComments(comment);
			*/
			
			attachment.setDOC_NAME(fileName);
			attachment.setDOC_URL("uploads/"+fileName);
			attachment.setDOC_CONTENT_TYPE("");
			attachment.setDOC_CREATED_BY(createdBy);
			attachment.setLEAD_ID(leadID);
			attachment.setDOC_VERSION("1.0");
			attachment.setDOC_CREATED_DATE(createdDate);
			attachment.setDOC_TYPE("COMMENTS");
			System.out.println(attachment.toString());
			
			attachmentDao.addAttachments(attachment);
			
			/*List<Attachment> attachments = new ArrayList<Attachment>();

			attachments = attachmentDao.getAttachmentsByLeadID(leadID);
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(attachments,
					new TypeToken<List<Attachment>>() {
					}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			*/
			
			
			
		} 
		catch (Exception e) 
		{
		  System.out.println("File upload failed");
		}
	}
}
}