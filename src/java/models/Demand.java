/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import WebServices.CalculateDistance;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author rocharic
 */
public class Demand {
    private int id;
    private Customer customer;
    private String destination;
    private Date date;
    private Time time;
    private Status status;
    private double distance;

    //for when they are created by a customer
    public Demand(Customer customer, String destination, Date date, Time time) {
        this.customer = customer;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.status = Status.Outstanding;
        this.distance = calcDistance();
    }
    
    //for use when reading from DB
    public Demand(int id, String name, String address, String destination, Date date, Time time, Status status) {
        this.id = id;
        this.customer = new Customer(name, address);
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.status = status;
        this.distance = calcDistance();
    }
    
    public Demand(int id, Customer customer, String destination, Date date, Time time, Status status) {
        this.id = id;
        this.customer = customer;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.status = status;
        this.distance = calcDistance();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public double getDistance() {
        return distance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer cust) {
        this.customer = cust;
    }

    public String getAddress() {
        return customer.getAddress();
    }

    public String getName() {
        return customer.getName();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }
    
    public void setTime(Time time) {
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public double calcDistance(){
        String origin = this.customer.getAddress();
        String destin = this.destination;
        
        return ((double) CalculateDistance.calculate(origin, destin).getDistanceAsInt()) / 1000;
    }
}
