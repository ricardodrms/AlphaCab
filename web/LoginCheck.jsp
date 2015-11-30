<%@page import="models.DriverDB"%>
<%@page import ="java.sql.*" %>
<%@page import ="java.io.IOException" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@page import="java.io.*"%>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Check</title> 

    </head> 
    <body> 
        
        <%
            //DriverView
                String Drivername ="";
                String Name = request.getParameter("Name");
                String password = request.getParameter("password");
                
                DriverDB Driver = new models.DriverDB((Connection)request.getServletContext().getAttribute("connection"));
                
                Drivername = Driver.doDriverLogin(Name, password).getName();
                //Drivername = Driver.doLogin(Name, password);
                if (Drivername != null){
                    
                session.setAttribute("Drivername", Drivername);
                response.sendRedirect("DriverView.jsp");

                    out.println("Valid login credentials");
                } else {
                    out.println("Invalid login credentials");
                }

        %>
    </body>
</html>