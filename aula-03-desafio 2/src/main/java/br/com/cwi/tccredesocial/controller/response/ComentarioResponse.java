package br.com.cwi.tccredesocial.controller.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter @Setter
public class ComentarioResponse {

    private Long id;
    private String usuarioNome;
    private String usuarioImagem;
    private String texto;
    private LocalDateTime dataInclusao;

}
