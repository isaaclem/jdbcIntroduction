package test;

import data.JdbcPerson;
import domain.Person;
import java.util.List;
/**
 *
 * @author isaaclem
 */
public class JdbcHandlingTest {
    public static void main(String[] args) {
        JdbcPerson jdbcPerson = new JdbcPerson();
        
        //Test insert method
        //jdbcPerson.insert("Isaac");
        
        //Test update method
        //jdbcPerson.update(2, "Johnny");
        
        //Test delete method
        //jdbcPerson.delete(1);
        
        List<Person> peopleList = jdbcPerson.select();
        for(Person p: peopleList) {
            System.out.print(p);
            System.out.println("");
        }
    }
}
