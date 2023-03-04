package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.controller.request.IncluirPostagemRequest;
import br.com.cwi.tccredesocial.domain.Postagem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class IncluirPostagemMapperTest {

    @InjectMocks
    private IncluirPostagemMapper tested;

    @Test
    @DisplayName("Deve retornar entidade de comentario")
    void deveRetornarComUltimoComentario() {
        IncluirPostagemRequest request = IncluirPostagemRequest.builder()
                .titulo("Titulo teste")
                .publico(true)
                .build();

        Postagem entity = tested.toEntity(request);

        assertEquals(request.getTitulo(), entity.getTitulo());
        assertEquals(request.isPublico(), entity.isPublico());
    }
}