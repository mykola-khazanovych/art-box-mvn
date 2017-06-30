package com.artbox.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by mykola.khazanovych on 6/30/2017.
 */
@WebFilter( filterName = "PasswordRegistrationFilter" )
public class PasswordRegistrationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(
            ServletRequest req,
            ServletResponse resp,
            FilterChain chain
    ) throws ServletException, IOException {
        chain.doFilter( req, resp );
    }

    public void init( FilterConfig config ) throws ServletException {

    }

}
