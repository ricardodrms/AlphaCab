<%-- 
    Document   : demandConfirmation
    Created on : 30-Nov-2015, 19:44:57
    Author     : Jarrett
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            Thanks for your booking. The invoice is outlined below.
            <ul>
                <li>Distance: <%
                    models.Demand demand = (models.Demand) session.getAttribute("Demand");
                    out.print(demand.getDistance());
                    %>
                </li>
                <li>Incurred Cost: <%
                    models.Journey journey = new models.Journey(demand);
                    double cost = journey.getCost();
                    out.print(cost);
                    %>
                </li>
                <li>VAT Added @ 20%: <%
                    double totalCost = (cost * 0.2) + cost;
                    out.print(cost * 0.2);
                    %>
                </li>
                <li>Total Cost: <%
                    out.print(totalCost);
                    %>
                </li>
            </ul>
                <p><a href="index.jsp">Return to Homepage</a></p>
        </div>
    </body>
</html>
