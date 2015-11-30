<%-- 
    Document   : DriverView
    Created on : Nov 19, 2015, 6:50:57 PM
    Author     : sammccarthy
--%>

<%@page import="models.DriverDB"%>
<%@page import="models.Journey"%>
<%@page import="java.util.List"%>
<%@page import="models.BookingDB"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <body>
        <h1>All current jobs for driver</h1>
        <form action="headOfficeHomePage.jsp">
            <input type="submit" value="Back to menu">
        </form>
        <br>
        <table  border="1" style="width:50%">
            <tr>
                <th>Destination</th>
                <th>Distance</th>
                <th>Customer ID</th> 
                <th>Date</th> 
                <th>Time</th> 
            </tr>
            <%
                DriverDB driverDB = new DriverDB((Connection) request.getServletContext().getAttribute("connection"));
                String reg = (String)session.getAttribute("Registration");
                
                List<Journey> journeys = driverDB.getJobsForDriver(reg);
                for (int i = 0; i < journeys.size(); i++) {
                    Journey j = journeys.get(i);
            %>
            <tr>
                <td> <%= j.getDestination()%> </td>
                <td> <%= j.getDistance()%> </td>
                <td> <%= j.getCustomer().getName()%> </td>
                <td> <%= j.getDate()%> </td>
                <td> <%= j.getTime()%> </td>
            </tr>
            <%
                } //ending the loop from above
            %>
        </table>
    </body>
</html>
