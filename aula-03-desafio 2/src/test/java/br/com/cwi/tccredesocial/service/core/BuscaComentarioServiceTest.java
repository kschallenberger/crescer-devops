package br.com.cwi.tccredesocial.service.core;

import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.factories.ComentarioFactory;
import br.com.cwi.tccredesocial.repository.ComentarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscaComentarioServiceTest {

    @InjectMocks
    private BuscaComentarioService tested;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Test
    @DisplayName("Deve buscar o comentário por Id")
    void deveBuscaComentarioPorId() {
        Comentario comentario = ComentarioFactory.getNovo();
        Long idComentario = comentario.getId();

        when(comentarioRepository.findById(idComentario)).thenReturn(Optional.of(comentario));

        Comentario retorno = tested.porId(idComentario);

        verify(comentarioRepository).findById(idComentario);
        assertEquals(comentario, retorno);
    }

    @Test
    @DisplayName("Deve retornar erro quando não encontrar o comentário")
    void DeveRetornarErroQuandoNaoEncontrarComentario() {
        Comentario comentario = ComentarioFactory.getNovo();
        Long idComentario = comentario.getId();

        assertThrows(ResponseStatusException.class, () -> {
            tested.porId(idComentario);
        });
    }
}