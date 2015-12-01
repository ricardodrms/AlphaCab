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
    private double pricePerKM;
    private double shortDist;
    private String real;

    public Price(String realPath) {
        try {
            // FileReader reads text files in the default encoding.
            // Always wrap FileReader in BufferedReader.
            real = realPath;
            BufferedReader br = new BufferedReader(new FileReader(real + "WEB-INF/" + fileName));            
            String line = br.readLine();
            pricePerKM = Double.parseDouble(line.replaceAll("price=", ""));
            line = br.readLine();
            shortDist = Double.parseDouble(line.replaceAll("short=", ""));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Price.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Price.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double getPricePerKM() {
        return pricePerKM;
    }

    public double getShortDist() {
        return shortDist;
    }
    
    public double getPrice(double distance){
        double price = distance * pricePerKM;
        
        if(distance < 8.048){
            price += shortDist;
        }
        double roundedprice = Math.round(price*100);
        roundedprice /= 100;
        return roundedprice;
    }
    
    public void editShortPrice(double changeKM, double changeShort){
        shortDist = changeShort;
        pricePerKM = changeKM;
        
        try {
            // Put some bytes in a buffer so we can
            // write them. Usually this would be
            // image data or something. Or it might
            // be unicode text.
            String bytes = "price=" + pricePerKM + "\nshort=" + shortDist;
            byte[] buffer = bytes.getBytes();

            FileOutputStream fos = new FileOutputStream(real + "WEB-INF/" + fileName, false);
            fos.write(buffer);
            fos.flush();
            fos.close();
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
