<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script>
function enableSample() {
	document.getElementById('region').disabled=false;
	document.getElementById('state').disabled=false;
	document.getElementById('city').disabled=false;
	document.getElementById('user').disabled=false;
	document.getElementById('from_date').disabled=false;
}
function getXMLHttpRequestObject()
{
  var xmlhttp;
  /*@cc_on
  @if (@_jscript_version >= 5)
    try {
      xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
      try {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (E) {
        xmlhttp = false;
      }
    }
  @else
  xmlhttp = false;
  @end @*/
  if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
    try {
      xmlhttp = new XMLHttpRequest();
    } catch (e) {
      xmlhttp = false;
    }
  }
  return xmlhttp;
}

function converToLead(str)
{
	
	var http = new getXMLHttpRequestObject();
	var url = "ConvertBudgetToLead";
	
	var parameters = "budgetLead="+str;
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	        alert("Your budget lead - "+str +" has converted to lead No - "+http.responseText);
	        
	        var s=location.href.split("#")[0];
	        window.location=s;
	      
			
	    }
	}
	http.send(null);
}
</script>
<div id="page-wrapper">
            
 <div class="row" >
			<div class="col-lg-12" style="margin-top:5px">
	           <ol class="breadcrumb">
				 <li><a href="index.jsp"><i class="fa fa-home fa-fw"></i></a></li>
				  <li class="active">View Budget Leads</li>
				  
				</ol>
			</div>
		</div>
            <!-- /.row -->
			<div class="row">
			<div class="col-lg-12">
				 <div class="panel panel-primary">
						
						<a data-toggle="collapse" href="#collapse1" style="text-decoration:none;" >
						<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
                            
							Search Budget Leads
                        </div>
						</a>
                        
						<div id="collapse1" class="panel-collapse collapse">
						<div class="panel-body">
							<form role="form" action="/LMSInventorySystem/MainController" method="get" onsubmit="enableSample();">
                                        
										<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label>Region</label>
												<input type="hidden" name="search" value="mysearch" />
												<input type="hidden" name="action" value="listUser" />
												
												
												
											<select path="region" id="region"  name="region" class="form-control"
											<c:choose>
															<c:when test="${userRole=='RSM'}">
																
																disabled
																
															</c:when>
															<c:when test="${userRole=='BM'}">
																
																disabled
																
															</c:when> 
															<c:when test="${userRole=='SE'}">
																
																disabled
																
															</c:when>   															
															<c:otherwise>
																
																
																
																
															</c:otherwise>
												</c:choose>
											
											>
											 	<option value="0">Select Region </option>
												<c:forEach items="${regionType}" var="region"> 
														<option value="${region}" ${region == userRegion ? 'selected="selected"' : ''}>${region}</option>
											    </c:forEach>
						                    </select>
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label>State</label>
											<select path="state" id="state"  name="state" class="form-control"
											<c:choose>
															
															<c:when test="${userRole=='BM'}">
																
																disabled
																
															</c:when> 
															<c:when test="${userRole=='SE'}">
																
																disabled
																
															</c:when>   															
															<c:otherwise>
																
																
																
																
															</c:otherwise>
												</c:choose>
											
											
											
											>
											 	<option value="0">Select State</option>
												<c:forEach items="${exe_state}" var="state"> 
														<option value="${state}" ${state == userState ? 'selected="selected"' : ''}>${state}</option>
											    </c:forEach>
						                    </select>
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label>City</label>
												
												
												<select path="city" id="city"  name="city" class="form-control"
												<c:choose>
															
															<c:when test="${userRole=='SE'}">
																
																disabled
																
															</c:when>   															
															<c:otherwise>
																
																
																
																
															</c:otherwise>
												</c:choose>
												>
											 	<option value="0">Select City</option>
												<c:forEach items="${exe_city}" var="city"> 
														<option value="${city}" ${city == userDCity ? 'selected="selected"' : ''}>${city}</option>
											    </c:forEach>
						                    </select>
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label>Created By</label>
												
												<select path="user" id="user" name="user" class="form-control" 
												
													<c:choose>
															<c:when test="${userRole=='SE'}">
																
																disabled
																
															</c:when>    
															<c:otherwise>
																
																
																
																
															</c:otherwise>
														</c:choose>
												>
											 	<option value="0">Select Executive</option>
												<c:forEach items="${seList}" var="users"> 
														<option value="${users.USER_ID}" ${users.USER_ID == userID ? 'selected="selected"' : ''}>${users.USER_ID}</option>
											    	</c:forEach>
						                    </select>
												
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label>From </label>
												<input type="hidden" name="roleName" value="${userRole}" />
												<input class="form-control" type="date" name="from_date" id="from_date" value="${from_date}" required>
												
											</div>
										</div>
										
										<div class="col-lg-3">
											<div class="form-group">
											   <label>To </label>
												<input class="form-control"  type="date" name="to_date" value="${to_date}" required>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
											   <button type="submit" class="btn btn-primary">Submit </button>
											<button type="reset" class="btn btn-warning">Reset </button>
											</div>
										</div>
									</div>
										
                                        
                                        
										
                    	</form>
					</div>
					
					</div>
					
					</div>
                </div>
				
				
				
                <!-- /.col-lg-12 -->
            </div>
<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Budget Lead Details
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                             <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th style="width:20%">Lead ID</th>
                                            <th>Customer</th>
                                            <th>Location</th>
                                            <th>Owner</th>
                                            <th>Created Date</th>
                                            <th>Convert</th>
														<c:choose>
															<c:when test="${userRole=='BM'}">
																
																<th>Action</th>
																
															</c:when> 
															<c:when test="${userRole=='RSM'}">
																
																<th>Action</th>
																
															</c:when>															
															<c:otherwise>
																
																
																
																
															</c:otherwise>
														</c:choose>
                                            
                                            
                                            
                                            
                                        </tr>
                                    </thead>


								   <tbody>
								   <c:forEach items="${leaddetails1}" var="user">
						                <tr class="gradeA odd" role="row">
						                    <td style="width:20%">
						                    <a href="MainController?action=view&leadid=<c:out value="${user.LEAD_ID}"/>"><c:out value="${user.LEAD_ID}" />
						                    </a>
						                    
						                    <c:choose>
											    <c:when test="${user.LEAD_CHANCES.equalsIgnoreCase('G')}">
											        <div class="progress progress-striped progress-success active">
														<div class="bar" style="width: 100%;"></div>
													</div>
											    </c:when>
											    <c:when test="${user.LEAD_CHANCES.equalsIgnoreCase('Y')}">
											        <div class="progress progress-striped progress-info active">
														<div class="bar" style="width: 70%;"></div>
													</div>
											    </c:when>
											    <c:when test="${user.LEAD_CHANCES.equalsIgnoreCase('R')}">
											        <div class="progress progress-striped progress-danger active">
														<div class="bar" style="width: 40%;"></div>
													</div>
											    </c:when>
											    
											    <c:otherwise>
											        
											    </c:otherwise>
											</c:choose>
						                    </td>
						                     <td>
						             
                                <div class="tooltip-demo">
                                <a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title="" 
                                data-original-title="  Mobile No - ${user.CUSTOMER_MOBILENO} 
						                Address - ${user.CUSTOMER_ADDRESS}  
						                Email Address - ${user.CUSTOMER_EMAIL_ADDRESS}">
                                <c:out value="${user.CUSTOMER_NAME}" /></a></div>
                                
                                </td>
						                   <td><c:out value="${user.PROJECT_EXE_PLACE}" /></td>
						                    <td><c:out value="${user.OWNER_ID}" /></td>
						                   <td><c:out value="${user.CREATION_DATE}" /></td>
						                   <td><a href="#" onclick="return converToLead('${user.LEAD_ID}')">Click To Conver To Lead</a></td>
						                   
						                   
						                   <c:choose>
															<c:when test="${userRole=='BM'}">
																
																 <td><a href="MainController?action=editIndent&leadid=<c:out value="${user.LEAD_ID}"/>"><img src="dist/images/edit.png" /></a>
																 <a href="MainController?action=viewIndent&leadid=<c:out value="${user.LEAD_ID}"/>"><img src="dist/images/view1.png" /></a>
																</td>
															</c:when> 
															<c:when test="${userRole=='RSM'}">
																
																 <td><a href="MainController?action=editIndent&leadid=<c:out value="${user.LEAD_ID}"/>"><img src="dist/images/edit.png" /></a>
																 <a href="MainController?action=viewIndent&leadid=<c:out value="${user.LEAD_ID}"/>"><img src="dist/images/view1.png" /></a>
																 </td>
																
															</c:when>															
															<c:otherwise>
																
																
																
																
															</c:otherwise>
														</c:choose>
						                   
						                    
						                   
						                   
						                </tr>
            						</c:forEach>
										<!--  <tr class="gradeA odd" role="row">
                                            <td class="sorting_1"><a href="#">BGL_2015_01</a></td>
                                            <td>Tulip</td>
                                            <td>Banglore</td>
                                            <td class="center">TestUser</td>
                                            <td class="center">2015-10-30 10:28</td>
                                     </tr>
										<tr class="gradeA even" role="row">
                                             <td class="sorting_1"><a href="#">TRI_2015_01</a></td>
                                            <td>TechnoCity</td>
                                            <td>Trivendrum </td>
                                            <td class="center">TestUser</td>
                                            <td class="center">2015-10-29 4:35</td>
                                        </tr>-->
                                        
										
								   
								   </tbody>
								   </table>
							</div>
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
           </div>
          
           <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>
   <!-- Morris Charts JavaScript -->
   

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
     <!-- DataTables JavaScript -->
    <script src="bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

 
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
    	
    	var table = $('#dataTables-example').DataTable();
    	table.destroy();
       
        
        $('#dataTables-example').DataTable({
        	"responsive":true,
        	"bDestroy":true,
        	"bSort":false,
        	"iDisplayLength" : 5,
        	
        	"aLengthMenu": [[5, 10, 20,50,100,-1], [5, 10, 20,50,100, "All"]]
        });
        document.getElementById("dataTables-example_filter").style.float='right';    
    });
    
    </script>
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
       
        
    });
    </script>

</body></html>
          