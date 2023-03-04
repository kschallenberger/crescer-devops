package br.com.cwi.tccredesocial.factories;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.security.domain.Usuario;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.cwi.tccredesocial.domain.Situacao.*;

public class AmizadeFactory {

    public static Amizade getSolicitado() {
        Amizade amizade = new Amizade();
        amizade.setId(SimpleFactory.getRandomLong());
        amizade.setSituacao(SOLICITADO);
        amizade.setDataPedido(LocalDateTime.of(2023, 02, 01, 00, 00, 00));
        return amizade;
    }

    public static Amizade getPendente() {
        Amizade amizade = new Amizade();
        amizade.setId(SimpleFactory.getRandomLong());
        amizade.setSituacao(PENDENTE);
        amizade.setDataPedido(LocalDateTime.of(2023, 02, 01, 00, 00, 00));
        return amizade;
    }

    public static Amizade getAceito() {
        Amizade amizade = new Amizade();
        amizade.setId(SimpleFactory.getRandomLong());
        amizade.setSituacao(ACEITO);
        amizade.setDataPedido(LocalDateTime.of(2023, 02, 01, 00, 00, 00));
        return amizade;
    }

    public static List<Amizade> getLista() {
        Usuario usuario1 = UsuarioFactory.get();
        Usuario usuario2 = UsuarioFactory.get();
        Usuario usuario3 = UsuarioFactory.get();
        Usuario usuario4 = UsuarioFactory.get();

        Amizade amizade1e2 = getAceito();
        amizade1e2.setUsuarioAtivo(usuario1);
        amizade1e2.setUsuarioAmigo(usuario2);

        Amizade amizade1e3 = getAceito();
        amizade1e3.setUsuarioAtivo(usuario1);
        amizade1e3.setUsuarioAmigo(usuario3);

        Amizade amizade1e4 = getAceito();
        amizade1e4.setUsuarioAtivo(usuario1);
        amizade1e4.setUsuarioAmigo(usuario4);

        return List.of(amizade1e2, amizade1e3, amizade1e4);
    }
}
