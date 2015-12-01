/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controllers.Price;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author rocharic
 */
public class Journey {
    private int id;
    private String destination;
    private Customer customer;
    private Driver driver;
    private double distance;
    private Date date;
    private Time time;
    private double cost;

    //for getting from DB
    public Journey(int id, String destination, Customer customer, Driver driver, Date date, Time time, double distance) {
        this.id = id;
        this.destination = destination;
        this.customer = customer;
        this.driver = driver;
        this.date = date;
        this.time = time;
        this.distance = distance;
        this.cost = calculateCost();
    }
    
    //for asigning to driver
    public Journey(Demand demand, Driver driver){
        this.destination = demand.getDestination();
        this.customer = demand.getCustomer();
        this.driver = driver;
        this.date = demand.getDate();
        this.time = demand.getTime();
        this.distance = demand.getDistance();
        this.cost = calculateCost();
    }
    
    public Journey(Demand demand) {
        this.distance = demand.getDistance();
        this.cost = calculateCost();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
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
    
    public double calculateCost(){
        Price price = new Price("/");
        double ratePerMeter = price.getPricePerKM();
        double additionalRate = price.getShortDist();
        
        
        return 0.0;
    }
}
