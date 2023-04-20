import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class SupplierList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		String pais =req.getParameter("country");
        toClient.println(Utils.header("Suppliers from "+pais+"<br>Developed by Marga"));
        toClient.println("<table border='1'>");
        Vector<SupplierData> supplierList;
        supplierList = SupplierData.getSuppliersList(connection, pais);
		toClient.println("<tr><td>ID</td><td>ContactName</td><td>City</td><td>Edit </td></tr>");
		
        for(int i=0; i< supplierList.size(); i++){
                SupplierData supplier = supplierList.elementAt(i);
                toClient.println("<tr>");
                toClient.println("<td>" + supplier.SupplierId + " </td>");
                toClient.println("<td>" + supplier.ContactName + " </td>");
                toClient.println("<td>" + supplier.City + " </td>");
				toClient.println("<td><a href=SupplierEdit?supplierId=" + supplier.SupplierId + ">Edit supplier</a></td>");
                toClient.println("</tr>");
        }

        toClient.println("</table>");
        toClient.println(Utils.footer("Suppliers"));
        toClient.close();
    }
}