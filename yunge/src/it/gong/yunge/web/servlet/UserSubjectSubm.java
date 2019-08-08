package it.gong.yunge.web.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

@WebServlet("/userSubjectSubm")
public class UserSubjectSubm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String subject = req.getParameter("subject");
        ServletContext servletContext = req.getServletContext();
        String realPath = servletContext.getRealPath("/subject/" + new Date().getTime() + ".txt");
        String realPath1 = "E:/IdeaProjects/yunge/web/myNote.txt";
        FileWriter fw = new FileWriter(realPath);
        OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(realPath1,true),"UTF-8");
        fw.write(subject);
        fw.flush();
        fw.close();
        os.write(new Date()+": 你有一条新的建议，目录是："+realPath);
        os.write("\r\n");
        os.flush();
        os.close();
        resp.getWriter().write("您的建议已提交，笔芯");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
