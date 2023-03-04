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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidaAceitarAmizadeServiceTest {

    @InjectMocks
    private ValidaAceitarAmizadeService tested;

    @Mock
    private AmizadeRepository amizadeRepository;

    @Test
    @DisplayName("Deve validar corretamente dados de aceitar amizade")
    void deveValidarCorretamenteAceitarAmizade() {
        Usuario usuarioSolicitado = UsuarioFactory.get();
        Long idUsuarioSolicitado = usuarioSolicitado.getId();
        Usuario usuarioLogado = UsuarioFactory.get();
        Long idUsuarioSolicitante = usuarioLogado.getId();
        Amizade amizade = AmizadeFactory.getPendente();
        amizade.setUsuarioAtivo(usuarioLogado);
        amizade.setUsuarioAmigo(usuarioSolicitado);

        when(amizadeRepository.findByUsuarioAtivoAndUsuarioAmigo(usuarioLogado, usuarioSolicitado))
                .thenReturn(amizade);

        tested.validar(usuarioLogado, usuarioSolicitado);
    }

    @Test
    @DisplayName("Deve retornar erro ao informar id igual")
    void deveRetornarErroAoInformarDoisIdIguais() {
        Usuario usuario = UsuarioFactory.get();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuario, usuario);
        });
        assertEquals("Id do usuário é igual ao usuário solicitante", exception.getReason());
    }

    @Test
    @DisplayName("Deve retornar erro ao informar id de usuario que nao possui solicitacao amizade")
    void deveRetornarErroAoInformarIdAmizadeSemSolicitacao() {
        Usuario usuarioSolicitado = UsuarioFactory.get();
        Long idUsuarioSolicitado = usuarioSolicitado.getId();
        Usuario usuarioLogado = UsuarioFactory.get();
        Long idUsuarioSolicitante = usuarioLogado.getId();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuarioLogado, usuarioSolicitado);
        });
        assertEquals("Não existe um pedido de amizade pendente", exception.getReason());
    }

    @Test
    @DisplayName("Deve retornar erro ao informar id de usuario que já é amigo")
    void deveRetornarErroAoInformarIdAmizadeQueEhAmigo() {
        Usuario usuarioSolicitado = UsuarioFactory.get();
        Long idUsuarioSolicitado = usuarioSolicitado.getId();
        Usuario usuarioLogado = UsuarioFactory.get();
        Long idUsuarioSolicitante = usuarioLogado.getId();
        Amizade amizade = AmizadeFactory.getAceito();

        when(amizadeRepository.findByUsuarioAtivoAndUsuarioAmigo(usuarioLogado, usuarioSolicitado))
                .thenReturn(amizade);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuarioLogado, usuarioSolicitado);
        });
        assertEquals("O usuário já é seu amigo", exception.getReason());
    }

    @Test
    @DisplayName("Deve retornar erro ao informar id de usuario que está com o pedido de amizade pendente")
    void deveRetornarErroAoInformarIdAmizadeQueEstaComPedidoPendente() {
        Usuario usuarioSolicitado = UsuarioFactory.get();
        Usuario usuarioLogado = UsuarioFactory.get();
        Amizade amizade = AmizadeFactory.getSolicitado();

        when(amizadeRepository.findByUsuarioAtivoAndUsuarioAmigo(usuarioLogado, usuarioSolicitado))
                .thenReturn(amizade);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuarioLogado, usuarioSolicitado);
        });
        assertEquals("Aguarde o usuário aceitar o pedido de amizade!", exception.getReason());
    }
}