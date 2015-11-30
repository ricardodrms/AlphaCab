<%-- 
    Document   : addDriver
    Created on : 25-Nov-2015, 20:11:43
    Author     : Kyran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alphacab</title>
    </head>
    <body>
        <form method="POST"
              action="addDriver.do">
            Registration: <input type="text" name="registration"><br>
            Name: <input type="text" name="name"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" value="Add Driver">
        </form>
        <FORM>
            <INPUT Type="button" VALUE="Cancel" onClick="history.go(-1);return true;">
        </FORM>
    </body>
</html>
