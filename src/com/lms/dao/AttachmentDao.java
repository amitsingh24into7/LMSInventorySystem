
package com.lms.dao;

import java.util.List;

import com.lms.model.Attachment;


public interface AttachmentDao {
	public void addAttachments(Attachment attachments);
	public List<Attachment> getCommentsAttachmentsByLeadID(String leadID);
	public List<Attachment> getCostSheet(String leadID);
	public List<Attachment> getOtherAttachement(String leadID);
	public void updateAttachmentLeadID(String budleadID,String leadID,String createdDate,String createdBy);
	
}
