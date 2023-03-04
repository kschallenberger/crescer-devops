package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.factories.PostagemFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurtirPostagemServiceTest {

    @InjectMocks
    private CurtirPostagemService tested;

    @Mock
    private BuscaPostagemService buscaPostagemService;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private PostagemRepository postagemRepository;
    @Captor
    private ArgumentCaptor<Postagem> postagemCaptor;

    @Test
    @DisplayName("Deve curtir post")
    void deveCurtir() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Postagem post = PostagemFactory.get();
        Long idPostagem = post.getId();
        post.setPublico(true);

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(buscaPostagemService.porId(idPostagem)).thenReturn(post);

        tested.curtir(idPostagem);

        verify(postagemRepository).save(postagemCaptor.capture());

        assertTrue(postagemCaptor.getValue().getCurtidas().contains(usuarioLogado));
    }
}