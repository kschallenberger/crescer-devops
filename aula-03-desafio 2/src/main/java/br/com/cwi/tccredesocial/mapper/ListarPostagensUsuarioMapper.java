package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.controller.response.ComentarioResponse;
import br.com.cwi.tccredesocial.controller.response.ListarPostagensUsuarioResponse;
import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.security.domain.Usuario;

import java.util.Comparator;

import static java.util.Comparator.reverseOrder;
import static java.util.Objects.nonNull;

public class ListarPostagensUsuarioMapper {

    public static ListarPostagensUsuarioResponse toResponse(Usuario usuarioLogado, Postagem entity) {
        Boolean curtiu = null;
        if (nonNull(usuarioLogado)) {
            curtiu = entity.getCurtidas().contains(usuarioLogado);
        }

        entity.getComentarios().sort(Comparator.comparing(Comentario::getDataInclusao, reverseOrder()));
        Comentario ultimoComentario = entity.getComentarios().stream().findFirst().orElse(null);

        ComentarioResponse ultimoComentarioResponse = null;

        if (nonNull(ultimoComentario)) {
            ultimoComentarioResponse = ComentarioResponse.builder()
                    .id(ultimoComentario.getId())
                    .usuarioNome(ultimoComentario.getUsuario().getNome())
                    .usuarioImagem(ultimoComentario.getUsuario().getImagem())
                    .texto(ultimoComentario.getTexto())
                    .dataInclusao(ultimoComentario.getDataInclusao())
                    .build();
        }

        return ListarPostagensUsuarioResponse.builder()
                .idPostagem(entity.getId())
                .titulo(entity.getTitulo())
                .descricao(entity.getDescricao())
                .imagem(entity.getImagem())
                .dataInclusao(entity.getDataInclusao())
                .publico(entity.isPublico())
                .ultimoComentario(ultimoComentarioResponse)
                .qtdComentarios(entity.getComentarios().size())
                .qtdCurtidas(entity.getCurtidas().size())
                .usuarioCurtiu(curtiu)
                .build();
    }
}