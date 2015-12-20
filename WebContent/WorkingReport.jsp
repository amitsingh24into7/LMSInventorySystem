
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>

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

function callReport()
{

	//alert("asda"+str);
	
	
	
	str=document.getElementById('regionType').value;
	userid=document.getElementById('createdBy').value;
	
	if(str=="0")
		{
			alert("Please choose Region");
			document.getElementById('regionType').focus();
			return false;
		}
	else
		{
	
		document.getElementById("loadingCreatingFile").style.display='inline';
		document.getElementById("downloadForm").style.display='none';		
	var http = new getXMLHttpRequestObject();
	 
	var url = "WorkingReport";
	var parameters = "action=viewWorking&"+"region="+str+"&user="+userid;
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	        //alert(http.responseText);
	        document.getElementById("loadingCreatingFile").style.display='none';
	        
	        var link=document.getElementById("downlaodLink");
			 link.setAttribute("href","uploads/excelTemplate/"+http.responseText);
			 link.setAttribute("class", "btn btn-info");
			 
			 link.innerHTML='Download Sheet';
			 document.getElementById("downloadForm").style.display='block';
			//a=JSON.parse(http.responseText);
			//var output = [];
			
			
	    }
	   
	}
	http.send(null);
	}
}


</script>
<div id="page-wrapper">
<div class="row">
<div class="col-lg-12" style="margin-top:10px">
<c:if test="${not empty message}">
				    
            <c:choose> 
            
            <c:when test="${message=='There are some error while updating sheet , Either Sheet is not proper or there are some error , please contact administrator'}">
            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                ${message}
                            </div>
            </c:when>
            <c:otherwise>
             <div class="alert alert-success alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                ${message}
                            </div>
                            
              
              </c:otherwise>
              
              </c:choose>
			</c:if>
<div class="panel panel-primary">
	<div class="panel-heading">
	   Working Report
	</div>
<div class="panel-body">



<button class="btn btn-lg btn-warning" id="loadingCreatingFile"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> Processing and downloading your report, please wait.......</button>


	<div class="row" id="downloadForm">
	
		
		
		
		<div class="col-lg-4">
		
		<div class="form-group">
												<label>Region</label> <span class="text-alert-danger">*</span>
                                            	<select  class="form-control" name="regionType" id="regionType" >
                                            		<option value="0">Select Region</option>
													<c:forEach items="${regionTypes}" var="regionType"> 
														<option value="${regionType}" ${regionType == userRegion ? 'selected="selected"' : ''}>${regionType}</option>
											    	</c:forEach>
						                    	</select>
						                    	
					</div>
		</div>
		
		
		
											
		<div class="col-lg-4">
			<div class="form-group" style="margin-top:20px">
				<input type="button" id="btn_download" name="btn_download" value="Click To Download Report" class="btn btn-success" onclick="return callReport();"/>
				<a href="#" id="downlaodLink" target="_blank"></a>
			</div>
		</div>
	
	</div>

<div class="row">
	
		
<script>
function hidesubmit()
{
	document.getElementById("btn_create").style.display='none';	
}
</script>		
<form name="frm_engine_forcast" action="WorkingReport?action=all" method="post" enctype="multipart/form-data" data-toggle="validator" role="form" novalidate="true" onsubmit="hidesubmit()">
		<div class="col-lg-8">
						<input path="createdBy" type="hidden" id="action" value="${action}" class="form-control" name="action" />
				<input path="createdBy" type="hidden" id="createdBy" value="${userID}" class="form-control" name="createdBy" />
		
		
		
																	<div class="form-group">
																		<label path="costsummary">Choose Working Report To Upload</label>
																		<input type="file"  id="myfile1" name="myfile1" class="form-control">
																		
																	</div>
																</div>
																
		
	
		
		<div class="col-lg-4">
			<div class="form-group" style="margin-top:10px">
			
					<input type="submit" id="btn_create" name="btn_create" value="Submit" class="btn btn-success"/>
					
			</div>
		</div>
		</form>
</div>


	




	
</div>	
</div>
</div>
</div>

</div>

    
    <script>
//GetItemModelDetails('Engine');
document.getElementById("loadingCreatingFile").style.display='none';

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




<!-- Footer Part Different JS will get add in Differnt Pages -->
    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.js"></script>
	<script src="bower_components/jquery/dist/jquery.form.js"></script>
     <script src="dist/js/fileUploadScript.js"></script>
    

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    
    <!-- DataTables JavaScript -->
    <script src="bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>
   <!-- Morris Charts JavaScript -->
   

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
	<script src="dist/js/script.js"></script>
	<script src="dist/js/validator.js"></script>
	
	<style>
	
	.form-group ul {
	width: 90%;
	border: 1px solid #eaeaea;
	position: absolute;
	z-index: 9;
	background: #f3f3f3;
	list-style: none;
}
.form-group ul li {
	margin-left: -30px;
	border-bottom:1px solid #eaeaea;
}
.form-group ul li:hover {
	background: #eaeaea;
}
	</style>
	
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
       
        
    });
    </script>


</body></html>