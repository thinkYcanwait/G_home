package it.gong.yunge.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

//@WebFilter("/*")
public class TestFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, java.io.IOException {
        System.out.println(123);
        chain.doFilter(req, resp);
    }

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
