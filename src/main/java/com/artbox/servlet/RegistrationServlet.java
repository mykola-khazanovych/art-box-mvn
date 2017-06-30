package com.artbox.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artbox.builder.UserBuilder;
import com.artbox.model.User;
import com.artbox.storage.UserStorage;
import org.apache.log4j.Logger;

@WebServlet( "/register" )
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 485135717800530684L;

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private static final Logger log = Logger.getLogger( RegistrationServlet.class );

    public RegistrationServlet() {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        log.warn( "There was a call of doGet in " + this.getServletName() + " : " + request.getRequestURI() );

    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String email = request.getParameter( EMAIL );
        String password = request.getParameter( PASSWORD );

        //If all correct adding User to storage
        User user = new UserBuilder().email( email ).password( password ).build();
        log.info( "User: " + user + " created successfully! In " + this.getServletName() );

        String message;
        String textColor;
        UserStorage storage = UserStorage.getInstance();
        if ( !storage.add( user ) ) {
            log.error( "An error occured during adding the user: " + user + " to storage!" );
            message = "An error occured during adding the user to storage!";
            textColor = "textColorRed";
            request.setAttribute( "message", message );
            request.setAttribute( "textColor", textColor );
            log.debug( "sendRedirect from doPost in" + this.getServletName() + " to registration.jsp" );
            request.getRequestDispatcher( "/registration.jsp" ).forward( request, response );
        }

        //If all correct we redirect user with success message to Home page

        log.debug( "sendRedirect from doPost in" + this.getServletName() + " to registration.jsp" );
        request.setAttribute( "message", "Email was successfully registered!" );
        request.getRequestDispatcher( "/index.jsp" ).forward( request, response );
    }
}
