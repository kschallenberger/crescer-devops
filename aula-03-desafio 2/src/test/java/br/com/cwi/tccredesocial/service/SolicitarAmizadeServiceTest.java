package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.AmizadeRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaUsuarioService;
import br.com.cwi.tccredesocial.service.core.ValidaSolicitarAmizadeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SolicitarAmizadeServiceTest {

    @InjectMocks
    private SolicitarAmizadeService tested;

    @Mock
    private AmizadeRepository amizadeRepository;
    @Mock
    private ValidaSolicitarAmizadeService validaSolicitarAmizadeService;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private BuscaUsuarioService buscaUsuarioService;

    @Test
    @DisplayName("Deve fazer nova solicitação de amizade")
    void deveSolicitarAmizade() {
        Usuario usuarioSolicitado = UsuarioFactory.get();
        Long idUsuarioSolicitado = usuarioSolicitado.getId();
        Usuario usuarioLogado = UsuarioFactory.get();

        when(buscaUsuarioService.porId(idUsuarioSolicitado)).thenReturn(usuarioSolicitado);
        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);

        tested.solicitar(usuarioSolicitado.getId());

        verify(validaSolicitarAmizadeService).validar(usuarioLogado, usuarioSolicitado);
    }
}