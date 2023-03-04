package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.response.ListarPostagensAmigosResponse;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.domain.Situacao;
import br.com.cwi.tccredesocial.factories.PostagemFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
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
public class ListarPostagensAmigosServiceTest {

    @InjectMocks
    private ListarPostagensAmigosService tested;

    @Mock
    private PostagemRepository postagemRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Test
    @DisplayName("Deve listar postagens para o feed paginado")
    void deveListarPostagensParaOFeedPaginado() {
        Usuario usuarioLogado = UsuarioFactory.get();
        List<Postagem> postagens = PostagemFactory.getList();
        Pageable pageable = PageRequest.of(0, 5);

        Page<Postagem> postagensPaginado = new PageImpl<>(postagens);

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(postagemRepository.findAllDistinctByUsuarioIdOrUsuarioAmizadesUsuarioAmigoIdAndUsuarioAmizadesSituacao
                (usuarioLogado.getId(), usuarioLogado.getId(), Situacao.ACEITO, pageable)).thenReturn(postagensPaginado);

        Page<ListarPostagensAmigosResponse> response = tested.listar(pageable);

        verify(postagemRepository).findAllDistinctByUsuarioIdOrUsuarioAmizadesUsuarioAmigoIdAndUsuarioAmizadesSituacao
                (usuarioLogado.getId(), usuarioLogado.getId(), Situacao.ACEITO, pageable);

        assertEquals(postagens.size(), response.getSize());
        assertEquals(postagens.get(0).getId(), response.getContent().get(0).getIdPostagem());
        assertEquals(postagens.get(1).getId(), response.getContent().get(1).getIdPostagem());
        assertEquals(postagens.get(2).getId(), response.getContent().get(2).getIdPostagem());
    }
}