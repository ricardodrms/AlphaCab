/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author k9-sheppard
 */
public class Price {
    
    private String fileName = "price.txt";
    private double pricePerMile;
    private double shortDist;

    public Price() {
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            pricePerMile = Double.parseDouble(line.replaceAll("price=", ""));
            line = bufferedReader.readLine();
            shortDist = Double.parseDouble(line.replaceAll("short=", ""));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Price.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Price.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double getPricePerMile() {
        return pricePerMile;
    }

    public double getShortDist() {
        return shortDist;
    }
    
    
    
    public double getPrice(double distance){
        double price = distance * pricePerMile;
        
        if(distance < 5){
            price += shortDist;
        }
        
        return price;
    }
    
    public void editShortPrice(double changeMile, double changeShort){
        shortDist = changeShort;
        pricePerMile = changeMile;
        
        try {
            // Put some bytes in a buffer so we can
            // write them. Usually this would be
            // image data or something. Or it might
            // be unicode text.
            String bytes = "price=" + pricePerMile + "\nshort=" + shortDist;
            byte[] buffer = bytes.getBytes();

            FileOutputStream outputStream =
                new FileOutputStream(fileName);

            outputStream.write(buffer);

            // Always close files.
            outputStream.close();       
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing file '"
                + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

    }
    
}
