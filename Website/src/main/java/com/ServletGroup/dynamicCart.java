package com.ServletGroup;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dynamicCartServlet")
public class dynamicCart extends HttpServlet 
{
	private static final long serialVersionUID = -688557614474425351L;
	static String itemid,Q1,Q2,Q3,Q4;
	static int i;
	protected void service(HttpServletRequest req,HttpServletResponse res) throws IOException 
	{
		    PrintWriter out=res.getWriter();
			Enumeration<String> pn=req.getParameterNames();
            while(pn.hasMoreElements())
            {
            	itemid=pn.nextElement();
            	
            }
			try {
				Class.forName("org.postgresql.Driver");
		        HttpSession hs=req.getSession();
		        hs.setAttribute("mostImpItemID",itemid);
	            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ShopWebsiteDataBase", "postgres", "root");
	            PreparedStatement stm = con.prepareStatement("select ImagePath,itemname,price,features from item where itemid=?");  
	            stm.setInt(1, Integer.parseInt(itemid));
	            ResultSet rs=stm.executeQuery();
	        Q1="<!DOCTYPE html>\r\n"
	        		+ "<html lang=\"en\">\r\n"
	        		+ "\r\n"
	        		+ "<head>\r\n"
	        		+ "    <meta charset=\"UTF-8\">\r\n"
	        		+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
	        		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
	        		+ "    <title>Document</title>\r\n"
	        		+ "    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n"
	        		+ "    <link rel=\"stylesheet\" href=\"womencss.css\">\r\n"
	        		+ "</head>\r\n"
	        		+ "\r\n"
	        		+ "<body style=\"background-color: rgb(140, 148, 145);\">\r\n"
	        		+ "    <nav class=\"navbar navbar-expand-lg navbar-light colring\">\r\n"
	        		+ "        <div class=\"container-fluid\">\r\n"
	        		+ "            <a class=\"navbar-brand\" href=\"#\">ShoppingSite</a>\r\n"
	        		+ "            <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\"\r\n"
	        		+ "                data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\"\r\n"
	        		+ "                aria-label=\"Toggle navigation\">\r\n"
	        		+ "                <span class=\"navbar-toggler-icon\"></span>\r\n"
	        		+ "            </button>\r\n"
	        		+ "            <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\r\n"
	        		+ "                <ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">\r\n"
	        		+ "                    <li class=\"nav-item\">\r\n"
	        		+ "                        <a class=\"nav-link active\" aria-current=\"page\" href=\"#\">Home</a>\r\n"
	        		+ "                    </li>\r\n"
	        		+ "                    <li class=\"nav-item\">\r\n"
	        		+ "                        <a class=\"nav-link\" href=\"#\">Trending</a>\r\n"
	        		+ "                    </li>\r\n"
	        		+ "                    <li class=\"nav-item \">\r\n"
	        		+ "                        <a class=\"nav-link\" href=\"#\" role=\"button\">\r\n"
	        		+ "                            Complain\r\n"
	        		+ "                        </a>\r\n"
	        		+ "                    </li>\r\n"
	        		+ "                    <li class=\"nav-item\">\r\n"
	        		+ "                        <a class=\"nav-link\" href=\"add\" role=\"button\">Items</a>\r\n"
	        		+ "                    </li>\r\n"
	        		+ "                </ul>\r\n"
	        		+ "                <form class=\"d-flex\">\r\n"
	        		+ "                    <input class=\"form-control me-2\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\r\n"
	        		+ "                    <button class=\"btn btn-outline-success\" type=\"submit\">Search</button>\r\n"
	        		+ "                </form>\r\n"
	        		+ "            </div>\r\n"
	        		+ "        </div>\r\n"
	        		+ "    </nav>\r\n"
	        		+ "\r\n"
	        		+ "\r\n"
	        		+ "    <section class=\"product\">\r\n"
	        		+ "        <div class=\"container py-5\">\r\n"
	        		+ "            <form action=\"lastamlast\">\r\n"
	        		+ "            <div class=\"row\">\r\n"
	        		+ "                <div class=\"col text-center\">\r\n"
	        		+ "                    <div class=\"card border-0 bg-light mb-2\">\r\n"
	        		+ "                        <div class=\"card-body\">\r\n"
	        		+ "                            <img src=\"";
	        while(rs.next())
	        {
	        	String ImagePath=rs.getString("ImagePath");
	        	String name=rs.getString("itemname");
	        	int price=rs.getInt("price");
	        	String features=rs.getString("features"); 
	        	Q2=ImagePath+"\" class=\"img-fluid1\" alt=\"\">\r\n"
	        			+ "                        </div>\r\n"
	        			+ "                    </div>\r\n"
	        			+ "                    <h6>"
	        			+name+"</h6>\r\n"
	        					+ "                    <p>price : "
	        			+price+"</p>\r\n"
	        					+ "                    <input type=\"submit\" class=\"btn btn-outline-success\" type=\"submit\" value=\"Add to Cart\">\r\n"
	        					+ "                    <!--<p class=\"py-2\">Addes Succesful</p>-->\r\n"
	        					+ "                </div>\r\n"
	        					+ "                <div class=\"col\">\r\n"
	        					+ "                    <p style=\"color: rgb(114, 55, 129);\">"
	        					+features+"</p>\r\n"
	        							+ "                </div>\r\n"
	        							+ "            </div>\r\n"
	        							+ "        </form>\r\n"
	        							+ "        </div>\r\n"
	        							+ "    </section>";
	        }
	        Q3="</body>\r\n"
	        		+ "\r\n"
	        		+ "</html>";
	        
	        Q4=Q1+Q2+Q3;
	        out.println(Q4);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
		
	}

}
