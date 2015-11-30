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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author k9-sheppard
 */
public class DriverDB {

    private Connection conn;

    public DriverDB(Connection conn) {
        this.conn = conn;
    }

    public boolean addDriver(Driver driver) {
        try {

            Statement state = conn.createStatement();
            state.executeUpdate(String.format(
                    "INSERT INTO drivers VALUES ('%s', '%s', '%s')", driver.getReg(), driver.getName(), driver.getPassword()));
            state.close();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean removeDriver(String reg) {
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(String.format(
                    "DELETE FROM drivers WHERE Registration='%s'", reg));
            state.close();

        } catch (SQLException e) {
            //System.err.println("Error: " + e);
            return false;

        }//try
        return true;
    }

    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<Driver>();
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * from drivers");
            while(rs.next()){
                String reg = rs.getString(1);
                String name = rs.getString(2);
                String pass = rs.getString(3);
                drivers.add(new Driver(reg, name, pass));
            }
            
            state.close();
            rs.close();

        } catch (SQLException e) {
            //System.err.println("Error: " + e);

        }//try
        return drivers;
    }
    
    public Driver doDriverLogin(String registration, String pass){
        Driver driver = null;
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(String.format("SELECT * from drivers WHERE Registration = '%s' AND password = '%s'", registration, pass));
            while(rs.next()){

                driver = new Driver(rs.getString(1), rs.getString(2), rs.getString(3));
            }
            
            state.close();
            rs.close();

        } catch (SQLException e) {
            //System.err.println("Error: " + e);

        }//try
        return driver;
    }

    public List<Journey> getJobsForDriver(String reg) {
        List<Journey> journeys = new ArrayList<Journey>();
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(String.format(
                    "SELECT * from Journey INNER JOIN drivers ON Journey.`Drivers.Registration`=Drivers.Registration " + 
                            "INNER JOIN customer ON Journey.`Customer.id`=Customer.id WHERE `Drivers.Registration`='%s'", reg));
            while(rs.next()){
                int id = rs.getInt("id");
                String dest = rs.getString("Destination");
                double distance = rs.getDouble("Distance");
                Date date = rs.getDate("Date");
                Time time = rs.getTime("Time");
                Customer cust = new Customer(rs.getInt("Customer.id"), rs.getString("Name"), rs.getString("Address"));
                Driver driver = new Driver(reg, rs.getString("Name"), rs.getString("password"));
                journeys.add(new Journey(id, dest, cust, driver, date, time, distance));
            }
            
            state.close();
            rs.close();

        } catch (SQLException e) {
            //System.err.println("Error: " + e);

        }//try
        return journeys;
    }
    
}
