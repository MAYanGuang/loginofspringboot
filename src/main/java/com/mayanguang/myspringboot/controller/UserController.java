package com.mayanguang.myspringboot.controller;

import com.mayanguang.myspringboot.bean.MainBean;
import com.mayanguang.myspringboot.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: MA
 * @Date: 2021/11/09 10:32
 */
@Controller
public class UserController {
    @Autowired
    MainServiceImpl mainServiceimpl;


    @RequestMapping(value = "/addUser")
    public String addUser(Model model) {
        return "user/addUser";
    }

    //用户注册
    @RequestMapping(value = "/addUsers", method = RequestMethod.POST)
    public String jpaTest(
            @RequestParam(value = "name", defaultValue = "") String pName,
            @RequestParam(value = "age", defaultValue = "0") Integer pAge,
            @RequestParam(value = "pwd") String pPwd,
            Model model

    ) {
        if (mainServiceimpl.getNameNumber(pName) > 1) {
            model.addAttribute("sameuser","存在相同用户名");
            return "user/addUser";
        } else {
            System.out.println("长度是"+mainServiceimpl.getNameNumber(pName));
            System.out.println("33333333333333333");
            MainBean mainBean = new MainBean();
            mainBean.setName(pName);
            mainBean.setAge(pAge);
            mainBean.setPwd(pPwd);
            mainServiceimpl.addMain(mainBean);

            return "user/Userindex";
        }


    }


    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userlogin(@RequestParam(value = "name") String uName,
                            @RequestParam(value = "pwd") String uPwd,
                            Model model

    ) {
        try {
            MainBean mainBean = mainServiceimpl.getMainByName(uName);
            if (uPwd.equals(mainBean.getPwd())) {
                model.addAttribute("username", uName);
                return "user/User";
            } else {
                return "user/loginagain";
            }
        } catch (Exception e) {
            return "user/loginagain";
        }

    }
}
