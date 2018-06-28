package com.han.command;

import com.han.core.dto.RoleDTO;
import com.han.core.dto.UserDTO;
import com.han.core.web.command.AbstractCommand;

import java.util.List;

/**
 * Created by VO VAN NHAN on 6/4/2018.
 */
public class UserCommand extends AbstractCommand<UserDTO> {
    private String confirmPassword;
    private List<RoleDTO> roles;
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserCommand() {
        this.pojo = new UserDTO();
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
