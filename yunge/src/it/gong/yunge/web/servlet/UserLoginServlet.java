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

@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String checkCode = req.getParameter("checkCode");
        Map<String, String[]> umap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,umap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkCode!=null){
            if(!checkCode.equalsIgnoreCase(checkcode_server)){
                session.setAttribute("login_msg","验证码错误，请重新输入");
                resp.sendRedirect(req.getContextPath()+"/userLogin.jsp");
            }else{
                UserService us = new UserServiceImpl();
                List<User> list = us.selectByUser(user);
                if(list!=null&&list.size()!=0){
                    session.setAttribute("user",list.get(0));
                    resp.sendRedirect(req.getContextPath()+"/index.jsp");
                }else{
                    session.setAttribute("login_msg","账号或密码输入错误，请重新输入");
                    resp.sendRedirect(req.getContextPath()+"/userLogin.jsp");
                }
            }
        }else{
            session.setAttribute("login_msg","验证码错误，请重新输入");
            resp.sendRedirect(req.getContextPath()+"/userLogin.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
