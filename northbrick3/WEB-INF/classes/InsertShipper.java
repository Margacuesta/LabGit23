import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class InsertShipper extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    } 

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        
		ShippersData nuevoshipper = new ShippersData(
                    0,
                    req.getParameter("companyname"),
                    req.getParameter("phone")
                    );
		int n = ShippersData.insertnewshipper(connection, nuevoshipper);

		if (n!=0){
			System.out. println("insertado");
			res.sendRedirect("Shippers.html");
		}
		else {
			System.out. println("NO insertado");
		}
		
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();

        toClient.println(Utils.header("Shipper insert"));
        //OrderEdit.printOrder(toClient, connection, orderId);
        toClient.println(Utils.footer("Shipper insert"));
        toClient.close();
    }
}