package domain;

/**
 *
 * @author isaaclem
 */
public class Person {
    private int id_person;
    private String name;
    
    public int getIdPerson() {
        return id_person;
    }
    
    public void setIdPerson(int idPerson) {
        this.id_person = idPerson;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Person{" + "id_person=" + id_person + ", name=" + name + "}";
    }
}
