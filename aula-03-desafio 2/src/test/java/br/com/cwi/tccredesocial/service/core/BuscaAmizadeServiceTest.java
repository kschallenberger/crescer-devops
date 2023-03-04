package br.com.cwi.tccredesocial.service.core;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.factories.AmizadeFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.AmizadeRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscaAmizadeServiceTest {

    @InjectMocks
    private BuscaAmizadeService tested;

    @Mock
    private AmizadeRepository amizadeRepository;

    @Test
    @DisplayName("Deve buscar a Amizade por usuario")
    void deveBuscaAmizadePorUsuario() {
        Usuario usuarioAtivo = UsuarioFactory.get();
        Usuario usuarioAmigo = UsuarioFactory.get();

        Amizade amizade = AmizadeFactory.getAceito();
        amizade.setUsuarioAtivo(usuarioAtivo);
        amizade.setUsuarioAmigo(usuarioAmigo);

        when(amizadeRepository.findByUsuarioAtivoAndUsuarioAmigo(usuarioAtivo, usuarioAmigo))
                .thenReturn(amizade);

        Amizade retorno = tested.porUsuario(usuarioAtivo, usuarioAmigo);

        verify(amizadeRepository).findByUsuarioAtivoAndUsuarioAmigo(usuarioAtivo, usuarioAmigo);
        assertEquals(amizade, retorno);
    }

    @Test
    @DisplayName("Deve retornar erro quando não encontrar amizade")
    void DeveRetornarErroQuandoNaoEncontrarAmizade() {
        Usuario usuarioAtivo = UsuarioFactory.get();
        Usuario usuarioAmigo = UsuarioFactory.get();

        assertThrows(ResponseStatusException.class, () -> {
            tested.porUsuario(usuarioAtivo, usuarioAmigo);
        });
    }
}