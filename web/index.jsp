<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AlphaCab</title>
    </head>
    <body>
        <div id='cssmenu'>    
            <ul>
                <%
                    String driverName = (String)session.getAttribute("Drivername");
                    controllers.HomePageController hpc = new controllers.HomePageController();
                    
                    if (driverName == null) {
                        driverName = "";
                    }
                    
                    List styles = hpc.getUserActions(driverName);
                    Iterator it = styles.iterator();
                    while (it.hasNext()) {
                        out.print("<br>" + it.next());
                    }
                    
                %>
            </ul>
        </div>
    </body>
</html>
