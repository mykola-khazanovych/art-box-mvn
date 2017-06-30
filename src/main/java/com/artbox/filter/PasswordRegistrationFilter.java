package com.artbox.filter;

import com.artbox.servlet.RegistrationServlet;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static com.artbox.util.RegistrationValidator.validatePassword;

/**
 * Created by mykola.khazanovych on 6/30/2017.
 */
@WebFilter( filterName = "PasswordRegistrationFilter" )
public class PasswordRegistrationFilter implements Filter {

    private static final Logger log = Logger.getLogger( RegistrationServlet.class );
    private static final String PASSWORD = "password";
    private static final String PASSWORD_REPEAT = "passwordRepeat";

    public void destroy() {
    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        String password = request.getParameter( PASSWORD );
        String passwordRepeat = request.getParameter( PASSWORD_REPEAT );

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
            log.debug( "sendRedirect from doPost in" + this.getClass().getName() + " to registration.jsp" );
            request.getRequestDispatcher( "/registration.jsp" ).forward( request, response );
        }


        chain.doFilter( request, response );
    }

    public void init( FilterConfig config ) throws ServletException {

    }

}
