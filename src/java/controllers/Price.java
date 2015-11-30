/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.servlet.SessionTrackingMode.URL;

/**
 *
 * @author k9-sheppard
 */
public class Price {
    
    private String fileName = "price.txt";
    private double pricePerMile;
    private double shortDist;
    private String real;

    public Price(String realPath) {
        try {
            // FileReader reads text files in the default encoding.
            // Always wrap FileReader in BufferedReader.
            real = realPath;
            BufferedReader br = new BufferedReader(new FileReader(real + "WEB-INF/" + fileName));            
            String line = br.readLine();
            pricePerMile = Double.parseDouble(line.replaceAll("price=", ""));
            line = br.readLine();
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

            String sRootPath = new File("").getAbsolutePath();
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
