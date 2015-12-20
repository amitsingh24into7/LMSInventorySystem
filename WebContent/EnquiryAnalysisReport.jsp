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
</script>

<div id="page-wrapper">
<div class="row">
                <div class="col-lg-12" style="margin-top:10px">
				 <div class="panel panel-primary">
						
						<a data-toggle="collapse" href="#collapse1" style="text-decoration:none;" >
						<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
                            
							Search 
                        </div>
						</a>
                        
						<div id="collapse1" class="panel-collapse collapse">
						<div class="panel-body">
							<form role="form" action="/LMSInventorySystem/ReportController" method="get" onSubmit="enableSample();">
                                        
										<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label>Region</label>
												<input type="hidden" name="reportType" value="EnquiryAnalysis" />
												<input type="hidden" name="action" value="search" />
												
												
												
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
												<label>Project Type</label>
												
												
												<select path="projectType" id="projectType"  name="projectType" class="form-control"
												
												>
											 	<option value="0">Select Project Type</option>
												<c:forEach items="${projectType}" var="project"> 
														<option value="${project}" ${project == userProject ? 'selected="selected"' : ''}>${project}</option>
											    </c:forEach>
						                    </select>
												
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
                            Enquiry Bank Analysis Report
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        
                             <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Chance</th>
                                            <th>Project Type</th>
                                            
                                            <c:forEach items="${engineList}" var="engines"> 
												<th>${engines} Qty</th>
	                                            <th>${engines} Value</th>			
											</c:forEach>
											    	

                                            <th>Total Qty</th>
                                            <th>Total Value</th>
                                           
                                           
                                            
                                            
                                            
                                            
                                        </tr>
                                    </thead>
                                     


								   <tbody>
								   
								    <c:forEach items="${reportdetails}" var="engineVal" >
									    
										    	
										    <tr> 
													<td
													<c:choose>
											    <c:when test="${engineVal[1].equalsIgnoreCase('G')}">
											        style="background-color:green;text-align: center;"
											    </c:when>
											    <c:when test="${engineVal[1].equalsIgnoreCase('Y')}">
											        style="background-color:yellow;text-align: center;"
											    </c:when>
											    <c:when test="${engineVal[1].equalsIgnoreCase('R')}">
											        style="background-color:red;text-align: center;"
											    </c:when>
											    
											    <c:otherwise>
											        style="background-color:blue;text-align: center;"
											    </c:otherwise>
											</c:choose>
													>${engineVal[1]}</td>
													<td>${engineVal[0]}</td>
													
													<c:forEach var="i" begin="2" end="${totalEngine*2+1}">
														   <td
														   
														   
														   >${engineVal[i]}</td>
													</c:forEach>
													
													<td>${engineVal[totalEngine*2+2]}</td>
													<td>${engineVal[totalEngine*2+3]}</td>
		                                            
		                                     </tr>
	                                    
                                     </c:forEach>			
									
											    	
											    	
								  
								   
								  
										
								   
								   </tbody>
								   
								   <tfoot>
								   <td></td>
								   <td></td>
								   <c:forEach var="i" begin="1" end="${totalEngine*2+2}">
														   <td
														   
														   
														   ></td>
									</c:forEach>
								   
								   
								   </tfoot>
								   
								 
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
    
    <script type="text/javascript" charset="utf-8" src="media/ZeroClipboard/ZeroClipboard.js"></script>
	<script type="text/javascript" charset="utf-8" src="media/js/TableTools.js"></script>

 
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
    	
    	
    	var table = $('#dataTables-example').DataTable();
    	table.destroy();
       
        
        $('#dataTables-example').DataTable({
        	"sDom": 'T<"clear">lfrtip',
        	"responsive":true,
        	"bDestroy":true,
        	"bSort":false,
        	"iDisplayLength" : 10,
        	
        	
        	"aLengthMenu": [[10, 20,50,100,-1], [10, 20,50,100, "All"]],
        	"oTableTools":{
                "sRowSelect": "single",
                "aButtons": [
                  {
                    "sExtends": "copy",
                    "bFooter": true,
                    "mColumns": [0,1,2,3]
                  },
                  {
                    "sExtends": "xls",
                    "mColumns": [0,1,2,3],
                    //"bHeader": true,
                    "bFooter": true
                  },
                  {
                    "sExtends": "pdf",
                    "bFooter": true,
                    "mColumns": [0,1,2,3]
                  },
                  {
                    "sExtends": "print",
                    "bFooter": true,
                    "mColumns": [0,1,2,3]
                  }
                ]
            },"footerCallback": function ( row, data, start, end, display ) {
                var api = this.api(), data;
     
                // Remove the formatting to get integer data for summation
                var intVal = function ( i ) {
                    return typeof i === 'string' ?
                        i.replace(/[\$,]/g, '')*1 :
                        typeof i === 'number' ?
                            i : 0;
                };
     
                
     
                
     
                // Update footer
                $( api.column( 1 ).footer() ).html(
                    '<b>Total  </b>'
                );
                a=parseInt(document.getElementById("totalEngine").value);
                for(i=2;i<a*2+4;i++)
                	{
	                tot=api
	                .column( i )
	                .data()
	                .reduce( function (a, b) {
	                    return intVal(a) + intVal(b);
	                }, 0 );
	                $( api.column( i ).footer() ).html(
	                		tot
	                    );
           			}
            }
            
   
        });
    });
    
    </script>
    
    <style type="text/css" title="currentStyle">
			
			@import "media/css/TableTools.css";
			
			/*#dataTables-example_filter
			{
				float:right;
				margin-right:-130px;
			}*/
			th
			{
				font-size:12px !important;
			}
			td
			{
				font-size:12px !important;
			}
			.table
			{
				margin-bottom:0px !important;
			}
		</style>
<script>
enableSample();

</script>
<input type="text" value="${totalEngine}" id="totalEngine" />

<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
       
        
    });
    </script>
</body></html>