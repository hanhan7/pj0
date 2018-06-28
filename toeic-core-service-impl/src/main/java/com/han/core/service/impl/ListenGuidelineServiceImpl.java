package com.han.core.service.impl;

import com.han.core.dao.ListenGuidelineDao;
import com.han.core.daoimpl.ListenGuidelineDaoImpl;
import com.han.core.dto.ListenGuidelineDTO;
import com.han.core.persistence.entity.ListenGuidelineEntity;
import com.han.core.service.ListenGuidelineService;
import com.han.core.utils.ListenGuidelineBeanUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VO VAN NHAN on 6/9/2018.
 */
public class ListenGuidelineServiceImpl implements ListenGuidelineService {

    private ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();



//    @Override
//    public Object[] findListenGuidelineByProperties(String property,
//                            Object value, String sortExpression, String sortDirection, Integer offset, Integer limit) {
//        List<ListenGuidelineDTO> result = new ArrayList<>();
//        Object[] objects = listenGuidelineDao.findByProperty(property, value, sortExpression, sortDirection, offset, limit);
//        for (ListenGuidelineEntity item : (List<ListenGuidelineEntity>) objects[1]) {
//            ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(item);
//            result.add(dto);
//        }
//        objects[1] = result;
//        return objects;
//    }
}
