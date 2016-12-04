import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cmmdc")
public class CmmdcServlet extends HttpServlet {
	private static final long serialVersionUID = 8973486170260321980L;

	public long cmmdc(long m, long n) {
		long r, c;
		do {
			c = n;
			r = m % n;
			m = n;
			n = r;
		} while (r != 0);
		return c;
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		String sm = req.getParameter("m"), sn = req.getParameter("n");
		String tip = req.getParameter("tip");
		long m = Long.parseLong(sm), n = Long.parseLong(sn);
		long x = cmmdc(m, n);
		PrintWriter out = res.getWriter();
		if (tip.equals("text/html")) {
			String title = "Cmmdc Servlet";
			res.setContentType("text/html");
			out.println("<HTML><HEAD><TITLE>");
			out.println(title);
			out.println("</TITLE></HEAD><BODY>");
			out.println("<H1>" + title + "</H1>");
			out.println("<P>Cmmdc is " + x);
			out.println("</BODY></HTML>");
		} else {
			res.setContentType("text/plain");
			out.println(x);
		}
		out.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
}