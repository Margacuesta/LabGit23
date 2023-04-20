import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierData {
    int    SupplierId;
    String ContactName;
    String  City;
    String country;
    
    SupplierData (int    SupplierId, String ContactName, String  City) { 
        this.SupplierId    = SupplierId; 
        this.ContactName  = ContactName;
        this.City = City;
    }
    
    SupplierData (int SupplierId, String ContactName, String  City, String country) { 
        this.SupplierId    = SupplierId; 
        this.ContactName  = ContactName;
        this.City = City;
        this.country = country;
    }

    public static Vector<SupplierData> getSuppliersList(Connection connection, String pais){ 
        Vector<SupplierData> vec = new Vector<SupplierData>();
        String sql = "Select SupplierID, ContactName, City FROM Suppliers WHERE Suppliers.Country=?";
        System.out.println("getSuppliersList: " + sql); 
        try { 
            PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, pais);
            ResultSet result = pstmt.executeQuery(); 
            while(result.next()) { 
                SupplierData supplier = new SupplierData( 
                    Integer.parseInt(result.getString("SupplierID")),
                    result.getString("ContactName"),
                    result.getString("City")
                );
                vec.addElement(supplier);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getSuppliersList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static SupplierData getSupplierInfo(Connection connection, int idStr) {
	SupplierData supplier2 = null;
    String sql = "Select SupplierID, ContactName, City, Country FROM Suppliers WHERE SupplierID=?";
    System.out.println("getSupplierInfo: " + sql);
    try {
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1, idStr);
        ResultSet result = pstmt.executeQuery();
        while(result.next()) {
            supplier2 = new SupplierData(
            Integer.parseInt(result.getString("SupplierID")),
            result.getString("ContactName"),
            result.getString("City"),
            result.getString("Country")
            );
        }
    }
            catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Error in getSupplierInfo: " + sql + " Exception: " + e);
            }
			return supplier2;
    }
	
	public static int updateSupplier(Connection connection, SupplierData supplier2) {
    String sql = "UPDATE Suppliers SET ContactName=?, City=?, Country=? WHERE SupplierID=?";
    System.out.println("updateSupplier: " + sql);
	int n=0;
    try {
        PreparedStatement stmtUpdate=connection.prepareStatement(sql);
		stmtUpdate.setString(1, supplier2.ContactName);
		stmtUpdate.setString(2, supplier2.City);
		stmtUpdate.setString(3, supplier2.country);
		stmtUpdate.setInt(4, supplier2.SupplierId);
		
        n= stmtUpdate.executeUpdate();
		stmtUpdate.close();
    }
            catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Error in updateSupplier: " + sql + " Exception: " + e);
            }
			return n;
    }
	
	public static int insertSupplier(Connection connection, SupplierData supplier2) {
    String sql = "INSERT INTO Suppliers (ContactName, City, Country, CompanyName, ContactTitle) VALUES (?, ?, ?, ?, ?)";
    System.out.println("insertSupplier: " + sql);
	int n=0;
    try {
        PreparedStatement stmtUpdate=connection.prepareStatement(sql);
        
		stmtUpdate.setString(1, supplier2.ContactName);
		stmtUpdate.setString(2, supplier2.City);
		stmtUpdate.setString(3, supplier2.country);
		stmtUpdate.setString(4, "Tokyo Traders");
        stmtUpdate.setString(5, "Marketing manager");
        
        n= stmtUpdate.executeUpdate();
		stmtUpdate.close();
    }
            catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Error in insertSupplier: " + sql + " Exception: " + e);
            }
			return n;
    }

}