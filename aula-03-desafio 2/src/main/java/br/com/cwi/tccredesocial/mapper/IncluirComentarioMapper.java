package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.controller.request.IncluirComentarioRequest;
import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.security.domain.Usuario;

import java.time.LocalDateTime;

public class IncluirComentarioMapper {
    public static Comentario toEntity(Usuario usuarioLogado, IncluirComentarioRequest request) {
        return Comentario.builder()
                .texto(request.getTexto())
                .dataInclusao(LocalDateTime.now())
                .usuario(usuarioLogado)
                .build();
    }
}
