package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.request.AlterarPostagemRequest;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.factories.PostagemFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaPostagemService;
import br.com.cwi.tccredesocial.validator.AlterarPostagemValidator;
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
public class AlterarPostagemServiceTest {

    @InjectMocks
    private AlterarPostagemService tested;

    @Mock
    private PostagemRepository postagemRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private BuscaPostagemService buscaPostagemService;
    @Mock
    private AlterarPostagemValidator alterarPostagemValidator;
    @Captor
    private ArgumentCaptor<Postagem> postagemCaptor;

    @Test
    @DisplayName("Deve alterar postagem")
    void deveAlterarPostagem() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Postagem post = PostagemFactory.get();
        post.setUsuario(usuarioLogado);
        Long idPostagem = post.getId();

        AlterarPostagemRequest request = AlterarPostagemRequest.builder()
                .publico(false)
                .build();

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(buscaPostagemService.porId(idPostagem)).thenReturn(post);

        tested.alterar(idPostagem, request);
        verify(postagemRepository).save(postagemCaptor.capture());

        assertEquals(request.isPublico(), postagemCaptor.getValue().isPublico());
    }
}