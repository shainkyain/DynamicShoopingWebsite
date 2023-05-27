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

@WebServlet("/updatePhone")
public class LastBySwear extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		PrintWriter out;
        HttpSession ss=req.getSession();
        String str=ss.getAttribute("phone")+"";
        long phone=Long.parseLong(str.substring(2));
		try {
			out = res.getWriter();
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ShopWebsiteDataBase", "postgres", "root");
            PreparedStatement stm = con.prepareStatement("select * from UserInfo where phone=?"); 
            stm.setLong(1, phone);
            ResultSet rs=stm.executeQuery();
            if(rs.next())
            {
            	if(req.getParameter("pass").equals(req.getParameter("phone")))
            	{
                    PreparedStatement stm1 = con.prepareStatement("update table userInfo set password =? where phone=?"); 
                    
                    stm1.setString(1, req.getParameter("pass"));
                    stm1.setLong(2, phone);
                    stm1.executeUpdate();
                    out.println("Password Change SuccesFully !!!");
            	}
            	else
            	{
            		out.println("Password Invalid");
            		res.sendRedirect("changing.html");
            	}
            }
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
