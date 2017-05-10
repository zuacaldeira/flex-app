/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utils;

import flex.backend.news.db.NewsAuthor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author zua
 */
public class FlexUtils {
    
    private static FlexUtils instance;
    
    private FlexUtils() {}
    
    public static FlexUtils getInstance() {
        if(instance == null) {
            instance = new FlexUtils();
        }
        return instance;
    }
    
    public  Set<NewsAuthor> extractAuthors(String value) {
        Set<NewsAuthor> authors = new HashSet<>();

        String[] parts = value.split(",");
        Set<String> allParts = new HashSet<>(Arrays.asList(parts));
        for(String part: allParts) {
            System.out.println("Processing author... " + part.trim());
            authors.add(new NewsAuthor(part.trim()));
        }
        
        return authors;
    }
}
