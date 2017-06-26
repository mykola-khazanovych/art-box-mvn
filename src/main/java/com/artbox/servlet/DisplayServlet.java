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

@WebServlet( "/list" )
public class DisplayServlet extends HttpServlet {

    private static final long serialVersionUID = 485135717800530684L;
    private static final Logger log = Logger.getLogger( DisplayServlet.class );

    public DisplayServlet() {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        ArtBoxStorage storage = ArtBoxStorage.getInstance();
        Map<Integer, ArtBox> artBoxCollection = storage.getAll();
        log.info( "Art box collection:" + artBoxCollection + " loaded in " + this.getServletName() );

        request.setAttribute( "products", artBoxCollection.entrySet() );

        String message = "";
        if ( artBoxCollection.isEmpty() ) {
            message = "Sorry! Database is empty!";
            log.warn( "Trying to list an empty collection in " + this.getServletName() );
            request.setAttribute( "message", message );
            request.setAttribute( "textColor", "textColorRed" );
        }

        request.getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
        log.debug( "Forwarded updated request from doGet in " + this.getServletName() );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        this.doGet( request, response );
        log.debug( "Redirection from doGet in " + this.getServletName() );
    }
}