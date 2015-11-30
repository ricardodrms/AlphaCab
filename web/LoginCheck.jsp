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
                
                String Registration = request.getParameter("Registration");
                String Password = request.getParameter("Password");
                
                DriverDB driver = new models.DriverDB((Connection)request.getServletContext().getAttribute("connection"));
                
                String Drivername = driver.doDriverLogin(Registration, Password).getName();
                //Drivername = Driver.doLogin(Name, password);
                if (Drivername != null){
                    
                session.setAttribute("Drivername", Drivername);
                session.setAttribute("Registration", Registration);
                response.sendRedirect("index.jsp");

                    out.println("Valid login credentials");
                } else {
                    out.println("Invalid login credentials");
                }

        %>
    </body>
</html>