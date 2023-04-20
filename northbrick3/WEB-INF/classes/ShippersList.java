import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class ShippersList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		String codigo =req.getParameter("cod");
        toClient.println(Utils.header("Shippers"));
        toClient.println("<table border='1'>");
        //shipperList = ShippersData.getShippersList(connection, codigo);
        //shipperinfo = ShippersData.getShipperInfo(connection, codigo);
		toClient.println("<tr><td>Id</td><td>CompanyName</td><td>Phone</td></tr>");
        Vector<ShippersData> shipperList= new Vector <ShippersData>();
        ShippersData shipperinfo=null;
		int aux= Integer.parseInt(codigo);
        
        if (aux!=4){
            shipperinfo = ShippersData.getShipperInfo(connection, codigo);
            shipperList.add(shipperinfo);
        }else{
            shipperList = ShippersData.getShippersList(connection, codigo);
        }
        
        for(int i=0; i< shipperList.size(); i++){
                ShippersData shipper = shipperList.elementAt(i);
                toClient.println("<tr>");
                toClient.println("<td>" + shipper.shipperId + " </td>");
                toClient.println("<td>" + shipper.companyname + " </td>");
                toClient.println("<td>" + shipper.phone + " </td>");
                toClient.println("</tr>");
        }

        toClient.println("</table>");
        toClient.println(Utils.footer("Suppliers"));
        toClient.close();
    }
}