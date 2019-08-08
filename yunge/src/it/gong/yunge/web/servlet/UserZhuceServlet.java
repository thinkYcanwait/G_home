package it.gong.yunge.web.servlet;

import it.gong.yunge.domain.User;
import it.gong.yunge.service.UserService;
import it.gong.yunge.service.serviceImpl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/userZhuceServlet")
public class UserZhuceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession se = req.getSession();
        String checkcode_server = (String) se.getAttribute("CHECKCODE_SERVER");
        se.removeAttribute("CHECKCODE_SERVER");
        String checkCode = req.getParameter("checkCode");
        if(checkCode!=null){
            if (!checkCode.equalsIgnoreCase(checkcode_server)) {
                se.setAttribute("zhuce_msg", "验证码错误，请重新输入");
                resp.sendRedirect(req.getContextPath() + "/userZhuce.jsp");
            } else {
                User user = new User();
                Map<String, String[]> umap = req.getParameterMap();
                try {
                    BeanUtils.populate(user, umap);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                UserService us = new UserServiceImpl();
                us.addUser(user);
                List<User> users = us.selectByUser(user);
                se.setAttribute("user", users.get(0));
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }
        }else{
            se.setAttribute("zhuce_msg", "验证码错误，请重新输入");
            resp.sendRedirect(req.getContextPath() + "/userZhuce.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
