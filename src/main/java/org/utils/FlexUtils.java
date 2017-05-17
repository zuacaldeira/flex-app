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

    public boolean isUrl(String value) {
        return value.trim().toLowerCase().startsWith("http");
    }
    
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
    
    public  Set<String> extractAuthorsNames(String value) {
        String[] parts = value.split(",");
        Set<String> allParts = new HashSet<>(Arrays.asList(parts));
        allParts.forEach((part) -> {
            part = part.trim();
        });
        return allParts;
    }

    public String extractNameFromUrl(String authorName) {
        String name = authorName.trim().toLowerCase();
        if(name.startsWith("http://www.abc.net.au/news/")) {
            return extractNameFromAbcNews(authorName);
        }
        if(name.startsWith("http://xml.zeit.de/autoren/")) {
            return extractNameFromDieZeit(authorName);
        }
        else return authorName;
    }

    private String toNameCase(String name) {
        String lowerCaseName = name.toLowerCase();
        String c = lowerCaseName.substring(0, 1);
        return lowerCaseName.replaceFirst(c, c.toUpperCase());
    }

    private String extractNameFromAbcNews(String authorName) {
        String rest = authorName.replace("http://www.abc.net.au/news/", "");
        String[] parts = rest.split("/");            
        parts = parts[0].split("-");

        StringBuilder builder = new StringBuilder();
        if(parts.length >= 1 && parts[0] != null) {
            String r0 = toNameCase(parts[0]);
            builder.append(r0);
            if(parts.length >= 2 && parts[1] != null) {
                String r1 = toNameCase(parts[1]);
                builder.append(" ");
                builder.append(r1);
            }
        }
        return builder.toString();
    }

    private String extractNameFromDieZeit(String authorName) {
        String rest = authorName.replace("http://xml.zeit.de/autoren/", "");
        System.out.println("Without authors page ..." + rest + Character.isSpaceChar(rest.charAt(0)));

        if(Character.isSpaceChar(rest.charAt(0))) {
            rest = rest.substring(3).trim();
        }
        else {
            rest = rest.substring(2).trim();
        }
        System.out.println("Without authors Letter ..." + rest);

        String[] parts = rest.split("/");            
        
        if(parts[0].contains("-")) {
            parts = parts[0].split("-");
        }
        if(parts[0].contains("_")) {
            parts = parts[0].split("_");
        }

        StringBuilder builder = new StringBuilder();
        String r0 = toNameCase(parts[0]);
        builder.append(r0);

        if(parts.length >= 2) {
            String r1 = toNameCase(parts[1]);
            builder.append(" ");
            builder.append(r1);
        }

        String result = builder.toString();
        System.out.println("result -> " + result);
        return result;
    }
    
}
