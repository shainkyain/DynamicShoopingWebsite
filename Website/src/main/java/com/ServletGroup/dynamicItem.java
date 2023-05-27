package com.ServletGroup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dynamicItemPage")
public class dynamicItem extends HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String Q1,Q2,Q3;
	protected void service(HttpServletRequest req,HttpServletResponse res) 
	{
		PrintWriter out;
		
		try 
		{
			HttpSession ss=req.getSession();
			out = res.getWriter();
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ShopWebsiteDataBase", "postgres", "root");
            PreparedStatement stm = con.prepareStatement("select * from item");         
            ResultSet rs=stm.executeQuery();
            Q1="<!DOCTYPE html>\r\n"
            		+ "<html lang=\"en\">\r\n"
            		+ "<head>\r\n"
            		+ "    <meta charset=\"UTF-8\">\r\n"
            		+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
            		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
            		+ "    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n"
            		+ "    <link rel=\"stylesheet\" href=\"womencss.css\">\r\n"
            		+ "    <title>Document</title>\r\n"
            		+ "</head>\r\n"
            		+ "<body>\r\n"
            		+ "  <nav class=\"navbar navbar-expand-lg navbar-light colring\">\r\n"
            		+ "    <div class=\"container-fluid\">\r\n"
            		+ "      <a class=\"navbar-brand\" href=\"#\">ShoppingSite</a>\r\n"
            		+ "      <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\"\r\n"
            		+ "        aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n"
            		+ "        <span class=\"navbar-toggler-icon\"></span>\r\n"
            		+ "      </button>\r\n"
            		+ "      <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\r\n"
            		+ "        <ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">\r\n"
            		+ "          <li class=\"nav-item\">\r\n"
            		+ "            <a class=\"nav-link active\" aria-current=\"page\" href=\"#\">Home</a>\r\n"
            		+ "          </li>\r\n"
            		+ "          <li class=\"nav-item\">\r\n"
            		+ "            <a class=\"nav-link\" href=\"#\">Trending</a>\r\n"
            		+ "          </li>\r\n"
            		+ "          <li class=\"nav-item \">\r\n"
            		+ "            <a class=\"nav-link\" href=\"#\" role=\"button\">\r\n"
            		+ "              Complain\r\n"
            		+ "            </a>\r\n"
            		+ "          </li>\r\n"
            		+ "          <li class=\"nav-item\">\r\n"
            		+ "            <a class=\"nav-link\" href=\"add\" role=\"button\">Items</a>\r\n"
            		+ "          </li>\r\n"
            		+ "        </ul>\r\n"
            		+ "        <form class=\"d-flex\">\r\n"
            		+ "          <input class=\"form-control me-2\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\r\n"
            		+ "          <button class=\"btn btn-outline-success\" type=\"submit\">Search</button>\r\n"
            		+ "        </form>\r\n"
            		+ "      </div>\r\n"
            		+ "    </div>\r\n"
            		+ "  </nav>\r\n"
            		+ "\r\n"
            		+ "    \r\n"
            		+ "    <section class=\"product\">\r\n"
            		+ "        <div class=\"container py-5\">\r\n"
            		+ "          <div class=\"row\">";
            Q2="<i>";
            while(rs.next())
            {
            	Q2=Q2+"<div class=\"col-lg-3 text-center\">\r\n"
            			+ "              <div class=\"card border-0 bg-light mb-2\">\r\n"
            			+ "                <div class=\"card-body\">\r\n"
            			+ "                  <img src=\""+rs.getString("ImagePath")+"class=\"img-fluid1\" alt=\"\">\r\n"
            			+ "                </div>\r\n"
            			+ "              </div>\r\n"
            			+ "              <form action=\"dynamicCartServlet\">"
            			+ "                <h6>"+rs.getString("itemName")+"</h6>\r\n"
            		    + "                <p>price:₹"+rs.getInt("price")+"</p>\r\n"
            			+ "                <input style=\"background-color:white; border: none; color: burlywood;\" type=\"submit\" "
            			+ "value=\"Buy Now\" name=\""+rs.getInt("itemid")
            			+"\">\r\n"
            			+ "              </form>\r\n"
            			+ "            </div>";
            }
            Q3="          </div>\r\n"
            		+ "        </div>  \r\n"
            		+ "        </section>\r\n"
            		+ "</body>\r\n"
            		+ "</html>";
            out.println(Q1+Q2+Q3);
            /*FileOutputStream fs=new FileOutputStream("dynamicList.html");
            OutputStreamWriter osw=new OutputStreamWriter(fs);
            osw.write(Query);
            osw.close();*/
            
			
		} 
		catch (ClassNotFoundException | SQLException | IOException e) 
		{
			e.printStackTrace();
		}	
	}

}
