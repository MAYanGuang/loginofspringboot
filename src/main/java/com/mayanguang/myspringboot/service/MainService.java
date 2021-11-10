package com.mayanguang.myspringboot.service;

import com.mayanguang.myspringboot.bean.MainBean;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: MA
 * @Date: 2021/11/04 17:16
 */
public interface MainService {
    boolean addMain(MainBean mainBean);

    MainBean getMainById(Integer id);



    boolean updateMain(MainBean mainBean);

    boolean deleteMain(Integer id);

    List<MainBean> getUserList();
}
