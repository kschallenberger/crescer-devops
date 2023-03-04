package br.com.cwi.tccredesocial.security.mapper;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.domain.Situacao;
import br.com.cwi.tccredesocial.security.controller.response.ListarUsuarioResponse;
import br.com.cwi.tccredesocial.security.domain.Usuario;

import java.util.List;

public class ListarUsuarioMapper {

    public static ListarUsuarioResponse toResponse(Usuario entity, List<Amizade> amizades) {
        Situacao situacao = amizades.stream().filter(amizade -> amizade.getUsuarioAmigo().equals(entity)).map(Amizade::getSituacao).findFirst().orElse(null);
        return ListarUsuarioResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .apelido(entity.getApelido())
                .imagem(entity.getImagem())
                .amigo(situacao)
                .build();
    }
}
