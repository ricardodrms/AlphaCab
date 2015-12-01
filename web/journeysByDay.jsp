<%-- 
    Document   : journeysByDay
    Created on : 27-Nov-2015, 15:36:11
    Author     : Hannah
--%>

<%@page import="controllers.Price"%>
<%@page import="java.util.List"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="models.Journey"%>
<%@page import="models.BookingDB"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alphacab</title>
    </head>
    <body>
        <h1>All journeys by day</h1>
        <form action="headOfficeHomePage.jsp">
            <input type="submit" value="Back to menu">
        </form>
        <br>
        <table  border="1" style="width:50%">
            <tr>
                <th>Date</th>
                <th>Customer</th>
                <th>Destination</th> 
                <th>Cost</th> 
            </tr>
            <%
                Price price = new Price(application.getRealPath("/"));
                BookingDB bookingDB = new BookingDB((Connection) request.getServletContext().getAttribute("connection"));
                List<Journey> journeys = bookingDB.viewAllCompletedBookings();
                for (int i = 0; i < journeys.size(); i++) {
                    Journey j = journeys.get(i);
            %>
            <tr>
                <td> <%= j.getDate()%> </td>
                <td> <%= j.getCustomer().getName()%> </td>
                <td> <%= j.getDestination()%> </td>
                <td> <%= price.getPrice(j.getDistance())%> </td>
            </tr>
            <%
                } //ending the loop from above
            %>
        </table>
    </body>
</html>
