import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryData {
    int    categoryId;
    String categoryName;
    String categoryDescription;


    CategoryData (int categoryId, String categoryName, String categoryDescription) { // constructor (nombre igual a la clase)
        this.categoryId    = categoryId; // referirte a los que estan arriba y lo igualas a lo que le ingresas.
        this.categoryName  = categoryName;
        this.categoryDescription = categoryDescription;

    }


    public static Vector<CategoryData> getProductList(Connection connection) { // metodo llamado getProductList y devuelve un vector de tipo CategoryData.
        Vector<CategoryData> vec = new Vector<CategoryData>();
//definir el sql
        String sql = "Select CategoryID, CategoryName, Description FROM Categories";
        System.out.println("getProductList: " + sql); // verificar lo que hago
        try { // para poder detectar un error. TRY Y CATCH SIEMPRE ES IGUAL
            Statement statement=connection.createStatement(); // lo que antes se hacia con el odcb
            ResultSet result = statement.executeQuery(sql); // tipo de variables que te devuelve el sql. EXECUTE O UPDATE dependiendo de lo que te pidan
            while(result.next()) { // recorro todos los datos que tengo en el vector
			// estoy creando un product( una caja con un categoryID, name y description)
                CategoryData product = new CategoryData( 
                    Integer.parseInt(result.getString("CategoryID")),
                    result.getString("CategoryName"),
                    result.getString("Description")
                );
                vec.addElement(product);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProductList: " + sql + " Exception: " + e); // cambio respecto al metodo
        }
        return vec;
    }
    public static Vector<ProductData> getCategoryProductList(Connection connection, String id) {
        Vector<ProductData> vec = new Vector<ProductData>();
        String sql = "Select ProductId, ProductName, Products.SupplierId as SupplierId, CompanyName, UnitPrice FROM Products, Suppliers";
        sql += " WHERE Products.Category = Categories.CategoryName AND CategoryID=?";
        System.out.println("getProductList: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql); // PREPARED ES PORQUE TIENES UN ? ANTES
            pstmt.setInt(1, Integer.parseInt(id));  
            ResultSet result = pstmt.executeQuery(sql);
            while(result.next()) {
                ProductData product = new ProductData(
                    result.getString("ProductId"),
                    result.getString("ProductName"),
                    Integer.parseInt(result.getString("SupplierId")),
                    result.getString("CompanyName"),
                    Float.parseFloat(result.getString("UnitPrice"))
                );
                vec.addElement(product);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProductList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    // vamos a crear un nuevo metodo para category edition
    // METODO PARA SELECCIONAR UNA CATEGORIA EN ESPECiFICO


public static CategoryData getCategoryEdit(Connection connection, int id) {
    Vector<CategoryData> vec = new Vector<CategoryData>();
    String sql = "Select CategoryID, CategoryName, Description FROM Categories";
    sql += " WHERE CategoryID=?";
    System.out.println("getCategoryEdit: " + sql);
    try {
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet result = pstmt.executeQuery();
        while(result.next()) {
            CategoryData category = new CategoryData(
            Integer.parseInt(result.getString("CategoryId")),
            result.getString("CategoryName"),
            result.getString("Description")
            );
            vec.addElement(category);
        }
    }
            catch(SQLException e) {
                e.printStackTrace();
                System.out.println("Error in getCategoryEdit: " + sql + " Exception: " + e);
            }
                return vec.elementAt(0);
}
}


