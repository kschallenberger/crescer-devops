package br.com.cwi.tccredesocial.security.controller.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class AlterarUsuarioRequest {

    @NotBlank
    @Size(min = 5, max = 255)
    private String nome;

    @Size(max = 50)
    private String apelido;

    @URL
    private String imagem;

}
