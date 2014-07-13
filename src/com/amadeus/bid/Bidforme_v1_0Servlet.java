package com.amadeus.bid;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Bidforme_v1_0Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
