package br.com.cwi.tccredesocial.validator;

import br.com.cwi.tccredesocial.domain.Postagem;
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
public class ApagarPostagemValidatorTest {

    @InjectMocks
    private ApagarPostagemValidator tested;

    @Test
    @DisplayName("Não deve ocorrer nenhum erro se o usuário tentar apagar post próprio")
    void naoDeveOcorrerErroUsuarioApagandoProprioPost() {
        Usuario usuarioLogado = UsuarioFactory.get();

        Postagem postagem = PostagemFactory.get();
        postagem.setUsuario(usuarioLogado);

        tested.validar(usuarioLogado, postagem);
    }

    @Test
    @DisplayName("Deve ocorrer erro se o usuario tentar apagar post que nao é dele")
    void deveRetornarErroAoTentarApagarPostDeOutroUsuario() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Usuario outroUsuario = UsuarioFactory.get();

        Postagem postagem = PostagemFactory.get();
        postagem.setUsuario(outroUsuario);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuarioLogado, postagem);;
        });

        assertEquals("Não é possível apagar o post selecionado, pois ele não pertence ao usuário logado.", exception.getReason());
    }
}