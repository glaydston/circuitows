package br.com.circuito.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import br.com.circuito.bo.Parser;

import com.google.gson.Gson;

/**
 * Servlet implementation class CircuitoServlet
 */
@WebServlet("/event")
public class CircuitoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CircuitoServlet() {
        // TODO Auto-generated constructor stub
    }

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
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Parser parser = new Parser();
		String place = (StringUtils.isEmpty(request.getParameter("place")))? null : request.getParameter("place");
		String dateStr= (StringUtils.isEmpty(request.getParameter("date")))? null : request.getParameter("date");

		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
		} catch (ParseException e) {
			date = new Date();
		}
		
		String json = parser.request(date);		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}

}
