package com.han.core.test;

import com.han.core.dao.ListenGuidelineDao;
import com.han.core.daoimpl.ListenGuidelineDaoImpl;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VO VAN NHAN on 6/9/2018.
 */

public class ListenGuidelineTest {
    ListenGuidelineDao listenGuidelineDao;

    @Before
    public void initData() {
        listenGuidelineDao = new ListenGuidelineDaoImpl();
    }

    @Test
    public void testFindByProperties() {
//        ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();
//        Object[] result = listenGuidelineDao.findByProperty(null, null, null, null, 2, 2);

    }

    @Test
    public void checkApiFindByProperties() {
        HashMap<String, Object> property = new HashMap<>();
        property.put("title", "Bài hướng dẫn 8");
        property.put("content", "Nội dung bài HD 8");
        Object[] result = listenGuidelineDao.findByProperty(property, null, null, null, null);
    }
}
