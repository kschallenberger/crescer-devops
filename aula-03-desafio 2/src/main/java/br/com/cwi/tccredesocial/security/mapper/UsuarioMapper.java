package br.com.cwi.tccredesocial.security.mapper;

import br.com.cwi.tccredesocial.security.controller.request.UsuarioRequest;
import br.com.cwi.tccredesocial.security.controller.response.UsuarioResponse;
import br.com.cwi.tccredesocial.security.domain.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setApelido(request.getApelido());
        entity.setDataNascimento(request.getDataNascimento());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(entity.getId());
        response.setNome(entity.getNome());
        response.setEmail(entity.getEmail());
        response.setImagem(entity.getImagem());
        return response;
    }
}