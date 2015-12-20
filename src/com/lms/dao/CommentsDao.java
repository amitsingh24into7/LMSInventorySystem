package com.lms.dao;

import java.util.List;

import com.lms.model.Comments;


public interface CommentsDao {
	public void addComments(Comments comments);
	public List<Comments> getCommentsByLeadID(String leadID);
	public void updateCommentsLeadID(String budleadID,String leadID,String createdDate,String createdBy);

}
