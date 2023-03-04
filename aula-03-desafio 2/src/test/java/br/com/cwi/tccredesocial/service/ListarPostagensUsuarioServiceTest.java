package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.response.ListarPostagensUsuarioResponse;
import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.factories.AmizadeFactory;
import br.com.cwi.tccredesocial.factories.PostagemFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaUsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarPostagensUsuarioServiceTest {

    @InjectMocks
    private ListarPostagensUsuarioService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private BuscaUsuarioService buscaUsuarioService;
    @Mock
    private PostagemRepository postagemRepository;

    @Test
    @DisplayName("Deve listar postagens publicas do usuário se não for amigo")
    void deveListarPostagensPublicasDoUsuarioSeNaoForAmigo() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Usuario usuarioSolicitado = UsuarioFactory.get();
        Long idUsuario = usuarioSolicitado.getId();

        List<Postagem> postagens = PostagemFactory.getList();
        Pageable pageable = PageRequest.of(0, 5);
        Page<Postagem> postagensPaginado = new PageImpl<>(postagens);

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(buscaUsuarioService.porId(idUsuario)).thenReturn(usuarioSolicitado);
        when(postagemRepository.findAllByUsuarioAndPublico(usuarioSolicitado, true, pageable))
                .thenReturn(postagensPaginado);

        Page<ListarPostagensUsuarioResponse> response = tested.listar(idUsuario, pageable);

        verify(postagemRepository).findAllByUsuarioAndPublico(usuarioSolicitado, true, pageable);

        assertEquals(postagens.size(), response.getSize());
        assertEquals(postagens.get(0).getId(), response.getContent().get(0).getIdPostagem());
        assertEquals(postagens.get(1).getId(), response.getContent().get(1).getIdPostagem());
        assertEquals(postagens.get(2).getId(), response.getContent().get(2).getIdPostagem());
    }

    @Test
    @DisplayName("Deve listar postagens publicas e privadas do usuário se for amigo")
    void deveListarPostagensPublicasEPrivadasDoUsuarioAmigo() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Usuario usuarioSolicitado = UsuarioFactory.get();
        Long idUsuario = usuarioSolicitado.getId();

        Amizade amizadeAtivo = AmizadeFactory.getAceito();
        amizadeAtivo.setUsuarioAtivo(usuarioLogado);
        amizadeAtivo.setUsuarioAmigo(usuarioSolicitado);
        usuarioLogado.setAmizades(List.of(amizadeAtivo));

        Amizade amizadeAmigo = AmizadeFactory.getAceito();
        amizadeAmigo.setUsuarioAtivo(usuarioSolicitado);
        amizadeAmigo.setUsuarioAmigo(usuarioLogado);
        usuarioSolicitado.setAmizades(List.of(amizadeAmigo));

        List<Postagem> postagens = PostagemFactory.getList();
        Pageable pageable = PageRequest.of(0, 5);
        Page<Postagem> postagensPaginado = new PageImpl<>(postagens);

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(buscaUsuarioService.porId(idUsuario)).thenReturn(usuarioSolicitado);
        when(postagemRepository.findAllByUsuario(usuarioSolicitado, pageable))
                .thenReturn(postagensPaginado);

        Page<ListarPostagensUsuarioResponse> response = tested.listar(idUsuario, pageable);

        verify(postagemRepository).findAllByUsuario(usuarioSolicitado, pageable);

        assertEquals(postagens.size(), response.getSize());
        assertEquals(postagens.get(0).getId(), response.getContent().get(0).getIdPostagem());
        assertEquals(postagens.get(1).getId(), response.getContent().get(1).getIdPostagem());
        assertEquals(postagens.get(2).getId(), response.getContent().get(2).getIdPostagem());
    }
}