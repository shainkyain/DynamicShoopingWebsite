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

@WebServlet("/FinalRound")
public class last extends HttpServlet 
{
	private static final long serialVersionUID = -5462920197248174741L;

	protected void doPost(HttpServletRequest req,HttpServletResponse res)
	{
			try {
				PrintWriter out=res.getWriter();
	            HttpSession ss=req.getSession();
	            long otp=(long)ss.getAttribute("otp");
	            if(Long.parseLong(req.getParameter("pass"))!=Long.parseLong(req.getParameter("phone"))&& Long.parseLong(req.getParameter("pass"))==otp)
	            {
	            	res.sendRedirect("changing.html");
	            }
	            else
	            {
	            	out.println("Invalid OTP");
	            	res.sendRedirect("OtpValidate.html");
	            }
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

	}
}
