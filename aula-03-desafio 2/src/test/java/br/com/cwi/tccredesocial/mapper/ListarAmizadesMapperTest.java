package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.controller.response.ListarAmizadesResponse;
import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.factories.AmizadeFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ListarAmizadesMapperTest {

    @InjectMocks
    private ListarAmizadesMapper tested;

    @Test
    @DisplayName("Deve retornar a resposta com o último comentário, ordenado pela data mais atual")
    void deveRetornarComUltimoComentario() {
        Usuario usuarioAtivo = UsuarioFactory.get();
        Usuario usuarioAmigo = UsuarioFactory.get();
        Amizade amizade = AmizadeFactory.getAceito();
        amizade.setUsuarioAtivo(usuarioAtivo);
        amizade.setUsuarioAmigo(usuarioAmigo);

        ListarAmizadesResponse response = tested.toResponse(amizade);

        assertEquals(usuarioAmigo.getId(), response.getId());
        assertEquals(usuarioAmigo.getNome(), response.getNome());
        assertEquals(amizade.getSituacao(), response.getSituacao());
    }
}