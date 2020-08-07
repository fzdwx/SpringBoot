package lk.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author likeLove
 * @time 2020-08-06  14:20
 * 登录检查
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object username = session.getAttribute("username");
        //已经登录
        if (username != null) {
            return true;
        }
        //没有登录
        request.setAttribute("errorMsg", "请登录后操作");
        request.getRequestDispatcher("/index.html").forward(request, response);
        return false;
    }
}
