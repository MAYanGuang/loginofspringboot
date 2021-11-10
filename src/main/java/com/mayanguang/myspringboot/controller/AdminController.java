package com.mayanguang.myspringboot.controller;

import com.mayanguang.myspringboot.bean.MainBean;
import com.mayanguang.myspringboot.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: MA
 * @Date: 2021/11/09 10:33
 */
@Controller
public class AdminController {
    @Autowired
    MainServiceImpl mainServiceimpl;

    @RequestMapping("/ToAdminLogin")
    public String list() {
        return "user/adminLogin";
    }

    @RequestMapping("/manageUser")
    public String manageUser(HttpSession session) {
        if (session.getAttribute("admin").toString().length() > 0) {
            return "user/manageUser";
        } else {
            return "user/loginagain";
        }

    }

    /**
     * 简单的身份验证
     *
     * @RequestParam这个注解用于取得地址栏的参数
     */
    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public String adminLogin(@RequestParam(value = "pwd") String password, HttpSession session) {
        try {
            if ("123456".equals(password)) {
                session.setAttribute("admin", "管理员");
                return "user/manageUser";
            } else {
                return "user/loginagain";
            }
        } catch (Exception e) {
            return "user/loginagain";
        }


    }

    @RequestMapping(value = "/showUser", method = RequestMethod.GET)
    /**@PathVariable注解获取路径的值 这里有个{id} 就是能够获取这个值
     * required = true规定这是必须的参数*/
    public String jpaGet(@RequestParam(value = "page", defaultValue = "1") String start,
                         HttpSession session, Model model) {
        //这里通过判断是否存在admin对应的session的值来判断之前是否登陆成功,
        // 失败或者没有登录没有登录的不是admin的账号这里都是null
        if (session.getAttribute("admin") == null) {
            return "user/loginagain";
        } else {
            int s = Integer.parseInt(start);
            List<MainBean> users = mainServiceimpl.getSomeUserList(s * 5 - 5, 5);
            model.addAttribute("users", users);
            model.addAttribute("page",s);

            return "user/ShowUser";
        }
    }

    @RequestMapping(value = "/ReviseUser", method = RequestMethod.POST)
    public String jpaUpdate(MainBean mainBean,
                            HttpSession session) {

        if (session.getAttribute("admin").toString().length() > 0) {
            mainServiceimpl.updateMain(mainBean);
            return "redirect:/showUser";
        } else {
            return "user/deletefail";
        }

    }


    @RequestMapping(value = "/DelUser", method = RequestMethod.POST)
    public String jpaDel(@RequestParam(value = "id") Integer pId, HttpSession session) {
        try {
            if (session.getAttribute("admin").toString().length() > 0) {
                if (mainServiceimpl.deleteMain(pId)) {
                    return "redirect:/showUser";
                } else {
                    return "user/loginagain";
                }
            } else {
                return "user/deletefail";
            }
        } catch (Exception e) {
            return "user/deletefail";
        }
    }

}
