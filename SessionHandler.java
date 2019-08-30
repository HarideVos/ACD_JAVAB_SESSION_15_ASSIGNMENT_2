
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionHandler
 */
@WebServlet("/session")
public class SessionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionHandler() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = null;
		session = request.getSession();
		String c = (String)session.getAttribute("count");
		if (c == null) {
			c = "0";
			session.setAttribute("count", "0");
		}
		int count = Integer.parseInt(c);
		session.setAttribute("count", String.valueOf(++count));
		
		
		response.getWriter().append("<a href='session'>[Reload]</a><form action='SessionDestroyer' method='post'><input type='submit' value='Destroy'></form><br />");
		
		if (session.isNew()) response.getWriter().append("<h1> Session created Successfully </h1>");
		response.getWriter().append("<h1> Session Handler </h1>");
		response.getWriter().append("New Session: " + session.isNew() + "<br />");
		response.getWriter().append("Session ID: " + session.getId() + "<br />");
		response.getWriter().append("Date of Creation: " + df.format(session.getCreationTime()) + "<br />");
		response.getWriter().append("Date of Last Access: " + df.format(session.getLastAccessedTime()) + "<br />");
		response.getWriter().append("Number of Access: " + count + "<br />");	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = null;
		
		response.getWriter().append("<a href='session'>[Reload]</a><form action='SessionDestroyer' method='post'><input type='submit' value='Destroy'></form><br />");
		response.getWriter().append("<h1> Session Handler </h1>");
	
		session = request.getSession(false);
	
		if (session != null) {
			response.getWriter().append("closing session...<br />");
			response.getWriter().append("Session ID: " + session.getId() + "<br />");
			session.invalidate();
		} else {
			
			response.getWriter().append("No Session found.");
			
		}
		
	}

}
