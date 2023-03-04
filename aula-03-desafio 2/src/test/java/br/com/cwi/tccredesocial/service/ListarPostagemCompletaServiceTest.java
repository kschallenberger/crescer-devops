package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.response.ListarPostagemCompletaResponse;
import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.factories.ComentarioFactory;
import br.com.cwi.tccredesocial.factories.PostagemFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.ComentarioRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaPostagemService;
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
public class ListarPostagemCompletaServiceTest {

    @InjectMocks
    private ListarPostagemCompletaService tested;
    @Mock
    private ComentarioRepository comentarioRepository;
    @Mock
    private BuscaPostagemService buscaPostagemService;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Test
    @DisplayName("Deve listar postagem completa com comentarios paginados")
    void deveListarPostagemCompletaComComentariosPaginados() {
        Usuario usuarioLogado = UsuarioFactory.get();

        Postagem postagem = PostagemFactory.get();
        postagem.setPublico(true);
        postagem.setUsuario(usuarioLogado);
        Long idPostagem = postagem.getId();
        List<Comentario> comentarios = ComentarioFactory.getList(postagem);

        Pageable pageable = PageRequest.of(0, 5);

        Page<Comentario> comentariosPaginado = new PageImpl<>(comentarios);

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(buscaPostagemService.porId(idPostagem)).thenReturn(postagem);

        when(comentarioRepository.findAllByPostagemId(idPostagem, pageable))
                        .thenReturn(comentariosPaginado);

        Page<ListarPostagemCompletaResponse> response = tested.listar(idPostagem, pageable);

        verify(comentarioRepository).findAllByPostagemId(idPostagem, pageable);

        assertEquals(comentarios.size(), response.getSize());
        assertEquals(comentarios.get(0).getId(), response.getContent().get(0).getComentario().getId());
        assertEquals(comentarios.get(1).getId(), response.getContent().get(1).getComentario().getId());
        assertEquals(comentarios.get(2).getId(), response.getContent().get(2).getComentario().getId());
    }
}