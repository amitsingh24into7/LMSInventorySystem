		<!-- Tag Lib Directories to eliminate the JSP Directives -->
		
		
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<script>
var jsdata="";
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
        par.removeChild(sel.options[i] );
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

function storeCustomerDetails()
{
	if(checkInputError())
	{
	var http = new getXMLHttpRequestObject();
	var url = "CustomerController";
	
	leadID=document.getElementById("leadID").value;
	actionValue=document.getElementById("actionValue").value;
	createdBy=document.getElementById("createdBy").value;
	customerTempAddress=document.getElementById("customerTempAddress").value;
	
		var customerType=document.getElementById("customerType").value;
		
		var customerName;
		if(customerType=="Existing")
			{
				customerName=document.getElementById("customerName").value;
				
				if(customerName==0)
				{
					alert("Please choose Customer");
					document.getElementById("customerName").focus();
					return false
				}
			}
		else
			{
				customerName=document.getElementById("mcustomerName").value;
				if(customerName=="" || customerName=="undefined" || customerName==undefined)
				{
					alert("Please Enter Customer Name");
					document.getElementById("mcustomerName").focus();
					return false;
				}
			}

		var customerContactNumber=document.getElementById("customerContactNumber").value;
		var customerAlternateNumber=document.getElementById("customerAlternateNumber").value;
		var customerEmail=document.getElementById("customerEmail").value;
		
		var customerTempAddress=document.getElementById("customerTempAddress").value;
		
		var customerConsultant=document.getElementById("customerConsultant").value;
		var customerConsultantType=document.getElementById("customerConsultantType").value;
		
		var customerConsultantNumber=document.getElementById("customerConsultantNumber").value;
		var customerConsultantPersonName=document.getElementById("customerConsultantPersonName").value;
		
		var customerBillingAddress1=document.getElementById("customerBillingAddress1").value;
		var customerBillingAddress2=document.getElementById("customerBillingAddress2").value;
		var customerBillingAddress3=document.getElementById("customerBillingAddress3").value;
		var customerBillingAddress4=document.getElementById("customerBillingAddress4").value;

		sameAddress=document.getElementById("customerAddressCheckbox").checked;
		
		if(sameAddress)
		{
			var customerDeliveryAddress1=customerBillingAddress1;
			var customerDeliveryAddress2=customerBillingAddress2;
			var customerDeliveryAddress3=customerBillingAddress3;
			var customerDeliveryAddress4=customerBillingAddress4;
			
			
		}
		else{
			var customerDeliveryAddress1=document.getElementById("customerDeliveryAddress1").value;
			var customerDeliveryAddress2=document.getElementById("customerDeliveryAddress2").value;
			var customerDeliveryAddress3=document.getElementById("customerDeliveryAddress3").value;
			var customerDeliveryAddress4=document.getElementById("customerDeliveryAddress4").value;
			

		}
		
		var customerECCNO=document.getElementById("customerECCNO").value;
		var customerCSTNO=document.getElementById("customerCSTNO").value;
		var customerTINNO=document.getElementById("customerTINNO").value;
		var customerIECNO=document.getElementById("customerIECNO").value;
		
		var pmc=document.getElementById("pmc").value;
		var pmcType=document.getElementById("pmcType").value;
		var pmcContact=document.getElementById("pmcContact").value;
		var pmcAlternateContact=document.getElementById("pmcAlternateContact").value;
		var pmcEmail=document.getElementById("pmcEmail").value;
		var pmcPerson=document.getElementById("pmcPerson").value;
		
		
		
		var customergroup=document.getElementById("customergroup").value;
		var customergroup_person=document.getElementById("customergroup_person").value;
		var customergroup_person_contact=document.getElementById("customergroup_person_contact").value;
		
	
	var parameters = "leadID="+leadID+"&customerType="+customerType+"&customerName="+customerName+"&customerContactNumber="+customerContactNumber;
	parameters=parameters+"&customerType="+customerType+"&customerName="+customerName+"&customerContactNumber="+customerContactNumber+"&customerAlternateNumber="+customerAlternateNumber;
parameters=parameters+"&customerEmail="+customerEmail+"&customerConsultant="+customerConsultant+"&customerConsultantNumber="+customerConsultantNumber+"&customerConsultantPersonName=";
parameters=parameters+customerConsultantPersonName+"&customerBillingAddress1="+customerBillingAddress1+"&customerBillingAddress2="+customerBillingAddress2+"&customerBillingAddress3="+customerBillingAddress3+"&customerBillingAddress4="+customerBillingAddress4;
parameters=parameters+"&customerDeliveryAddress1="+customerDeliveryAddress1+"&customerDeliveryAddress2="+customerDeliveryAddress2+"&customerDeliveryAddress3="+customerDeliveryAddress3 +"&customerDeliveryAddress4="+customerDeliveryAddress4;
parameters=parameters+"&customerECCNO="+customerECCNO+"&customerCSTNO="+customerCSTNO +"&customerTINNO="+customerTINNO+"&customerIECNO="+customerIECNO;
parameters=parameters+"&pmc="+pmc+"&pmcContact="+pmcContact+"&pmcAlternateContact="+pmcAlternateContact+"&pmcEmail="+pmcEmail+"&pmcPerson="+pmcPerson;
parameters=parameters+"&customergroup="+customergroup+"&customergroup_person="+customergroup_person+"&customergroup_person_contact="+customergroup_person_contact+"&createdBy="+createdBy+"&actionValue="+actionValue+"&customerTempAddress="+customerTempAddress+"&pmcType="+pmcType+"&customerConsultantType="+customerConsultantType;
//alert(parameters);
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	       // alert(http.responseText);
	        if(http.responseText=="Done")
	        	{
	       // 		alert("asd");
	        		//document.getElementById("customerButton").style.display='none';
	        		
	        		document.getElementById("customerDetailsTab").className = document.getElementById("customerDetailsTab").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        		document.getElementById("customerDetails").className = document.getElementById("customerDetails").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        		
	        		
	        		document.getElementById("itemDetails").className = document.getElementById("customerDetails").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        			      
	        		document.getElementById("customerDetailsTab").className="";
	        		document.getElementById("customerDetails").className = "tab-pane fade";
					
	        		document.getElementById("itemDetailsTab").className = "active";
	        		document.getElementById("itemDetails").className = "tab-pane fade active in";
	        		
	        		
	        		
	        	}
	        else
	        	{
	        		alert("Database is Responding slowly,please wait");
	        	}
	    }
	}
	http.send(null);
	}
	else{
		alert(" Please correc error value");
		return false;
	}

}

function MoveToCommentsSection()
{
				
	        		
	        		
					document.getElementById("itemDetailsTab").className = document.getElementById("itemDetailsTab").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        		document.getElementById("itemDetails").className = document.getElementById("itemDetails").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        		
	        		
	        		document.getElementById("settings").className = document.getElementById("settings").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        			      
	        		document.getElementById("itemDetailsTab").className="";
	        		document.getElementById("itemDetails").className = "tab-pane fade";
					
	        		document.getElementById("settingsTab").className = "active";
	        		document.getElementById("settings").className = "tab-pane fade active in";
}
function storeHeaderDetails()
{
	
	
	if(checkInputError())
	{
	
	var http = new getXMLHttpRequestObject();
	var url = "LeadHeaderController";
	
	leadID=document.getElementById("leadID").value;
	actionValue=document.getElementById("actionValue").value;
	projectType=document.getElementById("projectType").value;
	projectExeState=document.getElementById("projectExeState").value;
	projectExePlace=document.getElementById("projectExePlace").value;

	dgType=document.getElementById("dgType").value;
	projectReference=document.getElementById("projectReference").value;
	segment=document.getElementById("segment").value;
	transactionType=document.getElementById("transactionType").value;
	enquiryType=document.getElementById("enquiryType").value;
	LeadExeDate=document.getElementById("LeadExeDate").value;
	DGDeliveryDate=document.getElementById("DGDeliveryDate").value;
	ProjectClosureDate=document.getElementById("ProjectClosureDate").value;
	leadStatus=document.getElementById("leadStatus").value;
	leadChances=document.getElementById("leadChances").value;
	
	if(projectType==0)
	{
		alert("Please choose Project Type");
		document.getElementById("projectType").focus();
		return false;
	}
	if(projectExeState==0)
	{
		alert("Please choose Project Execution State");
		document.getElementById("projectExeState").focus();
		return false;

	}
	if(projectExePlace==0)
	{
		alert("Please choose Project Execution Place");
		document.getElementById("projectExePlace").focus();
		return false;

	}
	if(dgType==0)
	{
		alert("Please choose DG Type");
		document.getElementById("dgType").focus();
		return false;

	}	
	if(segment==0)
	{
		alert("Please choose Segment");
		document.getElementById("segment").focus();
		return false;
	
	}
	if(transactionType==0)
	{
		alert("Please choose Transaction Type");
		document.getElementById("transactionType").focus();
		return false;
	
	}
	if(enquiryType==0)
	{
		alert("Please choose Enquiry Type");
		document.getElementById("enquiryType").focus();
		return false;
	
	}
	if(LeadExeDate==0)
	{
		alert("Please Enter Lead Execution Date");
		document.getElementById("LeadExeDate").focus();
		return false;

	}
	
	if(LeadExeDate=="" || LeadExeDate=="undefined" || LeadExeDate==undefined)
	{
		alert("Please Enter Lead Execution Date");
		document.getElementById("LeadExeDate").focus();
		return false;

	}

	competitions=document.getElementById("competitions").value;
	ownerID=document.getElementById("ownerID").value;
	ownerName=document.getElementById("ownerName").value;
	createdBy=document.getElementById("createdBy").value;
	
	var parameters = "leadID="+leadID+"&projectType="+projectType+"&projectExeState="+projectExeState+"&projectExePlace="+projectExePlace+"&dgType="+dgType+"&projectReference="+projectReference+"&segment="+segment+"&transactionType="+transactionType+"&enquiryType="+enquiryType+"&leadStatus="+leadStatus+"&leadChances="+leadChances+"&competitions="+competitions+"&ownerID="+ownerID+"&ownerName="+ownerName+"&createdBy="+createdBy+"&actionValue="+actionValue+"&LeadExeDate="+LeadExeDate+"&DGDeliveryDate="+DGDeliveryDate+"&ProjectClosureDate="+ProjectClosureDate;
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	       // alert(http.responseText);
	        if(http.responseText=="Done")
	        	{
	       // 		alert("asd");
	        		MoveToCustomerTab();

	        	}
	        else
	        	{
	        		alert("Database is Responding slowly,please wait");
	        	}
	    }
	}
	http.send(null);
	}
	else{
		
		alert(" Please correct error value");
		return false;
	}
}

function MoveToCustomerTab()
{
				
					//document.getElementById("headerButton").style.display='none';
	        		
	        		document.getElementById("homeTab").className = document.getElementById("homeTab").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        		document.getElementById("home").className = document.getElementById("home").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        		
	        		
	        		document.getElementById("customerDetails").className = document.getElementById("customerDetails").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        			      
	        		document.getElementById("homeTab").className="";
	        		document.getElementById("home").className = "tab-pane fade";
	        		document.getElementById("customerDetailsTab").className = "active";
	        		document.getElementById("customerDetails").className = "tab-pane fade active in";
				
}

function deleteLeadItem(id,leadID)
{
	//alert("Hello"+leadID);
	//alert("Hello"+id);
	var http = new getXMLHttpRequestObject();
	var url = "DeleteLeadItems";
	
	var parameters = "leadID="+leadID+"&id="+id;
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	      //  alert(http.responseText);
			jsdata=JSON.parse(http.responseText);
			 
			tableBody = document.getElementById('tableBody');
			var output='';
																		
			 for(var i=0; i < jsdata.length; i++)
			 {
				output += '<tr>';
				output += '<td>';
				output += jsdata[i].DTL_RATING;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_ENGINE_MODEL;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_ALTERNATOR_MODEL; 
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_VOLTAGE;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_QTY;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_AMOUNT;
				output += '</td>';
				output += '<td>';
				output += "<a href=javascript:deleteLeadItem('"+jsdata[i].DTL_ID+"','"+jsdata[i].LEAD_ID+"')>Delete</a>";
				output += '</td>';
				output += '</tr>';
				
			 }
			 
			 tableBody.innerHTML=output;
			
	       
	    }
	}
	http.send(null);
	
	
}
function storeItemDetails()
{
	
	if(checkInputError())
	{
	var http = new getXMLHttpRequestObject();
	var url = "LeadItemDetailsController";
	
	
	leadID=document.getElementById("leadID").value;
	

	
	itemQty=document.getElementById("itemQty").value;
	itemAmount=document.getElementById("itemAmount").value;
	itemHz=document.getElementById("itemHz").value;
	itemVoltage=document.getElementById("itemVoltage").value;
	coolingSystem=document.getElementById("coolingSystem").value;
	
	engineType="Engine";
	engineMakeType=document.getElementById("engineMakeType").value;
	
	if(engineMakeType==0)
	{
		alert("Please Choose Engine ");
		document.getElementById("engineMakeType").focus();
		return false;
	}
	if(itemHz==0)
	{
		alert("Please Choose Hz");
		document.getElementById("itemHz").focus();
		return false;
	}
	if(coolingSystem==0)
	{
		alert("Please Choose Cooling System");
		document.getElementById("coolingSystem").focus();
		return false;
	}
	if(itemQty==0 || itemQty=="" || itemQty==null || itemQty=="undefined")
	{
		alert("Please Enter Qty");
		document.getElementById("itemQty").focus();
		return false;
	}
	if(itemAmount==0 || itemAmount=="" || itemAmount==null || itemAmount=="undefined")
	{
		alert("Please Enter Amount");
		document.getElementById("itemAmount").focus();
		return false;
	}
	
	
	alternator=document.getElementById("alternator").value;
	createdBy=document.getElementById("createdBy").value;
	
	var parameters = "leadID="+leadID+"&engineType="+engineType+"&engineMakeType="+engineMakeType+"&itemQty="+itemQty+"&itemAmount="+itemAmount+"&itemHz="+itemHz+"&itemVoltage="+itemVoltage+"&createdBy="+createdBy+"&coolingSystem="+coolingSystem+"&alternator="+alternator;
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	      //  alert(http.responseText);
			jsdata=JSON.parse(http.responseText);
			document.getElementById("itemDetailsNextButton").style.display="inline";
			 
			tableBody = document.getElementById('tableBody');
			var output='';
																		
			 for(var i=0; i < jsdata.length; i++)
			 {
				output += '<tr>';
				output += '<td>';
				output += jsdata[i].DTL_RATING;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_ENGINE_MODEL;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_ALTERNATOR_MODEL;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_VOLTAGE;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_QTY;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_AMOUNT;
				output += '</td>';
				output += '<td>';
				output += "<a href=javascript:deleteLeadItem('"+jsdata[i].DTL_ID+"','"+jsdata[i].LEAD_ID+"')>Delete</a>";
				output += '</td>';
				output += '</tr>';
				
			 }
			 
			 tableBody.innerHTML=output;
			 document.getElementById("engineType").value='0';
			document.getElementById("engineMakeType").value='0';
			document.getElementById("alternator").value='';
			document.getElementById("itemQty").value='';
			document.getElementById("itemAmount").value='';
			document.getElementById("itemHz").value='0';
			document.getElementById("itemVoltage").value='';
			document.getElementById("coolingSystem").value='0';
			//dataTables-example
			
	        /*if(http.responseText=="Done")
	        	{
	       // 		alert("asd");
	        		document.getElementById("headerButton").style.display='none';
	        		
	        		document.getElementById("homeTab").className = document.getElementById("homeTab").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        		document.getElementById("home").className = document.getElementById("home").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        		
	        		
	        		document.getElementById("customerDetails").className = document.getElementById("customerDetails").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        			      
	        		document.getElementById("homeTab").className="";
	        		document.getElementById("home").className = "tab-pane fade";
	        		document.getElementById("customerDetailsTab").className = "active";
	        		document.getElementById("customerDetails").className = "tab-pane fade active in";

	        	}
	        else
	        	{
	        		alert("Database is Responding slowly,please wait");
	        	}
				*/
	    }
	}
	http.send(null);
	}
	else{
	alert("Please correct error value");
	return false;
	}
}

function storeScheduleDetails(scheduleAction)
{
	if(checkInputError())
	{
	var http = new getXMLHttpRequestObject();
	var url = "ScheduleController";
	
	modalleadID=document.getElementById("modalleadID").value;
	scheduleRemarks=document.getElementById("scheduleRemarks").value;
	scheduleQty=document.getElementById("scheduleQty").value;
	scheduleDate=document.getElementById("scheduleDate").value;
	itemid=document.getElementById("itemid").value;
	createdBy=document.getElementById("createdBy").value;
	if(scheduleAction=="Add")
	{
		var parameters = "modalleadID="+modalleadID+"&scheduleRemarks="+scheduleRemarks+"&scheduleQty="+scheduleQty+"&scheduleDate="+scheduleDate+"&itemid="+itemid+"&createdBy="+createdBy+"&scheduleAction="+scheduleAction;
	}
	else{
		schdid=document.getElementById("schdid").value;
		var parameters = "modalleadID="+modalleadID+"&scheduleRemarks="+scheduleRemarks+"&scheduleQty="+scheduleQty+"&scheduleDate="+scheduleDate+"&itemid="+itemid+"&createdBy="+createdBy+"&scheduleAction="+scheduleAction+"&schdid="+schdid;
	}
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	      //  alert(http.responseText);
			if(http.responseText=="Done")
			{
			
			getSchedules(modalleadID,itemid,'all');
			document.getElementById("scheduleRemarks").value='';
			document.getElementById("scheduleQty").value='';
			document.getElementById("scheduleDate").value='';
			
				document.getElementById("scheduleAddSubmit").style.display='block';
				document.getElementById("scheduleUpdatedSubmit").style.display='none';
				document.getElementById("AddSchduleText").innerHTML='Add Schedule ';
				document.getElementById("myModalLabel").innerHTML='Add Schedule ';

			
			getSchedulesQty(modalleadID,itemid);
			
			
			}
			
			//dataTables-example
			

	    }
	}
	http.send(null);
	}
	else{
	
		alert("please correct error value");
		return false;
	}
	

}

function storePriceInfo(type,action)
{
	if(checkInputError())
	{
	
	var http = new getXMLHttpRequestObject();
	var url = "IndentDetailsController";
	indentAction="PRICEINFO";
	leadID=document.getElementById("leadID").value;
	scheduleAction=action;
	createdBy=document.getElementById("createdBy").value;

	if(type=="DG")
	{
	commercialDGPrice=document.getElementById("commercialDGPrice").value;
	commercialexcise=document.getElementById("commercialexcise").value;
	commercialCESS=document.getElementById("commercialCESS").value;
	commercialSHEC=document.getElementById("commercialSHEC").value;
	commercialTOTAL=document.getElementById("commercialTOTAL").value;
	commercialVAT=document.getElementById("commercialVAT").value;
	commercialEntrytax=document.getElementById("commercialEntrytax").value;
	commercialOCTROI=document.getElementById("commercialOCTROI").value;
	commercialAnyOther=document.getElementById("commercialAnyOther").value;
	commercialFI=document.getElementById("commercialFI").value;
	commercialsubTOTAL=document.getElementById("commercialsubTOTAL").value;
	commercialNoofDG=document.getElementById("commercialNoofDG").value;
	commercialFinalTOTAL=document.getElementById("commercialFinalTOTAL").value;
	
	name="commercialDGPrice="+commercialDGPrice+",commercialexcise="+commercialexcise+",commercialCESS="+commercialCESS+",commercialSHEC="+commercialSHEC+",commercialTOTAL="+commercialTOTAL+",commercialVAT="+commercialVAT+",commercialEntrytax="+commercialEntrytax+",commercialOCTROI="+commercialOCTROI+",commercialAnyOther="+commercialAnyOther+",commercialFI="+commercialFI+",commercialsubTOTAL="+commercialsubTOTAL+",commercialNoofDG="+commercialNoofDG+",commercialFinalTOTAL="+commercialFinalTOTAL;

	}
	if(type=="INST")
	{
		commercialpipes=document.getElementById("commercialpipes").value;
		commercialpipesVAT=document.getElementById("commercialpipesVAT").value;
		commercialCables=document.getElementById("commercialCables").value;
		commercialCablesVAT=document.getElementById("commercialCablesVAT").value;
		commercialInstTotalDGAccess=document.getElementById("commercialInstTotalDGAccess").value;
		lowSidemateriaSupply=document.getElementById("lowSidemateriaSupply").value;
		lowSidemateriaSupplyVAT=document.getElementById("lowSidemateriaSupplyVAT").value;
		labourinstallation=document.getElementById("labourinstallation").value;
		installationlabourvat=document.getElementById("installationlabourvat").value;
		sublowsideworks=document.getElementById("sublowsideworks").value;
		totalprojectvalue=document.getElementById("totalprojectvalue").value;
		
		name="commercialpipes="+commercialpipes+",commercialpipesVAT="+commercialpipesVAT+",commercialCables="+commercialCables+",commercialCablesVAT="+commercialCablesVAT+",commercialInstTotalDGAccess="+commercialInstTotalDGAccess+",lowSidemateriaSupply="+lowSidemateriaSupply+",lowSidemateriaSupplyVAT="+lowSidemateriaSupplyVAT+",labourinstallation="+labourinstallation+",installationlabourvat="+installationlabourvat+",sublowsideworks="+sublowsideworks+",totalprojectvalue="+totalprojectvalue;
	}
		
		var parameters = "leadID="+leadID+"&name="+name+"&type="+type+"&indentAction="+indentAction+"&scheduleAction="+scheduleAction+"&createdBy="+createdBy;
	
	//alert(url+"?"+parameters);
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	      
			alert(http.responseText);
			
			
			//dataTables-example
			

	    }
	}
	http.send(null);
	}
	else{
	
	alert("Please correct error value");
	return false;
	}
	

	
}
function storePaymentTerms(type,action)
{
	if(checkInputError())
	{
	var http = new getXMLHttpRequestObject();
	var url = "IndentDetailsController";
	indentAction="PAYMENTTERMS";
	leadID=document.getElementById("leadID").value;
	scheduleAction=action;
	createdBy=document.getElementById("createdBy").value;

	if(type=="DG")
	{
	commercialDGAdvance=document.getElementById("commercialDGAdvance").value;
	commercialDGcheque=document.getElementById("commercialDGcheque").value;
	commercialDGchequedate=document.getElementById("commercialDGchequedate").value;
	commercialDGBank=document.getElementById("commercialDGBank").value;
	commercialDGBalance=document.getElementById("commercialDGBalance").value;
	commercialDGForms=document.getElementById("commercialDGForms").value;
	commercialDGPaymentTerms1=document.getElementById("commercialDGPaymentTerms1").value;
	commercialDGPaymentTerms2=document.getElementById("commercialDGPaymentTerms2").value;
	commercialDGPaymentTerms3=document.getElementById("commercialDGPaymentTerms3").value;
	commercialDGPG=document.getElementById("commercialDGPG").value;
	commercialDGLiqDamage=document.getElementById("commercialDGLiqDamage").value;

	
	
	var parameters = "leadID="+leadID+"&type="+type+"&indentAction="+indentAction+"&commercialDGAdvance="+commercialDGAdvance+"&commercialDGcheque="+commercialDGcheque+"&commercialDGchequedate="+commercialDGchequedate+"&commercialDGBank="+commercialDGBank+"&commercialDGBalance="+commercialDGBalance+"&commercialDGForms="+commercialDGForms+"&commercialDGPaymentTerms1="+commercialDGPaymentTerms1+"&commercialDGPaymentTerms2="+commercialDGPaymentTerms2+"&commercialDGPaymentTerms3="+commercialDGPaymentTerms3+"&commercialDGPG="+commercialDGPG+"&commercialDGLiqDamage="+commercialDGLiqDamage+"&scheduleAction="+scheduleAction+"&createdBy="+createdBy;

	}
	
	if(type=="INST")
	{
	
	commercialINSTAdvance=document.getElementById("commercialINSTAdvance").value;
	commercialINSTcheque=document.getElementById("commercialINSTcheque").value;
	commercialINSTchequedate=document.getElementById("commercialINSTchequedate").value;
	commercialINSTBank=document.getElementById("commercialINSTBank").value;
	commercialINSTBalance=document.getElementById("commercialINSTBalance").value;
	commercialINSTForms=document.getElementById("commercialINSTForms").value;
	commercialINSTPaymentTerms1=document.getElementById("commercialINSTPaymentTerms1").value;
	commercialINSTPaymentTerms2=document.getElementById("commercialINSTPaymentTerms2").value;
	commercialINSTPaymentTerms3=document.getElementById("commercialINSTPaymentTerms3").value;
	commercialINSTPG=document.getElementById("commercialINSTPG").value;
	commercialINSTLiqDamage=document.getElementById("commercialINSTLiqDamage").value;
	
	
	var parameters = "leadID="+leadID+"&type="+type+"&indentAction="+indentAction+"&commercialINSTAdvance="+commercialINSTAdvance+"&commercialINSTcheque="+commercialINSTcheque+"&commercialINSTchequedate="+commercialINSTchequedate+"&commercialINSTBank="+commercialINSTBank+"&commercialINSTBalance="+commercialINSTBalance+"&commercialINSTForms="+commercialINSTForms+"&commercialINSTPaymentTerms1="+commercialINSTPaymentTerms1+"&commercialINSTPaymentTerms2="+commercialINSTPaymentTerms2+"&commercialINSTPaymentTerms3="+commercialINSTPaymentTerms3+"&commercialINSTPG="+commercialINSTPG+"&commercialINSTLiqDamage="+commercialINSTLiqDamage+"&scheduleAction="+scheduleAction+"&createdBy="+createdBy;

	}
	
	
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	      
			alert(http.responseText);
			
			
			//dataTables-example
			

	    }
	}
	http.send(null);
	
}
else{
	alert("Please correct error value");
	return false;
}
	
}
function getAllsupplyscope(action)
{
	a=document.getElementById("warranty_info").value;
	if(a=="")
	{
		alert("Please add warranty details");
		document.getElementById("warranty_info").focus();
		return false;
	}
	indentAction="SUPPLYSCOPE";
	scheduleAction=action;
	factoryName=document.getElementById("factoryName").innerHTML;
	branchName=document.getElementById("branchName").innerHTML;
	//alert("asd"+a);
	//alert("asd"+document.factorysupplyscopeForm.elements.length);
	factoryScope="";
	branchScope="";
	
	
	
	for(i=0; i<document.factorysupplyscopeForm.elements.length; i++){
			if(document.factorysupplyscopeForm.elements[i].type=="checkbox"){
																		
			if(document.factorysupplyscopeForm.elements[i].checked==true)
				{
					factoryScope+=document.factorysupplyscopeForm.elements[i].value+"="+"1,";
				}
			else{
					factoryScope+=document.factorysupplyscopeForm.elements[i].value+"="+"0,";
				}
			}
	}
	for(i=0; i<document.branchsupplyscopeForm.elements.length; i++){
			if(document.branchsupplyscopeForm.elements[i].type=="checkbox"){
																		
			if(document.branchsupplyscopeForm.elements[i].checked==true)
				{
					branchScope+=document.branchsupplyscopeForm.elements[i].value+"="+"1,";
				}
			else{
					branchScope+=document.branchsupplyscopeForm.elements[i].value+"="+"0,";
				}
			}
	}
	
	
	
	var http = new getXMLHttpRequestObject();
	var url = "IndentDetailsController";
	indentAction="SUPPLYSCOPE";
	leadID=document.getElementById("leadID").value;
	createdBy=document.getElementById("createdBy").value;
	
	warranty_details=document.getElementById("warranty_info").value;
	if(document.getElementById("operator_required").checked)
	{
		operator_required="YES";
	}
	else{
		operator_required="NO";
	}
	//encodeURI(uri);
	
	var parameters = "leadID="+leadID+"&factoryScope="+encodeURIComponent(factoryScope)+"&branchScope="+encodeURIComponent(branchScope)+"&factoryName="+factoryName+"&branchName="+branchName+"&createdBy="+createdBy+"&scheduleAction="+scheduleAction+"&warranty_details="+warranty_details+"&operator_required="+operator_required+"&indentAction="+indentAction;

	//alert(url+"?"+parameters);
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	      
			alert(http.responseText);

	    }
	}
	http.send(null);
	
}

function storeTechnicalDetails(scheduleAction)
{
	if(checkInputError())
	{
	var http = new getXMLHttpRequestObject();
	var url = "IndentDetailsController";
	indentAction="TECHDETAIL";
	
	modalleadID=document.getElementById("modaltechleadID").value;
	//scheduleRemarks=document.getElementById("scheduleRemarks").value;
	//scheduleQty=document.getElementById("scheduleQty").value;
	//scheduleDate=document.getElementById("scheduleDate").value;
	itemid=document.getElementById("itemtechid").value;
	createdBy=document.getElementById("createdBy").value;
	
	fuleTank=document.getElementById("fuleTank").value;
	engineOptionals=document.getElementById("engineOptionals").value;
	alternatorOptionals=document.getElementById("alternatorOptionals").value;
	panelOptionals=document.getElementById("panelOptionals").value;
	soloparallel=document.getElementById("soloparallel").value;
	panelType=document.getElementById("panelType").value;
	testingProcedure=document.getElementById("testingProcedure").value;
	dgTesting=document.getElementById("dgTesting").value;
	
	if(document.getElementById("testingCharges").checked)
	{
		testingCharges=1;
	}
	else{
		testingCharges=0;
	}
	
		var parameters = "modalleadID="+modalleadID+"&fuleTank="+encodeURIComponent(fuleTank)+"&engineOptionals="+encodeURIComponent(engineOptionals)+"&alternatorOptionals="+encodeURIComponent(alternatorOptionals)+"&panelOptionals="+encodeURIComponent(panelOptionals)+"&createdBy="+createdBy+"&soloparallel="+encodeURIComponent(soloparallel)+"&panelType="+encodeURIComponent(panelType)+"&testingProcedure="+encodeURIComponent(testingProcedure)+"&dgTesting="+encodeURIComponent(dgTesting)+"&testingCharges="+encodeURIComponent(testingCharges)+"&scheduleAction="+scheduleAction+"&itemid="+itemid+"&indentAction="+indentAction;

	//alert(url+"?"+parameters);
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	      
			alert(http.responseText);
			
			document.getElementById("fuleTank").value='990 ltrs';
			document.getElementById("engineOptionals").value='';
			document.getElementById("alternatorOptionals").value='QDCT';
			document.getElementById("panelOptionals").value='';
			document.getElementById("soloparallel").value='Parallel';
			document.getElementById("panelType").value='Standard Isoltaor panel - MDO 3200 Amps with KWH';
			document.getElementById("testingProcedure").value='Standard Factory test procedure';
			document.getElementById("dgTesting").value='Standard Testing @ works wittnessed by PMC / Consultants';

	    }
	}
	http.send(null);
	}
	else{
		alert("Please correct error value");
		return false;
	}
	

}

function storeComments()
{
	
	var http = new getXMLHttpRequestObject();
	var url = "CommentsController";
	
	leadID=document.getElementById("leadID").value;
	
	
	comments=document.getElementById("comments").value;
	
	if(comments=="" || comments=="undefined" || comments==undefined)
	{
		alert("Please Enter Comments ");
		document.getElementById("comments").focus();
		return false;
	}
	
	
	
	createdBy=document.getElementById("createdBy").value;
	
	var parameters = "leadID="+leadID+"&comments="+comments+"&createdBy="+createdBy+"&types=INDENT_COMMENTS";
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	      //  alert(http.responseText);
			jsdata=JSON.parse(http.responseText);
			
			 
			tableBody = document.getElementById('tableCommentsBody');
			var output='';
			j=0;															
			 for(var i=0; i < jsdata.length; i++)
			 {
				j++;
				output += '<tr>';
				output += '<td>';
				output += j;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DETAILS;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].CREATED_BY;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].CRETAED_DATE;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].TYPES;
				output += '</td>';
				output += '</tr>';
				
			 }
			 
			 tableCommentsBody.innerHTML=output;
			 document.getElementById("comments").value='';
			//dataTables-example
			

	    }
	}
	http.send(null);
}

function GenerateIndentReport()
{
	if(checkInputError())
	{
	//getAllsupplyscope('Add');
	var http = new getXMLHttpRequestObject();
	var url = "GenerateIndentReport";
	
	leadID=document.getElementById("leadID").value;
	projectType=document.getElementById("projectType").value;
	projectExeState=document.getElementById("projectExeState").value;
	
	
	var parameters = "leadID="+leadID+"&projectType="+projectType+"&val=new"+"&state="+projectExeState;
	//alert(parameters);
	document.getElementById("loadedFile").style.display='none';
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
			
			 var link=document.getElementById("downlaodLink");
			 link.setAttribute("href", http.responseText);
			 
			 link.innerHTML='Click Here to Download Indent Sheet';
			 document.getElementById("loadedFile").style.display='block';
			document.getElementById("loadingFile").style.display='none';
			sendMailAttachment(http.responseText);

	    }
		else{
		document.getElementById("loadedFile").style.display='none';
		document.getElementById("loadingFile").style.display='block';
			
			
		}
	}
	http.send(null);
}
else{
	alert("Please correct Error Value");
	return false;
}
	
}

function sendMailAttachment(attachmentFile)
{
	var http = new getXMLHttpRequestObject();
	var url = "MailController";
	

	
	
	var parameters = "attachmentFile="+attachmentFile;
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
		

	    }
		else{
			
			
		}
	}
	http.send(null);
}

function getAttachments(leadID)
{
	
	var http = new getXMLHttpRequestObject();
	var url = "GetAttachmentByLeadID";
	
	leadID=leadID;
	
	var parameters = "leadID="+leadID;
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	        //alert(http.responseText);
			jsdata=JSON.parse(http.responseText);
			
			 
			tableBody = document.getElementById('tableAttachmentsBody');
			var output='';
			j=0;															
			 for(var i=0; i < jsdata.length; i++)
			 {
				j++;
				output += '<tr>';
				output += '<td>';
				output += j;
				output += '</td>';
				output += '<td>';
				output += '<a href="'+jsdata[i].DOC_URL+'">'+jsdata[i].DOC_NAME+'</a>';
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DOC_CREATED_BY;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DOC_CREATED_DATE;
				output += '</td>';
				
				output += '</tr>';
				
			 }
			 
			 tableAttachmentsBody.innerHTML=output;
			 
			//dataTables-example
			

	    }
	}
	http.send(null);
}

function getSchedulesQty(leadID,id)
{
	getSchedules(leadID,id,"qty");
	
	
}
function getSchedules(leadID,id,val)
{
	
	var http = new getXMLHttpRequestObject();
	var url = "GetScheduleDetails";
	
	leadID=leadID;
	
	item_id=id;
	
	
	
	var parameters = "leadID="+leadID+"&dtl_id="+item_id+"&val="+val;
	
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	       // alert(http.responseText);
			
			if(val=="all")
			{
			jsdata=JSON.parse(http.responseText);
			
			 
			tableBody = document.getElementById('tableSchedulesBody');
			updateScheduleRole=document.getElementById('updateScheduleRole').value;
			var output='';
			j=0;															
			 for(var i=0; i < jsdata.length; i++)
			 {
				j++;
				output += '<tr>';
				output += '<td>';
				output += j;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].SCHD_DELIVERY_DATE;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].SCHD_QTY;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].SCH_CREATED_BY;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].SCHD_REMARKS;
				output += '</td>';
				output += '<td>';
				output += "<a href=javascript:setScheduleItem('"+jsdata[i].SCHD_ID+"','"+jsdata[i].SCHD_DELIVERY_DATE+"','"+jsdata[i].SCHD_QTY+"','"+jsdata[i].SCHD_REMARKS+"')><p class='fa fa-pencil fa-2x'>  </p></a>";
				output += '</td>';
				
				
				output += '</tr>';
				
			 }
			 
			 tableSchedulesBody.innerHTML=output;
			}
			else{
				
				document.getElementById("usedQty").value=http.responseText;
				
				
				
			}


	    }
	}
	http.send(null);
}

function setScheduleItem(schdid,del_date,qty,remarks)
{
	document.getElementById("scheduleAddSubmit").style.display='none';
	document.getElementById("scheduleUpdatedSubmit").style.display='block';
	document.getElementById("AddSchduleText").innerHTML='Updated Schedule ';
	document.getElementById("myModalLabel").innerHTML='Updated Schedule ';
	
	
	
	document.getElementById("scheduleDate").value=del_date;
	document.getElementById("scheduleQty").value=qty;
	document.getElementById("scheduleRemarks").value=remarks;
	document.getElementById("schdid").value=schdid;
	
	
	
	
	
	
}

function callCustomer(str)
{

	var http = new getXMLHttpRequestObject();
	 
	var url = "GetCustomerDetails";
	var parameters = "CUSTOMER_NAME="+str;
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	      //  alert(http.responseText);
			a=JSON.parse(http.responseText);
			
			var customerContact,emailAddress,alternateContact;
			
			if(a[0].CONTACT_NUMBER=="undefined" || a[0].CONTACT_NUMBER==undefined || a[0].CONTACT_NUMBER=="" || a[0].CONTACT_NUMBER==null)
			{
				customerContact="";
			}
			else{
				customerContact=a[0].CONTACT_NUMBER
			}
			if(a[0].EMAIL_ADDRESS=="undefined" || a[0].EMAIL_ADDRESS==undefined || a[0].EMAIL_ADDRESS=="" || a[0].EMAIL_ADDRESS==null)
			{
				emailAddress="";
			}
			else{
				emailAddress=a[0].EMAIL_ADDRESS
			}
			if(a[0].ALTERNATE_NO=="undefined" || a[0].ALTERNATE_NO==undefined || a[0].ALTERNATE_NO=="" || a[0].ALTERNATE_NO==null)
			{
				alternateContact="";
			}
			else{
				alternateContact=a[0].ALTERNATE_NO
			}
			
			
			
			document.getElementById("customerContactNumber").value=customerContact;
			document.getElementById("customerEmail").value=emailAddress;
			document.getElementById("customerAlternateNumber").value=alternateContact;
			
				
			
			
	    }
	}
	http.send(null);
}

function GetItemModelDetails(str)
{

	//alert("asda"+str);
	select = document.getElementById('engineMakeType');
	
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
			select = document.getElementById('engineMakeType');
			
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
function GetCity(str,type)
{

	
	if(str==0)
		{
			alert("Please choose state");
			
			if(type=="MainCity")
			{
				document.getElementById('projectExeState').focus();
			}
			if(type=="BillingAddress")
			{
				document.getElementById('customerBillingAddress3').focus();
			}
			if(type=="DeliveryAddress")
			{
				document.getElementById('customerDeliveryAddress3').focus();
			}
			
			return false;
		}
		
		if(type=="MainCity")
			{
					select = document.getElementById('projectExePlace');
					removeAllOptions(select,true);

			}
			if(type=="BillingAddress")
			{
				
					select = document.getElementById('customerBillingAddress3');
					removeAllOptions(select,true);

			}
			if(type=="DeliveryAddress")
			{
				
				select = document.getElementById('customerDeliveryAddress3');
				removeAllOptions(select,true);
			}
			
	var http = new getXMLHttpRequestObject();
	 
	var url = "GetCity";
	var parameters = "state="+str+"&val="+"update";;
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
			a=JSON.parse(http.responseText);
			var output = [];
			//alert(a.length);
			if(type=="MainCity")
			{
				select = document.getElementById('projectExePlace');
			}
			if(type=="BillingAddress")
			{
				select = document.getElementById('customerBillingAddress3');
			}
			if(type=="DeliveryAddress")
			{
				select = document.getElementById('customerDeliveryAddress3');
			}
			
			 for(var i=0; i < a.length; i++)
			 {
				//output[i++] = '<option value="'+ a[i] +'">'+ a[i] +'</option>';
				var opt = document.createElement('option');
				opt.value = a[i];
				opt.innerHTML = a[i];
				select.appendChild(opt);
			 }

			
	    }
	}
	http.send(null);
}



function hideSubmit()
{
	
	if(document.getElementById("myfile").value=="" || document.getElementById("myfile").value=="undefined" || document.getElementById("myfile").value==undefined)
	{
		alert("Please Attach Necessary File");
		document.getElementById("myfile").focus();
		return false;
	}	
	//document.getElementById("commentsSubmitButton").style.display='none';
	//document.getElementById("commentsNextButton").style.display='block';
	
}

function MoveToCostSummary()
{
					document.getElementById("settingsTab").className = document.getElementById("settingsTab").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        		document.getElementById("settings").className = document.getElementById("settings").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        		
	        		
	        		document.getElementById("costsummarysheet").className = document.getElementById("costsummarysheet").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        			      
	        		document.getElementById("settingsTab").className="";
	        		document.getElementById("settings").className = "tab-pane fade";
	        		document.getElementById("costsummarysheetTab").className = "active";
	        		document.getElementById("costsummarysheet").className = "tab-pane fade active in";
}

function MoveToFinalStep()

{


					document.getElementById("validationchecksTab").style.display = "block";
	        		document.getElementById("validationchecks").style.display = "block";
					
					document.getElementById("costsummarysheetTab").className = document.getElementById("costsummarysheetTab").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        		document.getElementById("costsummarysheet").className = document.getElementById("costsummarysheet").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        		
	        		
	        		document.getElementById("validationchecks").className = document.getElementById("validationchecks").className.replace( /(?:^|\s)MyClass(?!\S)/g , '' );
	        			      
	        		document.getElementById("costsummarysheetTab").className="";
	        		document.getElementById("costsummarysheet").className = "tab-pane fade";
	        		document.getElementById("validationchecksTab").className = "active";
	        		document.getElementById("validationchecks").className = "tab-pane fade active in";
}



function hideShowDeleiverySection()
{
	
	a=document.getElementById("customerAddressCheckbox").checked;
	
	if(a)
	{
		document.getElementById("deliveryAddressSection").style.display="none";
	}
	else{
		document.getElementById("deliveryAddressSection").style.display="block";
	}
}

function checkScheduleQty(val)
{
	
	enteredVal=parseInt(val);
	total=parseInt(document.getElementById("modalEngineQty").value);
	used=parseInt(document.getElementById("usedQty").value);
	
	
	if((used+enteredVal)>total)
	{
		alert("Ooops!!! Entered Qty is more than total amount ,max allowed Qty "+(total-used));
		document.getElementById("scheduleQty").value='';
		document.getElementById("scheduleQty").focus();
		return false;
		
		
	}
	
	
}

function validateMessage(leadID,tabletype)
{
	leadID=document.getElementById("leadID").value;
	if(leadID=="" || leadID=="undefined" || leadID==undefined)
	{
		alert("Please choose state and fill all Details in Main Tab first");
		return false;
	}
	validateIndentCost(leadID);
	//Calling Indent Creation in sb-admin page
	
			
	

	
	
}
function callValidationMessage(leadID,tabletype)
{
	var http = new getXMLHttpRequestObject();
	var url = "ValidatorController";
	
	
	tabletype="DGINDENT";
	var parameters = "leadID="+leadID+"&tabletype="+tabletype;
	
	http.open("GET", url+"?"+parameters, true);
	//return http.responseText;
	//alert(url+"?"+parameters);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	       // alert(http.responseText);
			a=JSON.parse(http.responseText);
			
	        for(i=0;i<a.length;i++)
			{
				tabletype=a[i].split("--")[0];
				value=a[i].split("--")[1];
				
					if(tabletype=="SCHEDULE")
					{
						if(value=="yes")
						{
							document.getElementById("leadScheduleVMessage").innerHTML='Lead Schedule Data Added Successfully';
						}
						else
						{
							alert("Please Add Lead Item Schedule");
							document.getElementById("leadScheduleVMessage").innerHTML='<span style="color:red">Main Tab data is missing , please add to proceed further</span>';
							
							document.getElementById("viewValidateMessage").style.display="block";
							return false;
						}
						
					}
					if(tabletype=="TECH_INFO")
					{
						if(value=="yes")
						{
							document.getElementById("leadTechnicalVMessage").innerHTML='Technical Information has been added succesfully';
						}
						else{
							alert("Please Add Lead Items Technical Information");
							document.getElementById("leadTechnicalVMessage").innerHTML='Customer data is missing , please add to proceed further';
							
							document.getElementById("viewValidateMessage").style.display="block";
							return false;

						}
					}
					if(tabletype=="COMMERCIALDG")
					{
						if(value=="yes")
						{
							document.getElementById("leadCommercialDGVMessage").innerHTML='Commercial DG Information Added Successfully';
						}
						else{
							alert("Commercial DG Information has not been added");
							document.getElementById("leadCommercialDGVMessage").innerHTML='Customer data is missing , please add to proceed further';
							
							document.getElementById("viewValidateMessage").style.display="block";
							return false;

						}
					}
					if(tabletype=="COMMERCIALINST")
					{
						if(value=="yes")
						{
							document.getElementById("leadCommercialINSTVMessage").innerHTML='Commercial Installation Information Added Successfully';
							
							
						}
						else{
							alert("Commercial Installation Information is not added  , please add to proceed further");
							//document.getElementById("leadCostSummaryVMessage").innerHTML="Cost Summary Information is not added  , please add to proceed further";
							
							document.getElementById("viewValidateMessage").style.display="block";
							return false;
						}
						
					}
					if(tabletype=="PAYMENTDG")
					{
						if(value=="yes")
						{
						document.getElementById("leadPaymentDGVMessage").innerHTML='DG payment Information Updated Successfully';
						
						}
						else{
							
							alert("DG payment Information is not added  , please add to proceed further");
							document.getElementById("leadPaymentDGVMessage").innerHTML="PMC Data Not Added";
							return false;
						}
						
					}
					if(tabletype=="PAYMENTINST")
					{
						if(value=="yes")
						{
						document.getElementById("leadPaymentINSTVMessage").innerHTML='Installation payment Information Updated Successfully';
						
						}
						else{
							
							alert("Installation payment Information is not added  , please add to proceed further");
							document.getElementById("leadPaymentINSTVMessage").innerHTML="PMC Data Not Added";
							return false;
						}
						
					}
					
					if(tabletype=="SUPPLY_SCOPE")
					{
						if(value=="yes")
						{
						document.getElementById("leadSupplyScopeVMessage").innerHTML='Supply Scope Information Updated Successfully';
						document.getElementById("viewMessage").style.display='block';
						document.getElementById("viewValidateMessage").style.display='none';
						GenerateIndentReport();
						
						
						}
						else{
							
							alert("Supply Scope Information is not added  , please add to proceed further");
							document.getElementById("leadSupplyScopeVMessage").innerHTML="Supply Scope Data Not Added";
							return false;
						}
						
					}
				
			}
			
			}
		 else
	        	{
	        		
	        	}
	}
	http.send(null);
}
</script>
        <div id="page-wrapper">
           
            <div class="row" >
			<div class="col-lg-12" style="margin-top:5px">
	           <ol class="breadcrumb">
				 <li><a href="index.jsp"><i class="fa fa-home fa-fw"></i> Home</a></li>
				 
				 <li class="dropdown active">
        			  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
        			  aria-haspopup="true" aria-expanded="false">Indent <span class="caret"></span></a>
			          <ul class="dropdown-menu">
              			<li class=active><a href="MainController?action=listQualifiedLead&search=All">Cretae Indent</a></li>
              			<li role="separator" class="divider"></li>
              			<li><a href="MainController?action=listIndent&search=All">View Indents</a></li>
              		
			          </ul>
        		</li>
				  
				  
				</ol>
			</div>
		</div>
            <!-- /.row -->
			<div class="row">
			<div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
						
													<c:choose>
															<c:when test="${doAction=='createIndent'}">
																
																Create Indent
																
															</c:when> 
															<c:when test="${doAction=='editIndent'}">
																
																Edit Indent Details - ${leadID}
																
															</c:when>   
															<c:otherwise>
																
																View Indent Details - ${leadID}
																
																
															</c:otherwise>
														</c:choose>
                            
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs">
                           
                                <li class="active" id="homeTab"><a href="#home" data-toggle="tab" aria-expanded="false">Main</a>
                                </li>
                                <li class="" id="customerDetailsTab"><a href="#customerDetails" data-toggle="tab" aria-expanded="false">Customer</a>
                                </li>
								<li class="" id="itemDetailsTab"><a href="#itemDetails" data-toggle="tab" aria-expanded="false">Item/Schedule Details</a>
                                </li>
                                <li class="" id="settingsTab"><a href="#settings" data-toggle="tab" aria-expanded="false">Comments </a>
                                </li>
								<li class="" id="costsummarysheetTab"><a href="#costsummarysheet" data-toggle="tab" aria-expanded="false">Cost Summary Sheet</a>
                                </li>
								<li class="" id="validationchecksTab" style="display:none"><a href="#validationchecks" data-toggle="tab" aria-expanded="false">Validation and Check</a>
                                </li>
                                <li class="" id="CommercialInformationTab"><a href="#CommercialInformation" data-toggle="tab" aria-expanded="false">Commercial Information</a>
                                </li>
                                <li class="" id="SupplyScopeTab"><a href="#SupplyScope" data-toggle="tab" aria-expanded="false">Scope of Supply</a>
                                </li>
                                
								<li class="" id="LeadcreateIndentTab"><a href="#LeadcreateIndent" data-toggle="tab" aria-expanded="false">Create Indent</a>
                                </li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div class="tab-pane fade active in" id="home">
                                 <form data-toggle="validator" role="form" novalidate="true">   
								<br />
                               
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label>Project Type</label>  <span class="text-alert-danger">*</span>
                                            	<select  class="form-control" name="projectType" id="projectType" value="${projectType}">
                                            		<option value="0">Select Lead Type</option>
													<c:forEach items="${projectTypes}" var="projectType"> 
														<option value="${projectType}"  ${projectType == selectedprojectType ? 'selected="selected"' : ''}>${projectType}</option>
											    	</c:forEach>
						                    	</select>
						                    	<errors path="leadType" cssClass="error"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
											<label path="projectExeState">Execution State</label>  <span class="text-alert-danger">*</span>
											 <select path="projectExeState" id="projectExeState" value="${projectExeState}" name="projectExeState" class="form-control" onchange="return GetCity(this.value,'MainCity');">
											 	<option value="0">Select State</option>
												<c:forEach items="${exe_state}" var="states"> 
														<option value="${states}" ${states == sel_state ? 'selected="selected"' : ''}>${states}</option>
											    	</c:forEach>
						                    </select>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="projectExePlace">Execution City</label>  <span class="text-alert-danger">*</span>
	                                            
												
												<select path="projectExePlace" id="projectExePlace" value="${projectExePlace}" name="projectExePlace" class="form-control">
	                                            	<option value="0">Select City</option>
													<c:forEach items="${exe_city}" var="city"> 
														<option value="${city}" ${city == sel_city ? 'selected="selected"' : ''}>${city}</option>
												    </c:forEach>
							                    </select>
											</div>
										</div>
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="dgType">DG Type</label>
												<select path="dgType" id="dgType" name="dgType"  class="form-control">
													<option value="0">Select DG Type</option>  <span class="text-alert-danger">*</span>
													<c:forEach items="${dgTypes}" var="dgType"> 
														<option value="${dgType}" ${dgType == sel_dgType ? 'selected="selected"' : ''}>${dgType}</option>
											    	</c:forEach>
						                    	</select>											
											</div>
										</div>
										
									</div>
									
									<div class="row">
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="segment">Segment</label>  <span class="text-alert-danger">*</span>
												<select path="segment" id="segment" value="${segment}" name="segment" class="form-control">
													<option value="0">Select Segment</option>
													<c:forEach items="${segmentType}" var="segment"> 
														<option value="${segment}" ${segment == sel_segment ? 'selected="selected"' : ''}>${segment}</option>
											    	</c:forEach>
						                    	</select>
											</div>
										</div>
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="transactionType">Transaction Type</label>  <span class="text-alert-danger">*</span>
												<select path="transactionType" id="transactionType" value="${transactionType}" name="transactionType" class="form-control">
													<option value="0">Select Transaction Type</option>
													<c:forEach items="${transactionType}" var="tType"> 
														<option value="${tType}" ${tType == sel_transaction_type ? 'selected="selected"' : ''}>${tType}</option>
											    	</c:forEach>
						                    	</select>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
											<label path="enquiryType">Enquiry Type</label>  <span class="text-alert-danger">*</span>
											<select path="enquiryType" id="enquiryType" value="${enquiryType}" name="enquiryType" class="form-control">
												<option value="0">Select Enquiry Type</option>
													<c:forEach items="${enquiryType}" var="etype"> 
														<option value="${etype}" ${etype == sel_enq_type ? 'selected="selected"' : ''}>${etype}</option>
											    	</c:forEach>
						                    </select>
											</div>
										</div>
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="competitions">Competition</label>
												<input path="competitions" maxlength="100" type="text" value="${competitions}" id="competitions" class="form-control" name="competitions" onkeyup="autocomplet()"/>
												
												<ul id="country_list_id"></ul>
											</div>
										</div>
										
									</div>

									<div class="row"> 
									<div class="col-lg-3">
											<div class="form-group">
												<label path="projectReference">Project Reference</label>
												<input path="projectReference" maxlength="100" value="${projectReference}" class="form-control" name="projectReference" id="projectReference" />
												<errors path="projectReference" cssClass="error"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="LeadExeDate">Lead Execution Date </label>
												<input path="LeadExeDate" type="date" id="LeadExeDate" value="${LeadExeDate}" class="form-control" name="LeadExeDate" readonly />
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="DGDeliveryDate">DG Delivery Date </label>
												<input path="DGDeliveryDate" type="date" id="DGDeliveryDate" value="${DGDeliveryDate}" class="form-control" name="DGDeliveryDate" />
												
											</div>
										</div>
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="ProjectClosureDate">Project Closure Date </label>
												<input path="ProjectClosureDate" type="date" id="ProjectClosureDate" value="${ProjectClosureDate}" class="form-control" name="ProjectClosureDate" />
												
											</div>
										</div>
										
										
									</div>
									<div class="row">
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="ownerID">Owner </label>
												<input path="ownerID" type="text" id="ownerID" value="${lead_owner_id}" class="form-control" name="ownerID" />
												<input path="ownerName" type="hidden" id="ownerName" value="${lead_owner_name}" class="form-control" name="ownerName" />
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="leadStatus">Status</label>  <span class="text-alert-danger">*</span>
												<select path="leadStatus" id="leadStatus" value="${leadStatus}" name="leadStatus" class="form-control">
													<option value="0">Select Status</option>
													<c:forEach items="${statusType}" var="stype"> 
														
														
														<c:choose>
															<c:when test="${stype=='Pending'}">
																
															</c:when> 
															<c:when test="${stype=='Draft'}">
																
															</c:when>  															
															<c:otherwise>
																<option value="${stype}" ${stype == sel_lead_status ? 'selected="selected"' : ''}>${stype}</option>
															</c:otherwise>
														</c:choose>
											    	</c:forEach>
						                    	</select>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="leadChances">Chances</label>  <span class="text-alert-danger">*</span>
												
												
												
												<select path="leadChances" name="leadChances" id="leadChances" value="${leadChances}"  class="form-control">
													
													<c:forEach items="${chancesType}" var="chanceType"> 
														
														
														<c:choose>
															<c:when test="${chanceType=='R'}">
																
															</c:when> 
															<c:when test="${chanceType=='Y'}">
																
															</c:when>															
															<c:otherwise>
																<option value="${chanceType}" ${chanceType == sel_lead_Chance ? 'selected="selected"' : ''}>${chanceType}</option>
															</c:otherwise>
														</c:choose>
											    	</c:forEach>
						                    	</select>
											</div>
										</div>
										<div class="col-lg-3">
										
											<div class="form-group">
												<label path="leadID">Lead ID</label>
												<input type="text" name="leadID" value="${leadID}" class="form-control" readonly="true" id="leadID"/>
												<input type="text" name="actionValue" value="update" class="form-control" style="display:none" id="actionValue"/>
												<errors path="leadID" cssClass="error"/>
											</div>
										</div>
										
										
										
									</div>
							
									<div class="row" style="display:none">
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="createdBy">Created By </label>
												<input path="createdBy" type="text" id="createdBy" value="${userID}" class="form-control" name="createdBy" />
											</div>
										</div>
										
										
									</div>										
									
								
									<div align="middle"><input type="button" value="Update" id="headerButton" class="btn btn-success" onclick="return storeHeaderDetails()"/></div>
							</form>	
                            </div>
                                
                            <div class="tab-pane fade" id="customerDetails">
                            <br />
                                <form data-toggle="validator" role="form" novalidate="true">   
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerType">Customer Type</label>  <span class="text-alert-danger">*</span>
												<select path="customerType" id="customerType" name="customerType"  class="form-control" onchange="getCustomer(this.value)">
												<option value="0">Select</option>
														<c:forEach items="${customerType}" var="customerType">
															<option value="${customerType}" ${customerType == sel_customerType ? 'selected="selected"' : ''}>${customerType}</option>
												    	</c:forEach>
							                    </select>
											</div>
										</div>
										<input type="hidden" path="leadID" value="${leadID}" class="form-control" readonly="true"/>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerName">Customer Name</label>  <span class="text-alert-danger">*</span>
												<input type="text" path="customerName" maxlength="100" id="mcustomerName"  class="form-control" name="customerName" style="display:none" />
												
												<select path="customerName" id="customerName" name="customerName" value="${customerNames}" class="form-control"  onChange="callCustomer(this.value)">
														<option value="0">Select</option>
														<c:forEach items="${customerNames}" var="customerName">
															<option value="${customerName}" ${customerName == sel_customerName ? 'selected="selected"' : ''}>${customerName}</option>
												    	</c:forEach>
							                    </select>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerContactNumber">Contact Number</label>
												<input type="text" path="customerContactNumber" value="${customerContactNumber}" class="form-control" name="customerContactNumber" id="customerContactNumber"  data-error="Contact Number is invalid" maxlength="12" pattern="^([0|\+[0-9]{1,5})?([1-9][0-9]{9})$" />

												<div class="help-block with-errors"></div>													
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerEmail">Email</label>
												<input type="email" path="customerEmail" value="${customerEmail}" class="form-control" name="customerEmail" id="customerEmail" data-error="Email address is invalid"/>
												<div class="help-block with-errors"></div>
											</div>
										</div>
									</div>
									<input type="hidden" path="customerAlternateNumber" value="${customerAlternateNumber}" class="form-control" name="customerAlternateNumber" id="customerAlternateNumber"/>
									
							<h4>Address & End Customer details</h4>
									<hr />
									<div class="row">
										<div class="col-lg-4" style="display:none">
												<div class="form-group">
													<label path="customergroup">Customer Group</label>
													<input type="text" maxlength="50" path="customergroup" value="${customergroup}" class="form-control" name="customergroup" id="customergroup" />
												</div>
											</div>
										<div class="col-lg-4">
											<div class="form-group">
												<label path="customerBillingAddress1">Address</label>
												<input type="text" maxlength="50" path="customerTempAddress" value="${customerTempAddress}" class="form-control" name="customerTempAddress" id="customerTempAddress" />
											</div>
										</div>
											<div class="col-lg-4">
												<div class="form-group">
													<label path="customergroup_person">End Customer Name</label>
													<input type="text" maxlength="50" path="customergroup_person" value="${customergroup_person}" class="form-control" name="customergroup_person" id="customergroup_person" />
												</div>
											</div>

											<div class="col-lg-4">
												<div class="form-group">
													<label path="customergroup_person_contact">End Customer ContactNo</label>
													<input type="text" path="customergroup_person_contact" value="${customergroup_person_contact}" class="form-control" name="customergroup_person_contact" id="customergroup_person_contact" pattern="^([0|\+[0-9]{1,5})?([1-9][0-9]{9})$" />
													<div class="help-block with-errors"></div>
												</div>
											</div>

									</div>									
								<h4>Customer Address & Other details</h4>
									<hr />
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerBillingAddress1">Billing Address</label>
												<input type="text" maxlength="50" path="customerBillingAddress1" value="${customerBillingAddress1}" class="form-control" name="customerBillingAddress1" id="customerBillingAddress1" />
											</div>
										</div>
										<div class="col-lg-3">
										
										
												
												
												
											<div class="form-group">
												<label path="customerBillingAddress2">State</label>  <span class="text-alert-danger">*</span>
												
												<select path="customerBillingAddress2" id="customerBillingAddress2" value="${customerBillingAddress2}" name="customerBillingAddress2" class="form-control" onchange="return GetCity(this.value,'BillingAddress');">
											 	<option value="0">Select</option>
												<c:forEach items="${exe_state}" var="states"> 
														<option value="${states}" ${states == sel_customerBillingAddress2 ? 'selected="selected"' : ''}>${states}</option>
											    	</c:forEach>
						                    </select>
											
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerBillingAddress3">City</label>  <span class="text-alert-danger">*</span>
												
												<select path="customerBillingAddress3" id="customerBillingAddress3" value="${customerBillingAddress3}" name="customerBillingAddress3" class="form-control">
	                                            	<option value="0">Select </option>
													<c:forEach items="${exe_city}" var="city"> 
														<option value="${city}" ${city == sel_customerBillingAddress3 ? 'selected="selected"' : ''}>${city}</option>
												    </c:forEach>
							                    </select>
												
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerBillingAddress4">Pin Code</label>
												<input type="text" maxlength="6" path="customerBillingAddress4" value="${customerBillingAddress4}" class="form-control" name="customerBillingAddress4" id="customerBillingAddress4" pattern="^([1-9])([0-9]){5}$" />
												<div class="help-block with-errors"></div>	
											</div>
										</div>
									</div>	

									<div class="row">
										<div class="col-lg-12">
											<div class="form-group">
											<input type="checkbox" path="customerAddressCheckbox" checked name="customerAddressCheckbox" id="customerAddressCheckbox" onclick="hideShowDeleiverySection()" />
											<label  path="customerAddressCheckbox">Delivery Address is Same As Billing Address</label>
											</div>
										</div>
									</div>	
									<div class="row" id="deliveryAddressSection" style="display:none">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerDeliveryAddress1">Delivery Address</label>
												<input type="text" maxlength="50" path="customerDeliveryAddress1" value="${customerDeliveryAddress1}" class="form-control" name="customerDeliveryAddress1" id="customerDeliveryAddress1" />
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerDeliveryAddress2">State</label>
												
												<select path="customerDeliveryAddress2" id="customerDeliveryAddress2" value="${customerDeliveryAddress2}" name="customerDeliveryAddress2" class="form-control" onchange="return GetCity(this.value,'DeliveryAddress');">
											 	<option value="0">Select</option>
												<c:forEach items="${exe_state}" var="states"> 
														<option value="${states}" ${states == sel_customerDeliveryAddress2 ? 'selected="selected"' : ''}>${states}</option>
											    	</c:forEach>
												</select>
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerDeliveryAddress3">City</label>
												
												<select path="customerDeliveryAddress3" id="customerDeliveryAddress3" value="${customerDeliveryAddress3}" name="customerDeliveryAddress3" class="form-control">
	                                            	<option value="0">Select </option>
													<c:forEach items="${exe_city}" var="city"> 
														<option value="${city}" ${city == sel_customerDeliveryAddress3 ? 'selected="selected"' : ''}>${city}</option>
												    </c:forEach>
							                    </select>
												
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerDeliveryAddress4">Pin Code</label>
												<input type="text" path="customerDeliveryAddress4" value="${customerDeliveryAddress4}" class="form-control" name="customerDeliveryAddress4" maxlength="6" id="customerDeliveryAddress4" pattern="^([1-9])([0-9]){5}$" />
												<div class="help-block with-errors"></div>	
											</div>
										</div>
									</div>	
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerECCNO">ECC NO</label>
												<input type="text" maxlength="50" path="customerECCNO" value="${customerECCNO}" class="form-control" id="customerECCNO" name="customerECCNO"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerCSTNO">CST NO</label>
												<input type="text" maxlength="50" path="customerCSTNO" value="${customerCSTNO}" class="form-control" id="customerCSTNO" name="customerCSTNO"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerTINNO">TIN NO</label>
												<input type="text" maxlength="50" path="customerTINNO" value="${customerTINNO}" class="form-control" id="customerTINNO" name="customerTINNO"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerIECNO">IEC NO</label>
												<input type="text" maxlength="50" path="customerIECNO" value="${customerIECNO}" class="form-control" id="customerIECNO" name="customerIECNO"/>
											</div>
										</div>
									</div>	
									<h4>Consultant details</h4>
									<hr />
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerConsultantType">Type </label>
												<select name="customerConsultantType" id="customerConsultantType" class="form-control">
													<option value="0">Select</option>
													<option selected>Existing</option>
													<option>New</option>
													<option>None</option>
													
												
												</select>
											</div>
										</div>
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerConsultant">Consultant Name</label>
												<input type="text" maxlength="50" path="customerConsultant" value="${customerConsultant}" class="form-control" name="customerConsultant" id="customerConsultant"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerConsultantPersonName">Consultant Person Name</label>
												<input type="text" maxlength="100" path="customerConsultantPersonName" value="${customerConsultantPersonName}" class="form-control" name="customerConsultantPersonName" id="customerConsultantPersonName"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerConsultantNumber">Contact Number</label>
												<input type="text" maxlength="12" path="customerConsultantNumber" value="${customerConsultantNumber}" class="form-control" name="customerConsultantNumber" id="customerConsultantNumber" pattern="^([0|\+[0-9]{1,5})?([1-9][0-9]{9})$"/>
												<div class="help-block with-errors"></div>
											</div>
										</div>
										
									</div>
									<h4>PMC details</h4>
									<hr />
									
									<div class="row">
									<div class="col-lg-3">
											<div class="form-group">
												<label path="pmcType">Type </label>
												<select name="pmcType" id="pmcType" class="form-control">
													<option value="0">Select</option>
													<option selected>Existing</option>
													<option>New</option>
													<option>None</option>
													
												
												</select>
											</div>
										</div>
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="pmc">PMC</label>
												<input type="text" maxlength="50" path="pmc" value="${pmc}" class="form-control" name="pmc" id="pmc"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="pmcPerson">PMC Person Name</label>
												<input type="text" maxlength="50" path="pmcPerson" value="${pmcPerson}" class="form-control" name="pmcPerson" id="pmcPerson"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="pmcContact">PMC Contact</label>
												<input type="text" maxlength="12" path="pmcContact" value="${pmcContact}" class="form-control" name="pmcContact" id="pmcContact" pattern="^([0|\+[0-9]{1,5})?([1-9][0-9]{9})$"/>
												<div class="help-block with-errors"></div>
											</div>
										</div>
										
									</div>
									<div class="row">
									<div class="col-lg-3">
											<div class="form-group">
												<label path="pmcAlternateContact">PMC Alternate Contact</label>
												<input type="text" maxlength="12" path="pmcAlternateContact" value="${pmcAlternateContact}" class="form-control" name="pmcAlternateContact" id="pmcAlternateContact" pattern="^([0|\+[0-9]{1,5})?([1-9][0-9]{9})$"/>
												<div class="help-block with-errors"></div>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="pmcEmail">PMC Email</label>
												<input type="email" path="pmcEmail" value="${pmcEmail}" class="form-control" name="pmcEmail" id="pmcEmail"/>
												<div class="help-block with-errors"></div>
											</div>
										</div>
									</div>

									<div align="middle"><input type="button" value="Update" id="customerButton" class="btn btn-success" onclick="return storeCustomerDetails()"/></div>
								</form>	
                                </div>
								<div class="tab-pane fade" id="itemDetails">
                                	<br />
                                	<form data-toggle="validator" role="form" novalidate="true">   
									<div class="row" id="addItemDetails" style="display:none">
										<div class="col-lg-12">
											<div class="panel panel-primary">
		                                    	<a data-toggle="collapse" href="#collapseOne" style="text-decoration:none"  >
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Add Item
													</div>
												</a>
			                                    <div id="collapseOne" class="panel-collapse collapse" aria-expanded="false" >
			                                        <div class="panel-body">
																<div class="row">
																	
																	<div class="col-lg-3" style="display:none">
																		<div class="form-group">
																			<label path="itemEngineType">Engine Type</label>  <span class="text-alert-danger">*</span>
																			<select path="engineType" value="${engineType}" class="form-control" name="engineType" id="engineType" onChange="GetItemModelDetails(this.value)">
																				<option value="0">Select Type</option>
																				<option value="ENGINE">Engine</option>
																				<option value="ALTERNATOR">Alternator</option>
																				
																			</select>
																			
																			<input type="hidden" path="leadID" value="${leadID}" class="form-control" readonly="true"/>
																		</div>
																	</div>
																	
																	<div class="col-lg-6">
																		<div class="form-group">
																			<label path="itemEngineType">Choose Engine </label>
																			<select path="engineMakeType" value="${engineMakeType}" class="form-control" name="engineMakeType" id="engineMakeType">
																			<option value="0">Select</option>	
																				
																				
																			</select>
																		</div>
																	</div>
																	
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="itemVoltage">Alternator</label>
																			<input type="text" path="alternator" maxlength="100" value="${alternator}" class="form-control" id="alternator" name="alternator" />
																			
																			
																		</div>
																	</div>
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="itemHz">Hz</label>
																			
																			<select  id="itemHz" name="itemHz"  class="form-control" >
																			<option value="0">Select</option>
																				<c:forEach items="${hzType}" var="hztype">
																					<option value="${hztype}">${hztype}</option>
																				</c:forEach>
																			</select>
																			
																			
																			
																			
																		</div>
																	</div>
																	
																</div>
																
																<div class="row">
																
															<input type="hidden" path="itemRatingType"  name="itemRatingType" value="${itemRatingType}" value="${itemRatingType}" class="form-control"/>																	
															<input type="hidden" path="itemModelNo" value="${itemModelNo}" class="form-control"/>
																
																	
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="itemVoltage">Voltage</label>
																			<input type="text" path="itemVoltage" value="${itemVoltage}" class="form-control" id="itemVoltage" name="itemVoltage" maxlength="10" pattern="^[1-9]\d*(\.\d+)?$"  />
																			<div class="help-block with-errors"></div>
																			
																			
																		</div>
																	</div>
																	
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="itemQty">Qty</label>
																			<input type="text" path="itemQty" maxlength="5" value="${itemQty}" class="form-control" id="itemQty" name="itemQty" pattern="^[1-9]\d*(\.\d+)?$" />
																			
																			
																		</div>
																	</div>
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="itemAmount">Amount (In Lakh)</label>
																			<input type="text" maxlength="10" path="itemAmount" value="${itemAmount}" class="form-control" id="itemAmount" name="itemAmount" pattern="^[1-9]\d*(\.\d+)?$" />
																		</div>
																	</div>
																	
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="coolingSystem">Cooling System</label>  <span class="text-alert-danger">*</span>
																			<select path="coolingSystem" id="coolingSystem" name="coolingSystem" value="${coolingSystem}" class="form-control" >
																			<option value="0">Select</option>
																				<c:forEach items="${coolingSystemType}" var="coolingSystem">
																					<option value="${coolingSystem}">${coolingSystem}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																	
																	<!-- 
																	<div class="col-lg-6">
																		<div class="form-group">
																			<button type="button" class="btn btn-success">Submit</button>
																			<button type="button" class="btn btn-warning">Reset</button>
																		</div>
																	</div>  -->
																</div>
																
																
																<div align="middle"><input type="button" value="Add" id="itemDetailsAddButton" class="btn btn-success" onclick=" return storeItemDetails()"/>
																<input type="button" value="Update" id="itemDetailsNextButton" class="btn btn-success" onclick="MoveToCommentsSection()" style="display:none" />
																</div>
																
																
			                                        </div>
			                                    </div>
	                                		</div>
										</div>
	                                </div>
									
									<div class="row">
										<div class="col-lg-12">
											<div class="panel panel-primary">
		                                    	<a data-toggle="collapse" href="#collapseTwo" style="text-decoration:none"  >
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Item/Schedule  Details
													</div>
												</a>
			                                    <div id="collapseTwo" class="panel-collapse collapse in" aria-expanded="false" >
			                                    	<div class="panel-body">
														<div class="dataTable_wrapper">
							                                <table class="table table-striped table-bordered table-hover">
							                                    <thead>
							                                        <tr>
							                                            
																		<th>Rating</th>
																		<th>Engine Model</th>
							                                            <th>Alternator Model</th>
							                                            <th>Voltage</th>
							                                            <th>Qty</th>
																		<th>Value (In Lakhs)</th>
																		<th>Action</th>
							                                        </tr>
							                                    </thead>
															   <tbody id="tableBody">
															   
															   <c:forEach items="${leadItemsList}" var="leadItems"> 
																	<tr>
																	<td>${leadItems.DTL_RATING}</td>
																	<td>${leadItems.DTL_ENGINE_MODEL}</td>
																	<td>${leadItems.DTL_ALTERNATOR_MODEL}</td>
																	<td>${leadItems.DTL_VOLTAGE}</td>
																	
																	<td>${leadItems.DTL_QTY}</td>
																	<td>${leadItems.DTL_AMOUNT}</td>
																	<td>
																	<!--
																	javascript:addSchedule('${leadItems.DTL_ID}','${leadItems.LEAD_ID}')
																	-->
																	<a href="#" title="Click Here to Add Schedule" data-id="${leadItems.DTL_ID}" data-title-id="Add Schedule" data-lead-id="${leadItems.LEAD_ID}" data-engine="${leadItems.DTL_ENGINE_MODEL}" data-qty="${leadItems.DTL_QTY}" data-alternator="${leadItems.DTL_ALTERNATOR_MODEL}" data-amount="${leadItems.DTL_AMOUNT}"  class="fa fa-plus fa-2x" data-toggle="modal" data-target="#myModal"></a>
																	
																	<a href="#" title="Click Here to View Schedule" data-id="${leadItems.DTL_ID}" data-title-id="View Schedule" data-lead-id="${leadItems.LEAD_ID}" data-engine="${leadItems.DTL_ENGINE_MODEL}" data-qty="${leadItems.DTL_QTY}" data-alternator="${leadItems.DTL_ALTERNATOR_MODEL}" data-amount="${leadItems.DTL_AMOUNT}"  class="fa fa-eye fa-2x" data-toggle="modal" data-target="#myModal" style="display:none"></a>
																	
																	<a href="#" title="Technical Information" data-id="${leadItems.DTL_ID}" data-title-id="Add Technical" data-lead-id="${leadItems.LEAD_ID}" data-engine="${leadItems.DTL_ENGINE_MODEL}" data-qty="${leadItems.DTL_QTY}" data-alternator="${leadItems.DTL_ALTERNATOR_MODEL}" data-amount="${leadItems.DTL_AMOUNT}"  class="fa fa-wrench fa-2x" data-toggle="modal" data-target="#myTechnicalModal"></a>
																	
																	<input type="hidden" id="updateScheduleRole" value="${lroleId}" />
																	
																	
																	</td>
																	</tr>
																</c:forEach>
													
							                                      
							                                    </tbody>
							                                </table>
							                        	</div>
			                            				<!-- /.table-responsive -->
			                                    	</div>
													
		                                    	</div>
		                                	</div>
										</div>
	                                </div>
									</form>
                                </div>
                               
                                
                                 <div class="tab-pane fade" id="settings">
                                    
									<br />
									
									<div class="row">
										<div class="col-lg-12">
											<div class="panel panel-primary">
		                                    	<a data-toggle="collapse" href="#collapseComments" style="text-decoration:none"  >
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Comments & Attachment
													</div>
												</a>
		                                    	<div id="collapseComments" class="panel-collapse collapse in" aria-expanded="false" >
			                                        <div class="panel-body">
														
														
																	<!-- Nav tabs -->
																	<ul class="nav nav-pills">
																		<li class="active"><a href="#home-pills" data-toggle="tab" aria-expanded="true">Comments</a>
																		</li>
																		<li class=""><a href="#profile-pills" data-toggle="tab" aria-expanded="false">Attachements</a>
																		</li>
																		<li><a href="#messages-pills" data-toggle="tab" aria-expanded="false" onclick="MoveToCostSummary()">Move To Next</a>
																		</li>
																		
																	</ul>

																	<!-- Tab panes -->
																	<div class="tab-content">
																		<div class="tab-pane fade active in" id="home-pills" style="margin-top:20px">
																		
																		<div class="row">
																					<div class="col-lg-6">
																						<div class="form-group">
																							<label path="comments">Add New Comments</label>
																							<textarea path="comments" maxlength="200" value="${comments}" name="comments" class="form-control" rows="3" id="comments"></textarea>
																						</div>
																					</div>
																					
																					<div class="col-lg-6">
																						<div class="form-group">
																							<br />
																							<input type="button" value="Submit" id="commentsSubmitButton"  class="btn btn-success" onclick="storeComments()" />
																						</div>
																					</div>					
																				
																		</div>
																		
																		<div class="row">
																			<div class="col-lg-12">
																				<div class="panel panel-primary">
																					<a data-toggle="collapse" href="#collapseAddComments" style="text-decoration:none" aria-expanded="true" class="">
																						<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
																							Comments Details
																						</div>
																					</a>
																					<div id="collapseAddComments" class="panel-collapse collapse in" aria-expanded="true">
																						<div class="panel-body">
																							<div class="dataTable_wrapper">
																								<table class="table table-striped table-bordered table-hover">
																									<thead>
																										<tr>
																											
																											<th>Sno</th>
																											<th>Comments</th>
																											<th>Created By</th>
																											<th>Created Date</th>
																											<th>Types</th>
																											
																											
																										</tr>
																									</thead>
																								   <tbody id="tableCommentsBody">
																									   <c:forEach items="${commentsList}" var="comments" varStatus="count"> 
																										<tr>
																										<td>${count.count}</td>
																										<td>${comments.DETAILS}</td>
																										<td>${comments.CREATED_BY}</td>
																										<td>${comments.CRETAED_DATE}</td>
																										<td>${comments.TYPES}</td>
																										
																										
																										</tr>
																									</c:forEach>
																									</tbody>
																								</table>
																							</div>
																							<!-- /.table-responsive -->
																						</div>
																						
																					</div>
																				</div>
																			</div>
																		</div>
																
																		</div>
																		<div class="tab-pane fade" id="profile-pills" style="margin-top:20px">
																			
																			
																	<form id="UploadForm" action="UploadFile" method="post" enctype="multipart/form-data" >
																		<div class="row">
																		<div class="col-lg-6">
																			<div class="form-group">
																				<label path="comments">Add New Attachment <span class="text-alert-danger">*</span></label> 
																				<input type="file"  id="myfile" name="myfile" class="form-control">
																				<div id="progressbox">
																						<div id="progressbar"></div>
																						<div id="percent">0%</div>
																						<span id="message"></span>
																					</div>
																					<br />
																			</div>
																		</div>			
																					
																		<input type="hidden" path="leadID" value="${leadID}" class="form-control" name="leadID" readonly="true" id="attachmentID"/>
																		<input type="hidden" path="userID" value="${userID}" class="form-control" name="createdBy" readonly="true"/>
																		
																		<div class="col-lg-6">
																				<div class="form-group">
																					<br />
																					<input type="submit" value="Submit" id="commentsSubmitButton"  class="btn btn-success" onclick="return hideSubmit()"/>
																				</div>
																			</div>																		
																		
																					
																					
																		</div>
															
															
																	</form>
																	
																	<div class="row">
																			<div class="col-lg-12">
																				<div class="panel panel-primary">
																					<a data-toggle="collapse" href="#collapseAddAttachments" style="text-decoration:none" aria-expanded="true" class="">
																						<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
																							Attachments Details
																						</div>
																					</a>
																					<div id="collapseAddAttachments" class="panel-collapse collapse in" aria-expanded="true">
																						<div class="panel-body">
																							<div class="dataTable_wrapper">
																								<table class="table table-striped table-bordered table-hover">
																									<thead>
																										<tr>
																											
																											<th>SNo</th>
																											<th>Attachment</th>
																											<th>Created By</th>
																											<th>Created Date</th>
																											
																											
																										</tr>
																									</thead>
																								   <tbody id="tableAttachmentsBody">
																									<c:forEach items="${attachmentsList}" var="attachments" varStatus="count"> 
																									<tr>
																									<td>${count.count}</td>
																									<td><a href="${attachments.DOC_URL}">${attachments.DOC_NAME}</a></td>
																									
																									<td>${attachments.DOC_CREATED_BY}</td>
																									<td>${attachments.DOC_CREATED_DATE}</td>
																									
																									
																									</tr>
																								</c:forEach>   
																									</tbody>
																								</table>
																							</div>
																							<!-- /.table-responsive -->
																						</div>
																						
																					</div>
																				</div>
																			</div>
																		</div>
																			
																			
																		</div>
																		<div class="tab-pane fade" id="messages-pills" style="margin-top:20px">
																			
																			<input type="button" value="Next" id="commentsNextButton" class="btn btn-success" style="display:none" onclick="MoveToCostSummary()"/>
																		</div>
																		
																	</div>
																
														<!--
														
														<form id="UploadForm" action="UploadFile" method="post" enctype="multipart/form-data" >
															<div class="row">
																<div class="col-lg-6">
																	<div class="form-group">
																		<label path="comments">Comments</label>
																		<textarea path="comments" value="${comments}" name="comments" class="form-control" rows="3"></textarea>
																	</div>
																</div>
																<div class="col-lg-6">
																	<div class="form-group">
																		<label path="comments">Attachment</label> <span class="text-alert-danger">*</span>
																		<input type="file"  id="myfile" name="myfile" class="form-control">
																		<div id="progressbox">
																				<div id="progressbar"></div>
																				<div id="percent">0%</div>
																				<span id="message"></span>
																			</div>
																			<br />
																	</div>
																</div>
																		<input type="hidden" path="leadID" value="$(leadID)" class="form-control" name="leadID" readonly="true" id="attachmentID"/>
																		<input type="hidden" path="userID" value="${userID}" class="form-control" name="createdBy" readonly="true"/>
																		
																		
															<div align="middle"><input type="submit" value="Submit" id="commentsSubmitButton"  class="btn btn-success" onclick="return hideSubmit()"/>
															<input type="button" value="Next" id="commentsNextButton" class="btn btn-success" style="display:none" onclick="MoveToCostSummary()"/>
															</div>
																
																
															</div>
															
															
														</form>
														
														
														
														-->
													</div>
												</div>
											</div>
										</div>
									</div>
									
								 	
								
                            </div>
							
							
							<div class="tab-pane fade" id="costsummarysheet">
                                   
									<br />
									
									<div class="row" id="addCostSummary" style="display:none">
										<div class="col-lg-12">
											<div class="panel panel-primary">
		                                    	<a data-toggle="collapse" href="#collapseCostSummary" style="text-decoration:none"  >
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Upload Cost Summary Sheet
													</div>
												</a>
		                                    	<div id="collapseCostSummary" class="panel-collapse collapse" aria-expanded="false" >
			                                        <div class="panel-body">
														<form id="UploadForm1" action="CostSummary" method="post" enctype="multipart/form-data">
															<div class="row">
																<div class="col-lg-12">
																<a href="Parameter File.xlsx" style="float:right" target="_blank">Download Cost Summary Sheet</a>
																	<div class="form-group">
																		<label path="costsummary">Choose Cost Summary</label>
																		<input type="file"  id="myfile1" name="myfile1" class="form-control">
																		<div id="progressbox1">
																				<div id="progressbar1"></div>
																				<div id="percent1">0%</div>
																			</div>
																			<br />
																	</div>
																</div>
																<input type="hidden" path="leadID" value="${leadID}" class="form-control" name="leadID" readonly="true"/>
																<input type="hidden" path="userID" value="${userID}" class="form-control" name="createdBy" readonly="true"/>
																<div align="middle">
																
																<input type="submit" value="Submit" id="CostSubmitButton" class="btn btn-success"/>
																<input type="button" value="Next" id="CostNextButton" class="btn btn-success" style="display:none" onclick="MoveToFinalStep()"/>
																
																</div>
															</div>
															
															
														</form>
														
														
														<div class="alert alert-danger alert-dismissable" id="matchingerror" style="display:none">
															<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
															Total Item Cost Amount and Cost Summary Sheet Project Total Amount is not matching , please check and click Next button to Continue <a href="#" class="alert-link">Matching Error</a>.
														</div>
														<div class="alert alert-success alert-dismissable" id="matchingsuccess" style="display:none">
															<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
															click Next button to Continue <a href="#" class="alert-link">Success</a>.
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-12">
											<div class="panel panel-primary">
		                                    	<a data-toggle="collapse" href="#collapseCostSheet" style="text-decoration:none"  >
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Cost Sheet  Details
													</div>
												</a>
			                                    <div id="collapseCostSheet" class="panel-collapse collapse in" aria-expanded="false" >
			                                    	<div class="panel-body">
														<div class="dataTable_wrapper">
							                                <table class="table table-striped table-bordered table-hover">
							                                    <thead>
							                                       <tr>
							                                            
																		<th>SNo</th>
																		<th>Document</th>
							                                            <th>Created By</th>
							                                            <th>Created Date</th>
							                                            
							                                        </tr>
							                                    </thead>
															   <tbody id="tableBody">
															   
															   <c:forEach items="${costSheetList}" var="attachments" varStatus="count"> 
																	<tr>
																	<td>${count.count}</td>
																	<td><a href="${attachments.DOC_URL}">${attachments.DOC_NAME}</a></td>
																	
																	<td>${attachments.DOC_CREATED_BY}</td>
																	<td>${attachments.DOC_CREATED_DATE}</td>
																	
																	
																	</tr>
																</c:forEach>
													
							                                      
							                                    </tbody>
							                                </table>
							                        	</div>
			                            				<!-- /.table-responsive -->
			                                    	</div>
													
		                                    	</div>
		                                	</div>
										</div>
	                                </div>
									
									
									
									
									<div class="row">
										<div class="col-lg-12">
											<div class="panel panel-primary">
		                                    	<a data-toggle="collapse" href="#collapsePrice" style="text-decoration:none"  >
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Price Summary
													</div>
												</a>
		                                    	<div id="collapsePrice" class="panel-collapse collapse in" aria-expanded="false" >
			                                        <div class="panel-body">
															<div class="row">
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">DG Value</label>
																		<input type="text" name="DGValue" id="DGValue" value="${DGValue}" class="form-control" />
																	</div>
																</div>
																
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">Project Total</label>
																		<input type="text" name="projecttotal" id="projecttotal" value="${projecttotal}" class="form-control" />
																	</div>
																</div>
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">Low Side Supply Basic</label>
																		<input type="text" name="lowsidesupbasic" id="lowsidesupbasic" value="${lowsidesupbasic}" class="form-control" />
																	</div>
																</div>
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">Low Side Labour Basic</label>
																		<input type="text" name="lowsidelabbasic" id="lowsidelabbasic" value="${lowsidelabbasic}" class="form-control" />
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">Low Side Margin</label>
																		<input type="text" name="lowsidesmargin" id="lowsidesmargin" value="${lowsidesmargin}" class="form-control" />
																	</div>
																</div>
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">Project Margin</label>
																		<input type="text" name="projectmargin" id="projectmargin" value="${projectmargin}" class="form-control" />
																	</div>
																</div>
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">DG Selling Value</label>
																		<input type="text" name="dgSellingValue" id="dgSellingValue" value="${dgSellingValue}" class="form-control" />
																	</div>
																</div>
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">Low Side Selling Value</label>
																		<input type="text" name="lowSideSellignValue" id="lowSideSellignValue" value="${lowSideSellignValue}" class="form-control" />
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">Project Selling Value</label>
																		<input type="text" name="projectSellingValue" id="projectSellingValue" value="${projectSellingValue}" class="form-control" />
																	</div>
																</div>
																
															</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									
								
                            </div>
							
							
							
							<div class="tab-pane fade" id="LeadcreateIndent">
                                    <div class="row" id="viewValidateMessage">
										<div class="col-lg-12" style="margin-top:10px">
										
										<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> Wow !!! You are just one step behind creating indent , please click validate button 
																		
																		<button class="btn btn btn-primary" onclick="return validateMessage()" style="float:right">Validate</button>
																		
																		
																	  </div>
										</div>
										
										</div>
									</div>
									
									
									<div class="row" id="viewMessage" style="display:none">
										<div class="col-lg-12" style="margin-top:10px">
											<div class="panel panel-primary">
		                                    	<a data-toggle="collapse" href="#collapsevalidation" style="text-decoration:none" >
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
													Indent Summary
													</div>
												</a>
		                                    	<div id="collapsevalidation" class="panel-collapse collapse in" aria-expanded="false" >
			                                        <div class="panel-body">
														
															<div class="row">
																<div class="col-lg-12">
																	<div class="form-group">
																	
																	
																	
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> <span id="leadScheduleVMessage">Schedule Added Successfully</span>
																	  </div>
																	</div>
																	
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> <span id="leadTechnicalVMessage">Schedule Added Successfully</span>
																	  </div>
																	</div>
																	
																	
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> <span id="leadCommercialDGVMessage">Schedule Added Successfully</span>
																	  </div>
																	</div>
																	
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> <span id="leadCommercialINSTVMessage">Schedule Added Successfully</span>
																	  </div>
																	</div>
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> <span id="leadPaymentDGVMessage">Schedule Added Successfully</span>
																	  </div>
																	</div>
																	
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> <span id="leadPaymentINSTVMessage">Schedule Added Successfully</span>
																	  </div>
																	</div>
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> <span id="leadSupplyScopeVMessage">Schedule Added Successfully</span>
																	  </div>
																	</div>
																	
																<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		
																		<button class="btn btn-lg btn-warning" id="loadingFile"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> Indent Report Sheet Generating, Please Wait.......</button>
																		
																		<div id="loadedFile"><i class="fa fa-check-circle"></i> <a href="#" id="downlaodLink" target="_blank"></a>
																		</div>
																	  </div>
																	</div>
																		
																		
																		
																		
																	</div>
																</div>
																
																<div class="col-lg-12">
																	<div class="form-group">
																		
																	<div class="well well-transparent">
																	  <div style="font-size: 24px; line-height: 1.5em;">
																		<i class="fa fa-thumbs-o-up"></i> Huraay !!! Indent Created Successfully
																	  </div>
																	</div>
																		
																	</div>
																</div>
																
																<div class="col-lg-4">
																	<div class="well well-transparent">
																	  <div style="font-size: 24px; line-height: 1.5em;">
																	  
																		<a href="MainController?action=listIndent&search=All">Go To Indent List</a>
																	  </div>
																	</div>
																
																</div>
																
																<div class="col-lg-4">
																
																<div class="well well-transparent">
																	  <div style="font-size: 24px; line-height: 1.5em;">
																		<a href="index.jsp">Go To Dashboard</a>
																	  </div>
																	</div>
																
																</div>
																<div class="col-lg-4">
																<div class="well well-transparent">
																	  <div style="font-size: 24px; line-height: 1.5em;">
																		<a href="MainController?action=listQualifiedLead&search=All">Create Indent</a>
																	  </div>
																	</div>
																
																</div>
																
																		
																		
															
																
																
															</div>
															
															
														
													</div>
												</div>
											</div>
										</div>
									</div>
									
									
								
                            </div>
							
							
							
							<div class="tab-pane fade" id="SupplyScope">
                                    
									
									<div class="row">
										<div class="col-lg-12" style="margin-top:10px">
											<div class="panel panel-primary">
		                                    	<a data-toggle="collapse" href="#collapseScope" style="text-decoration:none">
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Supply Scope
													</div>
												</a>
		                                    	<div id="collapseScope" class="panel-collapse collapse in" aria-expanded="false" >
												
			                                        <div class="panel-body">
													<div class="row">
															<div class="col-lg-12">
																<div class="col-lg-6">
																
																	<div class="form-group">
																		<label path="customerBillingAddress1">Warranty From Date of Commissioning</label>
																		<input type="text" value="" maxlength="200" class="form-control" name="warranty_info" id="warranty_info">
																	
																	</div>
																
																</div>
																<div class="col-lg-3">
																	
																	<div class="form-group">
																	<label path="customerBillingAddress1">Operator Requirement <input type="checkbox"   name="operator_required" id="operator_required"></label>
																		
																	
																	</div>
																
																</div>
																<div class="col-lg-3">
																	
																	<div class="form-group">
																	<label path="customerBillingAddress1"></label>
																	<input type="button" value="Update" id="storeSupply" class="btn btn-primary" onclick="return getAllsupplyscope('Add');" style="display: block;">
																	
																	</div>
																
																</div>
															</div>
															
															</div>	
															
														<div class="row">
																<div class="col-lg-6">
																<form name="factorysupplyscopeForm">
																	<div class="form-group">
																	
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> <span id="factoryName">SGPL - SILVASSA</span>
																	  </div>
																	</div>
																	<c:forEach items="${factorySupplyScope}" var="factorySupply"> 
																		<div class="checkbox">
																			<label>
																				 <input type="checkbox"  value="${factorySupply.value}" <c:choose>
																							<c:when test="${factorySupply.link1=='1'}">
																								
																								checked
																								
																							</c:when> 
																							 
																							<c:otherwise>
																								
																								false
																								
																								
																							</c:otherwise>
																						</c:choose>
																						> ${factorySupply.value}
																			</label>
																		</div>
																	</c:forEach>
																	</div>
																</form>
																</div>
																<div class="col-lg-6">
																	<form name="branchsupplyscopeForm">
																	<div class="form-group">
																	
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> <span id="branchName">EXECUTION BRANCH</span>
																	  </div>
																	</div>
																	<c:forEach items="${branchSupplyScope}" var="branchSupply"> 
																		<div class="checkbox">
																			<label>
																				<input type="checkbox" value="${branchSupply.value}"
																				
																				<c:choose>
																							<c:when test="${branchSupply.link1=='1'}">
																								
																								checked
																								
																							</c:when> 
																							 
																							<c:otherwise>
																								
																								
																								
																								
																							</c:otherwise>
																						</c:choose>
																						>${branchSupply.value} 
																				
																			</label>
																		</div>
																	</c:forEach>
																	</div>
																</form>
																</div>
															
															</div>
															
														
														
													</div>
												</div>
											</div>
										</div>
									</div>
									
									
								
                            </div>
							
							<div class="tab-pane fade" id="CommercialInformation">
                                    
								<form data-toggle="validator" role="form" novalidate="true">   	
									<div class="row">
										<div class="col-lg-12" style="margin-top:10px">
											<div class="panel panel-primary">
		                                    	<a data-toggle="collapse" href="#collapseCommercial" style="text-decoration:none">
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Commercial Information
													</div>
												</a>
		                                    	<div id="collapseCommercial" class="panel-collapse collapse in" aria-expanded="false" >
			                                        <div class="panel-body">
														
														
																	<!-- Nav tabs -->
																	<ul class="nav nav-pills">
																		<li class="active"><a href="#dgset-pills" data-toggle="tab" aria-expanded="true">DG Set</a>
																		</li>
																		<li class=""><a href="#installation-pills" data-toggle="tab" aria-expanded="false">Installation</a>
																		</li>
																		
																		
																	</ul>

																	<!-- Tab panes -->
																	<div class="tab-content">
																	
																	<div class="tab-pane fade active in" id="dgset-pills" style="margin-top:20px">
																		<div class="panel panel-primary">
																		<a data-toggle="collapse" href="#collapseCommercialDG" style="text-decoration:none"  >
																			<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
																				DG Set Pricing Details
																			</div>
																		</a>
															<div id="collapseCommercialDG" class="panel-collapse collapse in" aria-expanded="false" >
																		<div class="row">
																					
																			<div class="col-lg-12">
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">DG PRICE (Basic)</label>
																			<input  type="text" id="commercialDGPrice" maxlength="8"  class="form-control" name="commercialDGPrice" value="0" onkeyup="addDGTotal()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   >
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">EXCISE DUTY- 12.5%</label>
																			<input  type="text" id="commercialexcise"  class="form-control" name="commercialexcise" value="0" onkeyup="addDGTotal()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">CESS</label>
																			<input  type="text" id="commercialCESS"  class="form-control" name="commercialCESS" value="0" onkeyup="addDGTotal()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">SHEC</label>
																			<input  type="text" id="commercialSHEC"  class="form-control" name="commercialSHEC" value="0" onkeyup="addDGTotal()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-4">
																						<div class="form-group">
																							<label path="comments">SubTotal</label>
																			<input  type="text" id="commercialTOTAL"  class="form-control" name="commercialTOTAL" readonly value="0"  pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																									
																		</div>		
																		<script>
																		function addFirstSubTotal()
																		{
																			commercialexcise=document.getElementById("commercialexcise").value;
																			commercialCESS=document.getElementById("commercialCESS").value;
																			commercialSHEC=document.getElementById("commercialSHEC").value;
																			commercialDGPrice=document.getElementById("commercialDGPrice").value;
																			sum1=parseFloat(commercialexcise)+parseFloat(commercialCESS)+parseFloat(commercialSHEC)+parseFloat(commercialDGPrice);
																			document.getElementById("commercialTOTAL").value=sum1;
																			return sum1;
																		}
																		</script>
																		</div>
																		
																		
																		<div class="row">
																		<div class="col-lg-12">
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">VAT @(5.5%)</label>
																			<input  type="text" id="commercialVAT" value="0" class="form-control" name="commercialVAT" onkeyup="addDGTotal()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">Entry Tax</label>
																			<input  type="text" id="commercialEntrytax" value="0" class="form-control" name="commercialEntrytax" onkeyup="addDGTotal()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">OCTROI</label>
																			<input  type="text" id="commercialOCTROI" value="0" class="form-control" name="commercialOCTROI" onkeyup="addDGTotal()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">Any Other Tax</label>
																			<input  type="text" id="commercialAnyOther" value="0" class="form-control" name="commercialAnyOther" onkeyup="addDGTotal()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group" >
																							<label path="comments">F&I</label>
																			<input  type="text" id="commercialFI" value="0" class="form-control" name="commercialFI" onkeyup="addDGTotal()"  pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">SubTotal</label>
																			<input  type="text" id="commercialsubTOTAL" value="0" class="form-control" name="commercialsubTOTAL" readonly />
																						</div>
																					</div>
																					
																									
																		</div>	
																		<script>
																		function addSecondSubTotal()
																		{
																			commercialVAT=document.getElementById("commercialVAT").value;
																			commercialEntrytax=document.getElementById("commercialEntrytax").value;
																			commercialOCTROI=document.getElementById("commercialOCTROI").value;
																			commercialAnyOther=document.getElementById("commercialAnyOther").value;
																			commercialFI=document.getElementById("commercialFI").value;
																			sum=parseFloat(commercialVAT)+parseFloat(commercialEntrytax)+parseFloat(commercialOCTROI)+parseFloat(commercialAnyOther)+parseFloat(commercialFI);
																			document.getElementById("commercialsubTOTAL").value=sum;
																			return sum;
																		}
																		</script>																		
																		</div>
																		
																		<div class="row">
																					<div class="col-lg-12">
																					<div class="col-lg-2">
																						<div class="form-group" >
																							<label path="comments">No Of DG Set</label>
																			<input  type="text" id="commercialNoofDG" value="1" class="form-control" name="commercialNoofDG" onkeyup="addDGTotal()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-4">
																						<div class="form-group">
																							<label path="comments">SubTotal (A) DG Set</label>
																			<input  type="text" id="commercialFinalTOTAL" value="0" class="form-control" name="commercialFinalTOTAL" readonly  />
																						</div>
																					</div>
																					
																					<script>
																		function addDGTotal()
																		{
																			commercialNoofDG=document.getElementById("commercialNoofDG").value;
																			
																			sumTotal=parseFloat(commercialNoofDG)*(parseFloat(addFirstSubTotal())+parseFloat(addSecondSubTotal()));
																			document.getElementById("commercialFinalTOTAL").value=sumTotal;
																			return sumTotal;
																		}
																		</script>
																					<div class="col-lg-4">
																						<div class="form-group">
																							<br />
																							<input type="button" value="Submit" id="priceDGAddButton"  class="btn btn-success" onclick="storePriceInfo('DG','Add')" />
																							<input type="button" value="Submit" id="priceDGUpdateButton"  class="btn btn-success" onclick="storePriceInfo('DG','Update')"   style="display:none" />
																						</div>
																					</div>	
																					
																					</div>
																					
																					
																									
																				
																		</div>
																		</div>
																	</div>
																		
																		
																		
																	<div class="panel panel-primary">
																		<a data-toggle="collapse" href="#collapseCommercialDGPayments" style="text-decoration:none"  >
																			<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
																				Payment & Other Details
																			</div>
																		</a>
															<div id="collapseCommercialDGPayments" class="panel-collapse collapse" aria-expanded="false" >
																<div class="panel-body">
																		<div class="row">
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">Advance</label>
																			<input  type="text" id="commercialDGAdvance" value="${commercialDGAdvance}" class="form-control" name="commercialDGAdvance" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">CHE/DD</label>
																			<input  type="text" id="commercialDGcheque" value="${commercialDGcheque}" class="form-control" name="commercialDGcheque" />
																						</div>
																					</div>
																					<div class="col-lg-3">
																						<div class="form-group">
																							<label path="comments">CHE Date</label>
																			<input  type="date" id="commercialDGchequedate" value="${commercialDGchequedate}" class="form-control" name="commercialDGchequedate" />
																						</div>
																					</div>
																					<div class="col-lg-3">
																						<div class="form-group">
																							<label path="comments">Bank,Place</label>
																			<input  type="text" id="commercialDGBank" value="${commercialDGBank}" class="form-control" name="commercialDGBank" />
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">Balance</label>
																			<input  type="text" id="commercialDGBalance" value="${commercialDGBalance}" class="form-control" name="commercialDGBalance" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					
																									
																				
																		</div>
																		
																		
																		<div class="row">
																					<div class="col-lg-12">
																						<div class="form-group">
																							<label path="comments">Forms</label>
																			<input  type="text" id="commercialDGForms" value="${commercialDGForms}" class="form-control" name="commercialDGForms" />
																						</div>
																					</div>
																		</div>
																		Payment Terms (DG Set)
																		<div class="row">
																					<div class="col-lg-4">
																						<div class="form-group">
																							
																			<input  type="text" id="commercialDGPaymentTerms1" value="${commercialDGPaymentTerms1}" class="form-control" name="commercialDGPaymentTerms1" />
																						</div>
																					</div>
																					<div class="col-lg-4">
																						<div class="form-group">
																							<input  type="text" id="commercialDGPaymentTerms2" value="${commercialDGPaymentTerms2}" class="form-control" name="commercialDGPaymentTerms2" />
																						</div>
																					</div>
																					<div class="col-lg-4">
																						<div class="form-group">
																							<input  type="text" id="commercialDGPaymentTerms3" value="${commercialDGPaymentTerms3}" class="form-control" name="commercialDGPaymentTerms3" />
																						</div>
																					</div>
																					
																					
																									
																				
																		</div>
																		
																		<div class="row">
																					
																					<div class="col-lg-4">
																						<div class="form-group" >
																							<label path="comments">Performance Gaurantee</label>
																			<input  type="text" id="commercialDGPG" value="${commercialDGPG}" class="form-control" name="commercialDGPG"  />
																						</div>
																					</div>
																					<div class="col-lg-4">
																						<div class="form-group">
																							<label path="comments">Liquidated Damages</label>
																			<input  type="text" id="commercialDGLiqDamage" value="${commercialDGLiqDamage}" class="form-control" name="commercialDGLiqDamage"  />
																						</div>
																					</div>
																					<div class="col-lg-4">
																						<div class="form-group">
																							<br />
																							<input type="button" value="Submit" id="PaymenttermsDGAddButton"  class="btn btn-success" onclick="storePaymentTerms('DG','Add')" />
																							<input type="button" value="Submit" id="PaymenttermsDGUpdateButton"  class="btn btn-success" onclick="storePaymentTerms('DG','Update')" style="display:none" />
																						</div>
																					</div>	
																					
																					
																					
																					
																									
																				
																		</div>
																		
																		</div>
																	</div>
																		
																		</div>
																	</div>
																		
																		<div class="tab-pane fade" id="installation-pills" style="margin-top:20px">
																		<div class="panel panel-primary">
																		<a data-toggle="collapse" href="#collapseCommercialinstallation" style="text-decoration:none"  >
																			<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
																				Installation Pricing Details
																			</div>
																		</a>
															<div id="collapseCommercialinstallation" class="panel-collapse collapse in" aria-expanded="false" >
															
																		<div class="row">
																					
																			<div class="col-lg-12">
																					<label style="margin:15px">A. DG Set Accessories</label>	
																					<hr style="margin:0px !important;">
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">Pipes(Exhaust & Fuel) </label>
																			<input  type="text" id="commercialpipes" value="0" class="form-control" name="commercialpipes" onkeyup="TotalAmount()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">Vat @ 5% </label>
																			<input  type="text" id="commercialpipesVAT" value="0" class="form-control" name="commercialpipesVAT" onkeyup="TotalAmount()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">Cables</label>
																			<input  type="text" id="commercialCables" value="0" class="form-control" name="commercialCables" onkeyup="TotalAmount()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">Vat @ 5.5%</label>
																			<input  type="text" id="commercialCablesVAT" value="0" class="form-control" name="commercialCablesVAT" onkeyup="TotalAmount()"  pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-4">
																						<div class="form-group">
																							<label path="comments">Sub Total(DG set Accessories)</label>
																			<input  type="text" id="commercialInstTotalDGAccess" value="0" class="form-control" name="commercialInstTotalDGAccess" readonly  />
																						</div>
																					</div>
																									
																		</div>		
																		</div>
																		<script>
																		function addDGAccessoriesTotal()
																		{
																			commercialCablesVAT=document.getElementById("commercialCablesVAT").value;
																			commercialCables=document.getElementById("commercialCables").value;
																			commercialpipesVAT=document.getElementById("commercialpipesVAT").value;
																			commercialpipes=document.getElementById("commercialpipes").value;
																			
																			sumDGTotal=parseFloat(commercialCablesVAT)+parseFloat(commercialCables)+parseFloat(commercialpipesVAT)+parseFloat(commercialpipes)
																			document.getElementById("commercialInstTotalDGAccess").value=sumDGTotal;
																			return sumDGTotal;
																		}
																		</script>	
																		
																		<div class="row">
																		<div class="col-lg-12">
																		<label style="margin:15px">B. Low side</label>
																		<hr style="margin:0px !important;">
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">Materials Supply </label>
																			<input  type="text" id="lowSidemateriaSupply" value="0" class="form-control" name="lowSidemateriaSupply" onkeyup="TotalAmount()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">VAT @ 14.5% </label>
																		<input  type="text" id="lowSidemateriaSupplyVAT" value="0" class="form-control" name="lowSidemateriaSupplyVAT" onkeyup="TotalAmount()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">Labour</label>
																			<input  type="text" id="labourinstallation" value="0" class="form-control" name="labourinstallation" onkeyup="TotalAmount()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">VAT % 14.5%</label>
																			<input  type="text" id="installationlabourvat" value="0" class="form-control" name="installationlabourvat" onkeyup="TotalAmount()" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-4">
																						<div class="form-group" >
																							<label path="comments">Sub Total ( c ) ( Low side works) </label>
																			<input  type="text" id="sublowsideworks" value="0" class="form-control" name="sublowsideworks" onkeyup="TotalAmount()" readonly  />
																						</div>
																					</div>
																		</div>		
																		</div>
																		
																		<script>
																		function addLowSideTotal()
																		{
																			lowSidemateriaSupply=document.getElementById("lowSidemateriaSupply").value;
																			lowSidemateriaSupplyVAT=document.getElementById("lowSidemateriaSupplyVAT").value;
																			labourinstallation=document.getElementById("labourinstallation").value;
																			installationlabourvat=document.getElementById("installationlabourvat").value;
																			
																			sumLowSideTotal=parseFloat(lowSidemateriaSupply)+parseFloat(lowSidemateriaSupplyVAT)+parseFloat(labourinstallation)+parseFloat(installationlabourvat)
																			document.getElementById("sublowsideworks").value=sumLowSideTotal;
																			return sumLowSideTotal;
																		}
																		</script>	
																		
																		<div class="row">
																					<div class="col-lg-12">
																					
																					<div class="col-lg-4" style="float:right">
																						<div class="form-group">
																							<label path="comments">Total Project value ( A + B + C )</label>
																			<input  type="text" id="totalprojectvalue" value="0" class="form-control" name="totalprojectvalue" readonly />
																						</div>
																					</div>
																					
																					
																					<div class="col-lg-8" style="float:right">
																						<div class="form-group">
																							<br />
																							<input type="button" value="Submit" id="priceInstAddButton"  class="btn btn-success" onclick="storePriceInfo('INST','Add')" />
																							<input type="button" value="Submit" id="priceInstUpdateButton"  class="btn btn-success" onclick="storePriceInfo('INST','Update')"  style="display:none" />
																						</div>
																					</div>	
																					
																					
																					
																					</div>
																					
																					
																									
																				
																		</div>
																		</div>
																	</div>
																		
																		
																		
																	<div class="panel panel-primary">
																		<a data-toggle="collapse" href="#collapseCommercialINSTPayments" style="text-decoration:none"  >
																			<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
																				Installation Payment & Other Details
																			</div>
																		</a>
															<div id="collapseCommercialINSTPayments" class="panel-collapse collapse" aria-expanded="false" >
																<div class="panel-body">
																		<div class="row">
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">Advance</label>
																			<input  type="text" id="commercialINSTAdvance" value="${commercialINSTAdvance}" class="form-control" name="commercialINSTAdvance" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">CHE/DD</label>
																			<input  type="text" id="commercialINSTcheque" value="${commercialINSTcheque}" class="form-control" name="commercialINSTcheque" />
																						</div>
																					</div>
																					<div class="col-lg-2">
																						<div class="form-group">
																							<label path="comments">CHE Date</label>
																			<input  type="date" id="commercialINSTchequedate" value="${commercialINSTchequedate}" class="form-control" name="commercialINSTchequedate" />
																						</div>
																					</div>
																					<div class="col-lg-3">
																						<div class="form-group">
																							<label path="comments">Bank,Place</label>
																			<input  type="text" id="commercialINSTBank" value="${commercialINSTBank}" class="form-control" name="commercialINSTBank" />
																						</div>
																					</div>
																					<div class="col-lg-3">
																						<div class="form-group">
																							<label path="comments">Balance</label>
																			<input  type="text" id="commercialINSTBalance" value="${commercialINSTBalance}" class="form-control" name="commercialINSTBalance" pattern="^-?\d+\.?\d*$"oninvalid="setCustomValidity('Only Numeric Allowed ')"    onchange="try{setCustomValidity('')}catch(e){}"   />
																			<div class="help-block with-errors"></div>	
																						</div>
																					</div>
																					
																									
																				
																		</div>
																		
																		
																		<div class="row">
																					<div class="col-lg-12">
																						<div class="form-group">
																							<label path="comments">Forms</label>
																			<input  type="text" id="commercialINSTForms" value="${commercialINSTForms}" class="form-control" name="commercialINSTForms" />
																						</div>
																					</div>
																		</div>
																		Payment Terms (DG Set)
																		<div class="row">
																					<div class="col-lg-4">
																						<div class="form-group">
																							
																			<input  type="text" id="commercialINSTPaymentTerms1" value="${commercialINSTPaymentTerms1}" class="form-control" name="commercialINSTPaymentTerms1" />
																						</div>
																					</div>
																					<div class="col-lg-4">
																						<div class="form-group">
																							<input  type="text" id="commercialINSTPaymentTerms2" value="${commercialINSTPaymentTerms2}" class="form-control" name="commercialINSTPaymentTerms2" />
																						</div>
																					</div>
																					<div class="col-lg-4">
																						<div class="form-group">
																							<input  type="text" id="commercialINSTPaymentTerms3" value="${commercialINSTPaymentTerms3}" class="form-control" name="commercialINSTPaymentTerms3" />
																						</div>
																					</div>
																					
																					
																									
																				
																		</div>
																		
																		<div class="row">
																					
																					<div class="col-lg-4">
																						<div class="form-group" >
																							<label path="comments">Performance Gaurantee</label>
																			<input  type="text" id="commercialINSTPG" value="${commercialINSTPG}" class="form-control" name="commercialINSTPG"  />
																						</div>
																					</div>
																					<div class="col-lg-4">
																						<div class="form-group">
																							<label path="comments">Liquidated Damages</label>
																			<input  type="text" id="commercialINSTLiqDamage" value="${commercialINSTLiqDamage}" class="form-control" name="commercialINSTLiqDamage"  />
																						</div>
																					</div>
																					<div class="col-lg-4">
																						<div class="form-group">
																							<br />
																							<input type="button" value="Submit" id="PaymenttermsINSTAddButton"  class="btn btn-success" onclick="storePaymentTerms('INST','Add')" />
																							<input type="button" value="Submit" id="PaymenttermsINSTUpdateButton"  class="btn btn-success" onclick="storePaymentTerms('INST','Update')" style="display:none"  />
																						</div>
																					</div>	
																					
																					
																					
																					
																									
																				
																		</div>
																		
																		</div>
																	</div>
																		
																		</div>	
																			
																	
																	
																		</div>
																		
																		
																	</div>
																
														
														
														
														
														
													</div>
												</div>
											</div>
										</div>
									</div>
									
									
							</form>	
                            </div>							
							
							
							
							<div class="tab-pane fade" id="validationchecks" style="display:none">
                                    
									
									<div class="row">
										<div class="col-lg-12" style="margin-top:10px">
											<div class="panel panel-primary">
		                                    	<a data-toggle="collapse" href="#collapsevalidation" style="text-decoration:none"  >
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Lead Creation Summary  
													</div>
												</a>
		                                    	<div id="collapsevalidation" class="panel-collapse collapse in" aria-expanded="false" >
			                                        <div class="panel-body">
														
															<div class="row">
																<div class="col-lg-12">
																	<div class="form-group">
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> Lead Header Infrmation Added Successfully
																	  </div>
																	</div>
																	
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> Customer Information Added Successfully 
																	  </div>
																	</div>
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> Consultant Details Added Successfully
																	  </div>
																	</div>
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> PMC Added Successfully
																	  </div>
																	</div>
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> Item Details Added Successfully
																	  </div>
																	</div>
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> Comments has Added
																	  </div>
																	</div>
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> Cost Summary Added Successfully
																	  </div>
																	</div>
																		
																		
																		
																		
																		
																	</div>
																</div>
																
																<div class="col-lg-12">
																	<div class="form-group">
																		
																	<div class="well well-transparent">
																	  <div style="font-size: 24px; line-height: 1.5em;">
																		<i class="fa fa-thumbs-o-up"></i> Huraay !!! Lead Created Successfully
																	  </div>
																	</div>
																		
																	</div>
																</div>
																
																<div class="col-lg-4">
																	<div class="well well-transparent">
																	  <div style="font-size: 24px; line-height: 1.5em;">
																		<a href="UserController?action=listUser">Go To View Lead Page</a>
																	  </div>
																	</div>
																
																</div>
																
																<div class="col-lg-4">
																
																<div class="well well-transparent">
																	  <div style="font-size: 24px; line-height: 1.5em;">
																		<a href="index.jsp">Go To Dashboard</a>
																	  </div>
																	</div>
																
																</div>
																<div class="col-lg-4">
																<div class="well well-transparent">
																	  <div style="font-size: 24px; line-height: 1.5em;">
																		<a href="MainController?action=creatlead&businessFile=DG">Create New Lead</a>
																	  </div>
																	</div>
																
																</div>
																
																		
																		
															
																
																
															</div>
															
															
														
													</div>
												</div>
											</div>
										</div>
									</div>
									
									
								
                            </div>
							
                         </div>
                     </div>
                	<!-- /.panel-body -->
                </div>
            	<!-- /.panel -->
        	</div>
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
	

														<c:choose>
															<c:when test="${doAction=='view'}">
																<script>
																document.getElementById("headerButton").style.display='none';
																document.getElementById("customerButton").style.display='none';
																document.getElementById("addItemDetails").style.display='none';
																//document.getElementById("addComments").style.display='none';
																document.getElementById("addCostSummary").style.display='none';
																</script>
															</c:when>    
															<c:otherwise>
																<script>
																document.getElementById("headerButton").style.display='block';
																document.getElementById("customerButton").style.display='block';
																//document.getElementById("addItemDetails").style.display='block';
																//document.getElementById("addComments").style.display='block';
																//document.getElementById("addCostSummary").style.display='block';
																
																</script>
															</c:otherwise>
														</c:choose>
														
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

					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel" style="display:inline"></h4>
											<h4 class="modal-title" id="myModalTotQty" style="float:right"></h4>
                                        </div>
                                        <div class="modal-body">
										
										<div class="panel panel-primary">
                                           <a data-toggle="collapse" href="#collapseEngineSchedule" style="text-decoration:none" aria-expanded="true" class="">
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Engine Details
													</div>
												</a>
												
                                          <div id="collapseEngineSchedule" class="panel-collapse collapse" aria-expanded="true">
										<div class="row" style="margin:15px">
											<div class="col-lg-3">
											
												<div class="form-group">
													<label path="leadID">Lead ID</label>
													<input type="text" name="modalleadID" value="" class="form-control" readonly="true" id="modalleadID"/>
													<input type="hidden" name="itemid" value="" class="form-control" readonly="true" id="itemid"/>
													
													
												</div>
											</div>
											<div class="col-lg-3">
											
												<div class="form-group">
													<label path="leadID">Engine</label>
													<input type="text" name="modalEngine" value="" class="form-control" readonly="true" id="modalEngine"/>
													
													
												</div>
											</div>
											
											<div class="col-lg-3">
											
												<div class="form-group">
													<label path="leadID">Total Qty</label>
													<input type="text" name="modalEngineQty" value="" class="form-control" readonly="true" id="modalEngineQty"/>
													
													
												</div>
											</div>
											<div class="col-lg-3">
											
												<div class="form-group">
													<label path="leadID">Total Value</label>
													<input type="text" name="modalEngineValue" value="" class="form-control" readonly="true" id="modalEngineValue"/>
													
													
												</div>
											</div>
											
										</div>
										
										</div>
										</div>
										
										
										<div class="panel panel-primary" id="addScheduleItem">
                                           <a data-toggle="collapse" href="#collapseAddSchedule" style="text-decoration:none" aria-expanded="true" class="">
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;" id="AddSchduleText">
														Add Schedule
													</div>
												</a>
												
											<div id="collapseAddSchedule" class="panel-collapse collapse in" aria-expanded="true">
											<div class="row" style="margin:15px">
											
												<div class="col-lg-6">
												
													<div class="form-group">
														<label path="scDate">Choose Schedule Date</label>
														<input type="date" name="scheduleDate" value="" class="form-control"  id="scheduleDate"/>
														
														
													</div>
												</div>
											
												<div class="col-lg-6">
													
														<div class="form-group">
															<label path="scDate">Qty</label>
															<input type="number" name="scheduleQty" value="" class="form-control" id="scheduleQty" onblur="return checkScheduleQty(this.value)"/>
															<input type="hidden" name="usedQty"  class="form-control" id="usedQty"/>
															<input type="hidden" name="schdid"  class="form-control" id="schdid"/>
															
															
														</div>
												</div>
											</div>
											
											<div class="row"  style="margin:15px">
												<div class="col-lg-6">
													
														<div class="form-group">
															<label path="scDate">Remarks</label>
															<input type="text" name="scheduleRemarks" value="" class="form-control" id="scheduleRemarks"/>
															
															
														</div>
												</div>
												<div class="col-lg-6">
													
														
															<button type="button" class="btn btn-primary" name="scheduleSubmit" id="scheduleAddSubmit" onclick="return storeScheduleDetails('Add')">Submit</button>
															
															<button type="button" class="btn btn-primary" name="scheduleSubmit" id="scheduleUpdatedSubmit" onclick="return storeScheduleDetails('Updated')" style="display:none">Update</button>
															
															
															
														
												</div>
													
											</div>
											
											</div>
										</div>
										
										
                                           <div class="panel panel-primary">
                                           <a data-toggle="collapse" href="#collapseScDetails" style="text-decoration:none" aria-expanded="true" class="">
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Schedule  Details
													</div>
												</a>
												
                                          <div id="collapseScDetails" class="panel-collapse collapse" aria-expanded="true">
										  <div class="panel-body">
                                           <div class="dataTable_wrapper">
																								<table class="table table-striped table-bordered table-hover">
																									<thead>
																										<tr>
																											
																											<th>Sno</th>
																											<th>Schedule Date</th>
																											<th>Qty</th>
																											
																											<th>Created By</th>
																											<th>Remarks</th>
																											<th>Action</th>
																											
																											
																											
																										</tr>
																									</thead>
																								   <tbody id="tableSchedulesBody">
																									   
																									</tbody>
																								</table>
											</div>
											
										</div>
										</div>
											
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                           
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
							
                            <!-- /.modal -->
						</div>
						
						<div class="modal fade" id="myTechnicalModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header" style="display:none">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel" style="display:inline"></h4>
											<h4 class="modal-title" id="myModalTotQty" style="float:right"></h4>
                                        </div>
                                        <div class="modal-body" style="position: none !important;padding: 0px !important;">

										<div class="panel panel-primary" id="addTechnicalInformation">
                                           <a data-toggle="collapse" href="#collapseaddTechnicalInformation" style="text-decoration:none" aria-expanded="true" class="">
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;" id="AddSchduleText">
														Add Technical Details
													</div>
												</a>
												
											<div id="collapseaddTechnicalInformation" class="panel-collapse collapse in" aria-expanded="true">
											<input type="hidden" name="modaltechleadID" value="" class="form-control"  id="modaltechleadID"/>
											<input type="hidden" name="itemtechid" value="" class="form-control"  id="itemtechid"/>
													
											<div class="row" style="margin:5px">
											
												<div class="col-lg-4">
												
													<div class="form-group">
														<label path="scDate">Fuel Tank</label>
														<input type="text" name="fuleTank" value="990 ltrs" class="form-control"  id="fuleTank"/>
														
														
													</div>
												</div>
												<div class="col-lg-4">
												
													<div class="form-group">
														<label path="scDate">Engine Optionals</label>
														<input type="text" name="engineOptionals" value="" class="form-control"  id="engineOptionals"/>
														
														
													</div>
												</div>
												
												<div class="col-lg-4">
												
													<div class="form-group">
														<label path="scDate">Alternator Optional </label>
														<input type="text" name="alternatorOptionals" value="QDCT" class="form-control"  id="alternatorOptionals"/>
														
														
													</div>
												</div>
												
												
											
												
											</div>
											<div class="row" style="margin:5px">
											
											<div class="col-lg-6">
												
													<div class="form-group">
														<label path="scDate">Panel Optional </label>
														<input type="text" name="panelOptionals" value="" class="form-control"  id="panelOptionals"/>
														
														
													</div>
												</div>
												
												
												
												<div class="col-lg-6">
												
													<div class="form-group">
														<label path="scDate">Solo/Parallel Optionals</label>
														<select name="soloparallel" class="form-control" id="soloparallel">
															<option selected>Parallel</option>
															<option>Solo</option>
															
														</select>
														
														
													</div>
												</div>
												
											
												
											</div>
											<div class="row" style="margin:5px">
											
											<div class="col-lg-6">
												
													<div class="form-group">
														<label path="scDate">Type of Panel</label>
														<input type="text" name="panelType" value="Standard Isoltaor panel - MDO 3200 Amps with KWH" class="form-control"  id="panelType"/>
														
														
													</div>
												</div>
												<div class="col-lg-6">
												
													<div class="form-group">
														<label path="scDate">Testing Procedure</label>
														<input type="text" name="testingProcedure" value="Standard Factory test procedure" class="form-control"  id="testingProcedure"/>
														
														
													</div>
												</div>
											
											
												
											</div>
											
											<div class="row" style="margin:5px">
											<div class="col-lg-6">
												
													<div class="form-group">
														<label path="scDate">DG Testing</label>
														<input type="text" name="dgTesting" value="Standard Testing @ works wittnessed by PMC / Consultants" class="form-control"  id="dgTesting"/>
														
														
													</div>
												</div>
												
												
												
												<div class="col-lg-6">
												
													<div class="form-group">
														Testing Charges Included (Y/N) ?
													<input type="checkbox" name="testingCharges" value="" checked   id="testingCharges"/>
														
														
													</div>
												</div>
											</div>
											
											
											
											</div>
										</div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											<button type="button" class="btn btn-primary" name="scheduleSubmit" id="technicalAddSubmit" onclick="return storeTechnicalDetails('Add')">Submit</button>
															
															<button type="button" class="btn btn-primary" name="scheduleSubmit" id="technicalUpdateSubmit" onclick="return storeTechnicalDetails('Update')" style="display:none">Update</button>
                                           
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
							
                            <!-- /.modal -->
						</div>
							
							
							<!--
							data-id="${leadItems.DTL_ID}" data-title-id="Add Schedule" data-lead-id="${leadItems.LEAD_ID}" data-engine="${leadItems.DTL_ENGINE_MODEL}" data-qty="${leadItems.DTL_QTY}" data-alternator="${leadItems.DTL_ALTERNATOR_MODEL}" data-amount="${leadItems.DTL_AMOUNT}"  class="fa fa-plus fa-2x" data-toggle="modal" data-target="#myModal"
							-->
							<script>
							
							
							$('#myTechnicalModal').on('show.bs.modal', function(e) {
									var itemid = $(e.relatedTarget).data('id');
									//$(e.currentTarget).find('input[name="bookId"]').val(bookId);
									
									var title = $(e.relatedTarget).data('title-id');
									var leadID = $(e.relatedTarget).data('lead-id');
									//var engine = $(e.relatedTarget).data('engine');
									//var qty = $(e.relatedTarget).data('qty');
									//var amount = $(e.relatedTarget).data('amount');
									
									$(e.currentTarget).find('input[id="itemtechid"]').val(itemid);
									$(e.currentTarget).find('input[id="modaltechleadID"]').val(leadID);
									//$(e.currentTarget).find('input[id="modalEngine"]').val(engine);
									//$(e.currentTarget).find('input[id="modalEngineQty"]').val(qty);
									//$(e.currentTarget).find('input[id="modalEngineValue"]').val(amount);
									
								//	var engine = $(e.relatedTarget).data('engine');
									$(e.currentTarget).find('#myModalLabel')[0].innerHTML=title;
									//$(e.currentTarget).find('#myModalTotQty')[0].innerHTML="Total Qty-"+qty;
									
									
									/*if(title=="View Schedule")
									{
										document.getElementById("addScheduleItem").style.display="none";
										
										document.getElementById("collapseScDetails").className = "tab-pane fade collapse in";
									}
									else{
										document.getElementById("addScheduleItem").style.display="block";
										document.getElementById("collapseScDetails").className = "tab-pane fade collapse";
									}*/
									
									//getSchedules(leadID,itemid,'all');
									//getSchedulesQty(leadID,itemid);
									
									//alert("Hello"+book);
								});
								
							$('#myModal').on('show.bs.modal', function(e) {
									var itemid = $(e.relatedTarget).data('id');
									//$(e.currentTarget).find('input[name="bookId"]').val(bookId);
									
									var title = $(e.relatedTarget).data('title-id');
									var leadID = $(e.relatedTarget).data('lead-id');
									var engine = $(e.relatedTarget).data('engine');
									var qty = $(e.relatedTarget).data('qty');
									var amount = $(e.relatedTarget).data('amount');
									
									
									/*
											modalleadID
											modalEngine
											modalEngineQty
											modalEngineValue
									*/
									$(e.currentTarget).find('input[id="itemid"]').val(itemid);
									$(e.currentTarget).find('input[id="modalleadID"]').val(leadID);
									$(e.currentTarget).find('input[id="modalEngine"]').val(engine);
									$(e.currentTarget).find('input[id="modalEngineQty"]').val(qty);
									$(e.currentTarget).find('input[id="modalEngineValue"]').val(amount);
									
								//	var engine = $(e.relatedTarget).data('engine');
									$(e.currentTarget).find('#myModalLabel')[0].innerHTML=title;
									$(e.currentTarget).find('#myModalTotQty')[0].innerHTML="Total Qty-"+qty;
									
									
									if(title=="View Schedule")
									{
										document.getElementById("addScheduleItem").style.display="none";
										
										document.getElementById("collapseScDetails").className = "tab-pane fade collapse in";
									}
									else{
										document.getElementById("addScheduleItem").style.display="block";
										document.getElementById("collapseScDetails").className = "tab-pane fade collapse";
									}
									
									getSchedules(leadID,itemid,'all');
									getSchedulesQty(leadID,itemid);
									
									//alert("Hello"+book);
								});
								
							</script>
							<style>
							.glyphicon-refresh-animate {
									-animation: spin .7s infinite linear;
									-animation: spin .7s infinite linear;
									-webkit-animation: spin2 .7s infinite linear;
								}

								@-webkit-keyframes spin2 {
									from { -webkit-transform: rotate(0deg);}
									to { -webkit-transform: rotate(360deg);}
								}

								@keyframes spin {
									from { transform: scale(1) rotate(0deg);}
									to { transform: scale(1) rotate(360deg);}
								}
							</style>
							<script>
																		function TotalAmount()
																		{
																			toalSum=addLowSideTotal()+addDGAccessoriesTotal()+addDGTotal();
																			document.getElementById("totalprojectvalue").value=toalSum;
																			
																		}
																		</script>
															<script>
															TotalAmount();
															</script>
					<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
       
        
    });
    </script>		
</body></html>