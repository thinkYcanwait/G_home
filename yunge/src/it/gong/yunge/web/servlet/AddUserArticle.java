package it.gong.yunge.web.servlet;

import it.gong.yunge.domain.User;
import it.gong.yunge.service.UserService;
import it.gong.yunge.service.serviceImpl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@WebServlet("/user/addUserArticle")
public class AddUserArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String articleTitle = req.getParameter("articleTitle");
        if (articleTitle.equals("无题大作")) {
            articleTitle = articleTitle + new Date().getTime();
        }
        String words = req.getParameter("writeArea");
        HttpSession se = req.getSession();
        User user = (User) se.getAttribute("user");
        String username = user.getUsername();
        int id = user.getId();
        System.out.println(id);
        ServletContext servletContext = req.getServletContext();
        String articleName = articleTitle;
        String realPath = servletContext.getRealPath("/article/"+ username + "-"+ articleName + ".txt");
        UserService us = new UserServiceImpl();
        boolean b = us.addUserArticle(words, realPath,id,articleName);
        if (b) {
            resp.sendRedirect(req.getContextPath() + "/msg.html");
        } else {
            resp.getWriter().write("<h1>发布失败，您的内容如下</h1>");
            resp.getWriter().write(words);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
