/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import db.news.NewsAuthor;
import ui.FlexBody;
import ui.FlexMainView;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author zua
 */
public class FlexUtils {
    
    private static FlexUtils instance;

    public static String formatDate(Date date) {
        if(date != null) {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm, dd/MMM/YY");
            String t = format.format(date).toLowerCase();
            return t;
        }
        return "";
    }

    public String wrapUp(String category) {
        return "\"" + category + "\"";
    }

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
    
    public Set<NewsAuthor> extractAuthors(String value) {
        Set<NewsAuthor> authors = new HashSet<>();

        Set<String> names = extractAuthorsNames(value);
        names.forEach(name -> {
            name = name.trim();
            NewsAuthor author = null;
            if(isUrl(name)) {
                String justName = extractNameFromUrl(value);
                author = new NewsAuthor(justName);
                author.setUrl(name);
            } else {
                author = new NewsAuthor(name);
            }
            
            authors.add(author);
        });
        return authors;
    }
    
    public  Set<String> extractAuthorsNames(String value) {
        String[] parts = value.split(", ");
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
        if(lowerCaseName.length() > 1) {
            String c = lowerCaseName.substring(0, 1);
            return lowerCaseName.replaceFirst(c, c.toUpperCase());
        }
        else {
            return lowerCaseName;
        }
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
    
    
    public FlexBody getBody(Component component) {
        return getMainView(component).getBody();
    }

    public FlexMainView getMainView(Component component) {
        return getMainView(component.getParent());
    }
    
    
    private FlexMainView getMainView(HasComponents parent) {
        if(parent == null) {
            return null;
        }
        if(parent instanceof FlexMainView) {
            return (FlexMainView) parent;
        }
        else {
            return getMainView(parent.getParent());
        }
    }
    
}
