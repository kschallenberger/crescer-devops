package br.com.cwi.tccredesocial.mapper;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.security.domain.Usuario;

import java.time.LocalDateTime;

import static br.com.cwi.tccredesocial.domain.Situacao.PENDENTE;
import static br.com.cwi.tccredesocial.domain.Situacao.SOLICITADO;

public class SolicitarAmizadeMapper {

    public static Amizade toEntityAtivo(Usuario usuarioAtivo, Usuario usuarioAmigo) {
        return Amizade.builder()
                .usuarioAtivo(usuarioAtivo)
                .usuarioAmigo(usuarioAmigo)
                .situacao(SOLICITADO)
                .dataPedido(LocalDateTime.now())
                .build();
    }

    public static Amizade toEntityAmigo(Usuario usuarioAtivo, Usuario usuarioAmigo) {
        return Amizade.builder()
                .usuarioAtivo(usuarioAmigo)
                .usuarioAmigo(usuarioAtivo)
                .situacao(PENDENTE)
                .dataPedido(LocalDateTime.now())
                .build();
    }
}
