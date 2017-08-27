/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import javax.ejb.ApplicationException;

/**
 *
 * @author zua
 */
@ApplicationException(rollback=false)
public class NewsServiceException extends RuntimeException {

    public NewsServiceException(Throwable e) {
        super(e);
    }
    
}
