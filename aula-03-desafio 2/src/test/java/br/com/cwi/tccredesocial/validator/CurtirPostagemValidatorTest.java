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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CurtirPostagemValidatorTest {

    @InjectMocks
    private CurtirPostagemValidator tested;

    @Test
    @DisplayName("Não deve ocorrer nenhum erro se o usuário não estiver na lista de usuários que curtiu o post")
    void naoDeveOcorrerErroSeUsuarioAindaNaoCurtiu() {
        Usuario usuarioLogado = UsuarioFactory.get();
        List<Usuario> usuariosCurtiu = new ArrayList<>();
        usuariosCurtiu.add(UsuarioFactory.get());
        usuariosCurtiu.add(UsuarioFactory.get());
        usuariosCurtiu.add(UsuarioFactory.get());
        usuariosCurtiu.add(UsuarioFactory.get());

        Postagem postagem = PostagemFactory.get();
        postagem.setPublico(true);
        postagem.setCurtidas(usuariosCurtiu);

        tested.validar(usuarioLogado, postagem);
    }

    @Test
    @DisplayName("Deve ocorrer erro quando o usuário já estiver na lista de quem curtiu o post")
    void deveRetornarErroSeUsuarioJaCurtiu() {
        Usuario usuarioLogado = UsuarioFactory.get();
        List<Usuario> usuariosCurtiu = new ArrayList<>();
        usuariosCurtiu.add(UsuarioFactory.get());
        usuariosCurtiu.add(UsuarioFactory.get());
        usuariosCurtiu.add(UsuarioFactory.get());
        usuariosCurtiu.add(UsuarioFactory.get());
        usuariosCurtiu.add(usuarioLogado);

        Postagem postagem = PostagemFactory.get();
        postagem.setPublico(true);
        postagem.setCurtidas(usuariosCurtiu);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuarioLogado, postagem);
        });

        assertEquals("Usuário já curtiu esse post", exception.getReason());
    }
}