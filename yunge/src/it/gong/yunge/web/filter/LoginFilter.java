package it.gong.yunge.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/user/*")
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws javax.servlet.ServletException, java.io.IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse respons = (HttpServletResponse) resp;
        String url = ((HttpServletRequest) req).getRequestURI();
        if(url.contains("/userLogin.jsp") ||url.contains("/userLoginServlet")||url.contains("/css/")||url.contains("/js/")||url.contains("/fonts/")||url.contains("/checkCodeServlet")){
            chain.doFilter(req, resp);
        }else{
            HttpSession session = request.getSession();
            Object manager = session.getAttribute("user");
            if(manager!=null){
                chain.doFilter(req, resp);
            }else {
                session.setAttribute("login_msg","尚未登录，请登录");
                respons.sendRedirect("/yun/userLogin.jsp");
            }
        }

    }

    public void destroy() {
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {
    }
}
