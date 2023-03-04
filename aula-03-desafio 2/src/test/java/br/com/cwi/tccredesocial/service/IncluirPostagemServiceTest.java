package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.request.IncluirPostagemRequest;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.validator.IncluirPostagemValidator;
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
public class IncluirPostagemServiceTest {

    @InjectMocks
    private IncluirPostagemService tested;

    @Mock
    private PostagemRepository postagemRepository;
    @Mock
    private IncluirPostagemValidator incluirPostagemValidator;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Captor
    private ArgumentCaptor<Postagem> postagemCaptor;

    @Test
    @DisplayName("Deve incluir nova postagem")
    void deveIncluirNovaPostagem() {
        Usuario usuarioLogado = UsuarioFactory.get();
        IncluirPostagemRequest request = IncluirPostagemRequest.builder()
                .titulo("Teste")
                .publico(true)
                .build();

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        tested.incluir(request);
        verify(postagemRepository).save(postagemCaptor.capture());

        assertEquals(request.getTitulo(), postagemCaptor.getValue().getTitulo());
    }
}