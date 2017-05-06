/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.db.Publishes;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author zua
 */
@Stateless
@LocalBean
public class PublishingService extends AbstractDBService<Publishes> {
    @Override
    protected Class<Publishes> getClassType() {
        return Publishes.class;
    }
}
