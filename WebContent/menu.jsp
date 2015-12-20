            <div class="navbar-default sidebar" role="navigation" id="side-menu">
                <div class="sidebar-nav navbar-collapse" >
                    <ul class="nav in">
                        
                        <li>
                            <a href="LoginController"><i class="fa fa-home fa-fw"></i> Home </a>
                        </li>
                       
                        
                        <li>
                            <a href="#"><i class="fa fa-dashboard fa-fw"></i> Lead & Enquiry <span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level collapse">
                                
                                
                                <li>
                                    <a href="#">Create Lead <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level collapse">
                                        <li>
                                            <a href="MainController?action=creatlead&businessFile=DG">DG</a>
                                        </li>
                                        <li>
                                            <a href="MainController?action=creatlead&businessFile=PN">Panel</a>
                                        </li>
                                        
                                    </ul>
                                    
                                </li>
                                
                                
                                <li>
                                   <a href="MainController?action=listLead&search=All">View Leads</a>
                                </li>
                               
                                
                                
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                         <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i>Budgetory Lead & Enquiry <span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level collapse">
                                
                                
                                <li>
                                    <a href="#">Create Budgetory Lead <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level collapse">
                                        <li>
                                            <a href="MainController?action=creatbudgetlead&businessFile=DG">DG</a>
                                        </li>
                                        <li>
                                            <a href="MainController?action=creatbudgetlead&businessFile=PN">Panel</a>
                                        </li>
                                        
                                    </ul>
                                    
                                </li>
                                
                                
                                <li>
                                   <a href="MainController?action=listBudgetLead&search=All">View Budgetory Leads</a>
                                </li>
                                
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                        <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> Indent<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level collapse">
                                <li>
                                     <a href="MainController?action=listQualifiedLead&search=All">Create Indent</a>
                                </li>
                                <li>
                                    <a href="MainController?action=listIndent&search=All">View Indents</a>
                                </li>
                                <li style="display:none">
                              
                                    <a href="MainController?action=createIndent&search=All">View Indents</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> Engine Budget Planning<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level collapse">
                                
                               					 <c:choose>
															<c:when test="${lroleId=='RSM'}">
																<li>
								                                     <a href="StockController?action=createStock">Add Stock Budget</a>
								                                </li>
								                                
								                                <li>
								                                    <a href="StockController?action=listBudgetStock&search=All">View Stock</a>
								                                </li> 
															</c:when>
															<c:when test="${lroleId=='BH'}">
																<li>
								                                     <a href="StockController?action=createStock">Add Stock Budget</a>
								                                </li>
								                                
								                                <li>
								                                    <a href="StockController?action=listBudgetStock&search=All">View Stock</a>
								                                </li> 
															</c:when>  
															   
															<c:otherwise>
																<li>
								                                    <a href="StockController?action=listBudgetStock&search=All">View Stock</a>
								                                </li> 
															</c:otherwise>
														</c:choose>
                                
                                
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                     					  <c:choose>
															<c:when test="${lroleId=='RSM'}">
																<li>
											                            <a href="#"><i class="fa fa-files-o fa-fw"></i>FFS<span class="fa arrow"></span></a>
											                            <ul class="nav nav-second-level collapse">
											                                <li>
											                                     <a href="FFSController?action=createFFS">Add FFS Report</a>
											                                </li>
											                                
											                                
											                            </ul>
											                         
											                        </li> 
															</c:when>
															<c:when test="${lroleId=='BH'}">
																<li>
											                            <a href="#"><i class="fa fa-files-o fa-fw"></i>FFS<span class="fa arrow"></span></a>
											                            <ul class="nav nav-second-level collapse">
											                                <li>
											                                     <a href="FFSController?action=createFFS">Add FFS Report</a>
											                                </li>
											                                
											                                
											                            </ul>
											                         
											                        </li> 
															</c:when>  
															   
															<c:otherwise>
																
															</c:otherwise>
														</c:choose>
                       
                        
                        	
					 <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Reports<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level collapse">
                               
                                <li>
                                    <a href="StockController?action=listFFS&search=All">FFS Report</a>
                                </li>
                                <li>
                                    <a href="ReportController?reportType=OrderBoardReport&action=all">Order Board Report</a>
                                </li>
								 <li>
                                    <a href="ReportController?reportType=ProjectReport&action=all">Project Report</a>
                                </li>
								 <li>
                                    <a href="ReportController?reportType=QuoteAnalysis&action=all">Quote Analysis Report</a>
                                </li>
                                <li>
                                    <a href="ReportController?reportType=EnquiryAnalysis&action=all">Enquiry Bank Analysis Report</a>
                                </li>
								 <li>
                                    <a href="ReportController?reportType=OrderLostReport&action=all">Order Loss Report</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
											 <c:choose>
															<c:when test="${lroleId=='RSM'}">
																<li>
										                            <a href="WorkingReport?action=all"><i class="fa fa-bar-chart-o fa-fw"></i> Upload Report </a>
										                           
										                        </li> 
															</c:when>
															<c:when test="${lroleId=='BH'}">
																<li>
										                            <a href="WorkingReport?action=all"><i class="fa fa-bar-chart-o fa-fw"></i> Upload Report </a>
										                           
										                        </li>
															</c:when>  
															   
															<c:otherwise>
																
															</c:otherwise>
														</c:choose>
						
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>