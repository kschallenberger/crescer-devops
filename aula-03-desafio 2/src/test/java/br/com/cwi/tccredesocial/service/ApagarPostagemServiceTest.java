package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.factories.PostagemFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.ComentarioRepository;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaPostagemService;
import br.com.cwi.tccredesocial.validator.ApagarPostagemValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApagarPostagemServiceTest {

    @InjectMocks
    private ApagarPostagemService tested;

    @Mock
    private PostagemRepository postagemRepository;
    @Mock
    private ComentarioRepository comentarioRepository;
    @Mock
    private BuscaPostagemService buscaPostagemService;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private ApagarPostagemValidator apagarPostagemValidator;

    @Test
    @DisplayName("Deve apagar postagem")
    void deveApagarPostagem() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Postagem post = PostagemFactory.get();
        Long idPostagem = post.getId();

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(buscaPostagemService.porId(idPostagem)).thenReturn(post);

        tested.apagar(idPostagem);

        verify(comentarioRepository).deleteAllByPostagemId(post.getId());
        verify(postagemRepository).delete(post);
    }
}