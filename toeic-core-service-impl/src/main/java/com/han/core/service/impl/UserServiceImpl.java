package com.han.core.service.impl;

import com.han.core.dao.UserDao;
import com.han.core.daoimpl.UserDaoImpl;
import com.han.core.dto.UserDTO;
import com.han.core.persistence.entity.UserEntity;
import com.han.core.service.UserService;
import com.han.core.utils.UserBeanUtil;

/**
 * Created by VO VAN NHAN on 6/4/2018.
 */
public class UserServiceImpl implements UserService {


    @Override
    public UserDTO isUserExit(UserDTO dto) {
        UserDao userDao = new UserDaoImpl();
        UserEntity entity = userDao.findUserByUsernameAndPassword(dto.getName(), dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    @Override
    public UserDTO findRoleByUser(UserDTO dto) {
        UserDao userDao = new UserDaoImpl();
        UserEntity entity = userDao.findUserByUsernameAndPassword(dto.getName(), dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }
}
