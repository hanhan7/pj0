package com.han.command;

import com.han.core.dto.ListenGuidelineDTO;
import com.han.core.web.command.AbstractCommand;

/**
 * Created by VO VAN NHAN on 6/8/2018.
 */
public class ListenGuidelineCommand extends AbstractCommand<ListenGuidelineDTO> {
    public ListenGuidelineCommand() {
        this.pojo = new ListenGuidelineDTO();
    }
}
