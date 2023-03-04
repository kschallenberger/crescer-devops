package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.factories.AmizadeFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.AmizadeRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.tccredesocial.service.core.BuscaAmizadeService;
import br.com.cwi.tccredesocial.service.core.BuscaUsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemoverAmizadeServiceTest {

    @InjectMocks
    private RemoverAmizadeService tested;

    @Mock
    private AmizadeRepository amizadeRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;
    @Mock
    private BuscaUsuarioService buscaUsuarioService;
    @Mock
    private BuscaAmizadeService buscaAmizadeService;

    @Test
    @DisplayName("Deve remover amizade")
    void deveRemoverAmizade() {
        Usuario usuarioAtivo = UsuarioFactory.get();
        Usuario usuarioAmigo = UsuarioFactory.get();
        Long idUsuarioAmigo = usuarioAmigo.getId();

        Amizade amizadeAtivo = AmizadeFactory.getAceito();
        amizadeAtivo.setUsuarioAtivo(usuarioAtivo);
        amizadeAtivo.setUsuarioAmigo(usuarioAmigo);

        Amizade amizadeAmigo = AmizadeFactory.getAceito();
        amizadeAmigo.setUsuarioAtivo(usuarioAmigo);
        amizadeAmigo.setUsuarioAmigo(usuarioAtivo);

        when(buscaUsuarioService.porId(idUsuarioAmigo)).thenReturn(usuarioAmigo);
        when(usuarioAutenticadoService.get()).thenReturn(usuarioAtivo);
        when(buscaAmizadeService.porUsuario(usuarioAmigo, usuarioAtivo)).thenReturn(amizadeAmigo);
        when(buscaAmizadeService.porUsuario(usuarioAtivo, usuarioAmigo)).thenReturn(amizadeAtivo);

        tested.remover(idUsuarioAmigo);

        verify(amizadeRepository).delete(amizadeAmigo);
        verify(amizadeRepository).delete(amizadeAtivo);
    }
}