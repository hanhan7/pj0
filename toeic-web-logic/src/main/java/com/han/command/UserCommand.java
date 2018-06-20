package com.han.command;

import com.han.core.dto.UserDTO;
import com.han.core.web.command.AbstractCommand;

/**
 * Created by VO VAN NHAN on 6/4/2018.
 */
public class UserCommand extends AbstractCommand<UserDTO> {
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserCommand() {
        this.pojo = new UserDTO();
    }
}
