		<!-- Tag Lib Directories to eliminate the JSP Directives -->
		
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header">Lead Details</h4>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
			<div class="row">
			<div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Create Lead
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#home" data-toggle="tab" aria-expanded="false">Main</a>
                                </li>
								<li class=""><a href="#itemDetails" data-toggle="tab" aria-expanded="false">Item Details</a>
                                </li>
                                <li class=""><a href="#profile" data-toggle="tab" aria-expanded="false">Customer</a>
                                </li>
                                
                                <li class=""><a href="#settings" data-toggle="tab" aria-expanded="false">Comments and Attachment</a>
                                </li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div class="tab-pane fade active in" id="home">
                                    <h4>Lead details</h4>
								<hr />
                                <form method="POST" action="/sdnext/createNewhtml">
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="leadID">Lead ID</label>
												<input type="text" path="leadID" value="${leadID}" class="form-control" readonly="true"/>
												<errors path="leadID" cssClass="error"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label>Lead Type</label>
                                            	<select  class="form-control">
                                            		<option value="0">Select Lead Type</option>
													<c:forEach items="${projectTypes}" var="projectType"> 
														<option value="${projectType}">${projectType}</option>
											    	</c:forEach>
						                    	</select>
						                    	<errors path="leadType" cssClass="error"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
											<label path="projectExeState">Execution State</label>
											 <select path="projectExeState" id="projectExeState" value="${projectExeState}" name="projectExeState" class="form-control">
											 	<option value="0">Select State</option>
												<c:forEach items="${exe_state}" var="states"> 
														<option value="${states}">${states}</option>
											    	</c:forEach>
						                    </select>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="projectExePlace">Execution City</label>
	                                            <select path="projectExePlace" id="projectExePlace" value="${projectExePlace}" name="projectExePlace" class="form-control">
	                                            	<option value="0">Select City</option>
													<c:forEach items="${exe_city}" var="city"> 
														<option value="${city}">${city}</option>
												    </c:forEach>
							                    </select>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="dgType">DG Type</label>
												<select path="dgType" id="dgType" name="dgType"  class="form-control">
													<option value="0">Select DG Type</option>
													<c:forEach items="${dgTypes}" var="dgType"> 
														<option value="${dgType}">${dgType}</option>
											    	</c:forEach>
						                    	</select>											
											</div>
										</div>
										<div class="col-lg-9">
											<div class="form-group">
												<label path="projectReference">Project Reference</label>
												<input path="projectReference" value="${projectReference}" class="form-control" />
												<errors path="projectReference" cssClass="error"/>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="segment">Segment</label>
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
												<label path="transactionType">Transaction Type</label>
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
											<label path="enquiryType">Enquiry Type</label>
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
												<label path="leadStatus">Status</label>
												<select path="leadStatus" id="leadStatus" value="${leadStatus}" name="leadStatus" class="form-control">
													<option value="0">Select Status</option>
													<c:forEach items="${statusType}" var="stype"> 
														<option value="${stype}">${stype}</option>
											    	</c:forEach>
						                    	</select>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerID">Customer </label>
												<input path="customerID" type="text" value="${customerID}" id="customerID" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="companyID">Consultant </label>
												<input path="companyID" type="text" id="companyID" value="${companyID}" class="form-control" />
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="pmc">PMC </label>
												<input path="pmc" type="text" id="pmc" value="${pmc}" class="form-control" />
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="competitions">Competition</label>
												<input path="competitions" type="text" value="${competitions}" id="competitions" class="form-control"/>
											</div>
										</div>
									</div>	
									
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="ownerID">Owner </label>
												<input path="ownerID" type="text" id="ownerID" value="${ownerID}" class="form-control" />
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="createdBy">Created By </label>
												<input path="createdBy" type="text" id="createdBy" value="${createdBy}" class="form-control" />
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="leadChances">Chances</label>
												<input path="leadChances" type="text" id="leadChances" value="${leadChances}" class="form-control" />
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="creationDate">Created Date</label>
												<input path="creationDate" type="text" id="creationDate" value="${creationDate}" class="form-control" readonly="true"/>
											</div>
										</div>
									</div>										
									
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="lowsideSupplyBasic">LowSide Supply Basic</label>
												<input path="lowsideSupplyBasic" type="text" id="lowsideSupplyBasic" value="${lowsideSupplyBasic}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="lowsideLabourBasic">Lowside Labour-Basic</label>
												<input path="lowsideLabourBasic" type="text" id="lowsideLabourBasic" value="${lowsideLabourBasic}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="lowsideMargin">Lowside Margin</label>
												<input path="lowsideMargin" type="text" id="lowsideMargin" value="${lowsideMargin}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="projectMargin">Project Margin</label>
												<input path="projectMargin" type="text" id="projectMargin" value="${projectMargin}" class="form-control"/>
											</div>
										</div>
									</div>	
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<label path="dgValueWithED">DG Value With ED</label>
												<input path="dgValueWithED" type="text" id="dgValueWithED" value="${dgValueWithED}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label path="projectTotal">Project Total</label>
												<input path="projectTotal" type="text" id="projectTotal" value="${projectTotal}" class="form-control"/>
											</div>
										</div>
									</div>
									<div align="middle"><input type="submit" value="Submit" id="submitButton" class="btn btn-success"/></div>
								</form>
                            </div>
                                
                            <div class="tab-pane fade" id="profile">
                            <h4>Customer details</h4>
							<hr />
                                <form method="POST" action="/sdnext/createLeadCustomer.html">
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="leadID">Lead ID</label>
												<input type="text" path="leadID" value="${leadID}" class="form-control" readonly="true"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerType">Customer Type</label>
												<select path="customerType" id="customerType" name="customerType" value="${customerType}" class="form-control">
														<c:forEach items="${customerType}" var="customerType">
															<option value="${customerType}">${customerType}</option>
												    	</c:forEach>
							                    </select>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerName">Customer Name</label>
												<input type="text" path="customerName" value="${customerName}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerContactNumber">Contact Number</label>
												<input type="text" path="customerContactNumber" value="${customerContactNumber}" class="form-control"/>												
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerAlternateNumber">Alternate Number</label>
												<input type="text" path="customerAlternateNumber" value="${customerAlternateNumber}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerEmail">Email</label>
												<input type="text" path="customerEmail" value="${customerEmail}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerConsultant">Consultant</label>
												<input type="text" path="customerConsultant" value="${customerConsultant}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerConsultantNumber">Consultant Number</label>
												<input type="text" path="customerConsultantNumber" value="${customerConsultantNumber}" class="form-control"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerBillingAddress1">Billing Address1</label>
												<input type="text" path="customerBillingAddress1" value="${customerBillingAddress1}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerBillingAddress2">Billing Address2</label>
												<input type="text" path="customerBillingAddress2" value="${customerBillingAddress2}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerBillingAddress3">Billing Address3</label>
												<input type="text" path="customerBillingAddress3" value="${customerBillingAddress3}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerBillingAddress4">Billing Address4</label>
												<input type="text" path="customerBillingAddress4" value="${customerBillingAddress4}" class="form-control"/>
											</div>
										</div>
									</div>		
									<div class="row">
										<div class="col-lg-12">
											<div class="form-group">
											<input type="checkbox" path="customerAddressCheckbox" />
											<label  path="customerAddressCheckbox">Delivery Address is Same As Billing Address</label>
											</div>
										</div>
									</div>	
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerDeliveryAddress1">Delivery Address1</label>
												<input type="text" path="customerDeliveryAddress1" value="${customerDeliveryAddress1}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerDeliveryAddress2">Delivery Address2</label>
												<input type="text" path="customerDeliveryAddress2" value="${customerDeliveryAddress2}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerDeliveryAddress3">Delivery Address3</label>
												<input type="text" path="customerDeliveryAddress3" value="${customerDeliveryAddress3}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerDeliveryAddress4">Delivery Address4</label>
												<input type="text" path="customerDeliveryAddress4" value="${customerDeliveryAddress4}" class="form-control"/>
											</div>
										</div>
									</div>	
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerECCNO">ECCNO</label>
												<input type="text" path="customerECCNO" value="${customerECCNO}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerCSTNO">CSTNO</label>
												<input type="text" path="customerCSTNO" value="${customerCSTNO}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerTINNO">TINNO</label>
												<input type="text" path="customerTINNO" value="${customerTINNO}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="customerIECNO">IECNO</label>
												<input type="text" path="customerIECNO" value="${customerIECNO}" class="form-control"/>
											</div>
										</div>
									</div>	
									<div class="row">
										<div class="col-lg-3">
											<div class="form-group">
												<label path="pmc">PMC</label>
												<input type="text" path="pmc" value="${pmc}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="pmcContact">PMC CONTACT</label>
												<input type="text" path="pmcContact" value="${pmcContact}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="pmcAlternateContact">PMC ALTERNATE CONTACT</label>
												<input type="text" path="pmcAlternateContact" value="${pmcAlternateContact}" class="form-control"/>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label path="pmcEmail">PMC EMAIL</label>
												<input type="text" path="pmcEmail" value="${pmcEmail}" class="form-control"/>
											</div>
										</div>
									</div>
									<div align="middle"><input type="submit" value="Submit" id="submitButton" class="btn btn-success"/></div>
									</form>
                                </div>
								<div class="tab-pane fade" id="itemDetails">
                                	<h4>Item Details</h4>
                                	<form method="POST" action="/sdnext/createLeadItems.html">
									<div class="row">
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
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="leadID">Lead ID</label>
																			<input type="text" path="leadID" value="${leadID}" class="form-control" readonly="true"/>
																		</div>
																	</div>
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="itemEngineType">Type</label>
																			<select path="itemEngineType" value="${itemEngineType}" class="form-control">
																				<option value="0">Select Type</option>
																				<option value="Engine">Engine</option>
																				<option value="Alternator">Alternator</option>
																			</select>
																		</div>
																	</div>
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="itemRatingType">Rating</label>
																			<select path="itemRatingType" id="itemRatingType" name="itemRatingType" value="${itemRatingType}" class="form-control">
																				<option value="0">Select Rating</option>
																				<c:forEach items="${ratingTypes}" var="itemRatingType"> 
																					<option value="${itemRatingType}">${itemRatingType}</option>
																		    	</c:forEach>
													                    	</select>
																		</div>
																	</div>
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="itemModelNo">Model No</label>
																			<input type="text" path="itemModelNo" value="${itemModelNo}" class="form-control"/>
																		</div>
																	</div>
																</div>
																
																<div class="row">
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="itemQty">Qty</label>
																			<select path="itemQty" id="itemQty" name="itemQty" value="${itemQty}" class="form-control">
																				<option value="0">Select Qty</option>
																				<c:forEach items="${qtyType}" var="itemQty"> 
																					<option value="${itemQty}">${itemQty}</option>
																		    	</c:forEach>
													                    	</select>
																		</div>
																	</div>
																	<div class="col-lg-3">
																		<div class="form-group">
																			<label path="itemAmount">Amount (In Lakh)</label>
																			<input type="text" path="itemAmount" value="${itemAmount}" class="form-control"/>
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
																<div align="middle"><input type="submit" value="Submit" id="submitButton" class="btn btn-success"/></div>
			                                        </div>
			                                    </div>
	                                		</div>
										</div>
	                                </div>
									</form>
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
							                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
							                                    <thead>
							                                        <tr>
							                                            <th>Rating</th>
							                                            <th>Engine Make</th>
							                                            <th>Engine Model</th>
							                                            <th>Voltage</th>
							                                            <th>Qty</th>
																		<th>Value (In Lakhs)</th>
							                                        </tr>
							                                    </thead>
															   <tbody>
							                                        <tr class="odd gradeX">
							                                            <td>Trident</td>
							                                            <td>Internet Explorer 4.0</td>
							                                            <td>Win 95+</td>
							                                            <td class="center">4</td>
							                                            <td class="center">X</td>
																		<td>1,0000</td>
							                                        </tr>
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
                                    <h4>Comments  & Attachement</h4>
									<hr />
									
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
														<form method="post" action="/sdnext/createLeadComment.html" enctype="multipart/form-data">
															<div class="row">
																<div class="col-lg-6">
																	<div class="form-group">
																		<label path="comments">Comments</label>
																		<textarea path="comments" value="${comments}" class="form-control" rows="3"></textarea>
																	</div>
																</div>
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="file">Attachment</label>
																		<input path="file" value="${file}" type="file" />
																	</div>
																</div>
																<div class="col-lg-3">
																	<div class="form-group">
																		<button type="submit" name="upload" class="btn btn-success">Upload</button>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-lg-3">
																	<div class="form-group">
																		<label path="leadID">Lead ID</label>
																		<input type="text" path="leadID" value="${leadID}" class="form-control" readonly="true"/>
																	</div>
																</div>
															</div>
															<div align="middle"><input type="submit" value="Submit" id="submitButton" class="btn btn-success"/></div>
														</form>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-lg-12">
										<div class="panel panel-primary">
		                                    <a data-toggle="collapse" href="#collapseThree" style="text-decoration:none"  >
													<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
														Comments
													</div>
												</a>
		                                    <div id="collapseThree" class="panel-collapse collapse" aria-expanded="false" >
		                                        <div class="panel-body">
													<div class="dataTable_wrapper">
						                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
						                                    <thead>
						                                        <tr>
						                                            <th>ID</th>
						                                            <th>Comments</th>
						                                            <th>Created By</th>
						                                            <th>Created Date</th>
						                                        </tr>
						                                    </thead>
														   <tbody>
						                                        <tr class="odd gradeX">
						                                            <td>1</td>
						                                            <td>Data Attached</td>
						                                            <td>Admin</td>
																	 <td>2015-01-01</td>
						                                        </tr>
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
		                                    <a data-toggle="collapse" href="#collapseFour" style="text-decoration:none"  >
												<div class="panel-heading" style="color: #fff; background-color: #337ab7;border-color: #337ab7;">
													Attachments
												</div>
											</a>
		                                    <div id="collapseFour" class="panel-collapse collapse" aria-expanded="false" >
		                                        <div class="panel-body">
													<div class="dataTable_wrapper">
						                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
						                                    <thead>
						                                        <tr>
						                                            <th>ID</th>
						                                            <th>File Name</th>
						                                            <th>Created By</th>
						                                            <th>Created Date</th>
						                                        </tr>
						                                    </thead>
														   <tbody>
						                                        <tr class="odd gradeX">
						                                            <td>1</td>
						                                            <td><a href="#">Test.XLS</a></td>
						                                            <td>Admin</td>
						                                            
																	 <td>2015-01-01</td>
						                                        </tr>
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
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>
   <!-- Morris Charts JavaScript -->
   

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    
    </script>

</body></html>