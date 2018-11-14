<%-- 
    Document   : discountcode
    Created on : 7 nov. 2018, 13:14:38
    Author     : vanat
--%>

<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edition des taux de remise</h1>
        <c:choose>
            <c:when test="${empty discountCodes}">
            Aucun code promo
        </c:when>
        <c:otherwise>
            <form method='GET'>
                    Code : <input name="code" size="1" maxlength="1" pattern="[A-Z]{1}+" title="Une lettre en MAJUSCULES"><br/>
		    Taux : <input name="taux" type="number" step="0.01" min="0.0" max="99.99" size="5"><br/>
			<input type="hidden" name="action" value="ADD">
			<input type="submit" value="Ajouter">
		</form>
		
		<div><h4></h4></div>
                <div>
                    <table border=2>
        <tr> <th>Code</th> <th>Taux</th> <th>Action</th> </tr>
        <c:forEach var="discount" items="${discountCodes}">
            <tr> <td>${discount.discountId}</td> <td>${discount.rate}</td>
                <td><a href="?action=DELETE&code=${discount.discountId}">delete</a></td></tr>
        </c:forEach>
    </table>
                </div>	
        </c:otherwise>
        </c:choose>
        
    </body>
</html>
