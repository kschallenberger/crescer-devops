package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.request.IncluirComentarioRequest;
import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.factories.PostagemFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.ComentarioRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaPostagemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IncluirComentarioServiceTest {

    @InjectMocks
    private IncluirComentarioService tested;

    @Mock
    private ComentarioRepository comentarioRepository;
    @Mock
    private BuscaPostagemService buscaPostagemService;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Captor
    private ArgumentCaptor<Comentario> comentarioCaptor;

    @Test
    @DisplayName("Deve incluir nova postagem")
    void deveIncluirNovaPostagem() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Postagem post = PostagemFactory.get();
        Long idPostagem = post.getId();

        IncluirComentarioRequest request = IncluirComentarioRequest.builder()
                .texto("Teste")
                .build();

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(buscaPostagemService.porId(idPostagem)).thenReturn(post);
        tested.incluir(idPostagem, request);
        verify(comentarioRepository).save(comentarioCaptor.capture());

        assertEquals(request.getTexto(), comentarioCaptor.getValue().getTexto());
    }
}