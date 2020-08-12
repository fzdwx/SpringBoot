package lk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author likeLove
 * @time 2020-08-03  16:52
 */
@Controller
public class IndexController {
    @ResponseBody
    @RequestMapping ("/hello")
    public String hello(@RequestParam String user) {
        if (user.equals("a") ) {
            throw new IndexOutOfBoundsException("用户不存在");
        }
        return "HelloApplicationContextInitializer world";
    }

    @RequestMapping ("/index")
    public String index() {
        return "index";
    }

}
