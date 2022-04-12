package it.polimi.telcowebemployee.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class LoginFilter extends HttpFilter {

    private static final Set<String> ALLOWED_PATHS = Set.of("/login", "/css/*", "/js/*", "/img/*");


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String path = request.getRequestURI().substring(request.getContextPath().length());

        boolean allowed = ALLOWED_PATHS.stream().anyMatch(allowedPath -> {
            if(allowedPath.endsWith("*")) return path.startsWith(allowedPath.substring(0, allowedPath.length()-1));
            if(allowedPath.startsWith("*")) return path.endsWith(allowedPath.substring(1));
            return path.equals(allowedPath);
        });

        if(allowed){
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        boolean logged = !(session.isNew() || username == null);

        if(!logged){
            response.sendRedirect(request.getContextPath()+"/login?redirect="+path);
            return;
        }

        request.setAttribute("username", username);

        chain.doFilter(request, response);
    }
}
