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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemoverCurtirPostagemServiceTest {

    @InjectMocks
    private RemoverCurtirPostagemService tested;

    @Mock
    private BuscaPostagemService buscaPostagemService;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private PostagemRepository postagemRepository;
    @Captor
    ArgumentCaptor<Postagem> postagemCaptor;

    @Test
    @DisplayName("Deve remover curtir do post")
    void deveRemoverCurtir() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Postagem post = PostagemFactory.get();
        Long idPostagem = post.getId();
        List<Usuario> curtidas = new ArrayList<>();
        curtidas.add(UsuarioFactory.get());
        curtidas.add(UsuarioFactory.get());
        curtidas.add(usuarioLogado);
        curtidas.add(UsuarioFactory.get());
        curtidas.add(UsuarioFactory.get());
        post.setCurtidas(curtidas);
        post.setPublico(true);

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(buscaPostagemService.porId(idPostagem)).thenReturn(post);

        tested.remover(idPostagem);

        verify(postagemRepository).save(postagemCaptor.capture());

        assertTrue(!postagemCaptor.getValue().getCurtidas().contains(usuarioLogado));
    }
}