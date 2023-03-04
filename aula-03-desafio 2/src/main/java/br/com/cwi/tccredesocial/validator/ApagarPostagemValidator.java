package br.com.cwi.tccredesocial.validator;

import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class ApagarPostagemValidator {

    public void validar(Usuario usuarioLogado, Postagem post) {
        if (!usuarioLogado.getId().equals(post.getUsuario().getId())) {
            throw new ResponseStatusException(BAD_REQUEST, "Não é possível apagar o post selecionado, pois ele não pertence ao usuário logado.");
        }
    }

}
