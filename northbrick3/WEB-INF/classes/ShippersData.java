import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShippersData {
    int    shipperId;
    String companyname;
    String phone;
    
    ShippersData (int shipperId, String companyname, String phone) {
        this.shipperId = shipperId; 
        this.companyname = companyname;
        this.phone = phone;
    }
// este primer método me va a enseñar toda la base de datos.
    public static Vector<ShippersData> getShippersList(Connection connection, String codigo){ 
        Vector<ShippersData> vec = new Vector<ShippersData>();
        String sql = "Select ShipperID, CompanyName, Phone FROM Shippers";
        System.out.println("getShippersList: " + sql); 
        try { 
            Statement pstmt=connection.createStatement();
            ResultSet result = pstmt.executeQuery(sql); 
            while(result.next()) {
                ShippersData shipper = new ShippersData( 
                    Integer.parseInt(result.getString("ShipperID")),
                    result.getString("CompanyName"),
                    result.getString("Phone")
                );
                vec.addElement(shipper);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getShippersList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static ShippersData getShipperInfo(Connection connection, String codigo) {
	ShippersData shipper2 = null;
    String sql = "Select ShipperID, CompanyName, Phone FROM Shippers WHERE ShipperID=?";
    System.out.println("getShipperInfo: " + sql);
    try {
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1, Integer.parseInt(codigo));
        ResultSet result = pstmt.executeQuery();
        while(result.next()) {
            shipper2 = new ShippersData(
            Integer.parseInt(codigo),
            result.getString("CompanyName"),
            result.getString("Phone")
            );
        }
    }
            catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Error in getShipperInfo: " + sql + " Exception: " + e);
            }
			return shipper2;
    }
    
    public static int insertnewshipper(Connection connection, ShippersData supplier2) {
    String sql = "INSERT INTO Shippers (CompanyName, Phone) VALUES (?, ?)";
    System.out.println("insertnewshipper: " + sql);
	int n=0;
    try {
        PreparedStatement stmtUpdate=connection.prepareStatement(sql);
		stmtUpdate.setString(1, supplier2.companyname);
		stmtUpdate.setString(2,supplier2.phone);
        n= stmtUpdate.executeUpdate();
		stmtUpdate.close();
    }
            catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Error in insertnewshipper: " + sql + " Exception: " + e);
            }
			return n;
    }
    
}