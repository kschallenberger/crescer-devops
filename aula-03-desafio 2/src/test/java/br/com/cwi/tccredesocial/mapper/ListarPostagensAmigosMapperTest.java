package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.controller.response.ListarPostagensAmigosResponse;
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
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class ListarPostagensAmigosMapperTest {

    @InjectMocks
    private ListarPostagensAmigosMapper tested;

    @Test
    @DisplayName("Deve retornar a resposta com o último comentário, ordenado pela data mais atual")
    void deveRetornarComUltimoComentario() {
        Usuario usuario = UsuarioFactory.get();
        List<Comentario> comentarios = new ArrayList<>();
        Comentario comentarioNovo = ComentarioFactory.getNovo();
        comentarioNovo.setUsuario(usuario);
        Comentario comentarioVelho = ComentarioFactory.getVelho();
        comentarioVelho.setUsuario(usuario);
        comentarios.add(comentarioNovo);
        comentarios.add(comentarioVelho);
        Postagem postagem = PostagemFactory.get();
        postagem.setComentarios(comentarios);
        postagem.setUsuario(usuario);

        ListarPostagensAmigosResponse postagemResponse = tested.toResponse(usuario, postagem);

        assertEquals(comentarioNovo.getId(), postagemResponse.getUltimoComentario().getId());
        assertEquals(comentarioNovo.getDataInclusao(), postagemResponse.getUltimoComentario().getDataInclusao());
        assertEquals(postagem.getId(), postagemResponse.getIdPostagem());
        assertEquals(comentarios.size(), postagemResponse.getQtdComentarios());
    }

    @Test
    @DisplayName("Deve retornar a resposta sem comentários")
    void deveRetornarSemComentarios() {
        Usuario usuario = UsuarioFactory.get();
        Postagem postagem = PostagemFactory.get();
        postagem.setUsuario(usuario);

        ListarPostagensAmigosResponse postagemResponse = tested.toResponse(usuario, postagem);

        assertNull(postagemResponse.getUltimoComentario());
        assertEquals(postagem.getId(), postagemResponse.getIdPostagem());
        assertEquals(0, postagemResponse.getQtdComentarios());
    }
}