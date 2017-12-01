<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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

<body>


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

            <!--/.nav-collapse -->

        </div>
        <!-- /.container -->
    </div>
    <!-- /#navbar -->

    <!-- *** NAVBAR END *** -->

    <div id="all">

        <div id="content">
            <div class="container">

                <div class="col-md-12">
                    <ul class="breadcrumb">
                        <li><a href="/home" >Home</a>
                        </li>
                        <li>Shopping cart</li>
                    </ul>
                </div>

                <div class="col-md-9" id="basket">

                    <div class="box">

                        <form method="post" action="orderPrepare">

                            <h1>Shopping cart</h1>
                            <p class="text-muted">You currently have following item(s) in your cart.</p>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                         <th>Image</th>  
                                            <th>Product</th>                             
                                            <th>Unit price</th>
                                        </tr>
                                    </thead>
                                    <tbody>                                    
                                   <c:forEach items="${obj1}" var="item">
                                   
                                        <tr>
                                            <td>
                                                <a href="#">
                                                    <img src="<c:url value="/resources/img/product${item.value.id}.jpg"/>" alt="" class="img-responsive">
                                                </a>
                                            </td>
                                            <td><a href="#"><c:out value="${item.value.title}"/> </a>
                                            </td>
                                            <td><c:out value="${item.value.price}"/></td>

                                            <td><a href="deletefromcart.html?id=${item.value.id}"><i class="fa fa-trash-o"></i></a>
                                            </td>
                                        </tr>
                                     </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th colspan="5">Total</th>
                                            <th colspan="2">                   
                                            <c:forEach items="${obj1}" var="item">
                                             <c:set var="total" value="${total+item.value.price}" />                                              
                                            </c:forEach>
                                            <c:out value="$${total}" />
                                            </th>
                                        </tr>
                                    </tfoot>
                                </table>

                            </div>
                            <!-- /.table-responsive -->

                            <div class="box-footer">
                                <div class="pull-left">
                                    <a href="/home" class="btn btn-default"><i class="fa fa-chevron-left"></i> Continue shopping</a>
                                </div>
                                <div class="pull-right">                                   
                                    <button type="submit" name="total" value="${total}" class="btn btn-primary" ${obj1.size() == null ? 'disabled' : 'enabled'}>Proceed to checkout <i class="fa fa-chevron-right"></i>
                                    </button>
                                </div>
                            </div>

                        </form>

                    </div>
                    <!-- /.box -->


                 
                </div>
                <!-- /.col-md-9 -->

                <div class="col-md-3">
              
                </div>
                <!-- /.col-md-3 -->

            </div>
            <!-- /.container -->
        </div>
        </div>
        <!-- /#content -->

        <!-- *** FOOTER ***
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
                    <p class="pull-left">© 2017 Codefellas</p>

                </div>
                <div class="col-md-6">
                    <p class="pull-right">Template by <a href="https://bootstrapious.com/e-commerce-templates">Bootstrapious</a> & <a href="https://fity.cz">Fity</a>
                         <!-- Not removing these links is part of the license conditions of the template. Thanks for understanding :) If you want to use the template without the attribution links, you can do so after supporting further themes development at https://bootstrapious.com/donate  -->
                    </p>
                </div>
            </div>
        </div>
        <!-- *** COPYRIGHT END *** -->
    <!-- /#all -->
    

    <!-- *** SCRIPTS TO INCLUDE ***
 _________________________________________________________ -->
    <script src="<c:url value="/resources/js/jquery-1.11.0.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
    <script src="<c:url value="/resources/js/jquery.cookie.js" />" /></script>
    <script src="<c:url value="/resources/js/waypoints.min.js" />" ></script>
    <script src="<c:url value="/resources/js/modernizr.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap-hover-dropdown.js" />"></script>
    <script src="<c:url value="/resources/js/owl.carousel.min.js" />"></script>
    <script src="<c:url value="/resources/js/front.js" />"></script>



</body>

</html>