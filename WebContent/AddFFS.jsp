
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

function setValue(projecttype,engine,stock,transit,m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12)
{
	
	document.getElementById("projectType").value=projecttype;
	document.getElementById("txt_engine").value=engine;
	document.getElementById("txt_stock_as_on").value=stock;
	document.getElementById("txt_transit").value=transit;
	
	document.getElementById("txt_m1budgetqty").value=m1;
	document.getElementById("txt_m2budgetqty").value=m2;
	document.getElementById("txt_m3budgetqty").value=m3;
	document.getElementById("txt_m4budgetqty").value=m4;
	document.getElementById("txt_m5budgetqty").value=m5;
	document.getElementById("txt_m6budgetqty").value=m6;
	document.getElementById("txt_m7budgetqty").value=m7;
	document.getElementById("txt_m8budgetqty").value=m8;
	document.getElementById("txt_m9budgetqty").value=m9;
	document.getElementById("txt_m10budgetqty").value=m10;
	document.getElementById("txt_m11budgetqty").value=m11;
	document.getElementById("txt_m12budgetqty").value=m12;
	
	
	
}

</script>
<div id="page-wrapper">
        
         <div class="row" >
			<div class="col-lg-12" style="margin-top:5px">
	           <ol class="breadcrumb">
				 <li><a href="index.jsp"><i class="fa fa-home fa-fw"></i> Home</a></li>
				 <li class="dropdown active">
        			  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
        			  aria-haspopup="true" aria-expanded="false">FFS Report <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            		            
			           
              			<li><a href="FFSController?action=createFFS">Add FFS Report</a></li>
              		
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
	    FFS Entry
	</div>
<div class="panel-body">


<form name="frm_engine_forcast" action="FFSController" method="post" data-toggle="validator" role="form" novalidate="true">

	<div class="row">
	
		
		
		<div class="col-lg-2">
		<div class="form-group">
												<label>Project Type</label> <span class="text-alert-danger">*</span>
                                            	<select  class="form-control" name="projectType" id="projectType" >
                                            		<option value="0">Select Type</option>
													<c:forEach items="${projectTypes}" var="projectType"> 
														<option value="${projectType}" ${projectType == selprojectType ? 'selected="selected"' : ''}>${projectType}</option>
											    	</c:forEach>
						                    	</select>
						                    	<errors path="leadType" cssClass="error"/>
					</div>
		</div>
											
		<div class="col-lg-2">
			<div class="form-group">
				<label>Engine Name</label>
					<select id='txt_engine' name='txt_engine' style="width: 100%" class="form-control">
						<option value="0">Select</option>
						
					</select>
				
			</div>
		</div>


		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Stock As On</label>
				<input  type="text" id="txt_stock_as_on" value="${txt_stock_as_on}" class="form-control" name="txt_stock_as_on" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Transit</label>
				<input  type="text" id="txt_transit" value="${txt_transit}" class="form-control" name="txt_transit" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Jan </label>
				<input  type="text" id="txt_m1budgetqty" value="${txt_m1budgetqty}" class="form-control" name="txt_m1budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Feb </label>
				<input  type="text" id="txt_m2budgetqty" value="${txt_m2budgetqty}" class="form-control" name="txt_m2budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
	
	</div>
	
	<div class="row">
		
		
		
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Mar </label>
				<input  type="text" id="txt_m3budgetqty" value="${txt_m3budgetqty}" class="form-control" name="txt_m3budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Apr </label>
				<input  type="text" id="txt_m4budgetqty" value="${txt_m4budgetqty}" class="form-control" name="txt_m4budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>May </label>
				<input  type="text" id="txt_m5budgetqty" value="${txt_m5budgetqty}" class="form-control" name="txt_m5budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Jun </label>
				<input  type="text" id="txt_m6budgetqty" value="${txt_m6budgetqty}" class="form-control" name="txt_m6budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Jul </label>
				<input  type="text" id="txt_m7budgetqty" value="${txt_m7budgetqty}" class="form-control" name="txt_m7budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Aug </label>
				<input  type="text" id="txt_m8budgetqty" value="${txt_m8budgetqty}" class="form-control" name="txt_m8budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		
		
	</div>


	
	<div class="row">
	
		
		
		
		<div class="col-lg-2">
			<div class="form-group">
				<label>Sep </label>
				<input  type="text" id="txt_m9budgetqty" value="${txt_m9budgetqty}" class="form-control" name="txt_m9budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Oct </label>
				<input  type="text" id="txt_m10budgetqty" value="${txt_m10budgetqty}" class="form-control" name="txt_m10budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Nov </label>
				<input  type="text" id="txt_m11budgetqty" value="${txt_m11budgetqty}" class="form-control" name="txt_m11budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<div class="col-lg-2">
			<div class="form-group">
				<label>Dec </label>
				<input  type="text" id="txt_m12budgetqty" value="${txt_m12budgetqty}" class="form-control" name="txt_m12budgetqty" pattern="^-?\d+\.?\d*$" oninvalid="setCustomValidity('Only Numeric Allowed ')" onchange="try{setCustomValidity('')}catch(e){}"   />
                <div class="help-block with-errors"></div>
			</div>
		</div>
		<input path="createdBy" type="hidden" id="action" value="${action}" class="form-control" name="action" />
				<input path="createdBy" type="hidden" id="createdBy" value="${userID}" class="form-control" name="createdBy" />
		<div class="col-lg-2">
			<div class="form-group" style="margin-top:10px">
			
					<input type="submit" id="btn_create" name="btn_create" value="Submit" class="btn btn-success"/>
					<input type="button" id="btn_reset" name="btn_reset" value="Reset" class="btn btn-warning"/>
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
                                             <th>Action</th>
                                            
                                           
                                           
                                           
                                          
                                           
                                            
                                             
                                            
                                            
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
											
											<td>
											<a href="#" onclick="setValue('${engineLst.PROJECT_TYPE}','${engineLst.ENGINE_NAME}','${engineLst.STOCK_AS_ON}','${engineLst.TRANSIT}','${engineLst.m1qty}','${engineLst.m2qty}','${engineLst.m3qty}','${engineLst.m4qty}','${engineLst.m5qty}','${engineLst.m6qty}','${engineLst.m7qty}','${engineLst.m8qty}','${engineLst.m9qty}','${engineLst.m10qty}','${engineLst.m11qty}','${engineLst.m12qty}')
											
											
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