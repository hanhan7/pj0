package com.han.controller.admin;

/**
 * Created by VO VAN NHAN on 6/8/2018.
 */

import com.han.command.ListenGuidelineCommand;
import com.han.core.common.utils.UploadUtil;
import com.han.core.dto.ListenGuidelineDTO;
import com.han.core.service.ListenGuidelineService;
import com.han.core.service.impl.ListenGuidelineServiceImpl;
import com.han.core.utils.ListenGuidelineBeanUtil;
import com.han.core.web.common.WebConstant;
import com.han.core.web.utils.FormUtil;
import com.han.core.web.utils.RequestUtil;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-guideline-listen-list", "/admin-guideline-listen-edit"})
public class ListenGuidelineController extends HttpServlet {
    private ListenGuidelineService guidelineService = new ListenGuidelineServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class,request);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ResourcesBundle");
        HttpSession session = request.getSession();
        session.setAttribute(WebConstant.ALERT,session.getAttribute(WebConstant.ALERT));
        session.setAttribute(WebConstant.MESSAGE_RESPONSE, session.getAttribute(WebConstant.MESSAGE_RESPONSE));
        /*List<ListenGuidelineDTO> listenGuidelineDTOs = new ArrayList<>();
        ListenGuidelineDTO dto1 = new ListenGuidelineDTO();
        dto1.setTitle("Bài HD nghe 1");
        dto1.setContent("Nội dung bài HD nghe 1");
        listenGuidelineDTOs.add(dto1);
        ListenGuidelineDTO dto2 = new ListenGuidelineDTO();
        dto2.setTitle("Bài HD nghe 2");
        dto2.setContent("Nội dung bài HD nghe 2");
        listenGuidelineDTOs.add(dto2);
        ListenGuidelineDTO dto3 = new ListenGuidelineDTO();
        dto3.setTitle("Bài HD nghe 2");
        dto3.setContent("Nội dung bài HD nghe 2");
        listenGuidelineDTOs.add(dto2);
        command.setListResult(listenGuidelineDTOs);
        command.setMaxPageItems(2);
        command.setTotalItems(listenGuidelineDTOs.size());
        request.setAttribute(WebConstant.LIST_ITEMS, command);*/
//        command.setMaxPageItems(2);
//        RequestUtil.initSearchBean(request,command);
//        Object[] objects = guidelineService.findListenGuidelineByProperties
//                (null,null,command.getSortExpression(),command.getSortDirection(),command.getFirstItem(),command.getMaxPageItems());
//        command.setListResult((List<ListenGuidelineDTO>)objects[1]);
////        command.setTotalItems((Integer) objects[0]);
//        command.setTotalItems(Integer.parseInt(objects[0].toString()));
        request.setAttribute(WebConstant.LIST_ITEMS, command);
        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)){
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/listenguideline/edit.jsp");
            rd.forward(request, response);
        }
        session.removeAttribute(WebConstant.ALERT);
        session.removeAttribute(WebConstant.MESSAGE_RESPONSE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ListenGuidelineCommand command = new ListenGuidelineCommand();
        ResourceBundle bundle = ResourceBundle.getBundle("ResourcesBundle");

        UploadUtil uploadUtil = new UploadUtil();
        HttpSession session = request.getSession();
        Set<String> valueTitle = buildListenGuideline();
        try {
            Object[] objects = uploadUtil.writeOrUpdateFile(request, valueTitle, WebConstant.LISTENGUIDELINE);
            Map<String, String> mapValue = (HashMap<String, String>) objects[3];
            command = returnValueListenGuidelineCommand(valueTitle, command, mapValue);
            


            session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.guideline.listen.add.success"));

        } catch (Exception e) {
            session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.error"));
        }

        response.sendRedirect("/admin-guideline-listen-list?urlType=url_list");

    }

    private ListenGuidelineCommand returnValueListenGuidelineCommand(Set<String> valueTitle, ListenGuidelineCommand command, Map<String, String> mapValue) {
        for (String item : valueTitle) {
            if (mapValue.containsKey(item)) {
                if (item.equals("pojo.title")) {
                    command.getPojo().setTitle(mapValue.get(item));
                } else if (item.equals("pojo.content")) {
                    command.getPojo().setContent(mapValue.get(item));
                }
            }
        }
        return command;
    }

    private Set<String> buildListenGuideline() {
        Set<String> returnValue = new  HashSet<>();
        returnValue.add("pojo.title");
        returnValue.add("pojo.content");
        return returnValue;
    }
}
