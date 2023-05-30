
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="entities.Customers"%>
<%@page import="entities.Stores"%>
<%@page import="entities.ListSales"%>
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
    	String nombreUsuario = (String) request.getAttribute("nombreUsuario");
    
    	ArrayList<Customers> customers = (ArrayList) request.getAttribute("customers");
    	ArrayList<Stores> stores = (ArrayList) request.getAttribute("stores");
  

		String customer_selected = new String();
		String store_selected = new String();
		String cust = (String)request.getAttribute("customer");
		String sto = (String)request.getAttribute("store");
		
		if(cust != null){
			customer_selected = cust;
		}
		if(sto != null){
			store_selected = sto;
		}
		
		String msg = (String) request.getAttribute("msg");
		
		String msgAddOK = (String) request.getAttribute("msgAddOK");
		
		
		ArrayList<ListSales> ls = (ArrayList) request.getAttribute("ls");
		
		Double total = 0.0;
		if(ls != null){
			for(ListSales l: ls){
			
				total += l.getPrice();
			
			}
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
                <a class="nav-link" href="Delivery">
                    <i class="fas fa-shopping-cart"></i>
                    <span>Entregas</span>
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
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=nombreUsuario%></span>
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
                                    Desconectarse
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
                        <h1 class="h3 mb-0 text-gray-800">Lista de Ventas</h1>
                    </div>

                    <!-- Content Row -->
                    <form action="ListSale" method ="POST" name="form_sale" id = "form_sale">
                        <div class="row">
                            <div class="col-3">
                               <div class="mb-3">
                                    <label for="fechahora" class="form-label">Fecha Desde</label>
                                    <input type="datetime-local" class="form-control" id="datetime" name = "desde_datetime" value = "<%= request.getAttribute("desde_datetime") %>">
                                  </div> 
                            </div>
                            <div class="col-3">
                            	<div class="mb-3">
                                    <label for="fechahora" class="form-label">Fecha Hasta</label>
                                    <input type="datetime-local" class="form-control" id="datetime" name = "hasta_datetime" value = "<%= request.getAttribute("hasta_datetime") %>">
                                  </div> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <div class="mb-3">
                                  <label for="customer" class="form-label">Cliente</label>
                                  <select class="form-control" aria-label="Default select example" id="customer" name="customer">
                                  <option value = ""></option>
                                 <% for (Customers customer: customers) {%>
									  <option value= "<%=customer.getId() %>" 
									  <%if(customer_selected != null && !customer_selected.isEmpty()) {%>
									  <%if(customer.getId() == Integer.parseInt(customer_selected)){ %> selected<% }}%> ><%=customer.getComercial_name() %></option>
									  <% } %>
								 </select>
                                </div> 
                            </div>
                            <div class="col-6">
                                <div class="mb-3">
                                  	<label for="store" class="form-label">Deposito</label>
                                  	<select class="form-control" aria-label="Default select example" id="store" name="store">
										<option value = ""></option>
                                  		<% for (Stores store: stores) {%>
									  <option value= "<%=store.getId() %>" 
									  <%if(store_selected != null && !store_selected.isEmpty()) {%>
									  <%if(store.getId() == Integer.parseInt(store_selected)){ %> selected<% }}%> ><%=store.getDetail() %></option>
									  <% } %>
									</select>
                                </div> 
                            </div>
                        </div>
                        <div class="row">
                        	<div class="col-2 align-self-end">
                                <div class="mb-3">
                                    <input type="submit" class="form-control bg-warning text-gray-100" value="Filtrar" id ="search" name = "action">
                                    <% if(msg != null){
                                    	%> <p class = "d-block"> <%=msg %></p><%
                                    } %>
                                </div>
                            </div>
                        </div>
                        <% if(msgAddOK != null){
                                    	%><div class= "row"><p class = "d-block"> <%=msgAddOK %></p></div> <%
                                    } %>
                        <!-- Begin Page Content -->
                        <div class="row">
                            <div class="col-12">
                                <!-- DataTales Example -->
                                <div class="card shadow mb-4">
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                <thead bgcolor= "#f6c23e">
                                                    <tr style="color:white">
                                                    	<th class="col-1">Venta</th>
                                                    	<th class="col-2">Cliente</th>
                                                    	<th class="col-2">Deposito</th>
                                                        <th class="col-2">Articulo</th> 
                                                        <th class="col-1 text-right">Cantidad</th>
                                                        <th class="col-2 text-right">Precio Unitario</th>
                                                        <th class="col-1 text-right">Total</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                              <% if(ls != null){ %>
                                                	<% for (ListSales l: ls ){ %>
                                                	<tr>
                                                		<td class="col-1"><%= l.getId() %></td>
                                                        <td class="col-2"><%= l.getCustomer() %></td>
                                                        <td class="col-2"><%= l.getStore() %></td>
                                                        <td class="col-2"><%= l.getProduct() %></td>
                                                        <td class="col-1 text-right"><%= l.getQuantity() %></td>
                                                        <td class="col-1 text-right"><%= l.getUnit_price() %></td>
                                                        <td class="col-1 text-right"><%= l.getPrice() %></td>
                                                	</tr>
                                                	<% } %>
                                                <% }%>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row justify-content-end">
                            <div class="col-2 text-right">
                                <div class="mb-3">
                                
                                 <% if(ls != null){ %>
                                    <label for="" class="pr-4">Total</label>
                                    <input type="text" class="form-control text-right font-weight-bold" name="total" value = "<%= total %>" disabled>
                                    
                                                                                    <% }%>
                                </div>
                            </div>
                        </div>
                    </form>
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
                    <h5 class="modal-title" id="exampleModalLabel">Listo para salir?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">—</span>
                    </button>
                </div>
                <div class="modal-body">Haz clic en "Desconectarse" para salir.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                    <form action="Login" method ="POST">
                   	<input type="submit" value="Desconectarse" class="form-control bg-warning text-gray-100" id="logout" name="action">
                    </form>
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