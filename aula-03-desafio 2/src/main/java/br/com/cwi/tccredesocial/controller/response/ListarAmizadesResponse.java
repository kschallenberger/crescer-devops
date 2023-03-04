package br.com.cwi.tccredesocial.controller.response;

import br.com.cwi.tccredesocial.domain.Situacao;
import lombok.*;

import java.time.LocalDateTime;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter @Setter
public class ListarAmizadesResponse {

    private Long id;
    private String nome;
    private String apelido;
    private String imagem;
    private Situacao situacao;
    private LocalDateTime dataSolicitacao;

}
