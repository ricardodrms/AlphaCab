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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author k9-sheppard
 */
public class CustomerDB {
    
    private Connection conn;
    
    public CustomerDB(Connection conn) {
        this.conn = conn;
    }
    
    public boolean addCustomer(Customer cust){
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(String.format(
                    "INSERT INTO customer (`Name`, `Address`) VALUES ('%s', '%s')", cust.getName(), cust.getAddress()));
            state.close();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public boolean editCustomer(Customer cust){
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(String.format(
                    "UPDATE customer SET Name = '%s', Address = '%s' WHERE id = %d", cust.getName(), cust.getAddress(), cust.getId()));
            state.close();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * from customer");
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                String addr = rs.getString("Address");
                customers.add(new Customer(id, name, addr));
            }
            
            state.close();
            rs.close();

        } catch (SQLException e) {
            //System.err.println("Error: " + e);

        }//try
        return customers;
    }
    
}
