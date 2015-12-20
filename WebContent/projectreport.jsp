<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>




<div id="page-wrapper">

<div class="row">
                <div class="col-lg-12" style="margin-top:10px">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Project Report
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                             <div class="dataTable_wrapper">
                            
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Lead Id</th>
<th>Transaction Type</th>
<th>Project Type</th>
<th>Enquiry Type</th>

<th>Project Exe State</th>
<th>Project Exe Place</th>

<th>Lead Creation Region</th>
<th>Lead Creation State</th>
<th>Lead Creation Place</th>



<th>Creation Date</th>
<th>Created By</th>
<th>Owner Id</th>
<th>Owner Name</th>
<th>Project Reference</th>
<th>Dg Type</th>
<th>Segment</th>
<th>Competitions</th>
<th>Lead Chances</th>
<th>Lead Status</th>

<th>Consultant Name</th>
<th>Consultant Person Name</th>
<th>Consultant Mobile No</th>

<th>Customer Name</th>
<th>Customer Email Address</th>
<th>Customer Address</th>
<th>Customer Mobileno</th>

<th>End Person Customer Name</th>
<th>End Person Customer Contactno</th>

<th>Pmc Name</th>
<th>Pmc Contact Person</th>
<th>Pmc Contact No</th>

<th>Low Side Supply Basic</th>
<th>Low Side Labour Basic</th>
<th>Low Side Margin</th>
<th>Project Margin</th>
<th>Dg Ed Value</th>
<th>Project Total</th>
<th> Rating</th>
<th> Item Type</th>
<th> Engine Make</th>
<th> Engine Model</th>

<th> Voltage</th>
<th> Hz</th>
<th> Qty</th>
<th> Cooling System</th>
<th> Amount</th>


											
                                            
                                         
                                        </tr>
                                    </thead>


								   <tbody>
								   <c:forEach items="${reportdetails}" var="report">
						                <tr class="gradeA odd" role="row">
						                    <td>${report.LEAD_ID}</td>
											<td>${report.TRANSACTION_TYPE}</td>
											<td>${report.PROJECT_TYPE}</td>
											<td>${report.ENQUIRY_TYPE}</td>
											<td>${report.PROJECT_EXE_STATE}</td>
											<td>${report.PROJECT_EXE_PLACE}</td>
											<td>${report.LEAD_CREATION_REGION}</td>
											<td>${report.LEAD_CREATION_STATE}</td>
											<td>${report.LEAD_CREATION_PLACE}</td>
											<td>${report.CREATION_DATE}</td>
											<td>${report.CREATED_BY}</td>
											
											<td>${report.OWNER_ID}</td>
											<td>${report.OWNER_NAME}</td>
											<td>${report.PROJECT_REFERENCE}</td>
											<td>${report.DG_TYPE}</td>
											<td>${report.SEGMENT}</td>
											<td>${report.COMPETITIONS}</td>
											<td>${report.LEAD_CHANCES}</td>
											<td>${report.LEAD_STATUS}</td>
											
											<td>${report.CONSULTANT_NAME}</td>
											<td>${report.CONSULTANT_PERSON_NAME}</td>
											<td>${report.CONSULTANT_MOBILE_NO}</td>
											
											<td>${report.CUSTOMER_NAME}</td>
											<td>${report.CUSTOMER_EMAIL_ADDRESS}</td>
											<td>${report.CUSTOMER_ADDRESS}</td>
											<td>${report.CUSTOMER_MOBILENO}</td>
											
											<td>${report.END_PERSON_CUSTOMER_NAME}</td>
											<td>${report.END_PERSON_CUSTOMER_CONTACTNO}</td>
											
											<td>${report.PMC_NAME}</td>
											<td>${report.PMC_CONTACT_PERSON}</td>
											<td>${report.PMC_CONTACT_NO}</td>
											
											<td>${report.LOW_SIDE_SUPPLY_BASIC}</td>
											<td>${report.LOW_SIDE_LABOUR_BASIC}</td>
											<td>${report.LOW_SIDE_MARGIN}</td>
											<td>${report.PROJECT_MARGIN}</td>
											<td>${report.DG_ED_VALUE}</td>
											<td>${report.PROJECT_TOTAL}</td>
											
											<td>${report.DTL_RATING}</td>
											<td>${report.DTL_ITEM_TYPE}</td>
											<td>${report.DTL_ENGINE_MAKE}</td>
											<td>${report.DTL_ENGINE_MODEL}</td>
											
											<td>${report.DTL_VOLTAGE}</td>
											<td>${report.DTL_HZ}</td>
											<td>${report.DTL_QTY}</td>
											<td>${report.DTL_COOLING_SYSTEM}</td>
											<td>${report.DTL_AMOUNT}</td>
											

						                  </tr> 
									</c:forEach>	
								   
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
        	"iDisplayLength" : 5,
        	"scrollX": true,
        	
        	"aLengthMenu": [[5,10, 20,50,100,-1], [5,10, 20,50,100, "All"]]
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
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
       
        
    });
    </script>

</body></html>