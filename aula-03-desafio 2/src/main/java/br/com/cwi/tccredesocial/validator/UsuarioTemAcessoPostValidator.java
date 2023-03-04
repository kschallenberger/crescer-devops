package br.com.cwi.tccredesocial.validator;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static br.com.cwi.tccredesocial.domain.Situacao.ACEITO;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class UsuarioTemAcessoPostValidator {

    public static void validar(Usuario usuarioLogado, Postagem post) {
        if (post.isPublico()) {
            return;
        }
        if (usuarioLogado.getId().equals(post.getUsuario().getId())) {
            return;
        }
        Optional<Amizade> amizadeComUsuPost = usuarioLogado.getAmizades().stream()
                .filter(amizade -> amizade.getUsuarioAtivo().getId().equals(usuarioLogado.getId()) &&
                        amizade.getUsuarioAmigo().getId().equals(post.getUsuario().getId()))
                .findFirst();
        if (amizadeComUsuPost.isPresent() && amizadeComUsuPost.get().getSituacao().equals(ACEITO)) {
            return;
        }
        throw new ResponseStatusException(BAD_REQUEST, "Usuário não tem acesso à essa postagem");
    }
}
