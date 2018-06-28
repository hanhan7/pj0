package com.han.core.service.impl;

import com.han.core.dao.RoleDao;
import com.han.core.daoimpl.RoleDaoImpl;
import com.han.core.dto.RoleDTO;
import com.han.core.persistence.entity.RoleEntity;
import com.han.core.service.RoleService;
import com.han.core.utils.RoleBeanUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VO VAN NHAN on 6/25/2018.
 */
public class RoleServiceImpl implements RoleService {
    RoleDao roleDao = new RoleDaoImpl();
    @Override
    public List<RoleDTO> findAll() {
        List<RoleEntity> entities = roleDao.findAll();
        List<RoleDTO> dtos = new ArrayList<>();
        for (RoleEntity item : entities) {
            RoleDTO dto = RoleBeanUtil.entity2Dto(item);
            dtos.add(dto);
        }
        return dtos;
    }
}
