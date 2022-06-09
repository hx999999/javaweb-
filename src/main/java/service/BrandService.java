package service;

import pojo.Brand;
import pojo.PageBean;

import java.util.List;

public interface BrandService {

    List<Brand> selectALl();
    void add(Brand brand);
    void delete(int id);
    Brand selectById(int id);
    void update(Brand brand);
    void deleteByIds(int [] ids);
    PageBean<Brand> selectByPage(int currentPage,int pageSize);
    PageBean<Brand> selectByPageAndCondition(int currentPage,int PageSize,Brand brand);
}
