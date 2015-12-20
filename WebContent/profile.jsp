
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>





<div id="page-wrapper">
        
             <div class="row" >
			<div class="col-lg-12" style="margin-top:5px">
	           <ol class="breadcrumb">
				 <li><a href="index.jsp"><i class="fa fa-home fa-fw"></i> Home</a></li>
				  <li><a href="#"><i class="fa fa-user fa-fw"></i> Profile</a></li>
				
				  
				  
				</ol>
			</div>
		</div>
            <!-- /.row -->
			<div class="row">
			<div class="col-lg-12">
				 <div class="panel panel-primary">
						
						<a data-toggle="collapse" href="#collapse1" style="text-decoration:none;" >
						<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
                            
							User Details 
                        </div>
						</a>
                        
						<div id="collapse1" class="panel-collapse collapse in">
						
						<c:forEach items="${userlist}" var="userl">
						<div class="panel-body">
							
                                        
									
									<div class="row">
										
										
										<div class="col-lg-4">
											<div class="form-group">
												<label >User ID</label>
												<input type="text" readonly value="${userl.USER_ID}"  class="form-control"/>
											</div>
										</div>
										
										<div class="col-lg-4">
											<div class="form-group">
												<label >User Name</label>
												<input type="text" readonly value="${userl.USER_NAME}"  class="form-control"/>
											</div>
										</div>
										<div class="col-lg-4">
											<div class="form-group">
												<label >Role Name</label>
												<input type="text" readonly value="${userl.ROLE_NAME}"  class="form-control"/>
											</div>
										</div>
										
										
									</div>
									
									<div class="row">
									
									<div class="col-lg-4">
											<div class="form-group">
												<label >Default Region</label>
												<input type="text" readonly value="${userl.REGION_TYPE}"  class="form-control"/>
											</div>
										</div>
									
									<div class="col-lg-4">
											<div class="form-group">
												<label >Default State</label>
												<input type="text" readonly value="${userl.EXE_STATE}"  class="form-control"/>
											</div>
										</div>
									
									<div class="col-lg-4">
											<div class="form-group">
												<label >Default City</label>
												<input type="text" readonly value="${userl.EXE_CITY}"  class="form-control"/>
											</div>
										</div>
									</div>
									</div>
										
                                        
                                        
										
                   	
					</div>
					
					
					 </c:forEach>
					
					</div>
					
					</div>
					
					
					
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
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
       
        
    });
    </script>
</body></html>