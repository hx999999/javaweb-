package web.servlet;

import service.BrandService;
import service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class baseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        int index = requestURI.lastIndexOf('/');
        String substring = requestURI.substring(index+1);

        Class<? extends baseServlet> aClass = this.getClass();

        try {
            Method method = aClass.getMethod(substring, HttpServletRequest.class, HttpServletResponse.class);
            try {
                method.invoke(this,req,resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
