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
function resetForm()
{
	document.getElementById("projectType").value="0";
	document.getElementById("txt_engine").value="0";
}
</script>
<div id="page-wrapper">
        
             <div class="row" >
			<div class="col-lg-12" style="margin-top:5px">
	           <ol class="breadcrumb">
				 <li><a href="index.jsp"><i class="fa fa-home fa-fw"></i> Home</a></li>
				 <li class="dropdown active">
        			  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
        			  aria-haspopup="true" aria-expanded="false">Report <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            		            
			         
			           <li class="active"><a href="StockController?action=listFFS&search=All">FFS Report</a></li>
              			<li role="separator" class="divider"></li>
              			<li><a href="ReportController?reportType=OrderBoardReport&action=all">Order Board Report</a></li>
              			<li role="separator" class="divider"></li>
              			<li><a href="ReportController?reportType=ProjectReport&action=all">Project Report</a></li>
              			<li role="separator" class="divider"></li>
              			
              			<li><a href="ReportController?reportType=QuoteAnalysis&action=all">Quote Analysis Report</a></li>
              			<li role="separator" class="divider"></li>
              			
              			<li><a href="ReportController?reportType=EnquiryAnalysis&action=all">Enquiry Bank Analysis</a></li>
              			<li role="separator" class="divider"></li>
              			
              			<li><a href="ReportController?reportType=OrderLostReport&action=all">Order Loss Report</a></li>
              			
              		
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
                                        
										<input type="hidden" name="search" value="mysearch" />
												<input type="hidden" name="action" value="listFFS" />
										<div class="row">
										
										<div class="col-lg-3">
											<div class="form-group">
												<label>Project Type</label> 
                                            	<select  class="form-control" name="projectType" id="projectType" >
                                            		<option value="0">Select Type</option>
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
														<option value="0">Select</option>
														
													</select>
												<input type="hidden" id="sel_engine" value="${sel_txt_engine}" />
											</div>
										</div>
										
										<div class="col-lg-3">
											<div class="form-group">
											   <button type="submit" class="btn btn-primary">Submit </button>
											<button type="reset" class="btn btn-warning" onclick="resetForm();">Reset </button>
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
                            FFS Report
                           
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                     
                             <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            
                                            <th style="width:100px !important">Project Type</th>
                                            <th style="width:100px">Engine Name</th>
                                            <th style="width:100px">Stock As On</th>
                                             <th>Transit</th>
                                             <th>Jan</th>
                                             <th>Feb</th>
                                             <th>Mar</th>
                                             <th>Apr</th>
                                             <th>May</th>
                                             <th>Jun</th>
                                             <th>Jul</th>
                                             <th>Aug</th>
                                             <th>Sep</th>
                                             <th>Oct</th>
                                             <th>Nov</th>
                                             <th>Dec</th>
                                             <th >Total F Stock</th>
                                             <th style="width:100px">Total  Stock</th>
                                             <th style="width:100px">Exe Pending Order</th>
                                             <th style="width:100px">Pending Order On Hold</th>
                                             <th style="width:100px">Total Pending Order</th>
                                             <th style="width:100px">FFS (Based on E.PO)</th>
                                             <th style="width:100px">FFS (Based on Total PO)</th>
                                             <th style="display:none"></th>
                                             <th>Lead Execution Orders</th>
                                           
                                           
                                           
                                          
                                           
                                            
                                             
                                            
                                            
                                        </tr>
                                    </thead>
                                     


								   <tbody>
								  
								   <c:forEach items="${engingBudList}" var="engineLst">
						                <tr class="gradeA odd" role="row">
						                    <td>
						                    	${engineLst.PROJECT_TYPE}
                                			</td>
                                			
                                			 <td>
                                			 
											  <c:out value="${engineLst.ENGINE_NAME}" /> 
                                			
                                			</td>
                                			
						                   
						                   <td>
						                   		<c:out value="${engineLst.STOCK_AS_ON}" />
						                   </td>
						                	
						                	<td>
						                   		<c:out value="${engineLst.TRANSIT}" />
						                   </td>
						                	
						                	
						                	<td>${engineLst.m1qty}</td>
											<td>${engineLst.m2qty}</td>
											<td>${engineLst.m3qty}</td>
											<td>${engineLst.m4qty}</td>
											<td>${engineLst.m5qty}</td>
											<td>${engineLst.m6qty}</td>
											<td>${engineLst.m7qty}</td>
											<td>${engineLst.m8qty}</td>
											<td>${engineLst.m9qty}</td>
											<td>${engineLst.m10qty}</td>
											<td>${engineLst.m11qty}</td>
											
											<td>${engineLst.m12qty}</td>
											
					<td><c:out value="${engineLst.TRANSIT+engineLst.m1qty+engineLst.m2qty+engineLst.m3qty+engineLst.m4qty+engineLst.m5qty+engineLst.m6qty+engineLst.m7qty+engineLst.m8qty+engineLst.m9qty+engineLst.m10qty+engineLst.m11qty+engineLst.m12qty}" /></td>
					<td><c:out value="${engineLst.STOCK_AS_ON + engineLst.TRANSIT+engineLst.m1qty+engineLst.m2qty+engineLst.m3qty+engineLst.m4qty+engineLst.m5qty+engineLst.m6qty+engineLst.m7qty+engineLst.m8qty+engineLst.m9qty+engineLst.m10qty+engineLst.m11qty+engineLst.m12qty}" /></td>
					<td><c:out value="${engineLst.exe_pending_order}" /></td>
					<td><c:out value="${engineLst.pending_order_onhold}" /></td>
					<td><c:out value="${engineLst.exe_pending_order+engineLst.pending_order_onhold}" /></td>
					
<td><c:out value="${engineLst.STOCK_AS_ON+engineLst.TRANSIT+engineLst.m1qty+engineLst.m2qty+engineLst.m3qty+engineLst.m4qty+engineLst.m5qty+engineLst.m6qty+engineLst.m7qty+engineLst.m8qty+engineLst.m9qty+engineLst.m10qty+engineLst.m11qty+engineLst.m12qty-engineLst.exe_pending_order}" /></td>
<td><c:out value="${engineLst.STOCK_AS_ON+engineLst.TRANSIT+engineLst.m1qty+engineLst.m2qty+engineLst.m3qty+engineLst.m4qty+engineLst.m5qty+engineLst.m6qty+engineLst.m7qty+engineLst.m8qty+engineLst.m9qty+engineLst.m10qty+engineLst.m11qty+engineLst.m12qty-engineLst.exe_pending_order-engineLst.pending_order_onhold}" /></td>
											
											  
											<td style="display:none">${engineLst.leads}</td>
											<td>
											<div class="tooltip-demo">
												<a href="#" style="text-decoration:underline"  data-toggle="tooltip" data-placement="top" title="" 
												data-original-title="${engineLst.leads}">
											   <c:out value="View here" /> </a>
											   </div>
											</td>
											
						                   
						                </tr>
            						</c:forEach>
										
                                        
										
								   
								   </tbody>
								  <tfoot>
								    <tr>
								        <c:forEach begin="1" end="23" var="val">
										   <td></td>
										</c:forEach>
								        <td style="display:none"></td>
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
    		 "columnDefs": [
    		                { "width": "20%", "targets": 0 }
    		              ],
         	"sDom": 'T<"clear">lfrtip',
         	
         	"bDestroy":true,
         	"bSort":false,
         	"iDisplayLength" : 10,
         	"scrollX":true,
         	
         	
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
                $( api.column( 1 ).footer() ).html(
                    '<b>Total  </b>'
                );
       
     
                // Total over this page
                for(i=2;i<=22;i++)
                	{
                pageTotal = api
                    .column( i, { page: 'current'} )
                    .data()
                    .reduce( function (a, b) {
                        return intVal(a) + intVal(b);
                    }, 0 );
     
                $( api.column( i ).footer() ).html(
                        pageTotal
                    );
                	}
             
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
				width:100px !important;
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
          