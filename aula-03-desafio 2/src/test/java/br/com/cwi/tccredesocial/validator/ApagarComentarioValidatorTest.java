package br.com.cwi.tccredesocial.validator;

import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.factories.ComentarioFactory;
import br.com.cwi.tccredesocial.factories.PostagemFactory;
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
public class ApagarComentarioValidatorTest {

    @InjectMocks
    private ApagarComentarioValidator tested;

    @Test
    @DisplayName("Não deve ocorrer nenhum erro se o usuário tentar apagar comentario próprio")
    void naoDeveOcorrerErroUsuarioApagandoProprioComentario() {
        Usuario usuarioLogado = UsuarioFactory.get();

        Comentario comentario = ComentarioFactory.getNovo();
        comentario.setUsuario(usuarioLogado);

        tested.validar(usuarioLogado, comentario);
    }

    @Test
    @DisplayName("Não deve ocorrer nenhum erro se o usuário tentar apagar comentario de outro usuário em post seu")
    void naoDeveOcorrerErroUsuarioApagandoComentarioDeOutroUsuarioEmSeuPost() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Usuario outroUsuario = UsuarioFactory.get();

        Postagem postagem = PostagemFactory.get();
        postagem.setUsuario(usuarioLogado);
        Comentario comentario = ComentarioFactory.getNovo();
        comentario.setUsuario(outroUsuario);
        comentario.setPostagem(postagem);

        tested.validar(usuarioLogado, comentario);
    }

    @Test
    @DisplayName("Deve ocorrer erro se o usuario tentar apagar comentário que nao é dele em outro post")
    void deveRetornarErroAoInformarDoisIdIguais() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Usuario outroUsuario = UsuarioFactory.get();

        Postagem postagem = PostagemFactory.get();
        postagem.setUsuario(outroUsuario);
        Comentario comentario = ComentarioFactory.getNovo();
        comentario.setUsuario(outroUsuario);
        comentario.setPostagem(postagem);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuarioLogado, comentario);;
        });

        assertEquals("Não é possível apagar o comentário. Só pode ser apagado comentários feitos por você ou em algum post seu.", exception.getReason());
    }
}