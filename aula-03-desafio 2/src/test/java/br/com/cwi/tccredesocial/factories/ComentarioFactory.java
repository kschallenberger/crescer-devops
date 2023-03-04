package br.com.cwi.tccredesocial.factories;

import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.domain.Postagem;

import java.time.LocalDateTime;
import java.util.List;

public class ComentarioFactory {

    public static Comentario getNovo() {
        Comentario comentario = new Comentario();
        comentario.setId(SimpleFactory.getRandomLong());
        comentario.setTexto("Comentario teste");
        comentario.setDataInclusao(LocalDateTime.of(2023,02,01,00,00,00));
        return comentario;
    }

    public static Comentario getVelho() {
        Comentario comentario = new Comentario();
        comentario.setId(SimpleFactory.getRandomLong());
        comentario.setTexto("Comentario teste");
        comentario.setDataInclusao(LocalDateTime.of(2023,01,01,00,00,00));
        return comentario;
    }

    public static List<Comentario> getList(Postagem postagem) {
        Comentario comentario1 = getNovo();
        comentario1.setPostagem(postagem);
        comentario1.setUsuario(UsuarioFactory.get());
        Comentario comentario2 = getNovo();
        comentario2.setPostagem(postagem);
        comentario2.setUsuario(UsuarioFactory.get());
        Comentario comentario3 = getNovo();
        comentario3.setPostagem(postagem);
        comentario3.setUsuario(UsuarioFactory.get());

        return List.of(comentario1, comentario2, comentario3);
    }
}
