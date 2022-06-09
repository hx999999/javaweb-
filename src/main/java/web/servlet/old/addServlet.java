package web.servlet.old;


import com.alibaba.fastjson.JSON;
import pojo.Brand;
import service.BrandService;
import service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet("/addServlet")
public class addServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        Brand brand = JSON.parseObject(s, Brand.class);
        brandService.add(brand);
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}