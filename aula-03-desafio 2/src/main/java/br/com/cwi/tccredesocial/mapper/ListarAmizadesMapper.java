package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.controller.response.ListarAmizadesResponse;
import br.com.cwi.tccredesocial.domain.Amizade;

public class ListarAmizadesMapper {
    public static ListarAmizadesResponse toResponse(Amizade entity) {
        return ListarAmizadesResponse.builder()
                .id(entity.getUsuarioAmigo().getId())
                .nome(entity.getUsuarioAmigo().getNome())
                .apelido(entity.getUsuarioAmigo().getApelido())
                .imagem(entity.getUsuarioAmigo().getImagem())
                .situacao(entity.getSituacao())
                .dataSolicitacao(entity.getDataPedido())
                .build();
    }
}
