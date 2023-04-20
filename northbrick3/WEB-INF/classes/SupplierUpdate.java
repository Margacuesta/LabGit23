import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class SupplierUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String id = req.getParameter("supplierId");
		
		SupplierData supplier2 = new SupplierData(
                    Integer.parseInt(req.getParameter("supplierId")),
                    req.getParameter("suppliername"),
					req.getParameter("City"),
					req.getParameter("Country")
                );
				
				System.out.println("SupplierUpdate: "+ id);
		
		 int n2 = SupplierData.updateSupplier(connection, supplier2);
		 System.out.println("n2: " + n2);
		
		
		//res.sendRedirect("SupplierEdit?supplierId=" + id + "&a=" + Math.random());
		res.sendRedirect("Countries.html");
    }
}