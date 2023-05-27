package com.ServletGroup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/passRetrieve")
public class forgetPass extends HttpServlet 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8854264322359467392L;
	static int otp;
	protected void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		try {
			PrintWriter out = res.getWriter();
			Random ram=new Random();
			otp=ram.nextInt(99999);
			String apiKey = "apikey=" + "NjU1NzUzNmI0NTM4NjI1NjRmNmQ2NDYzNTI2NDQ4NDQ=";
			String message = "&message=" + otp;
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + Long.parseLong(91+""+req.getParameter("phone"));
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println(stringBuffer.toString());
            HttpSession hss=req.getSession();
            hss.setAttribute("phone",req.getParameter("phone"));
            hss.setAttribute("otp",otp);
            res.sendRedirect("OtpValidate.html");
			
			
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
		}
	}

}
