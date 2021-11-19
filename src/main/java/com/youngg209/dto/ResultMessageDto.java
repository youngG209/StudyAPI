package com.youngg209.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResultMessageDto {

    private Object data;
    private Object error;

    @Builder
    public ResultMessageDto(Object data, Object error) {
        this.data = data;
        this.error = error;
    }
}
