/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import db.Person;
import java.util.HashMap;
import org.neo4j.ogm.cypher.query.SortOrder;
import services.AbstractDBService;
import services.FlexPeopleServiceInterface;

/**
 *
 * @author zua
 */
public class FlexPeopleService extends AbstractDBService<Person> implements FlexPeopleServiceInterface {

    public FlexPeopleService() {
    }

    @Override
    public Person findPersonNamed(String name) {
        String query = "MATCH (n:Person) WHERE n.name=" + DatabaseUtils.getInstance().wrapUp(name);
        return getSession().queryForObject(Person.class, query, new HashMap<>());
    }

    @Override
    public Class<Person> getClassType() {
        return Person.class;
    }

    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "name");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "name");
    }
    
}
