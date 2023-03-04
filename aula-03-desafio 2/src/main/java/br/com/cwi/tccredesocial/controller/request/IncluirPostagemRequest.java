package br.com.cwi.tccredesocial.controller.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter @Setter
public class IncluirPostagemRequest {

    @NotBlank
    @Size(min = 2, max = 100)
    private String titulo;
    @NotBlank
    @Size(max = 512)
    private String descricao;
    @Size(max = 512)
    private String imagem;
    private boolean publico;

}
