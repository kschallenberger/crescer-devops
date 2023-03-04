package br.com.cwi.tccredesocial.controller.request;

import lombok.*;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter @Setter
public class AlterarPostagemRequest {

    private boolean publico;

}