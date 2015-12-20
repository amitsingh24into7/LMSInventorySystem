$(document).ready(function() {
	
var options = {
	beforeSend : function() {
		$("#progressbox").show();
		// clear everything
		$("#progressbar").width('0%');
		$("#message").empty();
		$("#percent").html("0%");
	},
	uploadProgress : function(event, position, total, percentComplete) {
		$("#progressbar").width(percentComplete + '%');
		$("#percent").html(percentComplete + '%');

		// change message text and % to red after 50%
		if (percentComplete > 50) {
			$("#message").html("<font color='red'>File Upload is in progress .. </font>");
		}
	},
	success : function() {
		$("#progressbar").width('100%');
		$("#percent").html('100%');
	},
	complete : function(response) {
		$("#message").html("<font color='blue'>Your file has been uploaded!</font>");
		//hideSubmit();
		//alert("Hello"+$("#leadID").val);
		a=document.getElementById("leadID").value;
		
		getAttachments(a);
		document.getElementById("myfile").value='';
		$("#progressbar").width('0%');
		$("#message").empty();
		$("#percent").html("0%");
	},
	error : function() {
		$("#message").html("<font color='red'> ERROR: unable to upload files</font>");
	}
};
var options1 = {
		beforeSend : function() {
			
			
			$("#progressbox1").show();
			// clear everything
			$("#progressbar1").width('0%');
			//$("#message").empty();
			$("#percent1").html("0%");
		},
		uploadProgress : function(event, position, total, percentComplete) {
			//$("#CostSubmitButton").hide();
			$("#progressbar1").width(percentComplete + '%');
			$("#percent1").html(percentComplete + '%');

			// change message text and % to red after 50%
			if (percentComplete > 50) {
				$("#message").html("<font color='red'>File Upload is in progress .. </font>");
			}
		},
		success : function() {
			$("#progressbar1").width('100%');
			$("#percent1").html('100%');
		},
		complete : function(response) {
			
				$("#collapsePrice").removeClass("panel-collapse collapse");
				$("#collapsePrice").addClass("panel-collapse collapse in");
			//$("#message").html("<font color='blue'>Your file has been uploaded!</font>");
				validateLeadCost($("#leadID").val());
				//validateLeadCost("15KAPN0144");
		},
		error : function() {
			$("#message").html("<font color='red'> ERROR: unable to upload files</font>");
			$("#CostSubmitButton").show();
		}
	};
$("#UploadForm").ajaxForm(options);
$("#UploadForm1").ajaxForm(options1);
});