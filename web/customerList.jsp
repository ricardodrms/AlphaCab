<%-- 
    Document   : customerList
    Created on : 23-Nov-2015, 19:58:24
    Author     : Kyran
--%>

<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="models.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alphacab</title>
    </head>
    <body>
        <h1>All Alphacab customers</h1>
        <form action="headOfficeHomePage.jsp">
            <input type="submit" value="Back to menu">
        </form>
        <br><br>
        <table  border="1" style="width:50%">
            <tr>
                <th>Name</th>
                <th>Address</th> 
            </tr>
            <%
                CustomerDB customerDB = new CustomerDB((Connection) request.getServletContext().getAttribute("connection"));
                List<Customer> customers = customerDB.getAllCustomers();
                Customer c;
                for (int i = 0; i < customers.size(); i++) {
                    c = customers.get(i);
                    out.println("<tr>");
                    out.println("<td>" + c.getName() + "<//td>");
                    out.println("<td>" + c.getAddress() + "<//td>");
                    out.println("</tr>");
                }

            %>
        </table>
    </body>
</html>
