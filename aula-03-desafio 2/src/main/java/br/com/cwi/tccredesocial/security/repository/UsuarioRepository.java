package br.com.cwi.tccredesocial.security.repository;

import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<Usuario> findAllByAtivoAndNomeContainingIgnoreCaseOrAtivoAndEmailContainingIgnoreCase(boolean b, String search, boolean b1, String search1, Pageable pageable);
}
