package it.polimi.telcowebconsumer.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter extends HttpFilter {


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = request.getSession();
        boolean logged = !(session.isNew() || session.getAttribute("user") == null);
        String path = request.getRequestURI().substring(request.getContextPath().length());

        if(logged && path.equals("/")){
            response.sendRedirect(request.getContextPath()+"/home");
            return;
        }

        chain.doFilter(request, response);
    }
}
