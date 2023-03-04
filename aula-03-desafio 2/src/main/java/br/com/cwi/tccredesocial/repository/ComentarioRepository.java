package br.com.cwi.tccredesocial.repository;

import br.com.cwi.tccredesocial.domain.Comentario;
import br.com.cwi.tccredesocial.domain.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    void deleteAllByPostagem(Postagem post);

    void deleteAllByPostagemId(Long id);

    Page<Comentario> findAllByPostagemId(Long idPostagem, Pageable pageable);
}
