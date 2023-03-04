package br.com.cwi.tccredesocial.security.validator;

import br.com.cwi.tccredesocial.security.controller.request.AlterarUsuarioRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AlterarUsuarioValidator {

    public void validar(AlterarUsuarioRequest request) {
        if (request.getNome().trim().length() < 5) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "nome deve ter no mínimo 5 caracteres");
        }
    }
}
