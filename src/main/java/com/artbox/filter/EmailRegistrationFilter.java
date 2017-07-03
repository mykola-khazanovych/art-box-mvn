package com.artbox.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static com.artbox.util.RegistrationValidator.validateEmail;

/**
 * Created by mykola.khazanovych on 6/30/2017.
 */
@WebFilter( "/register" )
public class EmailRegistrationFilter implements Filter {

    private static final String EMAIL = "email";
    private static final Logger log = Logger.getLogger( EmailRegistrationFilter.class );


    public void destroy() {
    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        String email = request.getParameter( EMAIL );
        String emailMessage;
        String emailMessageTextColor;

//if email non-valid return to registration page with message
        if ( !validateEmail( email ) ) {
            log.debug("Non-valid email entered!" + this.getClass().getName());
            emailMessage = "Non-valid email!";
            emailMessageTextColor = "textColorRed";
            request.setAttribute( "emailMessage", emailMessage );
            request.setAttribute( "emailMessageTextColor", emailMessageTextColor );
            log.debug( "sendRedirect from doPost in" + this.getClass()
                                                           .getName() + " to registration.jsp" );
            request.getRequestDispatcher( "/registration.jsp" ).forward( request, response );
        }

        emailMessage = "Email correct!";
        emailMessageTextColor = "textColorGreen";
        request.setAttribute( "emailMessage", emailMessage );
        request.setAttribute( "emailMessageTextColor", emailMessageTextColor );

        chain.doFilter( request, response );
    }

    public void init( FilterConfig config ) throws ServletException {

    }
}
