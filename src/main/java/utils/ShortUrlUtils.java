/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zua
 */
public class ShortUrlUtils {

    private ObjectMapper objectMapper;

    public ShortUrlUtils() {
        objectMapper = new ObjectMapper();
    }

    public String getShortUrl(String url) {
        try {
            String dest = "b" + "shortned.txt";
            Process p = Runtime.getRuntime().exec("./shortenUrl.sh " + url + " " + dest);
            while(p.isAlive()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ShortUrlUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            File file = new File(dest);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            SingleShortnerResponse response = objectMapper.readValue(reader, SingleShortnerResponse.class);
            reader.close();
            file.delete();
            return response.getShortenedUrl();
        } catch (IOException ex) {
            Logger.getLogger(ShortUrlUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
