package service.impl;

import mapper.BrandMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Brand;
import pojo.PageBean;
import service.BrandService;
import util.SqlSessionFactoryUtils;

import java.util.List;

public class BrandServiceImpl implements BrandService {

    SqlSessionFactory factory =SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<Brand> selectALl() {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }
    public void add(Brand brand){
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        sqlSession.commit();
        sqlSession.close();

    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.delete(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Brand selectById(int id) {
        SqlSession sqlSession = factory.openSession();

        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = mapper.selectById(id);
        sqlSession.close();
        return brand;
    }

    @Override
    public void update(Brand brand) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. 调用方法
        mapper.update(brand);

        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. 调用方法
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin = (currentPage - 1) *pageSize;
        int size = pageSize;
        List<Brand> brands = mapper.selectByPage(begin, size);
        int i = mapper.selectTotalCount();
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(brands);
        pageBean.setTotalCount(i);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int PageSize, Brand brand) {
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin = (currentPage - 1) * PageSize;
        int size = PageSize;
        String brandName = brand.getBrandName();
        if(brandName != null && brandName.length() >0){
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if(companyName != null && companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }
        System.out.println(brand);
        List<Brand> brands = mapper.selectByPageAndCondition(begin, size,brand);
        int i = mapper.selectTotalCountByCondition(brand);
        System.out.println(brands.size());
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(brands);
        pageBean.setTotalCount(i);
        sqlSession.close ();
        return pageBean;
    }
}
