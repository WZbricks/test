package com.wz.newsprovider.dao;


import com.wz.common.pojo.news;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface newsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(news record);

    int insertSelective(news record);

    news selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(news record);

    int updateByPrimaryKey(news record);

    List<news> selectAll();

    int selectAllCount();

    List<news> selectNewsByPage(@Param("currentPage") int currentPage,@Param("pageSize") int pageSize);

    int selectNewsCountByPage(@Param("currentPage") int currentPage,@Param("pageSize")int pageSize);
}