package com.artbox.servlet;

import org.apache.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IntroServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 5914647821821437185L;
    private static final Logger log = Logger.getLogger( HomeServlet.class );


    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug( "Redirection from doGet to index.jsp in: : " + this.getServletName() );
        response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug( "Redirection from doPost into doGet in: " + this.getServletName() );
        this.doGet(request, response);
	}

}
