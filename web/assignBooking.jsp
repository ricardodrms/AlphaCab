<%-- 
    Document   : assignJourney
    Created on : 27-Nov-2015, 15:40:29
    Author     : notHannah
--%>

<%@page import="models.Driver"%>
<%@page import="models.DriverDB"%>
<%@page import="models.Demand"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="models.BookingDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alphacab</title>
    </head>
    <body>
        <h1>Assign booking to driver!</h1>

        <table  border="1" style="width:50%">
            <tr>
                <th>Customer</th>
                <th>Date</th> 
                <th>Destination</th> 
                <th>Distance</th> 
                <th>Action</th> 
            </tr>
            <%
                BookingDB bookingdb = new BookingDB((Connection) request.getServletContext().getAttribute("connection"));
                List<Demand> demands = bookingdb.viewAllUnassignedBookings();

                DriverDB driverDB = new DriverDB((Connection) request.getServletContext().getAttribute("connection"));
                List<Driver> drivers = driverDB.getAllDrivers(true);

                for (int i = 0; i < demands.size(); i++) {
                    Demand d = demands.get(i);
            %>
            <tr>
                <td> <%= d.getName()%> </td>
                <td> <%= d.getDate()%> </td>
                <td> <%= d.getDestination()%> </td>
                <td> <%= d.getDistance()%> </td>
                <td> <form method="POST"
                           action="assignBooking.do">
                        <select required name="driver">
                            <%
                                for (int j = 0; j < drivers.size(); j++) {
                                    Driver driver = drivers.get(j);
                            %>
                            <option value="<%= driver.getReg()%>"><%= driver.getName()%></option>
                            <%
                                }
                            %>
                        </select>
                        <input type="hidden" name="id" value="<%= d.getId()%>">
                        <input type="submit" value="Assign">
                    </form></td>
            </tr>
            <%
                } //ending the loop from above
%>
        </table>

        <form action="headOfficeHomePage.jsp">
            <input type="submit" value="Back to menu">
        </form>
    </body>
</html>
