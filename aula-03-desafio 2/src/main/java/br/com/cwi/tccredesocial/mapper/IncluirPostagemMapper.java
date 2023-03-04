package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.controller.request.IncluirPostagemRequest;
import br.com.cwi.tccredesocial.domain.Postagem;

public class IncluirPostagemMapper {

    public static Postagem toEntity(IncluirPostagemRequest request) {
        return Postagem.builder()
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .imagem(request.getImagem())
                .publico(request.isPublico())
                .build();
    }

}
