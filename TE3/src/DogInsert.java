
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DogInsert")
public class DogInsert extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public DogInsert() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name = request.getParameter("NAME");
      String breed = request.getParameter("BREED");
      String favToy = request.getParameter("FAVORITE_TOY");
      String aggression = request.getParameter("AGGRESSION");
      
      Connection connection = null;
      String insertSql = " INSERT INTO DogTable (id, NAME, BREED, FAVORITE_TOY, AGGRESSION) values (default, ?, ?, ?, ?)";

      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, 	name);
         preparedStmt.setString(2, breed);
         preparedStmt.setString(3, favToy);
         preparedStmt.setString(4, aggression);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Dog to Database";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Name</b>: " + name + "\n" + //
            "  <li><b>Breed</b>: " + breed + "\n" + //
            "  <li><b>Favorite Toy</b>: " + favToy + "\n" + //
            "  <li><b>Aggression</b>: " + aggression + "\n" + //

            "</ul>\n");

      out.println("<a href=DogFormSearch.html>Search Dogs</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
