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
public class AlterarPostagemValidatorTest {

    @InjectMocks
    private AlterarPostagemValidator tested;

    @Test
    @DisplayName("Não deve ocorrer nenhum erro se o usuário tentar alterar post próprio")
    void naoDeveOcorrerErroUsuarioAlterandoProprioPost() {
        Usuario usuarioLogado = UsuarioFactory.get();

        Postagem postagem = PostagemFactory.get();
        postagem.setUsuario(usuarioLogado);

        tested.validar(usuarioLogado, postagem);
    }

    @Test
    @DisplayName("Deve ocorrer erro se o usuario tentar alterar post que nao é dele")
    void deveRetornarErroAoTentarAlterarPostDeOutroUsuario() {
        Usuario usuarioLogado = UsuarioFactory.get();
        Usuario outroUsuario = UsuarioFactory.get();

        Postagem postagem = PostagemFactory.get();
        postagem.setUsuario(outroUsuario);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuarioLogado, postagem);;
        });

        assertEquals("Não é possível alterar o post selecionado, pois ele não pertence ao usuário logado.", exception.getReason());
    }
}