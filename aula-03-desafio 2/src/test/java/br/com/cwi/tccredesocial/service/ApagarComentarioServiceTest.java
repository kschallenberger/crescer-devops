package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.factories.ComentarioFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.ComentarioRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaComentarioService;
import br.com.cwi.tccredesocial.validator.ApagarComentarioValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApagarComentarioServiceTest {

    @InjectMocks
    private ApagarComentarioService tested;

    @Mock
    private ComentarioRepository comentarioRepository;
    @Mock
    private BuscaComentarioService buscaComentarioService;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private ApagarComentarioValidator apagarComentarioValidator;

    @Test
    @DisplayName("Deve alterar comentario")
    void deveAlterarComentario() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Comentario comentario = ComentarioFactory.getNovo();
        Long idComentario = comentario.getId();

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(buscaComentarioService.porId(idComentario)).thenReturn(comentario);

        tested.apagar(idComentario);
        verify(comentarioRepository).delete(comentario);
    }
}