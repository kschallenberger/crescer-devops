package br.com.cwi.tccredesocial.controller.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder @AllArgsConstructor
@NoArgsConstructor @Getter @Setter
public class ListarPostagemCompletaResponse {

    private Long idUsuario;
    private String nomeUsuario;
    private String apelidoUsuario;
    private String imagemUsuario;
    private Long idPostagem;
    private String titulo;
    private String descricao;
    private String imagem;
    private LocalDateTime dataInclusao;
    private boolean publico;
    private ComentarioResponse comentario;
    private int qtdComentarios;
    private int qtdCurtidas;
    private boolean usuarioCurtiu;

}
