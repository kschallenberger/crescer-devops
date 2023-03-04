package br.com.cwi.tccredesocial.security.controller.response;

import br.com.cwi.tccredesocial.domain.Situacao;
import lombok.*;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter @Setter
public class ListarUsuarioResponse {

    private Long id;
    private String nome;
    private String apelido;
    private String imagem;
    private Situacao amigo;

}
