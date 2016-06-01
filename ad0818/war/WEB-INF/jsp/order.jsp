<%-- 
    Document   : order
    Created on : 2015/12/28, 15:42:37
    Author     : HikariHasegawa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>order.jsp</title>
        <style>
            table {border-collapse: collapse;}
            td,th {border: solid 1px; padding: 0.5em;}
        </style>
    </head>
    <body>
        <%int count=0;%>
        <p>この内容で注文しますか？</p>
        <table>
            <tr>
                <th>商品ID</th>
                <th>名称</th>
                <th>単価</th>
                <th>個数</th>    
            </tr>

            <c:forEach var="item" items="${Items}">
                
                <% if(!session.getAttribute("num"+count).equals("")) {%>
                <tr>
                    <td> <c:out value="${item.itemId}" /> </td>
                    <td> <c:out value="${item.itemName}" /> </td>
                    <td> <c:out value="${item.price}" /> </td>
                    <td> <%=session.getAttribute("num"+count) %> </td>
                </tr>
                <%}%>
                <% count++; %>
            </c:forEach>
        </table>
        <p>合計金額　¥<%=session.getAttribute("sum") %></p>
        <form action="/ad0818/OrderResult" method="get"><!--Order.javaのpost→OrderDone.javaに移動、DBに保存-->
            <input type="submit" value="注文する">
        </form>
        <form action="/ad0818/OrderList" method="get"><!--OrderList.javaに戻る-->
            <input type="submit" value="キャンセル">
        </form>

    </body>
</html>
