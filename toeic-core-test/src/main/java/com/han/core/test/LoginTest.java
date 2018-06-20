package com.han.core.test;

import com.han.core.dao.UserDao;
import com.han.core.daoimpl.UserDaoImpl;
import com.han.core.persistence.entity.UserEntity;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;


/**
 * Created by VO VAN NHAN on 6/4/2018.
 */
public class LoginTest {
    private final Logger log = Logger.getLogger(this.getClass());


    @Test
    public void checkIsUserExist() {
        UserDao userDao = new UserDaoImpl();
        String name = "han";
        String password = "12";
        UserEntity entity = userDao.findUserByUsernameAndPassword(name, password);
        if (entity != null) {
            log.error("login success");
        } else {
            log.error("login fail");
        }
    }

    @Test
    public void checkFindRoleByUser() {
        UserDao userDao = new UserDaoImpl();
        String name = "han";
        String password = "12";
        UserEntity entity = userDao.findUserByUsernameAndPassword(name, password);
        log.error(entity.getRoleEntity().getRoleId() + "-" + entity.getRoleEntity().getName());
    }
}