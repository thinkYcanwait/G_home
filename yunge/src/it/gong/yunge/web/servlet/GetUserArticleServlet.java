package it.gong.yunge.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.gong.yunge.domain.Article;
import it.gong.yunge.domain.User;
import it.gong.yunge.service.ArticleService;
import it.gong.yunge.service.serviceImpl.ArticleServiceImpl;


import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/getUserArticleServlet")
public class GetUserArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        ArticleService us = new ArticleServiceImpl();
        List<Article> ar= us.selectArticleByUser(user);
        ObjectMapper om = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        om.writeValue(resp.getWriter(),ar);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
