package com.han.core.test;
import com.han.core.dao.RoleDao;
import com.han.core.daoimpl.RoleDaoImpl;
import com.han.core.persistence.entity.RoleEntity;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by VO VAN NHAN on 5/22/2018.
 */

public class RoleTest {
    @Test
    public void checkFindAll() {
        RoleDao roleDao = new RoleDaoImpl();
        List<RoleEntity> list = roleDao.findAll();
    }

    @Test
    public void checkUpdateRole() {
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(2);
        entity.setName("USER");
        roleDao.update(entity);
    }

    @Test
    public void checkSaveRole() {
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(4);
        entity.setName("PROFESSOR");
        roleDao.save(entity);
    }

    @Test
    public void checkFindById() {
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = roleDao.findById(1);
    }

    @Test
    public void checkFindByProperty() {
        RoleDao roleDao = new RoleDaoImpl();
        String property = null;
        Object value = null;
        String sortExpression = null;
        String sortDirection = null;
//        Object[] objects = roleDao.findByProperty(property, value, sortExpression, sortDirection,0,2);
    }

    @Test
    public void checkDelete() {
        List<Integer> listId = new ArrayList<Integer>();
        listId.add(1);
        listId.add(2);
        RoleDao roleDao = new RoleDaoImpl();
        Integer count = roleDao.delete(listId);
    }
}
