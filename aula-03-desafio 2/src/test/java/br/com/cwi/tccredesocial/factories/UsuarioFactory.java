package br.com.cwi.tccredesocial.factories;

import br.com.cwi.tccredesocial.security.domain.Usuario;

import java.time.LocalDate;

public class UsuarioFactory {
    public static Usuario get() {
        Usuario usuario = new Usuario();
        usuario.setId(SimpleFactory.getRandomLong());
        usuario.setNome("Usuário de teste");
        usuario.setEmail("teste@cwi.com.br");
        usuario.setAtivo(true);
        usuario.setDataNascimento(LocalDate.of(1990, 01, 01));
        return usuario;
    }
}
