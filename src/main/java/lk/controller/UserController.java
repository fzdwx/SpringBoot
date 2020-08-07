package lk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author likeLove
 * @time 2020-08-06  13:44
 */
@Controller
@RequestMapping ("/user")
public class UserController {
    @PostMapping ("/login")
    public String login(@RequestParam ("username") String username,
                        @RequestParam ("pwd") String pwd, Model m, HttpSession session) {
        if (Objects.equals(username, "root") && Objects.equals(pwd, "1")) {
            session.setAttribute("username", username);
            //登录成功重定向到主页面
            return "redirect:/main.html";
        } else {
            m.addAttribute("errorMsg", "用户名或密码错误");
            //登录识别到登录页面
            return "index";
        }
    }
}
