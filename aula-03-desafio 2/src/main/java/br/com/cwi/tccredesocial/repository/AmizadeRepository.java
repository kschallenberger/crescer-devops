package br.com.cwi.tccredesocial.repository;

import br.com.cwi.tccredesocial.domain.Amizade;
import br.com.cwi.tccredesocial.domain.Situacao;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmizadeRepository extends JpaRepository<Amizade, Long> {

    Amizade findByUsuarioAtivoAndUsuarioAmigo(Usuario usuarioAtivo, Usuario usuarioAmigo);

    Page<Amizade> findAllByUsuarioAtivoAndUsuarioAmigoNomeContainingIgnoreCaseOrUsuarioAtivoAndUsuarioAmigoEmailContainingIgnoreCase(Usuario usuarioLogado, String search, Usuario usuarioLogado1, String search1, Pageable pageable);

    List<Amizade> findByUsuarioAtivo(Usuario usuarioLogado);
}
