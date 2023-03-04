package br.com.cwi.tccredesocial.factories;

import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.security.domain.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class PostagemFactory {

    public static Postagem get() {
        Postagem postagem = new Postagem();
        postagem.setId(SimpleFactory.getRandomLong());
        postagem.setTitulo("Titulo teste");
        postagem.setDataInclusao(LocalDateTime.of(2023,02,01,00,00,00));
        return postagem;
    }

    public static List<Postagem> getList() {
        Usuario usuario = UsuarioFactory.get();
        Postagem postagem1 = get();
        postagem1.setUsuario(usuario);
        Postagem postagem2 = get();
        postagem2.setUsuario(usuario);
        Postagem postagem3 = get();
        postagem3.setUsuario(usuario);

        return List.of(postagem1, postagem2, postagem3);
    }
}
