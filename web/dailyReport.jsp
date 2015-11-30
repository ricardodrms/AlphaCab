<%-- 
    Document   : dailyReport
    Created on : 27-Nov-2015, 12:59:43
    Author     : Hannah
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="models.Journey"%>
<%@page import="java.util.List"%>
<%@page import="models.BookingDB"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alphacab</title>
    </head>
    <body>
        <%
                BookingDB bookingDB = new BookingDB((Connection) request.getServletContext().getAttribute("connection"));
                Calendar c = new GregorianCalendar();
                SimpleDateFormat formatter= new SimpleDateFormat("dd/MMM/yyyy");
                String date = formatter.format(c.getTime());
                List<Journey> journeys = bookingDB.viewDailyBookings(date);
                double turnover = 0;
                
                for (int i = 0; i < journeys.size(); i++) {
                    Journey j = journeys.get(i);
                    turnover += j.getCost();
                }
            %>
        <h1>Daily report for <%= date %></h1>
        <form action="headOfficeHomePage.jsp">
            <input type="submit" value="Back to menu">
        </form>
        <br>
        Number of bookings today: <%= journeys.size() %><br>
        Daily turnover: <%= turnover %><br>
        
    </body>
</html>
