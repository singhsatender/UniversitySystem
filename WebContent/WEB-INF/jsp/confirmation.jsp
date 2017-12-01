 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="robots" content="all,follow">
    <meta name="googlebot" content="index,follow,snippet,archive">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Obaju e-commerce template">
    <meta name="author" content="Ondrej Svestka | ondrejsvestka.cz">
    <meta name="keywords" content="">
    
    <title>
        Musify.com
    </title>

    <meta name="keywords" content="">

    <link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100' rel='stylesheet' type='text/css'>
       <!-- styles -->
    <link href="<c:url value="/resources/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.min.css" />"  rel="stylesheet">
    <link href="<c:url value="/resources/css/owl.carousel.css" />"  rel="stylesheet">
    <link href="<c:url value="/resources/css/owl.theme.css" />"  rel="stylesheet">

    <!-- theme stylesheet -->
    <link href="<c:url value="/resources/css/style.default.css" />" rel="stylesheet" id="theme-stylesheet">

    <!-- your stylesheet with modifications -->
    <link href="<c:url value="/resources/css/custom.css" />" rel="stylesheet">

    <script src="<c:url value="/resources/js/respond.min.js" /> "></script>

    <link rel="shortcut icon" href="favicon.png">


</head>
<body onload='document.address.shippingAdd.focus()'>

   <!-- *** NAVBAR ***
 _________________________________________________________ -->

    <div class="navbar navbar-default yamm" role="navigation" id="navbar">
        <div class="container">
            <div class="navbar-header">

                <a class="navbar-brand home" href="/home" data-animate-hover="bounce">
                   <img src="<c:url value="/resources/img/Musify-logo.png"/>" alt="CD Store logo" class="hidden-xs">
                   <img src="<c:url value="/resources/img/Musify-logo-small.png"/>" alt="CD Storelogo" class="visible-xs"><span class="sr-only">go to homepage</span>
               </a>
            </div>
            <!--/.navbar-header -->

            <div class="navbar-collapse collapse" id="navigation">

                <ul class="nav navbar-nav navbar-left">
                    <li class="active"><a href="/home">Home</a>
                    </li>
                </ul>

            </div>
            <!--/.nav-collapse -->

        </div>
        <!-- /.container -->
    </div>
    <!-- /#navbar -->

    <!-- *** NAVBAR END *** -->

 <div id="all">

        <div id="content">
            <div class="container">
                <div class="col-md-9" id="checkout">

                    <div class="box">
                        <form name="address" method="post" action="confirmPayment" onsubmit="return validateaddressForm();">
                            <h1>Checkout -Confirm</h1>                               
                            <ul class="nav nav-pills nav-justified">
                                <li class="active"><a href="#"><i class="fa fa-map-marker"></i><br>Address</a>
                                </li>
                                <li class="disabled"><a href="#"><i class="fa fa-money"></i><br>Payment</a>
                                </li>
                            </ul>

                            <div class="content">
                            <h2>Verify Your Address Details</h2>   
                                                     
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label  for="firstname">Shipping Address</label>
                                            <textarea name="shippingAdd" id="shippingAdd" class="form-control">${shippingInfo}</textarea>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.row -->

                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="form-group">
                                            <label for="billingAdd">Billing Address</label>
                                            <textarea class="form-control" name="billingAdd" id="billingAdd">${billingInfo}</textarea>
                                        </div>
                                    </div>                                   
                                </div>
                                <!-- /.row -->

                            </div>

                            <div class="box-footer">                             
                                <div class="pull-right">
                                    <button type="submit" class="btn btn-primary">Continue to Payment<i class="fa fa-chevron-right"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- /.box -->


                </div>
                <!-- /.col-md-9 -->


            </div>
            <!-- /.container -->
        </div>
        <!-- /#content -->

    <!-- *** FOOTER ***
 _________________________________________________________ -->
        <div id="footer" data-animate="fadeInUp">
            <div class="container">
                <div class="row">
                    
                    
                    <!-- /.col-md-3 -->

                    <div class="col-md-3 col-sm-6">

                        <h4>Where to find us</h4>

                        <p><strong>CodeFellas Group of Technologies</strong>
                            <br>170 Lees Avenue
                            <br>K1S 5G5
                            <br>Ottawa
                            <br>
                            <strong>Canada</strong>
                        </p>
                    </div>
                    <!-- /.col-md-3 -->



                    <div class="col-md-3 col-sm-6">

                        <h4>Stay in touch</h4>

                        <p class="social">
                            <a href="#" class="facebook external" data-animate-hover="shake"><i class="fa fa-facebook"></i></a>
                            <a href="#" class="twitter external" data-animate-hover="shake"><i class="fa fa-twitter"></i></a>
                            <a href="#" class="instagram external" data-animate-hover="shake"><i class="fa fa-instagram"></i></a>
                            <a href="#" class="gplus external" data-animate-hover="shake"><i class="fa fa-google-plus"></i></a>
                            <a href="#" class="email external" data-animate-hover="shake"><i class="fa fa-envelope"></i></a>
                        </p>


                    </div>
                    <!-- /.col-md-3 -->

                </div>
                <!-- /.row -->

            </div>
            <!-- /.container -->
        </div>
        <!-- /#footer -->

        <!-- *** FOOTER END *** -->



        <!-- *** COPYRIGHT ***
 _________________________________________________________ -->
        <div id="copyright">
            <div class="container">
                <div class="col-md-6">
                    <p class="pull-left">© 2015 Your name goes here.</p>

                </div>
                <div class="col-md-6">
                    <p class="pull-right">Template by <a href="https://bootstrapious.com/e-commerce-templates">Bootstrapious</a> & <a href="https://fity.cz">Fity</a>
                         <!-- Not removing these links is part of the license conditions of the template. Thanks for understanding :) If you want to use the template without the attribution links, you can do so after supporting further themes development at https://bootstrapious.com/donate  -->
                    </p>
                </div>
            </div>
        </div>
        <!-- *** COPYRIGHT END *** -->



    </div>
    <!-- /#all -->
    
      <!-- *** SCRIPTS TO INCLUDE ***
 _________________________________________________________ -->
    <script src="<c:url value="/resources/js/jquery-1.11.0.min.js" /> " ></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" /> " ></script>
    <script src="<c:url value="/resources/js/jquery.cookie.js" /> " ></script>
    <script src="<c:url value="/resources/js/waypoints.min.js" /> " ></script>
    <script src="<c:url value="/resources/js/modernizr.js" /> " ></script>
    <script src="<c:url value="/resources/js/bootstrap-hover-dropdown.js" /> " ></script>
    <script src="<c:url value="/resources/js/owl.carousel.min.js" /> " ></script>
    <script src="<c:url value="/resources/js/front.js" /> " ></script>
    <script src="<c:url value="/resources/js/homepage.js" /> " ></script>

</body>
</html>