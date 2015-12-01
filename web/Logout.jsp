<%-- 
    Document   : Logout
    Created on : Dec 1, 2015, 3:06:11 PM
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
                HttpSession newsession = request.getSession(false);
    if (newsession != null) 
    {
         newsession.invalidate();

    } 
    response.sendRedirect("index.jsp");
        %>
    </body>
</html>
