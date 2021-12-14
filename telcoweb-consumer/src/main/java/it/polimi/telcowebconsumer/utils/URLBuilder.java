package it.polimi.telcowebconsumer.utils;

import jakarta.servlet.GenericFilter;
import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletContext;

import java.util.Arrays;

public class URLBuilder {

    public static String buildUrl(GenericFilter filter, String path){
        return buildUrl(filter.getServletContext(), path);
    }

    public static String buildUrl(GenericServlet servlet, String path){
        return buildUrl(servlet.getServletContext(), path);
    }

    public static String buildUrl(GenericFilter filter, String path, String[]... queryParameters){
        return buildUrl(filter.getServletContext(), path, queryParameters);
    }

    public static String buildUrl(GenericServlet servlet, String path, String[]... queryParameters){
        return buildUrl(servlet.getServletContext(), path, queryParameters);
    }

    private static String buildUrl(ServletContext context, String path, String[]... queryParameters){
        String queryString = Arrays.stream(queryParameters)
                .map(entry -> entry[0]+"="+entry[1])
                .reduce("?", (param1, param2) -> param1+"&"+param2);

        return context.getContextPath()+path+queryString;
    }
}
