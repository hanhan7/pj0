package com.han.controller.admin;


import com.han.command.UserCommand;
import com.han.core.dto.UserDTO;
import com.han.core.service.UserService;
import com.han.core.service.impl.UserServiceImpl;
import com.han.core.web.common.WebConstant;
import com.han.core.web.utils.FormUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 8/7/2017.
 */
@WebServlet(urlPatterns = {"/login.html", "/logout.html"}, name = "login")
public class LoginController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    //    ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle");
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
        rd.forward(request, response);
//        String action = request.getParameter("action");
//        if (action.equals(WebConstant.LOGIN)) {
//            RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
//            rd.forward(request, response);
//        } else if(action.equals(WebConstant.LOGOUT)) {
//            SessionUtil.getInstance().remove(request, WebConstant.LOGIN_NAME);
//            response.sendRedirect("/home.html");
//        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        test log4j
       /* log.error("jsp servlet com.han");
        String test = null;
        try {
            if (test.equals("asdf")) {
                System.out.println("hello");
            }
        } catch (NullPointerException e) {
            log.error(e.getMessage(),e);
        }*/

        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = command.getPojo();
        UserService userService = new UserServiceImpl();
        try {
            if (userService.isUserExit(pojo) != null) {
                if (userService.findRoleByUser(pojo) != null && userService.findRoleByUser(pojo).getName() != null) {
                    if (userService.findRoleByUser(pojo).getRoleDTO().getName().equals(WebConstant.ROLE_ADMIN)) {
//                        request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
//                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, "ADMIN");
                        response.sendRedirect("/admin-home");

                    } else if (userService.findRoleByUser(pojo).getRoleDTO().getName().equals(WebConstant.ROLE_USER)) {
//                        request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
//                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, "USER");
                       response.sendRedirect("/home");
                    }
                }
            }
        } catch (NullPointerException e) {
            log.error(e.getMessage(), e);
            request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, "Tên hoặc mật khẩu sai");
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
            rd.forward(request, response);
        }


//        if (pojo != null) {
//            CheckLogin login = SingletonServiceUtil.getUserServiceInstance().checkLogin(pojo.getName(), pojo.getPassword());
//            if (login.isUserExist()) {
//                SessionUtil.getInstance().putValue(request, WebConstant.LOGIN_NAME, pojo.getName());
//                if (login.getRoleName().equals(WebConstant.ROLE_ADMIN)) {
//                    response.sendRedirect("/admin-home.html");
//                } else if (login.getRoleName().equals(WebConstant.ROLE_USER)) {
//                    response.sendRedirect("/home.html");
//                }
//            } else {
//                request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
//                request.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.name.password.wrong"));
//                RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
//                rd.forward(request, response);
//            }
//        }
    }
}
