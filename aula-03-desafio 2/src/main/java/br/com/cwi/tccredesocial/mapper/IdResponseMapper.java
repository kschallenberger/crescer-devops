package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.controller.response.IdResponse;

public class IdResponseMapper {
    public static IdResponse toResponse(Long id) {
        return IdResponse.builder()
                .id(id)
                .build();
    }
}
