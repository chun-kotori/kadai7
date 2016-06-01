<%-- 
    Document   : orderList
    Created on : 2015/12/28, 15:42:21
    Author     : HikariHasegawa
--%>

<%@page import="java.util.*"%>
<%@page import="model.Items"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>orderList.jsp</title>
        <style>
            table {border-collapse: collapse;}
            td,th {border: solid 1px; padding: 0.5em;}
        </style>

    </head>
    <body>
        <% int count = 0;%>
        <h1>注文する商品</h1>
        <form method="post" action="/ad0818/Order"> 
            <table>
                <tr>
                    <th>商品ID</th>
                    <th>名称</th>                
                    <th>単価(円)</th>
                    <th>個数</th>    
                </tr>
                <c:forEach var="item" items="${Items}">

                    <tr>
                        <td> <c:out value="${item.itemId}" /> </td>
                        <td> <c:out value="${item.itemName}" /> </td>
                        <td> <c:out value="${item.price}" /> </td>
                        <td> <input type="text" name="num<%=count%>"> </td>
                    </tr>
                    <%count++;%>
                </c:forEach>

            </table>

            <input type="submit" value="注文">
        </form>
        <a href ="/ad0818/Main">在庫一覧へ戻る</a>
    </body>
</html>
