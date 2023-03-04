package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.request.AlterarComentarioRequest;
import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.factories.ComentarioFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.ComentarioRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaComentarioService;
import br.com.cwi.tccredesocial.validator.AlterarComentarioValidator;
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
public class AlterarComentarioServiceTest {

    @InjectMocks
    private AlterarComentarioService tested;

    @Mock
    private ComentarioRepository comentarioRepository;
    @Mock
    private BuscaComentarioService buscaComentarioService;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private AlterarComentarioValidator alterarComentarioValidator;
    @Captor
    private ArgumentCaptor<Comentario> comentarioCaptor;

    @Test
    @DisplayName("Deve alterar comentario")
    void deveAlterarComentario() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Comentario comentario = ComentarioFactory.getNovo();
        Long idComentario = comentario.getId();

        AlterarComentarioRequest request = AlterarComentarioRequest.builder()
                .texto("Novo teste")
                .build();

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(buscaComentarioService.porId(idComentario)).thenReturn(comentario);

        tested.alterar(idComentario, request);
        verify(comentarioRepository).save(comentarioCaptor.capture());

        assertEquals(request.getTexto(), comentarioCaptor.getValue().getTexto());
    }
}