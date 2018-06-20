package com.han.core.utils;

import com.han.core.dto.UserDTO;
import com.han.core.persistence.entity.UserEntity;
import org.apache.catalina.User;

/**
 * Created by VO VAN NHAN on 6/4/2018.
 */
public class UserBeanUtil {
    public static UserDTO entity2Dto(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        dto.setFullName(entity.getFullName());
        dto.setCreatedDate(entity.getCreatedDate());
        //
        dto.setRoleDTO(RoleBeanUtil.entity2Dto(entity.getRoleEntity()));
        return dto;
    }

    public static UserEntity dto2Entity (UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUserId(dto.getUserId());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setFullName(dto.getFullName());
        entity.setCreatedDate(dto.getCreatedDate());
        //
        entity.setRoleEntity(RoleBeanUtil.dto2Entity(dto.getRoleDTO()));
        return entity;
    }
}
