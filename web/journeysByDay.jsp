<%-- 
    Document   : journeysByDay
    Created on : 27-Nov-2015, 15:36:11
    Author     : lul
--%>

<%@page import="java.text.SimpleDateFormat"%>
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
        <%
            Price price = new Price(application.getRealPath("/"));
                BookingDB bookingDB = new BookingDB((Connection) request.getServletContext().getAttribute("connection"));
                String date = request.getParameter("job_date");
                if (date == null){
                    Calendar c = new GregorianCalendar();
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                    date = formatter.format(c.getTime());
                }
        %>
        <form action="index.jsp">
        <input type="submit" value="Back to menu">
        </form> 
        <br><br>
        <form method="POST" action="journeysByDay.jsp">
            <input type="date" value="<%= date%>" name="job_date"><br>
            <input type="submit" value="Submit">
        </form>
        <table  border="1" style="width:50%" id="journeysTable">
            <tr>
                <th>Date</th>
                <th>Customer</th>
                <th>Destination</th> 
                <th>Cost</th> 
            </tr>
            <%
                List<Journey> journeys = bookingDB.viewDailyBookings(date);
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
