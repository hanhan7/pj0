package com.han.core.service.impl;

import com.han.core.dao.UserDao;
import com.han.core.daoimpl.UserDaoImpl;
import com.han.core.dto.UserDTO;
import com.han.core.persistence.entity.UserEntity;
import com.han.core.service.UserService;
import com.han.core.utils.UserBeanUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by VO VAN NHAN on 6/4/2018.
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public UserDTO isUserExit(UserDTO dto) {

        UserEntity entity = userDao.findUserByUsernameAndPassword(dto.getName(), dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    @Override
    public UserDTO findRoleByUser(UserDTO dto) {

        UserEntity entity = userDao.findUserByUsernameAndPassword(dto.getName(), dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    @Override
    public Object[] findByProperty(HashMap<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {

        Object[] objects = userDao.findByProperty(property, sortExpression, sortDirection, offset, limit);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity item : (List<UserEntity>)objects[1]) {
            UserDTO userDTO = UserBeanUtil.entity2Dto(item);
            userDTOs.add(userDTO);
        }
        objects[1] = userDTOs;
        return objects;
    }

    @Override
    public UserDTO findById(Integer userId) {
        UserEntity entity = userDao.findById(userId);
        UserDTO dto = UserBeanUtil.entity2Dto(entity);
        return dto;
    }
}
