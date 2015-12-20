<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.lms.dao.*"%>
<%@page import="com.lms.daoImpl.*"%>
<%@page import="com.lms.model.*"%>


<%
	
CustomerDao ct=new CustomerDaoImpl();
Customer c=new Customer();

Iterator it=ct.getCustomerDetails().iterator();
String countries[]=new String[2000];
int j=0;
while(it.hasNext())
{
	
	c=(Customer)it.next();
	countries[j]="'"+c.getCUSTOMER_NAME()+"'";
	System.out.println(c.getCUSTOMER_NAME());
	j++;
}

	
	String query = (String)request.getParameter("q");
	//System.out.println("1"+request.getParameterNames().nextElement());
	response.setHeader("Content-Type", "text/html");
	int cnt=1;
	for(int i=0;i<countries.length;i++)
	{
		/*if(countries[i].toUpperCase().startsWith(query.toUpperCase()))
		{
			out.print(countries[i]+"\n");
			if(cnt>=10)
				break;
			cnt++;
		}*/
		out.print(countries[i].toUpperCase());
	}
%>