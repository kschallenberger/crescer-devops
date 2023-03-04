package br.com.cwi.tccredesocial.security.mapper;

import br.com.cwi.tccredesocial.security.controller.request.AlterarUsuarioRequest;
import br.com.cwi.tccredesocial.security.domain.Usuario;

public class AlterarUsuarioMapper {
    public static Usuario toEntity(Usuario usuario, AlterarUsuarioRequest request) {
        return Usuario.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .ativo(usuario.isAtivo())
                .dataNascimento(usuario.getDataNascimento())
                .permissoes(usuario.getPermissoes())
                .amizades(usuario.getAmizades())
                .nome(request.getNome())
                .apelido(request.getApelido())
                .imagem(request.getImagem())
                .build();
    }
}
