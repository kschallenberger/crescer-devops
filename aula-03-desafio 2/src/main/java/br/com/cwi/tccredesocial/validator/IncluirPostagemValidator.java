package br.com.cwi.tccredesocial.validator;

import br.com.cwi.tccredesocial.controller.request.IncluirPostagemRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
public class IncluirPostagemValidator {
    public void validar(IncluirPostagemRequest request) {

        if (request.getTitulo().trim().length() < 2) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "título deve ter pelo menos 2 caracteres");
        }
    }
}
