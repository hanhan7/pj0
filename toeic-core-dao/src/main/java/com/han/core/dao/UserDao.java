package com.han.core.dao;

import com.han.core.data.dao.GenericDao;
import com.han.core.persistence.entity.RoleEntity;
import com.han.core.persistence.entity.UserEntity;
import org.apache.catalina.User;

/**
 * Created by VO VAN NHAN on 6/4/2018.
 */
public interface UserDao extends GenericDao<Integer, UserEntity> {
    UserEntity findUserByUsernameAndPassword(String name, String password);
}
