package com.han.core.utils;

import com.han.core.dto.ListenGuidelineDTO;
import com.han.core.persistence.entity.ListenGuidelineEntity;

/**
 * Created by VO VAN NHAN on 6/9/2018.
 */
public class ListenGuidelineBeanUtil {
    /**
     *
     * @param entity
     * @return
     */
    public static ListenGuidelineDTO entity2Dto(ListenGuidelineEntity entity) {
        ListenGuidelineDTO dto = new ListenGuidelineDTO();
        dto.setListenGuidelineId(entity.getListenGuidelineId());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    public static ListenGuidelineEntity dto2Entity(ListenGuidelineDTO dto) {
        ListenGuidelineEntity entity = new ListenGuidelineEntity();
        entity.setListenGuidelineId(dto.getListenGuidelineId());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        return entity;
    }
}
