<%-- 
    Document   : main
    Created on : 2016/01/14, 15:18:28
    Author     : HikariHasegawa
--%>
<%@page import="java.util.*"%>
<%@page import="model.Items"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>メイン</title>
        <style>
            table {border-collapse: collapse;}
            td,th {border: solid 1px; padding: 0.5em;}
        </style>
    </head>
    <body>
        <h1><font color="red">メイン</font></h1>
        ようこそ<c:out value="^^" />さん</p>
        <a href="/ad0818/Logout">ログアウト</a>

        <h2>在庫一覧</h2>
        <table>
            <tr>
                <th>商品ID</th>
                <th>名称</th>                
                <th>個数</th>    
            </tr>
            
            <c:forEach var="item" items="${Items}">

                <tr>
                    <td> <c:out value="${item.itemId}" /> </td>
                    <td> <c:out value="${item.itemName}" /> </td>
                    <td> <c:out value="${item.num}" /> </td>
                </tr>
            </c:forEach>
                
        </table>

        <p><a href="/ad0818/OrderList">発注</a></p> 
    </body>
</html>
