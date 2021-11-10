package com.mayanguang.myspringboot.mapper;

import com.mayanguang.myspringboot.bean.MainBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: MA
 * @Date: 2021/11/04 18:53
 */
@Repository
public  interface MainDao extends JpaRepository<MainBean, Integer> {
    @Query(value = "from MainBean where name = ?1")
    MainBean getBeanByName(String name);

    @Query(value = "select * from test.springofpeople limit :start,:number",nativeQuery = true)
    List <MainBean> getAll(@Param("start") Integer start, @Param("number") Integer number);



}
