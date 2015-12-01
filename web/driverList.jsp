<%-- 
    Document   : driverList.jsp
    Created on : 23-Nov-2015, 20:34:20
    Author     : Kyran
--%>

<%@page import="models.DriverDB"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@page import="models.Driver"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alphacab</title>
    </head>
    <body>
        <h1>All Alphacab drivers</h1>
        <form action="headOfficeHomePage.jsp">
            <input type="submit" value="Back to menu">
        </form>
        <br><br>
        <form action="addDriver.jsp">
            <input type="submit" value="Add New Driver">
        </form>
        <br>
        <table  border="1" style="width:50%">
            <tr>
                <th>Name</th>
                <th>Address</th> 
                <th>Action</th> 
            </tr>
            <%
                DriverDB driverDB = new DriverDB((Connection) request.getServletContext().getAttribute("connection"));
                List<Driver> drivers = driverDB.getAllDrivers(true);
                for (int i = 0; i < drivers.size(); i++) {
                    Driver c = drivers.get(i);
            %>
            <tr>
                <td> <%= c.getReg()%> </td>
                <td> <%= c.getName()%> </td>
                <td> <form method="POST"
                           action="deleteDriver.do">
                        <input type="hidden" name="driverReg" value="<%= c.getReg()%>">
                        <input type="submit" value="delete">
                    </form></td>
            </tr>
            <%
                } //ending the loop from above
            %>
        </table>

    </body>
</html>



