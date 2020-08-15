package com.indi.web;

import com.google.gson.Gson;
import com.indi.pojo.User;
import com.indi.service.UserService;
import com.indi.service.impl.UserServiceImpl;
import com.indi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 注册
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        //删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        String code = req.getParameter("code");


        if (token != null && token.equalsIgnoreCase(code)) {
            if (userService.existUsername(user.getUsername())) {
                req.setAttribute("msg", "用户名" + user.getUsername() + "已存在");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());
                req.setAttribute("type", user.getType());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                userService.registUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            req.setAttribute("type", user.getType());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 登录
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        User loginUser = userService.login(user);
        if (loginUser == null) {
            //把错误信息，和回显的表单项信息，保存到request域中
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /**
     * 注销
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁Session中用户登录的信息（或者销毁Session）
        req.getSession().invalidate();
        //重定向到首页或登录界面
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existUsername = userService.existUsername(username);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

}
