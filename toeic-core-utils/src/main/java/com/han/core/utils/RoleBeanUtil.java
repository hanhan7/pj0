package com.han.core.utils;

import com.han.core.dto.RoleDTO;
import com.han.core.persistence.entity.RoleEntity;

import javax.persistence.Entity;

/**
 * Created by VO VAN NHAN on 6/4/2018.
 */
public class RoleBeanUtil {

    /**
     *
     * @param entity
     * @return
     */
    public static RoleDTO entity2Dto(RoleEntity entity) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(entity.getRoleId());
        dto.setName(entity.getName());
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    public static RoleEntity dto2Entity(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(dto.getRoleId());
        entity.setName(dto.getName());
        return entity;
    }
}
