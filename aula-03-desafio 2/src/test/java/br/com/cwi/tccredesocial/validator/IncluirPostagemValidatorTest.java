package br.com.cwi.tccredesocial.validator;

import br.com.cwi.tccredesocial.controller.request.IncluirPostagemRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class IncluirPostagemValidatorTest {

    @InjectMocks
    private IncluirPostagemValidator tested;

    @Test
    @DisplayName("Não deve ocorrer nenhum erro se o usuário informou titulo maior que 2 caracteres")
    void naoDeveOcorrerErroTituloMaiorQueDois() {
        IncluirPostagemRequest request = IncluirPostagemRequest.builder()
                .titulo("Teste")
                .build();

        tested.validar(request);
    }

    @Test
    @DisplayName("Deve ocorrer erro quando o usuário informar titulo com espaços e a quantidade de caracteres for menor que 2")
    void deveRetornarErroAoInformarTituloMenorDoisCaracteres() {
        IncluirPostagemRequest request = IncluirPostagemRequest.builder()
                .titulo("    T     ")
                .build();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tested.validar(request);
        });

        assertEquals("título deve ter pelo menos 2 caracteres", exception.getReason());
    }
}