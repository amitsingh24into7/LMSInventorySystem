package com.lms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.LeadHeaderDao;
import com.lms.dao.UserDao;
import com.lms.dao.UserRoleDao;
import com.lms.daoImpl.LeadHeaderDaoImpl;
import com.lms.daoImpl.UserDaoImpl;
import com.lms.daoImpl.UserRoleDaoImpl;
import com.lms.model.UserRole;

public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/viewlead.jsp";
    private static String PROFILE_USER = "/profile.jsp";
    
    private UserDao dao;
    private LeadHeaderDao leadheaderdao;
    private UserRoleDao userRoleDao;

    public UserController() {
        super();
        dao = new UserDaoImpl();
        leadheaderdao=new LeadHeaderDaoImpl();
        userRoleDao=new UserRoleDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
      

        if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("userId"));
           // dao.deleteUser(userId);
            forward = LIST_USER;
           // request.setAttribute("users", dao.getAllUsers());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
           // User user = dao.getUserById(userId);
            request.setAttribute("user", "user");
        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USER;
            request.setAttribute("leaddetails1","");
          //  request.setAttribute("leaddetails1", leadheaderdao.getLeadHeadDataDetails("Pending"));
         
        }
        else if (action.equalsIgnoreCase("search")){
        	
            forward = LIST_USER;
            String fromDate = request.getParameter("from_date");
            String toDate = request.getParameter("to_date");
            System.out.println("Hello India1");
            request.setAttribute("from_date", fromDate);
            request.setAttribute("to_date", toDate);
           
         
        }
        else if (action.equalsIgnoreCase("profile")){
        	
            forward = PROFILE_USER;
            String userid = request.getParameter("userid");
            List<UserRole> ls=userRoleDao.getDefaultUserOtherDetails(userid);
            request.setAttribute("userlist", ls);
            
           
         
        }
        else {
        	
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	/*User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        try {
            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
            user.setDob(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(request.getParameter("email"));
        String userid = request.getParameter("userid");
        if(userid == null || userid.isEmpty())
        {
            dao.addUser(user);
        }
        else
        {
            user.setUserid(Integer.parseInt(userid));
            dao.updateUser(user);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("users", dao.getAllUsers());
        view.forward(request, response);*/
    }
}