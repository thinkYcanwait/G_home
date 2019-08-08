package it.gong.yunge.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.gong.yunge.domain.Article;
import it.gong.yunge.domain.Note;
import it.gong.yunge.service.ArticleService;
import it.gong.yunge.service.NoteService;
import it.gong.yunge.service.serviceImpl.ArticleServiceImpl;
import it.gong.yunge.service.serviceImpl.NoteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/loadNewArticle")
public class LoadNewArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //直接查询数据库，返回最新的article文章
        resp.setContentType("application/json;charset=utf-8;");
        ArticleService as = new ArticleServiceImpl();
        List<Article> list = as.selectNewArticle();
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(list);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
