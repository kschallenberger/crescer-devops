package br.com.cwi.tccredesocial.validator;

import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.factories.ComentarioFactory;
import br.com.cwi.tccredesocial.factories.UsuarioFactory;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AlterarComentarioValidatorTest {

    @InjectMocks
    private AlterarComentarioValidator tested;

    @Test
    @DisplayName("Não deve ocorrer nenhum erro se o usuário tentar alterar comentario próprio")
    void naoDeveOcorrerErroUsuarioAlterandoProprioComentario() {
        Usuario usuarioLogado = UsuarioFactory.get();

        Comentario comentario = ComentarioFactory.getNovo();
        comentario.setUsuario(usuarioLogado);

        tested.validar(usuarioLogado, comentario);
    }

    @Test
    @DisplayName("Deve ocorrer erro se o usuario tentar alterar comentário que nao é dele")
    void deveRetornarErroAoInformarDoisIdIguais() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Usuario outroUsuario = UsuarioFactory.get();

        Comentario comentario = ComentarioFactory.getNovo();
        comentario.setUsuario(outroUsuario);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuarioLogado, comentario);;
        });

        assertEquals("Só pode ser alterado comentários feitos pelo usuário", exception.getReason());
    }
}