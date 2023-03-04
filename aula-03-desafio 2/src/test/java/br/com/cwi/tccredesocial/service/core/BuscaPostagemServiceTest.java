package br.com.cwi.tccredesocial.service.core;

import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.factories.PostagemFactory;
import br.com.cwi.tccredesocial.repository.PostagemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscaPostagemServiceTest {

    @InjectMocks
    private BuscaPostagemService tested;

    @Mock
    private PostagemRepository postagemRepository;

    @Test
    @DisplayName("Deve buscar a postagem por Id")
    void deveBuscaPostagemPorId() {
        Postagem postagem = PostagemFactory.get();
        Long idPostagem = postagem.getId();

        when(postagemRepository.findById(idPostagem)).thenReturn(Optional.of(postagem));

        Postagem retorno = tested.porId(idPostagem);

        verify(postagemRepository).findById(idPostagem);
        assertEquals(postagem, retorno);
    }

    @Test
    @DisplayName("Deve retornar erro quando não encontrar a postagem")
    void DeveRetornarErroQuandoNaoEncontrarPostagem() {
        Postagem postagem = PostagemFactory.get();
        Long idPostagem = postagem.getId();

        assertThrows(ResponseStatusException.class, () -> {
            tested.porId(idPostagem);
        });
    }
}