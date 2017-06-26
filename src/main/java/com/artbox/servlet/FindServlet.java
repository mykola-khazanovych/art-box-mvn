package com.artbox.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artbox.model.ArtBox;
import com.artbox.storage.ArtBoxStorage;
import org.apache.log4j.Logger;

@WebServlet( "/find" )
public class FindServlet extends HttpServlet {

    private static final long serialVersionUID = 485135717800530684L;
    private static final String ART_BOX_THEME = "theme";
    private static final Logger log = Logger.getLogger( FindServlet.class );

    public FindServlet() {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String theme = request.getParameter( ART_BOX_THEME );

        ArtBoxStorage storage = ArtBoxStorage.getInstance();
        ArtBox findArtBox = storage.findByTheme( theme );
        Map<Integer, ArtBox> artBoxCollection = storage.getAll();
        log.info( "Art box collection loaded in " + this.getServletName() );

        String message = "ERROR! There is no ArtBox with entered theme \'" + theme + "\' in the storage!";
        String textColor = "textColorRed";

        if ( theme.isEmpty() ) {
            message = "";
            if ( artBoxCollection.isEmpty() ) {
                log.warn( "Trying to proceed an empty collection in " + this.getServletName() );
                message = "Sorry! Database is empty!";
                textColor = "textColorRed";
            }
            request.setAttribute( "products", artBoxCollection.entrySet() );
        }

        if ( findArtBox != null ) {
            log.info( "ArtBox with theme \"" + theme + "\" has been found in" + this.getServletName() );
            message = "ArtBox with theme \"" + theme + "\" has been found!";
            textColor = "textColorGreen";
            request.setAttribute( "artbox", findArtBox );
        }

        request.setAttribute( "message", message );
        request.setAttribute( "textColor", textColor );
        log.debug( "Forwarded updated request from doGet in: " + this.getServletName() + " to dashboard.jps!" );
        request.getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        log.debug( "Redirection from doPost into doGet in: " + this.getServletName() );
        this.doGet( request, response );
    }
}