import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class SupplierEdit extends HttpServlet {
    Connection connection;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
	
		int idStr = Integer.parseInt(req.getParameter("supplierId"));

		SupplierData supplier2 = SupplierData.getSupplierInfo(connection, idStr);

		toClient.println(Utils.header("Supplier Form"));
		toClient.println("<form  action=SupplierUpdate method=GET>");
		toClient.println("<table border=1>"); 
        
        toClient.println("<tr><td>Id</td>");
		toClient.println("<td><input name=supplierId value=" + supplier2.SupplierId + "></td></tr>");
        
		toClient.println("<tr><td>Name</td>");
        toClient.println("<td><input name=suppliername value=" + supplier2.ContactName + "></td></tr>");
        
        toClient.println("<tr><td>City</td>");
        toClient.println("<td><input name=City value=" + supplier2.City + "></td></tr>");
        
        toClient.println("<tr><td>Country</td>");
        toClient.println("<td><input name=Country value=" + supplier2.country + "></td></tr>");
        
        toClient.println("</table>");
        toClient.println("<input type=submit>");
        toClient.println("</form>");
        toClient.println(Utils.footer("Supplier Form"));
        toClient.close();
    }
}