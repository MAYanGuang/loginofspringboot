package com.mayanguang.myspringboot.service;

import com.mayanguang.myspringboot.bean.MainBean;
import com.mayanguang.myspringboot.mapper.MainDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author: MA
 * @Date: 2021/11/04 17:20
 */
@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private MainDao mainDao ;

    @PersistenceContext
    EntityManager em;


//public Page<MainBean> findByJpaPage(int page,int size){
//    return mainDao.findAll(PageRequest.of(page,size));
//}

    @Override
    public boolean addMain(MainBean mainBean) {
        boolean flag = false;
        try {
            mainDao.save(mainBean);
            flag = true;
            System.out.println("添加成功");
        }catch (Exception e){
            System.out.println("添加失败");
            e.printStackTrace();
        }
        return flag;
    }



   public MainBean getMainByName(String name){
        return mainDao.getBeanByName(name);
   }

    @Override
    public boolean updateMain(MainBean mainBean) {
        boolean flag = false;
        try {
            mainDao.save(mainBean);
            flag = true;
        }catch (Exception e){
            System.out.println("更新失败");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteMain(Integer id) {
        //删除
        boolean flag = false;
        try {
            MainBean mainBean = new MainBean();
            mainBean = mainDao.findById(id).get();
            mainDao.delete(mainBean);
            flag = true;
        }catch (Exception e){
            System.out.println("删除失败");
            e.printStackTrace();
        }
        return flag;
    }



    public List<MainBean> getSomeUserList(Integer start,Integer number){
        return mainDao.getAll(start,number);

    }

    /**判断是不是已经存在用户名*/
    public int getNameNumber(String name){
        return mainDao.sameName(name);
    }
    public List<MainBean> selectUser(String name,Integer start,Integer number){
        return mainDao.selectLikeUsers(name,start,number);
    }

}
