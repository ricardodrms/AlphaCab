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
            Price price = new Price();
            %>
            
        <form method="POST"
              action="the servlet to do this">
            Price per mile: <input type="text" name="registration" value="<%= price.getPricePerMile()%>"><br>
            Short Distance modifier (0-5 miles): <input type="text" name="name" value="<%= price.getShortDist()%>"><br>
            <input type="submit" value="Change Price">
        </form>
        <FORM>
            <INPUT Type="button" VALUE="Cancel" onClick="history.go(-1);return true;">
        </FORM>
    </body>
</html>
