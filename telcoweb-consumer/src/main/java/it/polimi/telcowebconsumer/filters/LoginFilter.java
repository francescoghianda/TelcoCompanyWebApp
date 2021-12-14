package it.polimi.telcowebconsumer.filters;

import it.polimi.telcowebconsumer.utils.URLBuilder;

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
        String username = (String) session.getAttribute("username");
        boolean logged = !(session.isNew() || username == null);
        String path = request.getRequestURI().substring(request.getContextPath().length());

        if(logged){
            request.setAttribute("username", username);
        }
        else{
            request.setAttribute("username", "");
        }

        if(logged && path.equals("/")){
            response.sendRedirect(URLBuilder.buildUrl(this, "/home"));
            return;
        }

        chain.doFilter(request, response);
    }
}
