<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sterling LMS & Inventory System</title>

    <!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div class="container">
	<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0;border-color:#f8f8f8 !important">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="login.html"><img src="dist/images/sterling_logo.png" ></a>
            </div>
            <!-- /.navbar-header -->




        </nav>
        
		<div class="row" >
		
            <div class="col-md-4 col-md-offset-4" >
            
            
            <c:if test="${not empty message}">
				    <div class="panel panel default" style="margin-bottom:0% !important" id="loginmessage">
                
                    <div class="panel-heading">
                       
                        <h3 class="panel-title">${message}</h3>
                        
                    </div>
                    
             </div>
			</c:if>
            
            
                <div class="login-panel panel panel-primary" style="margin-bottom:0% !important">
                
                    <div class="panel-heading">
                       
                        <h3 class="panel-title">Sterling LMS & Inventory System</h3>
                        
                    </div>
                    
                </div>
            </div>
        </div>
        <div class="row" id="login">
            <div class="col-md-4 col-md-offset-4">
			
                <div class="login-panel panel panel-default" style="margin-top:0% !important">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form  action="LoginController" method="post" data-toggle="validator" role="form" novalidate="true">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email" type="email"  required>
                                    <div class="help-block with-errors" style="margin-top:0px" ></div>
                                    
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" required>
                                    <div class="help-block with-errors" style="margin-top:0px" ></div>
                                </div>
                                <input type="hidden" name="action" value="login">
                                <!-- Change this to a button or input when using this as a form -->
                                <button type="submit" value="Submit" class="btn btn-lg btn-success btn-block">Submit</button>
                                
                                 <a href="#" class="form-control" style="text-decoration:underline;text-align:center" onclick="return showForgotPasswordPage('forgot')">Forgot Password ? Click Here</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="forgot" style="display:none">
            <div class="col-md-4 col-md-offset-4">
			
                <div class="login-panel panel panel-default" style="margin-top:0% !important">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please enter your email address</h3>
                    </div>
                    <div class="panel-body">
                        <form  action="LoginController" method="post" data-toggle="validator" role="form" novalidate="true">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email" type="email"  required>
                                    <div class="help-block with-errors" style="margin-top:0px" ></div>
                                </div>
                                <input type="hidden" name="action" value="forgotpassword">
                                <!-- Change this to a button or input when using this as a form -->
                                <button type="submit" value="Submit" class="btn btn-lg btn-success btn-block">Submit</button>
                                
                                 <a href="#" class="form-control" style="text-decoration:underline;text-align:center" onclick="return showForgotPasswordPage('login')">Click Here to Login</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script>
	function showForgotPasswordPage(s)
	{
		if(s=="forgot"){
		document.getElementById("login").style.display='none';
		document.getElementById("forgot").style.display='block';
		document.getElementById("loginmessage").style.display='none';
		
		}
		else
			{
			document.getElementById("login").style.display='block';
			document.getElementById("forgot").style.display='none';
			document.getElementById("loginmessage").style.display='none';
			
			
			}
	}
</script>
    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    <script src="dist/js/validator.js"></script>

</body>

</html>
