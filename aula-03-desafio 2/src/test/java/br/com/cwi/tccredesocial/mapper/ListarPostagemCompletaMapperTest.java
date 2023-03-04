package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.controller.response.ListarPostagemCompletaResponse;
import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.factories.ComentarioFactory;
import br.com.cwi.tccredesocial.factories.PostagemFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ListarPostagemCompletaMapperTest {

    @InjectMocks
    private ListarPostagemCompletaMapper tested;

    @Test
    @DisplayName("Deve retornar comentários")
    void deveRetornarComUltimoComentario() {
        Usuario usuario = UsuarioFactory.get();
        Comentario comentario = ComentarioFactory.getNovo();
        comentario.setUsuario(usuario);
        List<Comentario> comentarios = new ArrayList<>();
        comentarios.add(comentario);
        Postagem postagem = PostagemFactory.get();
        postagem.setComentarios(new ArrayList<>(comentarios));
        postagem.setUsuario(usuario);
        comentario.setPostagem(postagem);

        ListarPostagemCompletaResponse postagemResponse = tested.toResponse(usuario, comentario);

        assertEquals(comentario.getId(), postagemResponse.getComentario().getId());
        assertEquals(comentario.getDataInclusao(), postagemResponse.getComentario().getDataInclusao());
        assertEquals(postagem.getId(), postagemResponse.getIdPostagem());
        assertEquals(postagem.getUsuario().getId(), postagemResponse.getIdUsuario());
        assertEquals(comentarios.size(), postagemResponse.getQtdComentarios());
    }
}