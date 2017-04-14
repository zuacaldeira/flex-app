package org.test;

import com.csvreader.CsvReader;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;

import java.io.*;
import java.util.*;


/**
 * Created by zua on 12/04/17.
 */
public class Countries {

    private static Map<String, List<CountryData>> countries = loadAll();
    private static String FILENAME = "World_Cities_Location_table.csv";

    private static Map<String, List<CountryData>> loadAll() {
        countries = new HashMap<>();
        try {
            String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
            String filename = basepath + "/World_Cities_Location_table.csv";
            System.out.println("Filename = " + filename + " exists? " + new File(filename).exists());




            CsvReader products = new CsvReader(filename);

            products.readHeaders();
            products.setDelimiter(';');


            while (products.readRecord())
            {
                String[] values = products.getValues();

                String id = values[0];
                String country = values[1];
                String city = values[2];
                Double latitude = Double.parseDouble(values[3]);
                Double longitude = Double.parseDouble(values[4]);
                Double altitude = Double.parseDouble(values[5]);

                // perform program logic here
                addCountryData(new CountryData(id, country, city, latitude, longitude, altitude));
            }

            products.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countries;
    }

    private static void addCountryData(CountryData countryData) {
        if(!countries.containsKey(countryData.getCountry())) {
            countries.put(countryData.getCountry(), new LinkedList<CountryData>());
        }

        countries.get(countryData.getCountry()).add(countryData);
    }


    public static CountryData getCountryData(String country) {

        System.out.println("Input is: " + country);
        Locale[] locales = Locale.getAvailableLocales();
        for(Locale locale1: locales) {
            if(country.equalsIgnoreCase(extractCountry(locale1))
                    || country.equalsIgnoreCase(extractLanguage(locale1))) {
                if(countries.get(locale1.getDisplayCountry()) != null) {
                    int i = (int) Math.random()*countries.get(locale1.getDisplayCountry()).size();
                    return countries.get(locale1.getDisplayCountry()).get(i);
                }
            }
        }

        return new CountryData("Unknown", country, "Unknown City", 0, 0, 0);
    }


    private static String extractCountry(Locale locale1) {
        return locale1.getCountry();
    }
    private static String extractLanguage(Locale locale1) {
        return locale1.getLanguage();
    }
}
