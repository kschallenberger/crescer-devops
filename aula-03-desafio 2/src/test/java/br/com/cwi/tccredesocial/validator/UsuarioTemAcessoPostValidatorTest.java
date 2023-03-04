package br.com.cwi.tccredesocial.validator;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.factories.AmizadeFactory;
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
public class UsuarioTemAcessoPostValidatorTest {

    @InjectMocks
    private UsuarioTemAcessoPostValidator tested;

    @Test
    @DisplayName("Não deve ocorrer nenhum erro se o post for público")
    void naoDeveOcorrerErroPostPublico() {
        Usuario usuarioAtivo = UsuarioFactory.get();
        Postagem postagem = PostagemFactory.get();
        postagem.setPublico(true);

        tested.validar(usuarioAtivo, postagem);
    }

    @Test
    @DisplayName("Nao deve ocorrer nenhum erro se os usuarios forem amigos")
    void deveRetornarErroAoInformarDoisIdIguais() {
        Usuario usuarioAmigo = UsuarioFactory.get();
        Usuario usuarioAtivo = UsuarioFactory.get();

        Amizade amizadeAmigo = AmizadeFactory.getAceito();
        amizadeAmigo.setUsuarioAmigo(usuarioAmigo);
        amizadeAmigo.setUsuarioAtivo(usuarioAtivo);
        List<Amizade> amizadesAmigo = new ArrayList<>();
        Amizade amizadeAtivo = AmizadeFactory.getAceito();
        amizadeAtivo.setUsuarioAmigo(usuarioAtivo);
        amizadeAtivo.setUsuarioAtivo(usuarioAmigo);
        List<Amizade> amizadesAtivo = new ArrayList<>();
        amizadesAmigo.add(amizadeAtivo);
        amizadesAtivo.add(amizadeAmigo);

        usuarioAmigo.setAmizades(amizadesAmigo);
        usuarioAtivo.setAmizades(amizadesAtivo);
        Postagem postagem = PostagemFactory.get();
        postagem.setUsuario(usuarioAmigo);
        postagem.setPublico(false);

        tested.validar(usuarioAtivo, postagem);
    }

    @Test
    @DisplayName("Deve retornar erro se os usuarios nao forem amigos")
    void deveRetornarErroQuandoUsuariosNaoSaoAmigos() {
        Usuario usuarioAmigo = UsuarioFactory.get();
        Usuario usuarioAtivo = UsuarioFactory.get();

        Postagem postagem = PostagemFactory.get();
        postagem.setUsuario(usuarioAmigo);
        postagem.setPublico(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuarioAtivo, postagem);
        });

        assertEquals("Usuário não tem acesso à essa postagem", exception.getReason());
    }

    @Test
    @DisplayName("Deve retornar erro quando tiver solicitacao de amizade não aceita")
    void deveRetornarErroQuandoSolicitacaoAmizadeNaoAceita() {
        Usuario usuarioAmigo = UsuarioFactory.get();
        Usuario usuarioAtivo = UsuarioFactory.get();

        Amizade amizadeAmigo = AmizadeFactory.getPendente();
        amizadeAmigo.setUsuarioAmigo(usuarioAmigo);
        amizadeAmigo.setUsuarioAtivo(usuarioAtivo);
        List<Amizade> amizadesAmigo = new ArrayList<>();
        Amizade amizadeAtivo = AmizadeFactory.getSolicitado();
        amizadeAtivo.setUsuarioAmigo(usuarioAtivo);
        amizadeAtivo.setUsuarioAtivo(usuarioAmigo);
        List<Amizade> amizadesAtivo = new ArrayList<>();
        amizadesAmigo.add(amizadeAtivo);
        amizadesAtivo.add(amizadeAmigo);

        usuarioAmigo.setAmizades(amizadesAmigo);
        usuarioAtivo.setAmizades(amizadesAtivo);
        Postagem postagem = PostagemFactory.get();
        postagem.setUsuario(usuarioAmigo);
        postagem.setPublico(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuarioAtivo, postagem);
        });

        assertEquals("Usuário não tem acesso à essa postagem", exception.getReason());
    }
}