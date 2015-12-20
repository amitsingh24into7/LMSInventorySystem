
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
function GetItemModelDetails(str)
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
				opt.value = a[i];
				opt.innerHTML = a[i];
				select.appendChild(opt);
			 }
			//alert(i); 
			//document.getElementById("engineMakeType").innerHTML = output;
			
			//innerHTML = output.join('');
			
	    }
	}
	http.send(null);
}
function Engine_Stock_Form_Validation()
{
	
	if(document.getElementById("region").value == "0")
	{
		alert("Please Select the Region");
		document.getElementById("region").focus();
		return false;
	}
	else if(document.getElementById("projectType").value == "0")
	{
		alert("Please Select the Project Type");
		document.getElementById("projectType").focus();
		return false;
	}
	else if(document.getElementById("txt_engine").value == "0")
	{
		alert("Please Select the Engine");
		document.getElementById("txt_engine").focus();
		return false;
	}
	else if(document.getElementById("txt_year").value== "0")
	{
		alert("Please Select the Year");
		document.getElementById("txt_year").focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m1budgetqty.value=='')
	{
		alert("Please Enter M1 Budget Qty");
		document.frm_engine_forcast.txt_m1budgetqty.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m1budgetvalue.value=='')
	{
		alert("Please Enter M1 Budget Value");
		document.frm_engine_forcast.txt_m1budgetvalue.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m2budgetqty.value=='')
	{
		alert("Please Enter M2 Budget Qty");
		document.frm_engine_forcast.txt_m2budgetqty.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m2budgetvalue.value=='')
	{
		alert("Please Enter M2 Budget Value");
		document.frm_engine_forcast.txt_m2budgetvalue.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m3budgetqty.value=='')
	{
		alert("Please Enter M3 Budget Qty");
		document.frm_engine_forcast.txt_m3budgetqty.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m3budgetvalue.value=='')
	{
		alert("Please Enter M3 Budget Value");
		document.frm_engine_forcast.txt_m3budgetvalue.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m4budgetqty.value=='')
	{
		alert("Please Enter M4 Budget Qty");
		document.frm_engine_forcast.txt_m4budgetqty.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m4budgetvalue.value=='')
	{
		alert("Please Enter M4 Budget Value");
		document.frm_engine_forcast.txt_m4budgetvalue.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m5budgetqty.value=='')
	{
		alert("Please Enter M5 Budget Qty");
		document.frm_engine_forcast.txt_m5budgetqty.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m5budgetvalue.value=='')
	{
		alert("Please Enter M5 Budget Value");
		document.frm_engine_forcast.txt_m5budgetvalue.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m6budgetqty.value=='')
	{
		alert("Please Enter M6 Budget Qty");
		document.frm_engine_forcast.txt_m6budgetqty.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m6budgetvalue.value=='')
	{
		alert("Please Enter M6 Budget Value");
		document.frm_engine_forcast.txt_m6budgetvalue.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m7budgetqty.value=='')
	{
		alert("Please Enter M7 Budget Qty");
		document.frm_engine_forcast.txt_m7budgetqty.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m7budgetvalue.value=='')
	{
		alert("Please Enter M7 Budget Value");
		document.frm_engine_forcast.txt_m7budgetvalue.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m8budgetqty.value=='')
	{
		alert("Please Enter M8 Budget Qty");
		document.frm_engine_forcast.txt_m8budgetqty.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m8budgetvalue.value=='')
	{
		alert("Please Enter M8 Budget Value");
		document.frm_engine_forcast.txt_m8budgetvalue.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m9budgetqty.value=='')
	{
		alert("Please Enter M9 Budget Qty");
		document.frm_engine_forcast.txt_m9budgetqty.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m9budgetvalue.value=='')
	{
		alert("Please Enter M9 Budget Value");
		document.frm_engine_forcast.txt_m9budgetvalue.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m10budgetqty.value=='')
	{
		alert("Please Enter M10 Budget Qty");
		document.frm_engine_forcast.txt_m10budgetqty.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m10budgetvalue.value=='')
	{
		alert("Please Enter M10 Budget Value");
		document.frm_engine_forcast.txt_m10budgetvalue.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m11budgetqty.value=='')
	{
		alert("Please Enter M11 Budget Qty");
		document.frm_engine_forcast.txt_m11budgetqty.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m11budgetvalue.value=='')
	{
		alert("Please Enter M11 Budget Value");
		document.frm_engine_forcast.txt_m11budgetvalue.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m12budgetqty.value=='')
	{
		alert("Please Enter M12 Budget Qty");
		document.frm_engine_forcast.txt_m12budgetqty.focus();
		return false;
	}
	else if(document.frm_engine_forcast.txt_m12budgetvalue.value=='')
	{
		alert("Please Enter M12 Budget Value");
		document.frm_engine_forcast.txt_m12budgetvalue.focus();
		return false;
	}
}
function setValue(region,projecttype,engine,fin_year,m1qty,m2qty,m3qty,m4qty,m5qty,m6qty,m7qty,m8qty,m9qty,m10qty,m11qty,m12qty,m1value,m2value,m3value,m4value,m5value,m6value,m7value,m8value,m9value,m10value,m11value,m12value)
{
	document.getElementById("region").value=region;
	document.getElementById("txt_engine").value=engine;
	document.getElementById("projectType").value=projecttype;
	document.getElementById("txt_year").value=fin_year;
	
	document.getElementById("txt_m1budgetqty").value=m1qty;
	document.getElementById("txt_m2budgetqty").value=m2qty;
	document.getElementById("txt_m3budgetqty").value=m3qty;
	document.getElementById("txt_m4budgetqty").value=m4qty;
	document.getElementById("txt_m5budgetqty").value=m5qty;
	document.getElementById("txt_m6budgetqty").value=m6qty;
	document.getElementById("txt_m7budgetqty").value=m7qty;
	document.getElementById("txt_m8budgetqty").value=m8qty;
	document.getElementById("txt_m9budgetqty").value=m9qty;
	document.getElementById("txt_m10budgetqty").value=m10qty;
	document.getElementById("txt_m11budgetqty").value=m11qty;
	document.getElementById("txt_m12budgetqty").value=m12qty;
	
	document.getElementById("txt_m1budgetvalue").value=m1value;
	document.getElementById("txt_m2budgetvalue").value=m2value;
	document.getElementById("txt_m3budgetvalue").value=m3value;
	document.getElementById("txt_m4budgetvalue").value=m4value;
	document.getElementById("txt_m5budgetvalue").value=m5value;
	document.getElementById("txt_m6budgetvalue").value=m6value;
	document.getElementById("txt_m7budgetvalue").value=m7value;
	document.getElementById("txt_m8budgetvalue").value=m8value;
	document.getElementById("txt_m9budgetvalue").value=m9value;
	document.getElementById("txt_m10budgetvalue").value=m10value;
	document.getElementById("txt_m11budgetvalue").value=m11value;
	document.getElementById("txt_m12budgetvalue").value=m12value;
	
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
              			
              			<li><a href="StockController?action=listBudgetStock&search=All">View Stock</a></li>
              		
			          </ul>
        		</li>
				  
				  
				</ol>
			</div>
		</div>
            <!-- /.row -->
			<div class="row">
			<div class="col-lg-12">
<c:if test="${not empty message}">
				    
             <div class="alert alert-success alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                               ${message}
                            </div>
			</c:if>
<div class="panel panel-primary">
	<div class="panel-heading">
	    Engine Budget Stock Entry
	</div>
<div class="panel-body">


<form name="frm_engine_forcast" action="StockController" method="post" data-toggle="validator" role="form" novalidate="true" onsubmit="return Engine_Stock_Form_Validation()">

	<div class="row">
	
		<div class="col-lg-2">
			<div class="form-group">
			<label>Select Region</label> <span class="text-alert-danger">*</span>
			<select path="region" id="region"  name="region" class="form-control">
											 	<option value="0">Select Region </option>
												<c:forEach items="${regionType}" var="region"> 
														<option value="${region}" ${region == userRegion ? 'selected="selected"' : ''}>${region}</option>
											    </c:forEach>
						                    </select>
			
			</div>
		</div>
		
		<div class="col-lg-2">
		<div class="form-group">
												<label>Project Type</label> <span class="text-alert-danger">*</span>
                                            	<select  class="form-control" name="projectType" id="projectType" >
                                            		<option value="0">Select Type</option>
													<c:forEach items="${projectTypes}" var="projectType"> 
														<option value="${projectType}" ${projectType == selprojectType ? 'selected="selected"' : ''}>${projectType}</option>
											    	</c:forEach>
						                    	</select>
						                    	
					</div>
		</div>
											
		<div class="col-lg-2">
			<div class="form-group">
				<label>Engine Name</label><span class="text-alert-danger">*</span>
					<select id='txt_engine' name='txt_engine' style="width: 100%" class="form-control" onchange="return callCustomer(this.value,'engine')">
						<option value="0">Select</option>
						
					</select>
				
			</div>
		</div>

		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Year</label><span class="text-alert-danger">*</span>
				
				<select name="txt_year" class="form-control" id="txt_year">
				<option value="0">Select</option>
					<option>${curFinYear}</option>
					<option>${prevFinYear}</option>
					
				</select>
				
			</div>
		</div>
		
		<div class="col-lg-2" style="display:none">
			<div class="form-group">
				<label>Stock As On</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_stock_as_on" value="${txt_stock_as_on}" class="form-control" name="txt_stock_as_on" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2" style="display:none">
			<div class="form-group">
				<label>Transit</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_transit" value="${txt_transit}" class="form-control" name="txt_transit" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Jan  Qty</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m1budgetqty" value="${txt_m1budgetqty}" class="form-control" name="txt_m1budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
			<label>Jan   Value</label> <span class="text-alert-danger">*</span>
			<input  type="text" id="txt_m1budgetvalue" value="${txt_m1budgetvalue}" class="form-control" name="txt_m1budgetvalue" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
           	<div class="help-block with-errors"></div>
			</div>
		</div>
	
	</div>
	
	<div class="row">
		<div class="col-lg-2">
			<div class="form-group">
				<label>Feb  Qty</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m2budgetqty" value="${txt_m2budgetqty}" class="form-control" name="txt_m2budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Feb   Value</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m2budgetvalue" value="${txt_m2budgetvalue}" class="form-control" name="txt_m2budgetvalue" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Mar  Qty</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m3budgetqty" value="${txt_m3budgetqty}" class="form-control" name="txt_m3budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Mar   Value</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m3budgetvalue" value="${txt_m3budgetvalue}" class="form-control" name="txt_m3budgetvalue" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Apr  Qty</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m4budgetqty" value="${txt_m4budgetqty}" class="form-control" name="txt_m4budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Apr   Value</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m4budgetvalue" value="${txt_m4budgetvalue}" class="form-control" name="txt_m4budgetvalue" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
	</div>

	<div class="row">

		
		<div class="col-lg-2">
			<div class="form-group">
				<label>May  Qty</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m5budgetqty" value="${txt_m5budgetqty}" class="form-control" name="txt_m5budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>May  Value</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m5budgetvalue" value="${txt_m5budgetvalue}" class="form-control" name="txt_m5budgetvalue" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Jun  Qty</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m6budgetqty" value="${txt_m6budgetqty}" class="form-control" name="txt_m6budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Jun  Value</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m6budgetvalue" value="${txt_m6budgetvalue}" class="form-control" name="txt_m6budgetvalue" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Jul  Qty</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m7budgetqty" value="${txt_m7budgetqty}" class="form-control" name="txt_m7budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Jul  Value</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m7budgetvalue" value="${txt_m7budgetvalue}" class="form-control" name="txt_m7budgetvalue" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
	</div>

	
	<div class="row">
	
		
	
		<div class="col-lg-2">
			<div class="form-group">
				<label>Aug  Qty</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m8budgetqty" value="${txt_m8budgetqty}" class="form-control" name="txt_m8budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Aug  Value</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m8budgetvalue" value="${txt_m8budgetvalue}" class="form-control" name="txt_m8budgetvalue" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Sep  Qty</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m9budgetqty" value="${txt_m9budgetqty}" class="form-control" name="txt_m9budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Sep  Value</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m9budgetvalue" value="${txt_m9budgetvalue}" class="form-control" name="txt_m9budgetvalue" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Oct  Qty</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m10budgetqty" value="${txt_m10budgetqty}" class="form-control" name="txt_m10budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Oct  Value</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m10budgetvalue" value="${txt_m10budgetvalue}" class="form-control" name="txt_m10budgetvalue" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
	
		
	</div>	
	
	<div class="row">
		<input path="createdBy" type="hidden" id="action" value="${action}" class="form-control" name="action" />
		<input path="createdBy" type="hidden" id="createdBy" value="${userID}" class="form-control" name="createdBy" />
		
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Nov  Qty</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m11budgetqty" value="${txt_m11budgetqty}" class="form-control" name="txt_m11budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Nov  Value</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m11budgetvalue" value="${txt_m11budgetvalue}" class="form-control" name="txt_m11budgetvalue" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
	
		<div class="col-lg-2">
			<div class="form-group">
				<label>Dec  Qty</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m12budgetqty" value="${txt_m12budgetqty}" class="form-control" name="txt_m12budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Dec  Value</label><span class="text-alert-danger">*</span>
				<input  type="text" id="txt_m12budgetvalue" value="${txt_m12budgetvalue}" class="form-control" name="txt_m12budgetvalue" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group" style="margin-top:15px">
			
				<input type="submit" id="btn_create" name="btn_create" value="Submit" class="btn btn-success"/>
				<input type="reset" id="btn_reset" name="btn_reset" value="Reset" class="btn btn-warning"/>
				<div align="center" id="addMsg" style="color: green;"></div>
			</div>
		</div>
	
	</div>
</form>
	
</div>	
</div>
</div>
</div>

<!-- Display Table -->

<div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                           Stock Budget Details
                           
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                     
                             <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            
                                            <th style="width:100px !important">Region</th>
                                            <th style="width:100px">Project Type</th>
                                            <th style="width:100px">Engine</th>
                                             <th>Year</th>
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
                                             <th>Action</th>
                                        </tr>
                                    </thead>
                                     


								   <tbody>
								 
								   <c:forEach items="${engingBudList}" var="engineLst">
						                <tr class="gradeA odd" role="row">
						                    <td>
						                    	${engineLst.REGION}
                                			</td>
                                			 <td>
											  <c:out value="${engineLst.PROJECT_TYPE}" /> 
                                			</td>
                                			
						                   
						                   <td>
						                   		<c:out value="${engineLst.ENGINE_NAME}" />
						                   </td>
						                	
						                	<td>
						                   		<c:out value="${engineLst.FIN_YEAR}" />
						                   </td>
						                	
						                	
											<td>${engineLst.m1budget}</td>
											<td>${engineLst.m2budget}</td>
											<td>${engineLst.m3budget}</td>
											<td>${engineLst.m4budget}</td>
											<td>${engineLst.m5budget}</td>
											<td>${engineLst.m6budget}</td>
											<td>${engineLst.m7budget}</td>
											<td>${engineLst.m8budget}</td>
											<td>${engineLst.m9budget}</td>
											<td>${engineLst.m10budget}</td>
											<td>${engineLst.m11budget}</td>
											<td>${engineLst.m12budget}</td>											<td>
											<a href="#" onclick="setValue('${engineLst.REGION}','${engineLst.PROJECT_TYPE}','${engineLst.ENGINE_NAME}','${engineLst.FIN_YEAR}','${engineLst.m1budget}','${engineLst.m2budget}','${engineLst.m3budget}','${engineLst.m4budget}','${engineLst.m5budget}','${engineLst.m6budget}','${engineLst.m7budget}','${engineLst.m8budget}','${engineLst.m9budget}','${engineLst.m10budget}','${engineLst.m11budget}','${engineLst.m12budget}','${engineLst.m1budgetvalue}','${engineLst.m2budgetvalue}','${engineLst.m3budgetvalue}','${engineLst.m4budgetvalue}','${engineLst.m5budgetvalue}','${engineLst.m6budgetvalue}','${engineLst.m7budgetvalue}','${engineLst.m8budgetvalue}','${engineLst.m9budgetvalue}','${engineLst.m10budgetvalue}','${engineLst.m11budgetvalue}','${engineLst.m12budgetvalue}')
											
											
											">
											<img src="dist/images/edit.png">
											
											</a>
											</td>
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
GetItemModelDetails('Engine');

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
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
       
        
    });
    </script>


</body></html>