/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

/**
 *
 * @author zua
 */
public class NewsCrawlerException extends NewsServiceException {

    public NewsCrawlerException(Exception e) {
        super(e);
    }
    
}