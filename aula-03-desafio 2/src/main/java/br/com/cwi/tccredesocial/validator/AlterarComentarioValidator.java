package br.com.cwi.tccredesocial.validator;

import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class AlterarComentarioValidator {

    public void validar(Usuario usuarioLogado, Comentario comentario) {
        if (!usuarioLogado.getId().equals(comentario.getUsuario().getId())) {
            throw new ResponseStatusException(BAD_REQUEST,
                "Só pode ser alterado comentários feitos pelo usuário");
        }
    }
}
