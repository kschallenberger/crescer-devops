package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.controller.response.ComentarioResponse;
import br.com.cwi.tccredesocial.controller.response.ListarPostagemCompletaResponse;
import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.security.domain.Usuario;

public class ListarPostagemCompletaMapper {
    public static ListarPostagemCompletaResponse toResponse(Usuario usuarioLogado, Comentario entity) {
        boolean curtiu = entity.getPostagem().getCurtidas().contains(usuarioLogado);

        ComentarioResponse comentarioResponse = ComentarioResponse.builder()
                .id(entity.getId())
                .usuarioNome(entity.getUsuario().getNome())
                .usuarioImagem(entity.getUsuario().getImagem())
                .texto(entity.getTexto())
                .dataInclusao(entity.getDataInclusao())
                .build();

        return ListarPostagemCompletaResponse.builder()
                .idUsuario(entity.getPostagem().getUsuario().getId())
                .nomeUsuario(entity.getPostagem().getUsuario().getNome())
                .apelidoUsuario(entity.getPostagem().getUsuario().getApelido())
                .imagemUsuario(entity.getPostagem().getUsuario().getImagem())
                .idPostagem(entity.getPostagem().getId())
                .titulo(entity.getPostagem().getTitulo())
                .descricao(entity.getPostagem().getDescricao())
                .imagem(entity.getPostagem().getImagem())
                .dataInclusao(entity.getPostagem().getDataInclusao())
                .publico(entity.getPostagem().isPublico())
                .comentario(comentarioResponse)
                .qtdComentarios(entity.getPostagem().getComentarios().size())
                .qtdCurtidas(entity.getPostagem().getCurtidas().size())
                .usuarioCurtiu(curtiu)
                .build();
    }
}
