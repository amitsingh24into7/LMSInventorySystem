<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
var jsdata="";
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
function removeAllOptions(sel, removeGrp) {
    var len, groups, par;
    if (removeGrp) {
        groups = sel.getElementsByTagName('optgroup');
        len = groups.length;
        for (var i=len; i; i--) {
            sel.removeChild( groups[i] );
        }
        
    }
    
    len = sel.options.length;
    for (var i=len-1; i; i--) {
        par = sel.options[i-1].parentNode;
        par.removeChild( sel.options[i] );
    }
}
function removeAllEngineOptions(sel, removeGrp) {
    var len, groups, par;
    if (removeGrp) {
        groups = sel.getElementsByTagName('optgroup');
        len = groups.length;
        for (var i=len; i; i--) {
            sel.removeChild( groups[i-1] );
        }
        
    }
    
    len = sel.options.length;
    for (var i=len-1; i; i--) {
        par = sel.options[i-1].parentNode;
        par.removeChild( sel.options[i-1] );
    }
}
function GetItemModelDetails(str,selvalue)
{

	//alert("asda"+str);
	select = document.getElementById('txt_engine');
	
	removeAllEngineOptions(select,true);
			
	var http = new getXMLHttpRequestObject();
	 
	var url = "GetEngineModelDetails";
	var parameters = "ENGINE_TYPE="+str;
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	       // alert(http.responseText);
			a=JSON.parse(http.responseText);
			var output = [];
			//alert(a.length);
			select = document.getElementById('txt_engine');
			
			 for(var i=0; i < a.length; i++)
			 {
				//output[i++] = '<option value="'+ a[i] +'">'+ a[i] +'</option>';
				var opt = document.createElement('option');
				if(selvalue=="")
					{
					opt.value = a[i];
					opt.innerHTML = a[i];	
					}
				else
					{
						if(a[i]==selvalue)
							{
							opt.value = a[i];
							opt.selected=true;
							opt.innerHTML = a[i];
							}
					
					}
				
				select.appendChild(opt);
			 }
			//alert(i); 
			//document.getElementById("engineMakeType").innerHTML = output;
			
			//innerHTML = output.join('');
			
	    }
	}
	http.send(null);
}
</script>

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
        
             <div class="row" >
			<div class="col-lg-12" style="margin-top:5px">
	           <ol class="breadcrumb">
				 <li><a href="index.jsp"><i class="fa fa-home fa-fw"></i> Home</a></li>
				 <li class="dropdown active">
        			  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
        			  aria-haspopup="true" aria-expanded="false">Engine Budget Planning <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            		            
			           
			           <li><a href="StockController?action=createStock">Add Stock Budget</a></li>
              			<li role="separator" class="divider"></li>
              			<li class="active"><a href="StockController?action=listBudgetStock&search=All">View Stocks</a></li>
              		
			          </ul>
        		</li>
				  
				  
				</ol>
			</div>
		</div>
            <!-- /.row -->
			<div class="row">
			<div class="col-lg-12">
				 <div class="panel panel-primary">
						
						<a data-toggle="collapse" href="#collapse1" style="text-decoration:none;" >
						<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
                            
							Search 
                        </div>
						</a>
                        
						<div id="collapse1" class="panel-collapse collapse">
						<div class="panel-body">
							<form role="form" action="/LMSInventorySystem/StockController" method="post" onsubmit="enableSample();">
                                        
										<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label>Region</label>
												<input type="hidden" name="search" value="mysearch" />
												<input type="hidden" name="action" value="listBudgetStock" />
												
												
												
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
											 	<option value="">Select Region </option>
												<c:forEach items="${regionType}" var="region"> 
														<option value="${region}" ${region == userRegion ? 'selected="selected"' : ''}>${region}</option>
											    </c:forEach>
						                    </select>
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label>Project Type</label> 
                                            	<select  class="form-control" name="projectType" id="projectType" >
                                            		<option value="">Select Type</option>
													<c:forEach items="${projectTypes}" var="projectType"> 
														<option value="${projectType}" ${projectType == selprojectType ? 'selected="selected"' : ''}>${projectType}</option>
											    	</c:forEach>
						                    	</select>
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label>Engine Name</label>
													<select id='txt_engine' name='txt_engine' style="width: 100%" class="form-control">
														<option value="">Select</option>
														<option selected></option>
													</select>
												<input type="hidden" id="sel_engine" value="${sel_txt_engine}" />
											</div>
										</div>
										
										<div class="col-lg-3">
											<div class="form-group">
												<label>Year ${sel_txt_year} </label>
												
												<select name="txt_year" class="form-control" id="txt_year">
												
												
												<c:choose>
															
															

															<c:when test="${empty sel_txt_year}">
																
																	<option selected>${curFinYear}</option>
																	<option>${prevFinYear}</option>
																
															</c:when>
															
															<c:when test="${sel_txt_year==curFinYear}">
																
																	<option selected>${curFinYear}</option>
																	<option>${prevFinYear}</option>
																
															</c:when>
															   															
															<c:otherwise>
																
																<option>${curFinYear}</option>
																<option selected>${prevFinYear}</option>
																
															</c:otherwise>
															
												</c:choose>
												
												
												
													
												</select>
												
											</div>
										</div>
									</div>
									<div class="row">
										<!-- 
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
										 -->
										<div class="col-lg-3">
											<div class="form-group">
												<label>Type </label>
												<select id='type' name='type' style="width: 100%" class="form-control">
														<c:choose>
															
															

															<c:when test="${empty sel_type}">
																
																	<option selected>Qty</option>
																	<option>Value</option>
																
															</c:when>
															
															<c:when test="${sel_type eq 'Qty'}">
																
																	<option selected>Qty</option>
																	<option>Value</option>
																
															</c:when>
															   															
															<c:otherwise>
																
																<option>Qty</option>
																<option selected>Value</option>
																
															</c:otherwise>
															
												</c:choose>
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
                            Budget Stock Details
                           
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                             <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th style="width:20%">Region</th>
                                            <th>Project Type</th>
                                            <th>Engine Name</th>
                                           
                                           <c:choose>
															
															

															<c:when test="${empty sel_type}">
																
																	<th style="display:none">Q1Qty</th>
																	<th style="display:none">Q2Qty</th>
																	<th style="display:none">Q3Qty</th>
																	<th style="display:none">Q4Qty</th>
																	<th>Q1</th>
                                            
						                                            
						                                            <th >Q2</th>
						                                            
						                                            <th>Q3</th>
						                                            
						                                            <th>Q4</th>
						                                            
																
															</c:when>
															
															<c:when test="${sel_type eq 'Qty'}">
																
																	<th style="display:none">Q1Qty</th>
																	<th style="display:none">Q2Qty</th>
																	<th style="display:none">Q3Qty</th>
																	<th style="display:none">Q4Qty</th>
																	<th>Q1</th>
                                            
						                                            
						                                            <th >Q2</th>
						                                            
						                                            <th>Q3</th>
						                                            
						                                            <th>Q4</th>
						                                            
																
															</c:when>
															   															
															<c:otherwise>
																
																<th style="display:none">Q1Value</th>
																	<th style="display:none">Q2Value</th>
																	<th style="display:none">Q3Value</th>
																	<th style="display:none">Q4Value</th>
																	<th>Q1</th>
                                            
						                                            
						                                            <th >Q2</th>
						                                            
						                                            <th>Q3</th>
						                                            
						                                            <th>Q4</th>
						                                            
																
															</c:otherwise>
															
												</c:choose>
                                           
                                            
                                             <th>Total</th>
                                            
                                            
                                        </tr>
                                    </thead>
                                     


								   <tbody>
								  
								   <c:forEach items="${engingBudList}" var="engineLst">
						                <tr class="gradeA odd" role="row">
						                    <td style="width:20%">
						                   		${engineLst.REGION}
						                    </td>
						                    <td>
						                    	${engineLst.PROJECT_TYPE}
                                			</td>
						                   <td>
						                   		<c:out value="${engineLst.ENGINE_NAME}" />
						                   </td>
						                   <c:choose>
															
															

															<c:when test="${empty sel_type}">
																
										<td style="display:none">
											<c:out value="${engineLst.m1budget+engineLst.m2budget+engineLst.m3budget}" />
										</td>
										<td style="display:none">
						                   <c:out value="${engineLst.m4budget+engineLst.m5budget+engineLst.m6budget}" />
						                   </td>
										   <td style="display:none">
						                		<c:out value="${engineLst.m7budget+engineLst.m8budget+engineLst.m9budget}" />
											</td>
										   
										   <td style="display:none">
						                        <c:out value="${engineLst.m10budget+engineLst.m11budget+engineLst.m12budget}" />
						                   </td>
										   
										   
											
											<td>
																											
						                   <div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title=""
													data-original-title=" Jan - ${engineLst.m1budget} Feb - ${engineLst.m2budget}  Mar - ${engineLst.m3budget}">
												  <c:out value="${engineLst.m1budget+engineLst.m2budget+engineLst.m3budget}" /></a>
											</div>
						                </td>
						     		
						     		
										
																                  
										  
										  <td>
						                  
											<div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title="" 
												data-original-title="  Apr - ${engineLst.m4budget} May - ${engineLst.m5budget} Jun - ${engineLst.m6budget}"> 
											   <c:out value="${engineLst.m4budget+engineLst.m5budget+engineLst.m6budget}" /></a>
										   </div>     
						                   
						                   </td>
						                   
										   
						                   
										   <td>
						                   
											   <div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title="" 
												data-original-title="  Jul - ${engineLst.m7budget}   Aug - ${engineLst.m8budget}  Sep - ${engineLst.m9budget}">
											   <c:out value="${engineLst.m7budget+engineLst.m8budget+engineLst.m9budget}" /> </a>
											   </div>     
						                   
						                   </td>
											
						                   <td>
						                   
										   <div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title=""
												data-original-title="  Oct - ${engineLst.m10budget}  Nov - ${engineLst.m11budget} Dec - ${engineLst.m12budget}">
												<c:out value="${engineLst.m10budget+engineLst.m11budget+engineLst.m12budget}" />
												</a>
										   </div>
										   
						                   </td>
						                   
						                   <td> ${engineLst.m1budget+engineLst.m2budget+engineLst.m3budget+engineLst.m4budget+engineLst.m5budget+engineLst.m6budget+engineLst.m7budget+engineLst.m8budget+engineLst.m9budget+engineLst.m10budget+engineLst.m11budget+engineLst.m12budget}
 											</td> 
																
															</c:when>
															
															<c:when test="${sel_type eq 'Qty'}">
															<td style="display:none">
											<c:out value="${engineLst.m1budget+engineLst.m2budget+engineLst.m3budget}" />
										</td>
										<td style="display:none">
						                   <c:out value="${engineLst.m4budget+engineLst.m5budget+engineLst.m6budget}" />
						                   </td>
										   <td style="display:none">
						                		<c:out value="${engineLst.m7budget+engineLst.m8budget+engineLst.m9budget}" />
											</td>
										   
										   <td style="display:none">
						                        <c:out value="${engineLst.m10budget+engineLst.m11budget+engineLst.m12budget}" />
						                   </td>
										   
										   
											
											<td>
																											
						                   <div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title=""
													data-original-title=" Jan - ${engineLst.m1budget} Feb - ${engineLst.m2budget}  Mar - ${engineLst.m3budget}">
												  <c:out value="${engineLst.m1budget+engineLst.m2budget+engineLst.m3budget}" /></a>
											</div>
						                </td>
						     		
						     		
										
																                  
										  
										  <td>
						                  
											<div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title="" 
												data-original-title="  Apr - ${engineLst.m4budget} May - ${engineLst.m5budget} Jun - ${engineLst.m6budget}"> 
											   <c:out value="${engineLst.m4budget+engineLst.m5budget+engineLst.m6budget}" /></a>
										   </div>     
						                   
						                   </td>
						                   
										   
						                   
										   <td>
						                   
											   <div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title="" 
												data-original-title="  Jul - ${engineLst.m7budget}   Aug - ${engineLst.m8budget}  Sep - ${engineLst.m9budget}">
											   <c:out value="${engineLst.m7budget+engineLst.m8budget+engineLst.m9budget}" /> </a>
											   </div>     
						                   
						                   </td>
											
						                   <td>
						                   
										   <div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title=""
												data-original-title="  Oct - ${engineLst.m10budget}  Nov - ${engineLst.m11budget} Dec - ${engineLst.m12budget}">
												<c:out value="${engineLst.m10budget+engineLst.m11budget+engineLst.m12budget}" />
												</a>
										   </div>
										   
						                   </td>
						                   
						                   <td> ${engineLst.m1budget+engineLst.m2budget+engineLst.m3budget+engineLst.m4budget+engineLst.m5budget+engineLst.m6budget+engineLst.m7budget+engineLst.m8budget+engineLst.m9budget+engineLst.m10budget+engineLst.m11budget+engineLst.m12budget}
 											</td> 
																
															</c:when>
															   															
															<c:otherwise>
<td style="display:none">
											<c:out value="${engineLst.m1budgetvalue+engineLst.m2budgetvalue+engineLst.m3budgetvalue}" />
										</td>
										<td style="display:none">
						                   <c:out value="${engineLst.m4budgetvalue+engineLst.m5budgetvalue+engineLst.m6budgetvalue}" />
						                   </td>
										   <td style="display:none">
						                		<c:out value="${engineLst.m7budgetvalue+engineLst.m8budgetvalue+engineLst.m9budgetvalue}" />
											</td>
										   
										   <td style="display:none">
						                        <c:out value="${engineLst.m10budgetvalue+engineLst.m11budgetvalue+engineLst.m12budgetvalue}" />
						                   </td>
										   
										   
											
											<td>
																											
						                   <div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title=""
													data-original-title=" Jan - ${engineLst.m1budgetvalue} Feb - ${engineLst.m2budgetvalue}  Mar - ${engineLst.m3budgetvalue}">
												  <c:out value="${engineLst.m1budgetvalue+engineLst.m2budgetvalue+engineLst.m3budgetvalue}" /></a>
											</div>
						                </td>
						     		
						     		
										
																                  
										  
										  <td>
						                  
											<div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title="" 
												data-original-title="  Apr - ${engineLst.m4budgetvalue} May - ${engineLst.m5budgetvalue} Jun - ${engineLst.m6budgetvalue}"> 
											   <c:out value="${engineLst.m4budgetvalue+engineLst.m5budgetvalue+engineLst.m6budgetvalue}" /></a>
										   </div>     
						                   
						                   </td>
						                   
										   
						                   
										   <td>
						                   
											   <div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title="" 
												data-original-title="  Jul - ${engineLst.m7budgetvalue}   Aug - ${engineLst.m8budgetvalue}  Sep - ${engineLst.m9budgetvalue}">
											   <c:out value="${engineLst.m7budgetvalue+engineLst.m8budgetvalue+engineLst.m9budgetvalue}" /> </a>
											   </div>     
						                   
						                   </td>
											
						                   <td>
						                   
										   <div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title=""
												data-original-title="  Oct - ${engineLst.m10budgetvalue}  Nov - ${engineLst.m11budgetvalue} Dec - ${engineLst.m12budgetvalue}">
												<c:out value="${engineLst.m10budgetvalue+engineLst.m11budgetvalue+engineLst.m12budgetvalue}" />
												</a>
										   </div>
										   
						                   </td>
						                   
						                   <td> ${engineLst.m1budgetvalue+engineLst.m2budgetvalue+engineLst.m3budgetvalue+engineLst.m4budgetvalue+engineLst.m5budgetvalue+engineLst.m6budgetvalue+engineLst.m7budgetvalue+engineLst.m8budgetvalue+engineLst.m9budgetvalue+engineLst.m10budgetvalue+engineLst.m11budgetvalue+engineLst.m12budgetvalue}
 											</td> 
																
															</c:otherwise>
															
												</c:choose>
						                   
						                   
						                    
    					                   
						                  
						                   
						                </tr>
            						</c:forEach>
										
                                        
										
								   
								   </tbody>
								  <tfoot>
								    <tr>
								        <td></td>
								        <td></td>
								        <td></td>
								        
								        
								        <td style="display:none"></td>
										<td style="display:none"></td>
										<td style="display:none"></td>
										<td style="display:none"></td>
								        <td></td>
								        
								        <td></td>
								        
								        <td></td>
								        
								        <td></td>
										<td></td>
								        
								    </tr>
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
         		"sSwfPath": "media/swf/copy_cvs_xls_pdf.swf",
                 "sRowSelect": "single",
                 "aButtons": [
                   {
                     "sExtends": "copy",
                     "bFooter": true,
                     "mColumns": [0, 1, 2, 3, 4 , 5 , 6],
                   },
                   {
                     "sExtends": "xls",
                     "mColumns": [0, 1, 2, 3, 4 , 5 , 6],
                     //"bHeader": true,
                     "bFooter": true
                   },
                   {
                     "sExtends": "pdf",
                     "bFooter": true,
                     "mColumns": [0, 1, 2, 3, 4 , 5 , 6],
                   },
                   {
                     "sExtends": "print",
                     "bFooter": true,
                     "mColumns": [0, 1, 2, 3, 4 , 5 , 6],
                   }
                 ]
             },
        	"footerCallback": function ( row, data, start, end, display ) {
                var api = this.api(), data;
     
                // Remove the formatting to get integer data for summation
                var intVal = function ( i ) {
                    return typeof i === 'string' ?
                        i.replace(/[\$,]/g, '')*1 :
                        typeof i === 'number' ?
                            i : 0;
                };
     
                // Update footer
                $( api.column( 2 ).footer() ).html(
                    '<b>Total  </b>'
                );
       
     
                // Total over this page
                pageTotal = api
                    .column( 3, { page: 'current'} )
                    .data()
                    .reduce( function (a, b) {
                        return intVal(a) + intVal(b);
                    }, 0 );
     
                $( api.column( 7 ).footer() ).html(
                        pageTotal
                    );
             // Total over this page
                pageTotal = api
                    .column( 4, { page: 'current'} )
                    .data()
                    .reduce( function (a, b) {
                        return intVal(a) + intVal(b);
                    }, 0 );
     
                $( api.column( 8 ).footer() ).html(
                        pageTotal
                    );
             // Total over this page
                pageTotal = api
                    .column( 5, { page: 'current'} )
                    .data()
                    .reduce( function (a, b) {
                        return intVal(a) + intVal(b);
                    }, 0 );
     
                $( api.column( 9 ).footer() ).html(
                        pageTotal
                    );
             // Total over this page
                pageTotal = api
                    .column( 6, { page: 'current'} )
                    .data()
                    .reduce( function (a, b) {
                        return intVal(a) + intVal(b);
                    }, 0 );
     
                $( api.column( 10 ).footer() ).html(
                        pageTotal
                    );
            }
        });
        
        document.getElementById("dataTables-example_filter").style.float='right';  
        
    });
    
 // tooltip demo
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    })

    
    $("[data-toggle=popover]")
    .popover()
    </script>
    
    <script>
GetItemModelDetails('Engine',document.getElementById("sel_engine").value);

function checkInputError()
{
	str=true;
	obj=document.getElementsByClassName("help-block with-errors");
	for(i=0;i<obj.length;i++)
	{
		if(obj[i].innerHTML!="")
		{
			str=false;
			break;
		}
	}
	return str;
	
}
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
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
       
        
    });
    </script>

</body></html>
          