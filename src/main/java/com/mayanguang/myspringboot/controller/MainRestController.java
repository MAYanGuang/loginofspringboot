package com.mayanguang.myspringboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mayanguang.myspringboot.service.MainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: MA
 * @Date: 2021/11/04 10:37
 * <p>
 * IntelliJ IDEA 会自动导入需要的”org.springframework.web.bind.annotation.RestController“包。
 * 这个注解是@ResponseBody注解和@Controller注解的组合。
 * 如果你有Spring基础就会明白它的含义，如果暂时不明白也没有关系，
 * 总之，只要是不需要引用模板文件的返回路径，都需要这个注解，特别是返回Json之类的接口数据。
 */

/**
 * @author hp
 * @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用
 * <p>
 * 原因就是 @ResponseBody注解引起的
 * @ResponseBody是作用在方法上的，@ResponseBody 表示该方法的返回结果直接写入 HTTP response body 中，
 * 一般在异步获取数据时使用【也就是AJAX】，在使用 @RequestMapping后，返回值通常解析为跳转路径，
 * 但是加上 @ResponseBody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中。
 * 比如异步获取 json 数据，加上 @ResponseBody 后，会直接返回 json 数据。
 * @RequestBody 将 HTTP 请求正文插入方法中，使用适合的 HttpMessageConverter 将请求体写入某个对象。
 * <p>
 * 不会被解析成跳转路径，那么 return "redirect:” 重定向也就失效了。
 * 解决方法就是把@RestController改成@Controller注解。
 */
//@RestController
@Controller

public class MainRestController {

    /*
    这里要用@Autowired自动注入 使用为自动连接属性传递的时候，Spring 会将这些传递过来的值或者引用自动分配给那些属性
     */
    // private MainServiceImpl mainServiceimpl = new MainServiceImpl();
    @Autowired

    MainServiceImpl mainServiceimpl;

    /**
     * 规定路径为/hello,响应方法为get
     * 这里要注意的是@RequestMapping这个注解也可以放在类声明上,这样整个类的路径的基路径就都会变成声明的值
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(HttpServletResponse response) {

        /*Cookies
        用在浏览器本地，但我们可以通过服务器端设置，设置后，本地的操作系统就会在本地写入Cookies文件，
        而两者再次通讯时，客户端就会携带Cookies信息访问服务器。
        早年的Cookies文件系统很容易被攻破，当今的主流操作系统虽然加强了安全性，
        但Cookies还是很容易被篡改。
        Cookies可以被本地JS修改，利用青花瓷或者fiddler等工具，很轻松的可以实现抓包、修改JS代码，
        这意味着Cookies也很容易被篡改。
        所以，服务器端不要相信客户端携带的Cookies信息
        ，特别是在进行用户身份验证时。只能将其作为辅助信息，
        并且，最终要对客户端发送的Cookies进行充分彻底的验证。
         */
        //键为"userCookie",值为"myUserName"
        Cookie cookie = new Cookie("userCookie", "myUserName");
        //设置过期时间 秒
        cookie.setMaxAge(100);
        response.addCookie(cookie);

        return "user/index";


    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String list() {
        return "user/index";
    }

    @RequestMapping(value = "/string1json", method = RequestMethod.GET)
    public String string1json() {
        String jsonStr = "{'水果名':'苹果','形状':'圆形','重量':100}";
        //将字符串转换为json对象
        JSONObject jsonResult = JSON.parseObject(jsonStr);

        String name = jsonResult.getString("水果名");
        int weight = jsonResult.getIntValue("重量");
        //重新转换成字符串并返回
        return "水果名: " + name + "<br />重量" + weight;
    }

    @RequestMapping("/getthy")
    public ModelAndView getthy(ModelAndView mav) {
        //设置模板为根目录的thy.html
        mav.setViewName("/thy");
        String s = "欢迎使用thymeleaf";
        //将 welcome 变量的值 绑定给 thy.html中的 welcome标签上
        mav.addObject("welcome", s);
        return mav;
    }

    @RequestMapping("/thy2")
    public String getthy2(Model model) {
        model.addAttribute("say", "这是第二个");
        return "/thy2";
    }





}
