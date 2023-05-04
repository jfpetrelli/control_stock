<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="entities.Products"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sistema de Control de Stock</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <%
    
    		
    		ArrayList<Products> products = (ArrayList) request.getAttribute("products");
    		String error = (String) request.getAttribute("error");

    	
    %>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-warning sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="Main" name="logo">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Control Stock</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
             <li class="nav-item active">
                <a class="nav-link" href="Sale">
                    <i class="fas fa-shopping-cart"></i>
                    <span>Venta</span>
                </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="Customer">
                    <i class="fas fa-address-book"></i>
                    <span>Clientes</span>
                </a>
			</li>
			 <li class="nav-item active">
                <a class="nav-link" href="Stock">
                    <i class = "fas fa-clipboard-list"></i>
                    <span>Stock</span>
                </a>
			</li>
			 <li class="nav-item active">
                <a class="nav-link" href="StoreList">
                    <i class = "fas fa-clipboard-list"></i>
                    <span>Depositos</span>
                </a>
			</li>
			<li class="nav-item active">
                <a class="nav-link" href="Product">
                    <i class = "fab fa-product-hunt"></i>
                    <span>Productos</span>
                </a>
			</li>
			<li class="nav-item active">
                <a class="nav-link" href="User">
                    <i class = "fa fa-users"></i>
                    <span>Usuarios</span>
                </a>
			</li>
			<li class="nav-item active">
                <a class="nav-link" href="Rol">
                    <i class = "fas fa-user-cog"></i>
                    <span>Roles</span>
                </a>
			</li>
			<li class="nav-item active">
                <a class="nav-link" href="LocationList">
                    <i class = "fas fa-user-cog"></i>
                    <span>Localidades</span>
                </a>
			</li>	
			<li class="nav-item active">
                <a class="nav-link" href="ListSale">
                    <i class = "fas fa-user-cog"></i>
                    <span>Listado de Ventas</span>
                </a>
			</li>					
            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars text-warning"></i>
                    </button>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Productos</h1>
                    </div>
					<div class="row">
                        	<div class="col-2 align-self-end">
                                <div class="mb-3">
									<a  class="btn btn-primary" href="NewProduct">Dar de Alta</a>
                                </div>
                            </div>                        	                        
                    </div>
					<div class="row">
                        	<div class="col-2 align-self-end">
                                <div class="mb-3">
									<a  class="btn btn-primary" href="SendMail">Enviar listado</a>
                                </div>
                            </div>                        	                        
                    </div>                    
                    <!-- Content Row -->
                                              <% if(error != null) {%>
                       <div class="row">
                        	<div class="text-center">
                                <div class="mb-3">
									<h4><%= error %></h4>
                                </div>
                            </div>                        	                        
                        </div>
 <%}%>                        


                        <!-- Begin Page Content -->
                        <div class="row">
                            <div class="col-12">
                                <!-- DataTales Example -->
                                <div class="card shadow mb-4">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-bordered" id="productsTable" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th class="col-2 text-center">Descripcion</th>
                                                        <th class="col-2 text-center">Precio</th>
                                                        <th colspan=2 class="col-10 text-center">Acciones</th> 
                                                    </tr>
                                                </thead>
                                                <tbody id="tbodyProducts">
                                                <% for (Products product : products) {%>                                              
                                                	<tr id="<%= product.getId() %>">
                                                		<td class="col-2 text-center"><%=product.getDetail() %></td>
                                                        <td class="col-2 text-center"><%=product.getPrice()%></td>	
                                                        <td class="col-3 ">
															<div  class="row justify-content-md-center">
																<div class="col-3">
																	<form id="editProduct<%= product.getId() %>" action="EditProduct" method="GET">
																			<input type="hidden" name="product_id" value="<%= product.getId() %>">
																			<input form="editProduct<%= product.getId() %>" type="submit" class="btn btn-primary" value="Editar"> 																																					
																	</form>	
																			 	
																	
																</div>
																<div class="col-3">
																	<form id="removeProduct<%= product.getId() %>" action="RemoveProduct" method="POST">
																			<input type="hidden" name="product_id"  value="<%=product.getId() %>">																		
																			<input form="removeProduct<%= product.getId() %>" type="submit" class="btn btn-danger" value="Eliminar">																
																	</form>	
																</div>		
															</div>																									
														</td>												
                                                    </tr>                                              		
                                                <% } %>	
                                                	
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                     
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
    

	
	
	
    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>
</body>

</html>
