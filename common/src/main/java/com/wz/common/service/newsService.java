package com.wz.common.service;

import com.wz.common.pojo.news;
import com.wz.common.utils.PageBean;

import java.util.List;

public interface newsService {
    List<news> selectAll();
    int addNews(news news);
    int modifyNews(news news);
    int deleteNews(Long id);
    public List<news> findItemByPage(int currentPage,int pageSize);
    public PageBean<news> Pages(int currentPage, int pageSize);
}
