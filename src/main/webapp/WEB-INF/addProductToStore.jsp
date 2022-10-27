
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="entities.Stores"%>
<%@page import="entities.Products"%>
<%@page import="logic.ProductsLogic"%>
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

    <title>Control Stock</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <%
    	ArrayList<Stores> stores = (ArrayList) request.getAttribute("stores");
    	ArrayList<Products> products = (ArrayList) request.getAttribute("products");	
    	ProductsLogic productLogic = new ProductsLogic();
    		
		String store_selected = new String();
		String sto = (String)request.getAttribute("store");
		
		if(sto != null){
			store_selected = sto;
		}
    	
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
                        <h1 class="h3 mb-0 text-gray-800">Stock</h1>
                    </div>

                    <!-- Content Row -->

                        <div class="row">

                            <div class="col">
                                <div class="mb-3">
                                  	<label for="store" class="form-label">Deposito <%= sto %></label>
                                  	
                                </div> 
                            </div>
                        </div>

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
                                                    	<th class="d-none">ID</th>
                                                        <th class="col-3">Articulo</th>
                                                        <th class="col-2 text-right">Precio Unitario</th>
                                                        <th class="col-3 text-center">Acción</th>
                                                        
                                                    </tr>
                                                </thead>
                                                <tbody id="tbodyProducts">
                                                <% for (Products product : products) {%>                                              
                                                	<tr id="<%= product.getId() %>">
                                                		<td class="d-none"><%= product.getId() %></td>  
                                                		<td class="col-3"><%=product.getDetail() %></td>
                                                        <td class="col-2 text-right"><%=product.getPrice()%></td>
                                                        <td class="col-3 ">
															<div  class="row justify-content-md-center">
																
																<% if(!productLogic.belongsToStore(store_selected, product)){ %>
																<div class="col-4">
																	<form id="addToStore<%= product.getId() %>" action="SaveProductToStore" method="POST">
																		<input id ="quantity<%= product.getId() %>" required class="form-control" type="number" name="quantity" value="">
																		<input type="hidden" name="store" value="<%=store_selected %>">
																		<input id ="product_id" type="hidden" name="product" value="<%= product.getId() %>">
																	</form>	
																</div>
																<div class="col-2">
																	<button type="submit" class="btn btn-primary" onclick="stockValidation()">Agregar</button>
																</div>
																<%} else {%>
																<div class="col-2">
																	<form id="removeFromStore<%= product.getId() %>" action="RemoveProductFromStore" method="POST">
																		<input type="hidden" name="store" value="<%=store_selected %>">
																		<input type="hidden" name="product" value="<%= product.getId() %>">																		
																	</form>	
																	<button form="removeFromStore<%= product.getId() %>" type="submit" class="btn btn-danger">Eliminar</button>
																</div>
																<%}%>																
																	
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
    
    <form action="AddProductToStore" method ="POST" id="product_to_store_form">
    </form>
    

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">Ã—</span>
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
    
        <script>
		function stockValidation(){
			var productId = document.getElementById('product_id').value;	
			var quantityId = 'quantity'+productId;
			var quantityToAdd = document.getElementById(quantityId).value;	

			quantityToAdd = parseInt(quantityToAdd);
			if(quantityToAdd < 0){
				alert("No puede ingresar números negativos.");
				return;
			}
			if(!quantityToAdd){
				alert("Debe ingresar un número válido.");
				return;
			}
			
			document.getElementById("addToStore"+productId).submit();
			
		}
	</script>
	
	
</body>

</html>

<script>
	
</script>