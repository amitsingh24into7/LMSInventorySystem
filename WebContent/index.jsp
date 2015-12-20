<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
        <div id="page-wrapper" style="min-height: 290px;">
            
            <div class="row">
                <div class="col-lg-3 col-md-6" style="margin-top:10px">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-thumbs-o-up fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                   
								   <div class="medium">${leadCount}</div>
								   <div>Total Lead Count</div>
                                    
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
				<div class="col-lg-3 col-md-6" style="margin-top:10px">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-inr fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                   
								   <div class="smaller">${leadAmount}</div>
								   <div>Total Lead Value</div>
                                    
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
               
                <div class="col-lg-3 col-md-6" style="margin-top:10px">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-shopping-cart fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="medium">${indentCount}</div>
                                    <div>Total Indent Count</div>
                                    
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
				
				<div class="col-lg-3 col-md-6" style="margin-top:10px">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-inr fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="smaller">${indentAmount}</div>
                                    <div>Total Indent Value</div>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
                
            </div>
            <!-- /.row -->
            
            <!-- Chart -->
            <div class="row">
			<!-- /.panel-heading -->
			<div class="col-lg-8">
			<div class="panel panel-default">
			<div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> Month Wise Lead/Indent Trend 
                            <span id="barlegend"></span>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div id="morris-bar-chart"></div>
                                    
                                </div>
                                <!-- /.col-lg-8 (nested) -->
                            </div>
                            <!-- /.row -->
                        </div>
			</div>
			</div>
						
                <div class="col-lg-4">
                    
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> Current Month Lead/Indent Trend
                            
                        </div>
                        <div class="panel-body">
                        <div class="row">
                                <div class="col-lg-12">
                            <div id="morris-donut-chart-count"></div>
                            <span id="donutcountlegend" ></span>
                            <a href="MainController?action=listLead&search=All" class="btn btn-default btn-block">View Details</a>
                            </div>
                            
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    
                </div>
			</div>
            <div class="row">
                <div class="col-lg-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> Lead Trend
                            <span id="linetrendlegend" style="float:right;font-weight:bolder"></span>
                            
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="morris-line-chart"></div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    
                   


                </div>
                <div class="col-lg-4">
                    
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> Current Month Lead/Indent Value
                            
                        </div>
                        <div class="panel-body">
                        <div class="row">
                                <div class="col-lg-12">
                            <div id="morris-donut-chart-val" style="height:100%;width:100%;display:inline-block;"></div>
                            <span id="donutvallegend"></span>
                            <a href="MainController?action=listLead&search=All" class="btn btn-default btn-block">View Details</a>
                            </div>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    
                </div>
                
            </div>
            
            <!-- Chart End -->
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    
     <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>
   <!-- Morris Charts JavaScript -->
   

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
   

 	<script src="bower_components/raphael/raphael-min.js"></script>
    <script src="bower_components/morrisjs/morris.min.js"></script>
    
    <script src="js/morris-data.js"></script>
    
    <input type="text" value="${leadDistribution}" id="leadDistribution" style="display:none"/>
    <input type="text" value="${indentDistribution}" id="indentDistribution" style="display:none"/>
   
    <input type="text" value="${leadAmtDistribution}" id="leadAmtDistribution" style="display:none"/>
    <input type="text" value="${indentAmtDistribution}" id="indentAmtDistribution" style="display:none"/>
     
 
    
    <c:forEach items="${leadList}" var="ldValue" varStatus="count" > 
																	
		<input type="text" id="k${count.count}" value="${ldValue}"  style="display:none"/>
	</c:forEach>
	
	 <c:forEach items="${indentList}" var="ldValue" varStatus="count"> 
																	
		<input type="text" id="m${count.count}" value="${ldValue}"  style="display:none"/>
	</c:forEach>
<script>
   function getDonutDataCount(leadCount,indentCount,l1,l2)
   {
  		donut_chart_data=[];
  		
  			donut_chart_data[0]={label:l1,
                          value:leadCount
                     };
                     donut_chart_data[1]={label:l2,
                          value:indentCount
                     };
                     
                    // alert(donut_chart_data);
                     
         return donut_chart_data;
   }
   function getDonutDataVal(leadVal,indentVal,l1,l2)
   {
  		donut_chart_data=[];
  		
  			
                     donut_chart_data[2]={label:l1,
                    		 value:leadVal
                        };
                     donut_chart_data[3]={label:l2,
                             value:indentVal
                        };
                   //  alert(donut_chart_data);
                     
         return donut_chart_data;
   }
   function getBarData()
   {
	   b_chart_data=[];
		var theMonths = ["Apr", "May","Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec","Jan", "Feb", "Mar"];
	   
	   for(i=0;i<theMonths.length;i++)
	   {
		   j=i+1;
		   xValue=document.getElementById('k'+j).value;
		   yValue=document.getElementById('m'+j).value;
		   b_chart_data[i]={
				   y:theMonths[i],
				   a:xValue,
				   b:yValue
				   
		   }
		  // document.write(theMonths[i]);
	   }
	   
	   return b_chart_data;
	   
   }
   function getAreaData()
   {
	   a_chart_data=[];
		var theMonths = ["Apr", "May","Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec","Jan", "Feb", "Mar"];
	   
		xValue=parseInt(document.getElementById('k1').value)+parseInt(document.getElementById('k2').value)+parseInt(document.getElementById('k3').value);
		yValue=parseInt(document.getElementById('m1').value)+parseInt(document.getElementById('m2').value)+parseInt(document.getElementById('m3').value);
		a_chart_data[0]={
					   'period':'2015 Q1',
					   Lead:xValue,
					  
					   
			   };
			 xValue=parseInt(document.getElementById('k4').value)+parseInt(document.getElementById('k5').value)+parseInt(document.getElementById('k6').value);
			yValue=parseInt(document.getElementById('m4').value)+parseInt(document.getElementById('m5').value)+parseInt(document.getElementById('m6').value);
			a_chart_data[1]={
					   'period':'2015 Q2',
					   Lead:xValue,
					  
					   
			   };
			   xValue=parseInt(document.getElementById('k7').value)+parseInt(document.getElementById('k8').value)+parseInt(document.getElementById('k9').value);
		yValue=parseInt(document.getElementById('m7').value)+parseInt(document.getElementById('m8').value)+parseInt(document.getElementById('m9').value);
		a_chart_data[2]={
					   'period':'2015 Q3',
					   Lead:xValue,
					   
					   
			   };
			   xValue=parseInt(document.getElementById('k10').value)+parseInt(document.getElementById('k11').value)+parseInt(document.getElementById('k12').value);
		yValue=parseInt(document.getElementById('m10').value)+parseInt(document.getElementById('m11').value)+parseInt(document.getElementById('m12').value);
		a_chart_data[3]={
					   'period':'2015 Q4',
					   Lead:xValue,
					  
					   
			   };
	   
	   return a_chart_data; 
	   
   }
   
   var dDataCount=getDonutDataCount(document.getElementById("leadDistribution").value,document.getElementById("indentDistribution").value,"Lead Count","Indent Count");
   
   var dDataVal=getDonutDataCount(document.getElementById("leadAmtDistribution").value,document.getElementById("indentAmtDistribution").value,"Lead Value","Indent Value");
   
   var bData=getBarData();
   
   var aChartData=getAreaData();
   
   </script>
   <style>
    #barlegend {
        float:right;
        
    }
    #barlegend span {
        display: inline-block;
        
        position: relative;
    }
    #barlegend span:after {
        padding-left: 4px;
        content: '\00a0\00a0\00a0\00a0\00a0\00a0';
    text-decoration: line-through;
    }
</style>
   
    <script type="text/javascript">
    $(function () {
    	chart=Morris.Bar({
    		barColors :[ '#3A89C9','#5cb85c'],
            element: 'morris-bar-chart',
            data: bData,
            xkey: 'y',
            ykeys: ['a', 'b'],
            labels: ['Lead', 'Indent'],
            hideHover: 'auto',
            parseTime:false,
            resize: true
        });
    	chart1=Morris.Donut({
    		colors :[ '#3A89C9','#5cb85c'] ,
            element: 'morris-donut-chart-count',
            
            labels: ['Lead','Indent'],
            data: dDataCount,
            parseTime:false,
            resize: true
        });
    	
    	chart2=Morris.Donut({
    		colors :[ '#3A89C9','#5cb85c'] ,
            element: 'morris-donut-chart-val',
            
            data: dDataVal,
            labels: ['Lead','Indent'],
            parseTime:false,
            resize: true
        });
    	
    	chart3=Morris.Line({
            element: 'morris-line-chart',
            lineColors: ['green'],
            data: aChartData,
            xkey: 'period',
            ykeys: ['Lead'],
            labels: ['Lead'],
            parseTime:false,
            pointSize: 2,
            hideHover: 'auto',
            resize: true,
            continuousLine: false
        });
    	
    	chart.options.labels.forEach(function(label, i){
    	    var legendItem = $('<span></span>').text(label).css('color', chart.options.barColors[i])
    	    $('#barlegend').append(legendItem);
    	    
    	   
    	});
    	chart3.options.labels.forEach(function(label, i){
    	    var legendItem1 = $('<span></span>').text(label).css('color', chart3.options.lineColors[i])
    	    $('#linetrendlegend').append(legendItem1)
    	});
    	
    	chart1.options.labels.forEach(function(label, i){
    	    var legendItem1 = $('<span></span>').text(label).css('color', chart1.options.colors[i])
    	    $('#donutcountlegend').append(legendItem1);
    	    
    	    
    	});
    	chart2.options.labels.forEach(function(label, i){
    	    var legendItem1 = $('<span></span>').text(label).css('color', chart2.options.colors[i])
    	    $('#donutvallegend').append(legendItem1);
    	    
    	    
    	});
           
    });
</script>
<style>
    #donutcountlegend {
        height: 50px;
       
    }
    #donutcountlegend span {
        display: inline-block;
        padding: 15px 30px;
        position: relative;
    }
    #donutcountlegend span:after {
        padding-left: 4px;
        content: '\00a0\00a0\00a0\00a0\00a0\00a0';
    text-decoration: line-through;
    }
    
    #linetrendlegend {
       
       
    }
    #linetrendlegend span {
        display: inline-block;
        
        position: relative;
    }
    #linetrendlegend span:after {
        padding-left: 4px;
        content: '\00a0\00a0\00a0\00a0\00a0\00a0';
    text-decoration: line-through;
    }
    
	#donutvallegend {
        height: 50px;
        
    }
    #donutvallegend span {
        display: inline-block;
        padding: 15px 30px;
        position: relative;
    }
    #donutvallegend span:after {
        padding-left: 4px;
        content: '\00a0\00a0\00a0\00a0\00a0\00a0';
    text-decoration: line-through;
    }
</style>
 <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
       
        
    });
    </script>

</body></html>
    
     