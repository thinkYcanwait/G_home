package it.gong.yunge.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebFilter("/*")
public class DadWordFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws javax.servlet.ServletException, java.io.IOException {
        req.setCharacterEncoding("utf-8");
        ServletRequest request = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("getParameter")) {
                    String value = (String) method.invoke(req,args);
                    if(value!=null){
                        for (String s : list) {
                            if(value.contains(s)){
                                value= value.replaceAll(s, "***");
                            }
                        }
                    }
                    return value;
                }
                return method.invoke(req,args);
            }
        });
        chain.doFilter(request, resp);
    }

    public void destroy() {
    }

    private List<String> list = new ArrayList<>();

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {
        try {
            String realPath = config.getServletContext().getRealPath("/WEB-INF/classes/敏感词汇.txt");
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line;
            while((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
