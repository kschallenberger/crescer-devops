package br.com.cwi.tccredesocial.controller.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter @Setter
public class ListarPostagensUsuarioResponse {

    private Long idPostagem;
    private String titulo;
    private String descricao;
    private String imagem;
    private LocalDateTime dataInclusao;
    private boolean publico;
    private ComentarioResponse ultimoComentario;
    private int qtdComentarios;
    private int qtdCurtidas;
    private Boolean usuarioCurtiu;

}
