package com.artbox.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private static final String PASSWORD_REPEAT = "passwordRepeat";

    private Pattern pattern;
    private Matcher matcher;

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
        String passwordRepeat = request.getParameter( PASSWORD_REPEAT );

        String emailMessage;
        String emailMessageTextColor;

//if email non-valid return to reqistration page with message
        if ( !validateEmail( email ) ) {
            emailMessage = "Non-valid email!";
            emailMessageTextColor = "textColorRed";
            request.setAttribute( "emailMessage", emailMessage );
            request.setAttribute( "emailMessageTextColor", emailMessageTextColor );
            log.debug( "sendRedirect from doPost in" + this.getServletName() + " to registration.jsp" );
            request.getRequestDispatcher( "/registration.jsp" ).forward( request, response );
        }

        emailMessage = "Email correct!";
        emailMessageTextColor = "textColorGreen";
        request.setAttribute( "emailMessage", emailMessage );
        request.setAttribute( "emailMessageTextColor", emailMessageTextColor );

        String passwordMessage;
        String passwordMessageTextColor;

//if password non-valid return to reqistration page with message
        if ( !validatePassword( password, passwordRepeat ) ) {
            passwordMessage = "Non-valid password! It should contain any symbol, and be from" +
                    " 5 to 25 symbols long! Or retype it carefully, please!";
            log.error( "Non-valid password! It should contain any symbol, and be from" +
                               " 5 to 25 symbols long!" );
            passwordMessageTextColor = "textColorRed";
            request.setAttribute( "passwordMessage", passwordMessage );
            request.setAttribute( "passwordMessageTextColor", passwordMessageTextColor );
            log.debug( "sendRedirect from doPost in" + this.getServletName() + " to registration.jsp" );
            request.getRequestDispatcher( "/registration.jsp" ).forward( request, response );
        }

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
    /*
    ^			          #start of the line
   [_A-Za-z0-9-\.]{4,31}  #  must contain no less than 4 characters including "_" and ".", but no more than 31
   [A-Za-z]{1}            # If group after @ contains more than one character, the first one ought to be non-numeric
   (\.[A-Za-z]{2,3})      # after the dot we need only two ar three characters
    *                     # (it could be optional)
    $			          #end of the line
    */

    private boolean validateEmail( String email ) {
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\.]{4,31}@"
                        + "[A-Za-z]{1}([A-Za-z0-9-])*(\\.[A-Za-z]{2,3})*$";
        pattern = Pattern.compile( EMAIL_PATTERN );
        matcher = pattern.matcher( email );
        return matcher.matches();
    }

    //Password should contain any symbol, and be from 5 to 25 symbols long!"
    private boolean validatePassword( String password, String passwordRepeat ) {
        final String PASSWORD_PATTERN =
                "^.{5,25}$";
        pattern = Pattern.compile( PASSWORD_PATTERN );
        matcher = pattern.matcher( password );
        return ( matcher.matches() && password.equals( passwordRepeat ) );
    }
}