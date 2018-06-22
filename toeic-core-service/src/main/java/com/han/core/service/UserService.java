package com.han.core.service;

import com.han.core.dto.UserDTO;

import java.util.HashMap;

/**
 * Created by VO VAN NHAN on 6/4/2018.
 */
public interface UserService {
    UserDTO isUserExit(UserDTO dto);

    UserDTO findRoleByUser(UserDTO dto);

    Object[] findByProperty(HashMap<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
}
