package br.com.cwi.tccredesocial.service;

import br.com.cwi.tccredesocial.controller.response.ListarAmizadesResponse;
import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.factories.AmizadeFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.repository.AmizadeRepository;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import br.com.cwi.tccredesocial.security.service.UsuarioAutenticadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarAmizadeServiceTest {

    @InjectMocks
    private ListarAmizadeService tested;
    @Mock
    private AmizadeRepository amizadeRepository;
    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Test
    @DisplayName("Deve listar amizades do usuário autenticado")
    void deveListarAmizades() {
        Usuario usuarioLogado = UsuarioFactory.get();
        String search = "";
        Pageable pageable = PageRequest.of(0, 5);

        List<Amizade> amizades = AmizadeFactory.getLista();
        Page<Amizade> amizadesPaginado = new PageImpl<>(amizades);

        when(usuarioAutenticadoService.get()).thenReturn(usuarioLogado);
        when(amizadeRepository
                .findAllByUsuarioAtivoAndUsuarioAmigoNomeContainingIgnoreCaseOrUsuarioAtivoAndUsuarioAmigoEmailContainingIgnoreCase
                    (usuarioLogado, search, usuarioLogado, search, pageable))
                        .thenReturn(amizadesPaginado);

        Page<ListarAmizadesResponse> response = tested.listar(search, pageable);

        verify(amizadeRepository).findAllByUsuarioAtivoAndUsuarioAmigoNomeContainingIgnoreCaseOrUsuarioAtivoAndUsuarioAmigoEmailContainingIgnoreCase
                (usuarioLogado, search, usuarioLogado, search, pageable);

        assertEquals(amizades.size(), response.getSize());
        assertEquals(amizades.get(0).getUsuarioAmigo().getId(), response.getContent().get(0).getId());
        assertEquals(amizades.get(1).getUsuarioAmigo().getId(), response.getContent().get(1).getId());
        assertEquals(amizades.get(2).getUsuarioAmigo().getId(), response.getContent().get(2).getId());
    }
}