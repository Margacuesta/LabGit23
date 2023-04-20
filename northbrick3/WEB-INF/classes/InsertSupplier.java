import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class InsertSupplier extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

		 
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        
		SupplierData nuevoinsert = new SupplierData(
                    0,
                    req.getParameter("name"),
                    req.getParameter("city"),
					req.getParameter("country")
                    );
		int n = SupplierData.insertSupplier(connection, nuevoinsert);

		if (n!=0){
			System.out. println("insertado");
			res.sendRedirect("Countries.html");
		}
		else {
			System.out. println("NO insertado");
		}
		
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        toClient.println(Utils.header("Supplier insert"));
        //OrderEdit.printOrder(toClient, connection, orderId);
        toClient.println(Utils.footer("Supplier insert"));
        toClient.close();
    }
}