package com.han.command;

import com.han.core.dto.JqueryDTO;
import com.han.core.web.command.AbstractCommand;

/**
 * Created by VO VAN NHAN on 6/21/2018.
 */
public class JqueryCommand extends AbstractCommand<JqueryDTO> {
    private  String urlType;

    public JqueryCommand() {
        this.pojo = new JqueryDTO();
    }

    @Override
    public String getUrlType() {
        return urlType;
    }

    @Override
    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }
}
