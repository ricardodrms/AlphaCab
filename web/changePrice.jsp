<%-- 
    Document   : changePrice
    Created on : 30-Nov-2015, 13:19:46
    Author     : k9-sheppard
--%>

<%@page import="controllers.Price"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alphacab</title>
    </head>
    <body>
        <h1>Change Prices!</h1>
        <br>
        <%
            Price price = new Price(application.getRealPath("/"));
            %>
            
        <form method="POST"
              action="ChangePrice.do">
            Price per mile: <input type="number" name="pricePerMile" value="<%= price.getPricePerKM()%>"><br>
            Short Distance modifier (0-5 miles): <input type="number" name="short" value="<%= price.getShortDist()%>"><br>
            <input type="submit" value="Change Price">
        </form>
        <FORM>
            <INPUT Type="button" VALUE="Cancel" onClick="history.go(-1);return true;">
        </FORM>
    </body>
</html>
