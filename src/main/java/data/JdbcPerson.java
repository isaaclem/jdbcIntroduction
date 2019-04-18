package data;

import domain.Person;
import java.util.*;
import java.sql.*;

/**
 *
 * @author isaaclem
 */
public class JdbcPerson {
    // We rely on MySql's autoincrementable primary key
    // so the id_person field is omitted
    // A prepareStatement is used, so we can
    // use parameters (signs of ?)
    // which will later be replaced by the respective parameter
    
    private final String SQL_INSERT = "INSERT INTO person(name) VALUES(?)";
    private final String SQL_UPDATE = "UPDATE person SET name=? WHERE id_person=?";
    private final String SQL_DELETE = "DELETE FROM person WHERE id_person=?";
    private final String SQL_SELECT = "SELECT id_person, name FROM person ORDER BY id_person";
    
    public int insert(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = JavaConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, name);
            System.out.println("stmt:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Affected records: "+ rows);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            JavaConnection.close(stmt);
            JavaConnection.close(conn);
        }
        
        return rows;
    }
    
    public int update(int idPerson, String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = JavaConnection.getConnection();
            System.out.println("Executing query:"+ SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, name);
            stmt.setInt(2, idPerson);
            rows = stmt.executeUpdate();
            System.out.println("Updated records: " + rows);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            JavaConnection.close(stmt);
            JavaConnection.close(conn);
        }
        return rows;
    }
    
    public int delete(int idPerson) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = JavaConnection.getConnection();
            System.out.println("Executing query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idPerson);
            rows = stmt.executeUpdate();
            System.out.println("Deleted records: " + rows);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            JavaConnection.close(stmt);
            JavaConnection.close(conn);
        }
        return rows;
    }
    
    public List<Person> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Person personObj = null;
        List<Person> personObjs = new ArrayList<>();
        
        try {
            conn = JavaConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                int idPerson = rs.getInt(1);
                String namePerson = rs.getString(2);
                personObj = new Person();
                personObj.setIdPerson(idPerson);
                personObj.setName(namePerson);
                personObjs.add(personObj);
            }
        } catch(SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            JavaConnection.close(rs);
            JavaConnection.close(stmt);
            JavaConnection.close(conn);
        }
        return personObjs;
    }
    
}
