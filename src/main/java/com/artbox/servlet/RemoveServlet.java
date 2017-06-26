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
import org.apache.jasper.tagplugins.jstl.core.Remove;
import org.apache.log4j.Logger;

@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {

	private static final long serialVersionUID = 485135717800530684L;
	private static final String ART_BOX_ID = "id";
	private static final Logger log = Logger.getLogger( RemoveServlet.class );


	public RemoveServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    log.warn( "There was a call of doGet in " + this.getServletName() + " : " + request.getRequestURI() );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String stringId = request.getParameter(ART_BOX_ID);
		int id;
		String message;
		String textColor;

		try {

			id = Integer.parseInt(stringId);
			log.debug( id + " was successfully parsed in: " + this.getServletName() );
			ArtBoxStorage storage = ArtBoxStorage.getInstance();
			Map<Integer, ArtBox> artBoxCollection = storage.getAll();
            log.info( "Art box collection:" + artBoxCollection + " loaded in " + this.getServletName() );

			request.setAttribute("products", artBoxCollection.entrySet());

			message = "ERROR! There is no ArtBox with id" + id + " in the storage!";
			textColor = "textColorRed";
			if (storage.removeById(id)) {
			    log.info( "ArtBox with id=" + id + " has been successfully removed!"  );
				message = "ArtBox with id=" + id + " has been successfully removed!";
				textColor = "textColorGreen";
				
			}
			
		} catch (NumberFormatException nfe) {
		    log.error( "You've entered incorrect 'id' value! = " + stringId );
			message = "You've entered incorrect 'id' value! = " + stringId;
			textColor = "textColorRed";
		}

		request.setAttribute("message", message);
		request.setAttribute("textColor", textColor);
        log.debug( "Forwarded updated request from doPost in: " + this.getServletName() + " to dashboard.jps!" );
		request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
	}
}