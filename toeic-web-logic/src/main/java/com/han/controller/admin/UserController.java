package com.han.controller.admin;

import com.han.command.UserCommand;
import com.han.core.dto.UserDTO;
import com.han.core.service.UserService;
import com.han.core.service.impl.UserServiceImpl;
import com.han.core.web.common.WebConstant;
import com.han.core.web.utils.FormUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by VO VAN NHAN on 6/22/2018.
 */
@WebServlet(urlPatterns = {"/admin-user-list"})
public class UserController extends HttpServlet {
    UserService userService = new UserServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = command.getPojo();
        if (command.getUrlType().equals(WebConstant.URL_LIST)) {
            HashMap<String, Object> mapProperty = new HashMap<>();
            Object[] objects = userService.findByProperty(mapProperty, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
            command.setListResult((List<UserDTO>) objects[1]);
//            command.setTotalItems((Integer) objects[0]); java.lang.Long cannot be cast to java.lang.Integer
            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            request.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/list.jsp");
            rd.forward(request, response);
        }

    }
}
