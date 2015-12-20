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

function getCustomer(str)
{
	if(str=='Existing')
	{
		document.getElementById("customerName").style.display='block';
		document.getElementById("mcustomerName").style.display='none';
	}
	else{
		document.getElementById("customerName").style.display='none';
		document.getElementById("mcustomerName").style.display='block';
	}
}
function getConsultantDetails(str)
{
	
	if(str=='Existing')
	{
		document.getElementById("customerConsultant").style.display='block';
		document.getElementById("mcustomerConsultant").style.display='none';
		
		document.getElementById("customerConsultant").readOnly=false;
		document.getElementById("mcustomerConsultant").readOnly=false;
		document.getElementById("customerConsultantPersonName").readOnly=false;
		document.getElementById("customerConsultantNumber").readOnly=false;

	}
	else if(str=='None'){
		
		document.getElementById("mcustomerConsultant").readOnly=true;
		document.getElementById("customerConsultant").readOnly=true;
		document.getElementById("customerConsultantPersonName").readOnly=true;
		document.getElementById("customerConsultantNumber").readOnly=true;
		
		
	}
	else{
		document.getElementById("customerConsultant").style.display='none';
		document.getElementById("mcustomerConsultant").style.display='block';
		
		document.getElementById("customerConsultant").readOnly=false;
		document.getElementById("mcustomerConsultant").readOnly=false;
		document.getElementById("customerConsultantPersonName").readOnly=false;
		document.getElementById("customerConsultantNumber").readOnly=false;
		}
}
function getPMCDetails(str)
{
	
	if(str=='Existing')
	{
		document.getElementById("pmc").style.display='block';
		document.getElementById("mpmc").style.display='none';
		
		document.getElementById("pmc").readOnly=false;
		document.getElementById("mpmc").readOnly=false;
		document.getElementById("pmcPerson").readOnly=false;
		document.getElementById("pmcContact").readOnly=false;
		document.getElementById("pmcAlternateContact").readOnly=false;
		document.getElementById("pmcEmail").readOnly=false;
	}
	else if(str=='None')
	{
		document.getElementById("pmc").readOnly=true;
		document.getElementById("mpmc").readOnly=true;
		document.getElementById("pmcPerson").readOnly=true;
		document.getElementById("pmcContact").readOnly=true;
		document.getElementById("pmcAlternateContact").readOnly=true;
		document.getElementById("pmcEmail").readOnly=true;
	}
	else{
		document.getElementById("pmc").style.display='none';
		document.getElementById("mpmc").style.display='block';
		
		document.getElementById("pmc").readOnly=false;
		document.getElementById("mpmc").readOnly=false;
		document.getElementById("pmcPerson").readOnly=false;
		document.getElementById("pmcContact").readOnly=false;
		document.getElementById("pmcAlternateContact").readOnly=false;
		document.getElementById("pmcEmail").readOnly=false;
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
	if(leadID=="" || leadID=="undefined" || leadID==undefined)
	{
		alert("Please choose  state and fill all Details in Main Tab first");
		return false;
	}
	actionValue=document.getElementById("actionValue").value;
	createdBy=document.getElementById("createdBy").value;
	
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
		
		customerConsultantType=document.getElementById("customerConsultantType").value;
		pmcType=document.getElementById("pmcType").value;
		var customerConsultant;
		
		if(customerConsultantType=='0')
		{
			alert("Please choose Consultant");
			document.getElementById("customerConsultantType").focus();
			return false
			
		}
		if(customerConsultantType=='Existing')
		{
			customerConsultant=document.getElementById("customerConsultant").value;
			
			if(customerConsultant==0)
				{
					alert("Please choose Consultant");
					document.getElementById("customerConsultant").focus();
					return false
				}
		}
		else{
			customerConsultant=document.getElementById("mcustomerConsultant").value;
		}
		
		
		
		var customerConsultantNumber=document.getElementById("customerConsultantNumber").value;
		var customerConsultantPersonName=document.getElementById("customerConsultantPersonName").value;
		
			
		var customerTempAddress=document.getElementById("customerTempAddress").value;
		var customerBillingAddress1=document.getElementById("customerBillingAddress1").value;
		
		var customerBillingAddress2=document.getElementById("customerBillingAddress2").value;
		var customerBillingAddress3=document.getElementById("customerBillingAddress3").value;
		/*if(customerBillingAddress2==0)
		{
					alert("Please Choose State");
					document.getElementById("customerBillingAddress2").focus();
					return false;
		}
		
		if(customerBillingAddress3==0)
		{
					alert("Please Choose City");
					document.getElementById("customerBillingAddress3").focus();
					return false;
		}*/
		
		
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
			
				/*if(customerDeliveryAddress2==0)
				{
							alert("Please Choose Delivery State");
							document.getElementById("customerDeliveryAddress2").focus();
							return false;
				}
				
				if(customerDeliveryAddress3==0)
				{
							alert("Please Choose Delivery City");
							document.getElementById("customerDeliveryAddress3").focus();
							return false;
				}*/

		}
		
		var customerECCNO=document.getElementById("customerECCNO").value;
		var customerCSTNO=document.getElementById("customerCSTNO").value;
		var customerTINNO=document.getElementById("customerTINNO").value;
		var customerIECNO=document.getElementById("customerIECNO").value;
		var pmc;
		
		if(pmcType=='0')
		{
			alert("Please choose PMC");
			document.getElementById("pmcType").focus();
			return false
			
		}
		if(pmcType=='Existing')
		{
			pmc=document.getElementById("pmc").value;
			if(pmc==0)
				{
					alert("Please choose PMC");
					document.getElementById("pmc").focus();
					return false
				}
			
		}
		else{
			pmc=document.getElementById("mpmc").value;
		}
		
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
parameters=parameters+"&customergroup="+customergroup+"&customergroup_person="+customergroup_person+"&customergroup_person_contact="+customergroup_person_contact+"&createdBy="+createdBy+"&actionValue="+actionValue+"&customerTempAddress="+customerTempAddress+"&customerConsultantType="+customerConsultantType+"&pmcType="+pmcType;
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
		alert("please correct error value");
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
	if(leadID=="" || leadID=="undefined" || leadID==undefined)
	{
		alert("Please choose Choose State and fill all Details in Main Tab first");
		return false;
	}
	
	actionValue=document.getElementById("actionValue").value;
	
	projectType=document.getElementById("projectType").value;
	projectExeState=document.getElementById("projectExeState").value;
	projectExePlace=document.getElementById("projectExePlace").value;
	dgType=document.getElementById("dgType").value;
	segment=document.getElementById("segment").value;
	transactionType=document.getElementById("transactionType").value;
	enquiryType=document.getElementById("enquiryType").value;
	LeadExeDate=document.getElementById("LeadExeDate").value;
	DGDeliveryDate=document.getElementById("DGDeliveryDate").value;
	ProjectClosureDate=document.getElementById("ProjectClosureDate").value;
	
	
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
		alert("Please choose  Type");
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

	
	leadStatus=document.getElementById("leadStatus").value;
	leadChances=document.getElementById("leadChances").value;


	competitions=document.getElementById("competitions").value;
	
	projectReference=document.getElementById("projectReference").value;
	ownerID=document.getElementById("ownerID").value;
	ownerName=document.getElementById("ownerName").value;
	createdBy=document.getElementById("createdBy").value;
	
	var parameters = "leadID="+leadID+"&projectType="+projectType+"&projectExeState="+projectExeState+"&projectExePlace="+projectExePlace+"&dgType="+dgType+"&projectReference="+projectReference+"&segment="+segment+"&transactionType="+transactionType+"&enquiryType="+enquiryType+"&leadStatus="+leadStatus+"&leadChances="+leadChances+"&competitions="+competitions+"&ownerID="+ownerID+"&ownerName="+ownerName+"&createdBy="+createdBy+"&actionValue="+actionValue+"&LeadExeDate="+LeadExeDate+"&DGDeliveryDate="+DGDeliveryDate+"&ProjectClosureDate="+ProjectClosureDate;
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	        
	        if(http.responseText=="Done")
	        	{
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
		alert("please correct error value");
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
	a=confirm("Do you want to delete the item ?");
	if(a)
	{
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
				output += '<td style="display:none">';
				output += jsdata[i].DTL_RATING;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_ENGINE_MODEL;
				output += '</td>';
				output += '<td style="display:none">';
				output += jsdata[i].DTL_ALTERNATOR_MODEL;
				output += '</td>';
				output += '<td style="display:none">';
				output += jsdata[i].DTL_VOLTAGE;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_QTY;
				output += '</td>';
				output += '<td>';
				output += jsdata[i].DTL_AMOUNT;
				output += '</td>';
				output += '<td>';
				output += "<a href=javascript:setLeadItem('"+jsdata[i].DTL_ID+"','"+jsdata[i].LEAD_ID+"','"+jsdata[i].DTL_RATING+"','"+jsdata[i].DTL_ENGINE_MODEL+"','"+jsdata[i].DTL_ALTERNATOR_MODEL+"','"+jsdata[i].DTL_VOLTAGE+"','"+jsdata[i].DTL_QTY+"','"+jsdata[i].DTL_AMOUNT+"','"+jsdata[i].DTL_ENGINE_MAKE+"','"+jsdata[i].DTL_COOLING_SYSTEM+"','"+encodeURIComponent(jsdata[i].DTL_FLEX1)+"','"+jsdata[i].DTL_HZ+"','"+jsdata[i].DTL_ALTERNATOR_MAKE+"','"+jsdata[i].DTL_ENGINE_MODEL+"','"+jsdata[i].DTL_FLEX2+"')><p class='fa fa-pencil fa-2x'>  </p></a>";

				
				output += "<a href=javascript:deleteLeadItem('"+jsdata[i].DTL_ID+"','"+jsdata[i].LEAD_ID+"')><p class='fa fa-times fa-2x'>  </p></a>";
				output += '</td>';
				output += '</tr>';
				
			 }
			 
			 tableBody.innerHTML=output;
			
	       
	    }
	}
	http.send(null);
	
	}
}
function storeItemDetails(itemAction)
{
	
	var http = new getXMLHttpRequestObject();
	var url = "LeadItemDetailsController";
	
	leadID=document.getElementById("leadID").value;
	if(leadID=="" || leadID=="undefined" || leadID==undefined)
	{
		alert("Please choose Choose State and fill all Details in Main Tab first");
		return false;
	}
	
	engineType="Engine";
	engineMakeType=document.getElementById("engineMakeType").value;
	
	if(engineMakeType==0)
	{
		alert("Please Choose Engine ");
		document.getElementById("engineMakeType").focus();
		return false;
	}
	
	alternator=document.getElementById("alternator").value;
	
	alternatorMakeType=document.getElementById("alternatorMakeType").value;
	DGModel=document.getElementById("DGModel").value;
	EngineModel=document.getElementById("EngineModel").value;
	
	itemQty=document.getElementById("itemQty").value;
	itemAmount=document.getElementById("itemAmount").value;
	itemHz=document.getElementById("itemHz").value;
	itemVoltage=document.getElementById("itemVoltage").value;
	coolingSystem=document.getElementById("coolingSystem").value;
	
	if(coolingSystem==0)
	{
		alert("Please Choose Cooling System");
		document.getElementById("coolingSystem").focus();
		return false;
	}
	if(itemHz==0)
	{
		alert("Please Choose Hz");
		document.getElementById("itemHz").focus();
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
	
	createdBy=document.getElementById("createdBy").value;
	
	if(itemAction=="Add")
	{

	parameters = "leadID="+leadID+"&engineType="+engineType+"&engineMakeType="+engineMakeType+"&itemQty="+itemQty+"&itemAmount="+itemAmount+"&itemHz="+itemHz+"&itemVoltage="+itemVoltage+"&createdBy="+createdBy+"&coolingSystem="+coolingSystem+"&alternator="+alternator+"&itemAction="+itemAction+"&DGModel="+DGModel+"&EngineModel="+EngineModel+"&alternatorMakeType="+alternatorMakeType;
	}
	else{
		dtlID=document.getElementById("dtlID").value;
		parameters = "leadID="+leadID+"&engineType="+engineType+"&engineMakeType="+engineMakeType+"&itemQty="+itemQty+"&itemAmount="+itemAmount+"&itemHz="+itemHz+"&itemVoltage="+itemVoltage+"&createdBy="+createdBy+"&coolingSystem="+coolingSystem+"&alternator="+alternator+"&itemAction="+itemAction+"&dtlID="+dtlID+"&DGModel="+DGModel+"&EngineModel="+EngineModel+"&alternatorMakeType="+alternatorMakeType;
		
	}
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	      //  alert(http.responseText);
			jsdata=JSON.parse(http.responseText);
			document.getElementById("itemDetailsAddButton").style.display='inline';
			document.getElementById("itemDetailsUpdateButton").style.display='none';		
			document.getElementById("itemDetailsNextButton").style.display="inline";
			document.getElementById("itemPanelHead").innerHTML='Add Item';
			 
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
				output += "<a href=javascript:setLeadItem('"+jsdata[i].DTL_ID+"','"+jsdata[i].LEAD_ID+"','"+jsdata[i].DTL_RATING+"','"+jsdata[i].DTL_ENGINE_MODEL+"','"+jsdata[i].DTL_ALTERNATOR_MODEL+"','"+jsdata[i].DTL_VOLTAGE+"','"+jsdata[i].DTL_QTY+"','"+jsdata[i].DTL_AMOUNT+"','"+jsdata[i].DTL_ENGINE_MAKE+"','"+jsdata[i].DTL_COOLING_SYSTEM+"','"+encodeURIComponent(jsdata[i].DTL_FLEX1)+"','"+jsdata[i].DTL_HZ+"','"+jsdata[i].DTL_ALTERNATOR_MAKE+"','"+jsdata[i].DTL_ENGINE_MODEL+"','"+jsdata[i].DTL_FLEX2+"')><p class='fa fa-pencil fa-2x'>  </p></a>";
				
				output += "<a href=javascript:deleteLeadItem('"+jsdata[i].DTL_ID+"','"+jsdata[i].LEAD_ID+"')><p class='fa fa-times fa-2x'>  </p></a>";
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
			document.getElementById("alternatorMakeType").value='0';
			document.getElementById("DGModel").value='';
			document.getElementById("EngineModel").value='';
			

	    }
	}
	http.send(null);
}

function storeComments()
{
	
	var http = new getXMLHttpRequestObject();
	var url = "CommentsController";
	
	leadID=document.getElementById("leadID").value;
	if(leadID=="" || leadID=="undefined" || leadID==undefined)
	{
		alert("Please choose Choose State and fill all Details in Main Tab first");
		return false;
	}
	
	
	comments=document.getElementById("comments").value;
	
	if(comments=="" || comments=="undefined" || comments==undefined)
	{
		alert("Please Enter Comments ");
		document.getElementById("comments").focus();
		return false;
	}
	
	
	
	createdBy=document.getElementById("createdBy").value;
	
	var parameters = "leadID="+leadID+"&comments="+comments+"&createdBy="+createdBy+"&types=LEAD_COMMENTS";
	
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
				output += '<td>';
				output += jsdata[i].DOC_TYPE;
				output += '</td>';
				output += '</tr>';
				
			 }
			 
			 tableAttachmentsBody.innerHTML=output;
			 
			//dataTables-example
			

	    }
	}
	http.send(null);
}

function callCustomer(str,type)
{

	if(str!=0)
	{
	var http = new getXMLHttpRequestObject();
	var url = "GetCustomerDetails";
	var parameters = "NAME="+str+"&type="+type;
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	      //  alert(http.responseText);
			a=JSON.parse(http.responseText);
			
			// When type is customer
			if(type=="customer")
			{
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
			if(type=="consultant")
			{
				var customerConsultantPersonName,customerConsultantNumber;
				
				if(a[0].CONSULATNT_CONTACT_PERSON=="undefined" || a[0].CONSULATNT_CONTACT_PERSON==undefined || a[0].CONSULATNT_CONTACT_PERSON=="" || a[0].CONSULATNT_CONTACT_PERSON==null)
				{
					customerConsultantPersonName="";
				}
				else{
					customerConsultantPersonName=a[0].CONSULATNT_CONTACT_PERSON;
				}
				if(a[0].CONSULATNT_CONTACT_MOBILE=="undefined" || a[0].CONSULATNT_CONTACT_MOBILE==undefined || a[0].CONSULATNT_CONTACT_MOBILE=="" || a[0].CONSULATNT_CONTACT_MOBILE==null)
				{
					customerConsultantNumber="";
				}
				else{
					customerConsultantNumber=a[0].CONSULATNT_CONTACT_MOBILE;
				}
				document.getElementById("customerConsultantPersonName").value=customerConsultantPersonName;
				document.getElementById("customerConsultantNumber").value=customerConsultantNumber;
				
				
			}
			if(type=="pmc")
			{
				var customerConsultantPersonName,customerConsultantNumber;
				var pmcPerson,pmcContact,pmcAlternateContact,pmcEmail;
				
				if(a[0].PMC_CONTACT_PERSON=="undefined" || a[0].PMC_CONTACT_PERSON==undefined || a[0].PMC_CONTACT_PERSON=="" || a[0].PMC_CONTACT_PERSON==null)
				{
					pmcPerson="";
				}
				else{
					pmcPerson=a[0].PMC_CONTACT_PERSON;
				}
				if(a[0].PMC_CONTACT_MOBILE=="undefined" || a[0].PMC_CONTACT_MOBILE==undefined || a[0].PMC_CONTACT_MOBILE=="" || a[0].PMC_CONTACT_MOBILE==null)
				{
					pmcContact="";
				}
				else{
					pmcContact=a[0].PMC_CONTACT_MOBILE;
				}
				if(a[0].PMC_ALTERNATE_MOBILE=="undefined" || a[0].PMC_ALTERNATE_MOBILE==undefined || a[0].PMC_ALTERNATE_MOBILE=="" || a[0].PMC_ALTERNATE_MOBILE==null)
				{
					pmcAlternateContact="";
				}
				else{
					pmcAlternateContact=a[0].PMC_ALTERNATE_MOBILE;
				}
				if(a[0].PMC_EMAIL=="undefined" || a[0].PMC_EMAIL==undefined || a[0].PMC_EMAIL=="" || a[0].PMC_EMAIL==null)
				{
					pmcEmail="";
				}
				else{
					pmcEmail=a[0].PMC_EMAIL;
				}
				
				
				
				document.getElementById("pmcPerson").value=pmcPerson;
				document.getElementById("pmcContact").value=pmcContact;
				document.getElementById("pmcAlternateContact").value=pmcAlternateContact;
				document.getElementById("pmcEmail").value=pmcEmail;
				
				
				
			}
			if(type=="engine")
			{
				var alternator,itemHz,itemVoltage,coolingSystem;
				
				
				if(a[0].DEFAULT_ALTERNATORE_MODEL=="undefined" || a[0].DEFAULT_ALTERNATORE_MODEL==undefined || a[0].DEFAULT_ALTERNATORE_MODEL=="" || a[0].DEFAULT_ALTERNATORE_MODEL==null)
				{
					alternator="";
				}
				else{
					alternator=a[0].DEFAULT_ALTERNATORE_MODEL;
				}
				if(a[0].DEFAULT_VOLTAGE=="undefined" || a[0].DEFAULT_VOLTAGE==undefined || a[0].DEFAULT_VOLTAGE=="" || a[0].DEFAULT_VOLTAGE==null)
				{
					itemVoltage="";
				}
				else{
					itemVoltage=a[0].DEFAULT_VOLTAGE;
				}
				if(a[0].DEFAULT_HZ=="undefined" || a[0].DEFAULT_HZ==undefined || a[0].DEFAULT_HZ=="" || a[0].DEFAULT_HZ==null)
				{
					itemHz="";
				}
				else{
					itemHz=a[0].DEFAULT_HZ;
				}
				if(a[0].DEFAULT_COOLING_SYSTEM=="undefined" || a[0].DEFAULT_COOLING_SYSTEM==undefined || a[0].DEFAULT_COOLING_SYSTEM=="" || a[0].DEFAULT_COOLING_SYSTEM==null)
				{
					coolingSystem="";
				}
				else{
					coolingSystem=a[0].DEFAULT_COOLING_SYSTEM;
				}
				document.getElementById("alternator").value=alternator;
				document.getElementById("itemHz").value=itemHz;
				document.getElementById("itemVoltage").value=itemVoltage;
				document.getElementById("coolingSystem").value=coolingSystem
				
				
			}
			
				
			
			
	    }
	}
	http.send(null);
	}
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

function GetCity(str)
{

	
	if(str==0)
		{
			alert("Please choose state");
			document.getElementById('projectExeState').focus();
			return false;
		}
	
	businessid = document.getElementById('businessid').value;
	select = document.getElementById('projectExePlace');
	
	removeAllOptions(select,true);
			
	var http = new getXMLHttpRequestObject();
	 
	var url = "GetCity";
	var parameters = "state="+str+"&businessFile="+businessid+"&val="+"create";
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	       // alert(http.responseText);
			a=JSON.parse(http.responseText);
			var output = [];
			//alert(a.length);
			select = document.getElementById('projectExePlace');
			
			 for(var i=0; i < a.length-1; i++)
			 {
				//output[i++] = '<option value="'+ a[i] +'">'+ a[i] +'</option>';
				var opt = document.createElement('option');
				opt.value = a[i];
				opt.innerHTML = a[i];
				select.appendChild(opt);
			 }
			 val=a[a.length-1];
			 
			 	document.getElementById("headerleadID").innerHTML="- "+val;
				document.getElementById("leadID").value=val;
				document.getElementById("customerleadID").value=val;
				document.getElementById("itemleadID").value=val;
				document.getElementById("attachmentID").value=val;
				document.getElementById("costSummaryleadID").value=val;
			//alert(i); 
			//document.getElementById("engineMakeType").innerHTML = output;
			
			//innerHTML = output.join('');
			
	    }
	}
	http.send(null);
}

function hideSubmit()
{
	
		
		//alert("Hello"+document.getElementById("myfile").value)
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

function updateAllHeaderValues()
{
	var http = new getXMLHttpRequestObject();
	var url = "LeadHeaderController";
	
	leadID=document.getElementById("leadID").value;
	if(leadID=="" || leadID=="undefined" || leadID==undefined)
	{
		alert("Please choose Choose State and fill all Details in Main Tab first");
		return false;
	}
	document.getElementById("actionValue").value="update";
	document.getElementById("leadStatus").value="Pending";	
	actionValue=document.getElementById("actionValue").value;
	
	projectType=document.getElementById("projectType").value;
	projectExeState=document.getElementById("projectExeState").value;
	projectExePlace=document.getElementById("projectExePlace").value;
	dgType=document.getElementById("dgType").value;
	segment=document.getElementById("segment").value;
	transactionType=document.getElementById("transactionType").value;
	enquiryType=document.getElementById("enquiryType").value;
	LeadExeDate=document.getElementById("LeadExeDate").value;
	DGDeliveryDate=document.getElementById("DGDeliveryDate").value;
	ProjectClosureDate=document.getElementById("ProjectClosureDate").value;
	
	
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
		alert("Please choose Type");
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

	
	leadStatus=document.getElementById("leadStatus").value;
	leadChances=document.getElementById("leadChances").value;


	competitions=document.getElementById("competitions").value;
	
	projectReference=document.getElementById("projectReference").value;
	ownerID=document.getElementById("ownerID").value;
	ownerName=document.getElementById("ownerName").value;
	createdBy=document.getElementById("createdBy").value;
	
	var parameters = "leadID="+leadID+"&projectType="+projectType+"&projectExeState="+projectExeState+"&projectExePlace="+projectExePlace+"&dgType="+dgType+"&projectReference="+projectReference+"&segment="+segment+"&transactionType="+transactionType+"&enquiryType="+enquiryType+"&leadStatus="+leadStatus+"&leadChances="+leadChances+"&competitions="+competitions+"&ownerID="+ownerID+"&ownerName="+ownerName+"&createdBy="+createdBy+"&actionValue="+actionValue+"&LeadExeDate="+LeadExeDate+"&DGDeliveryDate="+DGDeliveryDate+"&ProjectClosureDate="+ProjectClosureDate;
	
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	        
	        if(http.responseText=="Done")
	        	{
					//MoveToFinalStep();
	        	}
	        else
	        	{
	        		alert("Database is Responding slowly,please wait");
	        	}
	    }
	}
	http.send(null);
}
function validateMessage(leadID,tabletype)
{
	leadID=document.getElementById("leadID").value;
	if(leadID=="" || leadID=="undefined" || leadID==undefined)
	{
		alert("Please choose Choose State and fill all Details in Main Tab first");
		return false;
	}
	
	tabletype="LEAD_HEAD";
	a=callValidationMessage(leadID,tabletype);
	
	
	
}
function callValidationMessage(leadID,tabletype)
{
	var http = new getXMLHttpRequestObject();
	var url = "ValidatorController";
	
	
	tabletype="LEAD";
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
					document.getElementById("loadingCreatingFile").style.display='none';
					if(tabletype=="LEAD_HEAD")
					{
						if(value=="yes")
						{
							document.getElementById("leadHeaderVMessage").innerHTML='Lead Header Data Added Successfully';
						}
						else
						{
							alert("Main Tab data is missing , please add to proceed further");
							document.getElementById("leadHeaderVMessage").innerHTML='<span style="color:red">Main Tab data is missing , please add to proceed further</span>';
							document.getElementById("loadingCreatingFile").style.display='none';
							document.getElementById("viewValidateMessage").style.display="block";
							return false;
						}
						
					}
					if(tabletype=="CUSTOMER")
					{
						if(value=="yes")
						{
							document.getElementById("leadCustomerVMessage").innerHTML='Customer Data Added Successfully';
						}
						else{
							alert("Customer Information is not added  , please add to proceed further");
							document.getElementById("leadCustomerVMessage").innerHTML='Customer data is missing , please add to proceed further';
							document.getElementById("loadingCreatingFile").style.display='none';
							document.getElementById("viewValidateMessage").style.display="block";
							return false;

						}
					}
					if(tabletype=="LEADITEMS")
					{
						if(value=="yes")
						{
							document.getElementById("leadItemVMessage").innerHTML='Items Data Added Successfully';
						}
						else{
							alert("Lead Items Information is not added  , please add to proceed further");
							document.getElementById("leadItemVMessage").innerHTML='Customer data is missing , please add to proceed further';
							document.getElementById("loadingCreatingFile").style.display='none';
							document.getElementById("viewValidateMessage").style.display="block";
							return false;

						}
					}
					if(tabletype=="COSTSHEET")
					{
						if(value=="yes")
						{
							document.getElementById("leadCostSummaryVMessage").innerHTML='Cost Sheet Uploaded Successfully';
							document.getElementById("viewMessage").style.display="block";
							document.getElementById("viewValidateMessage").style.display="none";
							updateAllHeaderValues();
							
						}
						else{
							alert("Cost Summary Information is not added  , please add to proceed further");
							document.getElementById("leadCostSummaryVMessage").innerHTML="Cost Summary Information is not added  , please add to proceed further";
							document.getElementById("loadingCreatingFile").style.display='none';
							document.getElementById("viewValidateMessage").style.display="block";
							return false;
						}
						
					}
					if(tabletype=="PMC")
					{
						if(value=="yes")
						{
						document.getElementById("leadPMCVMessage").innerHTML='PMC Data Updated Successfully';
						//document.getElementById("viewMessage").style.display="block";
						}
						else{
							
							document.getElementById("leadPMCVMessage").innerHTML="PMC Data Not Added";
						}
						
					}
					if(tabletype=="CONSULTANT")
					{
						if(value=="yes")
						{
						document.getElementById("leadConsultantVMessage").innerHTML='Consultant Data Updated Successfully';
						}
						//document.getElementById("viewMessage").style.display="block";
						else{
							document.getElementById("leadConsultantVMessage").innerHTML="Consultant Data Not Added";
						}
						
					}
					if(tabletype=="COMMENTS")
					{
						if(value=="yes")
						{
						document.getElementById("leadCommentsVMessage").innerHTML='Comments Updated Successfully';
						}
						//document.getElementById("viewMessage").style.display="block";
						else{
							document.getElementById("leadCommentsVMessage").innerHTML="Comments has not Added";
						}
						
					}
				
			}
			
			}
		 else
	        	{
	        		document.getElementById("loadingCreatingFile").style.display='inline';
	        	}
	}
	http.send(null);
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

function validateLeadCost(leadID)
{
		//alert("Hello"+leadID);
		var http = new getXMLHttpRequestObject();
	 
	var url = "GetCostDetails";
	var parameters = "LEAD_ID="+leadID;
	http.open("GET", url+"?"+parameters, true);
	http.onreadystatechange = function() { //Handler function for call back on state change.
	    if(http.readyState == 4) {
	       // alert(http.responseText);
			a=JSON.parse(http.responseText);
			//var output = [];
			//alert(a.length);
			
			leadItemTotal=a[0];
			supbasic=a[1];
			labbasic=a[2];
			lowsidemargin=a[3];
			projectmargin=a[4];
			projecttotal=a[5];
			dgValue=a[6];
			macthingStatus=a[7];
			document.getElementById("lowsidesupbasic").value=supbasic;
			document.getElementById("lowsidelabbasic").value=labbasic;
			document.getElementById("lowsidesmargin").value=lowsidemargin;
			document.getElementById("projectmargin").value=projectmargin;
			document.getElementById("projecttotal").value=projecttotal;
			document.getElementById("DGValue").value=dgValue;
			
			if(macthingStatus=="NotMatching")
			{
				document.getElementById("matchingerror").style.display='block';
				document.getElementById("matchingsuccess").style.display='none';
			}
			else if(macthingStatus=="Matching"){
				document.getElementById("matchingerror").style.display='none';
				document.getElementById("matchingsuccess").style.display='block';
			}
			else{}
			
			document.getElementById("CostNextButton").style.display='inline';
			/*
			CostNextButton
			matchingerror
			matchingsuccess
			
			
			DGValue
																projecttotal
																lowsidesupbasic
																lowsidelabbasic
																lowsidesmargin
																projectmargin
			*/
			
			/*select = document.getElementById('engineMakeType');
			
			 for(var i=0; i < a.length; i++)
			 {
				//output[i++] = '<option value="'+ a[i] +'">'+ a[i] +'</option>';
				var opt = document.createElement('option');
				opt.value = a[i];
				opt.innerHTML = a[i];
				select.appendChild(opt);
			 }*/
			//alert(i); 
			//document.getElementById("engineMakeType").innerHTML = output;
			
			//innerHTML = output.join('');
			
	    }
	}
	http.send(null);
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
</script>


        <div id="page-wrapper">
        
         <div class="row" >
			<div class="col-lg-12" style="margin-top:5px">
	           <ol class="breadcrumb">
				 <li><a href="index.jsp"><i class="fa fa-home fa-fw"></i> Home</a></li>
				 <li class="dropdown active">
        			  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" 
        			  aria-haspopup="true" aria-expanded="false">Lead & Enquiry <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            		            
			           
			            <li class="dropdown-submenu">
			                <a tabindex="-1" href="#">Create Lead</a>
			                <ul class="dropdown-menu">
			                  <li><a tabindex="-1" href="MainController?action=creatlead&businessFile=DG">DG</a></li>
			                  
			                  <li><a href="MainController?action=creatlead&businessFile=PN">Panel</a></li>
			                  
			                </ul>
			               
              			</li>
              			<li role="separator" class="divider"></li>
              			<li><a href="MainController?action=listLead&search=All">View Leads</a></li>
              		
			          </ul>
        		</li>
				  
				  
				</ol>
			</div>
		</div>
            <!-- /.row -->
			<div class="row">
			<div class="col-lg-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
						<c:choose>
															<c:when test="${create=='create'}">
																Create Lead 
															</c:when>    
															<c:otherwise>
																Create Budget Lead
															</c:otherwise>
														</c:choose>
						
                              
							<span id="headerleadID">${leadID} </span>
							
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs">
                           
                                <li class="active" id="homeTab"><a href="#home" data-toggle="tab" aria-expanded="false">Main</a>
                                </li>
                                <li class="" id="customerDetailsTab"><a href="#customerDetails" data-toggle="tab" aria-expanded="false">Customer</a>
                                </li>
								<li class="" id="itemDetailsTab"><a href="#itemDetails" data-toggle="tab" aria-expanded="false">Item Details</a>
                                </li>
                                <li class="" id="settingsTab"><a href="#settings" data-toggle="tab" aria-expanded="false">Comments </a>
                                </li>
								<li class="" id="costsummarysheetTab"><a href="#costsummarysheet" data-toggle="tab" aria-expanded="false">Cost Summary Sheet</a>
                                </li>
								<li class="" id="validationchecksTab"><a href="#validationchecks" data-toggle="tab" aria-expanded="false">Validation and Check</a>
                                </li>
								
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div class="tab-pane fade active in" id="home">
                                    
								<br />
                               
									<div class="row">
										
										<div class="col-lg-3">
											<div class="form-group">
												<label>Project Type</label> <span class="text-alert-danger">*</span>
												<script>
												getLeadNo('create');
												</script>
                                            	<select  class="form-control" name="projectType" id="projectType" >
                                            		<option value="0">Select Lead Type</option>
													<c:forEach items="${projectTypes}" var="projectType"> 
														<option value="${projectType}" ${projectType == selbusinessFile ? 'selected="selected"' : ''}>${projectType}</option>
											    	</c:forEach>
						                    	</select>
						                    	<errors path="leadType" cssClass="error"/>
											</div>
										</div>
										<input type="hidden" id="businessid" value="<%=request.getParameter("businessFile") %>" />
										<div class="col-lg-3">
											<div class="form-group">
											<label path="projectExeState">Execution State</label> <span class="text-alert-danger">*</span>
											 <select path="projectExeState" id="projectExeState" value="${projectExeState}" name="projectExeState" class="form-control" onchange="return GetCity(this.value)">
											 	<option value="0">Select State</option>
												<c:forEach items="${exe_state}" var="states"> 
														<option value="${states}">${states}</option>
											    	</c:forEach>
						                    </select>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="projectExePlace">Execution City</label> <span class="text-alert-danger">*</span>
	                                            
												
												<select path="projectExePlace" id="projectExePlace" value="${projectExePlace}" name="projectExePlace" class="form-control">
	                                            	<option value="0">Select City</option>
													<c:forEach items="${exe_city}" var="city"> 
														<option value="${city}">${city}</option>
												    </c:forEach>
							                    </select>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="dgType">Type</label> <span class="text-alert-danger">*</span>
												<select path="dgType" id="dgType" name="dgType"  class="form-control">
													<option value="0">Select  Type</option>
													<c:forEach items="${dgTypes}" var="dgType"> 
														<option value="${dgType}">${dgType}</option>
											    	</c:forEach>
						                    	</select>											
											</div>
										</div>
									</div>
									
									<div class="row">
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="segment">Segment</label> <span class="text-alert-danger">*</span>
												<select path="segment" id="segment" value="${segment}" name="segment" class="form-control">
													<option value="0">Select Segment</option>
													<c:forEach items="${segmentType}" var="segment"> 
														<option value="${segment}">${segment}</option>
											    	</c:forEach>
						                    	</select>
											</div>
										</div>
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="transactionType">Transaction Type</label> <span class="text-alert-danger">*</span>
												<select path="transactionType" id="transactionType" value="${transactionType}" name="transactionType" class="form-control">
													<option value="0">Select Transaction Type</option>
													<c:forEach items="${transactionType}" var="tType"> 
														<option value="${tType}">${tType}</option>
											    	</c:forEach>
						                    	</select>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
											<label path="enquiryType">Enquiry Type</label> <span class="text-alert-danger">*</span>
											<select path="enquiryType" id="enquiryType" value="${enquiryType}" name="enquiryType" class="form-control">
												<option value="0">Select Enquiry Type</option>
													<c:forEach items="${enquiryType}" var="etype"> 
														<option value="${etype}">${etype}</option>
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
												<input path="LeadExeDate" type="date" id="LeadExeDate" value="${LeadExeDate}" class="form-control" name="LeadExeDate" />
												
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
												<input path="ownerID" type="text" id="ownerID" value="${userID}" class="form-control" name="ownerID" />
												<input path="ownerName" type="hidden" id="ownerName" value="${userName}" class="form-control" name="ownerName" />
											</div>
										</div>
										
									<div class="col-lg-3">
											<div class="form-group">
												<label path="leadStatus">Status</label>
												<select path="leadStatus" id="leadStatus" value="${leadStatus}" name="leadStatus" class="form-control" disabled>
													<option value="0">Select Status</option>
													<c:forEach items="${statusType}" var="stype"> 
														
														
														<c:choose>
															<c:when test="${stype=='Draft'}">
																<option value="${stype}" selected >${stype}</option>
															</c:when>    
															<c:otherwise>
																<option value="${stype}">${stype}</option>
															</c:otherwise>
														</c:choose>
											    	</c:forEach>
						                    	</select>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="leadChances">Chances</label>
												
												
												
												<select path="leadChances" name="leadChances" id="leadChances" value="${leadChances}"  class="form-control" disabled >
													
													<c:forEach items="${chancesType}" var="chanceType"> 
														
														
														<c:choose>
															<c:when test="${stype=='R'}"> <span class="text-alert-danger">*</span>
																<option value="${chanceType}" selected >${chanceType}</option>
															</c:when>    
															<c:otherwise>
																<option value="${chanceType}">${chanceType}</option>
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
												<input type="text" name="actionValue" value="${create}" class="form-control" style="display:none" id="actionValue"/>
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
									
									
									<div align="middle"><input type="button" value="Submit" id="headerButton" class="btn btn-success" onclick="return storeHeaderDetails()"/></div>
								
                            </div>
                                
                            <div class="tab-pane fade" id="customerDetails">
                            <br />
                                <form data-toggle="validator" role="form">
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerType">Customer Type</label> <span class="text-alert-danger">*</span>
												<select path="customerType" id="customerType" name="customerType" value="${customerType}" class="form-control" onchange="getCustomer(this.value)">
												<option value="0">Select</option>
												
												
												
														
														<c:forEach items="${customerType}" var="customerType">
															<option value="${customerType}">${customerType}</option>
												    	</c:forEach>
							                    </select>
											</div>
										</div>
										<input type="hidden" path="leadID" value="${leadID}" class="form-control" readonly="true" id="customerleadID"/>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerName">Customer Name</label> <span class="text-alert-danger">*</span>
												
												<input type="text" path="customerName" maxlength="100" id="mcustomerName" value="${customerName}" class="form-control" name="customerName" />
												
												<select path="customerName" id="customerName" name="customerName" value="${customerNames}" class="form-control"  onChange="callCustomer(this.value,'customer')" style="display:none">
														<option value="0">Select</option>
														<c:forEach items="${customerNames}" var="customerName">
															<option value="${customerName}">${customerName}</option>
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
									
								<!--<h4>Customer Address & Other details</h4>
									<hr />
									-->
									
									
									
									<div class="row" style="display:none">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerBillingAddress1">Billing Address</label>
												<input type="text" maxlength="50" path="customerBillingAddress1" value="${customerBillingAddress1}" class="form-control" name="customerBillingAddress1" id="customerBillingAddress1" />
											</div>
										</div>
										<div class="col-lg-3">
										
										
												
												
												
											<div class="form-group">
												<label path="customerBillingAddress2">State</label>
												
												<select path="customerBillingAddress2" id="customerBillingAddress2" value="${customerBillingAddress2}" name="customerBillingAddress2" class="form-control">
											 	<option value="0">Select</option>
												<c:forEach items="${exe_state}" var="states"> 
														<option value="${states}">${states}</option>
											    	</c:forEach>
						                    </select>
											
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerBillingAddress3">City</label>
												
												<select path="customerBillingAddress3" id="customerBillingAddress3" value="${customerBillingAddress3}" name="customerBillingAddress3" class="form-control">
	                                            	<option value="0">Select </option>
													<c:forEach items="${exe_city}" var="city"> 
														<option value="${city}">${city}</option>
												    </c:forEach>
							                    </select>
												
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerBillingAddress4">Pin Code</label>
												<input type="text" maxlength="6" path="customerBillingAddress4" value="${customerBillingAddress4}" class="form-control" name="customerBillingAddress4" id="customerBillingAddress4" pattern="^([0|\+[0-9]{1,5})?([1-9][0-9]{9})$" />
											</div>
										</div>
									</div>	

									<div class="row" style="display:none">
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
												
												<select path="customerDeliveryAddress2" id="customerDeliveryAddress2" value="${customerDeliveryAddress2}" name="customerDeliveryAddress2" class="form-control">
											 	<option value="0">Select</option>
												<c:forEach items="${exe_state}" var="states"> 
														<option value="${states}">${states}</option>
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
														<option value="${city}">${city}</option>
												    </c:forEach>
							                    </select>
												
												
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerDeliveryAddress4">Pin Code</label>
												<input type="text" path="customerDeliveryAddress4" value="${customerDeliveryAddress4}" class="form-control" name="customerDeliveryAddress4" maxlength="6" id="customerDeliveryAddress4" pattern="^([0|\+[0-9]{1,5})?([1-9][0-9]{9})$" />
											</div>
										</div>
									</div>	
									<div class="row" style="display:none">
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
												<select name="customerConsultantType" id="customerConsultantType" class="form-control" onchange="getConsultantDetails(this.value)">
													<option value="0">Select</option>
													<c:forEach items="${pmcTypeList}" var="pmcType">
															<option value="${pmcType}">${pmcType}</option>
												 </c:forEach>
													
												
												</select>
											</div>
										</div>
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerConsultant">Consultant Name</label>
												<input type="text" maxlength="50" path="customerConsultant" value="${customerConsultant}" class="form-control" name="customerConsultant" id="mcustomerConsultant"/>
												
												<select path="consultantName" id="customerConsultant" name="customerConsultant" class="form-control"  onChange="callCustomer(this.value,'consultant')" style="display:none">
														<option value="0">Select</option>
														<c:forEach items="${consultantNames}" var="consultantName">
															<option value="${consultantName}">${consultantName}</option>
												    	</c:forEach>
							                    </select>
												
												
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
												<select name="pmcType" id="pmcType" class="form-control" onchange="getPMCDetails(this.value)">
													<option value="0">Select</option>
													<c:forEach items="${pmcTypeList}" var="pmcType">
															<option value="${pmcType}">${pmcType}</option>
												 </c:forEach>
													
												
												</select>
											</div>
										</div>
										
										<div class="col-lg-3">
											<div class="form-group">
												<label path="pmc">PMC</label>
												<input type="text" maxlength="50" path="pmc" value="${pmc}" class="form-control" name="pmc" id="mpmc"/>
												<select path="pmc" id="pmc" name="pmc"  class="form-control"  onChange="callCustomer(this.value,'pmc')"  style="display:none">
														<option value="0">Select</option>
														<c:forEach items="${pmcNames}" var="pmcName">
															<option value="${pmcName}">${pmcName}</option>
												    	</c:forEach>
							                    </select>
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
									
									
								
									
									<div align="middle"><input type="button" value="Submit" id="customerButton" class="btn btn-success" onclick="return storeCustomerDetails()"/></div>
									
									</form>
                                </div>
								<div class="tab-pane fade" id="itemDetails">
                                	<br />
                                	
									<div class="row">
										<div class="col-lg-12">
											<div class="panel panel-primary">
		                                    	<a data-toggle="collapse" href="#collapseOne" style="text-decoration:none"  >
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;" id="itemPanelHead">
														Add Item
													</div>
												</a>
			                                    <div id="collapseOne" class="panel-collapse collapse" aria-expanded="false" >
			                                        <div class="panel-body">
																<div class="row">
																	
																	<div class="col-lg-3" style="display:none">
																		<div class="form-group">
																			<label path="itemEngineType">Engine Type</label> <span class="text-alert-danger">*</span>
																			<select path="engineType" value="${engineType}" class="form-control" name="engineType" id="engineType" onChange="GetItemModelDetails(this.value)">
																				<option value="0">Select Type</option>
																				<option value="ENGINE">Engine</option>
																				<option value="ALTERNATOR">Alternator</option>
																				
																			</select>
																			
																			<input type="hidden" path="leadID" value="${leadID}" class="form-control" readonly="true" id="itemleadID"/>
																		</div>
																	</div>
																	
																	<div class="col-lg-6">
																		<div class="form-group">
																			<label path="itemEngineType">Choose Panel </label>
																			<input type="hidden" value="" id="dtlID" name="dtlID" />
																			
																			<select path="engineMakeType" value="${engineMakeType}" class="form-control" name="engineMakeType" id="engineMakeType" onchange="return callCustomer(this.value,'engine')">
																			<option value="0">Select</option>
																				
																				
																			</select>
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
																	
																	
																	
																</div>
																
																<div class="row" style="display:none">
																
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
																			<label path="itemVoltage">Alternator</label>
																			<input type="text" path="alternator" maxlength="100" value="${alternator}" class="form-control" id="alternator" name="alternator" />
																			
																			
																		</div>
																	</div>
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="itemHz">Hz</label> <span class="text-alert-danger">*</span>
																			
																			<select  id="itemHz" name="itemHz"  class="form-control" >
																			<option value="0">Select</option>
																				<c:forEach items="${hzType}" var="hztype">
																					<option value="${hztype}">${hztype}</option>
																				</c:forEach>
																			</select>
																			
																			
																			
																			
																		</div>
																	</div>
																	
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="coolingSystem">Cooling System</label> <span class="text-alert-danger">*</span>
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
																
																
																<div align="middle">
																
																<input type="button" value="Add" id="itemDetailsAddButton" class="btn btn-success" onclick="return storeItemDetails(this.value)"/>
																
																<input type="button" value="Update" id="itemDetailsUpdateButton" class="btn btn-success" onclick="return storeItemDetails(this.value)" style="display:none"/>
																
																<input type="button" value="Next" id="itemDetailsNextButton" class="btn btn-success" onclick="MoveToCommentsSection()" style="display:none" />
																
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
														Item  Details
													</div>
												</a>
			                                    <div id="collapseTwo" class="panel-collapse collapse in" aria-expanded="false" >
			                                    	<div class="panel-body">
														<div class="dataTable_wrapper">
							                                <table class="table table-striped table-bordered table-hover">
							                                    <thead>
							                                        <tr>
							                                            
																		<th style="display:none">Rating</th>
																		<th>Panle Model</th>
							                                            <th style="display:none">Alternator Model</th>
							                                            <th style="display:none">Voltage</th>
							                                            <th>Qty</th>
																		<th>Value (In Lakhs)</th>
																		<th>Action</th>
							                                        </tr>
							                                    </thead>
															   <tbody id="tableBody">
							                                       
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
																							<textarea maxlength="200" path="comments" value="${comments}" name="comments" class="form-control" rows="3" id="comments"></textarea>
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
																											<th>Comment Types</th>
																											
																											
																										</tr>
																									</thead>
																								   <tbody id="tableCommentsBody">
																									   
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
																											
																											<th>Attachment Section</th>
																										</tr>
																									</thead>
																								   <tbody id="tableAttachmentsBody">
																									   
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
																		<input type="hidden" path="leadID" value="${leadID}" class="form-control" name="leadID" readonly="true" id="attachmentID"/>
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
									
									<div class="row">
										<div class="col-lg-12">
											<div class="panel panel-primary">
		                                    	<a data-toggle="collapse" href="#collapseCostSummary" style="text-decoration:none"  >
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Upload Cost Summary Sheet
													</div>
												</a>
												
												
												
		                                    	<div id="collapseCostSummary" class="panel-collapse collapse in" aria-expanded="false" >
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
																<input type="hidden" path="leadID" value="${leadID}" class="form-control" name="leadID" readonly="true" id="costSummaryleadID"/>
																
																
																
																<input type="hidden" path="userID" value="${userID}" class="form-control" name="createdBy" readonly="true"/>
																<div align="middle">
																
																<input type="submit" value="Submit" id="CostSubmitButton" class="btn btn-success"/>
																<input type="button" value="Next" id="CostNextButton" class="btn btn-success" style="display:none" onclick="return MoveToFinalStep()"/>
																
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
		                                    	<a data-toggle="collapse" href="#collapsePrice" style="text-decoration:none"  >
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Price Summary
													</div>
												</a>
		                                    	<div id="collapsePrice" class="panel-collapse collapse" aria-expanded="false" >
			                                        <div class="panel-body">
																	<div class="row">
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">DG Value</label>
																		<input type="number" name="DGValue" id="DGValue" class="form-control" />
																	</div>
																</div>
																
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">Project Total</label>
																		<input type="number" name="projecttotal" id="projecttotal" class="form-control" />
																	</div>
																</div>
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">Low Side Supply Basic</label>
																		<input type="number" name="lowsidesupbasic" id="lowsidesupbasic" class="form-control" />
																	</div>
																</div>
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">Low Side Labour Basic</label>
																		<input type="number" name="lowsidelabbasic" id="lowsidelabbasic" class="form-control" />
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">Low Side Margin</label>
																		<input type="number" name="lowsidesmargin" id="lowsidesmargin" class="form-control" />
																	</div>
																</div>
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="comments">Project Margin</label>
																		<input type="number" name="projectmargin" id="projectmargin" class="form-control" />
																	</div>
																</div>
															</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									
								
                            </div>
							
							
							<div class="tab-pane fade" id="validationchecks" >
                                    
									<div class="row" id="viewValidateMessage">
										<div class="col-lg-12" style="margin-top:10px">
										
										<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> Wow !!! You are just one step 
																		behind
																		<c:choose>
															<c:when test="${create=='create'}">
																Create Lead 
															</c:when>    
															<c:otherwise>
																Create Budget Lead
															</c:otherwise>
														</c:choose>
																		 , please click validate button 
																		
																		<button class="btn btn btn-primary" onclick="return validateMessage()" style="float:right">Validate</button>
																		
																		
																	  </div>
										</div>
										<button class="btn btn-lg btn-warning" id="loadingCreatingFile"><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span> validating your data Please Wait.......</button>	
										</div>
									</div>
									<div class="row" style="display:none" id="viewMessage">
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
																		<i class="fa fa-check-circle"></i> <span id="leadHeaderVMessage">Lead Header Information Added Successfully</span>
																	  </div>
																	</div>
																	
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> <span id="leadCustomerVMessage">Customer Information Added Successfully </span>
																	  </div>
																	</div>
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i><span id="leadItemVMessage"> Item Details Added Successfully</span>
																	  </div>
																	</div>
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i><span id="leadCostSummaryVMessage"> Cost Summary Added Successfully</span>
																	  </div>
																	</div>
																	
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i> <span id="leadConsultantVMessage">Consultant Details Added Successfully</span>
																	  </div>
																	</div>
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i><span id="leadPMCVMessage"> PMC Added Successfully</span>
																	  </div>
																	</div>
																	
																	<div class="well well-transparent">
																	  <div style="font-size: 15px; line-height: 1.5em;">
																		<i class="fa fa-check-circle"></i><span id="leadCommentsVMessage"> Comments has Added</span>
																	  </div>
																	</div>
																	
																		
																		
																		
																		
																		
																	</div>
																</div>
																
																<div class="col-lg-12">
																	<div class="form-group">
																		
																	<div class="well well-transparent">
																	  <div style="font-size: 24px; line-height: 1.5em;">
																		<i class="fa fa-thumbs-o-up"></i>
																		
																		 <c:choose>
															<c:when test="${create=='create'}">
																		 Huraay !!! Lead Created Successfully 
															</c:when>    
															<c:otherwise>
																		 Huraay !!! Budgetory Lead Created Successfully
															</c:otherwise>
														</c:choose>
																	
																		 
																		 
																	  </div>
																	</div>
																		
																	</div>
																</div>
																
																<div class="col-lg-6">
																	<div class="well well-transparent">
																	  <div style="font-size: 24px; line-height: 1.5em;">
																	  <c:choose>
															<c:when test="${create=='create'}">
																	<a href="MainController?action=listLead&search=All">Go To View Lead Page</a> 
															</c:when>    
															<c:otherwise>
																	<a href="MainController?action=listBudgetLead&search=All">Go To View Budgetory Lead </a>
															</c:otherwise>
														</c:choose>
																	
																	  </div>
																	</div>
																
																</div>
																
																<div class="col-lg-6">
																
																<div class="well well-transparent">
																	  <div style="font-size: 24px; line-height: 1.5em;">
																		<a href="LoginController">Go To Dashboard</a>
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
	
<script>
GetItemModelDetails('PANEL_TYPE');
document.getElementById("loadingCreatingFile").style.display='none';
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