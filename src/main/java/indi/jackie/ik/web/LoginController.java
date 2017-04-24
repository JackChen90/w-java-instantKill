package indi.jackie.ik.web;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jackie chen
 * @create 2017/01/16
 * @description 登录Controller
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/")
    public ModelAndView login2(HttpServletRequest request) {

        request.getContentType();
        ModelAndView result = new ModelAndView("instanceKill");
        return result;
    }

    @RequestMapping(value = "/a")
    public ModelAndView test() {

        ModelAndView result = new ModelAndView("instanceKill");
        return result;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login() {

        ModelAndView result = new ModelAndView("login");
        return result;
    }
}
