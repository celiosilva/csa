<%@tag description="Main template for pages" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="CSA Showcase">
<meta name="author" content="delogic.com.br">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Bootstrap -->
<c:set var="root" value="${pageContext.request.contextPath}" scope="application" />
<!-- =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- -->
<!-- JQUERY FRAMEWORK-->
<script src="${root}/resources/js/jquery-1.10.2.min.js"></script>
<!-- =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- -->
<!-- BOOTSTRAP FRAMEWORK -->
<link href="${root}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen" />
<script src="${root}/resources/bootstrap/js/bootstrap.min.js"></script>
<!-- =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- -->
<!-- BOOTSTRAP DATEPICKER -->
<link href="${root}/resources/datepicker/datepicker.css" rel="stylesheet" />
<script src="${root}/resources/datepicker/bootstrap-datepicker_pt_BR.js"></script>
<!-- =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- -->
<!-- BOOTSTRAP JQUERY FILEUPLOAD -->
<script src="${root}/resources/jqueryfileupload/vendor/jquery.ui.widget.js"></script>
<script src="${root}/resources/jqueryfileupload/jquery.iframe-transport.js"></script>
<script src="${root}/resources/jqueryfileupload/jquery.fileupload.js"></script>
<!-- =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- -->
<!-- JQUERY INPUTMASK -->
<script src="${root}/resources/jqueryinputmask/js/jquery.inputmask.js"></script>
<script src="${root}/resources/jqueryinputmask/js/jquery.inputmask.numeric.extensions.js"></script>
<!-- =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- -->
<!-- JQUERY AUTOCOMPLETE -->
<link href="${root}/resources/jqueryautocomplete/css/styles.css" rel="stylesheet" media="screen" />
<script src="${root}/resources/jqueryautocomplete/js/jquery.autocomplete.min.js"></script>
<!-- =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- -->
<!-- CHOSEN SELECT -->
<link href="${root}/resources/chosen/chosen.css" rel="stylesheet" media="screen" />
<script src="${root}/resources/chosen/chosen.jquery.min.js"></script>
<!-- =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- -->
<!-- CUSTOM RESOURCES -->
<link href="${root}/resources/css/style.css" rel="stylesheet" media="screen" />
<link href="${root}/resources/css/position.css" rel="stylesheet" />
<script src="${root}/resources/js/csa.js"></script>
<title>CSA</title>
</head>
<body>
   <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
         <div class="navbar-header">
            <button type="button" class="pull-left navbar-toggle margin-left-10"
               data-toggle="offcanvas">
               <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${root}/showcase/view">Delogic-CSA</a>
            <button type="button" class="navbar-toggle btn btn-primary" data-toggle="collapse"
               data-target=".navbar-collapse">
               <span class="caret"></span>
            </button>
         </div>
         <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
               <li class="active"><a href="${root}/">Home</a></li>
               <li><a href="${root}/users">Usuários</a></li>
               <li><a href="${root}/accounts">Contas</a></li>
               <li><a href="${root}/accounttypes">Tipos de Conta</a></li>
            </ul>
         </div>
         <!-- /.nav-collapse -->
      </div>
      <!-- /.container -->
   </div>
   <!-- /.navbar -->
   <div class="container">
      <div class="row row-offcanvas row-offcanvas-left">
         <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
            <div class="well sidebar-nav">
               <ul class="nav">
                  <li>Sidebar</li>
                  <li class="active"><a href="#">Link</a></li>
                  <li><a href="#">Link</a></li>
                  <li><a href="#">Link</a></li>
                  <li>Sidebar</li>
                  <li><a href="#">Link</a></li>
                  <li><a href="#">Link</a></li>
                  <li><a href="#">Link</a></li>
                  <li>Sidebar</li>
                  <li><a href="#">Link</a></li>
                  <li><a href="#">Link</a></li>
                  <li>Sidebar</li>
                  <li><a href="#">Link</a></li>
                  <li><a href="#">Link</a></li>
               </ul>
            </div>
            <!--/.well -->
         </div>
         <!--/span-->
         <div class="col-xs-12 col-sm-9">
            <jsp:doBody />
         </div>
         <!--/span-->
      </div>
      <!--/row-->
      <hr>
      <footer>
         <p>&copy; Delogic 2013</p>
      </footer>
   </div>
   <!--/.container-->
</body>
</html>