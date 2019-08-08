package it.gong.yunge.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.gong.yunge.domain.User;
import it.gong.yunge.service.UserService;
import it.gong.yunge.service.serviceImpl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/userNameCheckedSercvlet")
public class UserNameCheckedSercvlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8;");
        String username = req.getParameter("username");
        User user = new User();
        user.setUsername(username);
        UserService us = new UserServiceImpl();
        Map<String,Object> map = new HashMap<String,Object>();
        List<User> list = us.selectByUser(user);
        ObjectMapper mapper = new ObjectMapper();
        if (list != null && list.size() != 0) {
            map.put("nameExists",true);
            map.put("nameMsg","账号已存在");
        }else{
            map.put("nameExists",false);
            map.put("nameMsg","账号可用");
        }
        mapper.writeValue(resp.getWriter(),map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
