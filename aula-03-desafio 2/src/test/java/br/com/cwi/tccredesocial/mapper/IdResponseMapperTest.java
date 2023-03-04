package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.controller.response.IdResponse;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class IdResponseMapperTest {

    @InjectMocks
    private IdResponseMapper tested;

    @Test
    @DisplayName("Deve retornar a response com o id informado")
    void deveRetornarEntitySolicitado() {
        Usuario usuario = UsuarioFactory.get();

        IdResponse response = tested.toResponse(usuario.getId());

        assertEquals(usuario.getId(), response.getId());
    }
}