package com.artbox.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artbox.model.ArtBox;
import com.artbox.builder.ArtBoxBuilder;
import com.artbox.storage.ArtBoxStorage;
import org.apache.log4j.Logger;

@WebServlet( "/add" )
public class AddServlet extends HttpServlet {

    private static final long serialVersionUID = 485135717800530684L;

    private static final String ART_BOX_THEME = "theme";
    private static final String ART_BOX_RECOMMENDED_AGE = "age";
    private static final String ART_BOX_COST = "cost";

    private static final Logger log = Logger.getLogger( AddServlet.class );

    public AddServlet() {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        log.debug( "Redirected from doGet in " + this.getServletName() + " to add.jsp" );
        response.sendRedirect( "add.jsp" );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String theme = request.getParameter( ART_BOX_THEME );
        String ageStr = request.getParameter( ART_BOX_RECOMMENDED_AGE );
        String costStr = request.getParameter( ART_BOX_COST );

        String message;
        String textColor;

        try {

            int age = Integer.parseInt( ageStr );
            float cost = Float.parseFloat( costStr );
            log.debug( age + " and " + cost + " were successfully parsed in: " + this.getServletName() );

            ArtBoxStorage artboxStorage = ArtBoxStorage.getInstance();
            ArtBox artBox = new ArtBoxBuilder().theme( theme ).age( age ).cost( cost ).build();
            log.info( artBox + " was generated." );

            message = "Error! Artbox can't be added!";
            textColor = "textColorRed";

            if ( artboxStorage.add( artBox ) ) {

                log.info( "Success! Artbox '" + theme + "' has been added!" );
                message = "Success! Artbox '" + theme + "' has been added!";
                textColor = "textColorGreen";
            }

        } catch ( NumberFormatException nfe ) {

            log.error( "Error! Number format error in " + this.getServletName() + " : ", nfe );
            message = "Error! Number format error! Please enter correct values for ArtBox'es 'theme', 'age' and 'cost'!";
            textColor = "textColorRed";
        }
        request.setAttribute( "message", message );
        request.setAttribute( "textColor", textColor );
        log.debug( "sendRedirect from doPost in" + this.getServletName() + " to add.jsp" );
        request.getRequestDispatcher( "/add.jsp" ).forward( request, response );
    }
}