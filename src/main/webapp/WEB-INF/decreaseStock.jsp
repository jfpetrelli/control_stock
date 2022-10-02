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

</head>

<body>
	<div id="divNuevoRol">
	  	<h4>Disminuir stock:</h4>
		<form action="DecreaseStock" method="POST">
		<div>
		    <label for="product"> Producto </label>
		    <input name="product" id="product" value="">
		  </div>
		  <div>
		    <label for="store"> Almacen </label>
		    <input name="store" id="store" value="">
		  </div>
		  <div>
		    <label for="quantity"> Cantidad </label>
		    <input name="quantity" id="quantity" value="">
		  </div>
		  
		  <div>
		    <button>RESTAR</button>
		  </div>
		</form>
  </div>
</body>

</html>