package com.han.core.service;

import com.han.core.dto.UserDTO;

/**
 * Created by VO VAN NHAN on 6/4/2018.
 */
public interface UserService {
    UserDTO isUserExit(UserDTO dto);

    UserDTO findRoleByUser(UserDTO dto);
}
