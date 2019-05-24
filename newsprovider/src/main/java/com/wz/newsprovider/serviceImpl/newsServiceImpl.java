package com.wz.newsprovider.serviceImpl;

import com.wz.common.pojo.news;
import com.wz.common.service.newsService;
import com.wz.common.utils.PageBean;
import com.wz.newsprovider.dao.newsMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service
public class newsServiceImpl implements newsService {
    @Autowired
    private newsMapper newsMapper;

    @Value("${myname}")
    private String myname;

    @Override
    public List<news> selectAll() {
        return newsMapper.selectAll();
    }

    @Override
    public int addNews(news news) {
        return newsMapper.insertSelective(news);
    }

    @Override
    public int modifyNews(news news) {
        return newsMapper.updateByPrimaryKeySelective(news);
    }

    @Override
    public int deleteNews(Long id) {
        return newsMapper.deleteByPrimaryKey(id);
    }

    @Override
      public List<news> findItemByPage(int currentPage,int pageSize) {
                List<news> allItems = newsMapper.selectNewsByPage((currentPage-1)*pageSize,pageSize);        //全部商品
                int countNums = newsMapper.selectNewsCountByPage((currentPage-1)*pageSize,pageSize);            //总记录数

                PageBean<news> pageData = new PageBean<>(currentPage, pageSize, countNums);
                pageData.setItems(allItems);
                return allItems;
    }

    public PageBean<news> Pages(int currentPage, int pageSize) {
        int countNums = newsMapper.selectNewsCountByPage((currentPage-1)*pageSize,pageSize);
        PageBean<news> pageData = new PageBean<>(currentPage, pageSize, countNums);
        return pageData;
    }
}
