package br.com.cwi.tccredesocial.security.mapper;

import br.com.cwi.tccredesocial.security.controller.response.DetalhesUsuarioResponse;
import br.com.cwi.tccredesocial.security.domain.Usuario;

public class DetalhesUsuarioMapper {
    public static DetalhesUsuarioResponse toResponse(Usuario entity) {
        return DetalhesUsuarioResponse.builder()
                .nome(entity.getNome())
                .email(entity.getEmail())
                .apelido(entity.getApelido())
                .imagem(entity.getImagem())
                .dataNascimento(entity.getDataNascimento())
                .build();
    }
}
