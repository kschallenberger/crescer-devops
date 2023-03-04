package br.com.cwi.tccredesocial.controller.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter @Setter
public class AlterarComentarioRequest {

    @NotBlank
    @Size(max = 512)
    private String texto;

}
