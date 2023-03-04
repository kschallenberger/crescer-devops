package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.factories.AmizadeFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.AmizadeRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaAmizadeService;
import br.com.cwi.tccredesocial.service.core.BuscaUsuarioService;
import br.com.cwi.tccredesocial.service.core.ValidaAceitarAmizadeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AceitarAmizadeServiceTest {

    @InjectMocks
    private AceitarAmizadeService tested;

    @Mock
    private AmizadeRepository amizadeRepository;
    @Mock
    private ValidaAceitarAmizadeService validaAceitarAmizadeService;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private BuscaUsuarioService buscaUsuarioService;
    @Mock
    private BuscaAmizadeService buscaAmizadeService;
    @Captor
    private ArgumentCaptor<Amizade> amizadeCaptor;

    @Test
    @DisplayName("Deve aceitar solicitação de amizada")
    void deveAceitarAmizade() {
        Usuario usuarioAmigo = UsuarioFactory.get();
        Long idUsuarioAmigo = usuarioAmigo.getId();
        Usuario usuarioAtivo = UsuarioFactory.get();

        Amizade amizadeAmigo = AmizadeFactory.getSolicitado();
        amizadeAmigo.setUsuarioAmigo(usuarioAmigo);
        amizadeAmigo.setUsuarioAtivo(usuarioAtivo);
        Amizade amizadeAtivo = AmizadeFactory.getPendente();
        amizadeAtivo.setUsuarioAmigo(usuarioAtivo);
        amizadeAtivo.setUsuarioAtivo(usuarioAmigo);

        when(buscaUsuarioService.porId(idUsuarioAmigo)).thenReturn(usuarioAmigo);
        when(usuarioAutenticadoService.get()).thenReturn(usuarioAtivo);
        when(buscaAmizadeService.porUsuario(usuarioAmigo, usuarioAtivo)).thenReturn(amizadeAmigo);
        when(buscaAmizadeService.porUsuario(usuarioAtivo, usuarioAmigo)).thenReturn(amizadeAtivo);

        tested.aceitar(idUsuarioAmigo);

        verify(validaAceitarAmizadeService).validar(usuarioAtivo, usuarioAmigo);
        verify(buscaAmizadeService).porUsuario(usuarioAmigo, usuarioAtivo);
        verify(buscaAmizadeService).porUsuario(usuarioAtivo, usuarioAmigo);
        verify(amizadeRepository).save(amizadeAmigo);
        verify(amizadeRepository).save(amizadeAtivo);
    }
}