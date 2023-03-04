package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.cwi.tccredesocial.domain.Situacao.PENDENTE;
import static br.com.cwi.tccredesocial.domain.Situacao.SOLICITADO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SolicitarAmizadeMapperTest {

    @InjectMocks
    private SolicitarAmizadeMapper tested;

    @Test
    @DisplayName("Deve retornar a entity do usuario ativo como solicitado quando receber solicitacao amizade")
    void deveRetornarEntitySolicitado() {
        Usuario usuarioSolicitante = UsuarioFactory.get();
        Usuario usuarioSolicitado = UsuarioFactory.get();

        Amizade amizadeEntity = tested.toEntityAtivo(usuarioSolicitante, usuarioSolicitado);

        assertEquals(usuarioSolicitante, amizadeEntity.getUsuarioAtivo());
        assertEquals(usuarioSolicitado, amizadeEntity.getUsuarioAmigo());
        assertEquals(SOLICITADO, amizadeEntity.getSituacao());
    }

    @Test
    @DisplayName("Deve retornar a entity do usuario amigo como pendente quando receber solicitacao amizade")
    void deveRetornarEntityPendente() {
        Usuario usuarioSolicitante = UsuarioFactory.get();
        Usuario usuarioSolicitado = UsuarioFactory.get();

        Amizade amizadeEntity = tested.toEntityAmigo(usuarioSolicitante, usuarioSolicitado);

        assertEquals(usuarioSolicitado, amizadeEntity.getUsuarioAtivo());
        assertEquals(usuarioSolicitante, amizadeEntity.getUsuarioAmigo());
        assertEquals(PENDENTE, amizadeEntity.getSituacao());
    }
}