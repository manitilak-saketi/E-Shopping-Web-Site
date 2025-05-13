<%@page import="com.eshopping.DAO.ProductDAOImp"%>
<%@page import="com.eshopping.DAO.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.eshopping.model.ProductDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Details</title>
<style type="text/css">
*{
    margin: 0%;
    padding: 0%;
    font-family: cursive;
}
table{
    width:1150px;
    margin-top: 10px;
    margin-left: 230px;
    border: 2px solid black;
}
h1{
    margin-top: 20px;
}
input {
    margin-bottom: 10px;
    margin-right: 5px;
}
.button{
    font-size: large;
    margin: 5px;
    border: 1px solid black;
    border-radius: 5px;
}
</style>
</head>
<body>
<%
    ProductDAO pDAO = new ProductDAOImp();
    List<ProductDetails> productList = null;
    String searchItems = request.getParameter("search");
    
    if (searchItems != null && !searchItems.trim().isEmpty()) {
        productList = pDAO.getProductsBasedOnSelection(searchItems);
    } else {
        productList = pDAO.getAllProductDetails();
    }
%>

<center><h1>All Product Details</h1></center>
<center>
    <form action="AllProductDetails.jsp">
        <input name="search" placeholder="Enter Your Product" style="text-indent:3px;">
        <input type="submit">
    </form>
</center>
<center><a href="AddProduct.html"><button class="button">Add Product</button></a></center>

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
        if (productList != null) {
            for (ProductDetails pd : productList) {
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
            <button class="button">Update</button>
            <button class="button">Delete</button>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
