package br.com.cwi.tccredesocial.security.controller.response;

import lombok.*;

import java.time.LocalDate;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter @Setter
public class DetalhesUsuarioResponse {

    private String nome;
    private String email;
    private String apelido;
    private String imagem;
    private LocalDate dataNascimento;

}
