<%@page import="com.eshopping.DAO.CartDAOImp"%>
<%@page import="com.eshopping.DAO.CartDAO"%>
<%@page import="com.eshopping.model.ProductDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.eshopping.model.CustomerDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{
font-family: cursive;
}
table {
	border: 2px solid black;
	margin-top: 10px;
	margin-left: 250px;
}
</style>
</head>
<body>
<%
CustomerDetails cd=(CustomerDetails)session.getAttribute("customerlogin");
CartDAO cDAO=new CartDAOImp();
Integer cartCount=cDAO.getCustomerCartCount(cd.getCus_id());
List<ProductDetails> allProducts=(List<ProductDetails>) session.getAttribute("allProducts");
%>
<h1>Customer products</h1>
<h1>Customer Name:-<%=cd.getCus_first_name()%></h1>
<h3>Customer Cart:<button>myCart</button><sup><%=cartCount%></sup></h3> 
<table border="1" style="border-collapse: collapse;text-align: center;font-size: large;">
    <tr>
        <th>Product Id</th>
        <th>Product Name</th>
        <th>Product Brand</th>
        <th>Product Price</th>
        <th>Product Discount</th>
        <th>Product Category</th>
        <th>Product Quantity</th>
        <th>Action</th>
    </tr>
    <%
      for (ProductDetails pd : allProducts){
    %>
    <tr>
        <td><%= pd.getPro_id() %></td>
        <td><%= pd.getPro_name() %></td>
        <td><%= pd.getPro_brand() %></td>
        <td><%= pd.getPro_price() %></td>
        <td><%= pd.getPro_discount() %></td>
        <td><%= pd.getPro_category() %></td>
        <td><%= pd.getPro_quantity() %></td>
        <td>
        	<form action="addtocart">
            <input value=<%=pd.getPro_id() %> hidden="true" name="productid"> 
            <input value=<%=cd.getCus_id() %> hidden="true" name="customerid"> 
            <button class="button">Add to Cart</button>
             </form>
             </td>
             <td>
            <form action="BuyProduct.jsp">
            <input value=<%=pd.getPro_id() %> hidden="true" name="productid">
            <input value=<%=cd.getCus_id() %> hidden="true" name="customerid"> 
            <button class="button">Buy</button>
            </form>
            </td>
    </tr>
    <%
      }
    %>
</table>
</body>
</html>