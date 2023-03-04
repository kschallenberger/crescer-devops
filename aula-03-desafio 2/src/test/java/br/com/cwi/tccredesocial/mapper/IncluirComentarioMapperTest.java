package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.controller.request.IncluirComentarioRequest;
import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class IncluirComentarioMapperTest {

    @InjectMocks
    private IncluirComentarioMapper tested;

    @Test
    @DisplayName("Deve retornar entidade de comentario")
    void deveRetornarComUltimoComentario() {
        Usuario usuario = UsuarioFactory.get();
        IncluirComentarioRequest request = IncluirComentarioRequest.builder()
                .texto("Texto teste")
                .build();

        Comentario entity = tested.toEntity(usuario, request);

        assertEquals(request.getTexto(), entity.getTexto());
        assertEquals(usuario, entity.getUsuario());
    }
}