<%-- 
    Document   : dailyReport
    Created on : 27-Nov-2015, 12:59:43
    Author     : Hannah
--%>

<%@page import="models.Price"%>
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
         
                String date = request.getParameter("report_date");
                if (date == null){
                    Calendar c = new GregorianCalendar();
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                    date = formatter.format(c.getTime());
                }
 
                BookingDB bookingDB = new BookingDB((Connection) request.getServletContext().getAttribute("connection"));

                List<Journey> journeys = bookingDB.viewDailyBookings(date);
                double turnover = 0;
                
                Price price = new Price(application.getRealPath("/"));
                
                for (int i = 0; i < journeys.size(); i++) {
                    Journey j = journeys.get(i);
                    turnover += price.getPrice(j.getDistance());
                }
                turnover *= 1.2;
                double rdtotal = Math.round((turnover * 100)) ;
                rdtotal /= 100;
            %>
        <h1>Daily report for <%= date %></h1>
        <form action="index.jsp">
            <input type="submit" value="Back to menu">
        </form>
        <br><br>
        <form method="POST" action="dailyReport.jsp">
            <input type="date" value="<%= date%>" name="report_date"><br>
            <input type="submit" value="Submit">
        </form><br>
        Number of bookings today: <%= journeys.size() %><br>
        Daily turnover: £<%= turnover %><br>
        
    </body>
</html>
