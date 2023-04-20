import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeData {
    int    id;
    String lastname;
    String  firstname;
    
    EmployeeData (int    id, String lastname, String  firstname) { 
        this.id    = id; 
        this.lastname  = lastname;
        this.firstname = firstname;
    }

    public static Vector<EmployeeData> getEmployeesList(Connection connection, String ciudad){
        Vector<EmployeeData> vec = new Vector<EmployeeData>();
        String sql = "Select EmployeeID, LastName, FirstName FROM Employees WHERE Employees.City=?";
        System.out.println("getEmployeesList: " + sql); 
        try { 
            PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, ciudad);
            ResultSet result = pstmt.executeQuery(); 
            while(result.next()) { 
                EmployeeData empleado = new EmployeeData( 
                    Integer.parseInt(result.getString("EmployeeID")),
                    result.getString("LastName"),
                    result.getString("FirstName")
                );
                vec.addElement(empleado);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getEmployeesList: " + sql + " Exception: " + e);
        }
        return vec;
    }
}