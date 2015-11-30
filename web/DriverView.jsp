<%-- 
    Document   : DriverView
    Created on : Nov 19, 2015, 6:50:57 PM
    Author     : sammccarthy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            //DriverView
            String Drivername = (String) session.getAttribute("Drivername");
            
            out.print(Drivername);
        %>
    </body>
</html>
