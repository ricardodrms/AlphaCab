/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author k9-sheppard
 */
public class BookingDB {
    
    private Connection conn;
    
    public BookingDB(Connection conn) {
        this.conn = conn;
    }
    
     public Demand getDemand(int id){
         Demand demand = null;
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * from demands WHERE status like 'outstanding' and `id`=" + id);
            while(rs.next()){
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                String dest = rs.getString("Destination");
                Date date = rs.getDate("Date");
                Time time = rs.getTime("Time");
                Status status;
                try{
                    status = Status.valueOf(rs.getString("Status"));
                }
                catch(Exception e){
                    status = Status.Outstanding;
                }

                demand = new Demand(id, name, address, dest, date, time, status);
                break;
            }
            
            state.close();
            rs.close();

        } catch (SQLException e) {
            //System.err.println("Error: " + e);

        }//try
        return demand;
    }
    
    public boolean addBooking(Demand demand){
        try {
            Statement state = conn.createStatement();
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(demand.getDate());
            state.executeUpdate(String.format(
                    "INSERT INTO demands (Name, Address, Destination, Date, Time, Status) VALUES ('%s', '%s', \"%s\", '%s', '%s', '%s')"
                    , demand.getName(), demand.getAddress(), demand.getDestination(), dateString, demand.getTime(), demand.getStatus()));
            state.close();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public boolean assignBooking(Demand demand, String driver){
        //set status to assigned/completed in demand table and also create a journey entry in journey table
        
         try {
             String dateString = new SimpleDateFormat("yyyy-MM-dd").format(demand.getDate());
              String timeString = new SimpleDateFormat("HH:mm").format(demand.getTime());
            Statement state = conn.createStatement();
            state.executeUpdate(String.format(
                    "INSERT INTO journey (Destination, Distance, `Customer.id`, `Drivers.Registration`, Date, Time) VALUES (\"%s\", %s, %s, '%s', '%s', '%s')"
                    , demand.getDestination(), demand.getDistance(), demand.getCustomer().getId(), driver, dateString, timeString));
            state.executeUpdate(String.format(
                    "UPDATE demands SET status='Assigned' WHERE id=%d", demand.getId()));
            state.close();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public List<Demand> viewAllUnassignedBookings(){
         List<Demand> demands = new ArrayList<Demand>();
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * from demands WHERE status like 'outstanding'");
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                String dest = rs.getString("Destination");
                Date date = rs.getDate("Date");
                Time time = rs.getTime("Time");
                Status status;
                try{
                    status = Status.valueOf(rs.getString("Status"));
                }
                catch(Exception e){
                    status = Status.Outstanding;
                }

                demands.add(new Demand(id, name, address, dest, date, time, status));
            }
            
            state.close();
            rs.close();

        } catch (SQLException e) {
            //System.err.println("Error: " + e);

        }//try
        return demands;
    }
    
    public List<Journey> viewAllCompletedBookings(){
        List<Journey> journeys = new ArrayList<Journey>();
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(String.format(
                    "SELECT * from Journey " + //INNER JOIN drivers ON Journey.`Drivers.Registration`=Drivers.Registration " + 
                            "INNER JOIN customer ON Journey.`Customer.id`=Customer.id ORDER BY Date DESC"));
            //commented out the drivers as if a driver is then deleted it wont show up their details
            //shouldnt matter as method only used for displaying purposes
            while(rs.next()){
                int id = rs.getInt("id");
                String dest = rs.getString("Destination");
                double distance = rs.getDouble("Distance");
                Date date = rs.getDate("Date");
                Time time = rs.getTime("Time");
                Customer cust = new Customer(rs.getInt("Customer.id"), rs.getString("Name"), rs.getString("Address"));
                //Driver driver = new Driver(rs.getString("Registration"), rs.getString("Name"), rs.getString("password"));
                journeys.add(new Journey(id, dest, cust, null, date, time, distance));
            }
            
            state.close();
            rs.close();

        } catch (SQLException e) {
            //System.err.println("Error: " + e);

        }//try
        return journeys;
    }
    
    public List<Journey> viewDailyBookings(String date){
        //is it just completed ones or also outstanding?
        List<Journey> journeys = new ArrayList<Journey>();
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(String.format(
                    "SELECT Journey.*, Drivers.*, Customer.`name` as `CustName`, Customer.`Address` FROM Journey INNER JOIN drivers ON Journey.`Drivers.Registration`=Drivers.Registration " + 
                            "INNER JOIN customer ON Journey.`Customer.id`=Customer.id WHERE date='%s'", date));
            while(rs.next()){
                int id = rs.getInt("id");
                String dest = rs.getString("Destination");
                double distance = rs.getDouble("Distance");
                Time time = rs.getTime("Time");
                Date d = rs.getDate("Date");
                Customer cust = new Customer(rs.getInt("Customer.id"), rs.getString("CustName"), rs.getString("Address"));
                Driver driver = new Driver(rs.getString("Registration"), rs.getString("Name"), rs.getString("password"));
                journeys.add(new Journey(id, dest, cust, driver, d, time, distance));
            }
            
            state.close();
            rs.close();

        } catch (SQLException e) {
            //System.err.println("Error: " + e);

        }//try
        return journeys;
    }
}
