package it.gong.yunge.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/readArticleServlet")
public class ReadArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8;");
        String username = req.getParameter("username");
        String articlename = req.getParameter("articlename");
        ServletContext servletContext = req.getServletContext();
        String truename = username+"-"+articlename+".txt";
        String realPath = servletContext.getRealPath("/article/" + truename);
        BufferedReader fr = new BufferedReader(new FileReader(realPath));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line=fr.readLine())!=null){
            sb.append(line+"#");
        }
        String article = sb.toString();
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("article",article);
        map.put("articlename",articlename);
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(map);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
