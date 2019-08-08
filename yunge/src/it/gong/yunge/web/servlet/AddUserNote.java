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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/user/addUserNote")
public class AddUserNote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String words = req.getParameter("writeArea");
        HttpSession se = req.getSession();
        User user = (User) se.getAttribute("user");
        int id = user.getId();
        String username = user.getUsername();
        ServletContext servletContext = req.getServletContext();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        String time = sdf.format(new Date());
        String noteName = username + "-" + time ;
        String realPath = servletContext.getRealPath("/Note/" + noteName + ".txt");
        UserService us = new UserServiceImpl();
        boolean flag = us.addUserNote(words, realPath,id,noteName);
        if (flag) {
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
