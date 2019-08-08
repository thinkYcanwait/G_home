package it.gong.yunge.web.servlet;

import it.gong.yunge.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/checkUserLogin")
public class CheckUserLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession se = req.getSession();
        resp.setContentType("text/html;charset=utf-8;");
        User user = (User) se.getAttribute("user");
        if(user==null){
            resp.getWriter().write("false");
        }else{
            resp.getWriter().write("true");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
