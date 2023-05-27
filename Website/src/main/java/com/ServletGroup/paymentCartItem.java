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

@WebServlet("/myCart")
public class paymentCartItem extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String Q1,Q2,Q3,Q4,email;
	static int Amount;
	protected void doPost(HttpServletRequest req,HttpServletResponse res) 
	{
		PrintWriter out;
		HttpSession ss;
		ss=req.getSession();
			try 
			{
				out = res.getWriter();
				email=(String) ss.getAttribute("email");
				
				Q1="<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Document</title>\r\n"
						+ "    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">\r\n"
						+ "    <link rel=\"stylesheet\" href=\"womencss.css\">\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
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
						+ "    </nav>";
				Q2="<i>";
                Class.forName("org.postgresql.Driver");
                Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ShopWebsiteDataBase", "postgres", "root");
                PreparedStatement stm = con.prepareStatement("select imagepath,name,price,features from userinfo,cart,item where userinfo.email=cart.email and item.itemid=cart.itemid and cart.email=?;");     
                stm.setString(1, email);
                ResultSet rs=stm.executeQuery();
                while(rs.next())
                {
                	String path=rs.getString("imagepath");
                	String name=rs.getString("name");
                	int price=rs.getInt("price");
                	String feature=rs.getString("features");
                	Q2=Q2+"    <section class=\"product\">\r\n"
                			+ "        <div class=\"container py-5\">\r\n"
                			+ "            <div class=\"row\">\r\n"
                			+ "                <div class=\"col text-center\">\r\n"
                			+ "                    <div class=\"card border-0 bg-light mb-2\">\r\n"
                			+ "                        <div class=\"card-body\">\r\n"
                			+ "                            <a href=\"\" name=\"1\"><img src=\""+path
                			+"\" class=\"img-fluid1\" alt=\"\"></a>\r\n"
                			+ "                        </div>\r\n"
                			+ "                    </div>\r\n"
                			+ "                    <h6>"+name
                			+"</h6>\r\n"
                			+ "                    <p>price: "+price
                			+"</p>\r\n"
                			+ "                </div>\r\n"
                			+ "                <div class=\"col\">\r\n"
                			+ "                    <p style=\"color: rgb(114, 55, 129);\">"+feature
                			+"                    </p>\r\n"
                			+ "                </div>\r\n"
                			+ "            </div>\r\n"
                			+ "\r\n"
                			+ "            \r\n"
                			+ "        </div>\r\n"
                			+ "    </section>";
                }
                Amount=0;
                
                stm = con.prepareStatement("select sum(price) as income from userinfo,cart,item where userinfo.email=cart.email and item.itemid=cart.itemid and cart.email=?;");
                stm.setString(1, email);
                rs=stm.executeQuery();
                if(rs.next())
                {
                	Amount=Amount+rs.getInt("income");
                }
                Q3="    <section class=\"total\">\r\n"
                		+ "        <div class=\"container py-4\">\r\n"
                		+ "            <div class=\"row\">\r\n"
                		+ "                <div class=\"col text-center\">\r\n"
                		+ "                    Total Amount\r\n"
                		+ "                </div>\r\n"
                		+ "                <div class=\"col text-center\">\r\n"
                		+ "                     "+Amount
                		+"                </div>\r\n"
                		+ "            </div>\r\n"
                		+ "        </div>\r\n"
                		+ "    </section>\r\n"
                		+ "    \r\n"
                		+ "</body>\r\n"
                		+ "</html>";
                Q4=Q1+Q2+Q3;
                out.println(Q4);
                
			} 
			catch (IOException | ClassNotFoundException | SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
