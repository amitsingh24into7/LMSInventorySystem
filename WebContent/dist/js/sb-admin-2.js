$(function() {

    $('#side-menu').metisMenu();

});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    var element = $('ul.nav a').filter(function() {
        return this.href == url || url.href.indexOf(this.href) == 0;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});

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
		document.getElementById("mcustomerName").value='';
		document.getElementById("customerContactNumber").value='';
		document.getElementById("customerEmail").value='';
		
		
	}
}
function getConsultantDetails(str)
{
	
	if(str=='Existing')
	{
		document.getElementById("customerConsultant").style.display='block';
		document.getElementById("mcustomerConsultant").style.display='none';
		document.getElementById("mcustomerConsultant").value='';
		
		document.getElementById("customerConsultant").readOnly=false;
		document.getElementById("mcustomerConsultant").readOnly=false;
		document.getElementById("customerConsultantPersonName").readOnly=false;
		document.getElementById("customerConsultantNumber").readOnly=false;

	}
	else if(str=='None'){
		
		document.getElementById("customerConsultant").style.display='none';
		document.getElementById("mcustomerConsultant").style.display='block';
		document.getElementById("mcustomerConsultant").readOnly=true;
		document.getElementById("customerConsultant").readOnly=true;
		document.getElementById("customerConsultantPersonName").readOnly=true;
		document.getElementById("customerConsultantNumber").readOnly=true;
		
		document.getElementById("mcustomerConsultant").value='';
		document.getElementById("mcustomerConsultant").value='';
		document.getElementById("customerConsultantPersonName").value='';
		document.getElementById("customerConsultantNumber").value='';
		
		
	}
	else{
		document.getElementById("customerConsultant").style.display='none';
		document.getElementById("mcustomerConsultant").style.display='block';
		document.getElementById("mcustomerConsultant").value='';
		
		document.getElementById("customerConsultant").readOnly=false;
		document.getElementById("mcustomerConsultant").readOnly=false;
		document.getElementById("customerConsultantPersonName").readOnly=false;
		document.getElementById("customerConsultantNumber").readOnly=false;
		
		document.getElementById("mcustomerConsultant").value='';
		document.getElementById("mcustomerConsultant").value='';
		document.getElementById("customerConsultantPersonName").value='';
		document.getElementById("customerConsultantNumber").value='';
		}
}
function getPMCDetails(str)
{
	
	if(str=='Existing')
	{
		document.getElementById("pmc").style.display='block';
		document.getElementById("mpmc").style.display='none';
		document.getElementById("mpmc").value='';
		
		document.getElementById("pmc").readOnly=false;
		
		document.getElementById("mpmc").readOnly=false;
		document.getElementById("pmcPerson").readOnly=false;
		document.getElementById("pmcContact").readOnly=false;
		document.getElementById("pmcAlternateContact").readOnly=false;
		document.getElementById("pmcEmail").readOnly=false;
	}
	else if(str=='None')
	{
		document.getElementById("pmc").style.display='none';
		document.getElementById("mpmc").style.display='block';
		document.getElementById("pmc").readOnly=true;
		document.getElementById("mpmc").readOnly=true;
		document.getElementById("pmcPerson").readOnly=true;
		document.getElementById("pmcContact").readOnly=true;
		document.getElementById("pmcAlternateContact").readOnly=true;
		document.getElementById("pmcEmail").readOnly=true;
		document.getElementById("mpmc").value='';
		document.getElementById("pmcPerson").value='';
		document.getElementById("pmcContact").value='';
		document.getElementById("pmcAlternateContact").value='';
		document.getElementById("pmcEmail").value='';
		
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
		
		document.getElementById("mpmc").value='';
		document.getElementById("pmcPerson").value='';
		document.getElementById("pmcContact").value='';
		document.getElementById("pmcAlternateContact").value='';
		document.getElementById("pmcEmail").value='';
	}
}

function getLeadNo(action)
{
	str=document.getElementById('projectExeState').value;
	if(str==0)
	{
		alert("Please choose state");
		document.getElementById('projectExeState').focus();
		return false;
	}

businessid = document.getElementById('businessid').value;
//select = document.getElementById('projectExePlace');

//removeAllOptions(select,true);
		
var http = new getXMLHttpRequestObject();
 
var url = "GetLeadNo";
var parameters = "state="+str+"&businessFile="+businessid+"&val="+action;
http.open("GET", url+"?"+parameters, true);
http.onreadystatechange = function() { //Handler function for call back on state change.
    if(http.readyState == 4) {
       // alert(http.responseText);
		a=JSON.parse(http.responseText);
		
		 val=a[0];
		 
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

var dates = {
	    convert:function(d) {
	        // Converts the date in d to a date-object. The input can be:
	        //   a date object: returned without modification
	        //  an array      : Interpreted as [year,month,day]. NOTE: month is 0-11.
	        //   a number     : Interpreted as number of milliseconds
	        //                  since 1 Jan 1970 (a timestamp) 
	        //   a string     : Any format supported by the javascript engine, like
	        //                  "YYYY/MM/DD", "MM/DD/YYYY", "Jan 31 2009" etc.
	        //  an object     : Interpreted as an object with year, month and date
	        //                  attributes.  **NOTE** month is 0-11.
	        return (
	            d.constructor === Date ? d :
	            d.constructor === Array ? new Date(d[0],d[1],d[2]) :
	            d.constructor === Number ? new Date(d) :
	            d.constructor === String ? new Date(d) :
	            typeof d === "object" ? new Date(d.year,d.month,d.date) :
	            NaN
	        );
	    },
	    compare:function(a,b) {
	        // Compare two dates (could be of any type supported by the convert
	        // function above) and returns:
	        //  -1 : if a < b
	        //   0 : if a = b
	        //   1 : if a > b
	        // NaN : if a or b is an illegal date
	        // NOTE: The code inside isFinite does an assignment (=).
	        return (
	            isFinite(a=this.convert(a).valueOf()) &&
	            isFinite(b=this.convert(b).valueOf()) ?
	            (a>b)-(a<b) :
	            NaN
	        );
	    },
	    inRange:function(d,start,end) {
	        // Checks if date in d is between dates in start and end.
	        // Returns a boolean or NaN:
	        //    true  : if d is between start and end (inclusive)
	        //    false : if d is before start or after end
	        //    NaN   : if one or more of the dates is illegal.
	        // NOTE: The code inside isFinite does an assignment (=).
	       return (
	            isFinite(d=this.convert(d).valueOf()) &&
	            isFinite(start=this.convert(start).valueOf()) &&
	            isFinite(end=this.convert(end).valueOf()) ?
	            start <= d && d <= end :
	            NaN
	        );
	    }
	}
function validateIndentCost(leadID)
{
		//alert("Hello"+leadID);
		var http = new getXMLHttpRequestObject();
	 
	var url = "GetIndentCostSummaryDetails";
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
			dgSellingValue=a[8];
			lowSideSellignValue=a[9];
			projectSellingValue=a[10];
			
			document.getElementById("lowsidesupbasic").value=supbasic;
			document.getElementById("lowsidelabbasic").value=labbasic;
			document.getElementById("lowsidesmargin").value=lowsidemargin;
			document.getElementById("projectmargin").value=projectmargin;
			document.getElementById("projecttotal").value=projecttotal;
			document.getElementById("DGValue").value=dgValue;
			
			document.getElementById("dgSellingValue").value=dgSellingValue;
			document.getElementById("lowSideSellignValue").value=lowSideSellignValue;
			document.getElementById("projectSellingValue").value=projectSellingValue;
			
			if(macthingStatus=="NotMatching")
			{
				alert("Total Selling Value and Cost Summary Total Value is not matching");
				document.getElementById("matchingerror").style.display='block';
				document.getElementById("matchingsuccess").style.display='none';
				return false;
			}
			else if(macthingStatus=="Matching"){
				document.getElementById("matchingerror").style.display='none';
				document.getElementById("matchingsuccess").style.display='block';
				//return true;
				tabletype="LEAD_HEAD";
				a=callValidationMessage(leadID,tabletype);
			}
			else{}
			
			//document.getElementById("CostNextButton").style.display='inline';
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

function setLeadItem(DTL_ID,LEAD_ID,DTL_RATING,DTL_ENGINE_MODEL,DTL_ALTERNATOR_MODEL,DTL_VOLTAGE,DTL_QTY,DTL_AMOUNT,DTL_ENGINE_MAKE,DTL_COOLING_SYSTEM,DTL_FLEX1,DTL_HZ,DTL_ALTERNATOR_MAKE,DTL_ENGINE_MODEL,DTL_FLEX2)
{
	
	
	document.getElementById("itemDetailsAddButton").style.display='none';
	document.getElementById("itemDetailsUpdateButton").style.display='inline';
	document.getElementById("itemDetailsNextButton").style.display="none";
	document.getElementById("itemPanelHead").innerHTML='Update Item';
	
	
	document.getElementById("alternator").value=DTL_ALTERNATOR_MODEL;
	document.getElementById("DGModel").value=DTL_FLEX2;
	document.getElementById("EngineModel").value=DTL_ENGINE_MODEL;
	document.getElementById("alternatorMakeType").value=DTL_ALTERNATOR_MAKE;
	document.getElementById("itemHz").value=DTL_HZ;
	document.getElementById("itemVoltage").value=DTL_VOLTAGE;
	document.getElementById("itemQty").value=DTL_QTY;
	document.getElementById("itemAmount").value=DTL_AMOUNT;
	document.getElementById("coolingSystem").value=DTL_COOLING_SYSTEM
	
	if(DTL_FLEX1=='' || DTL_FLEX1=='undefined' || DTL_FLEX1==undefined)
	{
		engineMakeType=DTL_ENGINE_MAKE+"--"+DTL_RATING;
	}
	else{
		engineMakeType=DTL_ENGINE_MAKE+"--"+DTL_RATING+"--"+DTL_FLEX1;
	}
	document.getElementById("engineMakeType").value=engineMakeType;
	document.getElementById("dtlID").value=DTL_ID;
	
}
