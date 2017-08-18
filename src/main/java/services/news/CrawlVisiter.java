/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import org.jsoup.nodes.Document;

/**
 *
 * @author zua
 */
public class CrawlVisiter {
    
    public void visit(Document document) {
        System.out.println("Visiting " + document.tagName());
    }
}
