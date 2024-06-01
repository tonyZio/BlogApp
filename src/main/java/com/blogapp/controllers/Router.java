package com.blogapp.controllers;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Router implements Filter {

    private static final Pattern REWRITE_PATTERN = Pattern.compile("^(?!/(myblogs|logout|myposts|login|addBlogPost|addPostComment|updateBlogPost|deleteBlogPost|blogs)$)(?!.*\\.jsp$)/([^/]+)$");

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        String relativePath = uri.substring(contextPath.length());

        Matcher m = REWRITE_PATTERN.matcher(relativePath);
        if (m.matches()) {
            String titulo_blog = m.group().substring(1);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/blog?blog=" + titulo_blog);
            dispatcher.forward(req, res);
        } else {
            fc.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }
}
