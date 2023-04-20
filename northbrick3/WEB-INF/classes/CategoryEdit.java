import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class CategoryEdit extends HttpServlet {
    Connection connection;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		toClient.println(Utils.header("Category Form"));
		int idStr = Integer.parseInt(req.getParameter("codigo"));
		//Vector<CategoryData> Categories = CategoryData.getProductList(connection);

		CategoryData category = CategoryData.getCategoryEdit(connection, idStr);
        toClient.println("<form  action='CategoryUpdate' method='GET'>");
        toClient.println("<input name='categoryId' type='hidden' value="+category.categoryId+"></input>");
        toClient.println("<table border='1'>"); 
        
        toClient.println("<tr><td>Id</td>");
        toClient.println("<td>"+category.categoryId+"</td></tr>");
        
		toClient.println("<tr><td>Name</td>");
		//categoryName va a aparecer en la url
        toClient.println("<td><input name='categoryName' value='" + category.categoryName + "'></td></tr>");
        
        toClient.println("<tr><td>Description</td>");
        toClient.println("<td><input name='description' value='" + category.categoryDescription + "'></td></tr>");
        
        toClient.println("</table>");
        toClient.println("<input type='submit'>");
        toClient.println("</form>");
        toClient.println(Utils.footer("Category Form"));
        toClient.close();
    }
}