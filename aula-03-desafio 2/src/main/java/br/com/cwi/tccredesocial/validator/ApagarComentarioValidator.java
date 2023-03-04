package br.com.cwi.tccredesocial.validator;

import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class ApagarComentarioValidator {

    public void validar(Usuario usuarioLogado, Comentario comentario) {
        if (usuarioLogado.getId().equals(comentario.getUsuario().getId())) {
            return;
        }
        if (usuarioLogado.getId().equals(comentario.getPostagem().getUsuario().getId())) {
            return;
        }
        throw new ResponseStatusException(BAD_REQUEST,
                "Não é possível apagar o comentário. Só pode ser apagado comentários feitos por você ou em algum post seu.");
    }
}
