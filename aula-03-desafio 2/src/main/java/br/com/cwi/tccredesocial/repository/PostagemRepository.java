package br.com.cwi.tccredesocial.repository;

import br.com.cwi.tccredesocial.domain.Postagem;
import br.com.cwi.tccredesocial.domain.Situacao;
import br.com.cwi.tccredesocial.security.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    Page<Postagem> findAllDistinctByUsuarioIdOrUsuarioAmizadesUsuarioAmigoId(Long id, Long id1, Pageable pageable);

    Page<Postagem> findAllByUsuarioAndPublico(Usuario usuarioSolicitado, boolean b, Pageable pageable);

    Page<Postagem> findAllByUsuario(Usuario usuarioSolicitado, Pageable pageable);

    Page<Postagem> findAllDistinctByUsuarioIdOrUsuarioAmizadesUsuarioAmigoIdAndUsuarioAmizadesSituacao(Long id, Long id1, Situacao aceito, Pageable pageable);
}
