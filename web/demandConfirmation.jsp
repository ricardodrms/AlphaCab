<%-- 
    Document   : demandConfirmation
    Created on : 30-Nov-2015, 19:44:57
    Author     : Jarrett
--%>

<%@page import="models.Price"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AlphaCab</title>
    </head>
    <body>
        <div>
            <h2>Thanks for your booking. The invoice is outlined below.</h2>
            <ul>
                <li>Name: <%
                    models.Demand demand = (models.Demand) session.getAttribute("Demand");
                    out.print(demand.getCustomer().getName());
                    %>
                <li>Journey Date: <%=demand.getDate()%></li>
                <li>Distance: <%=demand.getDistance()%></li>
                <li>Incurred Cost: £<%
                    Price price = new Price(application.getRealPath("/"));
                    double cost = price.getPrice(demand.getDistance());
                    out.print(cost);
                    %>
                </li>
                <li>VAT Added @ 20%: £<%
                    double vatcost = Math.round((cost*0.2)*100);
                    vatcost /= 100;
                    double totalCost = cost + vatcost;
                    out.print(vatcost);
                    %>
                </li>
                <li>Total Cost: £<%=totalCost%>
                </li>
            </ul>
                <form action="index.jsp">
                <input type="submit" value="Continue Shopping">
                </form>
        </div>
    </body>
</html>
