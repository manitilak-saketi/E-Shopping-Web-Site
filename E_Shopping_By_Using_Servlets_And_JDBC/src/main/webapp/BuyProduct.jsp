<%@page import="com.eshopping.model.ProductDetails"%>
<%@page import="com.eshopping.DAO.ProductDAOImp"%>
<%@page import="com.eshopping.DAO.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
int productId=Integer.parseInt(request.getParameter("productid"));
System.out.println(productId);
ProductDAO pDAO=new ProductDAOImp();
ProductDetails pd=pDAO.getProductDetailsById(productId);
%>
<body>
	<h1><%=productId %></h1>
	<h1>Product Purchase</h1>	
	<div>
	<form action="">
	<input name="productname" value="<%=pd.getPro_name()%>"><br>
	<input name="productbrand" value="<%=pd.getPro_brand()%> "><br>
	<input name="productprice" id="price" value="<%= pd.getPro_price()%> "><br>
	<input name="productdisc" id="discount" value="<%= pd.getPro_discount()%>"><br>
	<input type="number" name="productquantity" placeholder="Quantity" id="quantity" onInput="updatePrice()"><br>
	<input name="totalprice" id="totalprice" placeholder="totalprice" readonly="readonly">
	</form>
	</div>
	<script type="text/javascript">
		function updatePrice() {
			var price = document.getElementById("price").value;
			var discount =document.getElementById("discount").value;
			var totalPrice=document.getElementById("totalprice");
			var quantity=document.getElementById("quantity").value;
			var quan=parseInt(quantity,10)||0;
			var pri=parseFloat(price);
			var disc=parseFloat(discount);
			var dis=pri-pri*(disc/100);
			var total=quan*dis;
			totalPrice.value=total;
		}
	</script>
</body>
</html>