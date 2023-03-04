package br.com.cwi.tccredesocial.service.core;

import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.repository.UsuarioRepository;
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
public class BuscaUsuarioServiceTest {

    @InjectMocks
    private BuscaUsuarioService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve buscar o usuário por Id")
    void deveBuscaUsuarioPorId() {
        Usuario usuarioSolicitado = UsuarioFactory.get();
        Long idUsuarioSolicitado = usuarioSolicitado.getId();

        when(usuarioRepository.findById(idUsuarioSolicitado)).thenReturn(Optional.of(usuarioSolicitado));

        Usuario retorno = tested.porId(idUsuarioSolicitado);

        verify(usuarioRepository).findById(idUsuarioSolicitado);
        assertEquals(usuarioSolicitado, retorno);
    }

    @Test
    @DisplayName("Deve retornar erro quando não encontrar o usuário")
    void DeveRetornarErroQuandoNaoEncontrarUsuario() {
        Usuario usuarioSolicitado = UsuarioFactory.get();
        Long idUsuarioSolicitado = usuarioSolicitado.getId();

        assertThrows(ResponseStatusException.class, () -> {
            tested.porId(idUsuarioSolicitado);
        });
    }
}