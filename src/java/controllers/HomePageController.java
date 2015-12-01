/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jarrett
 */
public class HomePageController {
    
    public HomePageController() {
        
    }
    public List getUserActions(String driverName) {
        List types = new ArrayList();
        
        if (driverName.equals("ADMIN")) {
            // User Session is Admin
            types.add("<li class='active'><a href='index.jsp'>Home</a></li>");
            types.add("<li><a href='assignBooking.jsp'>Assign booking</a></li>");
            types.add("<li><a href='customerList.jsp'>View Customers</a></li>");
            types.add("<li><a href='driverList.jsp'>View Drivers</a></li>");
            types.add("<li><a href='journeysByDay.jsp'>View All Journeys</a></li>");
            types.add("<li><a href='dailyReport.jsp'>View Daily Report</a></li>");
            types.add("<li><a href='changePrice.jsp'>Change Prices</a></li>");
            types.add("<li><a href='Logout.jsp'>Logout</a></li>");
            /*
            <h1>Menu</h1>
            <div id='cssmenu'>    
                <ul>
                    <li class='active'><a href='index.html'>Home</a></li>
                    <li><a href='assignBooking.jsp'>Assign booking</a></li>
                    <li><a href='customerList.jsp'>View Customers</a></li>
                    <li><a href='driverList.jsp'>View Drivers</a></li>
                    <li><a href='journeysByDay.jsp'>View All Journeys</a></li>
                    <li><a href='dailyReport.jsp'>View Daily Report</a></li>
                </ul>
            </div>
            */
        } else if (driverName.equals("")) {
            // User Session links to Guest / Public
            types.add("<li><a href='enterCustomerDetails.html'>Book a Cab</a></li>");
            types.add("<li><a href='login.jsp'>Driver/Admin Login</a></li>");
        } else {
            // Otherwise Driver has Logged in
            types.add("<li><a href='DriverJourneyView.jsp'>View all Journeys</a></li>");
            types.add("<li><a href='Logout.jsp'>Logout</a></li>");
        }
        
        return types;
    }
}
